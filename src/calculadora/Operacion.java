package calculadora;

public class Operacion {
	private String expresion;
	private String resultado;

	public Operacion(String expresion, String resultado) {
		this.expresion = expresion;
		this.resultado = resultado;
	}

	public String getExpresion() {
		return expresion;
	}

	public String getResultado() {
		return resultado;
	}

	public static Operacion desdeLinea(String linea) {
		int separador = linea.lastIndexOf(" = ");

		if (separador == -1) {
			return null;
		}

		String expresion = linea.substring(0, separador);
		String resultado = linea.substring(separador + 3);
		return new Operacion(expresion, resultado);
	}

	@Override
	public String toString() {
		return expresion + " = " + resultado;
	}
}
