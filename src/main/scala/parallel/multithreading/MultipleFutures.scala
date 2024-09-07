package parallel.multithreading

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object MultipleFutures:

  val startTime: Long = System.currentTimeMillis()
  def delta(): Long = System.currentTimeMillis() - startTime
  def sleep(millis: Long): Unit = Thread.sleep(millis)

  @main def multiplierFutures(): Unit =
    println(s"Creating the futures: ${delta()}")

    // 1, Start the computation that return futures
    val f1 = Future { sleep(800); 1 }
    val f2 = Future { sleep(200); 2 }
    val f3 = Future { sleep(400); 3 }

    // 2. Join the futures in a for-comprehension
    val result =
      for
        r1 <- f1
        r2 <- f2
        r3 <- f3
      yield
        println(s"In the 'yield': ${delta()}")
        r1 + r2 + r3

    // 3. Process the result
    result.onComplete {
      case Success(v) =>
        println(s"In the Success: ${delta()}")
        println(s"Result: $v")
      case Failure(e) => e.printStackTrace()
    }

    println(s"before the 'sleep(3000)': ${delta()}")

    // important for a little parallel demo: keep the jvm alive
    sleep(3000)
