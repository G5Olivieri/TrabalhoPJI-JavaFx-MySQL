package bar.model.bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySql {
	final private static String USERNAME = "root";
	final private static String PASSWORD = "";
	final private static String URL_BANCO = "jdbc:mysql://localhost:3306/bar_report";
	final private static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	
	public static Connection conexao() {
		try{
			Class.forName(DRIVER_NAME);
			return DriverManager.getConnection(URL_BANCO, USERNAME, PASSWORD);
		} catch(ClassNotFoundException e) {
			System.out.println("Driver não encontrado!");
			return null;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	public static boolean fecharConexao(Connection conexao) {
		try{
			conexao.close();
			return true;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static boolean reniciarConexao(Connection conexao) {
		if(!ConexaoMySql.fecharConexao(conexao))
			return false;
		
		conexao = conexao();
		if(conexao != null)
			return true;
		
		else return false;
	}
	
}