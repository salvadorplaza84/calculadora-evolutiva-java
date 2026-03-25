package calculadora;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class CalculadoraGUI {
	CalculadoraLogica logica;
	JFrame ventana;
	JButton uno;
	JButton dos;
	JButton tres;
	JButton cuatro;
	JButton cinco;
	JButton seis;
	JButton siete;
	JButton ocho;
	JButton nueve;
	JButton cero;
	JButton suma;
	JButton resta;
	JButton multiplica;
	JButton divide;
	JButton punto;
	JButton igual;
	JButton borrar;
	JTextField pantalla;
	JPanel botones;

	public CalculadoraGUI() {
		ventana = new JFrame("Calculadora básica");
		ventana.setSize(400, 500);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout());

		logica = new CalculadoraLogica();
		pantalla = new JTextField();
		pantalla.setEditable(false);

		botones = new JPanel(new GridLayout(4, 4));
		uno = new JButton("1");
		dos = new JButton("2");
		tres = new JButton("3");
		cuatro = new JButton("4");
		cinco = new JButton("5");
		seis = new JButton("6");
		siete = new JButton("7");
		ocho = new JButton("8");
		nueve = new JButton("9");
		cero = new JButton("0");
		suma = new JButton("+");
		resta = new JButton("-");
		multiplica = new JButton("*");
		divide = new JButton("/");
		igual = new JButton("=");
		punto = new JButton(".");
		borrar = new JButton("C");

		ventana.add(pantalla, BorderLayout.NORTH);
		ventana.add(botones, BorderLayout.CENTER);
		ventana.add(igual, BorderLayout.SOUTH);
		botones.add(siete);
		botones.add(ocho);
		botones.add(nueve);
		botones.add(divide);
		botones.add(cuatro);
		botones.add(cinco);
		botones.add(seis);
		botones.add(multiplica);
		botones.add(uno);
		botones.add(dos);
		botones.add(tres);
		botones.add(resta);
		botones.add(cero);
		botones.add(punto);
		botones.add(borrar);
		botones.add(suma);

		uno.addActionListener(e -> pantalla.setText(pantalla.getText() + "1"));
		dos.addActionListener(e -> pantalla.setText(pantalla.getText() + "2"));
		tres.addActionListener(e -> pantalla.setText(pantalla.getText() + "3"));
		cuatro.addActionListener(e -> pantalla.setText(pantalla.getText() + "4"));
		cinco.addActionListener(e -> pantalla.setText(pantalla.getText() + "5"));
		seis.addActionListener(e -> pantalla.setText(pantalla.getText() + "6"));
		siete.addActionListener(e -> pantalla.setText(pantalla.getText() + "7"));
		ocho.addActionListener(e -> pantalla.setText(pantalla.getText() + "8"));
		nueve.addActionListener(e -> pantalla.setText(pantalla.getText() + "9"));
		cero.addActionListener(e -> pantalla.setText(pantalla.getText() + "0"));
		borrar.addActionListener(e -> pantalla.setText(""));
		punto.addActionListener(e -> {
			String texto = pantalla.getText();

			int ultimaSuma = texto.lastIndexOf("+");
			int ultimaResta = texto.lastIndexOf("-");
			int ultimaMulti = texto.lastIndexOf("*");
			int ultimaDivision = texto.lastIndexOf("/");

			int ultimoOperador = Math.max(Math.max(ultimaSuma, ultimaResta), Math.max(ultimaMulti, ultimaDivision));

			String numeroActual = texto.substring(ultimoOperador + 1);

			if (texto.isEmpty()) {
				pantalla.setText("0.");
			} else if (!numeroActual.contains(".") && !texto.endsWith("+") && !texto.endsWith("-")
					&& !texto.endsWith("*") && !texto.endsWith("/") && !texto.endsWith(".")) {
				pantalla.setText(texto + ".");
			}
		});
		suma.addActionListener(e -> {
			String texto = pantalla.getText();

			if (!texto.isEmpty() && !texto.contains("+") && !texto.contains("-") && !texto.contains("*")
					&& !texto.contains("/") && !texto.endsWith(".")) {
				pantalla.setText(texto + "+");
			}
		});
		resta.addActionListener(e -> {
			String texto = pantalla.getText();

			if (!texto.isEmpty() && !texto.contains("+") && !texto.contains("-") && !texto.contains("*")
					&& !texto.contains("/") && !texto.endsWith(".")) {
				pantalla.setText(texto + "-");
			}
		});
		multiplica.addActionListener(e -> {
			String texto = pantalla.getText();

			if (!texto.isEmpty() && !texto.contains("+") && !texto.contains("-") && !texto.contains("*")
					&& !texto.contains("/") && !texto.endsWith(".")) {
				pantalla.setText(texto + "*");
			}
		});
		divide.addActionListener(e -> {
			String texto = pantalla.getText();

			if (!texto.isEmpty() && !texto.contains("+") && !texto.contains("-") && !texto.contains("*")
					&& !texto.contains("/") && !texto.endsWith(".")) {
				pantalla.setText(texto + "/");
			}
		});

		igual.addActionListener(e -> {
			String texto = pantalla.getText();
			String operador = "";

			if (texto.contains("+")) {
				operador = "+";
			} else if (texto.contains("-")) {
				operador = "-";
			} else if (texto.contains("*")) {
				operador = "*";
			} else if (texto.contains("/")) {
				operador = "/";
			}

			if (texto.isEmpty() || operador.equals("")) {
				JOptionPane.showMessageDialog(ventana, "Introduce una operación válida");
				return;
			}

			int posicionOperador = texto.indexOf(operador);

			String primerNumeroTexto = texto.substring(0, posicionOperador);
			String segundoNumeroTexto = texto.substring(posicionOperador + 1);

			if (primerNumeroTexto.isEmpty() || segundoNumeroTexto.isEmpty()) {
				JOptionPane.showMessageDialog(ventana, "Falta algún numero");
				return;
			}
			try {
				double primerNumero = Double.parseDouble(primerNumeroTexto);
				double segundoNumero = Double.parseDouble(segundoNumeroTexto);
				double resultado = 0;

				if (operador.equals("+")) {
					resultado = logica.sumar(primerNumero, segundoNumero);
				} else if (operador.equals("-")) {
					resultado = logica.restar(primerNumero, segundoNumero);
				} else if (operador.equals("*")) {
					resultado = logica.multiplicar(primerNumero, segundoNumero);
				} else if (operador.equals("/")) {
					resultado = logica.dividir(primerNumero, segundoNumero);
				}

				pantalla.setText(String.valueOf(resultado));

			} catch (ArithmeticException e2) {
				JOptionPane.showMessageDialog(ventana, e2.getMessage());
				pantalla.setText("");
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(ventana, "Número no válido");
				pantalla.setText("");
			}

		});

		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

}
