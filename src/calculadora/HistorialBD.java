package calculadora;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HistorialBD {
	private static final String URL = "jdbc:mysql://localhost:3306/calculadora";
	private static final String USUARIO = "root";
	private static final String PASSWORD = "123_dba_321";

	public HistorialBD() {
		crearTablaSiNoExiste();
	}

	private Connection obtenerConexion() throws SQLException {
		return DriverManager.getConnection(URL, USUARIO, PASSWORD);
	}

	private void crearTablaSiNoExiste() {
		String sql = "CREATE TABLE IF NOT EXISTS historial ("
				+ "id INT AUTO_INCREMENT PRIMARY KEY, "
				+ "operacion VARCHAR(100) NOT NULL, "
				+ "resultado VARCHAR(50) NOT NULL, "
				+ "fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

		try (Connection conexion = obtenerConexion();
			 Statement sentencia = conexion.createStatement()) {

			sentencia.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("No se pudo crear o comprobar la tabla historial.");
		}
	}

	public void guardarOperacion(Operacion operacion) {
		String sql = "INSERT INTO historial (operacion, resultado) VALUES (?, ?)";

		try (Connection conexion = obtenerConexion();
			 PreparedStatement sentencia = conexion.prepareStatement(sql)) {

			sentencia.setString(1, operacion.getExpresion());
			sentencia.setString(2, operacion.getResultado());
			sentencia.executeUpdate();

		} catch (SQLException e) {
			System.out.println("No se pudo guardar la operación en la base de datos.");
		}
	}

	public ArrayList<Operacion> obtenerHistorial() {
		ArrayList<Operacion> historial = new ArrayList<>();
		String sql = "SELECT id, operacion, resultado, fecha FROM historial ORDER BY id DESC";

		try (Connection conexion = obtenerConexion();
			 PreparedStatement sentencia = conexion.prepareStatement(sql);
			 ResultSet resultado = sentencia.executeQuery()) {

			while (resultado.next()) {
				int id = resultado.getInt("id");
				String expresion = resultado.getString("operacion");
				String res = resultado.getString("resultado");
				String fecha = resultado.getString("fecha");

				historial.add(new Operacion(id, expresion, res, fecha));
			}

		} catch (SQLException e) {
			System.out.println("No se pudo consultar el historial de la base de datos.");
		}

		return historial;
	}
}