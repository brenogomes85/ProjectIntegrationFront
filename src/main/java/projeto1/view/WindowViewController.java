package projeto1.view;


import java.util.ArrayList;
import java.util.List;

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
import model.Linha;
import model.ModelosMedidores;
import projeto1.model.CategoriaMedidores;
import javafx.scene.control.Accordion;


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
    
    
    @FXML
    private void initialize() {
    	carregaLinhas();	
    	modelo.setDisable(true); //Desabilita o titledPane 'modelo' no início
    	carregaTreeView();

    }
       
    //Insere/Carrega as linhas de modelo (Cronos, Ares) no combobox 
    public void carregaLinhas() {
    	
    	Linha linha1 = Linha.CRONOS;
    	Linha linha2 = Linha.ARES;
    	
    	linha.add(linha1.getLinha());
    	linha.add(linha2.getLinha());
    	
    	obsLinha = FXCollections.observableArrayList(linha);
    	
    	comboBoxLinhas.setItems(obsLinha);
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
			@Override
			public void changed(ObservableValue<? extends String> options, String oldValue, String newValue) {
				//Recebe o valor selecionado no combobox
				String linha = comboBoxLinhas.getValue();
				
				//Cria a raiz da Tree View
				TreeItem raiz = new TreeItem("");
				
				ArrayList <TreeItem> categoria = new ArrayList<TreeItem>();
				
				switch(linha){
					case "Cronos":
						TreeItem cronosOld = new TreeItem(ModelosMedidores.CRONOS_OLD.getModelo());
						cronosOld.getChildren().add(new TreeItem(CategoriaMedidores.CRONOS_OLD1.getCategoria()));
						cronosOld.getChildren().add(new TreeItem(CategoriaMedidores.CRONOS_OLD2.getCategoria()));
						cronosOld.getChildren().add(new TreeItem(CategoriaMedidores.CRONOS_OLD3.getCategoria()));
						cronosOld.setExpanded(true);
					
						TreeItem cronosL = new TreeItem(ModelosMedidores.CRONOS_L.getModelo());
						cronosL.getChildren().add(new TreeItem(CategoriaMedidores.CRONOS_L1.getCategoria()));
						cronosL.getChildren().add(new TreeItem(CategoriaMedidores.CRONOS_L2.getCategoria()));
						cronosL.setExpanded(true);
						
						TreeItem cronosNG = new TreeItem(ModelosMedidores.CRONOS_NG.getModelo());
						cronosNG.getChildren().add(new TreeItem(CategoriaMedidores.CRONOS_NG1.getCategoria()));
						cronosNG.getChildren().add(new TreeItem(CategoriaMedidores.CRONOS_NG2.getCategoria()));
						cronosNG.getChildren().add(new TreeItem(CategoriaMedidores.CRONOS_NG3.getCategoria()));
						cronosNG.getChildren().add(new TreeItem(CategoriaMedidores.CRONOS_NG4.getCategoria()));
						cronosNG.getChildren().add(new TreeItem(CategoriaMedidores.CRONOS_NG5.getCategoria()));
						cronosNG.getChildren().add(new TreeItem(CategoriaMedidores.CRONOS_NG6.getCategoria()));
						cronosNG.setExpanded(true);
						
						categoria.add(cronosOld);
						categoria.add(cronosL);
						categoria.add(cronosNG);
						
						break;
						
					case "Ares":
						TreeItem aresTB = new TreeItem(ModelosMedidores.ARES_TB.getModelo());
						aresTB.getChildren().add(new TreeItem(CategoriaMedidores.ARES_TB1.getCategoria()));
						aresTB.getChildren().add(new TreeItem(CategoriaMedidores.ARES_TB2.getCategoria()));
						aresTB.getChildren().add(new TreeItem(CategoriaMedidores.ARES_TB3.getCategoria()));
						aresTB.setExpanded(true);
						
						TreeItem aresTHS = new TreeItem(ModelosMedidores.ARES_THS.getModelo());
						aresTHS.getChildren().add(new TreeItem(CategoriaMedidores.ARES_THS1.getCategoria()));
						aresTHS.getChildren().add(new TreeItem(CategoriaMedidores.ARES_THS2.getCategoria()));
						aresTHS.getChildren().add(new TreeItem(CategoriaMedidores.ARES_THS3.getCategoria()));
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
