package calculadora;

import java.util.ArrayList;

public class CalculadoraLogica {
	private ArrayList<Operacion> historial;

	public CalculadoraLogica() {
		historial = new ArrayList<>();
	}

	public double sumar(double num1, double num2) {
		return num1 + num2;
	}

	public double restar(double num1, double num2) {
		return num1 - num2;
	}

	public double multiplicar(double num1, double num2) {
		return num1 * num2;
	}

	public double dividir(double num1, double num2) {
		if (num2 != 0) {
			return num1 / num2;
		} else {
			throw new ArithmeticException("No se puede dividir entre 0");
		}
	}

	public boolean esOperador(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	private double aplicarOperacion(double acumulado, double numero, char operador) {
		if (operador == '+') {
			return sumar(acumulado, numero);
		} else if (operador == '-') {
			return restar(acumulado, numero);
		} else if (operador == '*') {
			return multiplicar(acumulado, numero);
		} else if (operador == '/') {
			return dividir(acumulado, numero);
		}

		return numero;
	}

	public double evaluarExpresion(String texto) {
		String numeroActual = "";
		double resultado = 0;
		char operadorActual = '+';

		for (int i = 0; i < texto.length(); i++) {
			char c = texto.charAt(i);

			if (esOperador(c)) {
				if (numeroActual.isEmpty()) {
					throw new NumberFormatException();
				}

				double numero = Double.parseDouble(numeroActual);
				resultado = aplicarOperacion(resultado, numero, operadorActual);

				operadorActual = c;
				numeroActual = "";
			} else {
				numeroActual += c;
			}
		}

		if (numeroActual.isEmpty()) {
			throw new NumberFormatException();
		}

		double ultimoNumero = Double.parseDouble(numeroActual);
		resultado = aplicarOperacion(resultado, ultimoNumero, operadorActual);

		return resultado;
	}

	public void guardarOperacion(String expresion, double resultado) {
		historial.add(new Operacion(expresion, formatearNumero(resultado)));
	}

	public ArrayList<Operacion> obtenerHistorial() {
		return new ArrayList<>(historial);
	}

	public String formatearNumero(double numero) {
		if (numero == (long) numero) {
			return String.valueOf((long) numero);
		}

		return String.valueOf(numero);
	}
}