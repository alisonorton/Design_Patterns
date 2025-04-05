import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Explorer {
    private Directory root;
    private Directory current;

    public Explorer(Directory root) {
        this.root = root;
        this.current = root;
    }

    // Processes user commands from the given input and writes output.
    public void process(InputStream in, PrintStream out) {
        Scanner scanner = new Scanner(in);
        while (true) {
            // Print prompt using the current directory's name.
            out.print(current.getName() + "> ");
            String line = scanner.nextLine();
            String[] tokens = line.trim().split("\\s+");
            if (tokens.length == 0 || tokens[0].isEmpty()) {
                continue;
            }
            String command = tokens[0];
            if (command.equals("q")) {
                break;
            } else if (command.equals("list")) {
                current.list();
            } else if (command.equals("listall")) {
                current.listAll("");
            } else if (command.equals("chdir")) {
                if (tokens.length < 2) {
                    out.println("invalid command");
                } else {
                    String target = tokens[1];
                    Directory next = current.getChildDirectory(target);
                    if (next == null) {
                        out.println("no such directory");
                    } else {
                        current = next;
                    }
                }
            } else if (command.equals("up")) {
                if (current.getParent() != null) {
                    current = current.getParent();
                }
            } else if (command.equals("count")) {
                out.println(current.count());
            } else if (command.equals("countall")) {
                out.println(current.countAll());
            } else {
                out.println("invalid command");
            }
        }
        scanner.close();
    }
}
