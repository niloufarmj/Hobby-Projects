import java.util.ArrayList;

public class queue<T> {

    ArrayList<T> elements = new ArrayList<T>();
    int first;

    queue() {
        first = 0;
    }

    boolean isEmpty() {
        return this.size() == 0;
    }

    void enQueue(T element) {
        elements.add(element);
    }

    T deQueue() {
        first ++;
        return (T)elements.get(first-1);
    }

    int size() {
        return elements.size() - first;
    }


}
