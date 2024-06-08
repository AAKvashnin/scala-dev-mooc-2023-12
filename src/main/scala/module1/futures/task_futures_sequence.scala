package module1.futures

import module1.futures.HomeworksUtils.TaskSyntax
import org.scalatest.time.Seconds

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

object task_futures_sequence {

  /**
   * В данном задании Вам предлагается реализовать функцию fullSequence,
   * похожую на Future.sequence, но в отличии от нее,
   * возвращающую все успешные и не успешные результаты.
   * Возвращаемое тип функции - кортеж из двух списков,
   * в левом хранятся результаты успешных выполнений,
   * в правово результаты неуспешных выполнений.
   * Не допускается использование методов объекта Await и мутабельных переменных var
   */
  /**
   * @param futures список асинхронных задач
   * @return асинхронную задачу с кортежом из двух списков
   */
  def fullSequence[A](futures: List[Future[A]])
                     (implicit ex: ExecutionContext): Future[(List[A], List[Throwable])] = {
//    task"Реализуйте метод `fullSequence`" ()
    val seq=Future.sequence(futures)
    val result=Await.result(seq, scala.concurrent.duration.Duration.Inf)
    val errors=futures.filter((f:Future[A])=>f.value.get.isFailure).map((f:Future[A])=>f.value.get.toEither.left.get)
    println(result)
    println(errors)
    Future( (result  ,errors)  )

  }

}
