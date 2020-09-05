// 
// Decompiled by Procyon v0.5.36
// 

package Principal;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Pos;

public class ProyectoParcial2 extends Application
{
    public void start(final Stage primaryStage) {
        ElegirArchivo Ventana = new ElegirArchivo();
        Scene scene = new Scene(Ventana, 1000, 1000);
        Ventana.setAlignment(Pos.CENTER);
        primaryStage.setTitle("Inicio");
        primaryStage.setScene(scene);
        primaryStage.show();  
        primaryStage.setFullScreen(true);
    }
    
    public static void main(final String[] args) {
        launch(args);
    }
}
