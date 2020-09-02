package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.extflightdelays.model.Model;
import it.polito.tdp.extflightdelays.model.VelivoliAdiacenti;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private ComboBox<String> cmbBoxStati;

    @FXML
    private Button btnVisualizzaVelivoli;

    @FXML
    private TextField txtT;

    @FXML
    private TextField txtG;

    @FXML
    private Button btnSimula;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	
    	this.model.creaGrafo();
    	this.cmbBoxStati.getItems().clear();
    	this.cmbBoxStati.getItems().addAll(this.model.getVertex());
    	txtResult.appendText("Grafo creato!\n"+"#Vertici = "+this.model.getVertex().size()+" #Archi = "+this.model.getEdge().size());

    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	String stato = this.cmbBoxStati.getValue();
    	
    	if(stato == null) {
    		txtResult.appendText("Scegliere uno stato!");
    		return;
    	}
    	
    	String gi = this.txtG.getText();
    	String ti = this.txtT.getText();
    	Integer T;
    	Integer G;
    	if(gi == null || ti == null) {
    		txtResult.appendText("Scegliere uno stato!");
    		return;
    	}
    	try {
    		T = Integer.parseInt(ti);
    		G = Integer.parseInt(gi);
    		
    	}catch (IllegalArgumentException e) {
        	txtResult.appendText("Inserire valore numerico!");
        	return;
        }
    	
    	txtResult.appendText(this.model.simula(T, G, stato).entrySet()+"\n");

    }

    @FXML
    void doVisualizzaVelivoli(ActionEvent event) {
    	txtResult.clear();
    	
    	String stato = this.cmbBoxStati.getValue();
    	
    	if(stato == null) {
    		txtResult.appendText("Scegliere uno stato!");
    		return;
    	}
    	
    	for(VelivoliAdiacenti v: this.model.getVelivoli(stato)) {
    		txtResult.appendText(v.toString()+"\n");
    	}

    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert cmbBoxStati != null : "fx:id=\"cmbBoxStati\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnVisualizzaVelivoli != null : "fx:id=\"btnVisualizzaVelivoli\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert txtT != null : "fx:id=\"txtT\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert txtG != null : "fx:id=\"txtG\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}
