Fibonacci

import java.util.Scanner;

public class NonRecursiveFibonacci {

    // Non-recursive (iterative) Fibonacci function
    public static void fibonacci(int n) {
        int n1 = 0, n2 = 1, n3;

        // Print first two terms
        if (n > 0)
            System.out.print(n1 + " ");
        if (n > 1)
            System.out.print(n2 + " ");

        // Loop to calculate next Fibonacci numbers
        for (int i = 2; i < n; i++) {
            n3 = n1 + n2;
            System.out.print(n3 + " ");
            n1 = n2;
            n2 = n3;
        }
          public static int fibonacci(int n) {
        // Base cases
        if (n <= 1)
            return n;
        else
            // Recursive formula: F(n) = F(n-1) + F(n-2)
            return fibonacci(n - 1) + fibonacci(n - 2);
    }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of terms: ");
        int n = sc.nextInt();

        System.out.println("\nFibonacci Series (Non-Recursive): ");
        long start = System.nanoTime(); // Start time measurement

        fibonacci(n); // Function call

        long end = System.nanoTime();   // End time measurement
        double timeTaken = (end - start) / 1000.0; // microseconds

        // ---------- Time & Space Complexity Analysis ----------
        System.out.println("\n\n=== Time and Space Complexity Analysis ===");
        System.out.printf("Time Taken: %.2f microseconds%n", timeTaken);
        System.out.println("Time Complexity: O(n)");
        System.out.println("Space Complexity: O(1)");

        sc.close();
    }
}


//3 Write a program to implement Huffman Encoding using a 
//greedy strategy. 


import java.util.*;

class Node {
    char ch;
    int freq;
    Node left, right;

    Node(char c, int f) {
        ch = c;
        freq = f;
    }

    Node(Node l, Node r) {
        left = l;
        right = r;
        freq = l.freq + r.freq;
    }
}

class HuffmanEncoding {
    static Map<Character, String> codes = new HashMap<>();

    static void buildCode(Node root, String s) {
        if (root == null) return;

        // Leaf node (character found)
        if (root.left == null && root.right == null) {
            codes.put(root.ch, s);
            return;
        }

        buildCode(root.left, s + "0");
        buildCode(root.right, s + "1");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter string: ");
        String s = sc.nextLine();

        // Step 1: Frequency map
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray())
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);

        // Step 2: Min-Heap
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.freq));

        for (Map.Entry<Character, Integer> e : freqMap.entrySet())
            pq.add(new Node(e.getKey(), e.getValue()));

        // Step 3: Build Huffman Tree
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.add(new Node(left, right));
        }

        Node root = pq.peek();

        // Step 4: Generate Huffman Codes
        buildCode(root, "");

        // Step 5: Display codes
        System.out.println("\nCharacter | Huffman Code");
        for (Map.Entry<Character, String> e : codes.entrySet())
            System.out.println(e.getKey() + " -> " + e.getValue());
    }
}

//4 Write a program to solve a fractional Knapsack problem using a 
//greedy method. 


import java.util.*;

class Item {
    int value, weight;
    Item(int v, int w) {
        value = v;
        weight = w;
    }
}

public class FractionalKnapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];

        System.out.println("Enter value and weight of each item:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + " value: ");
            int v = sc.nextInt();
            System.out.print("Item " + (i + 1) + " weight: ");
            int w = sc.nextInt();
            items[i] = new Item(v, w);
        }

        System.out.print("Enter capacity of knapsack: ");
        int capacity = sc.nextInt();

        // Step 1: Sort items by value/weight ratio (descending order)
        Arrays.sort(items, (a, b) -> Double.compare((double)b.value / b.weight, (double)a.value / a.weight));

        double totalValue = 0.0;

        // Step 2: Pick items greedily
        for (int i = 0; i < n; i++) {
            if (capacity >= items[i].weight) {
                // Take the whole item
                totalValue += items[i].value;
                capacity -= items[i].weight;
            } else {
                // Take fraction of the item
                double fraction = (double) capacity / items[i].weight;
                totalValue += items[i].value * fraction;
                break; // Knapsack is full
            }
        }

        // Step 3: Print result
        System.out.println("\nMaximum value in Knapsack = " + totalValue);
    }
}
