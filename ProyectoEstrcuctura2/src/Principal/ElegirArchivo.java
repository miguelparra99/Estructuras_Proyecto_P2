// 
// Decompiled by Procyon v0.5.36
// 
package Principal;

import Apoyo.Historial;
import javafx.stage.Window;
import javafx.stage.FileChooser;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import javafx.scene.Node;
import Apoyo.HuffmanInfo;
import Tree.BinaryTree;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Hashtable;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ElegirArchivo extends VBox {

    private Button BttnArchivo;
    private Button BttnFrecuencia;
    private Button BttnCodificar;
    private Button BttnCode;
    private Button BttnCodePalabra;
    private Button BttnDecodificarPalabra;
    private Button BttnHistorial;
    private Button BttnVolverCargar;
    private TextField Ruta;
    private TextField txtPalabra;
    private TextField txtBits;
    private TextField Ruta1;
    private GridPane TablaCodigos;
    private GridPane TablaFrecuencia;
    private PriorityQueue<BinaryTree<HuffmanInfo>> ColaPrioridad;
    private BinaryTree<HuffmanInfo> ArbolHuffman;
    private Hashtable<String, Historial> DictHistorial;
    private Hashtable<String, HuffmanInfo> mapOfAll;
    private Hashtable<String, ArrayList<String>> mapParentChild;
    private Hashtable<String, Integer> map;
    private Hashtable<String, HuffmanInfo> mapOfDecod;
    private BorderPane codifica;
    private HBox derSuperior;
    private HBox derInferior;
    private ChoiceBox cb;

    public ElegirArchivo() {
        DictHistorial = new Hashtable<>();
        this.Llenar();
    }

    public ElegirArchivo(String a) {

    }

    public void Llenar() {
        this.derSuperior=new HBox();
        this.derInferior=new HBox();
        this.setAlignment(Pos.CENTER);
        this.BttnArchivo = new Button("Buscar Archivo");
        this.BttnFrecuencia = new Button("frecuencia");
        this.BttnCodificar = new Button("Descargar archivo codificado");
        this.BttnCode = new Button("Generar codificacion");
        this.BttnCodePalabra = new Button("Codifica la palabra");
        this.BttnDecodificarPalabra = new Button("Decodificar");
        this.BttnHistorial = new Button("Ir al historial");
        this.BttnVolverCargar = new Button("Cargar nuevo archio");
        cb = new ChoiceBox();
        cb.setTooltip(new Tooltip("Selecciona el tipo de codificacion"));
        cb.setItems(FXCollections.observableArrayList(
                "Mayor frecuencia + bits",
                new Separator(), "Mayor frecuencia - bits"));
        cb.getSelectionModel().select(1);
        this.txtPalabra = new TextField();
        this.Ruta = new TextField();
        this.Ruta1 = new TextField();
        this.txtBits = new TextField();
        this.Ruta.setEditable(false);
        this.getChildren().addAll(BttnArchivo, Ruta, cb, BttnFrecuencia);
        this.BttnArchivo.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar archivo Txt");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("TXT", new String[]{"*.txt*"})});
            Window stage = null;
            File archivo = fileChooser.showOpenDialog(stage);
            if (archivo != null) {
                this.Ruta.setText(archivo.getAbsolutePath());
                System.out.println("Texto del archivo");
            } else {
                System.out.println("Elija un archivo txt");
            }

        });
        BttnFrecuencia.setOnAction(event -> {

            if (txtPalabra.toString() == null) {
                HBox message = new HBox();
                Label msg = new Label("Ingrese una palabra");
                message.getChildren().add(msg);
                message.setAlignment(Pos.CENTER);
                this.getChildren().add(message);
            } else {

                TablaFrecuencia = Frecuencias(ContenidoTxt(Ruta.getText()));
                getChildren().addAll(TablaFrecuencia, BttnCode);
                LlenarCola();
                LlenarArbol();
                mapOfAll = ArbolHuffman.CodificarArbolTodos(map);
                mapOfDecod = ArbolHuffman.DeCodificarArbolTodos(mapOfAll);
                Ruta.setEditable(false);
                

            }
        });
        BttnCode.setOnAction(event -> {
            Label asunto = new Label("Ingrese la ruta donde guardara el .txt");
            codifica = new BorderPane();
            HBox nodo=new HBox();
           nodo.getChildren().addAll(derSuperior,derInferior);
            codifica.setRight(nodo);
            TablaCodigos = TablaCodigos();
            codifica.setLeft(TablaCodigos);
            VBox a = new VBox();
            a.getChildren().addAll(new Label("Ingresa letras del dominio y codificala!"), BttnCodePalabra, txtPalabra, new Label("Ingrese los bits del dominio y decodificala"), BttnDecodificarPalabra, txtBits);
            codifica.setCenter(a);
            HBox b = new HBox();
            b.setAlignment(Pos.CENTER);
            b.getChildren().addAll(BttnHistorial, BttnCodificar, BttnVolverCargar);
            getChildren().addAll(codifica, asunto, Ruta1, b);
            BttnCode.setVisible(false);
        });

        BttnCodificar.setOnAction(event -> {
            System.out.println("click");
            String RUTADESTINO = this.Ruta1.getText();
            String Bits = codificarArchivo(mapOfAll, ContenidoTxt(Ruta.getText()));
            escribir(RUTADESTINO, Bits);
            escribirArchivoBinario(RUTADESTINO, Bits);
            escribirArchivoArbol(RUTADESTINO);
            String source = Ruta.getText().replace('\\', '/');
            String[] Archivo = source.split("/");
            String source1 = Ruta1.getText().replace('\\', '/');
            DictHistorial.put(Archivo[Archivo.length - 1], new Historial(new Date(), source, source1, cb.getSelectionModel().getSelectedItem().toString(), mapOfAll,mapOfDecod, TablaFrecuencia, TablaCodigos));
            System.out.println("agregado con exito");
        });

        BttnCodePalabra.setOnAction(event -> {
            Label derecha = new Label(codificarArchivo(mapOfAll, txtPalabra.getText()));
            derSuperior.getChildren().add(derecha);
        });
        BttnDecodificarPalabra.setOnAction(event -> {
            Label izquierda = new Label(decodificarbits(mapOfDecod,txtBits.getText()));
            derInferior.getChildren().add(izquierda);
        });

        BttnVolverCargar.setOnAction(event -> {
            this.getChildren().clear();
            Llenar();
        });
        BttnHistorial.setOnAction(event -> {
            ViewHistorial Histo = new ViewHistorial(DictHistorial);

            Scene scene = new Scene(Histo, 600, 600);
            Stage ad = new Stage();
            ad.setScene(scene);
            ad.show();

        });
    }

    //ESTE METODO ME LLENA UN GRINDPANE CON EL CARACTER Y SU BITS SEGUN EL ARBOL DE BITS GENERADO
    public GridPane TablaCodigos() {
        GridPane tabla = new GridPane();
        tabla.setAlignment(Pos.CENTER);
        mapOfAll.forEach((k, v) -> {
            System.out.println("clave" + k + "contenido" + v.toString());
        });
        int j = 0;
        for (String key : mapOfAll.keySet()) {
            Label clave2 = new Label(key);
            Label valor = new Label(mapOfAll.get(key).getBit());
            tabla.add(clave2, 0, j);
            tabla.add(valor, 1, j);
            ++j;
        }

        return tabla;

    }
    //ME DEVULVE EL TEXTO QUE EXISTIA DENTRO DE UN ARCHIVO

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
        } catch (Exception ex) {
        }
        return texto;
    }
//RECIBE UN TEXTO Y LE SACA LA FRECUENCIA Y LOS UBICA EN EL GRINDPANE CARACTER:FRECUENCIA

    public GridPane Frecuencias(String texto) {
        GridPane tabla = new GridPane();
        tabla.setMinSize(5, 5);
        map = new Hashtable<String, Integer>();
        char[] arrayTexto = texto.toCharArray();
        for (int i = 0; i < arrayTexto.length; ++i) {
            String clave = Character.toString(arrayTexto[i]);
            if (map.containsKey(clave)) {
                map.get(clave);
                map.put(clave, map.get(clave) + 1);
            } else {
                map.put(clave, 1);
            }
        }
        int j = 0;
        for (String key : map.keySet()) {
            Label clave2 = new Label(key);
            Label valor = new Label(map.get(key).toString());
            tabla.add((Node) clave2, j, 0);
            tabla.add((Node) valor, j, 1);
            ColumnConstraints column = new ColumnConstraints(20.0);
            tabla.getColumnConstraints().add(column);
            ++j;
        }

        return tabla;
    }

    //ESTE METODO ME GENERA UN ARCHIVO DE TEXTO(BITS) Y LOS GUARDA DONDE LA PERSONA DESEA   
    //  RECIBE LA RUTA DONDE SE GUARDARA EL ARCHIVO Y EL CONTENIDO QUE TENDRA ESTE
    public void escribir(String ruta, String contenido) {
        try {
            File file = new File(ruta + "/Codificado.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
            System.out.println("archivo creado");
        } catch (Exception e) {
            System.out.println("archivo no creado");
        }
    }
    //METODO DE APOYO QUE RECORRE EL MAPA DE FRECUENCIAS PARA UTILIZARLOS COMO NODOS EN UN BINARY TREE 
    //Y EL BINARYTREE SE LO GUARDA EN UNA COLA DE PRIORIDAD QUE LO ORDENA DE MENO A MAYOR FRECUENCIA.

    public void LlenarCola() {
        if (!cb.getSelectionModel().getSelectedItem().toString().equals("Mayor frecuencia + bits")) {
            ColaPrioridad = new PriorityQueue<>((b1, b2) -> {
                return b1.getRoot().getContent().getFrecuencia() - b2.getRoot().getContent().getFrecuencia();
            });
        } else {
            ColaPrioridad = new PriorityQueue<>((b1, b2) -> {
                return b2.getRoot().getContent().getFrecuencia() - b1.getRoot().getContent().getFrecuencia();
            });
        }
        map.forEach((k, v) -> {
            ColaPrioridad.add((new BinaryTree(new HuffmanInfo(k, v))));
        });
    }

    //Llena el ArbolHuffman y Llena un mapa relacion <Padre,hijo> como tiene varios hijos. se usa el ArrayList
    public void LlenarArbol() {
        mapParentChild = new Hashtable<>();
        while (!ColaPrioridad.isEmpty()) {
            BinaryTree<HuffmanInfo> arbol;
            if (ColaPrioridad.size() >= 2) {
                BinaryTree<HuffmanInfo> menorFrecuencia = ColaPrioridad.remove();
                BinaryTree<HuffmanInfo> mayorFrecuencia = ColaPrioridad.remove();
                String clave = mayorFrecuencia.getRoot().getContent().getText() + menorFrecuencia.getRoot().getContent().getText();
                if (!cb.getSelectionModel().getSelectedItem().toString().equals("Mayor frecuencia + bits")) {
                    arbol = new BinaryTree(new HuffmanInfo(clave, 0));
                } else {
                    arbol = new BinaryTree(new HuffmanInfo(clave, mayorFrecuencia.getRoot().getContent().getFrecuencia() + menorFrecuencia.getRoot().getContent().getFrecuencia()));

                }
                ArrayList<String> arr = new ArrayList<>();
                arr.add(menorFrecuencia.getRoot().getContent().getText());
                arr.add(mayorFrecuencia.getRoot().getContent().getText());
                mapParentChild.put(arbol.getRoot().getContent().getText(), arr);
                arbol.setLeft(menorFrecuencia);
                arbol.setRight(mayorFrecuencia);
                ColaPrioridad.add(arbol);
            } else {
                BinaryTree<HuffmanInfo> nodo = ColaPrioridad.remove();
                ArbolHuffman = nodo;
                ArrayList<String> arr = new ArrayList<>();
                arr.add(nodo.getLeft().getRoot().getContent().getText());
                arr.add(nodo.getRight().getRoot().getContent().getText());
                mapParentChild.put(nodo.getRoot().getContent().getText(), arr);
            }
        }
    }

    //RECIBE UN TEXTO Y ME DEVUELVE UNA CADENA DE BITS 
    public String codificarArchivo(Hashtable<String, HuffmanInfo> mapa, String texto) {

        String codificado = "";
        char[] arrayTexto = texto.toCharArray();
        for (int i = 0; i < arrayTexto.length; ++i) {
            codificado += mapa.get(Character.toString(arrayTexto[i])).getBit();
        }
        return codificado;
    }

    public String decodificarbits(Hashtable<String, HuffmanInfo> mapa, String bits) {
        String resultado="";
        String bit ="";
        for (int i = 1; i <= bits.length(); i++) {
            if (Integer.parseInt(bits.substring((i - 1), i)) == 1) {
                bit+=bits.substring((i - 1), i);
                if(bit.length()==mapa.size()-1){
                    resultado+=mapa.get(bit).getText();
                    bit="";
                }
            }else{
                bit+=bits.substring((i - 1), i);
                resultado+=mapa.get(bit).getText();
                bit="";
            }
        }
        return resultado;
    }

    public void escribirArchivoBinario(String ruta, String code) {
        try {
            File file = new File(ruta + "/CodificadoBianrio1.dat");
            OutputStream ar = new FileOutputStream(file);
            DataOutputStream b = new DataOutputStream(ar);
            b.write(code.getBytes());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void escribirArchivoArbol(String ruta) {
        try {
            File file = new File(ruta + "/Arbol.tree");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("parent,child");
            bw.newLine();
            mapParentChild.forEach((k, v) -> {
                for (String hijo : v) {
                    try {
                        bw.write(k + "," + hijo);
                        bw.newLine();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            });
            bw.close();
            System.out.println("archivo creado");
        } catch (Exception e) {
            System.out.println("archivo no creado");
        }
    }
}
