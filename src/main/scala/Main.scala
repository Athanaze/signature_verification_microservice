import org.ergoplatform.appkit.config.ErgoToolConfig
import org.ergoplatform.appkit.{SigmaProp, Signature}
import org.ergoplatform.appkit.Address
import org.scalatra.ScalatraServlet
import org.eclipse.jetty.server.Server
import javax.servlet.Servlet
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext
object Crypto {
  def verifySignature(addr: String, signature: String): Boolean ={
    val sigmaProp = SigmaProp.createFromAddress(Address.create(addr))
    Signature.verifySignature(
      sigmaProp,
      "login".getBytes(),
      signature.getBytes()
    )
  }
}
object WebServiceBuilder {
  def buildWebService(port: Integer, webServiceClass: Class[_ <: Servlet]): Server = {
    val server = new Server(port)
    val context = new WebAppContext()
    context.setContextPath("/")
    context.setResourceBase("/tmp")
    context.addServlet(webServiceClass, "/*")
    server.setHandler(context)
    server
  }
}

class WebService extends ScalatraServlet {
  get("/"){
    "pelele"
  }
  get("/login/:addr/:signature"){
    if (Crypto.verifySignature({params("addr")}, {params("signature")})){
      "Valid signature"
    }else{
      "Invalid signature"
    }
  }
}

object Main extends{
  def main(args: Array[String]): Unit = {
    val server: Server = WebServiceBuilder.buildWebService(8080, classOf[WebService])
    server.start()
  }

}