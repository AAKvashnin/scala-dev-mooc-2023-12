package module1.homework


import scala.util.Random

class BallsExperiment {

  val balls:List[Boolean]=List(true,true,true,false,false,false)

  def isFirstBlackSecondWhite(): Boolean = {
    val first=Random.between(0,6)
    val second=Random.between(0,5)
    !balls.apply(first) && balls.zipWithIndex.filter(_._2 != first).map(_._1).apply(second)
  }
}

object BallsTest {
  def main(args: Array[String]): Unit = {
    val count = 10000
    val listOfExperiments: List[BallsExperiment] = List.fill(count)(new BallsExperiment)
    val countOfExperiments = listOfExperiments.map(_.isFirstBlackSecondWhite())
    val countOfPositiveExperiments: Float = countOfExperiments.count(_ == true)
    println(countOfPositiveExperiments / count)
  }
}