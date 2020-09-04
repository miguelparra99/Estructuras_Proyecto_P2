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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class ViewHistorial extends VBox {

    private Hashtable<String, Historial> DictHistorial;
    private Button BttnMostrarInfo;
    private Button BttnCodificarPalabra;
    private Button BttnDecodificarBits;
    private Button BttnOtroArchivo;
    private BorderPane General;
    private TextField TxtDeCodificarPalabra;
    private TextField TxtCodificarPalabra;
    private Historial Hist;

    private ChoiceBox cb;

    public ViewHistorial(Hashtable<String, Historial> DictHistorial) {
        this.DictHistorial = DictHistorial;
        Llenar();
    }

    public void Llenar() {
        this.setAlignment(Pos.CENTER);
        TxtCodificarPalabra=new TextField();
        TxtDeCodificarPalabra=new TextField();
        General = new BorderPane();
         VBox l=new VBox();
         VBox ayudasup=new VBox();
         VBox ayudainf=new VBox();
         Label ayudas=new Label("Bits : ");
         Label ayudai=new Label("Caracteres : ");
         ayudas.setFont(new Font(25));
         ayudai.setFont(new Font(25));
         
            HBox derSuperior=new HBox();
            HBox derInferior=new HBox();        
        BttnMostrarInfo = new Button("Mostrar Informacion");
        BttnCodificarPalabra = new Button("Codificar");
        BttnDecodificarBits = new Button("Decodificar");
        BttnOtroArchivo = new Button("Reiniciar");
        Label Descripcion = new Label("Selecciona un archivos codificados guardado en el historial");
        Descripcion.setFont(new Font(30));
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
            this.getChildren().clear();
            Hist=DictHistorial.get(cb.getSelectionModel().getSelectedItem().toString());
            
            l.getChildren().addAll(derSuperior,derInferior);
            General.setRight(l);
            General.setLeft(Hist.getViewCodificacion());
            General.setTop(Hist.getViewFrecuencia());
            HBox a=new HBox();
            a.getChildren().add(BttnOtroArchivo);
            a.setAlignment(Pos.CENTER);
            General.setBottom(a);
            VBox Nodo=new VBox();
            Label titulo=new Label("Informacion");
            titulo.setFont(new Font(30));
            Nodo.getChildren().addAll(new Label("Ingre la palabra a codificar"),TxtCodificarPalabra,BttnCodificarPalabra,new Label("Ingrese la palabra a decodificar"),TxtDeCodificarPalabra,BttnDecodificarBits);
            Nodo.setMaxSize(400, 400);
            Nodo.setMinSize(400, 400);
            General.setCenter(Nodo);
            Label RutaOrigen = new Label("La ruta Origen es : "+Hist.getRutaOrigen());
            Label RutaDestino = new Label("La ruta Destino es : "+Hist.getRutaDestino());
            Label Manera=new Label("Se codifico de manera : "+Hist.getManera());
            Label Fecha = new Label("La hora de guardado es : "+Hist.getFehca());
            this.getChildren().addAll(titulo,RutaOrigen,RutaDestino,Manera,Fecha,General);
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #ffffff;");
        });
        BttnOtroArchivo.setOnAction(evente->{
        this.getChildren().clear();
        Llenar();
        }); 
        BttnCodificarPalabra.setOnAction(event->{
        if(TxtCodificarPalabra.toString()==null){
            HBox message= new HBox();
            Label msg= new Label("Ingrese una palabra");
            message.getChildren().add(msg);
            message.setAlignment(Pos.CENTER);
            this.getChildren().add(message);
        }else{
        ElegirArchivo Token=new ElegirArchivo("dda");
        
        Label dersup=new Label("Bits : "+Token.codificarArchivo((Hashtable<String, HuffmanInfo>) Hist.getMapaCodificar(),TxtCodificarPalabra.getText()));
        dersup.setFont(new Font(15.0));
        derSuperior.getChildren().clear();
        derSuperior.getChildren().add(dersup);
        }
        });
        BttnDecodificarBits.setOnAction(event->{
        ElegirArchivo Token=new ElegirArchivo("dda");
        Label derinf= new Label("Caracteres :"+Token.decodificarbits((Hashtable<String, HuffmanInfo>) Hist.getMapaDeCodificar(),TxtDeCodificarPalabra.getText()));
        derinf.setFont(new Font(15.0));
        derInferior.getChildren().clear();
        derInferior.getChildren().add(derinf);
        });
        
    }
}
