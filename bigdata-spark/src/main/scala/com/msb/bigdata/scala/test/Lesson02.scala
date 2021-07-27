package main.scala.com.msb.bigdata.scala.test

object Lesson02 {
  //成员方法
  def function1 = {
    println("functrion1")
  }

  def main(args: Array[String]): Unit = {
    println("-------1.basic----------")
    def function2 (){
      println("function2")
    }
    var y = function2()
    println(y)

    println("-------2.无参数有返回值的方法----------")
    def function3:Int = {
      return 3
    }
    var x = function3
    println(x)
  }

  println("------3.递归函数----------")

  //递归先写触底！  触发什么报错呀
  def fun04(num: Int): Int = {
    if (num == 1) {
      num
    } else {
      num * fun04(num - 1)
    }
  }
  var i: Int = fun04(4)
  println(i)



  println("-------3.默认值函数----------")

  def fun05(a: Int = 8, b: String = "abc"): Unit = {
    println(s"$a\t$b")
  }


  println("-------4.匿名函数----------")
  
  var yy :(Int,Int)=>Int = (a:Int, b:Int)=>{
    a + b
  }
  val w:Int=yy(3,4)
  println(w)


}
