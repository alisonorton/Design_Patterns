import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class DirectoryParser {
    // Parses the input stream and returns the root directory of the tree.
    public static Directory parse(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        Directory root = null;
        Stack<Directory> stack = new Stack<>();
        
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue; // skip empty lines
            }
            // Count the number of leading spaces.
            int numSpaces = 0;
            while (numSpaces < line.length() && line.charAt(numSpaces) == ' ') {
                numSpaces++;
            }
            // Each level is indented by 3 spaces.
            int level = numSpaces / 3;
            
            String trimmed = line.trim();
            boolean isDirectory = trimmed.endsWith(":");
            String name = isDirectory ? trimmed.substring(0, trimmed.length() - 1) : trimmed;
            
            Node node;
            if (isDirectory) {
                node = new Directory(name);
            } else {
                node = new FileNode(name);
            }
            
            if (level == 0) {
                // Root node
                if (node instanceof Directory) {
                    root = (Directory) node;
                    stack.clear();
                    stack.push(root);
                } else {
                    throw new IOException("Root must be a directory");
                }
            } else {
                // Pop back to the correct parent level.
                while (stack.size() > level) {
                    stack.pop();
                }
                Directory parent = stack.peek();
                parent.addChild(node);
                if (node instanceof Directory) {
                    stack.push((Directory) node);
                }
            }
        }
        reader.close();
        return root;
    }
}

