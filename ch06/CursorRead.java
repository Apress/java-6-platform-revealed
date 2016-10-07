import java.io.*;
import javax.xml.stream.*;
import javax.xml.stream.events.*;

public class CursorRead {
  public static void main(String args[]) throws Exception {
    Console console = System.console();
    XMLInputFactory xmlif = XMLInputFactory.newInstance();
    XMLStreamReader xmlsr = xmlif.createXMLStreamReader(
      new FileReader("points.xml"));
    int eventType;
    while (xmlsr.hasNext()) {
      eventType = xmlsr.next();
      switch (eventType) {
        case XMLEvent.START_ELEMENT:
          console.printf("%s", xmlsr.getName());
          break;
        case XMLEvent.CHARACTERS:
          console.printf("\t%s", xmlsr.getText());
          break;
        default:
          break;
      }
    } 
  }
}
