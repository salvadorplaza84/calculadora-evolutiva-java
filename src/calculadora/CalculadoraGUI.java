package calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculadoraGUI {
	private CalculadoraLogica logica;
	private JFrame ventana;
	private JButton uno;
	private JButton dos;
	private JButton tres;
	private JButton cuatro;
	private JButton cinco;
	private JButton seis;
	private JButton siete;
	private JButton ocho;
	private JButton nueve;
	private JButton cero;
	private JButton suma;
	private JButton resta;
	private JButton multiplica;
	private JButton divide;
	private JButton punto;
	private JButton igual;
	private JButton borrar;
	private JButton historial;
	private boolean resultadoMostrado;
	private JTextField pantalla;
	private JPanel botones;
	private JPanel panelInferior;

	public CalculadoraGUI() {
		resultadoMostrado = false;
		logica = new CalculadoraLogica();

		crearVentana();
		crearPantalla();
		crearBotones();
		colocarComponentes();
		asignarEventos();

		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	private void crearVentana() {
		ventana = new JFrame("Calculadora básica");
		ventana.setSize(400, 550);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout(10, 10));
		ventana.setResizable(false);
		ventana.getContentPane().setBackground(new Color(230, 230, 230));
	}

	private void crearPantalla() {
		pantalla = new JTextField();
		pantalla.setEditable(false);
		pantalla.setFont(new Font("Arial", Font.BOLD, 28));
		pantalla.setBackground(Color.WHITE);
		pantalla.setForeground(Color.BLACK);
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		pantalla.setPreferredSize(new Dimension(400, 85));
		pantalla.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createLineBorder(new Color(200, 200, 200), 1)));
	}

	private void crearBotones() {
		botones = new JPanel(new GridLayout(4, 4, 8, 8));
		botones.setBackground(new Color(230, 230, 230));
		botones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panelInferior = new JPanel(new GridLayout(1, 2, 8, 8));
		panelInferior.setBackground(new Color(230, 230, 230));
		panelInferior.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

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
		historial = new JButton("Historial");

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
		estilizarBotonHistorial(historial);
	}

	private void colocarComponentes() {
		ventana.add(pantalla, BorderLayout.NORTH);
		ventana.add(botones, BorderLayout.CENTER);
		ventana.add(panelInferior, BorderLayout.SOUTH);

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

		panelInferior.add(historial);
		panelInferior.add(igual);
	}

	private void asignarEventos() {
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

		borrar.addActionListener(e -> borrarUltimoCaracter());
		punto.addActionListener(e -> agregarPunto());
		suma.addActionListener(e -> agregarOperador("+"));
		resta.addActionListener(e -> agregarOperador("-"));
		multiplica.addActionListener(e -> agregarOperador("*"));
		divide.addActionListener(e -> agregarOperador("/"));
		igual.addActionListener(e -> calcularResultado());
		historial.addActionListener(e -> new HistorialVentana(logica.obtenerHistorialDesdeFichero()));
	}

	private void borrarUltimoCaracter() {
		if (resultadoMostrado) {
			return;
		}

		String texto = pantalla.getText();

		if (!texto.isEmpty()) {
			pantalla.setText(texto.substring(0, texto.length() - 1));
		}
	}

	private void agregarPunto() {
		if (resultadoMostrado) {
			pantalla.setText("0.");
			resultadoMostrado = false;
			return;
		}

		String texto = pantalla.getText();
		int ultimoOperador = obtenerUltimoOperador(texto);
		String numeroActual = texto.substring(ultimoOperador + 1);

		if (texto.isEmpty()) {
			pantalla.setText("0.");
		} else if (!numeroActual.contains(".") && !terminaEnOperador(texto) && !texto.endsWith(".")) {
			pantalla.setText(texto + ".");
		}
	}

	private int obtenerUltimoOperador(String texto) {
		int ultimaSuma = texto.lastIndexOf("+");
		int ultimaResta = texto.lastIndexOf("-");
		int ultimaMulti = texto.lastIndexOf("*");
		int ultimaDivision = texto.lastIndexOf("/");

		return Math.max(Math.max(ultimaSuma, ultimaResta), Math.max(ultimaMulti, ultimaDivision));
	}

	private boolean terminaEnOperador(String texto) {
		return texto.endsWith("+") || texto.endsWith("-") || texto.endsWith("*") || texto.endsWith("/");
	}

	private void calcularResultado() {
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
			String resultadoFormateado = logica.formatearNumero(resultado);

			logica.guardarOperacion(texto, resultado);

			pantalla.setText(resultadoFormateado);
			resultadoMostrado = true;

		} catch (ArithmeticException e) {
			JOptionPane.showMessageDialog(ventana, e.getMessage());
			pantalla.setText("");
			resultadoMostrado = false;

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(ventana, "Número no válido");
			pantalla.setText("");
			resultadoMostrado = false;
		}
	}

	private void agregarOperador(String operador) {
		String texto = pantalla.getText();

		if (texto.isEmpty()) {
			return;
		}

		char ultimo = texto.charAt(texto.length() - 1);

		if (logica.esOperador(ultimo) || ultimo == '.') {
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

	private void estilizarBotonHistorial(JButton boton) {
		boton.setFont(new Font("Arial", Font.BOLD, 20));
		boton.setBackground(new Color(180, 220, 180));
		boton.setForeground(Color.BLACK);
		boton.setFocusPainted(false);
		boton.setOpaque(true);
		boton.setBorder(BorderFactory.createLineBorder(new Color(140, 190, 140), 1));
	}
}