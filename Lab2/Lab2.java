import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Lab2 extends JPanel implements ActionListener, KeyListener {

    // ===== Bài 1 =====
    int width = 360, height = 640;
    Image bg;

    // ===== Bài 2 =====
    Image birdImg;
    int birdX = 80, birdY = 300;
    int birdVelocity = 0;
    int gravity = 1;

    // ===== Bài 3 =====
    class Pipe {
        int x, y;
        Image img;
        boolean passed = false;

        Pipe(int x, int y, Image img) {
            this.x = x;
            this.y = y;
            this.img = img;
        }
    }

    ArrayList<Pipe> pipes;
    Image topPipeImg;
    Image bottomPipeImg;
    Random rand = new Random();

    Timer gameLoop;
    int pipeGap = 150;
    int pipeWidth = 64;

    // ===== Bài 4 =====
    boolean gameOver = false;
    int score = 0;

    Lab2() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        addKeyListener(this);

        // load ảnh
        bg = new ImageIcon("flappybirdbg.png").getImage();
        birdImg = new ImageIcon("flappybird.png").getImage();
        topPipeImg = new ImageIcon("toppipe.png").getImage();
        bottomPipeImg = new ImageIcon("bottompipe.png").getImage();

        pipes = new ArrayList<>();

        gameLoop = new Timer(20, this); // game loop
        gameLoop.start();

        // spawn pipe mỗi 1.5s
        new Timer(1500, e -> placePipes()).start();
    }

    void placePipes() {
        int y = rand.nextInt(height / 2);
        pipes.add(new Pipe(width, y - 320, topPipeImg));
        pipes.add(new Pipe(width, y + pipeGap, bottomPipeImg));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // background
        g.drawImage(bg, 0, 0, width, height, null);

        // bird
        g.drawImage(birdImg, birdX, birdY, 34, 24, null);

        // pipes
        for (Pipe p : pipes) {
            g.drawImage(p.img, p.x, p.y, pipeWidth, 320, null);
        }

        // score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Score: " + score, 10, 40);

        if (gameOver) {
            g.drawString("GAME OVER", 70, 300);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            update();
        }
        repaint();
    }

    void update() {
        birdVelocity += gravity;
        birdY += birdVelocity;

        // pipe di chuyển
        for (Pipe p : pipes) {
            p.x -= 4;

            if (p.img == bottomPipeImg && !p.passed && p.x + pipeWidth < birdX) {
                p.passed = true;
                score++;
            }

            Rectangle birdRect = new Rectangle(birdX, birdY, 34, 24);
            Rectangle pipeRect = new Rectangle(p.x, p.y, pipeWidth, 320);

            if (birdRect.intersects(pipeRect)) {
                gameOver = true;
            }
        }

        // đụng đất
        if (birdY > height) {
            gameOver = true;
        }
    }

    // ===== điều khiển =====
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE ||
            e.getKeyCode() == KeyEvent.VK_ENTER) {

            if (gameOver) {
                restartGame();
            } else {
                birdVelocity = -10; // bay lên
            }
        }
    }

    void restartGame() {
        birdY = 300;
        birdVelocity = 0;
        pipes.clear();
        score = 0;
        gameOver = false;
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        Lab2 game = new Lab2();

        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // không cho resize
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}