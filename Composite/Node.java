public abstract class Node {
  protected String name;
  protected Directory parent;
  
  public Node(String name) {
      this.name = name;
      this.parent = null;
  }
  
  public String getName() {
      return name;
  }
  
  public Directory getParent() {
      return parent;
  }
  
  public void setParent(Directory parent) {
      this.parent = parent;
  }
  
  // Recursively prints the node (and its subtree if applicable)
  public abstract void listAll(String indent);
  
  // Returns the number of files immediately contained in this directory.
  // For files (leaves), this returns 0.
  public abstract int count();
  
  // Returns the number of files in the subtree starting from this node.
  public abstract int countAll();
}

