package module1.futures

import module1.futures.HomeworksUtils.TaskSyntax
import org.scalatest.time.Seconds

import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.{Failure, Success, Try}
import scala.concurrent.duration.Duration

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

    val initial:Future[(List[A],List[Throwable])]=Future((List(),List()))

    val result:Future[(List[A],List[Throwable])]=futures.foldLeft(initial){ (accumulator, nextFuture) =>
      accumulator.flatMap { case (successes, failures) =>
        nextFuture.map(x=>Success(x)).recover(x=>Failure(x)).map
          {
          case Failure(exception)=>(successes, exception :: failures)
          case Success(value)=>(value::successes, failures)
        }
      }
    }

    result.map(x=>(x._1.reverse, x._2.reverse))

  }

}
