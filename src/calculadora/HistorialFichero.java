package calculadora;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HistorialFichero {
	private static final String NOMBRE_ARCHIVO = "historial_calculadora.txt";
	private File archivo;

	public HistorialFichero() {
		archivo = new File(NOMBRE_ARCHIVO);
		crearArchivoSiNoExiste();
	}

	private void crearArchivoSiNoExiste() {
		try {
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
		} catch (IOException e) {
			System.out.println("No se pudo crear el archivo del historial.");
		}
	}

	public void guardarOperacion(Operacion operacion) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
			bw.write(operacion.toString());
			bw.newLine();
		} catch (IOException e) {
			System.out.println("Error al guardar el historial en el fichero.");
		}
	}

	public ArrayList<Operacion> cargarHistorial() {
		ArrayList<Operacion> historial = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				if (!linea.trim().isEmpty()) {
					Operacion operacion = Operacion.desdeLinea(linea);

					if (operacion != null) {
						historial.add(operacion);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error al leer el historial del fichero.");
		}

		return historial;
	}
}
