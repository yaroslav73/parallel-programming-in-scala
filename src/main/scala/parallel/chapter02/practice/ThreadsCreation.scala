package parallel.chapter02.practice

object ThreadsCreation extends App {

  class MyThread extends Thread {
    override def run(): Unit =
      println("New thread running...")
  }

  val thread = new MyThread
  thread.start()
  thread.join()

  println("New thread joined...")
}
