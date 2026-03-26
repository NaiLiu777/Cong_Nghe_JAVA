import java.util.*;
import java.util.Scanner;

public class Bai4 {

    static int maxLen = 0;             
    static List<Integer> best = new ArrayList<>(); 
    static void backtrack(int[] A, int i, int sum, int k, List<Integer> current) {

        if (sum == k) {
            if (current.size() > maxLen) {
                maxLen = current.size();
                best = new ArrayList<>(current); 
            }
            return; 
        }

        if (i == A.length || sum > k) return;

        current.add(A[i]); 
        backtrack(A, i + 1, sum + A[i], k, current);

        current.remove(current.size() - 1);
        backtrack(A, i + 1, sum, k, current);
    }

    public static void main(String[] args) {
        
        Scanner sc= new Scanner(System.in);
        System.out.print("Nhap n va k");
        int n = sc.nextInt();
        int k = sc.nextInt();
         int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        backtrack(A, 0, 0, k, new ArrayList<>());

        System.out.println("Day dai nhat:");
        for (int x : best) {
            System.out.print(x + " ");
        }
    }
}

