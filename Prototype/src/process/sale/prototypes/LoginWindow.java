/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import javafx.application.Application;
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
public class LoginWindow extends Application {
    private Register register;
    private Stage stage;
    
    public LoginWindow() {
        super();
        register = new Register();
    }
    
    @Override
    public void start(Stage primaryStage) {
        // This code is basically taken from this Java example: http://docs.oracle.com/javafx/2/get_started/form.htm
        // This code is also very not-modular. Could be improved a lot.
        
        stage = primaryStage;
        
        // Create new grid layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);  // Align by center
        grid.setHgap(10);               // Set margins on window
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Scene scene = new Scene(grid, 300, 275);    // Add scene (basically the window panel
        
        // Add text title
        Text scenetitle = new Text("The Sudoers");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        // Labels and input fields
        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        final TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        final PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        
        // Button for sign in
        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        
        // Button Event Handler
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // Verify information
                String username = userTextField.getText();
                String password = pwBox.getText();
                // TODO: Add error handling
                if (verifyLogin(username, password)) {
                    System.out.println("Login confirmed!");
                    continueToMainWindow();
                }
            }
        });
        
        // Set ENTER key Event Handler on password box
        pwBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.ENTER) {
                    String username = userTextField.getText();
                    String password = pwBox.getText();
                    // TODO: Add error handling
                    if (verifyLogin(username, password)) {
                        System.out.println("Login confirmed!");
                        continueToMainWindow();
                    }
                }
            }
        });
        
        // Go!
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private boolean verifyLogin(String username, String password) {
        if (register.verifyUsernameText(username)){
            if(register.verifyPasswordText(username, password)) {
                return true;
            }
        }
        // TODO: Add error handling for login
        return false;
    }
    
    private void continueToMainWindow() {
        // Close this window
        stage.close();
        
        // Open up main window
        MainWindow main = new MainWindow();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
