package parallel.chapter02.practice

object ThreadsMain extends App {
  val thread: Thread = Thread.currentThread()
  val name = thread.getName
  println(s"I am the thread: $name")
}
