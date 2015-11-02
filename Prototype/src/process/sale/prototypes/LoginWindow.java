package process.sale.prototypes;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private RegisterController registerController;
    
    public LoginWindow() {
        super();
        RegisterManager registerManager = RegisterManager.getInstance();
        Register register = new Register(registerManager.getNextId());
        registerManager.addRegister(register);
        registerController = new RegisterController(register);
        ProductCatalog.getCatalog();
    }
    
    @Override
    public void start(Stage primaryStage) {
        // This code is basically taken from this Java example: http://docs.oracle.com/javafx/2/get_started/form.htm
        
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
                Cashier cashier = verifyLogin(username, password);
                    if(cashier != null) {
                        // TODO: move on to the next part - show the sale chooser
                        System.out.println("Login confirmed!");
                        registerController.getRegister().setCashier(cashier);
                        registerController.start();
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
                    Cashier cashier = verifyLogin(username, password);
                    if(cashier != null) {
                        // TODO: move on to the next part - show the sale chooser
                        System.out.println("Login confirmed!");
                        registerController.getRegister().setCashier(cashier);
                        registerController.start();
                    }
                }
            }
        });
        
        // Go!
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Cashier verifyLogin(String username, String password) {
        if (registerController.verifyUsername(username)){
            return registerController.verifyPassword(username, password);
        }
        // TODO: Add error handling for login
        return null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
