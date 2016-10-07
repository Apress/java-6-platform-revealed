import javax.script.*;

public class InterfaceTest {
  public static void main(String args[]) {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("javascript");
    try {
      engine.eval("function run() {print('wave');}");
      Invocable invokeEngine = (Invocable)engine;
      Runnable runner = invokeEngine.getInterface(Runnable.class);
      Thread t = new Thread(runner);
      t.start();
      t.join();
    } catch (InterruptedException e) {
      System.err.println(e);
    } catch (ScriptException e) {
      System.err.println(e);
    }
  }
}

