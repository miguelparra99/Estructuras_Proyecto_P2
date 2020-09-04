/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apoyo;

import java.util.Date;
import java.util.Map;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author LAPTOP
 */
public class Historial {

    private Date fehca;
    private String rutaOrigen;
    private String rutaDestino;
    private String manera;
    private Map<String, HuffmanInfo> mapaCodificar;
    private Map<String, HuffmanInfo> mapaDeCodificar;
    private ScrollPane ViewFrecuencia;
    private ScrollPane ViewCodificacion; 
    
    
    public Historial(Date fehca, String rutaOrigen, String rutaDestino, String manera, Map<String, HuffmanInfo> mapaCodificar,Map<String, HuffmanInfo> mapaDeCodificar,ScrollPane ViewFrecuencia,ScrollPane ViewCodificacion) {
        this.fehca = fehca;
        this.rutaOrigen = rutaOrigen;
        this.rutaDestino = rutaDestino;
        this.manera = manera;
        this.mapaCodificar = mapaCodificar;
        this.mapaDeCodificar = mapaDeCodificar;
        this.ViewCodificacion=ViewCodificacion;
        this.ViewFrecuencia=ViewFrecuencia;
    }

    public Date getFehca() {
        return fehca;
    }

    public void setFehca(Date fehca) {
        this.fehca = fehca;
    }

    public String getRutaOrigen() {
        return rutaOrigen;
    }

    public void setRutaOrigen(String rutaOrigen) {
        this.rutaOrigen = rutaOrigen;
    }

    public String getRutaDestino() {
        return rutaDestino;
    }

    public void setRutaDestino(String rutaDestino) {
        this.rutaDestino = rutaDestino;
    }

    public String getManera() {
        return manera;
    }

    public void setManera(String manera) {
        this.manera = manera;
    }

    public Map<String, HuffmanInfo> getMapaCodificar() {
        return mapaCodificar;
    }

    public void setMapaCodificar(Map<String, HuffmanInfo> mapaCodificar) {
        this.mapaCodificar = mapaCodificar;
    }

    public Map<String, HuffmanInfo> getMapaDeCodificar() {
        return mapaDeCodificar;
    }

    public void setMapaDeCodificar(Map<String, HuffmanInfo> mapaDeCodificar) {
        this.mapaDeCodificar = mapaDeCodificar;
    }

    public ScrollPane getViewFrecuencia() {
        return ViewFrecuencia;
    }

    public void setViewFrecuencia(ScrollPane ViewFrecuencia) {
        this.ViewFrecuencia = ViewFrecuencia;
    }

    public ScrollPane getViewCodificacion() {
        return ViewCodificacion;
    }

    public void setViewCodificacion(ScrollPane ViewCodificacion) {
        this.ViewCodificacion = ViewCodificacion;
    }

    @Override
    public String toString() {
        return "Historial{" + "fehca=" + fehca + ", rutaOrigen=" + rutaOrigen + ", rutaDestino=" + rutaDestino + ", manera=" + manera + ", mapaCodificar=" + mapaCodificar + ", mapaDeCodificar=" + mapaDeCodificar + '}';
    }

}
