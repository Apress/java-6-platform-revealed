import java.io.*;
import java.net.*;
import javax.xml.ws.*;
import javax.xml.namespace.*;
import javax.xml.soap.*;
                                                                                
public class GoogleSearch {
  public static void main(String args[]) throws Exception {
   URL url = new URL("http://api.google.com/GoogleSearch.wsdl");
   QName serviceName = new QName("urn:GoogleSearch", "GoogleSearchService");
   QName portName = new QName("urn:GoogleSearch", "GoogleSearchPort");
   Service service = Service.create(url, serviceName);
   Dispatch<SOAPMessage> dispatch = service.createDispatch(portName,
   SOAPMessage.class, Service.Mode.MESSAGE);
   SOAPMessage request = MessageFactory.newInstance().createMessage(
     null, new FileInputStream(args[0]));
   SOAPMessage response = dispatch.invoke(request);
   response.writeTo(System.out);
  }
}
