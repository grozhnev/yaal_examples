package scala.file

import java.io.{BufferedReader, FileReader}

import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable.ListBuffer
import scala.io.Source

class ReadTextFileTest extends FlatSpec with Matchers {

  it should "read whole file to string" in {
    val uri = getClass.getResource("readme.txt").toURI
    val content = Source.fromFile(uri).getLines.mkString("\n")
    content shouldEqual "hi\nbye"
  }

  it should "read while readLine not null" in {
    val file = getClass.getResource("readme.txt").getFile
    val br = new BufferedReader(new FileReader(file))
    val lines: ListBuffer[String] = ListBuffer()
    var line: String = null
    while ( {
      line = br.readLine
      line != null
    }) {
      lines += line
    }
    br.close()
    val content = lines.mkString("\n")
    content shouldEqual "hi\nbye"
  }

}
