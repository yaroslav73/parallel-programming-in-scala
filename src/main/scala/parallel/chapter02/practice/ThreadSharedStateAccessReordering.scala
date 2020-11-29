package parallel.chapter02.practice

import parallel.parallel.{log, thread}

object ThreadSharedStateAccessReordering extends App {
  for (_ <- 0 to 100) {
    var a = false
    var b = false
    var x = -1
    var y = -1

    val thread1 = thread {
      a = true
      y = if (b) 0 else 1
    }

    val thread2 = thread {
      b = true
      x = if (a) 0 else 1
    }

    thread1.join()
    thread2.join()

    assert(!(x == 1 && y == 1), s"x = $x, y = $y")
  }
}