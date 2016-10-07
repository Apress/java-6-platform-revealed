import java.io.*;
import java.net.*;
import java.util.*;

public class NetworkInfo {
  public static void main(String args[]) throws SocketException {
    Console console = System.console();
    Enumeration<NetworkInterface> nets =
      NetworkInterface.getNetworkInterfaces();
    for (NetworkInterface netint : Collections.list(nets)) {
      console.printf("Display name: %s%n",
        netint.getDisplayName());
      console.printf("Name: %s%n", netint.getName());
      console.printf("Hardware address: %s%n",
        Arrays.toString(netint.getHardwareAddress()));
      console.printf("Parent: %s%n", netint.getParent());
      console.printf("MTU: %s%n", netint.getMTU());
      console.printf("Loopback? %s%n", netint.isLoopback());
      console.printf("PointToPoint? %s%n", netint.isPointToPoint());
      console.printf("Up? %s%n", netint.isUp());
      console.printf("Virtual? %s%n", netint.isVirtual());
      console.printf("Supports multicast? %s%n", netint.isVirtual());
      List<InterfaceAddress> addrs = netint.getInterfaceAddresses();
      for (InterfaceAddress addr : addrs) {
        console.printf("InterfaceAddress: %s --- %s%n",
          addr.getAddress(), addr.getBroadcast());
      }
      console.printf("%n");
    }
  }
}
