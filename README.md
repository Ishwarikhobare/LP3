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


//5 Write a program to solve a 0-1 Knapsack problem using 
//dynamic programming or branch and bound strategy. 


import java.util.*;

class Knapsack_0_1 {
    static int knapsack(int[] val, int[] wt, int cap) {
        int n = val.length;
        int[][] dp = new int[n + 1][cap + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= cap; w++) {
                if (wt[i - 1] <= w)
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }
        return dp[n][cap];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();
        int[] val = new int[n];
        int[] wt = new int[n];

        System.out.print("Enter values: ");
        for (int i = 0; i < n; i++) val[i] = sc.nextInt();

        System.out.print("Enter weights: ");
        for (int i = 0; i < n; i++) wt[i] = sc.nextInt();

        System.out.print("Enter capacity: ");
        int cap = sc.nextInt();

        System.out.println("Maximum Value: " + knapsack(val, wt, cap));
    }
}

//6 Design n-Queens matrix having first Queen placed. Use 
//backtracking to place remaining Queens to generate the final n
//queen‘s matrix. 

import java.util.*;

public class NQueens {

    int size;
    boolean[][] board;
    int count = 0;
    int fixedRow = -1, fixedCol = -1;

    // Constructor
    NQueens() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of chessboard: ");
        size = sc.nextInt();

        board = new boolean[size][size];
    }

    // Print board
    void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] ? "Q " : "X ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check if safe to place queen
    boolean isSafe(int row, int col) {

        // Check column
        for (int i = 0; i < size; i++)
            if (board[i][col])
                return false;

        // Back slash (\) diagonal upper
        int i = row, j = col;
        while (i >= 0 && j >= 0) {
            if (board[i][j]) return false;
            i--;
            j--;
        }

        // Back slash (\) diagonal lower
        i = row; j = col;
        while (i < size && j < size) {
            if (board[i][j]) return false;
            i++;
            j++;
        }

        // Forward slash (/) diagonal upper
        i = row; j = col;
        while (i >= 0 && j < size) {
            if (board[i][j]) return false;
            i--;
            j++;
        }

        // Forward slash (/) diagonal lower
        i = row; j = col;
        while (i < size && j >= 0) {
            if (board[i][j]) return false;
            i++;
            j--;
        }

        return true;
    }

    // Set the first queen manually
    void setFirstQueen() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter coordinates of first queen:");
        System.out.print("Enter row (1-" + size + "): ");
        fixedRow = sc.nextInt() - 1;

        System.out.print("Enter column (1-" + size + "): ");
        fixedCol = sc.nextInt() - 1;

        board[fixedRow][fixedCol] = true;
        printBoard();
    }

    // Backtracking function
    void solve(int row) {

        if (row == size) {
            count++;
            printBoard();
            return;
        }

        // If fixed queen is in this row → skip placing and move to next row
        boolean rowHasQueen = false;
        for (int j = 0; j < size; j++) {
            if (board[row][j]) {
                rowHasQueen = true;
                break;
            }
        }

        if (rowHasQueen) {
            solve(row + 1);
            return;
        }

        // Try all columns
        for (int col = 0; col < size; col++) {
            if (isSafe(row, col)) {
                board[row][col] = true;

                solve(row + 1);

                board[row][col] = false; // backtrack
            }
        }
    }

    // Final message
    void displayMessage() {
        if (count > 0)
            System.out.println("Solution exists for the given position of the queen.");
        else
            System.out.println("Solution doesn't exist for the given position of the queen.");
    }

    // Main
    public static void main(String[] args) {
        NQueens solver = new NQueens();
        solver.setFirstQueen();
        solver.solve(0);
        solver.displayMessage();
    }
}

