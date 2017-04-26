package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TextArea txtResult;
	@FXML
	private TextField inputNumeroLettere;
	@FXML
	private TextField inputParola;
	@FXML
	private Button btnGeneraGrafo;
	@FXML
	private Button btnTrovaVicini;
	@FXML
	private Button btnTrovaGradoMax;
	
	private Model model;
	
	public void setModel (Model model){
		this.model=model;
	}

	@FXML
	void doReset(ActionEvent event) {
		
		model.reset();
		
		inputNumeroLettere.clear();
		inputParola.clear();
		
		txtResult.setText("Reset!");
	}

	@FXML
	void doGeneraGrafo(ActionEvent event) {
		
		if(!inputNumeroLettere.getText().equals("") && inputNumeroLettere.getText().matches("[0-9]*"))
			txtResult.setText(model.createGraph(Integer.parseInt(inputNumeroLettere.getText())).toString());
		else
			txtResult.setText("Attenzione: inserisci un numero!");

		try {
			//txtResult.setText("Controller -- TODO!");
			
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaGradoMax(ActionEvent event) {
		
		txtResult.clear();
		
		txtResult.appendText(model.findMaxDegree());
		
		try {
			//txtResult.setText("Controller -- TODO!");

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {
		
		List<String> ris = model.displayNeighbours(inputParola.getText());
		
		if(ris!=null)
			txtResult.setText(ris.toString());
		else
			txtResult.setText("Attenzione: parola non trovata.");
		
		try {
			//txtResult.setText("Controller -- TODO!");

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputNumeroLettere != null : "fx:id=\"inputNumeroLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputParola != null : "fx:id=\"inputParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaTutti\" was not injected: check your FXML file 'Dizionario.fxml'.";
	}
}