import java.util.ArrayList;
import java.util.Scanner;
class point {
    int x;
    int y;
    public point (int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Bai3 {
    static point DiemCucTri(point[] Diem){
        point DiemCucTri = Diem[0];
        for (int i = 1; i<Diem.length; i++) {
            if (Diem[i].y < DiemCucTri.y){
                DiemCucTri=Diem[i];
            }
        }
        return DiemCucTri;
    }
    static double cos(point a, point b) {
        double x = b.x - a.x;
        double y = b.y - a.y;
        double ab = Math.sqrt(x*x + y*y);
        if (ab==0) {
            return 0;
        }
        return (x) / ab;
    }
    static int TichCoHuong(point A, point B, point C) {
    return (B.x - A.x) * (C.y - A.y)
         - (B.y - A.y) * (C.x - A.x);
}
    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so dim: ");
        int n = sc.nextInt();
        System.out.print("Nhap toa do cac diem:");
        point [] diem = new point[n];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            diem[i] = new point(x, y);
        }

        point cucTri = DiemCucTri(diem);

        java.util.Arrays.sort(diem, (a, b) -> Double.compare(cos(cucTri, b), cos(cucTri, a)));
        
        ArrayList<point> Hople = new ArrayList<>();

        point[] diem1 = new point[diem.length - 1];
        int index = 0;

        for (int i = 0; i < diem.length; i++) {
            if (!(diem[i].x == cucTri.x && diem[i].y == cucTri.y)) {
                diem1[index++] = diem[i];
            }
        }
        Hople.add(cucTri);
        Hople.add(diem1[0]);

        for (int i = 1; i < diem1.length; i++) {
            point B = diem1[i];

            while (Hople.size() >= 2) {
                point top = Hople.get(Hople.size() - 1);
                point nextToTop = Hople.get(Hople.size() - 2);

                if (TichCoHuong(nextToTop, top, B) > 0)
                    break;

                Hople.remove(Hople.size() - 1);
            }

            Hople.add(B);
        }
        for (point p : Hople) {
            System.out.println("Các diem hop le la: " + p.x + " " + p.y);
        }
    }
}
