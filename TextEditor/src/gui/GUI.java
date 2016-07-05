/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Controller.ActionListenersController;
import IDEEngine.ProgrammingLanguage;
import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class GUI extends Application {
    
    private Scene textEditorScene;
    private Scene IDEScene;
    private SceneEnum whichScene;
    private Stage primaryStage;
    private String textInMainTxtAreaIDEMode;
    private String textInInputTextAreaIDEMode;
    private TextArea outputTextArea;
    private TextArea mainTxtArea;
    private static GUI instance;
    private Button createNewProjectButton;
    private Button compileButton, runButton;
    private Button createNewClassButton;
    
    public ProgrammingLanguage getLanguageSelected() {
        return ProgrammingLanguage.JAVA;
    }
    
    public String getClassName() {
        return JOptionPane.showInputDialog("enter file name");
    }
    
    public void setTextInMainTextArea(String s){
        mainTxtArea.setText(s);
    }
    public void enableTextArea() {
        mainTxtArea.setText("");
        mainTxtArea.setEditable(true);
    }
    
    public void enableCreateClassButton() {
        createNewClassButton.setDisable(false);
    }

    public void enableRunProgramButton() {
        runButton.setDisable(false);
    }

    public void enableCompileProgramButton() {
        compileButton.setDisable(false);
    }
    
    private enum SceneEnum {
        TEXT_EDITOR, IDE
    }
    
    public static GUI getInstance() {
        return instance;
    }
    
    @Override
    public void init() throws Exception {
        whichScene = SceneEnum.TEXT_EDITOR;
        instance = this;
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setCurrentScene();
        primaryStage.setTitle("MonoMono");
        primaryStage.getIcons().add(new Image("pics/icon.png"));
        primaryStage.show();
    }
    
    private void setCurrentScene() {
        if (whichScene.equals(SceneEnum.TEXT_EDITOR)) {
            primaryStage.setScene(getTextEditorScene());
        } else {
            primaryStage.setScene(getIDEScene());
        }
    }
    
    private Scene getIDEScene() {
        
        if (IDEScene != null) {
            return IDEScene;
        }
        BorderPane rootNode = new BorderPane();
        BorderPane topNode = new BorderPane();
        BorderPane rightNode = new BorderPane();
        /*
        set up menu bar and its items
         */
        MenuBar mb = new MenuBar();

        //file menu 
        Menu fileMenu = new Menu("File");
        
        MenuItem newTab = new MenuItem("New", new ImageView("pics/new.gif"));
        newTab.setAccelerator(KeyCombination.keyCombination("shortcut+t"));
        
        MenuItem open = new MenuItem("Open", new ImageView("pics/open.gif"));
        open.setAccelerator(KeyCombination.keyCombination("shortcut+o"));
        
        MenuItem save = new MenuItem("Save", new ImageView("pics/save.gif"));
        save.setAccelerator(KeyCombination.keyCombination("shortcut+s"));
        
        MenuItem close = new MenuItem("Close", new ImageView("pics/Close file.png"));
        close.setAccelerator(KeyCombination.keyCombination("shortcut+w"));
        
        MenuItem closeAll = new MenuItem("Close All", new ImageView("pics/Close file.png"));
        
        fileMenu.getItems().addAll(newTab, open, save, close, closeAll);
        mb.getMenus().add(fileMenu);
        //end file menu

        //edit menu
        Menu edit = new Menu("Edit");
        
        MenuItem undo = new MenuItem("Undo", new ImageView("pics/undo.png"));
        undo.setAccelerator(KeyCombination.keyCombination("shortcut+z"));
        undo.setOnAction((e) -> System.out.println("undo"));
        
        MenuItem redo = new MenuItem("Redo", new ImageView("pics/redo.gif"));
        redo.setAccelerator(KeyCombination.keyCombination("shortcut+y"));
        
        MenuItem cutInMenu = new MenuItem("Cut", new ImageView("pics/cut.gif"));
        cutInMenu.setOnAction((e) -> System.out.println("cutting"));
        
        MenuItem copyInMenu = new MenuItem("Copy", new ImageView("pics/copy.gif"));
        
        MenuItem pasteInMenu = new MenuItem("Paste", new ImageView("pics/paste.gif"));
        
        edit.getItems().addAll(undo, redo, new SeparatorMenuItem(), cutInMenu, copyInMenu, pasteInMenu);
        mb.getMenus().add(edit);
        //end edit menu

        topNode.setTop(mb);
        /*.
        end menu bar
         */

 /*
        set up tool bar
         */
        ToolBar mainToolBar = new ToolBar();

//        Button newTabButton = new Button("", new ImageView("pics/new.gif"));
//        newTabButton.setTooltip(new Tooltip("Open new tab"));
//
//        Button openButton = new Button("", new ImageView("pics/open.gif"));
//        openButton.setTooltip(new Tooltip("Open file"));
//
//        Button saveButton = new Button("", new ImageView("pics/save.gif"));
//        saveButton.setTooltip(new Tooltip("Save file"));
//
//        Button closeButton = new Button("", new ImageView("pics/Close file.png"));
//        closeButton.setTooltip(new Tooltip("close current tab"));
//
//        Button closeAllButton = new Button("", new ImageView("pics/Close file.png"));
//        closeAllButton.setTooltip(new Tooltip("close all tabs"));
        createNewProjectButton = new Button("", new ImageView("pics/new project.png"));
        createNewProjectButton.setTooltip(new Tooltip("create new project"));
        createNewProjectButton.setOnAction(ActionListenersController::getCreatingProjectAction);
        
        createNewClassButton = new Button("", new ImageView("pics/new_class.png"));
        createNewClassButton.setTooltip(new Tooltip("create new class"));
        createNewClassButton.setOnAction(ActionListenersController::getCreatingClassAction);
        createNewClassButton.setDisable(true);
        
        Button undoButton = new Button("", new ImageView("pics/undo.png"));
        undoButton.setTooltip(new Tooltip("undo"));
        
        Button redoButton = new Button("", new ImageView("pics/redo.gif"));
        redoButton.setTooltip(new Tooltip("redo"));
        
        Button findButton = new Button("", new ImageView("pics/find.png"));
        findButton.setTooltip(new Tooltip("find in text"));
        
        compileButton = new Button("", new ImageView("pics/Compile1.png"));
        compileButton.setTooltip(new Tooltip("Compile"));
        compileButton.setDisable(true);
        compileButton.setOnAction(ActionListenersController::getCompilationAction);
        
        runButton = new Button("", new ImageView("pics/run.gif"));
        runButton.setTooltip(new Tooltip("Run program"));
        runButton.setDisable(true);
        runButton.setOnAction(ActionListenersController::getRunningAction);
        
        Button debugButton = new Button("", new ImageView("pics/debug.png"));
        debugButton.setDisable(true);
        debugButton.setTooltip(new Tooltip("Debug"));
        
        Button switchModeButton = new Button("", new ImageView("pics/switch.png"));
        switchModeButton.setTooltip(new Tooltip("switch to Text Editor"));
        switchModeButton.setOnAction((e) -> {
            whichScene = SceneEnum.TEXT_EDITOR;
            setCurrentScene();
        });
        
        mainToolBar.getItems().addAll(createNewProjectButton, createNewClassButton, /*newTabButton, openButton, saveButton,*/ new Separator(),
                /* closeButton, closeAllButton,*/ new Separator(), undoButton, redoButton,
                new Separator(), findButton, new Separator(), debugButton, compileButton,
                runButton, new Separator(), switchModeButton);
        
        topNode.setBottom(mainToolBar);
        /*
        finish tool bar
         */

 /*
        set up input text area and its functionalities
         */
        TextArea inputTextArea = new TextArea();
        inputTextArea.textProperty().addListener((e) -> textInInputTextAreaIDEMode = inputTextArea.getText());
        
        Label inputLabel = new Label("enter input");
        
        Button getInputFromFileButton = new Button("Use Input File");
        getInputFromFileButton.setTooltip(new Tooltip("get input from text file"));
        
        FlowPane inputAreaFlowPane = new FlowPane(10, 10);
        inputAreaFlowPane.getChildren().addAll(inputLabel, getInputFromFileButton, inputTextArea);
        rightNode.setTop(inputAreaFlowPane);
        /*
        finish input text area
         */

 /*
        set up output text area snd its functionalities
         */
        outputTextArea = new TextArea();
        
        Label outputLabel = new Label("output");
        
        Button outputToFileButton = new Button("Put in File");
        outputToFileButton.setTooltip(new Tooltip("put output in text file"));
        
        FlowPane outputAreaFlowPane = new FlowPane(10, 10);
        outputAreaFlowPane.getChildren().addAll(outputLabel, outputToFileButton, outputTextArea);
        rightNode.setBottom(outputAreaFlowPane);
        /*
        finish output text area
         */
 /*
       set up text area and its conText menues (menu that pop up when right clicked)
         */
        
        mainTxtArea = new TextArea();
        mainTxtArea.setEditable(false);
        mainTxtArea.setText("create project");
        
        mainTxtArea.setStyle("-fx-text-fill: green; -fx-font-size: 16;");
        mainTxtArea.setFont(new Font(20));
        mainTxtArea.textProperty().addListener((e, oldString, newString) -> {
            System.out.println(oldString);
            textInMainTxtAreaIDEMode = mainTxtArea.getText();
        });
        
        MenuItem cut = new MenuItem("Cut");
        cut.setAccelerator(KeyCombination.keyCombination("shortcut+x"));
        cut.setOnAction((e) -> System.out.println("cutting"));
        
        MenuItem copy = new MenuItem("Copy");
        copy.setAccelerator(KeyCombination.keyCombination("shortcut+c"));
        
        MenuItem paste = new MenuItem("Paste");
        paste.setAccelerator(KeyCombination.keyCombination("shortcut+v"));
        
        MenuItem delete = new MenuItem("Delete");
        delete.setAccelerator(KeyCombination.keyCombination("shortcut+d"));
        
        MenuItem selectAll = new MenuItem("Selecet All");
        selectAll.setAccelerator(KeyCombination.keyCombination("shortcut+a"));
        
        MenuItem upperCase = new MenuItem("UPPER CASE");
        upperCase.setAccelerator(KeyCombination.keyCombination("shortcut+u"));
        upperCase.setOnAction((e) -> System.out.println("upper"));
        
        MenuItem lowerCase = new MenuItem("lower case");
        lowerCase.setAccelerator(KeyCombination.keyCombination("shortcut+l"));
        
        ContextMenu rightClickMenu = new ContextMenu(cut, copy, paste, new SeparatorMenuItem(), delete, selectAll, upperCase,
                lowerCase);
        mainTxtArea.setContextMenu(rightClickMenu);
        rootNode.setCenter(mainTxtArea);
        /*
        end text area
         */
        
        rootNode.setRight(rightNode);
        rootNode.setTop(topNode);
        IDEScene = new Scene(rootNode, 1000, 500);
        return IDEScene;
    }
    
    private Scene getTextEditorScene() {
        
        if (textEditorScene != null) {
            return textEditorScene;
        }
        BorderPane rootNode = new BorderPane();
        BorderPane topNode = new BorderPane();

        /*
        set up menu bar and its items
         */
        MenuBar mb = new MenuBar();

        //file menu 
        Menu fileMenu = new Menu("File");
        
        MenuItem newTab = new MenuItem("New", new ImageView("pics/new.gif"));
        newTab.setAccelerator(KeyCombination.keyCombination("shortcut+t"));
        
        MenuItem open = new MenuItem("Open", new ImageView("pics/open.gif"));
        open.setAccelerator(KeyCombination.keyCombination("shortcut+o"));
        
        MenuItem save = new MenuItem("Save", new ImageView("pics/save.gif"));
        save.setAccelerator(KeyCombination.keyCombination("shortcut+s"));
        
        MenuItem close = new MenuItem("Close", new ImageView("pics/Close file.png"));
        close.setAccelerator(KeyCombination.keyCombination("shortcut+w"));
        
        MenuItem closeAll = new MenuItem("Close All", new ImageView("pics/Close file.png"));
        
        fileMenu.getItems().addAll(newTab, open, save, close, closeAll);
        mb.getMenus().add(fileMenu);
        //end file menu

        //edit menu
        Menu edit = new Menu("Edit");
        
        MenuItem undo = new MenuItem("Undo", new ImageView("pics/undo.png"));
        undo.setAccelerator(KeyCombination.keyCombination("shortcut+z"));
        undo.setOnAction((e) -> System.out.println("undo"));
        
        MenuItem redo = new MenuItem("Redo", new ImageView("pics/redo.gif"));
        redo.setAccelerator(KeyCombination.keyCombination("shortcut+y"));
        
        MenuItem cutInMenu = new MenuItem("Cut", new ImageView("pics/cut.gif"));
        cutInMenu.setOnAction((e) -> System.out.println("cutting"));
        
        MenuItem copyInMenu = new MenuItem("Copy", new ImageView("pics/copy.gif"));
        
        MenuItem pasteInMenu = new MenuItem("Paste", new ImageView("pics/paste.gif"));
        
        edit.getItems().addAll(undo, redo, new SeparatorMenuItem(), cutInMenu, copyInMenu, pasteInMenu);
        mb.getMenus().add(edit);
        //end edit menu

        topNode.setTop(mb);
        /*.
        end menu bar
         */

 /*
        set up tool bar
         */
        ToolBar mainToolBar = new ToolBar();
        
        Button newTabButton = new Button("", new ImageView("pics/new.gif"));
        newTabButton.setTooltip(new Tooltip("Open new tab"));
        
        Button openButton = new Button("", new ImageView("pics/open.gif"));
        openButton.setTooltip(new Tooltip("Open file"));
        
        Button saveButton = new Button("", new ImageView("pics/save.gif"));
        saveButton.setTooltip(new Tooltip("Save file"));
        
        Button closeButton = new Button("", new ImageView("pics/Close file.png"));
        closeButton.setTooltip(new Tooltip("close current tab"));
        
        Button closeAllButton = new Button("", new ImageView("pics/Close file.png"));
        closeAllButton.setTooltip(new Tooltip("close all tabs"));
        
        Button printButton = new Button("", new ImageView("pics/print.png"));
        printButton.setTooltip(new Tooltip("print current tab"));
        
        Button undoButton = new Button("", new ImageView("pics/undo.png"));
        undoButton.setTooltip(new Tooltip("undo"));
        
        Button redoButton = new Button("", new ImageView("pics/redo.gif"));
        redoButton.setTooltip(new Tooltip("redo"));
        
        Button findButton = new Button("", new ImageView("pics/find.png"));
        findButton.setTooltip(new Tooltip("find in text"));
        
        Button switchModeButton = new Button("", new ImageView("pics/switch.png"));
        switchModeButton.setTooltip(new Tooltip("switch to IDE"));
        switchModeButton.setOnAction((e) -> {
            whichScene = SceneEnum.IDE;
            setCurrentScene();
        });
        
        mainToolBar.getItems().addAll(newTabButton, openButton, saveButton, new Separator(),
                closeButton, closeAllButton, printButton, new Separator(), undoButton, redoButton,
                new Separator(), findButton, new Separator(), switchModeButton);
        
        topNode.setBottom(mainToolBar);
        /*
        finish tool bar
         */

 /*
       set up text area and its conText menues (menu that pop up when right clicked)
         */
        TextArea txtArea = new TextArea();
        
        MenuItem cut = new MenuItem("Cut");
        cut.setAccelerator(KeyCombination.keyCombination("shortcut+x"));
        cut.setOnAction((e) -> System.out.println("cutting"));
        
        MenuItem copy = new MenuItem("Copy");
        copy.setAccelerator(KeyCombination.keyCombination("shortcut+c"));
        
        MenuItem paste = new MenuItem("Paste");
        paste.setAccelerator(KeyCombination.keyCombination("shortcut+v"));
        
        MenuItem delete = new MenuItem("Delete");
        delete.setAccelerator(KeyCombination.keyCombination("shortcut+d"));
        
        MenuItem selectAll = new MenuItem("Selecet All");
        selectAll.setAccelerator(KeyCombination.keyCombination("shortcut+a"));
        
        MenuItem upperCase = new MenuItem("UPPER CASE");
        upperCase.setAccelerator(KeyCombination.keyCombination("shortcut+u"));
        upperCase.setOnAction((e) -> System.out.println("upper"));
        
        MenuItem lowerCase = new MenuItem("lower case");
        lowerCase.setAccelerator(KeyCombination.keyCombination("shortcut+l"));
        
        MenuItem googleIt = new MenuItem("Google It");
        googleIt.setAccelerator(KeyCombination.keyCombination("shortcut+g"));
        googleIt.setOnAction((e) -> System.out.println("monomono.MonoMonoGUI.start()"));
        
        ContextMenu rightClickMenu = new ContextMenu(cut, copy, paste, new SeparatorMenuItem(), delete, selectAll, upperCase,
                lowerCase, new SeparatorMenuItem(), googleIt);
        txtArea.setContextMenu(rightClickMenu);
        rootNode.setCenter(txtArea);
        /*
        end text area
         */
        
        rootNode.setTop(topNode);
        textEditorScene = new Scene(rootNode, 1000, 500);
        return textEditorScene;
    }
    
    public String getStringInIDEMainTxtArea() {
        System.out.println("monomono.GUI.getStringInIDEMainTxtArea()");
        System.out.println(textInMainTxtAreaIDEMode);
        return textInMainTxtAreaIDEMode;
    }
    
    public String getStringInInputTextArea() {
        return textInInputTextAreaIDEMode;
    }
    
    public void setStringInOutputTextArea(String output) {
        outputTextArea.setText(output);
    }
    
    public void appendStringInOutputTextArea(String output) {
        outputTextArea.appendText(output);
    }
    
    public String getFileDestination() {
        FileChooser fileChooser = new FileChooser();
        File f = fileChooser.showSaveDialog(primaryStage);
        return f.getAbsolutePath();
    }
}
