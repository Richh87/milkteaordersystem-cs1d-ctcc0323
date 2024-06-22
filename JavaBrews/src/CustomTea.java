import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomTea extends JFrame {
    private Map<Integer, Map<String, String>> orders;
    private Map<String, Integer> sizePrices;
    private JTextArea orderArea;
    private int orderNumber = 1;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public CustomTea() {
        this.orders = new HashMap<>();
        this.sizePrices = new HashMap<>();
        sizePrices.put("Medium", 100);
        sizePrices.put("Large", 120);
        sizePrices.put("XLarge", 130);

        initComponents();
    }

    private void initComponents() {
        setTitle("JavaBrews: Sip & Order Milk Tea System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel menuPanel = createMenuPanel();
        JPanel orderPanel = createOrderPanel();

        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(orderPanel, "Order");

        add(mainPanel);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        
        Color backgroundColor = new Color(0, 0, 0);
        Color textColor = new Color(230, 230, 230);
        Color buttonColor = new Color(70, 70, 70);
        Color buttonTextColor = new Color(230, 230, 230);

        panel.setBackground(backgroundColor);

       
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\Finals\\src\\Images\\IMAGEjava.png"); // dito lalagay yung logo
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(backgroundColor);
        topPanel.add(imageLabel, BorderLayout.WEST);

        JLabel headerLabel = new JLabel("Welcome to JavaBrews", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(textColor);
        topPanel.add(headerLabel, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);

        orderArea = new JTextArea();
        orderArea.setEditable(false);
        orderArea.setBackground(backgroundColor);
        orderArea.setForeground(textColor);
        JScrollPane scrollPane = new JScrollPane(orderArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));  
        buttonPanel.setBackground(backgroundColor);

        JButton orderButton = new JButton("Order Tea");
        JButton viewButton = new JButton("View Orders");
        JButton finishButton = new JButton("Finish Order");
        JButton exitButton = new JButton("Exit");

        
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        orderButton.setFont(buttonFont);
        viewButton.setFont(buttonFont);
        finishButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

      
        orderButton.setBackground(buttonColor);
        orderButton.setForeground(buttonTextColor);
        viewButton.setBackground(buttonColor);
        viewButton.setForeground(buttonTextColor);
        finishButton.setBackground(buttonColor);
        finishButton.setForeground(buttonTextColor);
        exitButton.setBackground(buttonColor);
        exitButton.setForeground(buttonTextColor);

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Order");
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewOrders();
            }
        });

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finishOrder();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmExit();
            }
        });

        buttonPanel.add(orderButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(finishButton);
        buttonPanel.add(exitButton);

        panel.add(buttonPanel, BorderLayout.EAST);

        return panel;
    }

    private JPanel createOrderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(0, 0, 0));
        Color textColor = new Color(230, 230, 230);
        Color buttonColor = new Color(70, 70, 70);
        Color buttonTextColor = new Color(230, 230, 230);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel sizeLabel = new JLabel("Choose your Size:");
        sizeLabel.setForeground(textColor);
        JComboBox<String> sizeBox = new JComboBox<>(new String[]{"Medium", "Large", "XLarge"});
        sizeBox.setBackground(buttonColor);
        sizeBox.setForeground(textColor);
        sizeBox.setPreferredSize(new Dimension(200, 25));

        JLabel baseLabel = new JLabel("Choose your Base:");
        baseLabel.setForeground(textColor);
        JComboBox<String> baseBox = new JComboBox<>(new String[]{"Black Tea", "Green tea", "Matcha"});
        baseBox.setBackground(buttonColor);
        baseBox.setForeground(textColor);
        baseBox.setPreferredSize(new Dimension(200, 25));

        JLabel flavorLabel = new JLabel("Choose your Flavour:");
        flavorLabel.setForeground(textColor);
        JComboBox<String> flavorBox = new JComboBox<>(new String[]{"Strawberry", "Matcha", "Chocolate", "Cookies n' Cream", "Taro", "Ube", "Vanilla"});
        flavorBox.setBackground(buttonColor);
        flavorBox.setForeground(textColor);
        flavorBox.setPreferredSize(new Dimension(200, 25));

        JLabel pearlLabel = new JLabel("Choose your Pearls:");
        pearlLabel.setForeground(textColor);
        JComboBox<String> pearlBox = new JComboBox<>(new String[]{"Regular", "Extra", "No Pearls"});
        pearlBox.setBackground(buttonColor);
        pearlBox.setForeground(textColor);
        pearlBox.setPreferredSize(new Dimension(200, 25));

        JLabel sugarLevelLabel = new JLabel("Choose your Sugar Level:");
        sugarLevelLabel.setForeground(textColor);
        JComboBox<String> sugarLevelBox = new JComboBox<>(new String[]{"No Sugar", "25%", "50%", "75%", "100%", "125%"});
        sugarLevelBox.setBackground(buttonColor);
        sugarLevelBox.setForeground(textColor);
        sugarLevelBox.setPreferredSize(new Dimension(200, 25));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(sizeLabel, gbc);
        gbc.gridx = 1;
        panel.add(sizeBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(baseLabel, gbc);
        gbc.gridx = 1;
        panel.add(baseBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(flavorLabel, gbc);
        gbc.gridx = 1;
        panel.add(flavorBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(pearlLabel, gbc);
        gbc.gridx = 1;
        panel.add(pearlBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(sugarLevelLabel, gbc);
        gbc.gridx = 1;
        panel.add(sugarLevelBox, gbc);

        JButton submitButton = new JButton("Submit Order");
        submitButton.setBackground(buttonColor);
        submitButton.setForeground(buttonTextColor);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String size = (String) sizeBox.getSelectedItem();
                String base = (String) baseBox.getSelectedItem();
                String flavor = (String) flavorBox.getSelectedItem();
                String pearl = (String) pearlBox.getSelectedItem();
                String sugarLevel = (String) sugarLevelBox.getSelectedItem();

                int sizePrice = sizePrices.getOrDefault(size, 0);
                int pearlsPrice = "Extra".equals(pearl) ? 10 : 0;
                int totalPrice = sizePrice + pearlsPrice;

                Map<String, String> orderDetails = new HashMap<>();
                orderDetails.put("Size", size);
                orderDetails.put("Base", base);
                orderDetails.put("Flavour", flavor);
                orderDetails.put("Pearls", pearl);
                orderDetails.put("Sugar Level", sugarLevel);
                orderDetails.put("Price", "Php " + totalPrice);

                StringBuilder orderSummary = new StringBuilder();
                orderSummary.append("Please confirm your order details:\n");
                for (Map.Entry<String, String> entry : orderDetails.entrySet()) {
                    orderSummary.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
                }

                UIManager.put("OptionPane.background", new Color(0, 0, 0));
                UIManager.put("Panel.background", new Color(0, 0, 0));
                UIManager.put("OptionPane.messageForeground", new Color(230, 230, 230));

                int confirmation = JOptionPane.showConfirmDialog(CustomTea.this, orderSummary.toString(), "Confirm Order", JOptionPane.YES_NO_CANCEL_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    orders.put(orderNumber, orderDetails);
                    orderArea.append("\nOrder " + orderNumber + " added successfully! Total Price: Php " + totalPrice + "\n");
                    orderNumber++;
                    cardLayout.show(mainPanel, "Menu");
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBackground(buttonColor);
        backButton.setForeground(buttonTextColor);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Menu");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);
        
        gbc.gridy = 6;
        panel.add(backButton, gbc);

        return panel;
    }

    private void viewOrders() {
        orderArea.setText("Current Orders:\n");
        int totalOrderPrice = 0;
        if (orders.isEmpty()) {
            orderArea.append("No orders yet.\n");
        } else {
            for (Map.Entry<Integer, Map<String, String>> entry : orders.entrySet()) {
                orderArea.append("Order " + entry.getKey() + ":\n");
                Map<String, String> details = entry.getValue();
                for (Map.Entry<String, String> detail : details.entrySet()) {
                    orderArea.append(detail.getKey() + " = " + detail.getValue() + "\n");
                    if ("Price".equals(detail.getKey())) {
                        String priceStr = detail.getValue().replace("Php ", "").trim();
                        try {
                            totalOrderPrice += Integer.parseInt(priceStr);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
                orderArea.append("\n");
            }
            orderArea.append("Total of all orders: Php " + totalOrderPrice + "\n");
        }
    }

    private void finishOrder() {
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(this, "There are Currently No Orders :)", "No Orders", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        for (Integer orderNum : orders.keySet()) {
            JButton orderButton = new JButton("Finish Order " + orderNum);
            orderButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    orders.remove(orderNum);
                    orderArea.append("Order " + orderNum + " finished successfully!\n");
                    JOptionPane.getRootFrame().dispose();
                }
            });
            panel.add(orderButton);
        }

        JOptionPane.showOptionDialog(this, panel, "Finish Order", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
    }

    private void saveOrdersToFile() {
        try (FileWriter writer = new FileWriter("Orders.txt")) {
            for (Map.Entry<Integer, Map<String, String>> entry : orders.entrySet()) {
                writer.write("Order " + entry.getKey() + ":\n");
                Map<String, String> details = entry.getValue();
                for (Map.Entry<String, String> detail : details.entrySet()) {
                    writer.write(detail.getKey() + " = " + detail.getValue() + "\n");
                }
                writer.write("\n");
            }
            JOptionPane.showMessageDialog(this, "Orders saved to Orders.txt", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving orders to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void confirmExit() {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit? Do you want to save orders before exiting?", "Exit", JOptionPane.YES_NO_CANCEL_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            saveOrdersToFile();
            System.exit(0);
        } else if (response == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CustomTea frame = new CustomTea();
                frame.setVisible(true);
            }
        });
    }
}
ff