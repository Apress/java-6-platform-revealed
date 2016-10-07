import javax.script.*;
import java.io.*;

public class FlipBindings {
  public static void main(String args[]) {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("javascript");
    if (args.length != 1) {
      System.err.println("Please pass name on command line");
      System.exit(-1);
    }

    try {
      engine.put("name", args[0]);
      engine.eval(
        "var output = '';" + 
        "for (i = 0; i <= name.length; i++) {" +
        "  output = name.charAt(i) + output" +
        "}");
      String name = (String)engine.get("output");
      Console console = System.console();
      console.printf("Reversed: %s%n", name);
    } catch (ScriptException e) {
      System.err.println(e);
    }
  }
}

