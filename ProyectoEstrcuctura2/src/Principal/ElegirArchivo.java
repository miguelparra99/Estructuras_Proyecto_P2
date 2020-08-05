// 
// Decompiled by Procyon v0.5.36
// 

package Principal;

import javafx.stage.Window;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import javafx.scene.Node;
import Apoyo.HuffmanInfo;
import Tree.BinaryTree;
import java.util.PriorityQueue;
import java.util.Hashtable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ElegirArchivo extends VBox
{
    private Button BttnArchivo;
    private Button BttnFrecuencia;
    private Button BttnCodificar;
    private TextField Ruta;
    private TextField Ruta1;
    private Hashtable<String, Integer> map;
    private PriorityQueue<BinaryTree<HuffmanInfo>> ColaPrioridad;
    private BinaryTree<HuffmanInfo> ArbolHuffman;
    
    public ElegirArchivo() {
        this.Llenar();
    }
    
    public void Llenar() {
        this.BttnArchivo = new Button("Buscar Archivo");
        this.BttnFrecuencia = new Button("frecuencia");
        this.BttnCodificar = new Button("Generar archivo codificado");
        this.Ruta = new TextField();
        this.Ruta1 = new TextField();
        this.Ruta.setEditable(false);
        this.getChildren().addAll(BttnArchivo,Ruta, BttnFrecuencia );
        this.BttnArchivo.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar archivo Txt");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[] { new FileChooser.ExtensionFilter("TXT", new String[] { "*.txt*" }) });
             Window stage = null;
             File archivo = fileChooser.showOpenDialog(stage);
            if (archivo != null) {
                this.Ruta.setText(archivo.getAbsolutePath());
                System.out.println("Texto del archivo");
            }
            else {
                System.out.println("Elija un archivo txt");
            }
        });
        BttnFrecuencia.setOnAction(evente -> {
            Label asunto = new Label("Ingrese la ruta donde guardara el .txt");
            System.out.println(Ruta.getText());
            getChildren().addAll(Frecuencias(ContenidoTxt(Ruta.getText())),asunto,Ruta1,BttnCodificar);
            LlenarCola();
            LlenarArbol();
        });
        this.BttnCodificar.setOnAction(event -> this.escribir(this.Ruta1.getText()));
    }
    public String ContenidoTxt(String ruta) {
        String texto = " ";
        String linea = " ";
        try {
            File archivo = new File(ruta);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                texto = texto + linea + " ";
            }
            br.close();
            fr.close();
        }
        catch (Exception ex) {}
        return texto;
    }
    
    public GridPane Frecuencias(String texto) {
        GridPane tabla = new GridPane();
        tabla.setMinSize(5.0, 5.0);
        map = new Hashtable<String, Integer>();
         char[] arrayTexto = texto.toCharArray();
        for (int i = 0; i < arrayTexto.length; ++i) {
            final String clave = Character.toString(arrayTexto[i]);
            if (map.containsKey(clave)) {
                map.get(clave);
                map.put(clave, map.get(clave) + 1);
            }
            else {
                map.put(clave, 1);
            }
        }
        int j = 0;
        for ( String key :map.keySet()) {
             Label clave2 = new Label(key);
             Label valor = new Label(map.get(key).toString());
            tabla.add((Node)clave2, j, 0);
            tabla.add((Node)valor, j, 1);
            ++j;
        }
        for (int k = 0; k < this.map.keySet().size(); ++k) {
            final ColumnConstraints column = new ColumnConstraints(25.0);
            tabla.getColumnConstraints().add(column);
        }
        for (int k = 0; k < this.map.keySet().size(); ++k) {
            final RowConstraints row = new RowConstraints(25.0);
            tabla.getRowConstraints().add(row);
        }
        return tabla;
    }
    
    public void escribir(final String ruta) {
        try {
            final String contenido = "Contenido de ejemplo";
            final File file = new File(ruta + "/Texto123.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            final FileWriter fw = new FileWriter(file);
            final BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
            System.out.println("archivo creado");
        }
        catch (Exception e) {
            System.out.println("archivo no creado");
        }
    }
    
    public void LlenarCola() {
        ColaPrioridad=new PriorityQueue<>((b1,b2)->{return b1.getRoot().getContent().getFrecuencia() - b2.getRoot().getContent().getFrecuencia();});
        map.forEach((k, v) -> {
            ColaPrioridad.add((new BinaryTree(new HuffmanInfo(k,v))));
        });
    }
    
    public void LlenarArbol() {
        while (!ColaPrioridad.isEmpty()) {
            if (ColaPrioridad.size() > 1) {
                BinaryTree<HuffmanInfo> menorFrecuencia = ColaPrioridad.remove();
                BinaryTree<HuffmanInfo> mayorFrecuencia = ColaPrioridad.remove();
                String clave = mayorFrecuencia.getRoot().getContent().getText() + menorFrecuencia.getRoot().getContent().getText();
                int valor = mayorFrecuencia.getRoot().getContent().getFrecuencia() + menorFrecuencia.getRoot().getContent().getFrecuencia();
                BinaryTree<HuffmanInfo> arbol = new BinaryTree(new HuffmanInfo(clave, valor));
                arbol.setLeft(menorFrecuencia);
                arbol.setRight(mayorFrecuencia);
                ColaPrioridad.add(arbol);
            }
            else {
                ArbolHuffman = ColaPrioridad.remove();
            }
        }
    }
}
