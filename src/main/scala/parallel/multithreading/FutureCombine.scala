package parallel.multithreading

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object FutureCombine extends App:
  private def getData(futureNumber: Int, number: Int): Int =
    println(s"[${Thread.currentThread}]: Future $futureNumber is running...")
    73

  // Running when created
  val f1 = Future(getData(1, 73))
  val f2 = Future(getData(2, 37))

  def f3 = Future(getData(3, 21))
  def f4 = Future(getData(4, 13))

  lazy val f5 = Future(getData(5, 1))
  lazy val f6 = Future(getData(6, 3))

  for {
    r1 <- f1
    r2 <- f2
    r3 <- f2
  } yield println(s"Result of f1 + f2 + f2: ${r1 + r2 + r3}")

  for {
    r3 <- f3
    r4 <- f4
    r5 <- f4
  } yield println(s"Result of f3 + f4 + f4: ${r3 + r4 + r5}")

  for {
    r5 <- f5
    r6 <- f6
    r7 <- f6
  } yield println(s"Result of f5 + f6 + f6: ${r5 + r6 + r7}")
