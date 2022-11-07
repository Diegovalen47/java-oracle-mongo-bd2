package UIMain;
import javax.swing.*;
import java.awt.event.*;

public class Interfaz {
    private JButton consultarDatosButton;
    private JComboBox comboBox1;
    private JButton calcularEstadisticasButton;
    private JButton consultarEstadisticasButton;
    private JTextArea textArea1;
    private javax.swing.JPanel JPanel;
    private JComboBox comboBox2;


    public Interfaz() {
        consultarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                String seleccion=comboBox1.getSelectedItem().toString();
                for (int i = 0; i < 100 ; i++) {
                    textArea1.append(seleccion+"\n");
                }
            }
        });
        calcularEstadisticasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
            }
        });
        consultarEstadisticasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
            }
        });
        comboBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                textArea1.setText("");
            }
        });
        comboBox2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                textArea1.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("interfaz");
        frame.setContentPane(new Interfaz().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
