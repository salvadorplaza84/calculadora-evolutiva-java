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

	@Override
	public String toString() {
		return expresion + " = " + resultado;
	}
}