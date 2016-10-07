import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Blocked {
  public static void main(String args[]) {
    Calendar now = Calendar.getInstance();
    Locale locale = Locale.getDefault();
    final Console console = System.console();
    final Map<String, Integer> names = now.getDisplayNames(
        Calendar.MONTH, Calendar.ALL_STYLES, locale);
    console.printf("Starting names: %s%n", names);
    final Deque<String> deque = new LinkedBlockingDeque<String>(6);
    try {
      // Fails as too many elements
      // Still adds some
      deque.addAll(names.keySet());
    } catch (IllegalStateException e) {
      console.printf("Full: %s%n", e);
    }
    // Reset, remove those that fit
    deque.clear();
    // Add one at time to beginning of deque
    new Thread() {
      public void run() {
        Set<String> keys = names.keySet();
        Iterator<String> itor = keys.iterator();
        String element = null;
        while (itor.hasNext() || element != null) {
          if (element == null) {
            element = itor.next();
            console.printf("MapGot: %s%n",  element);
          }
          console.printf("Offering: %s%n", element);
          if (deque.offerFirst(element)) {
            console.printf("MapRemoving: %s%n", element);
            itor.remove();
            element = null;
          } else {
            try {
              Thread.sleep(250);
            } catch (InterruptedException ignored) {
            }
          }
        }
        // Done. Give time to process rest.
        try {
          Thread.sleep(3500);
        } catch (InterruptedException ignored) {
        }
        System.exit(0);
      }
    }.start();
    while (true) {
      if ((deque.size() % 2 == 1)) {
        // remove head
        console.printf("Remove head: %s%n", deque.pollFirst());
      } else {
        // remove tail
        console.printf("Remove tail: %s%n", deque.pollLast());
      }
      // Sleep between loops
      try {
        Thread.sleep(500);
      } catch (InterruptedException ignored) {
      }
    }
  }
}
