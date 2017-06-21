package it.polito.tdp.meteo;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.meteo.model.Model;
import it.polito.tdp.meteo.model.Stat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class MeteoController {
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCalcola;

    @FXML
    private Button btnCerca;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtTemperatura;
    

    @FXML
    void doCalcola(ActionEvent event) {
  
    	String stemp=this.txtTemperatura.getText();
    	if(stemp.equals(" ")){
    		txtResult.appendText("ERRORE: Inserire una tempertura");
    		return;
    	}
    	int temp;
    	try{
    		temp=Integer.parseInt(stemp);
    	}catch(NumberFormatException e){
    		txtResult.appendText("ERRORE: Inserire un numero");
    		return;
    	}
    	txtResult.appendText("Le temperature possibili in due giorni sono:\n");
    	for(Integer i: model.getAllTempPos(temp)){
    		txtResult.appendText(i+"°\n");
    	}

    }

    @FXML
    void doCerca(ActionEvent event) {
    	txtResult.clear();
    	String stemp=this.txtTemperatura.getText();
    	if(stemp.equals(" ")){
    		txtResult.appendText("ERRORE: Inserire una tempertura");
    		return;
    	}
    	int temp;
    	try{
    		temp=Integer.parseInt(stemp);
    	}catch(NumberFormatException e){
    		txtResult.appendText("ERRORE: Inserire un numero");
    		return;
    	}
    	for(Stat s:model.getDateTmedia(temp)){
    		if(s.getDataPrec()!=null)
    			txtResult.appendText(s.getDataTemperatura().toString()+" "+s.getTemp()+"° giorno prec "+s.getDataPrec().toString()+" "+s.getTempPrec()+"°\n");
    		else
    			txtResult.appendText(s.getDataTemperatura().toString()+" "+s.getTemp()+"°\n");
    	}

    }


    @FXML
    void initialize() {
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert txtTemperatura != null : "fx:id=\"txtTemperatura\" was not injected: check your FXML file 'Meteo.fxml'.";


    }


	public void setModel(Model model) {
		this.model=model;
	}

}
