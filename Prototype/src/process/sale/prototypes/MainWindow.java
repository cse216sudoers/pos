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
    
    public MainWindow() {
        super();
        grid = new GridPane();
        scene = new Scene(grid, 800, 600);
        stage = new Stage();
        initWindow();
    }
    
    public void initWindow() {
        stage.setTitle("Sudoers Point of Sale System");
        stage.setScene(scene);
        stage.show();
    }
    
    public void displayProcessSale() {
        
    }
}
