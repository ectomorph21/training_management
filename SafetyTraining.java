/*
Copyright 2021 R & MS Solutions

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
import java.io.File;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.FileChooser;

public class SafetyTraining extends Application {
    
    Stage window;
    BorderPane layout;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Employee training");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        //System.out.println(primaryScreenBounds);
        //menu
        Menu fileMenu = new Menu("File");
        //menu items
        MenuItem newFile = new MenuItem("Connect...");
//        newFile.setOnAction(e -> System.out.println("Open existing DB"));
        newFile.setOnAction(e -> connect());
        fileMenu.getItems().add(newFile);
        MenuItem openFile = new MenuItem("Open...");
        openFile.setOnAction(e -> openFile());
        fileMenu.getItems().add(openFile);
        fileMenu.getItems().add(new SeparatorMenuItem());
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> closeProgram());
        fileMenu.getItems().add(exit);
        //fileMenu.getItems().add(new MenuItem("Exit..."));
        //edit menu
        Menu editMenu = new Menu("_Edit");
        editMenu.getItems().add(new MenuItem("Cut"));
        editMenu.getItems().add(new MenuItem("Copy"));
        
        MenuItem paste = new MenuItem("Paste...");
        paste.setOnAction(e -> System.out.println("Paste something"));
        paste.setDisable(true);
        editMenu.getItems().add(paste);
        //help menu
        Menu helpMenu = new Menu("Help");
        CheckMenuItem showLines = new CheckMenuItem("Show line numbers");
        showLines.setOnAction(e -> {
            if(showLines.isSelected())
                System.out.println("Program will display line numbers");
            else
                System.out.println("Hiding line numbers");
        });
        CheckMenuItem autoSave = new CheckMenuItem("Enable autosave");
        autoSave.setSelected(true);
        helpMenu.getItems().addAll(showLines,autoSave);
        helpMenu.getItems().add(new SeparatorMenuItem());
        MenuItem info = new MenuItem("Info");
        info.setOnAction(e -> System.out.println("This is where software and Github info will display"));
        helpMenu.getItems().add(info);
        //RadioMenuItems
        Menu difficultyMenu = new Menu("Difficulty");
        ToggleGroup difficultyGroup = new ToggleGroup();
        RadioMenuItem easy = new RadioMenuItem("Easy");
        RadioMenuItem medium = new RadioMenuItem("Medium");
        RadioMenuItem hard = new RadioMenuItem("Hard");
        easy.setToggleGroup(difficultyGroup);
        medium.setToggleGroup(difficultyGroup);
        hard.setToggleGroup(difficultyGroup);
        difficultyMenu.getItems().addAll(easy,medium,hard);
        
        //menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu,editMenu,helpMenu,difficultyMenu);
        //Label & button
        /*
        VBox test = new VBox();
        Label test_label = new Label("For testing");
        test_label.setId("bold-label");
        Button test_button = new Button("Login");
        Button signUp = new Button("Sign up");
        signUp.getStyleClass().add("button-blue");
        
        test.getChildren().addAll(test_label, test_button, signUp);
        */
        layout = new BorderPane();
        layout.setTop(menuBar);
        //layout.setCenter(test);
        Scene scene = new Scene(layout,500,300);
        // Scene scene = new Scene(layout,primaryScreenBounds.getMaxX(),primaryScreenBounds.getMaxY());
        //add custom style sheet
        //scene.getStylesheets().add("training.css");
        window.setScene(scene);
        window.show();
        //window.setFullScreen(true);
    }
    
    private void connect() {
        SqliteDB db = new SqliteDB();
    }
    
    private void openFile() {
        FileChooser open = new FileChooser();
        open.setTitle("Connect to DB");
        open.getExtensionFilters().addAll(new ExtensionFilter("sqlite files","*.sqlite","*.db"));
        File varFile = open.showOpenDialog(null);
        // System.out.println(varFile);
        if (varFile == null) {
            AlertBox.display("File selection error","No file selected");
        }else {
            AlertBox.display("Connected","connected to "+varFile.getName());
        }
    }
    
    private void closeProgram() {
        boolean answer = ConfirmBox.display("Confirm close program","Are you sure you want to close program?");
        if (answer) {
            System.out.println("File is saved.");
            window.close();
        }
    }
}
