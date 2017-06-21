package bar.view.login.cadastro;

import bar.Main;
import bar.model.Bar;
import bar.model.Login;
import bar.model.bancodedados.BarDAO;
import bar.model.bancodedados.LoginDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadBarController {
	@FXML
	private TextField nome;
	@FXML
	private TextField nome_pro;
	@FXML
	private TextField endereco;
	@FXML
	private TextField telefone;
	
	@FXML
	private Button cancelar;
	@FXML
	private Button cadastrar;
	
	@FXML
	private Label lbl;
	
	private static Bar bar;
	private String status;
	
	public CadBarController() {
		bar = new Bar();
	}

	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void btnActionCancelar() {
		Main.showLogin();
	}
	
	@FXML
	private void btnActionCadastrar() {
		if(camposPreenchidos()) {
			LoginDAO cad = new LoginDAO();
			bar.setNome(getNome());
			bar.setNomePro(getNomePro());
			bar.setEndereco(getEndereco());
			bar.setTelefone(getTelefone());
			cad.salvar(bar.getLogin());
			bar.getLogin().setId(getIdLogin());
			BarDAO cad1 = new BarDAO();
			cad1.salvar(bar);
			bar.setId(getId());
			Main.showLogin();
		}
		else {
			lbl.setText(status);
		}
	}
	
	public static int getId() {
		BarDAO cad = new BarDAO();
		return cad.buscarBar(bar.getLogin().getId()).getId();
	}
	
	public static int getIdLogin() {
		LoginDAO cad = new LoginDAO();
		return cad.buscarLogin(bar.getLogin().getUsername(), bar.getLogin().getSenha()).getId();
	}
	
	public boolean camposPreenchidos() {
		if(getNome().isEmpty()) {
			status = "* Campo nome está vazio !";
			return false;
		}
		if(getNomePro().isEmpty()) {
			status = "* Campo nome proprietário está vazio !";
			return false;
		}
		if(getEndereco().isEmpty()) {
			status = "* Campo endereço está vazio !";
			return false;
		}
		if(getTelefone().isEmpty()) {
			status = "* Campo telefone está vazio !";
			return false;
		}
		
		return true;
	}

	public static void setLogin(Login login) {
		bar.setLogin(login);
	}
	
	public String getNome() {
		return nome.getText();
	}
	
	public String getNomePro() {
		return nome_pro.getText();
	}
	
	public String getEndereco() {
		return endereco.getText();
	}
	
	public String getTelefone() {
		return telefone.getText();
	}
	
}
