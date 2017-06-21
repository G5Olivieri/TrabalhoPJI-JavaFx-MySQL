package bar.view.login.cadastro;

import bar.Main;
import bar.model.Cliente;
import bar.model.Login;
import bar.model.bancodedados.ClienteDAO;
import bar.model.bancodedados.LoginDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadClienteController {
	@FXML
	private TextField nome;
	@FXML
	private TextField telefone;
	@FXML
	private TextField email;
	@FXML
	private Button cadastrar;
	@FXML
	private Button cancelar;
	@FXML
	private Label lbl;
	private static Cliente cliente;
	
	private String status;
	
	public CadClienteController() {
		cliente = new Cliente();
	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void btnActionCadastrar() {
		if(camposPreenchidos()) {
			LoginDAO cad = new LoginDAO();
			cad.salvar(cliente.getLogin());
			cliente.getLogin().setId(getIdLogin());
			ClienteDAO cad1 = new ClienteDAO();
			cad1.salvar(cliente);
			cliente.setId(getId());
			Main.showLogin();
		}
		else {
			lbl.setText(status);
		}
	}
	
	@FXML
	private void btnActionCancelar() {
		Main.showLogin();
	}
	
	public static int getId() {
		ClienteDAO cad = new ClienteDAO();
		return cad.buscarCliente(cliente.getLogin().getId()).getId();
	}
	
	public static int getIdLogin() {
		LoginDAO cad = new LoginDAO();
		return cad.buscarLogin(cliente.getLogin().getUsername(), cliente.getLogin().getSenha()).getId();
	}
	
	public boolean camposPreenchidos() {
		if(getNome().isEmpty()) {
			status = "* Campo nome está vazio !";
			return false;
		}
		if(getTelefone().isEmpty()) {
			status = "* Campo telefone está vazio !";
			return false;
		}
		if(getEmail().isEmpty()) {
			status = "* Campo email está vazio !";
			return false;
		}
		
		return true;
	}

	public static void setLogin(Login login) {
		cliente.setLogin(login);
	}
	
	public String getNome() {
		return nome.getText();
	}
	
	public String getTelefone() {
		return telefone.getText();
	}
	
	public String getEmail() {
		return email.getText();
	}
}
