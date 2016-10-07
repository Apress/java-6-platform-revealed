import java.util.Arrays;

public class ArrayCopy {
  public static void main(String args[]) {
    System.console().printf("Before (original)\t%s%n", Arrays.toString(args));
    String copy[] = Arrays.copyOf(args, 4);
    System.console().printf("Before (copy)\t\t%s%n", Arrays.toString(copy));
    copy[0] = "egg";
    copy[1] = "caterpillar";
    copy[2] = "pupa";
    copy[3] = "butterfly";
    System.console().printf("After (original)\t%s%n", Arrays.toString(args));
    System.console().printf("After (copy)\t\t%s%n", Arrays.toString(copy));
  }
}
