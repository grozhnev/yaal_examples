package artist

import java.io._
import java.net.URI
import java.util.zip.GZIPInputStream

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.io.SequenceFile.Writer
import org.apache.hadoop.io._
import org.apache.hadoop.io.compress.DefaultCodec
import org.apache.hadoop.util.ReflectionUtils
import org.scalatest.{FlatSpec, Matchers}
import util.Factory
import java.util

class SplitToArtistsTest extends FlatSpec with Matchers {

  it should "init RDD from text file packaged with GZ" in {
    val gzFile = getClass.getResource("artists_sample_10.xml.gz").getFile
    val sequenceFileUri = File.createTempFile(getClass.getSimpleName, ".seq").toURI
    writeSequenceFile(gzFile, sequenceFileUri)

    val actMap = readToMap(sequenceFileUri)
    println("Act map: " + actMap)
    actMap should have size 10
  }

  def writeSequenceFile(gzFile: String, sequenceFileUri: URI): Unit = {
    val is: BufferedReader = null
    var writer: Writer = null
    try {
      val is = new BufferedReader(new InputStreamReader(
        new GZIPInputStream(new BufferedInputStream(new FileInputStream(gzFile)))))

      writer = prepareOutputWriter(sequenceFileUri)

      var line: String = ""
      var nextLine: String = ""
      while ( {
        nextLine = is.readLine
        nextLine != null
      }) {
        line = line + nextLine
        val beginIndex = line.indexOf("<artist>")
        val endIndex = line.indexOf("</artist>")
        val endIndexFull = endIndex + "</artist>".length
        if (beginIndex > 0 && endIndex > 0) {
          val artist = line.substring(beginIndex, endIndexFull)
          saveArtist(writer, artist)
          line = line.substring(endIndexFull)
        }
      }
    } finally {
      if (is != null) {
        is.close()
      }
      if (writer != null) {
        IOUtils.closeStream(writer)
      }
    }
  }

  val seqKey = new IntWritable
  val seqValue = new Text

  def prepareOutputWriter(sequenceFileUri: URI): Writer = {
    val conf = Factory.sc.hadoopConfiguration
    val fs = FileSystem.get(conf)
    val path = new Path(sequenceFileUri)
    var writer: Writer = null
    writer = SequenceFile.createWriter(
      conf,
      Writer.file(path),
      Writer.keyClass(seqKey.getClass),
      Writer.valueClass(seqValue.getClass),
      Writer.bufferSize(fs.getConf.getInt("io.file.buffer.size", 4096)),
      Writer.replication(fs.getDefaultReplication(path)),
      Writer.blockSize(1073741824),
      Writer.compression(SequenceFile.CompressionType.BLOCK, new DefaultCodec),
      Writer.progressable(null),
      Writer.metadata(new SequenceFile.Metadata))
    writer
  }

  var artistCounter = 0

  def saveArtist(writer: Writer, artist: String): Unit = {
    seqKey.set(artistCounter)
    seqValue.set(artist)
    writer.append(seqKey, seqValue)
    artistCounter += 1
  }

  def readToMap(sequenceFileUri: URI): util.Map[Integer, String] = {
    val conf: Configuration = new Configuration
    val path: Path = new Path(sequenceFileUri)
    var reader: SequenceFile.Reader = null
    val content: util.Map[Integer, String] = new util.HashMap[Integer, String]
    try {
      reader = new SequenceFile.Reader(conf,
        SequenceFile.Reader.file(path),
        SequenceFile.Reader.bufferSize(4096),
        SequenceFile.Reader.start(0))
      val key: IntWritable = ReflectionUtils.newInstance(reader.getKeyClass, conf).asInstanceOf[IntWritable]
      val value: Text = ReflectionUtils.newInstance(reader.getValueClass, conf).asInstanceOf[Text]
      while ( {
        reader.next(key, value)
      }) {
        val keyData: Integer = key.get
        val valueData: String = value.toString
        content.put(keyData, valueData)
      }
    } finally IOUtils.closeStream(reader)
    content
  }

}
