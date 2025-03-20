public class MyArray<T> implements IterableSequence<T> {
  private Object[] array;
  private int size;

  public MyArray(int capacity) {
      array = new Object[capacity];
      size = 0;
  }

  @Override
  public void add(T value) {
      if (size < array.length) {
          array[size++] = value;
      } else {
          throw new IndexOutOfBoundsException("Array is full");
      }
  }

  @SuppressWarnings("unchecked")
  @Override
  public T get(int index) {
      if(index >= 0 && index < size) {
          return (T) array[index];
      }
      throw new IndexOutOfBoundsException("Index out of bounds");
  }

  @Override
  public int size() {
      return size;
  }

  @Override
  public int capacity() {
      return array.length;
  }

  @Override
  public Iterator<T> getIterator() {
      return new MyIterator<T>(this);
  }
}
