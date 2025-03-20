import java.util.Stack;

public class ReverseIterator<T> implements Iterator<T> {
    private Stack<T> stack;

    public ReverseIterator(Iterator<T> iterator) {
        stack = new Stack<>();
        // Populate the stack with all elements from the given iterator.
        iterator.first();
        while (!iterator.isDone()) {
            stack.push(iterator.current());
            iterator.next();
        }
    }

    @Override
    public void first() {
        // This ReverseIterator is designed for one-pass usage.
        // You could re-populate the stack here if needed.
    }

    @Override
    public void next() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    @Override
    public boolean isDone() {
        return stack.isEmpty();
    }

    @Override
    public T current() {
        if (!isDone()) {
            return stack.peek();
        }
        throw new IndexOutOfBoundsException("Iterator out of bounds");
    }
}
