package parallel.chapter01.exercices

import scala.util.control.NonFatal

class Ex03 {
  def check[T](xs: Seq[T])(p: T => Boolean): Boolean =
    try {
      xs.forall(p)
    } catch {
      case NonFatal(_) => false
    }
}
