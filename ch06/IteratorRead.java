import java.io.*;
import javax.xml.stream.*;
import javax.xml.stream.events.*;

public class IteratorRead {
  public static void main(String args[]) throws Exception {
    Console console = System.console();
    XMLInputFactory xmlif = XMLInputFactory.newInstance();
    XMLEventReader xmler = xmlif.createXMLEventReader(
      new FileReader("points.xml"));
    XMLEvent event;
    while (xmler.hasNext()) {
      event = xmler.nextEvent();
      if (event.isStartElement()) {
        console.printf("%s", event.asStartElement().getName());
      } else if (event.isCharacters()) {
        console.printf("\t%s", event.asCharacters().getData());
      }
    } 
  }
}
