package org.glavo.num

import org.scalatest.{FunSuite, Matchers}

class SliceTest extends FunSuite with Matchers {
  test("slice to string") {
    Slice(Array(100, 200, 300)).toString should be("Slice(100, 200, 300)")
    Slice(Array(100, 200), 1).toString should be("Slice(200)")
  }

  test("slice to array") {
    Slice(Array(100, 200, 300)).toArray should be(Array(100, 200, 300))
    Slice(Array(100, 200, 300), 1).toArray should be(Array(200, 300))
  }
}
