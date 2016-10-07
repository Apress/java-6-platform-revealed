import java.io.Console;

public class Input {
  public static void main(String args[]) {
    Console console = System.console();
    console.printf("Enter name: ");
    String name = console.readLine();
    char password[] = console.readPassword("Enter password: ");
    console.printf("Name:%s:\tPassword:%s:%n",
        name, new String(password));
   }
}
