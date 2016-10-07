import javax.script.*;
import java.io.*;
import java.util.*;

public class ListEngines {
  public static void main(String args[]) {
    ScriptEngineManager manager = new ScriptEngineManager();
    List<ScriptEngineFactory> factories = manager.getEngineFactories();
    for (ScriptEngineFactory factory: factories) {
      Console console = System.console();
      console.printf("Name: %s%n" +
                     "Version: %s%n" +
                     "Language name: %s%n" +
                     "Language version: %s%n" +
                     "Extensions: %s%n" +
                     "Mime types: %s%n" +
                     "Names: %s%n",
                     factory.getEngineName(),
                     factory.getEngineVersion(),
                     factory.getLanguageName(),
                     factory.getLanguageVersion(),
                     factory.getExtensions(),
                     factory.getMimeTypes(),
                     factory.getNames());

      ScriptEngine engine = factory.getScriptEngine();
    }
  }
}
