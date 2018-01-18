package org.glavo.num

//todo
abstract sealed class Complex[@specialized(Float, Double) A](val real: A, val imag: A) {
  override def toString: String = s"($real+${imag}j)"
}

final class Complex64(real: Float, imag: Float) extends Complex[Float](real, imag) {
  def +(other: Complex64): Complex64 = new Complex64(real + other.real, imag + other.imag)

  def -(other: Complex64): Complex64 = new Complex64(real - other.real, imag - other.imag)

  def toComplex128: Complex128 = new Complex128(real, imag)
}

object Complex64 {

  trait Impl {
    //todo
  }

  def apply(real: Float, imag: Float): Complex64 = new Complex64(real, imag)
}

final class Complex128(real: Double, imag: Double) extends Complex[Double](real, imag) {
  def +(other: Complex128): Complex128 = new Complex128(real + other.real, imag + other.imag)

  def -(other: Complex128): Complex128 = new Complex128(real - other.real, imag - other.imag)
}

object Complex128 {

  trait Impl {

  }

  def apply(real: Double, imag: Double): Complex128 = new Complex128(real, imag)
}