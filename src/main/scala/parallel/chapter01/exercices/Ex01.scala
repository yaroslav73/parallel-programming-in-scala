package parallel.chapter01.exercices

class Ex01 {
  def compose01[A, B, C](g: B => C, f: A => B): A => C = { a => g(f(a)) }

  def compose02[A, B, C](g: B => C, f: A => B): A => C = f andThen g

  def compose03[A, B, C](g: B => C, f: A => B): A => C = g compose f
}
