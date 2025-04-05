public class FileNode extends Node {

  public FileNode(String name) {
      super(name);
  }

  @Override
  public void listAll(String indent) {
      System.out.println(indent + getName());
  }

  // A file has no children, so immediate count is 0.
  @Override
  public int count() {
      return 0;
  }

  // For counting in the subtree, the file itself counts as one file.
  @Override
  public int countAll() {
      return 1;
  }
}

