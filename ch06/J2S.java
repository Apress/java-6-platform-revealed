package help;

import java.io.*;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

public class J2S {

  public static void main(String[] args) {
    try {
      JAXBContext context = JAXBContext.newInstance(Point.class);
      Marshaller m = context.createMarshaller();
      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      Point p = new Point(3, 4);
      m.marshal(p, System.out);
    } catch (JAXBException jex) {
      System.out.println("JAXB Binding Exception");
      jex.printStackTrace();
    }
  }

  @XmlRootElement
  public static class Point {

    int x;
    int y;
    public Point() {
    }

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public void setX(int x) {
      this.x = x;
    }

    public void setY(int y) {
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }
  }
}

