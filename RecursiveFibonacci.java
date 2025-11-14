import java.util.Scanner;

public class RecursiveFibonacci {

    // Recursive function to calculate nth Fibonacci number
    public static int fibonacci(int n) {
        // Base cases
        if (n <= 1)
            return n;
        else
            // Recursive formula: F(n) = F(n-1) + F(n-2)
            return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of terms: ");
        int n = sc.nextInt();

        System.out.println("\nFibonacci Series (Recursive): ");
        long start = System.nanoTime(); // start time measurement

        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }

        long end = System.nanoTime(); // end time measurement
        double timeTaken = (end - start) / 1000.0; // microseconds

        System.out.println("\n\n=== Time and Space Complexity Analysis ===");
        System.out.printf("Time Taken: %.2f microseconds%n", timeTaken);
        System.out.println("Time Complexity: O(2^n)");
        System.out.println("Space Complexity: O(n)");

        sc.close();
    }
}

/*
---------------------------------------------
    ANALYSIS OF TIME AND SPACE COMPLEXITY
---------------------------------------------

1️⃣ TIME COMPLEXITY:
   - Each call to fibonacci(n) generates two more calls:
        fibonacci(n-1) and fibonacci(n-2)
   - Hence, the total number of calls grows exponentially.
   - Recurrence relation:
        T(n) = T(n-1) + T(n-2) + O(1)
   - Therefore:
        Time Complexity = O(2^n)

2️⃣ SPACE COMPLEXITY:
   - The maximum depth of the recursive call stack = n
     (for calls: fibonacci(n) → fibonacci(n-1) → ... → fibonacci(0))
   - Therefore:
        Space Complexity = O(n)

✅ FINAL SUMMARY:
   Approach          : Recursive
   Time Complexity   : O(2^n)
   Space Complexity  : O(n)
---------------------------------------------
*/
