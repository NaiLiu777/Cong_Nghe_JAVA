import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap ban kinh r: ");
        double r = sc.nextDouble();

        int n = 1000000; 
        double dx = (2 * r) / n;
        double area = 0;

        for ( int i = -0; i < n; i++) {
            double x = -r + i * dx;
            double y = Math.sqrt(r * r - x * x);
            area += 2 * y * dx;
        }

        System.out.println("Dien tich xap xi: " + area);
    }
}