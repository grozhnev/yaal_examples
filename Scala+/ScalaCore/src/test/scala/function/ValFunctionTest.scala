package function

import org.scalatest.{FlatSpec, Matchers}

class ValFunctionTest extends FlatSpec with Matchers {

  "without return type" should "works" in {
    val add = (a: Int, b: Int) => a + b
    add(1, 2) shouldEqual 3
  }

  "with return type" should "works" in {
    val add: (Int, Int) => Int = (x, y) => x + y
    add(1, 2) shouldEqual 3
  }

  "with return type with braces" should "works" in {
    val add: (Int, Int) => Int = (x, y) => {
      x + y
    }
    add(1, 2) shouldEqual 3
  }

}
