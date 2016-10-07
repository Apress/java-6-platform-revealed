public class EmptyString {
  public static void main(String args[]) {
    String one = null;
    String two = "";
    String three = "non empty";
    try {
      System.out.println("Is null empty? : " + one.isEmpty());
    } catch (NullPointerException e) {
      System.out.println("null is null, not empty");
    }
    System.out.println("Is empty string empty? : " + two.isEmpty());
    System.out.println("Is non empty string empty? : " + three.isEmpty());
  }
}
