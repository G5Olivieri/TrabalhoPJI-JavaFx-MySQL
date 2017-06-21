package bar.model;

public class Login {
	private int id;
	private String username;
	private String senha;
	
	
	public Login() {
		setUsername("glayson");
		setSenha("glaysonglayson");
	}
	
	public Login(int id, String username, String senha) {
		setId(id);
		setUsername(username);
		setSenha(senha);
	}
	
	public Login(String username, String senha) {
		setUsername(username);
		setSenha(senha);
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setSenha(String senha) {
			this.senha = senha;
	}
	
	public String getSenha() {
		return senha;
	}
	
	@Override
	public String toString() {
		return "ID: " + getId() + 
				"\nEmail: " + getUsername() + 
				"\nSenha: " + getSenha();
	}
}
