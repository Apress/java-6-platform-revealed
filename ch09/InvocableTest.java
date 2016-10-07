import javax.script.*;
import java.io.*;

public class InvocableTest {
  public static void main(String args[]) {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("javascript");
    if (args.length == 0) {
      System.err.println("Please pass name(s) on command line");
      System.exit(-1);
    }

    try {
      engine.eval(
        "function reverse(name) {" +
        "  var output = '';" + 
        "  for (i = 0; i <= name.length; i++) {" +
        "    output = name.charAt(i) + output" +
        "  }" +
        "  return output;" +
        "}");
      Invocable invokeEngine = (Invocable)engine;
      Console console = System.console();
      for (Object name: args) {
        Object o = invokeEngine.invoke("reverse", name);
        console.printf("%s / %s%n", name, o);
      }
    } catch (NoSuchMethodException e) {
      System.err.println(e);
    } catch (ScriptException e) {
      System.err.println(e);
    }
  }
}

