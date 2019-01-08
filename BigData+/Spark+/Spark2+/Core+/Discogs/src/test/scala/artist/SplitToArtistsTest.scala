package artist

import java.io._

import org.apache.hadoop.fs.FileSystem
import org.scalatest.{FlatSpec, Matchers}
import util.Factory

class SplitToArtistsTest extends FlatSpec with Matchers {

  it should "convert XML gz archive to a sequence file with artists" in {
    val gzFile = getClass.getResource("artists_sample_10.xml.gz").getFile
    val sequenceFileUri = File.createTempFile(getClass.getSimpleName, ".seq").toURI
    println("Temp sequence file: " + sequenceFileUri)
    val sc = Factory.sc
    val conf = Factory.sc.hadoopConfiguration
    val hadoopConf = sc.hadoopConfiguration
    val fs = FileSystem.get(hadoopConf)
    SplitXmlByArtist.convertXmlGzToSequenceFile(gzFile, sequenceFileUri, sc, conf, fs)

    val actMap = SplitXmlByArtist.readToMap(sequenceFileUri)
    println("Map size:" + actMap.size())
    actMap should have size 10
  }

}
