package bar.model;

public class Cliente {
	private int id;
	private String nome;
	private String telefone;
	private String email;
	private Login login;
	
	public Cliente() {
		setNome("glayson");
		setTelefone("glayson");
		setEmail("glayson");
		setLogin(new Login());
	}

	public Cliente(Login login) {
		setNome("glayson");
		setTelefone("glayson");
		setEmail("glayson");
		setLogin(login);
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	
}
