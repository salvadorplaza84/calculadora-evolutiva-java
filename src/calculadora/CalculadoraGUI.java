package calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	boolean resultadoMostrado;
	JTextField pantalla;
	JPanel botones;

	public CalculadoraGUI() {
		resultadoMostrado = false;

		ventana = new JFrame("Calculadora básica");
		ventana.setSize(400, 550);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout(10, 10));
		ventana.setResizable(false);
		ventana.getContentPane().setBackground(new Color(230, 230, 230));

		logica = new CalculadoraLogica();

		pantalla = new JTextField();
		pantalla.setEditable(false);
		pantalla.setFont(new Font("Arial", Font.BOLD, 28));
		pantalla.setBackground(Color.WHITE);
		pantalla.setForeground(Color.BLACK);
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		pantalla.setPreferredSize(new Dimension(400, 85));
		pantalla.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createLineBorder(new Color(200, 200, 200), 1)));

		botones = new JPanel(new GridLayout(4, 4, 8, 8));
		botones.setBackground(new Color(230, 230, 230));
		botones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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
		borrar = new JButton("←");

		estilizarBotonNumero(uno);
		estilizarBotonNumero(dos);
		estilizarBotonNumero(tres);
		estilizarBotonNumero(cuatro);
		estilizarBotonNumero(cinco);
		estilizarBotonNumero(seis);
		estilizarBotonNumero(siete);
		estilizarBotonNumero(ocho);
		estilizarBotonNumero(nueve);
		estilizarBotonNumero(cero);
		estilizarBotonNumero(punto);

		estilizarBotonOperador(suma);
		estilizarBotonOperador(resta);
		estilizarBotonOperador(multiplica);
		estilizarBotonOperador(divide);

		estilizarBotonIgual(igual);
		estilizarBotonBorrar(borrar);
		igual.setPreferredSize(new Dimension(400, 75));

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
		botones.add(borrar);
		botones.add(cero);
		botones.add(punto);
		botones.add(suma);

		uno.addActionListener(e -> agregarNumero("1"));
		dos.addActionListener(e -> agregarNumero("2"));
		tres.addActionListener(e -> agregarNumero("3"));
		cuatro.addActionListener(e -> agregarNumero("4"));
		cinco.addActionListener(e -> agregarNumero("5"));
		seis.addActionListener(e -> agregarNumero("6"));
		siete.addActionListener(e -> agregarNumero("7"));
		ocho.addActionListener(e -> agregarNumero("8"));
		nueve.addActionListener(e -> agregarNumero("9"));
		cero.addActionListener(e -> agregarNumero("0"));

		borrar.addActionListener(e -> {
			if (resultadoMostrado) {
				return;
			}

			String texto = pantalla.getText();

			if (!texto.isEmpty()) {
				pantalla.setText(texto.substring(0, texto.length() - 1));
			}
		});

		punto.addActionListener(e -> {
			if (resultadoMostrado) {
				pantalla.setText("0.");
				resultadoMostrado = false;
				return;
			}

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

		suma.addActionListener(e -> agregarOperador("+"));
		resta.addActionListener(e -> agregarOperador("-"));
		multiplica.addActionListener(e -> agregarOperador("*"));
		divide.addActionListener(e -> agregarOperador("/"));

		igual.addActionListener(e -> {
			String texto = pantalla.getText();

			if (texto.isEmpty()) {
				JOptionPane.showMessageDialog(ventana, "Introduce una operación válida");
				return;
			}

			char ultimo = texto.charAt(texto.length() - 1);

			if (logica.esOperador(ultimo) || ultimo == '.') {
				JOptionPane.showMessageDialog(ventana, "La operación está incompleta");
				return;
			}

			try {
				double resultado = logica.evaluarExpresion(texto);
				pantalla.setText(String.valueOf(resultado));
				resultadoMostrado = true;

			} catch (ArithmeticException e2) {
				JOptionPane.showMessageDialog(ventana, e2.getMessage());
				pantalla.setText("");
				resultadoMostrado = false;
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(ventana, "Número no válido");
				pantalla.setText("");
				resultadoMostrado = false;
			}
		});

		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	private void agregarOperador(String operador) {
		String texto = pantalla.getText();

		if (texto.isEmpty()) {
			return;
		}

		char ultimo = texto.charAt(texto.length() - 1);

		if (ultimo == '+' || ultimo == '-' || ultimo == '*' || ultimo == '/' || ultimo == '.') {
			return;
		}

		pantalla.setText(texto + operador);
		resultadoMostrado = false;
	}
	
	private void agregarNumero(String numero) {
		if (resultadoMostrado) {
			pantalla.setText(numero);
			resultadoMostrado = false;
		} else {
			pantalla.setText(pantalla.getText() + numero);
		}
	}

	private void estilizarBotonNumero(JButton boton) {
		boton.setFont(new Font("Arial", Font.BOLD, 22));
		boton.setBackground(new Color(245, 245, 245));
		boton.setForeground(Color.BLACK);
		boton.setFocusPainted(false);
		boton.setOpaque(true);
		boton.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210), 1));
	}

	private void estilizarBotonOperador(JButton boton) {
		boton.setFont(new Font("Arial", Font.BOLD, 22));
		boton.setBackground(new Color(255, 204, 102));
		boton.setForeground(Color.BLACK);
		boton.setFocusPainted(false);
		boton.setOpaque(true);
		boton.setBorder(BorderFactory.createLineBorder(new Color(230, 180, 80), 1));
	}

	private void estilizarBotonIgual(JButton boton) {
		boton.setFont(new Font("Arial", Font.BOLD, 24));
		boton.setBackground(new Color(102, 178, 255));
		boton.setForeground(Color.WHITE);
		boton.setFocusPainted(false);
		boton.setOpaque(true);
		boton.setBorder(BorderFactory.createLineBorder(new Color(80, 150, 220), 1));
	}

	private void estilizarBotonBorrar(JButton boton) {
		boton.setFont(new Font("Arial", Font.BOLD, 22));
		boton.setBackground(new Color(255, 153, 153));
		boton.setForeground(Color.BLACK);
		boton.setFocusPainted(false);
		boton.setOpaque(true);
		boton.setBorder(BorderFactory.createLineBorder(new Color(220, 120, 120), 1));
	}
}