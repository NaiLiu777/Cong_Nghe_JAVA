import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Lab3 extends JFrame {
   
    JLabel mainImage = new JLabel();
    JLabel nameLabel = new JLabel();
    JLabel priceLabel = new JLabel();
    JLabel descLabel = new JLabel();
    public Lab3() {
        setTitle("Lab3");
        setSize(1100, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));

        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout(0, 0));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(300, 0));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setMaximumSize(new Dimension(500, 1)); 
        separator.setForeground(Color.black); 

        nameLabel.setFont(new Font("Arial", Font.BOLD, 22));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        descLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descLabel.setForeground(Color.GRAY);

        mainImage.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        updateMainView("img1.png", "4DFWD PULSE SHOES", "$160.00", "This product is excluded from all promotional discounts and offers.");

        leftPanel.add(mainImage);
        leftPanel.add(Box.createVerticalStrut(10));   
        leftPanel.add(separator);
        leftPanel.add(nameLabel);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(priceLabel);
        leftPanel.add(Box.createVerticalStrut(10));
        JLabel brandHint = new JLabel("Adidas");
        brandHint.setForeground(Color.GRAY);
        leftPanel.add(brandHint);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(descLabel);

        add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new GridLayout(2, 4, 15, 15));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(70, 10, 20, 10));
        rightPanel.setBackground(Color.WHITE);

        // Dữ liệu mẫu
        String[] names = {
            "4DFWD PULSE SHOES", 
            "FORUM MID SHOES", 
            "SUPERNOVA SHOES", 
            "Adidas NMD",
            "Adidas NMD Black", 
            "4DFWD PULSE SHOES", 
            "4DFWD PULSE SHOES", 
            "FORUM MID SHOES"
        };

        String[] prices = {
            "$160.00", 
            "$100.00", 
            "$150.00", 
            "$160.00", 
            "$120.00", 
            "$160.00", 
            "$160.00", 
            "$100.00"
        };

        String[] images = {
            "img1.png", 
            "img2.png", 
            "img3.png", 
            "img4.png", 
            "img5.png", 
            "img6.png", 
            "img1.png", 
            "img2.png"
        };

        String[] descs = {
            "This product is excluded from all promotional discounts and offers.",
            "This product is excluded from all promotional discounts and offers.",
            "NMD City Stock 2",
            "NMD City Stock 2",
            "NMD City Stock 2",
            "This product is excluded from all promotional discounts and offers.",
            "This product is excluded from all promotional discounts and offers.",
            "This product is excluded from all promotional discounts and offers."
        };
        for (int i = 0; i < names.length; i++) {
            rightPanel.add(createItem(names[i], prices[i], images[i], descs[i]));
        }
        add(rightPanel, BorderLayout.CENTER); 
    }

    private void updateMainView(String imagePath, String name, String price, String desc) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image imgScaled = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        mainImage.setIcon(new ImageIcon(imgScaled));
        nameLabel.setText(name);
        priceLabel.setText(price);
        descLabel.setText("<html><p style='width:250px;'>" + desc + "</p></html>");
    }

    class RoundedPanel extends JPanel {
        private int radius;

        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false); // Quan trọng: để phần góc thừa không bị hiển thị
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
        
            g2.setColor(new Color(230, 230, 230));
            g2.setStroke(new BasicStroke(1));      
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
        }
     }
    private JPanel createItem(String name, String price, String imagePath, String desc) {
        RoundedPanel itemPanel = new RoundedPanel(25);
        itemPanel.setLayout(new BorderLayout(5, 5));
        itemPanel.setBackground(new Color(245, 245, 245)); 
        
        itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

       
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setOpaque(false); 
        JLabel lblName = new JLabel(name);
        lblName.setFont(new Font("Arial", Font.BOLD, 14));

        String shortDesc = (desc.length() > 25) ? desc.substring(0, 25) + "..." : desc;
        JLabel lblShortDesc = new JLabel("<html><font color='gray' size='2'>" + shortDesc + "</font></html>");
        
        topPanel.add(lblName);
        topPanel.add(lblShortDesc);

        ImageIcon icon = new ImageIcon(imagePath);
        Image imgScaled = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        JLabel lblImg = new JLabel(new ImageIcon(imgScaled));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false); 
        JLabel lblBrand = new JLabel("Adidas");
        lblBrand.setForeground(Color.GRAY);
        JLabel lblPrice = new JLabel(price);
        lblPrice.setFont(new Font("Arial", Font.BOLD, 15));
        
        bottomPanel.add(lblBrand, BorderLayout.WEST);
        bottomPanel.add(lblPrice, BorderLayout.EAST);

        itemPanel.add(topPanel, BorderLayout.NORTH);
        itemPanel.add(lblImg, BorderLayout.CENTER);
        itemPanel.add(bottomPanel, BorderLayout.SOUTH);

        itemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateMainView(imagePath, name, price, desc);
                itemPanel.setBackground(new Color(230, 240, 255)); 
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                itemPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                itemPanel.setBackground(new Color(235, 235, 235));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                itemPanel.setBackground(new Color(245, 245, 245));
            }
        });

        return itemPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lab3().setVisible(true));
    }
}