import java.util.function.Predicate;

public class MyFilterIterator<T> implements Iterator<T> {
    private Iterator<T> iterator;
    private Predicate<T> predicate;
    private boolean done;

    public MyFilterIterator(Iterator<T> iterator, Predicate<T> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
        first();
    }

    @Override
    public void first() {
        iterator.first();
        advanceToNextValid();
    }

    @Override
    public void next() {
        iterator.next();
        advanceToNextValid();
    }

    private void advanceToNextValid() {
        while (!iterator.isDone() && !predicate.test(iterator.current())) {
            iterator.next();
        }
        done = iterator.isDone();
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public T current() {
        if (!isDone()) {
            return iterator.current();
        }
        throw new IndexOutOfBoundsException("Iterator out of bounds");
    }
}
