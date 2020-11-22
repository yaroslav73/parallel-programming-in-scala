package parallel.chapter02.practice

import parallel.parallel.log

object ThreadsSleep extends App {
  val thread = new Thread() {
    override def run(): Unit = {
      Thread.sleep(1000)
      log("New thread running...")
      Thread.sleep(1000)
      log("Still running...")
      Thread.sleep(1000)
      log("Completed.")
    }

    this.start()
  }

  thread.join()

  log("New thread joined.")
}
