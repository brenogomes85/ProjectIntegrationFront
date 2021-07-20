package main.java.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeView;

public class WindowViewControllerTest extends ApplicationTest{

	WindowViewController wvc;
	
	@Before
	public void init() {
		wvc = spy(WindowViewController.class);
		
		wvc.autor = new Label();
		wvc.comboBoxLinhas = new ComboBox<String>();
		wvc.linha = new ArrayList<String>();
		wvc.modelo = new TitledPane();
		wvc.tpLinha = new TitledPane();
		wvc.treeViewModelos = new TreeView<String>();
		
	}
	
	@After
	public void finishTest() {
		wvc = null;
	}
	
	@Test
	public void initializeTeste01() throws IOException{
		wvc.initialize();
		verify(wvc).carregaLinhas(); //Check if carregaLinhas is called
	}
	
	@Test
	public void initializeTest02() throws IOException{
		wvc.initialize();
		verify(wvc).carregaTreeView(); //Check if carregaTreeView is called
	}
	
	@Test
	public void ifTitledPaneModeloIniciaDesativada() throws IOException {
		wvc.initialize();
		assertTrue("Checks if models' TitledPane starts disabled", wvc.modelo.isDisable());
	}
	
	@Test
	public void carregaLinhasTest01() throws IOException {
		wvc.carregaLinhas();
		assertEquals("Check if the ComboBox starts with 2 values", 2, wvc.comboBoxLinhas.getItems().size());
	}
	
	@Test
	public void carregaLinhasTest02() throws IOException{
		wvc.carregaLinhas();
		assertFalse("Ckeck if ComboBox is empty", wvc.comboBoxLinhas.getItems().isEmpty());
	}
	
	@Test
	public void carregaTreeViewTest01() {
		wvc.carregaTreeView();
		assertTrue("Check if TreeView is empty", wvc.treeViewModelos!=null);
	}
	
	@Test
	public void verificaSeExiste3Raizes() {
		wvc.comboBoxLinhas.getSelectionModel().select("Ares");
		wvc.carregaTreeView();
		wvc.comboBoxLinhas.getSelectionModel().select("Cronos");
		assertEquals(3, wvc.treeViewModelos.getRoot().getChildren().size());
		/*
		 * Checks if the TreeView has 3 roots
		 * when the option selected in the ComboBox is "Cronos"
		 */
		
		
	}
	
	@Test
	public void verificaSeExiste2Raizes() {
		wvc.comboBoxLinhas.getSelectionModel().select("Cronos");
		wvc.carregaTreeView();
		wvc.comboBoxLinhas.getSelectionModel().select("Ares");
		assertEquals(2, wvc.treeViewModelos.getRoot().getChildren().size());
		/*
		 * Checks if the TreeView has 2 roots
		 * when the option selected in the ComboBox is "Ares"
		 */
	}
	
	/*
	 * The following tests verify that the data loaded in the TreeView
	 * matches what was selected in the ComboBox
	 */
	@Test
	public void treeViewModelosTest01() {
		wvc.carregaTreeView();
		wvc.comboBoxLinhas.getSelectionModel().select("Cronos");
		assertEquals("Cronos 6001-A", wvc.treeViewModelos.getRoot().getChildren().get(0)
				.getChildren().get(0).getValue());
	}
	
	@Test
	public void treeViewModelosTest02() {
		wvc.carregaTreeView();
		wvc.comboBoxLinhas.getSelectionModel().select("Cronos");
		assertEquals("Cronos 6021L", wvc.treeViewModelos.getRoot().getChildren().get(1)
				.getChildren().get(0).getValue());
	}
	
	
	@Test
	public void treeViewModelosTest03() {
		wvc.carregaTreeView();
		wvc.comboBoxLinhas.getSelectionModel().select("Cronos");
		assertEquals("Cronos 6001-NG", wvc.treeViewModelos.getRoot().getChildren().get(2)
				.getChildren().get(0).getValue());
	}

	@Test
	public void treeViewModelosTest04() {
		wvc.carregaTreeView();
		wvc.comboBoxLinhas.getSelectionModel().select("Ares");
		assertEquals("ARES 7021", wvc.treeViewModelos.getRoot().getChildren().get(0)
				.getChildren().get(0).getValue());
	}
	
	@Test
	public void treeViewModelosTest05() {
		wvc.carregaTreeView();
		wvc.comboBoxLinhas.getSelectionModel().select("Ares");
		assertEquals("ARES 8023 15", wvc.treeViewModelos.getRoot().getChildren().get(1)
				.getChildren().get(0).getValue());
	}
}
