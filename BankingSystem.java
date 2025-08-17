import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BankingSystem extends Application {

    private double balance = 1000.00; // Initial balance
    private TextArea transactionHistory;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Smart Banking System");

        // ==== Login Screen ====
        Label loginTitle = new Label("Welcome to Smart Bank");
        loginTitle.setFont(new Font("Arial", 28));
        loginTitle.setTextFill(Color.WHITE);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginBtn = new Button("Login");
        loginBtn.setStyle("-fx-background-color: #00c853; -fx-text-fill: white; -fx-font-weight: bold;");

        VBox loginLayout = new VBox(15, loginTitle, usernameField, passwordField, loginBtn);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setPadding(new Insets(20));
        loginLayout.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene loginScene = new Scene(loginLayout, 600, 400);

        // ==== Dashboard ====
        Label balanceLabel = new Label("Balance: $ " + balance);
        balanceLabel.setFont(new Font("Arial", 22));

        Button depositBtn = new Button("Deposit");
        Button withdrawBtn = new Button("Withdraw");
        Button transferBtn = new Button("Transfer");
        Button historyBtn = new Button("View Transactions");

        HBox actionButtons = new HBox(15, depositBtn, withdrawBtn, transferBtn, historyBtn);
        actionButtons.setAlignment(Pos.CENTER);

        transactionHistory = new TextArea();
        transactionHistory.setEditable(false);
        transactionHistory.setPrefHeight(200);

        VBox dashboardLayout = new VBox(20, balanceLabel, actionButtons, transactionHistory);
        dashboardLayout.setAlignment(Pos.CENTER);
        dashboardLayout.setPadding(new Insets(20));

        Scene dashboardScene = new Scene(dashboardLayout, 800, 500);

        // ==== Actions ====
        loginBtn.setOnAction(e -> {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Please enter username and password!");
            } else {
                primaryStage.setScene(dashboardScene);
            }
        });

        depositBtn.setOnAction(e -> performTransaction("Deposit", balanceLabel));
        withdrawBtn.setOnAction(e -> performTransaction("Withdraw", balanceLabel));
        transferBtn.setOnAction(e -> performTransaction("Transfer", balanceLabel));
        historyBtn.setOnAction(e -> showAlert(Alert.AlertType.INFORMATION, "Transaction History", transactionHistory.getText()));

        // ==== Show Stage ====
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void performTransaction(String type, Label balanceLabel) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(type + " Money");
        dialog.setHeaderText("Enter amount to " + type);
        dialog.setContentText("Amount:");

        dialog.showAndWait().ifPresent(amountStr -> {
            try {
                double amount = Double.parseDouble(amountStr);
                if (type.equals("Deposit")) {
                    balance += amount;
                    transactionHistory.appendText("Deposited: $" + amount + "\n");
                } else if (type.equals("Withdraw")) {
                    if (amount <= balance) {
                        balance -= amount;
                        transactionHistory.appendText("Withdrew: $" + amount + "\n");
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Insufficient Funds", "You don't have enough balance!");
                        return;
                    }
                } else if (type.equals("Transfer")) {
                    if (amount <= balance) {
                        balance -= amount;
                        transactionHistory.appendText("Transferred: $" + amount + "\n");
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Insufficient Funds", "You don't have enough balance!");
                        return;
                    }
                }
                balanceLabel.setText("Balance: $ " + balance);
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid number!");
            }
        });
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
