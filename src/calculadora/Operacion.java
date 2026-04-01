package calculadora;

public class Operacion {
	private int id;
	private String expresion;
	private String resultado;
	private String fecha;

	public Operacion(String expresion, String resultado) {
		this.expresion = expresion;
		this.resultado = resultado;
	}

	public Operacion(int id, String expresion, String resultado, String fecha) {
		this.id = id;
		this.expresion = expresion;
		this.resultado = resultado;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public String getExpresion() {
		return expresion;
	}

	public String getResultado() {
		return resultado;
	}

	public String getFecha() {
		return fecha;
	}

	@Override
	public String toString() {
		return expresion + " = " + resultado;
	}
}