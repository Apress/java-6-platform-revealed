import java.io.*;
import java.util.*;

public class JapaneseCalendar {
  public static void main(String args[]) {
    Locale locale = new Locale("ja", "JP", "JP");
    Calendar now = Calendar.getInstance(locale);
    Console console = System.console();
    Map<String, Integer> names = now.getDisplayNames(
      Calendar.ERA, Calendar.LONG, locale);
    console.printf("%s%n", names);
    console.printf("It is year %tY of the current era%n", now);
    console.printf("The calendar class is: %s%n", now.getClass().getName());
  }
}

