public class MyHashMapTest {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        // Insert some key-value pairs
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        // Retrieve values
        System.out.println(map.get("Two"));  // Output: 2
        System.out.println(map.get("Four")); // Output: null

        // Remove a key-value pair
        map.remove("One");
        System.out.println(map.size());      // Output: 2

        // Insert additional key-value pairs
        map.put("Four", 4);
        map.put("Five", 5);

        // Retrieve values again
        System.out.println(map.get("Three"));  // Output: 3
        System.out.println(map.get("Five"));   // Output: 5

        // Test size
        System.out.println(map.size());  // Output: 4
    }
}
