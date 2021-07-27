package main.scala.com.msb.bigdata.scala.test


//object中的内容类似于java中的静态代码块
object Lesson1{
  println("object start")
//  private val lesson = new Lesson1(11)
  private val lesson = new Lesson1("male")

  //  lesson.printMsg()
  println("object end")
  private val name = "object:zhangsan"
  lesson.printMsg()


  // 在scala中，main方法只能是object中定义。
  def main(args: Array[String]): Unit = {
//    lesson.printMsg()

  }

}


//如果类名构造器中加了参数，在实现个性化的构造函数时也要调用有参数的
class Lesson1(xname:String) {
  println("start")

  //个性化构造函数必须调用无参数构造函数,外边裸露的代码就在无参数构造函数里边
//  def this(xname:Int){
//    this("male")
//  }
  val a:Int = 3

  def printMsg()={
    println("printMsg")
    //可以调用object中的name
    println(s"${Lesson1.name},$a")

  }
  println(s"${a+3}")

  println("end")




}
