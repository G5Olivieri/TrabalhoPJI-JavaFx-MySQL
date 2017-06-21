package bar.view.login.cadastro;

import java.io.IOException;

import bar.Main;
import bar.model.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class CadastroLoginController {
	@FXML
	private TextField username;
	@FXML
	private PasswordField senha;
	@FXML
	private PasswordField conSenha;
	@FXML
	private Button prosseguir;
	@FXML
	private Button calcel;
	@FXML
	private Label lbl;
	
	private static Login login;
	
	private String status;
	
	public CadastroLoginController() {
		lbl = new Label();
	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void btnActionProsseguir(ActionEvent event) {
		if(camposPreenchidos()){
			login = new Login(getUsername(), getSenha());
			Main.BarorCliente();
		}
		lbl.setText(status);
		
	}
	
	@FXML
	private void btnActionCancel() {
		Main.showLogin();
	}

	
	public static void showCadCliente(BorderPane rootPane) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/bar/view/login/cadastro/CadCliente.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			rootPane.setCenter(pane);
			CadClienteController.setLogin(login);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void showCadBar(BorderPane rootPane) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/bar/view/login/cadastro/CadBar.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			CadBarController.setLogin(login);
			rootPane.setCenter(pane);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isValida(String senha, String conSenha) {
		if(senha.isEmpty()) {
			status = "* Campo senha está vazio !";
			return false;
		}
		if(conSenha.isEmpty()) {
			status = "* Campo confimar senha está vazio !";
			return false;
		}
		
		if(senha.length() < 8) {
			status = "* Senha muito pequena!";
			return false;
		}
		if(!senha.equals(conSenha)){
			status = "* Senha diferente de confirmação de senha !";
			return false;
		}
		return true;
	}
	
	public boolean camposPreenchidos() {
		if(getUsername().isEmpty()) {
			status = "* Campo email está vazio !";
			return false;
		}
		if(isValida(getSenha(), getConSenha())) {
			status = "";
			return true;
		}
		return false;
		
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getUsername() {
		return username.getText();
	}
	public String getSenha() {
		return senha.getText();
	}
	public String getConSenha() {
		return conSenha.getText();
	}
}
