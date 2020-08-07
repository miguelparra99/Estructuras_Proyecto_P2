/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Apoyo.Historial;
import Apoyo.HuffmanInfo;
import java.util.ArrayList;
import java.util.Hashtable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author LAPTOP
 */
public class ViewHistorial extends VBox {

    private Hashtable<String, Historial> DictHistorial;
    private Button BttnMostrarInfo;
    private Button BttnCodificarPalabra;
    private Button BttnDecodificarBits;
    private Button BttnOtroArchivo;
    private BorderPane General;
    private TextField TxtDeCodificarPalabra;
    private TextField TxtCodificarPalabra;

    private ChoiceBox cb;

    public ViewHistorial(Hashtable<String, Historial> DictHistorial) {
        this.DictHistorial = DictHistorial;
        Llenar();
    }

    public void Llenar() {
        TxtCodificarPalabra=new TextField();
        TxtDeCodificarPalabra=new TextField();
        General = new BorderPane();
        BttnMostrarInfo = new Button("Mostrar Informacion");
        BttnCodificarPalabra = new Button("Codificar");
        BttnDecodificarBits = new Button("Decodificar");
        BttnOtroArchivo = new Button("Reiniciar");
        Label Descripcion = new Label("Selecciona un archivos codificados guardado en el historial");
        cb = new ChoiceBox();
        cb.setTooltip(new Tooltip("Selecciona el tipo de codificacion"));
        ObservableList<Object> A = FXCollections.observableArrayList();
        DictHistorial.forEach((k, v) -> {
            A.add(k);
        cb.getSelectionModel().select(k);
        });

        
        cb.setItems(A);
        this.getChildren().addAll(Descripcion, cb,BttnMostrarInfo);
        BttnMostrarInfo.setOnAction(event -> {
            Historial Hist=DictHistorial.get(cb.getSelectionModel().getSelectedItem().toString());
            General.setLeft(Hist.getViewCodificacion());
            General.setTop(Hist.getViewFrecuencia());
            General.setBottom(BttnOtroArchivo);
            VBox Nodo=new VBox();
            Nodo.getChildren().addAll(new Label("Ingre la palabra a codificar"),TxtCodificarPalabra,BttnCodificarPalabra,new Label("Ingrese la palabra a decodificar"),TxtDeCodificarPalabra,BttnDecodificarBits);
            General.setCenter(Nodo);
            Label RutaOrigen = new Label("La ruta Origen es : "+Hist.getRutaOrigen());
            Label RutaDestino = new Label("La ruta Destino es : "+Hist.getRutaDestino());
            Label Manera=new Label("Se codidico de manera : "+Hist.getManera());
            Label Fecha = new Label("La hora de guardado es : "+Hist.getFehca());
            
            this.getChildren().addAll(RutaOrigen,RutaDestino,Manera,Fecha,General);
        });
        BttnOtroArchivo.setOnAction(evente->{
        this.getChildren().clear();
        Llenar();
        }); 
        

    }
}
