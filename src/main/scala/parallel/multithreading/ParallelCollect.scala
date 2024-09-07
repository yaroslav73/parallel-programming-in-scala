package parallel.multithreading

import scala.collection.parallel.CollectionConverters._

object ParallelCollect:
  def fib(n: Int): Int = if n < 2 then 1 else fib(n - 1) + fib(n - 2)

  @main def parallelCollectMain(): Unit =
    for (i <- (30 to 15 by -1).par)
      println(s"[${Thread.currentThread}]: fib($i) = ${fib(i)}")

    // Race condition
    var i = 0
    for (j <- (0 until 1000000).par)
      // load i from memory
      // add 1 to register
      // store i to memory
      i += 1
    println(i)

    // Parallel collections
    val lastNames = List("Smith", "Jones", "Frankenstein", "Bach", "Jackson", "Rodin").par
    val uppercasedLastNames = lastNames.map(_.toUpperCase)
    println(uppercasedLastNames)

    val parArray = (1 to 1000000).toArray.par
    println(parArray.fold(0)(_ + _))

    // Creating a parallel collection
    // 1. new keyword
    // 2. converting with .par method

    var sum = 0
    val list1 = (1 to 1000).toList.par
    list1.foreach(sum += _)
    println(sum)

    // The source of this non-determinism is a data race
    // concurrent reads/writes to the same mutable variable.

    val list2 = (1 to 1000).toList.par
    println(list2.reduce(_ - _))

    val strings = List("abc","def","ghi","jk","lmnop","qrs","tuv","wx","yz").par
    val alphabet = strings.reduce(_++_)
    println(alphabet)
