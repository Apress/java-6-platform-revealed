import java.util.*;

public class DisplayNames {
  public static void main(String args[]) {
    Calendar now = Calendar.getInstance();
    Locale locale = Locale.getDefault();
    // Locale locale = Locale.ITALIAN;
    Map<String, Integer> names = now.getDisplayNames(
      Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
    System.out.println(names);
    System.console().printf("%s%n", names.toString());
    String name = now.getDisplayName(Calendar.DAY_OF_WEEK,
      Calendar.LONG, locale);
    System.console().printf("Today is a %s.%n", name);
  }
}
