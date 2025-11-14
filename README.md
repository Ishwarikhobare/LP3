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

