package calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HistorialVentana {

	public HistorialVentana(ArrayList<Operacion> historial) {
		JFrame ventanaHistorial = new JFrame("Historial");
		ventanaHistorial.setSize(700, 400);
		ventanaHistorial.setLocationRelativeTo(null);
		ventanaHistorial.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventanaHistorial.getContentPane().setBackground(new Color(230, 230, 230));
		ventanaHistorial.setLayout(new BorderLayout(10, 10));

		String[] columnas = { "ID", "Operación", "Resultado", "Fecha" };

		DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		for (Operacion operacion : historial) {
			Object[] fila = {
				operacion.getId(),
				operacion.getExpresion(),
				operacion.getResultado(),
				operacion.getFecha()
			};
			modelo.addRow(fila);
		}

		JTable tabla = new JTable(modelo);
		tabla.setFont(new Font("Arial", Font.PLAIN, 16));
		tabla.setRowHeight(24);
		tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

		JScrollPane scroll = new JScrollPane(tabla);
		ventanaHistorial.add(scroll, BorderLayout.CENTER);
		ventanaHistorial.setVisible(true);
	}
}