import javax.script.*;
import java.io.*;

public class CompileTest {
  public static void main(String args[]) {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("javascript");
    engine.put("counter", 0);
    if (engine instanceof Compilable) {
      Compilable compEngine = (Compilable)engine;
      try {
        CompiledScript script = compEngine.compile(
          "function count() { " +
          "  counter = counter +1; " +
          "  return counter; " +
          "}; count();");
        Console console = System.console();
        console.printf("Counter: %s%n", script.eval());
        console.printf("Counter: %s%n", script.eval());
        console.printf("Counter: %s%n", script.eval());
      } catch (ScriptException e) {
        System.err.println(e);
      }
    } else {
      System.err.println("Engine can't compile code");
    }
  }
}


