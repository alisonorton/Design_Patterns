import java.util.ArrayList;
import java.util.List;

public class Directory extends Node {
    private List<Node> children;

    public Directory(String name) {
        super(name);
        children = new ArrayList<>();
    }

    public void addChild(Node child) {
        child.setParent(this);
        children.add(child);
    }
    
    public List<Node> getChildren() {
        return children;
    }
    
    // Finds a child directory by name (returns null if not found)
    public Directory getChildDirectory(String name) {
        for (Node child : children) {
            // Only allow changing into a directory
            if (child instanceof Directory && child.getName().equals(name)) {
                return (Directory) child;
            }
        }
        return null;
    }
    
    // Prints the names of the immediate children separated by spaces
    public void list() {
        StringBuilder sb = new StringBuilder();
        for (Node child : children) {
            sb.append(child.getName()).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    @Override
    public void listAll(String indent) {
        System.out.println(indent + name + ":");
        for (Node child : children) {
            if (child instanceof Directory) {
                child.listAll(indent + "   ");
            } else {
                System.out.println(indent + "   " + child.getName());
            }
        }
    }

    @Override
    public int count() {
        int cnt = 0;
        for (Node child : children) {
            // Only count files (leaves)
            if (!(child instanceof Directory)) {
                cnt++;
            }
        }
        return cnt;
    }

    @Override
    public int countAll() {
        int cnt = 0;
        for (Node child : children) {
            if (child instanceof Directory) {
                cnt += ((Directory) child).countAll();
            } else {
                cnt++;
            }
        }
        return cnt;
    }
}

