package bar.model.bancodedados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import bar.model.Cliente;
import bar.model.Login;
import bar.model.bancodedados.interfaces.BasicDAO;

public class ClienteDAO implements BasicDAO<Cliente> {
	private Connection conexao;
	private PreparedStatement pstmt;
	
	public boolean isCliente(Cliente dado) {
		try{
			final String sql = "SELECT cliente.id, cliente.nome, cliente.telefone, cliente.email, " +
								"login.id, login.username,login.senha " + 
								"FROM login " + 
								"INNER JOIN cliente ON login.id=cliente.idLogin and login.id=?";
			if(!getConexao(sql))
				return false;
			pstmt.setInt(1, dado.getLogin().getId());
			ResultSet result = pstmt.executeQuery();
			dado = extraiClienteLogin(result);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean salvar(Cliente dado) {
		if(isCliente(dado)){
			return false;
		}
		try{
			final String sql = "INSERT INTO cliente(nome, telefone, email, idLogin) VALUES(?,?,?,?)";
			if(!getConexao(sql))
				return false;
			pstmt.setString(1, dado.getNome());
			pstmt.setString(2, dado.getTelefone());
			pstmt.setString(3, dado.getEmail());
			pstmt.setInt(4, dado.getLogin().getId());
			pstmt.executeUpdate();
			fecharConexao();
			return true;	
		}catch(SQLException e) {
			System.out.println("Salvar: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean atualizar(Cliente dado) {
		try {
			final String sql =  "UPDATE cliente " + 
								"SET "  +
								"nome=?, "		+
								"telefone=?, "      +
								"email=? " +
								"WHERE id=?";
			if(!getConexao(sql))
				return false;
			pstmt.setString(1, dado.getNome());
			pstmt.setString(2, dado.getTelefone());
			pstmt.setString(3, dado.getTelefone());
			pstmt.setInt(4, dado.getId());
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
			final String sql = "DELETE FROM cliente WHERE id=?";
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
	public Cliente buscarId(int id) {
		try{	
			final String sql = "SELECT cliente.id, cliente.nome, cliente.telefone, cliente.email, " +
					"login.id, login.username,login.senha " + 
					"FROM login " + 
					"INNER JOIN cliente ON login.id=cliente.idLogin and cliente.id=?";
			if(!getConexao(sql))
				return null;
			pstmt.setInt(1, id);
			ResultSet result = pstmt.executeQuery();
			result.next();
			Cliente cliente = extraiClienteLogin(result);
			return cliente;
		}catch(Exception e){
			System.out.println("Buscar por id: " + e.getMessage());
			return null;
		}
	}
	
	@Override
	public ArrayList<Cliente> buscarTudo() {
		ArrayList<Cliente> clientes = new ArrayList<>();
		try{	
			final String sql = "SELECT cliente.id, cliente.nome, cliente.telefone, cliente.email, " +
								"login.id, login.username,login.senha " + 
								"FROM login " + 
								"INNER JOIN cliente ON login.id=cliente.idLogin";
			if(!getConexao(sql))
				return null;
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				Cliente cliente = extraiClienteLogin(result);
				clientes.add(cliente);
			}
			return clientes;
		}catch(Exception e){
			System.out.println("Buscar todos: " + e.getMessage());
			return null;
		}
	}
	
	public Cliente buscarCliente(int idLogin) {
		try{	
			final String sql =  "SELECT cliente.id, cliente.nome, cliente.telefone, cliente.email, " +
								"login.id, login.username,login.senha " + 
								"FROM login " + 
								"INNER JOIN cliente ON login.id=cliente.idLogin and login.id=?";
			if(!getConexao(sql))
				return null;
			pstmt.setInt(1, idLogin);
			ResultSet result = pstmt.executeQuery();
			result.next();
			Cliente cliente  = extraiClienteLogin(result);
			return cliente;
		}catch(Exception e){
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
	
	public Cliente extraiCliente(ResultSet result) throws SQLException, ParseException {
		Cliente cliente = new Cliente();
		cliente.setId(result.getInt(1));
		cliente.setNome(result.getString(2));
		cliente.setTelefone(result.getString(3));
		cliente.setEmail(result.getString(4));
		return cliente;
		
	}
	
	public Cliente extraiClienteLogin(ResultSet result) throws SQLException, ParseException {
		Cliente cliente = new Cliente();
		cliente.setId(result.getInt(1));
		cliente.setNome(result.getString(2));
		cliente.setTelefone(result.getString(3));
		cliente.setEmail(result.getString(4));
		cliente.setLogin(new Login(result.getInt(5), result.getString(6), result.getString(7)));
		return cliente;
		
	}
}
