package parallel.chapter02.practice

import parallel.parallel.log

object ThreadsNondeterminism extends App {
  val thread = new Thread() {
    override def run(): Unit =
      log("New thread running...")
  }

  thread.start()

  log("...")
  log("...")

  thread.join()

  log("New thread joined.")
}
