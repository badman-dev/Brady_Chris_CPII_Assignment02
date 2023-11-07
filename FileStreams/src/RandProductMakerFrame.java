import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RandProductMakerFrame extends JFrame {
    JPanel centerPnl;
    JLabel idLbl;
    JLabel nameLbl;
    JLabel descLbl;
    JLabel priceLbl;

    JTextField idField;
    JTextField nameField;
    JTextField descField;
    JTextField priceField;

    JButton submitBtn;
    JLabel countLbl;
    JTextField countField;

    public RandProductMakerFrame() {
        createCenterPanel();

        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        setTitle("Random Product Maker");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createCenterPanel() {
        centerPnl = new JPanel();
        LayoutManager layout = new BoxLayout(centerPnl, BoxLayout.PAGE_AXIS);
        centerPnl.setLayout(layout);
        idLbl = new JLabel("ID:");
        idField = new JTextField(6);
        idField.setMaximumSize(idField.getPreferredSize());
        nameLbl = new JLabel("Name:");
        nameField = new JTextField(35);
        nameField.setMaximumSize(nameField.getPreferredSize());
        descLbl = new JLabel("Description:");
        descField = new JTextField(75);
        descField.setMaximumSize(descField.getPreferredSize());
        priceLbl = new JLabel("Price ($):");
        priceField = new JTextField(6);
        priceField.setMaximumSize(priceField.getPreferredSize());
        submitBtn = new JButton("Submit");
        countLbl = new JLabel("Entry count:");
        countField = new JTextField(6);
        countField.setMaximumSize(countField.getPreferredSize());

        submitBtn.addActionListener((ActionEvent ae) -> handleSubmit());

        centerPnl.add(idLbl);
        centerPnl.add(idField);
        centerPnl.add(nameLbl);
        centerPnl.add(nameField);
        centerPnl.add(descLbl);
        centerPnl.add(descField);
        centerPnl.add(priceLbl);
        centerPnl.add(priceField);
        centerPnl.add(submitBtn);
        centerPnl.add(countLbl);
        centerPnl.add(countField);
        add(centerPnl, BorderLayout.CENTER);
    }

    private void handleSubmit() {

    }
}
