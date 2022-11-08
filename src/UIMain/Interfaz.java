package UIMain;
import UIMain.crud.*;
import UIMain.statistic.ShowGenero;
import UIMain.statistic.ShowMarca;
import UIMain.statistic.ShowVendedorStat;
import com.mongodb.MongoException;
import mongodbcrud.GeneroDAC;
import mongodbcrud.MarcaDAC;
import mongodbcrud.VendedorDAC;
import oraclecrud.DataAcces.GlobalException;
import oraclecrud.DataAcces.NoDataException;

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
                String seleccion = comboBox1.getSelectedItem().toString();

                if (seleccion.equals("--Seleccione--")){

                    textArea1.setText("Seleccione una opcion");

                } else if (seleccion.equals("Sucursal")) {

                    ShowSucursal showSucursal = new ShowSucursal();

                    try {
                        textArea1.setText(showSucursal.printSucursal());
                    } catch (NoDataException | GlobalException noDataException) {
                        noDataException.printStackTrace();
                    }

                } else if (seleccion.equals("Vendedor")) {

                    ShowVendedor showVendedor = new ShowVendedor();
                    try {
                        textArea1.setText(showVendedor.printVendedor());
                    } catch (NoDataException | GlobalException ex) {
                        throw new RuntimeException(ex);
                    }

                } else if (seleccion.equals("Cliente")) {

                    ShowCliente showCliente = new ShowCliente();
                    try {
                        textArea1.setText(showCliente.printCliente());
                    } catch (NoDataException | GlobalException ex) {
                        throw new RuntimeException(ex);
                    }

                } else if (seleccion.equals("Producto")) {

                    ShowProducto showProducto = new ShowProducto();
                    try {
                        textArea1.setText(showProducto.printProducto());
                    } catch (NoDataException | GlobalException ex) {
                        throw new RuntimeException(ex);
                    }

                } else if (seleccion.equals("Venta")) {

                    ShowVenta showVenta = new ShowVenta();
                    try {
                        textArea1.setText(showVenta.printVenta());
                    } catch (NoDataException | GlobalException ex) {
                        throw new RuntimeException(ex);
                    }

                }

            }
        });
        calcularEstadisticasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");

                try{

                    MarcaDAC tmp = new MarcaDAC();
                    tmp.saveStatistics();

                    VendedorDAC tmp2 = new VendedorDAC();
                    tmp2.saveStatistics();

                    GeneroDAC tmp3 = new GeneroDAC();
                    tmp3.saveStatistics();

                    textArea1.setText("Estadisticas calculadas");

                }catch (MongoException err){
                    System.out.println(err);
                }catch (NoDataException err){
                    System.out.println(err);
                }

            }
        });
        consultarEstadisticasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");

                String seleccion = comboBox2.getSelectedItem().toString();

                if(seleccion.equals("--Seleccione--")) {

                    textArea1.setText("Seleccione una opcion");

                } else if (seleccion.equals("Marcas")) {

                    ShowMarca showMarca = new ShowMarca();
                    textArea1.setText(showMarca.printMarca());

                } else if (seleccion.equals("Generos")) {

                    ShowGenero showGenero = new ShowGenero();
                    textArea1.setText(showGenero.printGenero());

                } else if(seleccion.equals("Vendedores")) {

                    ShowVendedorStat showVendedorStat = new ShowVendedorStat();
                    textArea1.setText(showVendedorStat.printVendedor());

                }

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
        JFrame frame = new JFrame("ERP - Gestion de Ventas");
        frame.setBounds(685, 50, 300, 100);
        frame.setContentPane(new Interfaz().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
