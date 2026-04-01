package calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HistorialVentana {

	public HistorialVentana(ArrayList<Operacion> historial) {
		JFrame ventanaHistorial = new JFrame("Historial");
		ventanaHistorial.setSize(350, 400);
		ventanaHistorial.setLocationRelativeTo(null);
		ventanaHistorial.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventanaHistorial.getContentPane().setBackground(new Color(230, 230, 230));
		ventanaHistorial.setLayout(new BorderLayout(10, 10));

		JTextArea areaHistorial = new JTextArea();
		areaHistorial.setEditable(false);
		areaHistorial.setFont(new Font("Arial", Font.PLAIN, 18));
		areaHistorial.setBackground(Color.WHITE);
		areaHistorial.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		if (historial.isEmpty()) {
			areaHistorial.setText("No hay operaciones en el historial.");
		} else {
			StringBuilder texto = new StringBuilder();

			for (int i = 0; i < historial.size(); i++) {
				texto.append(i + 1)
				     .append(". ")
				     .append(historial.get(i).toString())
				     .append("\n");
			}

			areaHistorial.setText(texto.toString());
		}

		JScrollPane scroll = new JScrollPane(areaHistorial);
		ventanaHistorial.add(scroll, BorderLayout.CENTER);
		ventanaHistorial.setVisible(true);
	}
}
