package main.java.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meters.metersapi.resources.MedidoresResource;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import main.model.Meters;
import main.model.MetersService;

public class WindowViewController {
	
	private List<String> linha = new ArrayList<String>();
	
	private ObservableList<String> obsLinha;
	
    @FXML
    private TitledPane tpLinha;

    @FXML
    private ComboBox<String> comboBoxLinhas;

    @FXML
    private TitledPane modelo;

    @FXML
    private TreeView<String> treeViewModelos;

    @FXML
    private Label autor;
    
    private List<MetersView> meters;
    
    @FXML
    private void initialize() throws IOException {
    	carregaLinhas();	
    	modelo.setDisable(true); //Desabilita o titledPane 'modelo' no in�cio
    	carregaTreeView();

    }
       
    //Insere/Carrega as linhas de modelo (Cronos, Ares) no combobox 
    public void carregaLinhas() throws IOException {
    	String aux = "";
    	try {
    		meters = GetRequestMeters.sendGET();
        	
    		for(int i=0; i<meters.size(); i++) {
    			aux = ""+meters.get(i).getLine();
        		if(!linha.contains(aux))
        			linha.add(aux);
        	}

    	} catch (HibernateException exception) {
            System.out.println("Problem creating session factory");
            exception.printStackTrace();
            throw exception;
        }
    	
    	obsLinha = FXCollections.observableArrayList(linha);
       	comboBoxLinhas.setItems(obsLinha);
    }
    
    /*
    * Carrega os dados na tree view
    */
    @SuppressWarnings("unchecked")
    public TreeView<String> carregaTreeView() throws IOException {
    	/* 
    	 * Verifica o valor selecionado no combobox e constroi a tree view
    	 * de acordo com a op��o escolhida.
    	 */
    	comboBoxLinhas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				//Recebe o valor selecionado no ComboBox
				String linha = comboBoxLinhas.getValue();
				
				//Cria a raiz na TreeView
				TreeItem raiz = new TreeItem("");
				
				ArrayList <TreeItem> categoria = new ArrayList<TreeItem>();
				
				try {
					
					meters = GetRequestMeters.sendGET();
					
					switch(linha) {
						case "Cronos":
							//Cria os filhos da raiz
							TreeItem cronosOld = new TreeItem<>("Cronos Old");
							TreeItem cronosL = new TreeItem<>("Cronos L");
							TreeItem cronosNG = new TreeItem<>("Cronos NG");
							
							for(MetersView m : meters) {
								
								switch(m.getCategory()){
									case "Cronos Old":
										cronosOld.getChildren().add(new TreeItem<>(m.getModel()));
										break;
											
									case "Cronos L":
										cronosL.getChildren().add(new TreeItem<>(m.getModel()));
										break;
											
									case "Cronos NG":
										cronosNG.getChildren().add(new TreeItem<>(m.getModel()));
										break;
								}
							}
							
							
							cronosOld.setExpanded(true);
							cronosL.setExpanded(true);
							cronosNG.setExpanded(true);
							
							categoria.add(cronosOld);
							categoria.add(cronosL);
							categoria.add(cronosNG);
							
							break;
							
						case "Ares":
							//Cria os filhos da raiz
							TreeItem aresTB = new TreeItem<>("Ares TB");
							TreeItem aresTHS = new TreeItem<>("Ares THS");
							
							for(MetersView m : meters) {
								switch(m.getCategory()) {
									case "Ares TB":			
										aresTB.getChildren().add(new TreeItem<>(m.getModel()));
										break;
										
									case "Ares THS":
										aresTHS.getChildren().add(new TreeItem<>(m.getModel()));
										break;
								}
							}
							
							aresTB.setExpanded(true);
							aresTHS.setExpanded(true);
							
							categoria.add(aresTB);
							categoria.add(aresTHS);
							
							break;
					}
					
				} catch (IOException e) {
					System.out.println("erro no request");
					e.printStackTrace();
				}
				
				//Adiciona filhas na raiz
				raiz.getChildren().addAll(categoria);
				
				//Seta o n� da raiz
				treeViewModelos.setRoot(raiz);
				
				//Oculta a raiz
				treeViewModelos.setShowRoot(false);
				
				//Ativa o titledPane ap�s carregar a tree view
				modelo.setDisable(false);
			}
    		
    	});
    	
    	return treeViewModelos;
    
    }
}
