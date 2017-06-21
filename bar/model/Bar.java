package bar.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bar {
	private int id;
	private StringProperty nome;
	private String nome_pro;
	private String endereco;
	private String telefone;
	private Login login;
	
	
	public Bar(){
		setId(0);
		nome = new SimpleStringProperty("glayson");
		setNomePro("glayson");
		setEndereco("glayson");
		setTelefone("glayson");
		setLogin(new Login());
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	public Login getLogin() {
		return login;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		
		this.nome.set(nome);;
	}

	public StringProperty getNomeProperty() {
		return nome;
	}
	
	public String getNomePro() {
		return nome_pro;
	}

	public void setNomePro(String nome_pro) {
		this.nome_pro = nome_pro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
