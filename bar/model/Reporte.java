package bar.model;

import java.time.LocalDate;

public class Reporte {
	private int id;
	private String reclamacao;
	private LocalDate date;
	private int idbar;
	
	public Reporte() {
		setId(0);
		setReclamacao("Bar ótimo");
		setDate(LocalDate.now());
		setIdBar(0);
	}
	
	public void setIdBar(int i) {
		idbar = i;
	}
	
	public int getIdBar() {
		return idbar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReclamacao() {
		return reclamacao;
	}

	public void setReclamacao(String reclamacao) {
		this.reclamacao = reclamacao;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
