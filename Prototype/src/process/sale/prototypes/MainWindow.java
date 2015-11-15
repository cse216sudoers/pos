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

import java.util.ArrayList;
import javafx.scene.Node;

/**
 *
 * @author peterschaedler
 */
public class MainWindow {
    private Stage stage;
    private Scene scene;
    private GridPane grid;
    private TextArea console;
    private boolean powerState = true;
    private ArrayList<Node> itemsOnScreen;
    
    /**
     *
     */
    public MainWindow() {
        super();
        grid = new GridPane();
        scene = new Scene(grid);
        stage = new Stage();
        itemsOnScreen = new ArrayList<>();
        initWindow();
    }
    
    /**
     *
     */
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
        Button powerButton = new Button("POWER");
        powerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                togglePower();
            }
        });
        
        Button saleLabel = new Button("Sale");
        saleLabel.setMaxWidth(Double.MAX_VALUE);
        itemsOnScreen.add(saleLabel);
        saleLabel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                displayProcessSale();
            }
        });
        
        Button rentalLabel = new Button("Rental");
        rentalLabel.setMaxWidth(Double.MAX_VALUE);
        itemsOnScreen.add(rentalLabel);
        rentalLabel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                displayProcessRental();
            }
        });
        
        Button returnLabel = new Button("Return");
        returnLabel.setMaxWidth(Double.MAX_VALUE);
        itemsOnScreen.add(returnLabel);
        returnLabel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                displayProcessReturn();
            }
        });
        
        // Add buttons to grid
        buttonBox.getChildren().addAll(powerButton, saleLabel, rentalLabel, returnLabel);
        grid.add(buttonBox, 0, 0);
        
        // Create text area for writing receipts and things
        console = new TextArea("Sudoers Point of Sale System");
        console.setPrefColumnCount(40);
        console.setPrefRowCount(40);
        console.setEditable(false);
        grid.add(console, 1, 0);
        itemsOnScreen.add(console);
        
        // Show the window
        stage.show();
        
        // Default to showing process sale
        displayProcessSale();
    }
    
    /**
     *
     * @param text
     */
    public void writeToConsole(String text) {
        console.appendText("\n\n" + text);
    }
    
    /**
     *
     */
    public void togglePower() {
        // If system is currently on, turn off
        if (powerState) {
            disableAll(true);
            powerState = false;
        }
        // If currently off, turn on and reset to sale
        else {
            disableAll(false);
            powerState = true;
            displayProcessSale();
        }
    }
    
    /**
     *
     * @param disable
     */
    public void disableAll(boolean disable) {
        for (Node node : itemsOnScreen) {
            node.setDisable(disable);
        }
    }
    
    /**
     *
     */
    public void displayProcessSale() {
        // TODO: Add controls for process sale
        writeToConsole("A sale is totally in progress.");
        
        // Needs stuff for add item, quantity, suspend sale, user mgmt if admin
        // Close sale - go for payment (separate window for accepting payment)
        GridPane inputArea = new GridPane();
        // add stuff
    }
    
    /**
     *
     */
    public void displayProcessRental() {
        // TODO
        writeToConsole("A rental is totally in progress.");
    }
    
    /**
     *
     */
    public void displayProcessReturn() {
        // TODO
        writeToConsole("A return is totally in progress.");
    }
}
