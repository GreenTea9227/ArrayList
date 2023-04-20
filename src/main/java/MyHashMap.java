import java.util.Optional;

public class MyHashMap<T, R> implements MyCustomMap<T, R> {

    private Node<T, R>[] keys;
    private final int SIZE = 10000;
    private int count = 0;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        keys = (Node<T, R>[]) new Node<?, ?>[SIZE];
    }


    @Override
    public int size() {
        return count;
    }

    @Override
    public R get(T t) {

        Optional<Node<T, R>> node = findNodeByKey(t);

        return node.isEmpty() ? null : node.get().value;

    }

    @Override
    public boolean containsKey(T t) {
        return findNodeByKey(t).isPresent();
    }

    @Override
    public synchronized R put(T t, R r) {

        if (isFirstNode(t)) {
            keys[getHashCode(t)] = new Node<>(t, r);
            count++;
            return null;
        }

        Node<T, R> currentKey = keys[getHashCode(t)];
        while (currentKey != null) {
            if (currentKey.key.equals(t)) {
                R value = currentKey.value;
                currentKey = new Node<>(t, r);
                return value;
            }
            currentKey = currentKey.right;
        }

        Node<T, R> key = keys[getHashCode(t)];
        Node<T, R> insertNode = new Node<>(t, r);
        insertNode.right = key;
        key.left = insertNode;
        keys[getHashCode(t)] = insertNode;
        count++;

        return null;

    }

    private Optional<Node<T, R>> findNodeByKey(T t) {
        if (isFirstNode(t)) {
            return Optional.empty();
        }
        Node<T, R> currentKey = getFirstNode(t);

        while (currentKey != null) {
            if (currentKey.key.equals(t))
                return Optional.of(currentKey);
            currentKey = currentKey.right;
        }

        return Optional.empty();
    }

    private boolean isFirstNode(T t) {
        return getFirstNode(t) == null;
    }

    private Node<T, R> getFirstNode(T t) {
        return keys[getHashCode(t)];
    }

    @Override
    public boolean containsValue(R r) {
        for (int i = 0; i < keys.length; i++) {
            Node<T, R> key = keys[i];
            while (key != null) {
                if (key.value.equals(r))
                    return true;
                key = key.right;
            }
        }
        return false;
    }

    @Override
    public synchronized R remove(T t) {
        Optional<Node<T, R>> optionalNode = findNodeByKey(t);

        if (optionalNode.isEmpty())
            return null;

        Node<T, R> trNode = optionalNode.get();
        Node<T, R> leftNode = trNode.left;
        Node<T, R> rightNode = trNode.right;

        if (leftNode != null && rightNode != null) {
            leftNode.right = rightNode;
            rightNode.left = leftNode;
            R value = trNode.value;
            trNode = null;
            count--;
            return value;
        }

        R value = trNode.value;
        trNode = null;
        count--;
        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {

        keys = (Node<T, R>[]) new Node<?, ?>[SIZE];
        count = 0;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    private int getHashCode(T t) {
        return t.hashCode() % SIZE;
    }

    public static class Node<T, R> {
        private Node<T, R> left;
        private Node<T, R> right;
        private final T key;
        private final R value;

        public Node(T key, R value) {
            this.key = key;
            this.value = value;
        }

    }
}
