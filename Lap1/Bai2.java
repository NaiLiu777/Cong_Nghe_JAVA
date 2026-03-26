import java.util.Scanner;
public class Bai2 {
    public static void main(String[] args) {
        int n = 1000000;
        double dx= 2.0/n;
        double area = 0;
        for (int i=0; i<n; i++){
            double x = -1 +i*dx;
            double y = Math.sqrt(1-x*x);
            area += 2*y*dx;
        }
        double pi = area/1;
        System.out.print("So pi co gia tri la: "+ pi);
    }
}
