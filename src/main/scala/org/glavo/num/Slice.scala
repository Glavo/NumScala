package org.glavo.num

import scala.collection.mutable

final class Slice[@specialized A](val array: Array[A], val startIndex: Int, val endIndex: Int)
  extends mutable.AbstractSeq[A]
    with java.io.Serializable
    with Cloneable {

  if (startIndex < 0 || startIndex > endIndex)
    throw new IllegalArgumentException(s"startIndex = $startIndex")

  if(endIndex > array.length) {
    throw new IllegalArgumentException(s"endIndex = $endIndex")
  }

  def this(array: Array[A]) = {
    this(array, 0, array.length)
  }

  def this(array: Array[A], startIndex: Int) = {
    this(array, startIndex, array.length)
  }

  override def length: Int = endIndex - startIndex

  override def apply(index: Int): A = {
    val i = startIndex + index
    if (i >= endIndex) {
      throw new IndexOutOfBoundsException(index.toString)
    }

    array(startIndex + index)
  }

  override def update(index: Int, value: A): Unit = {
    val i = startIndex + index
    if (i >= endIndex) {
      throw new IndexOutOfBoundsException(index.toString)
    }
    array(i) = value
  }

  override def clone(): Slice[A] = new Slice(array.clone(), startIndex, endIndex)

  override def iterator: Iterator[A] = new SliceIterator

  override def foreach[U](f: A => U): Unit = {
    var index = startIndex
    while (index < endIndex) {
      f(array(index))
      index += 1
    }
  }

  def toArray: Array[A] = {
    val arr = java.lang.reflect.Array.newInstance(array.getClass.getComponentType, length).asInstanceOf[Array[A]]
    System.arraycopy(array, startIndex, arr, 0, length)
    arr
  }

  private class SliceIterator extends Iterator[A] {
    private var index: Int = startIndex
    private val end: Int = endIndex

    override def hasNext: Boolean = index < end

    override def next(): A = {
      if (index >= end) {
        throw new IndexOutOfBoundsException((index - startIndex).toString)
      }
      index += 1
      array.apply(index - 1)
    }
  }

}

object Slice {
  def apply[@specialized A](array: Array[A], startIndex: Int, endIndex: Int) =
    new Slice[A](array, startIndex, endIndex)

  def apply[@specialized A](array: Array[A], startIndex: Int) =
    new Slice[A](array, startIndex)

  def apply[@specialized A](array: Array[A]) =
    new Slice[A](array)
}