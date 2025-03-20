public class MyIterator<T> implements Iterator<T> {
  private MyArray<T> array;
  private int currentIndex;

  public MyIterator(MyArray<T> array) {
      this.array = array;
      first();
  }

  @Override
  public void first() {
      currentIndex = 0;
  }

  @Override
  public void next() {
      currentIndex++;
  }

  @Override
  public boolean isDone() {
      return currentIndex >= array.size();
  }

  @Override
  public T current() {
      if (!isDone()) {
          return array.get(currentIndex);
      }
      throw new IndexOutOfBoundsException("Iterator out of bounds");
  }
}
