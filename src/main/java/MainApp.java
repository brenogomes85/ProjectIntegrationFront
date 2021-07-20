package main.java;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

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
		this.primaryStage.setTitle("Projeto Integra��o");
		
		initWindowView();
	}
	
	/**
	 * Inicializa a aplica��o Principal
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
    	try{
    		launch(args);
    	}catch (Exception e) {
    	    JOptionPane.showMessageDialog(null, e.getMessage());
    	    try {
    	      PrintWriter pw = new PrintWriter(new File("<somefilename.txt>"));
    	      e.printStackTrace(pw);
    	      pw.close();
    	    } catch (IOException e1) {
    	    	e1.printStackTrace();
    	    }
    	  }
    }
}