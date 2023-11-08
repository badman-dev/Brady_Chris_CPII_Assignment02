import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class RandProductMakerFrame extends JFrame {
    int idLimit = 6;
    int nameLimit = 35;
    int descLimit = 75;

    int count = 0;

    JPanel centerPnl;
    JLabel idLbl;
    JLabel nameLbl;
    JLabel descLbl;
    JLabel costLbl;

    JTextField idField;
    JTextField nameField;
    JTextField descField;
    JTextField costField;

    JButton submitBtn;
    JLabel countLbl;
    JTextField countField;

    RandomAccessFile raf;

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
        idField = new JTextField(idLimit);
        idField.setMaximumSize(idField.getPreferredSize());
        nameLbl = new JLabel("Name:");
        nameField = new JTextField(nameLimit);
        nameField.setMaximumSize(nameField.getPreferredSize());
        descLbl = new JLabel("Description:");
        descField = new JTextField(descLimit);
        descField.setMaximumSize(descField.getPreferredSize());
        costLbl = new JLabel("Price ($):");
        costField = new JTextField(6);
        costField.setMaximumSize(costField.getPreferredSize());
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
        centerPnl.add(costLbl);
        centerPnl.add(costField);
        centerPnl.add(submitBtn);
        centerPnl.add(countLbl);
        centerPnl.add(countField);
        add(centerPnl, BorderLayout.CENTER);
    }

    private void handleSubmit() {
        String idInput = idField.getText();
        String nameInput = nameField.getText();
        String descInput = descField.getText();
        double costInput = Double.parseDouble(costField.getText());

        Product newProduct = new Product(idInput, nameInput, descInput, costInput);
        newProduct.formatFields(idLimit, nameLimit, descLimit);

        try {
            if (raf == null) {
                File newFile = new File(System.getProperty("user.dir") + "/products.txt");
                raf = new RandomAccessFile(newFile, "rw");
            }

            raf.writeChars(newProduct.getID());
            raf.writeChars(newProduct.getName());
            raf.writeChars(newProduct.getDescription());
            raf.writeDouble(newProduct.getCost());

            addCount(1);
            clearFields();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    private void addCount(int num) {
        count += num;
        countField.setText(String.valueOf(count));
    }

    private void clearFields() {
        idField.setText(null);
        nameField.setText(null);
        descField.setText(null);
        costField.setText(null);
    }
}
