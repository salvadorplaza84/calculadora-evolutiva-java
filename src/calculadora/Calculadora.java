package calculadora;

import javax.swing.SwingUtilities;

public class Calculadora {
	// Fase 1 completa
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new CalculadoraGUI());

	}

}
