package bar.view;

import java.util.ArrayList;

import bar.Main;
import bar.model.Bar;
import bar.model.Reporte;
import bar.model.bancodedados.BarDAO;
import bar.model.bancodedados.ReporteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class ReporteController {
	@FXML
	private TableView<Bar> tableBar;
	@FXML
	private TableColumn<Bar, String> bares;
	@FXML
	private Label nome;
	@FXML
	private Label nome_pro;
	@FXML
	private Label endereco;
	@FXML
	private Label telefone;
	@FXML
	private TextArea reporte;
	
	@FXML
	private Button enviar;
	
	private Bar bar;
	
	public ReporteController() {
		
	}
	
	@FXML
	private void initialize() {
		bares.setCellValueFactory(celldata -> celldata.getValue().getNomeProperty());
		setListView();
		showBarDetails(null);
	    tableBar.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showBarDetails(newValue));
	}
	
	private void showBarDetails(Bar bar) {
		if(bar != null) {
			this.bar = bar;
			nome.setText(bar.getNome());
			nome_pro.setText(bar.getNomePro());
			endereco.setText(bar.getEndereco());
			telefone.setText(bar.getTelefone());
		}
		else {
			nome.setText(" ");
			nome_pro.setText(" ");
			endereco.setText(" ");
			telefone.setText(" ");
		}
	}
	
	private void setListView() {
		ObservableList<Bar> names = FXCollections.observableArrayList();
		BarDAO con = new BarDAO();
		ArrayList<Bar> bares = con.buscarTudo();
		for(Bar a : bares) {
			names.add(a);
		}
		tableBar.setItems(names);
	}
	
	@FXML
	private void btnActionEnviar() {
		if(!nome.getText().equals(" ")){
			if(!reporte.getText().isEmpty()) {
				ReporteDAO cad = new ReporteDAO();
				Reporte reporte = new Reporte();
				reporte.setReclamacao(this.reporte.getText());
				reporte.setIdBar(bar.getId());
				cad.salvar(reporte);
				Main.showReporte();
			}
		}
	}
}
