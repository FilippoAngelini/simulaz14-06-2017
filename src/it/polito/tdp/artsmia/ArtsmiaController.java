/**
 * Sample Skeleton for 'Artsmia.fxml' Controller Class
 */

package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {
	
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ChoiceBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtFieldStudenti"
    private TextField txtFieldStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    public void setModel(Model model){
    	this.model = model;
    	boxAnno.getItems().addAll(this.model.getAllAnni());
    }

    @FXML
    void handleCreaGrafo(ActionEvent event) {
    	
    	Integer annoInizio = boxAnno.getValue();
    	
    	if(annoInizio==null){
    		txtResult.setText("Attenzione: devi selezionare un anno di inizio");
    		return;
    	}
    	
    	model.creaGrafo(annoInizio);
    	
    	txtResult.setText(model.getStats());

    }

    @FXML
    void handleSimula(ActionEvent event) {
    	
    	Integer anno = boxAnno.getValue();
    	
    	txtResult.setText(model.getClassificaOpere(anno));

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtFieldStudenti != null : "fx:id=\"txtFieldStudenti\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }
}
