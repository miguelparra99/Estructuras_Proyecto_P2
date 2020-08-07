/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apoyo;

import java.util.Date;
import java.util.Map;
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
    private Map<String, String> mapaDeCodificar;
    private GridPane ViewFrecuencia;
    private GridPane ViewCodificacion; 
    public Historial(Date fehca, String rutaOrigen, String rutaDestino, String manera, Map<String, HuffmanInfo> mapaCodificar,GridPane ViewFrecuencia,GridPane ViewCodificacion) {
        this.fehca = fehca;
        this.rutaOrigen = rutaOrigen;
        this.rutaDestino = rutaDestino;
        this.manera = manera;
        this.mapaCodificar = mapaCodificar;
        this.mapaDeCodificar = null;
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

    public Map<String, String> getMapaDeCodificar() {
        return mapaDeCodificar;
    }

    public void setMapaDeCodificar(Map<String, String> mapaDeCodificar) {
        this.mapaDeCodificar = mapaDeCodificar;
    }

    public GridPane getViewFrecuencia() {
        return ViewFrecuencia;
    }

    public void setViewFrecuencia(GridPane ViewFrecuencia) {
        this.ViewFrecuencia = ViewFrecuencia;
    }

    public GridPane getViewCodificacion() {
        return ViewCodificacion;
    }

    public void setViewCodificacion(GridPane ViewCodificacion) {
        this.ViewCodificacion = ViewCodificacion;
    }

    @Override
    public String toString() {
        return "Historial{" + "fehca=" + fehca + ", rutaOrigen=" + rutaOrigen + ", rutaDestino=" + rutaDestino + ", manera=" + manera + ", mapaCodificar=" + mapaCodificar + ", mapaDeCodificar=" + mapaDeCodificar + '}';
    }

}
