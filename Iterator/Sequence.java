public interface Sequence<T> {
  public void add(T value);
  public int size();
  public int capacity();
  public T get(int index);
}
