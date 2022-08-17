package agenda.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	// Connection model, parameters
	// Manual connection

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "orp101099";

	public Connection connect() {
		Connection conn = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Conected: " + conn);
			return conn;

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}

	}

	public void insertContato(JavaBeans contato) {
		String create = "insert into contatos (nome, fone, email) values (?,?,?)";
		try {
			Connection conn = connect();
			// preparar statement
			PreparedStatement ps = conn.prepareStatement(create);
			// parametrizando os campos "?" com os seus respectivos valores
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getFone());
			ps.setString(3, contato.getEmail());

			ps.executeUpdate();

			conn.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	public ArrayList<JavaBeans> mostrarContatos() {
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			Connection conn = connect();

			PreparedStatement ps = conn.prepareStatement(read);
			// result set executa a query, executa o comando select * from.
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer idcontato = rs.getInt(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// salvando em uma lista de contatos
				contatos.add(new JavaBeans(idcontato, nome, fone, email));
			}
			conn.close();
			return contatos;

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}

	}

}
