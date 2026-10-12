
package lab2practice;

import java.util.LinkedList;
import javax.swing.*;

public class Main extends JFrame {

    private JTextField txtInput;
    private DefaultListModel<String> listModel;
    private JList<String> listTask;
    private JScrollPane scrollPane;
    private JButton btnAdd, btnRemove, btnCompleted, btnClear;
    private LinkedList<String> linkedList;

    Main() {
        // Dito binabago ang title ng application
        setTitle("LAB 2 ACT GUI");

        // Dito ina-adjust ang laki ng buong window
        // setSize(width, height)
        setSize(500, 500);

        // Manual positioning ng mga components
        setLayout(null);

        // Para mag-close ang program kapag sinara ang window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TEXT FIELD
        txtInput = new JTextField();

        // Dito ina-adjust ang position at size ng input field
        // setBounds(x, y, width, height)
        txtInput.setBounds(50, 50, 400, 30);
        add(txtInput);

        // Frontend list model
        // Dito naka-store ang mga task na nakikita sa GUI
        listModel = new DefaultListModel<>();

        // Backend LinkedList
        // Dito naka-store ang mga task sa LinkedList
        linkedList = new LinkedList<>();

        // JList
        listTask = new JList<>(listModel);

        // SCROLL PANE
        scrollPane = new JScrollPane(listTask);

        // Dito ina-adjust ang position at size ng listahan
        scrollPane.setBounds(50, 100, 400, 250);
        add(scrollPane);

        // setBounds(x, y, width, height)
        //
        // x      = pakanan o pakaliwa ang button
        // y      = pataas o pababa ang button
        // width  = lapad ng button
        // height = taas ng button
        //
        // Mas mataas na x = mas pakanan
        // Mas mababang x = mas pakaliwa
        // Mas mataas na y = mas pababa
        // Mas mababang y = mas pataas
        // ==========================================

        // ADD BUTTON
        btnAdd = new JButton("Add");

        // Dito baguhin ang position at size ng Add button
        btnAdd.setBounds(50, 400, 80, 30);
        add(btnAdd);

        // REMOVE BUTTON
        btnRemove = new JButton("Remove");

        // Dito baguhin ang position at size ng Remove button
        btnRemove.setBounds(156, 400, 80, 30);
        add(btnRemove);

        // COMPLETED BUTTON
        btnCompleted = new JButton("Completed");

        // Dito baguhin ang position at size ng Completed button
        btnCompleted.setBounds(262, 400, 80, 30);
        add(btnCompleted);

        // CLEAR BUTTON
        btnClear = new JButton("Clear");

        // Dito baguhin ang position at size ng Clear button
        btnClear.setBounds(368, 400, 80, 30);
        add(btnClear);

        // ADD FUNCTION
        // Kapag pinindot ang Add, madadagdag ang task sa listahan
        btnAdd.addActionListener(e -> {
            String task = txtInput.getText().trim();

            // Hindi magdadagdag kapag walang nakalagay na text
            if (!task.isEmpty()) {

                // Idadagdag ang task sa backend LinkedList
                linkedList.add(task);

                // Ipapakita ang task sa GUI listahan
                listModel.addElement(task);

                // Lilinisin ang input field pagkatapos mag-add
                txtInput.setText("");
            }
        });

        // REMOVE FUNCTION
        // Tatanggalin ang napiling task sa listahan
        btnRemove.addActionListener(e -> {
            int index = listTask.getSelectedIndex();

            // Titingnan kung may napiling task
            if (index != -1) {

                // Tatanggalin sa backend LinkedList
                linkedList.remove(index);

                // Tatanggalin din sa GUI listahan
                listModel.remove(index);
            }
        });

        // COMPLETED FUNCTION
        // Lalagyan ng [Completed] ang napiling task
        btnCompleted.addActionListener(e -> {
            int index = listTask.getSelectedIndex();

            // Titingnan kung may napiling task
            if (index != -1) {
                String task = linkedList.get(index);

                // Para hindi maulit ang [Completed] sa task
                if (!task.startsWith("[Completed] ")) {
                    String completedTask = "[Completed] " + task;

                    // I-update ang task sa backend LinkedList
                    linkedList.set(index, completedTask);

                    // I-update ang task sa GUI listahan
                    listModel.set(index, completedTask);
                }
            }
        });

        // CLEAR FUNCTION
        // Buburahin ang lahat ng task sa listahan
        btnClear.addActionListener(e -> {

            // Buburahin ang lahat sa backend LinkedList
            linkedList.clear();

            // Buburahin ang lahat sa GUI listahan
            listModel.clear();
        });
    }

    public static void main(String[] args) {

        // Dito magsisimula ang Java Swing application
        SwingUtilities.invokeLater(() -> {
            Main window = new Main();

            // Ipapakita ang application window
            window.setVisible(true);

            JOptionPane.showMessageDialog(
                window,
                "Special Offer: Complete your tasks today!",
                "Special Offer",
                JOptionPane.INFORMATION_MESSAGE
            );
        });
    }
}
