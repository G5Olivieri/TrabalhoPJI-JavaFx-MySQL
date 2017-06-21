package bar.model.bancodedados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import bar.model.Bar;
import bar.model.Login;
import bar.model.bancodedados.interfaces.BasicDAO;

public class BarDAO implements BasicDAO<Bar> {
	private Connection conexao;
	private PreparedStatement pstmt;
	
	public boolean isBar(Bar dado) {
		try{
			final String sql = "SELECT bar.id, bar.nome, bar.nome_pro, bar.endereco, bar.telefone, " +
								"login.id, login.username,login.senha " + 
								"FROM login " + 
								"INNER JOIN bar ON login.id=bar.idLogin and login.id=?";
			if(!getConexao(sql))
				return false;
			pstmt.setInt(1, dado.getLogin().getId());
			ResultSet result = pstmt.executeQuery();
			dado = extraiBarLogin(result);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean salvar(Bar dado) {
		if(isBar(dado)){
			return false;
		}
		try{
			final String sql = "INSERT INTO bar(nome, nome_pro, endereco, telefone, idLogin) VALUES(?,?,?,?,?)";
			if(!getConexao(sql))
				return false;
			pstmt.setString(1, dado.getNome());
			pstmt.setString(2, dado.getNomePro());
			pstmt.setString(3, dado.getEndereco());
			pstmt.setString(4, dado.getTelefone());
			pstmt.setInt(5, dado.getLogin().getId());
			pstmt.executeUpdate();
			fecharConexao();
			return true;	
		}catch(SQLException e) {
			System.out.println("Salvar: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean atualizar(Bar dado) {
		try {
			final String sql =  "UPDATE bar " + 
								"SET "            +
								"nome=?, "		  +
								"nome_pro=?, "    +
								"endereco=?"  	  +
								"telefone=? "     +
								"WHERE id=?";
			if(!getConexao(sql))
				return false;
			pstmt.setString(1, dado.getNome());
			pstmt.setString(2, dado.getNomePro());
			pstmt.setString(3, dado.getEndereco());
			pstmt.setString(4, dado.getTelefone());
			pstmt.setInt(5, dado.getId());
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
			final String sql = "DELETE FROM bar WHERE id=?";
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
	public Bar buscarId(int id) {
		try{	
			final String sql =  "SELECT bar.id, bar.nome, bar.nome_pro, bar.endereco, bar.telefone," +
								"login.id, login.username,login.senha " +
								"FROM login " +
								"INNER JOIN bar ON login.id=bar.idLogin and bar.id=?";
			if(!getConexao(sql))
				return null;
			pstmt.setInt(1, id);
			ResultSet result = pstmt.executeQuery();
			result.next();
			Bar bar = extraiBarLogin(result);
			return bar;
		}catch(Exception e){
			System.out.println("Buscar por id: " + e.getMessage());
			return null;
		}
	}
	
	@Override
	public ArrayList<Bar> buscarTudo() {
		ArrayList<Bar> bares = new ArrayList<>();
		try{	
			final String sql = "SELECT bar.id, bar.nome, bar.nome_pro, bar.endereco, bar.telefone," +
								"login.id, login.username,login.senha " +
								"FROM login " +
								"INNER JOIN bar ON login.id=bar.idLogin";
			if(!getConexao(sql))
				return null;
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				Bar bar = extraiBarLogin(result);
				bares.add(bar);
			}
			return bares;
		}catch(Exception e){
			System.out.println("Buscar todos: " + e.getMessage());
			return null;
		}
	}
	
	public Bar buscarBar(int idLogin) {
		try{	
			final String sql = "SELECT bar.id, bar.nome, bar.nome_pro, bar.endereco, bar.telefone," +
								"login.id, login.username,login.senha " +
								"FROM login " +
								"INNER JOIN bar ON login.id=bar.idLogin and login.id=?";
			if(!getConexao(sql))
				return null;
			pstmt.setInt(1, idLogin);
			ResultSet result = pstmt.executeQuery();
			result.next();
			Bar bar  = extraiBarLogin(result);
			return bar;
		}catch(Exception e){
			System.out.println("Aqui mesmo: " + e.getMessage());
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
	
	public Bar extraiBar(ResultSet result) throws SQLException, ParseException {
		Bar bar = new Bar();
		bar.setId(result.getInt(1));
		bar.setNome(result.getString(2));
		bar.setNomePro(result.getString(3));
		bar.setEndereco(result.getString(4));
		bar.setTelefone(result.getString(5));
		return bar;
		
	}
	
	public Bar extraiBarLogin(ResultSet result) throws SQLException, ParseException {
		Bar bar = new Bar();
		bar.setId(result.getInt(1));
		bar.setNome(result.getString(2));
		bar.setNomePro(result.getString(3));
		bar.setEndereco(result.getString(4));
		bar.setTelefone(result.getString(5));
		bar.setLogin(new Login(result.getInt(6), result.getString(7), result.getString(8)));
		return bar;
		
	}

}
