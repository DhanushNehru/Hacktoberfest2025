import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Notepad_clone extends JFrame implements ActionListener {

    JTextArea textArea;
    JScrollPane scrollPane;
    String fileName = "";
    Notepad_clone() {
        setTitle(" Kishan's Notepad");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        String[] fileItems = {"New", "Open", "Save", "Exit"};
        for (String item : fileItems) {
            JMenuItem menuItem = new JMenuItem(item);
            menuItem.addActionListener(this);
            fileMenu.add(menuItem);
        }
        JMenu editMenu = new JMenu("Edit");
        String[] editItems = {"Cut", "Copy", "Paste", "Select All"};
        for (String item : editItems) {
            JMenuItem menuItem = new JMenuItem(item);
            menuItem.addActionListener(this);
            editMenu.add(menuItem);
        }
        JMenu formatMenu = new JMenu("Format");
        JMenuItem fontItem = new JMenuItem("Font");
        fontItem.addActionListener(this);
        formatMenu.add(fontItem);

        // Add menus to bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "New":
                textArea.setText("");
                fileName = "";
                break;

            case "Open":
                JFileChooser openFile = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
                openFile.setFileFilter(filter);
                int option = openFile.showOpenDialog(this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = openFile.getSelectedFile();
                    fileName = file.getAbsolutePath();
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        textArea.read(reader, null);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Could not open file");
                    }
                }
                break;

            case "Save":
                JFileChooser saveFile = new JFileChooser();
                int saveOption = saveFile.showSaveDialog(this);
                if (saveOption == JFileChooser.APPROVE_OPTION) {
                    File file = saveFile.getSelectedFile();
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        textArea.write(writer);
                        fileName = file.getAbsolutePath();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Could not save file");
                    }
                }
                break;

            case "Exit":
                int confirm = JOptionPane.showConfirmDialog(this, "Do you really want to exit?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                }
                break;

            case "Cut":
                textArea.cut();
                break;

            case "Copy":
                textArea.copy();
                break;

            case "Paste":
                textArea.paste();
                break;

            case "Select All":
                textArea.selectAll();
                break;

            case "Font":
                String fontName = JOptionPane.showInputDialog(this, "Enter font name (e.g., Arial, Courier New ,lora):");
                String fontSizeStr = JOptionPane.showInputDialog(this, "Enter font size:");
                try {
                    int fontSize = Integer.parseInt(fontSizeStr);
                    textArea.setFont(new Font(fontName, Font.PLAIN, fontSize));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input for font size pls give correct one.");
                }
                break;

            default:
                break;
        }
    }

    public static void main(String[] args) {
        new Notepad_clone();
    }
}