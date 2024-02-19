import java.util.Arrays;

public class MyHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f; // Double the size when 75% full

    private Entry<K, V>[] table; // index -> Entry<K, V>
    private int size;

    public MyHashMap() {
        table = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    public void put(K key, V value) {
        int hash = hash(key);
        int index = hash % table.length;

        // if the key already exists, update the value
        Entry<K, V>entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            entry = entry.next;
        }
        // if the key does not exist, add a new entry
        Entry<K, V> newEntry = new Entry<>(key, value, hash, table[index]);
        table[index] = newEntry;
        size++;
        if (size > table.length * LOAD_FACTOR) {
            resizeTable();
        }
    }

    public V get(K key) {
        int hash = hash(key);
        int index = getIndex(hash, table.length);

        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }

        return null;
    }

    public void remove(K key) {
        int hash = hash(key);
        int index = getIndex(hash, table.length);

        Entry<K, V> entry = table[index];
        Entry<K, V> prev = null;
        while (entry != null) {
            if (entry.key.equals(key)) {
                if (prev == null) {
                    table[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return;
            }
            prev = entry;
            entry = entry.next;
        }
    }

    public int size() {
        return size;
    }

    private int hash(K key) {
        return key.hashCode();
    }

    private int getIndex(int hash, int capacity) {
        return hash & (capacity - 1);
    }

    private void resizeTable() {
        Entry<K, V>[] oldTable = table;
        int newCapacity = oldTable.length * 2;
        table = Arrays.copyOf(table, newCapacity);
    }

    private static class Entry<K, V> {
        private K key;
        private V value;
        private int hash;
        private Entry<K, V> next;

        public Entry(K key, V value, int hash, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }
    }

}