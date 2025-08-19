import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class AiTaskManagement extends Application {

    private TableView<Task> table;
    private ObservableList<Task> taskList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("✨ AI Task Management System ✨");

        // Task list
        taskList = FXCollections.observableArrayList();

        // Table
        table = new TableView<>();
        table.setItems(taskList);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Task, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Task, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Task, String> priorityCol = new TableColumn<>("Priority");
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));

        table.getColumns().addAll(titleCol, descCol, priorityCol);

        // Input fields with labels
        Label titleLbl = new Label("Task Title:");
        TextField titleInput = new TextField();
        titleInput.setPromptText("Enter title...");

        Label descLbl = new Label("Description:");
        TextField descInput = new TextField();
        descInput.setPromptText("Enter description...");

        Label priorityLbl = new Label("Priority:");
        ComboBox<String> priorityInput = new ComboBox<>();
        priorityInput.getItems().addAll("High", "Medium", "Low");
        priorityInput.setPromptText("Select priority");

        Button addButton = new Button("➕ Add Task");
        addButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        addButton.setOnAction(e -> {
            String title = titleInput.getText();
            String desc = descInput.getText();
            String priority = priorityInput.getValue();

            if (!title.isEmpty() && priority != null) {
                taskList.add(new Task(title, desc, priority));
                titleInput.clear();
                descInput.clear();
                priorityInput.setValue(null);
            } else {
                showAlert("Error", "Task title and priority are required!");
            }
        });

        Button deleteButton = new Button("🗑 Delete Task");
        deleteButton.setStyle("-fx-background-color: #E53935; -fx-text-fill: white; -fx-font-weight: bold;");
        deleteButton.setOnAction(e -> {
            Task selectedTask = table.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                taskList.remove(selectedTask);
            } else {
                showAlert("Error", "Please select a task to delete.");
            }
        });

        // Profession Input
        Label professionLbl = new Label("Profession:");
        ComboBox<String> professionInput = new ComboBox<>();
        professionInput.getItems().addAll("Software Developer", "Doctor", "Teacher", "Student", "Engineer");
        professionInput.setPromptText("Select Profession");

        Button suggestButton = new Button("🤖 Suggest Tasks");
        suggestButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");
        suggestButton.setOnAction(e -> {
            String profession = professionInput.getValue();
            if (profession != null) {
                List<Task> suggestedTasks = getSuggestedTasks(profession);
                taskList.addAll(suggestedTasks);
            } else {
                showAlert("Error", "Please select a profession to get suggestions.");
            }
        });

        // Layout for inputs
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(15));

        inputGrid.add(titleLbl, 0, 0);
        inputGrid.add(titleInput, 1, 0);
        inputGrid.add(descLbl, 0, 1);
        inputGrid.add(descInput, 1, 1);
        inputGrid.add(priorityLbl, 0, 2);
        inputGrid.add(priorityInput, 1, 2);
        inputGrid.add(addButton, 0, 3);
        inputGrid.add(deleteButton, 1, 3);
        inputGrid.add(professionLbl, 0, 4);
        inputGrid.add(professionInput, 1, 4);
        inputGrid.add(suggestButton, 1, 5);

        // Split screen (Left = input, Right = table)
        HBox mainLayout = new HBox(20, inputGrid, table);
        mainLayout.setPadding(new Insets(15));
        mainLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainLayout, 950, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private List<Task> getSuggestedTasks(String profession) {
        List<Task> suggestions = new ArrayList<>();

        switch (profession) {
            case "Software Developer":
                suggestions.add(new Task("Code Review", "Review pull requests", "High"));
                suggestions.add(new Task("Debugging", "Fix reported bugs", "Medium"));
                suggestions.add(new Task("Learning", "Read about new frameworks", "Low"));
                break;
            case "Doctor":
                suggestions.add(new Task("Check Patients", "Morning patient rounds", "High"));
                suggestions.add(new Task("Research", "Study new treatments", "Medium"));
                suggestions.add(new Task("Reports", "Complete medical records", "High"));
                break;
            case "Teacher":
                suggestions.add(new Task("Prepare Lecture", "Create slides and notes", "High"));
                suggestions.add(new Task("Check Homework", "Evaluate student work", "Medium"));
                suggestions.add(new Task("Meeting", "Discuss with faculty", "Low"));
                break;
            case "Student":
                suggestions.add(new Task("Study", "Revise chapters for exam", "High"));
                suggestions.add(new Task("Assignments", "Complete pending homework", "High"));
                suggestions.add(new Task("Sports", "Participate in practice", "Low"));
                break;
            case "Engineer":
                suggestions.add(new Task("Project Work", "Work on current project", "High"));
                suggestions.add(new Task("Team Meeting", "Attend project discussion", "Medium"));
                suggestions.add(new Task("Learning", "Explore new engineering tools", "Low"));
                break;
        }

        return suggestions;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Task class
    public static class Task {
        private String title;
        private String description;
        private String priority;

        public Task(String title, String description, String priority) {
            this.title = title;
            this.description = description;
            this.priority = priority;
        }

        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getPriority() { return priority; }
    }
}
