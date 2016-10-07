import java.io.*;
import java.net.*;
import java.util.*;

public class ListCookieHandler extends CookieHandler {

  // "Long" term storage for cookies, not serialized so only
  // for current JVM instance
  private List<Cookie> cookieJar = new LinkedList<Cookie>();

  public void put(URI uri, Map<String, List<String>> responseHeaders)
        throws IOException {

    System.out.println("Cache: " + cookieJar);
    List<String> setCookieList = responseHeaders.get("Set-Cookie");
    if (setCookieList != null) {
      for (String item : setCookieList) {
        Cookie cookie = new Cookie(uri, item);
        // Remove cookie if it already exists
        // New one will replace
        for (Cookie existingCookie : cookieJar) {
          if((cookie.getURI().equals(existingCookie.getURI())) &&
             (cookie.getName().equals(existingCookie.getName()))) {
            cookieJar.remove(existingCookie);
            break;
          }
        }
        System.out.println("Adding to cache: " + cookie);
        cookieJar.add(cookie);
      }
    }
  }

  public Map<String, List<String>> get(URI uri,
     Map<String, List<String>> requestHeaders) throws IOException {

    // Retrieve all the cookies for matching URI
    // Put in comma-separated list
    StringBuilder cookies = new StringBuilder();
    for (Cookie cookie : cookieJar) {
      // Remove cookies that have expired
      if (cookie.hasExpired()) {
        cookieJar.remove(cookie);
      } else if (cookie.matches(uri)) {
        if (cookies.length() > 0) {
          cookies.append(", ");
        }
        cookies.append(cookie.toString());
      }
    }

    // Map to return
    Map<String, List<String>> cookieMap =
      new HashMap<String, List<String>>(requestHeaders);

    // Convert StringBuilder to List, store in map
    if (cookies.length() > 0) {
      List<String> list =
        Collections.singletonList(cookies.toString());
      cookieMap.put("Cookie", list);
    }
    System.out.println("CookieMap: " + cookieMap);
    // Make read-only
    return Collections.unmodifiableMap(cookieMap);
  }
}
