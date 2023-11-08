import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class RandProductSearchFrame extends JFrame{
    RandomAccessFile raf;
    ArrayList<Product> products;

    JPanel centerPnl;

    JTextField searchField;
    JTextArea resultsArea;
    JScrollPane resultsScroll;

    public RandProductSearchFrame() {
        createCenterPanel();

        readFile();

        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        setTitle("Random Product Search");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createCenterPanel() {
        centerPnl = new JPanel(new BorderLayout());
        searchField = new JTextField(20);
        resultsArea = new JTextArea(10, 30);
        resultsScroll = new JScrollPane(resultsArea);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchHandler();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                searchHandler();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                searchHandler();
            }
        });

        centerPnl.add(searchField, BorderLayout.NORTH);
        centerPnl.add(resultsScroll, BorderLayout.SOUTH);
        add(centerPnl, BorderLayout.CENTER);
    }

    private void readFile() {
        products = new ArrayList<Product>();
        try {
            File newFile = new File(System.getProperty("user.dir") + "/products.txt");
            raf = new RandomAccessFile(newFile, "r");
            raf.seek(0);

            while (raf.getFilePointer() < raf.length()) {
                byte[] bytes = new byte[12];
                raf.read(bytes);
                String ID = new String(bytes).replace("\0", "").trim();
                bytes = new byte[70];
                raf.read(bytes);
                String name = new String(bytes).replace("\0", "").trim();
                bytes = new byte[150];
                raf.read(bytes);
                String description = new String(bytes).replace("\0", "").trim();
                bytes = new byte[8];
                raf.read(bytes);
                double cost = ByteBuffer.wrap(bytes).getDouble();

                Product newProduct = new Product (ID, name, description, cost);
                resultsArea.append(newProduct.getID() + ", " + newProduct.getName() + ", " + newProduct.getDescription() + ", " + newProduct.getCost() + "\n");
                products.add(newProduct);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void searchHandler() {
        resultsArea.setText("");
        String filterWord = searchField.getText();

        for (Product prod : products) {
            if (prod.getName().toLowerCase().contains(filterWord)) {
                resultsArea.append(prod.getID() + ", " + prod.getName() + ", " + prod.getDescription() + ", " + prod.getCost() + "\n");
            }
        }
    }
}
