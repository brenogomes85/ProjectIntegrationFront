package main.java;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane principal;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Projeto Integração");
		
		initWindowView();
	}
	
	/**
	 * Inicializa a aplicação Principal
	 * 
	 */
	public void initWindowView() {
		try {
			//Carrega o principal do arquivo fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WindowView.fxml"));
			AnchorPane windowView = (AnchorPane) loader.load();
			
			//Mostra a scene contendo o arquivo principal.
			Scene scene = new Scene(windowView);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
        /**
         * Retorna o palco principal
         * @return
         */
        public Stage getPrimaryStage() {
            return primaryStage;
        }

    public static void main(String[] args) {
        launch(args);
    }
}