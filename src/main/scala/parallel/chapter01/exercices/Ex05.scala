package parallel.chapter01.exercices

object Ex05 {
  def permutations00(s: String): Seq[String] = s.toList.permutations.map(_.mkString).toSeq

  def permutations01(s: String): Seq[String] = {
    def swap(s: String): String = s(1).toString + s(0).toString

    if (s.length == 2) Seq(s, swap(s))
    else for {
      i <- 0 until s.length
      q <- permutations01(s.take(i) + s.takeRight(s.length - i - 1))
    } yield s(i) + q
  }
}
