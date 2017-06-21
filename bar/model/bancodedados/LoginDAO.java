package bar.model.bancodedados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import bar.model.Login;
import bar.model.bancodedados.interfaces.BasicDAO;

public class LoginDAO implements BasicDAO<Login> {
	private Connection conexao;
	private PreparedStatement pstmt;
	
	@Override
	public boolean salvar(Login dado) {
		if(isLogin(dado)){
			return false;
		}
		try{
			final String sql = "INSERT INTO login(username, senha) VALUES(?,?)";
			if(!getConexao(sql))
				return false;
			pstmt.setString(1, dado.getUsername());
			pstmt.setString(2, dado.getSenha());
			pstmt.executeUpdate();
			fecharConexao();
			return true;	
		}catch(SQLException e) {
			System.out.println("Salvar: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean atualizar(Login dado) {
		try {
			final String sql =  "UPDATE login " + 
								"SET "  +
								"username=?, "		+
								"senha=? "      +
								"WHERE id=?";
			if(!getConexao(sql))
				return false;
			pstmt.setString(1, dado.getUsername());
			pstmt.setString(2, dado.getSenha());
			pstmt.setInt(3, dado.getId());
			pstmt.executeQuery();
			fecharConexao();
			return true;
		}catch(SQLException e) {
			System.out.println("Atualizar: " + e.getMessage());
			return false;
		}
		
	}

	@Override
	public boolean deletar(int id) {
		try{
			final String sql = "DELETE FROM login WHERE id=?";
			if(getConexao(sql))
				return false;
			pstmt.setInt(1, id);
			pstmt.executeQuery();
			fecharConexao();
			return true;
		}catch(SQLException e){
			System.out.println("Deletar: " + e.getMessage());
			return false;
		}
	}

	@Override
	public Login buscarId(int id) {
		try{	
			final String sql = "SELECT id, username, senha FROM login WHERE id=?";
			if(!getConexao(sql))
				return null;
			pstmt.setInt(1, id);
			ResultSet result = pstmt.executeQuery();
			result.next();
			Login login = extraiLogin(result);
			return login;
		}catch(Exception e){
			System.out.println("Buscar por id: " + e.getMessage());
			return null;
		}
	}

	public Login buscarLogin(String username, String senha) {
		try{	
			final String sql = "SELECT id, username, senha FROM login WHERE username=? and senha=?";
			if(!getConexao(sql))
				return null;
			pstmt.setString(1, username);
			pstmt.setString(2, senha);
			ResultSet result = pstmt.executeQuery();
			result.next();
			Login login  = extraiLogin(result);
			return login;
		}catch(Exception e){
			return null;
		}
	}
	
	public boolean isLogin(Login login) {
		try{	
			final String sql = "SELECT id, username, senha FROM login WHERE username=? and senha=?";
			if(!getConexao(sql))
				return false;
			pstmt.setString(1, login.getUsername());
			pstmt.setString(2, login.getSenha());
			ResultSet result = pstmt.executeQuery();
			result.next();
			login  = extraiLogin(result);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public ArrayList<Login> buscarTudo() {
		ArrayList<Login> logins = new ArrayList<>();
		try{	
			final String sql = "SELECT id, username, senha FROM login";
			if(!getConexao(sql))
				return null;
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				Login login = extraiLogin(result);
				logins.add(login);
			}
			return logins;
		}catch(Exception e){
			System.out.println("Buscar todos: " + e.getMessage());
			return null;
		}
	}
	
	public boolean fecharConexao() {
		try{
			conexao.close();
			pstmt.close();
			return true;
		}catch(SQLException e) {
			System.out.println("Fechar a conexao: " + e.getMessage());
			return false;
		}
	}
	
	public boolean getConexao(String sql){
		try{
			conexao = ConexaoMySql.conexao();
			pstmt = conexao.prepareStatement(sql);
			return true;
		}catch(SQLException e){
			System.out.println("Pegar conexao: " + e.getMessage());
			return false;
		}
	}
	
	public Login extraiLogin(ResultSet result) throws SQLException, ParseException {
		Login login = new Login();
		login.setId(result.getInt(1));
		login.setUsername(result.getString(2));
		login.setSenha(result.getString(3));
		return login;
		
	}
}
