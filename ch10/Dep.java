public class Dep {
  /**
   * @deprecated Don't use this method any more.
   */
  @Deprecated
  public static void myDeprecatedMethod() {
     System.out.println("Why did you do that?");
  }
}
class DeprecatedUsage {
  @SuppressWarnings("deprecation")
  public void useDeprecatedMethod() {
    Dep.myDeprecatedMethod();
  }
}

