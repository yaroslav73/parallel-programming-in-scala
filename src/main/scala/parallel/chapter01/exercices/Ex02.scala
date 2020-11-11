package parallel.chapter01.exercices

class Ex02 {
  def fuse[A, B](a: Option[A], b: Option[B]): Option[(A, B)] = for {
    aa <- a
    bb <- b
  } yield aa -> bb
}
