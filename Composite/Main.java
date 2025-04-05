import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Usage: java Main <filename>");
            return;
        }
        
        try (FileInputStream fis = new FileInputStream(args[0])) {
            Directory root = DirectoryParser.parse(fis);
            Explorer explorer = new Explorer(root);
            explorer.process(System.in, System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

