package parallel

package object parallel {
  def log(msg: String): Unit =
    println(s"[${Thread.currentThread().getName}]: $msg")
}
