package parallel.multithreading

import scala.util.{Failure, Success}
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.DurationInt
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source

object FutureTest extends App:
  println(s"Main thread ${Thread.currentThread} start")
  val f1: Future[Unit] = Future {
    println(s"[${Thread.currentThread}]: Printing from the Future")
  }
  //  Thread.sleep(1)
  println(s"Main thread ${Thread.currentThread} end")

  val f2: Future[IndexedSeq[Int]] = Future {
    for (i <- 1 to 30) yield ParallelCollect.fib(i)
    throw new RuntimeException("I'm a bad Future")
  }

  // Callbacks
  // f2.foreach(value => println(value))
  f2.onComplete {
    case Success(value) => println(value)
    case Failure(exception) => println(s"Something went wrong: $exception")
  }

  Thread.sleep(1000)

  val f3 = Future(73)
  // Await
  // blocking the current thread until the Awaitable has been completed or a timeout has occurred
  println(Await.result(f3, 1.second))

  val p1 = Future("Google: " + Source.fromURL("https://www.google.com").take(100).mkString)

  val p2 = Future("YouTube: " + Source.fromURL("https://www.youtube.com").take(100).mkString)

  val p3 = Future("Twitter: " + Source.fromURL("https://www.twitter.com").take(100).mkString)

  val pages = List(p3, p2, p1)

  //  Future.firstCompletedOf(pages).onComplete {
  //    case Success(value) => println(s"First completed is $value")
  //    case Failure(exception) => println(s"Something went wrong: $exception")
  //  }

  //  Future.traverse(pages)(identity).onComplete {
  //    case Success(values) => values.foreach(println)
  //    case Failure(exception) => println(s"Something went wrong: $exception")
  //  }

  Future.sequence(pages).onComplete {
    case Success(values) => values.foreach(println)
    case Failure(exception) => println(s"Something went wrong: $exception")
  }

  Thread.sleep(1000)

  val f4 = Future {
    for (i <- 1 to 100)
      print("A")
      Thread.sleep(10)
  }

  val f5 = Future {
    for (i <- 1 to 100)
      print("B")
      Thread.sleep(10)
  }

  Thread.sleep(1000)
