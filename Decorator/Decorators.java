package Decorator;

abstract class BaseDecorator implements Output {
  protected Output base;

  public BaseDecorator(Output base) {
    this.base = base;
  }

  public void write(Object o) {}

}


class BracketOutput extends BaseDecorator {
  public BracketOutput(Output base) {
    super(base);
  }

  public void write(Object o) {
    base.write("[" + o.toString() + "]\n");
  }


}

class NumberedOutput extends BaseDecorator {
  private int count = 1;

  public NumberedOutput(Output base) {
    super(base);
  }

  public void write(Object o) {
    base.write(String.format("%5d: %s", count++, o.toString()));
  }

}

class TeeOutput extends BaseDecorator {
  private Output second;

  public TeeOutput(Output base, Output second) {
      super(base);
      this.second = second;
  }

  public void write(Object o) {
      // System.out.println("DEBUG: TeeOutput Writing -> " + o.toString()); // Debugging
      base.write(o);
      second.write(o);
  }
}



interface Predicate {
  boolean execute(Object o);
}

class ContainsDigit implements Predicate {
  public boolean execute(Object o) {
      return o.toString().matches(".*\\d.*");
  }
}

class LongerThanTenChars implements Predicate {
  public boolean execute(Object o) {
      return o.toString().length() > 10;
  }
}

class FilterOutput extends BaseDecorator {
  private Predicate predicate;

  public FilterOutput(Output base, Predicate predicate) {
    super(base);
    this.predicate = predicate;
  }

  public void write(Object o) {
    if(predicate.execute(o)) {
      base.write(o);
    }
  }
}

