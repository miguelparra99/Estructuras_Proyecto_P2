// 
// Decompiled by Procyon v0.5.36
// 

package Principal;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

public class ProyectoParcial2 extends Application
{
    public void start(final Stage primaryStage) {
        final ElegirArchivo Ventana = new ElegirArchivo();
        final Scene scene = new Scene(Ventana, 600, 600);
        primaryStage.setTitle("Inicio");
        primaryStage.setScene(scene);
        primaryStage.show();
       
    }
    
    public static void main(final String[] args) {
        launch(args);
    }
}
