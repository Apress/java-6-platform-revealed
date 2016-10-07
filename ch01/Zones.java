import java.util.*;

public class Zones {
  public static void main(String args[]) {
    TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");
    System.out.println(tz.getDisplayName(Locale.US));
    System.out.println(tz.getDisplayName(Locale.UK));
  }
}
