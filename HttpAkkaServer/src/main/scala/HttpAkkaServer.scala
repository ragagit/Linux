import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.io.StdIn

object HttpAkkaServer {
  def main(args: Array[String]) {

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher


    val route: HttpRequest => HttpResponse = {
      case HttpRequest(GET, Uri.Path("/"), _, _, _) =>
        HttpResponse(200, entity = "online!")
//        HttpResponse(entity = HttpEntity(
//          ContentTypes.`text/html(UTF-8)`,
//          "<html><body>Hello world!</body></html>"))

      case HttpRequest(GET, Uri.Path("/ping"), _, _, _) =>
        HttpResponse(entity = "PONG!")

      case HttpRequest(GET, Uri.Path("/crash"), _, _, _) =>
        HttpResponse(entity = "CRASH!")
      //sys.error("BOOM!")

      case HttpRequest(GET, Uri.Path("/redirectme"), _, _, _) =>
        HttpResponse(
          status = StatusCodes.PermanentRedirect,
          headers = headers.Location("http://localhost:8181/redirectme") :: Nil
          )
          //HttpResponse(
          //   status = redirectionType,
          //   headers = headers.Location(uri) :: Nil,
          //   entity = redirectionType.htmlTemplate match {
          //     case ""       ⇒ HttpEntity.Empty
          //     case template ⇒ HttpEntity(ContentTypes.`text/html(UTF-8)`, template format uri)
          //   })

      case r: HttpRequest =>
        r.discardEntityBytes() // important to drain incoming HTTP Entity stream
        HttpResponse(404, entity = "Unknown resource!")
    }

    //val bindingFuture = Http().bindAndHandleSync(route, "10.0.21.121", 8080)
    val bindingFuture = Http().bindAndHandleSync(route, "localhost", 8080)
//    val route =
//      pathPrefix("redirectme") {
//        pathSingleSlash {
//          complete("yes")
//        } ~
//          pathEnd {
//            redirect("http://localhost:8181/redirectme", StatusCodes.PermanentRedirect)
//          }
//      }
//
//    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)



    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
