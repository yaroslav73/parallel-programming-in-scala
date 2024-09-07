package parallel.multithreading

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}


object SequentialExecution:
  private def sleepWithLog(millis: Long): Unit =
    println(s"Executed on [${Thread.currentThread}]...")
    Thread.sleep(millis)

  val startTime: Long = System.currentTimeMillis()
  def delta(): Long = System.currentTimeMillis() - startTime
  def sleep(millis: Long): Unit = Thread.sleep(millis)


  @main def sequentialExecution(): Unit =
    // Sequential execution (no parallelism!)
    println(s"Creating the futures: ${delta()}")
    val result = for
      r1 <- Future { sleepWithLog(800); 1 }
      r2 <- Future { sleepWithLog(200); 2 }
      r3 <- Future { sleepWithLog(400); 3 }
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

    sleep(3000)
