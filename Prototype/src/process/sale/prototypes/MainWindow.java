/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author peterschaedler
 */
public class MainWindow {
    private Stage stage;
    private Scene scene;
    private GridPane grid;
    private TextArea console;
    
    public MainWindow() {
        super();
        grid = new GridPane();
        scene = new Scene(grid);
        stage = new Stage();
        initWindow();
    }
    
    public void initWindow() {
        // Settings for stage
        stage.setTitle("Sudoers Point of Sale System");
        stage.setScene(scene);
        
        // Default grid settings
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        // Left navigation pane
        GridPane navGrid = new GridPane();
        grid.add(navGrid, 0, 0);
        
        // VBox for Button sizing
        VBox buttonBox = new VBox();
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(0, 15, 0, 0));
        
        // Create buttons
        Button saleLabel = new Button("SALE");
        saleLabel.setMaxWidth(Double.MAX_VALUE);
        
        Button rentalLabel = new Button("RENTAL");
        rentalLabel.setMaxWidth(Double.MAX_VALUE);
        
        Button returnLabel = new Button("RETURN");
        returnLabel.setMaxWidth(Double.MAX_VALUE);
        
        // Add buttons to grid
        buttonBox.getChildren().addAll(saleLabel, rentalLabel, returnLabel);
        grid.add(buttonBox, 0, 0);
        
        // Create text area for writing receipts and things
        console = new TextArea("Sudoers Point of Sale System\n");
        console.setPrefColumnCount(40);
        console.setPrefRowCount(40);
        console.setEditable(false);
        grid.add(console, 1, 0);
        
        // Show the window
        stage.show();
    }
    
    public void displayProcessSale() {
        
    }
}
