package core.persistence

import core.Factory
import org.scalatest.{FlatSpec, Matchers}

class ListAllPersistedRdd extends FlatSpec with Matchers {

  it should "enumerate all persisted RDD" in {
    val sc = Factory.sc
    sc.getPersistentRDDs shouldBe empty

    val rdd = sc.parallelize(Seq(1, 2, 3))
    sc.getPersistentRDDs shouldBe empty

    rdd.cache()
    sc.getPersistentRDDs.values should contain only rdd

    rdd.unpersist()
    sc.getPersistentRDDs shouldBe empty
  }

}
