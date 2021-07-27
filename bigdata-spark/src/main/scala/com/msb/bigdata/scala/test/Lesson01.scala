package main.scala.com.msb.bigdata.scala.test

import scala.collection.immutable

object Lesson01 {
  def main(args: Array[String]): Unit = {
    var a:Int = 0
    if (a == 0){
      a +=1
      a = a+1
      println(a)
    }

    val seqs = 1 until(10)
    //Range(1, 2, 3, 4, 5, 6, 7, 8, 9) rang 是一个区间类型，是一个特殊的数组
    println(seqs)
    for(i <- seqs if i ==2){
      println(i)
    }

    for(i <- 1 to 9; j <- 1 to 9; if(j<=i)){
      if(j<=i) print(s"$i * $j = ${i * j}\t")
      if(j == i) println()
    }

    //yeild 将结果存放到数组的变量或表达式必须放在yield{}里最后位置
//    IndexedSeq[Int] =
    val seqqs : immutable.IndexedSeq[Int] =  for(i <- 1 to 10) yield{
      var x = 8
      i + x
    }
    for(i <- seqqs){
      println(i)
    }
  }


}
