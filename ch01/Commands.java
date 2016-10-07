import javax.activation.*;

public class Commands {
  public static void main(String args[]) {
    CommandMap map = CommandMap.getDefaultCommandMap();
    String types[] = map.getMimeTypes();
    for (String type: types) {
      System.out.println(type);
      CommandInfo infos[] = map.getAllCommands(type);
      for (CommandInfo info: infos) {
        System.out.println("\t" + info.getCommandName());
      }
    }
  }
}
