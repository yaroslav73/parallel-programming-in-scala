package parallel

package object parallel {
  def log(msg: String): Unit =
    println(s"[${Thread.currentThread().getName}]: $msg")

  def thread(block: => Unit): Thread = {
    val thread = new Thread {
      override def run(): Unit = block
    }

    thread.start()

    thread
  }
}
