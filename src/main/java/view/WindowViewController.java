package main.java.view;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

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
	private List<String> test = new ArrayList<String>();
	
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
    
    
    @FXML
    private void initialize() {
    	carregaLinhas();	
    	modelo.setDisable(true); //Desabilita o titledPane 'modelo' no início
    	carregaTreeView();

    }
       
    //Insere/Carrega as linhas de modelo (Cronos, Ares) no combobox 
    public void carregaLinhas() {
    	
    
    	try {
    		MetersService ms = new MetersService();
    		 
    		String aux = ""+ms.findById(1);
    		String aux2 = ""+ms.findById(15);
    		
    		String listString = String.join(",", aux);
    		String[] test = listString.split(",");
    		
    		linha.add(test[1]);
    		
    		listString = String.join(",", aux2);
    		test = listString.split(",");
    		
    		linha.add(test[1]);
    		
    		obsLinha = FXCollections.observableArrayList(linha);
        	comboBoxLinhas.setItems(obsLinha);
        	
        	

        } catch (HibernateException exception) {
            System.out.println("Problem creating session factory");
            exception.printStackTrace();
            throw exception;
        }
    	
    	//comboBoxLinhas.setItems(obsLinha);
    }
    
    /*
    * Carrega os dados na tree view
    */
    
    @SuppressWarnings("unchecked")
    public TreeView<String> carregaTreeView(){
    	 /* 
    	 * Verifica o valor selecionado no combobox e constroi a tree view
    	 * de acordo com a opção escolhida.
    	 */
    	comboBoxLinhas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> options, String oldValue, String newValue) {
				//Recebe o valor selecionado no combobox
				String linha = comboBoxLinhas.getValue();
				System.out.println(linha);
				
				//Cria a raiz da Tree View
				TreeItem raiz = new TreeItem("");
			
				ArrayList <TreeItem> categoria = new ArrayList<TreeItem>();
				
				MetersService ms = new MetersService();
				Meters meters = new Meters();
				String teste = null;
				
				switch(linha){
					case "Cronos":
						
						//Cria os filhos da raiz
						TreeItem cronosOld = new TreeItem<>("Cronos Old");
						TreeItem cronosL = new TreeItem<>("Cronos L");
						TreeItem cronosNG = new TreeItem<>("Cronos NG");
						
						int cont = 1;
						while(cont<=11){
							String aux = ""+ms.findById(cont);
							String listString = String.join(",", aux);
				    		String[] test = listString.split(",");
				    		
				    		switch(test[2]) {
				    			case "Cronos Old":
				    				cronosOld.getChildren().add(new TreeItem<>(test[3]));
				    				
				    				break;
				    			case "Cronos L":
				    				cronosL.getChildren().add(new TreeItem<>(test[3]));
				    				
				    				break;
				    			case "Cronos NG":
				    				cronosNG.getChildren().add(new TreeItem<>(test[3]));
				    				
				    				break;
				    		}
				    		
				    		cont++;
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
						cont = 12;
						while(cont<=17){
							String aux = ""+ms.findById(cont);
							String listString = String.join(",", aux);
				    		String[] test = listString.split(",");
				    		
				    		switch(test[2]) {
				    			case "Ares TB":
				    				aresTB.getChildren().add(new TreeItem<>(test[3]));
				    				
				    				break;
				    			case "Ares THS":
				    				aresTHS.getChildren().add(new TreeItem<>(test[3]));
				    				
				    				break;
				    		}
				    		
				    		cont++;
				    	}
						
						aresTB.setExpanded(true);
						aresTHS.setExpanded(true);
						
						categoria.add(aresTB);
						categoria.add(aresTHS);
						
						break;
				}
				
				//Adiciona filhas na raiz
				raiz.getChildren().addAll(categoria);
				
				//Seta o nó da raiz
				treeViewModelos.setRoot(raiz);
				
				//Oculta a raiz
				treeViewModelos.setShowRoot(false);
				
				//Ativa o titledPane após carregar a tree view
				modelo.setDisable(false);
				
			}
		});
    	
    	return treeViewModelos;
    	
    }
}
