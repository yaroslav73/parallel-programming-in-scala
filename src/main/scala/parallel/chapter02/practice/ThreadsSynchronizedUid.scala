package parallel.chapter02.practice

import parallel.parallel.log

object ThreadsSynchronizedUid extends App {
  var uidCount = 0L

  def getUniqueId: Long = this.synchronized {
    val freshUid = uidCount + 1
    uidCount = freshUid
    freshUid
  }

  def printUniqueIds(times: Int): Unit = {
    val uids = for (_ <- 0 until times) yield getUniqueId
    log(s"Generated uids: $uids")
  }

  val thread = new Thread() {
    override def run(): Unit =
      printUniqueIds(5)

    this.start()
  }

  printUniqueIds(5)

  thread.join()
}
