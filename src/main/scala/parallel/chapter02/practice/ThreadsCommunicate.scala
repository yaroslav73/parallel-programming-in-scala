package parallel.chapter02.practice

import parallel.parallel.log

object ThreadsCommunicate extends App {
  var result: String = null

  val thread = new Thread() {
    override def run(): Unit =
      result = s"\nTitle\n${"=" * 5}"

    this.start()
  }

  thread.join()

  log(result)
}
