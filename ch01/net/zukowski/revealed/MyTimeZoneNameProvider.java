package net.zukowski.revealed;

import java.util.*;
import java.util.spi.*;

public class MyTimeZoneNameProvider extends TimeZoneNameProvider {
  public String getDisplayName(String ID, boolean daylight,
      int style, Locale locale) {
    System.out.println("ID: " + ID);
    return ID;
  }
  public Locale[] getAvailableLocales() {
    return new Locale[] {Locale.US};
  }
}
