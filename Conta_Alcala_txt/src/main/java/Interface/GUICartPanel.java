package Interface;

import logic.LogicAlcala;
import model.Administrator;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class GUICartPanel {
    private final JPanel panel;
    private final LogicAlcala logicAlcala;

    public GUICartPanel(GUIstore guiStore) {
        this.logicAlcala = guiStore.getLogicAlcala();
        panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icons\\Shopping.png")));
                g.drawImage(backgroundImage.getImage(), 0, 0, panel.getWidth(), panel.getHeight(), this);
            }
        };

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        ImageIcon imageLogo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icons\\Logo.png")));
        Image imageLog = imageLogo.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon scaledImageLogo = new ImageIcon(imageLog);
        JLabel imgLog = new JLabel(scaledImageLogo);
        topPanel.add(imgLog, BorderLayout.WEST);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbcCart = new GridBagConstraints();
        gbcCart.insets = new Insets(10, 10, 10, 10);

        JLabel imagePro = new JLabel("Imagen");
        imagePro.setForeground(Color.WHITE);
        JLabel nameLabelTitle = new JLabel("Nombre");
        nameLabelTitle.setForeground(Color.WHITE);
        JLabel priceLabelTitle = new JLabel("Precio");
        priceLabelTitle.setForeground(Color.WHITE);
        JLabel numberLabel = new JLabel("Cantidad");
        numberLabel.setForeground(Color.WHITE);

        gbcCart.gridy++;
        gbcCart.gridx++;
        centerPanel.add(imagePro, gbcCart);
        gbcCart.gridx++;
        centerPanel.add(nameLabelTitle, gbcCart);
        gbcCart.gridx++;
        centerPanel.add(priceLabelTitle, gbcCart);
        gbcCart.gridx++;
        centerPanel.add(numberLabel, gbcCart);
        gbcCart.gridx++;

        ImageIcon eraseIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icons\\eraseCar.png")));
        Image eraseImage = eraseIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledEraseIcon = new ImageIcon(eraseImage);

        for (Product product : logicAlcala.getCart().getProducts()) {
            ImageIcon imageProduct;
            try {
                imageProduct = new ImageIcon(getClass().getResource("/Icons/" + product.getId() + ".png"));
            } catch (NullPointerException e) {
                // Si no se encuentra la imagen, usa una imagen predeterminada
                imageProduct = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icons/default.png")));
            }
            Image image = imageProduct.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon scaledImageProduct = new ImageIcon(image);
            JLabel imgProduct = new JLabel(scaledImageProduct);

            JLabel nameLabel = new JLabel(product.getNameProduct());
            nameLabel.setForeground(Color.WHITE);
            JLabel priceLabel = new JLabel("$" + product.getPrice());
            priceLabel.setForeground(Color.WHITE);
            JLabel purchased = new JLabel(String.valueOf(logicAlcala.getCart().getPurchased(product)));
            purchased.setForeground(Color.WHITE);
            JButton removeButton = new JButton("Eliminar de la Comanda", scaledEraseIcon);
            removeButton.setFont(new Font("Serif", Font.ITALIC, 12));
            removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            gbcCart.gridy++;
            gbcCart.gridx = 0;
            centerPanel.add(imgProduct, gbcCart);
            gbcCart.gridx++;
            centerPanel.add(nameLabel, gbcCart);
            gbcCart.gridx++;
            centerPanel.add(priceLabel, gbcCart);
            gbcCart.gridx++;
            centerPanel.add(purchased, gbcCart);
            gbcCart.gridx++;
            centerPanel.add(removeButton, gbcCart);

            removeButton.addActionListener(e -> {
                logicAlcala.eraseProductCart(product);
                JOptionPane.showMessageDialog(guiStore.getFrame(), "Producto eliminado de la Comanda.");
                guiStore.showCartPanel();
            });
        }
        JLabel totalLabel = new JLabel("Total: $" + logicAlcala.getCart().calcTotal());
        totalLabel.setForeground(Color.WHITE);
        gbcCart.gridy++;
        gbcCart.gridx = 3;
        centerPanel.add(totalLabel, gbcCart);

        ImageIcon clearIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icons\\vaciarCar1.png")));
        Image clearImage = clearIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledClaerIcon = new ImageIcon(clearImage);
        JButton clearButton = new JButton(" Vaciar  Comanda \n  ", scaledClaerIcon);
        clearButton.setFont(new Font("Serif", Font.ITALIC, 12));
        clearButton.setForeground(Color.WHITE);
        clearButton.setBackground(Color.black);
        clearButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbcCart.gridy++;
        gbcCart.gridx = -1;
        centerPanel.add(clearButton, gbcCart);

        JButton transfButton = new JButton("Transferencia");
        transfButton.setForeground(Color.WHITE);
        transfButton.setBackground(Color.black);
        transfButton.setFont(new Font("Serif", Font.ITALIC, 14));
        transfButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbcCart.gridx = 2;
        centerPanel.add(transfButton, gbcCart);

        JButton datafButton = new JButton("DATAFONO");
        datafButton.setForeground(Color.WHITE);
        datafButton.setBackground(Color.black);
        datafButton.setFont(new Font("Serif", Font.ITALIC, 14));
        datafButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbcCart.gridx = 3;
        centerPanel.add(datafButton, gbcCart);

        ImageIcon checkoutIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icons\\checkout.png")));
        Image checkoutImage = checkoutIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledCheckoutIcon = new ImageIcon(checkoutImage);
        JButton normalyButton = new JButton("Realizar Comanda", scaledCheckoutIcon);
        normalyButton.setForeground(Color.WHITE);
        normalyButton.setBackground(Color.black);
        normalyButton.setFont(new Font("Serif", Font.ITALIC, 14));
        normalyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbcCart.gridx = 4;
        centerPanel.add(normalyButton, gbcCart);

        JPanel buttomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttomPanel.setOpaque(false);

        ImageIcon backIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icons\\back.png")));
        Image backImage = backIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledBackIcon = new ImageIcon(backImage);
        JButton backButton = new JButton("Atrás", scaledBackIcon);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.black);
        backButton.setFont(new Font("Serif", Font.ITALIC, 11));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttomPanel.add(backButton);

        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icons/Shopping.png")));
                g.drawImage(backgroundImage.getImage(), 0, 0, backgroundImage.getIconWidth() + 500, backgroundImage.getIconHeight(), this);
            }
        };
        JScrollPane scrollPanel = new JScrollPane(backgroundPanel);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollBar verticalScrollBar = scrollPanel.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(16);
        backgroundPanel.add(centerPanel, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.PAGE_START);
        panel.add(scrollPanel, BorderLayout.CENTER);
        panel.add(buttomPanel, BorderLayout.PAGE_END);

        clearButton.addActionListener(e -> {
            logicAlcala.clearCart();
            guiStore.showCustomerMenuPanel();
        });

        transfButton.addActionListener(e -> {
            try {
                logicAlcala.setTipeTransfe(JOptionPane.showInputDialog("Tipo de transferencia"));
                logicAlcala.makePurchase("transferencia");
                JOptionPane.showMessageDialog(guiStore.getFrame(), logicAlcala.getFacture());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            guiStore.showCustomerMenuPanel();
        });

        datafButton.addActionListener(e -> {
            try {
                int option = JOptionPane.showOptionDialog(null, "¿Con Propina?", "Propina",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, new Object[]{"Sí", "No"}, "No");
                if (option == 0) {// Se seleccionó la opción "Sí"
                    logicAlcala.setPropina(Integer.parseInt(JOptionPane.showInputDialog("Valor Propina")));
                }
                logicAlcala.makePurchase("datafono");
                JOptionPane.showMessageDialog(guiStore.getFrame(), logicAlcala.getFacture());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            guiStore.showCustomerMenuPanel();
        });

        normalyButton.addActionListener(e -> {
            try {
                logicAlcala.makePurchase("noramaly");
                JOptionPane.showMessageDialog(guiStore.getFrame(), logicAlcala.getFacture());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            guiStore.showCustomerMenuPanel();
        });

        backButton.addActionListener(e -> guiStore.showCustomerMenuPanel());
    }

    public JPanel getPanel() {
        return panel;
    }
}