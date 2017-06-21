package bar.view.login;

import bar.Main;
import bar.model.Login;
import bar.model.bancodedados.LoginDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
	private TextField email;
	@FXML
	private PasswordField senha;
	
	@FXML
	private Button entrar;
	@FXML
	private Button cancel;
	@FXML
	private Button registrar;
	@FXML
	private Label result;
	
	private String status;
	
	public LoginController() {
	
	}
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void btnRegistrar() {
		Main.showCadastroLogin();
	}
	
	@FXML
	private void btnEntrar() {
		if(camposPreenchidos()) {
			Login login = new Login(getEmail(), getSenha());
			LoginDAO consult = new LoginDAO();
			if(consult.isLogin(login)) {
				Main.showReporte();
			}
			else {
				status = "* login ou senha inválido !";
				result.setText(status);
			}
		}
		else {
			result.setText(status);
		}
	}
	
	@FXML
	private void btnCancelar() {
		Main.fecharPrograma();
	}
	
	public boolean camposPreenchidos() {
		if(getEmail().isEmpty()) {
			status = "* Campo email está vazio !";
			return false;
		}
		if(getSenha().isEmpty()) {
			status = "* Campo senha está vazio !";
			return false;
		}
		return true;
		
	}
	
	
	public String getEmail() {
		return email.getText();
	}
	
	public String getSenha() {
		return senha.getText();
	}
	
	
}
