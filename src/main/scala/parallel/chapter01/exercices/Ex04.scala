package parallel.chapter01.exercices

object Ex04 {

  class Pair[F, S](val first: F, val second: S)

  object Pair {
    def apply[F, S](first: F, second: S): Pair[F, S] = new Pair(first, second)

    def unapply[F, S](p: Pair[F, S]): Option[(F, S)] = Option(p.first, p.second)
  }

}

