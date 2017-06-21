package bar.model.bancodedados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import bar.model.Reporte;
import bar.model.bancodedados.interfaces.BasicDAO;

public class ReporteDAO implements BasicDAO<Reporte> {
	private Connection conexao;
	private PreparedStatement pstmt;
	
	@Override
	public boolean salvar(Reporte dado) {
		try{
			final String sql = "INSERT INTO reporte (reclamacao, data_re, idBar) VALUES(?,?,?)";
			if(!getConexao(sql))
				return false;
			pstmt.setString(1, dado.getReclamacao());
			pstmt.setDate(2, java.sql.Date.valueOf(dado.getDate()));
			pstmt.setInt(3, dado.getIdBar());
			pstmt.executeUpdate();
			fecharConexao();
			return true;	
		}catch(SQLException e) {
			System.out.println("Salvar: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean atualizar(Reporte dado) {
		try {
			final String sql =  "UPDATE reporte " + 
								"SET "  +
								"reclamacao=?, "		+
								"data=?, "      +
								"WHERE id=?";
			if(!getConexao(sql))
				return false;
			pstmt.setString(1, dado.getReclamacao());
			pstmt.setDate(2, java.sql.Date.valueOf(dado.getDate()));
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
			final String sql = "DELETE FROM reporte WHERE id=?";
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
	public Reporte buscarId(int id) {
		try{	
			final String sql =  "SELECT id, reclamacao, data_re, idBar FROM reporte WHERE id=?";
			if(!getConexao(sql))
				return null;
			pstmt.setInt(1, id);
			ResultSet result = pstmt.executeQuery();
			result.next();
			Reporte reporte = extraiReporte(result);
			return reporte;
		}catch(Exception e){
			System.out.println("Buscar por id: " + e.getMessage());
			return null;
		}
	}
	
	@Override
	public ArrayList<Reporte> buscarTudo() {
		ArrayList<Reporte> reportes = new ArrayList<>();
		try{	
			final String sql = "SELECT * FROM reporte";
			if(!getConexao(sql))
				return null;
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				Reporte reporte = extraiReporte(result);
				reportes.add(reporte);
			}
			return reportes;
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
	
	public Reporte extraiReporte(ResultSet result) throws SQLException, ParseException {
		Reporte reporte = new Reporte();
		reporte.setId(result.getInt(1));
		reporte.setReclamacao(result.getString(2));
		reporte.setDate(result.getDate(3).toLocalDate());
		reporte.setIdBar(result.getInt(4));
		return reporte;
		
	}

}
