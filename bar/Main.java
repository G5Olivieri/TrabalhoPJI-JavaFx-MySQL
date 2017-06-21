package bar;

import java.io.IOException;

import bar.view.login.cadastro.CadastroLoginController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
	private static Stage primaryStage;
	private static BorderPane rootPane;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		initRootLayout(stage);
		showLogin();
	}
	
	public static void fecharPrograma() {
		primaryStage.close();
	}
	
	public static void initRootLayout(Stage stage) {
		primaryStage = stage;
		primaryStage.getIcons().add(new Image("file:imagens/icon.png"));
		primaryStage.setTitle("Bar Report");
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/bar/view/Principal.fxml"));
			rootPane = (BorderPane) loader.load();
			Scene scene = new Scene(rootPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void showCadastroLogin() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/bar/view/login/cadastro/CadastroLogin.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			rootPane.setCenter(pane);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void showLogin(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/bar/view/login/Login.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			rootPane.setCenter(pane);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void BarorCliente() {
		Stage dialog = new Stage();
		Button cliente = new Button("Cliente");
		Button bar = new Button("Bar");
		Text scenetitle = new Text("Escolha o tipo de login que deseja");
		GridPane pane = new GridPane();
		
		scenetitle.setId("bar");
		
		dialog.setTitle("Bar ou Cliente");
		
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
		pane.add(scenetitle, 0, 0);
		
		HBox hbtn = new HBox(10);
		hbtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbtn.getChildren().add(cliente);
		hbtn.getChildren().add(bar);
		
		pane.add(hbtn, 0, 1);
		
		cliente.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				CadastroLoginController.showCadCliente(rootPane);
				dialog.close();
			}
		});
		
		bar.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				CadastroLoginController.showCadBar(rootPane);
				dialog.close();
			}
		});
		
		Scene scene = new Scene(pane, 400, 300);
		scene.getStylesheets().add
		 (Main.class.getResource("/bar/view/root.css").toExternalForm());
		dialog.setScene(scene);
		dialog.show();
	}
	
	public static void showReporte() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/bar/view/Reporte.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			rootPane.setCenter(pane);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
