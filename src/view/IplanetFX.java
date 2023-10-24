package view;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import controller.Controller;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.*;
import java.awt.*;
import java.awt.event.*;
import javafx.beans.binding.Bindings;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;

import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.ParallelTransition;

import javafx.scene.input.MouseEvent;

//import javafx.scene.layout.StackPane;
import model.Block;
import model.Character;
import model.Enemy;
import model.Environment;
import model.Person;
import model.Enemies;
import model.Animal;
import model.Gabriel;
import model.MovableBlock;
import model.Spider;

import model.Parse;
import model.Scan;
import controller.Controller2;
import javafx.scene.PointLight;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
/*import javafx.scene.PerspectiveCamera;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Bloom;*/

import javafx.scene.effect.Reflection;
import javafx.scene.effect.BoxBlur;
import java.lang.Thread;


public class IplanetFX extends Application{

    // Some instance variables
    private GridPane mainGame;
    private GridPane infoDeck;
    private StackPane centerPane;
    private Stage mainStage;
    
    // Menu Items
    private MenuButton statButton;
    private MenuItem statChar;
    private MenuItem statMap;
    private MenuItem statInventory;
    
    private Label curDescription = new Label("Description"); // Description for object we hav
    private Label curHeader = new Label("Header"); // Header for what we have
    
    private ArrayList<Label> labelList;
    private Controller2 c;
    private AnimateEngine ag;
    private Stage s1;
    private Stage s2;
    
    // Some constants
    private final int SCENE_SIZE_ROW = 715;
    private final int SCENE_SIZE_COL = 810;
    public static final String CharStyle = "/utilities/charCss.css";
    
	public static void main(String args[]){
        //Stage st = new Stage();
       // mainStage = stage;
        launch(args);
        
	}

    @Override
    public void start(Stage st){
        //mp.start(new Stage());
        s1 = new Stage();
        s2 = new Stage();
        //Button b1 = new Button("Hey");
        //s1.setScene(new Scene(b1));
        //Button b1 = mp.getButton();
        //prevStage = mp.getStage();
        /*mainPage mp = new mainPage();
        Button startButton = mp.getStartButton();
        Button credits = mp.getCreditButton();
        Label title = mp.getTitleLabel();
        startButton.setOnAction((e)->{
            //prevStage.close();
            s1.close();
           this.start1(s2);
        });
        VBox vb = new VBox();
        vb.getChildren().addAll(title,startButton,credits);
        vb.setStyle("-fx-background-color: black");
        vb.setAlignment(Pos.CENTER);
        //mainPage mp = new mainPage();
        Scene mainScene = new Scene(vb,SCENE_SIZE_COL,SCENE_SIZE_ROW);
        s1.setScene(mainScene);
        s1.setTitle("I Planet");
        s1.show();*/
        this.start1(s2);
        //mp.start(new Stage());
        //launch(args);
    }
    

	public void start1(Stage stage){
       
        
        mainStage = stage;
    
    
        // Animation Engine
        ag = new AnimateEngine();
        
		stage.setTitle("I Planet");
        c = new Controller2(); // Initalizing the controller
        c.setAnimateEngine(ag);
        
        // Initalizing mainGame
        mainGame = new GridPane();
        mainGame.setBackground((new Background( new BackgroundFill(Color.rgb(42, 0, 67), CornerRadii.EMPTY, Insets.EMPTY))));
        mainGame.setStyle("-fx-border-color: GREY;" + "-fx-border-radius: 0.0; -fx-border-width: .1;");
        
        
        infoDeck = new GridPane();
        curDescription = new Label("Description");
        curDescription.setFont(new Font("Arial", 20));
        curDescription.setStyle("-fx-text-fill: cadetblue");
        curHeader = new Label("Header");
        curHeader.setFont(new Font("Arial", 30));
        curHeader.setStyle("-fx-text-fill: cornflowerblue");
        
        statButton = constructStats();
        
        statButton.setId("important");
        
        infoDeck.add(curDescription,2,0);
        infoDeck.add(curHeader,1,0);
        infoDeck.add(statButton,3,0);
        
        ArrayList<Block> list = c.constructMap(); // Consturcting the map
        
        labelList = c.parse(list);
        int col = 0;
        int row = 0;
        int amountCol = c.getAmountCol();
        System.out.println("The columns amount: "+amountCol);
        
        // We will have a temp center pane
        centerPane = new StackPane(mainGame);
        centerPane.setStyle("-fx-background-color:cyan");
                    centerPane.setPrefSize(amountCol*50, labelList.size()*50);
        
        // Getting the board
        for (int j = 0; j<= labelList.size()-1;j++){
                Label curLabel = labelList.get(j);
            
                curLabel.prefWidthProperty().bind(Bindings.min(centerPane.widthProperty().divide(amountCol),
                                                                            centerPane.heightProperty().divide(labelList.size()/amountCol)));
                curLabel.prefHeightProperty().bind(Bindings.min(centerPane.widthProperty().divide(amountCol),
                                                                            centerPane.heightProperty().divide(labelList.size()/amountCol)));
                mainGame.add(labelList.get(j),col,row);
                if (col > amountCol){
                    col = 0;
                    row++;
                }else{
                    col++;
                }
        }
    
        mainGame.setAlignment(Pos.BOTTOM_CENTER);
        
        /*Lighting light = new Lighting();
        Light.Point l = new Light.Point();
        l.xProperty().set(0);
        l.yProperty().set(0);
        l.setZ(1000);
        l.setColor(Color.WHITE);
        light.setLight(l);

       
        
        Blend blend = new Blend(BlendMode.ADD);
        blend.setTopInput(light);
        //blend.setBottomInput(light2);*/
        //ColorAdjust setBrightness = new ColorAdjust();
        //setBrightness.setBrightness(-0.1);
        //setBrightness.setHue(0.01);
        //setBrightness.setSaturation(.09);
        //mainGame.setEffect(setBrightness);
        
       // Bloom bloom = new Bloom();
       /// bloom.setThreshold(0.78);

       //mainGame.setEffect(bloom);
        
        
        infoDeck.setAlignment(Pos.BOTTOM_CENTER);
        infoDeck.setBackground(
                new Background(new BackgroundFill(Color.rgb(42, 0, 67), new CornerRadii(2.0), Insets.EMPTY)));
        infoDeck.setVgap(57);
        infoDeck.setHgap(10);
        
        VBox vb = new VBox(infoDeck,centerPane);
        vb.setStyle("-fx-border-color: GREY;" + "-fx-border-radius: 0.0;");
        vb.setSpacing(0);
        vb.setFillWidth(true);
    
        Timeline tl = new Timeline(
          new KeyFrame(
              Duration.millis(1000),
                  event -> {
                      
                      c.moveBlocks(mainGame,labelList,infoDeck,statButton);
                      // MOVING SORD!!
                     // if (((Character)c.getCurBlock()).hasStick()){
                       //   ag.grabAnimation(((Button)labelList.get(0).getGraphic()).getGraphic());
                      //}
                      if (c.getPrevBlock().getKey().equals("W") || ((Character)c.getCurBlock()).getGettingHit() || c.getPrevBlock().getKey().equals("F")  ){
                          
                          Label l1 = labelList.get(0);
                          Node n = l1.getGraphic();
                          if (c.getPrevBlock().getKey().equals("W")){
                             // Label l1 = labelList.get(0);
                              Button n1 = (Button)l1.getGraphic();
                              //Reflection reflection = new Reflection();
                              //reflection.setFraction(0.7);
                              
                              BoxBlur boxBlur = new BoxBlur();
                              boxBlur.setWidth(5);
                              boxBlur.setHeight(5);
                              boxBlur.setIterations(1);
                              n1.setEffect(boxBlur);
                              
                          }
                          //l1.setStyle("-fx-background-color: black;");
                          // Manipulate node
                          n.setStyle("-fx-background-color: #E9927E;");
                          FadeTransition ft = new FadeTransition(Duration.millis(3000), n);
                          ft.setFromValue(0.5);
                          ft.setToValue(1.0);
                          ft.setCycleCount(1);
                          //ft.setAutoReverse(true);
                          ft.setOnFinished((e) -> {
                              n.setStyle("-fx-background-color: #C7C7C7;");
                              
                                           });
                          ft.play();
                          
                          
                          
                          
                          Character c1 = (Character)c.getCurBlock();
                          c1.decrementLife();
                      
                          if (c1.getGettingHit()){
                              c1.setGettingHit(false);
                          }
                          c.setCurBlock(c1);
            
                          // Later stuff
                          displayCharInfo();
                      }
                      if (((Character)c.getCurBlock()).getLife() == 0){
                          System.out.println("Game Over!!!");
                          
                          Label l1 = labelList.get(0);
                          Node n = l1.getGraphic();
                          n.setStyle("-fx-background-color: #E9927E;");
                          
                          RotateTransition rt = new RotateTransition(Duration.millis(300), n);
                          rt.setByAngle(90);
                          rt.setCycleCount(1);
                          rt.setAutoReverse(true);
                          
                          rt.setOnFinished((e) -> {
                                               
                              displayGameOver(stage);

                                           });
                          rt.play();
                          }
                        }
                  ));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
        

        Scene scene = new Scene(vb,SCENE_SIZE_ROW,SCENE_SIZE_COL);
  
        
        
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent e) {

                // Must update entire enviornment
                ArrayList<Block> list = null;
                Button b1 = new Button("");
                b1.getStylesheets().add(CharStyle);
                //ag.translateUp2(b1, .2, 3, 1, 0, 0);
                b1.setDisable(true);
                
                //Button b2 = new Button("");
                
                if (e.getCode().equals(KeyCode.DOWN)) {
                    // Gonna have a move char3 that we will try and use
                    String move = "s";
                    labelList = c.moveChar3(move,labelList); // Move char 3 will take a string for move and the list of blocks
                    int curPos = c.getCharPos();
                    ag.translateLeftRight(labelList.get(curPos).getGraphic(), .6, 0, 1, 1, 0);
                    
                    
                    
                    /*System.out.println("Here!");
                    int prevPos = c.getCharPos();
                    
                    //labelList.clear();
                    Label prevLabel = c.getPrevLabel();
                    labelList = c.moveChar2("s",labelList);
                    // Did move condition
                    if (c.didMove()){
                    list = c.getBlockList();
                    int charPos = c.getCharPos();
                    int amountCol = c.getAmountCol();
                    
                    
                    System.out.println("The character position: "+charPos);
                    //int r1 = (amountCol)/(charPos+1);
                    int r1 = (charPos)/(amountCol+2);
                    int c1 = charPos%(amountCol+2);
                   
                    
                    //int r2 = (amountCol)/(prevPos+1);
                    int r2 = (prevPos)/(amountCol+2);
                    int c2 = prevPos%(amountCol+2);
                    
                    System.out.println("The row new should be on: "+(r1));
                    System.out.println("The col new should be on: "+(c1));
                    
               
                    
                    mainGame.getChildren().remove(c.getCurLabel());
                    mainGame.getChildren().remove(c.getPrevLabel());
                    
                    
                    // Making an animation for the previous place
                    Node n = prevLabel.getGraphic();
                    translateLeftRight(n, .6, 0, 1, 1, 0);
                    prevLabel.setGraphic(n);
                    
                    
                    mainGame.add(prevLabel,c2,r2);
                    Label newLabel = c.getCurLabel();
                    
                    newLabel.setBackground(c.getPrevLabel().getBackground());
                    newLabel.setGraphic(b1);
                    
                    mainGame.add(c.getCurLabel(),c1,r1);
                        }else{
                            
                            Label curLabel = c.getCurLabel();
                            curLabel.setGraphic(b1);
                            
                        }*/
                        
                    
                }
                if (e.getCode().equals(KeyCode.RIGHT)) {
                    
                    String move = "d";
                    labelList = c.moveChar3(move,labelList);
                    int curPos = c.getCharPos();
                    ag.translateLeftRight(labelList.get(curPos).getGraphic(), .6, 0, 1, 1, 0);
                    /*System.out.println("Here!");
                    int prevPos = c.getCharPos();
                    
                    //labelList.clear();
                    Label prevLabel = c.getPrevLabel();
                    labelList = c.moveChar2("d",labelList);
                    if (c.didMove()){
                    list = c.getBlockList();
                    int charPos = c.getCharPos();
                    int amountCol = c.getAmountCol();
                    
                    
                    System.out.println("The character position: "+charPos);
                    //int r1 = (amountCol)/(charPos+1);
                    int r1 = (charPos)/(amountCol+2);
                    int c1 = charPos%(amountCol+2);
                   
                    
                    //int r2 = (amountCol)/(prevPos+1);
                    int r2 = (prevPos)/(amountCol+2);
                    int c2 = prevPos%(amountCol+2);
                    
                    //System.out.println("The row new should be on: "+(r1));
                    //System.out.println("The col new should be on: "+(c1));
                    
                    mainGame.getChildren().remove(c.getCurLabel());
                    
                    mainGame.getChildren().remove(c.getPrevLabel());
                        
                        // Making an animation for the previous place
                        Node n = prevLabel.getGraphic();
                        translateLeftRight(n, .6, 0, 1, 1, 0);
                        prevLabel.setGraphic(n);
                        
                    mainGame.add(prevLabel,c2,r2);
                    Label newLabel = c.getCurLabel();
                    newLabel.setGraphic(b1);
                    newLabel.setBackground(c.getPrevLabel().getBackground());
                    mainGame.add(c.getCurLabel(),c1,r1);
                        }// End of did move condition
                    else{
                        Label curLabel = c.getCurLabel();
                        curLabel.setGraphic(b1);
                    }*/
                    
                }
                if (e.getCode().equals(KeyCode.UP)) {
                    
                    String move = "w";
                    labelList = c.moveChar3(move,labelList);
                    int curPos = c.getCharPos();
                    ag.translateLeftRight(labelList.get(curPos).getGraphic(), .6, 0, 1, 1, 0);
                    /*System.out.println("Here!");
                    int prevPos = c.getCharPos();
                    
                    //labelList.clear();
                    Label prevLabel = c.getPrevLabel();
                    labelList = c.moveChar2("w",labelList);
                    if (c.didMove()){
                    list = c.getBlockList();
                    int charPos = c.getCharPos();
                    int amountCol = c.getAmountCol();
                    
                    
                    System.out.println("The character position: "+charPos);
                    //int r1 = (amountCol)/(charPos+1);
                    int r1 = (charPos)/(amountCol+2);
                    int c1 = charPos%(amountCol+2);
                   
                    
                    //int r2 = (amountCol)/(prevPos+1);
                    int r2 = (prevPos)/(amountCol+2);
                    int c2 = prevPos%(amountCol+2);
                    
                    System.out.println("The row new should be on: "+(r1));
                    System.out.println("The col new should be on: "+(c1));
                    
                    mainGame.getChildren().remove(c.getCurLabel());
                    
                    mainGame.getChildren().remove(c.getPrevLabel());
                        
                        // Making an animation for the previous place
                        Node n = prevLabel.getGraphic();
                        translateLeftRight(n, .6, 0, 1, 1, 0);
                        prevLabel.setGraphic(n);
                        
                        
                    mainGame.add(prevLabel,c2,r2);
                    Label newLabel = c.getCurLabel();
                    
                    newLabel.setGraphic(b1);
                    
                    newLabel.setBackground(c.getPrevLabel().getBackground());
                    mainGame.add(newLabel,c1,r1);
                        
                        
                        }else{
                            Label curLabel = c.getCurLabel();
                            curLabel.setGraphic(b1);
                        }*/
                }
                if (e.getCode().equals(KeyCode.LEFT)) {
                    
                    
                    String move = "a";
                    labelList = c.moveChar3(move,labelList);
                    int curPos = c.getCharPos();
                    ag.translateLeftRight(labelList.get(curPos).getGraphic(), .6, 0, 1, 1, 0);
                    /*System.out.println("Here! left");
                    int prevPos = c.getCharPos();
                    if (prevPos != 0){
                    
                    //labelList.clear();
                    Label prevLabel = c.getPrevLabel();
                    labelList = c.moveChar2("a",labelList);
                    // Did move condition
                    if (c.didMove()){
                    list = c.getBlockList();
                    int charPos = c.getCharPos();
                    int amountCol = c.getAmountCol();
                    
                    
                    System.out.println("The character position: "+charPos);
                    //int r1 = (amountCol)/(charPos+1);
                    int r1 = (charPos)/(amountCol+2);
                    int c1 = charPos%(amountCol+2);
                   
                    
                    //int r2 = (amountCol)/(prevPos+1);
                    int r2 = (prevPos)/(amountCol+2);
                    int c2 = prevPos%(amountCol+2);
                    
                    
                    
                    System.out.println("The row new should be on: "+(r1));
                    System.out.println("The col new should be on: "+(c1));
                        
                    if (r2 >= 0 && r1 >= 0 && c1 >= 0 && c2 >= 0){
                    
                    mainGame.getChildren().remove(c.getCurLabel());
                    
                    mainGame.getChildren().remove(c.getPrevLabel());
                        
                    // Making an animation for the previous place
                    Node n = prevLabel.getGraphic();
                    translateLeftRight(n, .6, 0, 1, 1, 0);
                    prevLabel.setGraphic(n);
                        
                        
                    mainGame.add(prevLabel,c2,r2);
                    Label newLabel = c.getCurLabel();
                
                    newLabel.setGraphic(b1);
                    newLabel.setBackground(c.getPrevLabel().getBackground());
                    
                    
                    
                    mainGame.add(c.getCurLabel(),c1,r1);
                        
                        }
                        }else{
                            Label curLabel = c.getCurLabel();
                            curLabel.setGraphic(b1);
                            
                        }
                }*/
                     
                     }
                if (e.getCode().equals(KeyCode.SPACE)) {
                    //curHeader.setText(c.get)
                    infoDeck = clearButtons(infoDeck);
                    curHeader.setText(c.getKey());
                    String deScript = c.getDescription();
                    if (deScript.equalsIgnoreCase("next level")){
                        
                        System.out.println("Doing the next level!");
                        changeLevel();
                    }
                    else if (c.getPrevBlock().getKey().equals(">")){
                        
                        boolean taskDone = c.checkTask();
                        
                        if (taskDone){
                            c.incrementTask();
                            int charPosA = c.getCharPos();
                         
                            Timeline timeline = new Timeline();
                            timeline.setCycleCount(2);
                            timeline.getKeyFrames().add(new KeyFrame(
                                Duration.millis(1500),
                                    event -> {
                                        Node door = labelList.get(charPosA).getGraphic();
                                        ag.animateDoorOpen(door);
                                    }));
                            timeline.play();
                        
                            timeline.setOnFinished((en)->{
                                changeLevel();
                            });
                            
                           
                        }else{
                            c.getPrevBlock().setDescription(c.getCurrentTask());
                            curDescription.setText(c.getDescription());
                            
                        }
                    }
                    else if (c.getCharPos()==0){
                        
                        
                        changeLevel(-1);
                        System.out.println("Back");
                    
                        //changeLevel();
                        //changeLevelSpecific();
                        
                    }else if (deScript.equalsIgnoreCase("next levelz")){
                            System.out.println("change levels (To room)");
                            curDescription.setText("room--");
                            changeLevel(5);//
                            //System.out.println("Back");
                            
                    }else{
                            curDescription.setText(c.getDescription());
                  
                            c.determineTalk();
                    }
                    
                }
                if(e.getCode().equals(KeyCode.M) ) {
                    
 
                }
                if(e.getCode().equals(KeyCode.F) ) {
                    System.out.println("The flower gathering");
                    Character c4 = (Character)c.getCurBlock();
                    ((Button)labelList.get(0).getGraphic()).setGraphic(null);
                    boolean canRemove = false;
                    
                    // Trying to decrease inventory
                    ArrayList<Block> al = c4.getInventory();
                    for (int i = 0; i <= al.size() - 1; i++) {
                        if (al.get(i).getKey().equals("f")) {
                            c4.removeInventory(i);
                            canRemove = true;
                            break;
                        }
                    }
                    if (canRemove){
                        
                        clearButtons(infoDeck);
                        c4.setStick(false);
                    
                        c4.incrementLife();
                        c.setCurBlock(c4);
   
            
                    
                        String life = "";
                        for (int i = 0; i<= 9;i++){
                            if (i < c4.getLife()){
                                life += ".";
                            }else{
                                life += "-";
                            }
                        }
                        curHeader.setText(c4.getName());
                        curDescription.setText(life);
                        
                    }
                    
 
                }
                if (e.getCode().equals(KeyCode.G)) {
                    
                    // Going to try and place dirt after grabing
                    // Could try to set prevBlock and prevLabel

                    if (((Character)c.getCurBlock()).hasStick()){
                        //Node n = curLabel.getGraphic();
                        Node n = labelList.get(0).getGraphic();
                        Button b9 = (Button)n;
                        Node n5 = b9.getGraphic();
                        ag.grabAnimation(n5);
                        c.decrementFound();
                        
                    }else{
                       // infoDeck = clearButtons(infoDeck);
                        Block prevB = c.getPrevBlock();
                        if (!(prevB instanceof MovableBlock)){
                        Block curB = c.getCurBlock();
                        infoDeck = clearButtons(infoDeck);
                        
                        Block b = new Block(".");

                        Background br = new Background(new BackgroundFill(Color.rgb(137, 110, 77), CornerRadii.EMPTY, Insets.EMPTY));
                        
                        
                        c.setPrevBlock(b);
                        c.setPrevLabelBackground(br);
                        
                        int curPos = c.getCharPos();
                        Label curLabel = labelList.get(curPos);
              
                        curLabel.setGraphic(null);
                        ArrayList<Block> inventory = c.putInInventory(curB,prevB);
                        System.out.println(inventory);
        
                        
                        //Node n = curLabel.getGraphic();
                        Node n = labelList.get(0).getGraphic();
                        
                        ag.grabAnimation(n);
                        
                        // Doing display stuff
                        curHeader.setText("Inventory: ");
                        curDescription.setText("");
                        ArrayList<MenuButton> inventoryButtons = c.parseInventory(inventory,infoDeck,curDescription,curHeader);
                        System.out.println("The button list; "+inventoryButtons.toString());
                        for (int i = 0; i<= inventoryButtons.size()-1;i++){
                            infoDeck.add(inventoryButtons.get(i),i+2,0);
                        }
                    }
                        
                    }
              
               
                    // Will display inventory
                    //displayInventory();
                }
                if (e.getCode().equals(KeyCode.I)) {
                    // Get
                    infoDeck = clearButtons(infoDeck);
                    curHeader.setText("Inventory: ");
                    curDescription.setText("");
                    
                    Block curB = c.getCurBlock();
                    ArrayList<Block> inventory = c.getInventory(curB);
                    ArrayList<MenuButton> inventoryButtons = c.parseInventory(inventory,infoDeck,curDescription,curHeader);
                    for (int i = 0; i<= inventoryButtons.size()-1;i++){
                        infoDeck.add(inventoryButtons.get(i),i+2,0);
                        
                    }
                    
                }
                if (e.getCode().equals(KeyCode.C)){
                    // For the character info!!
                    System.out.println("Characer question requested: ");
                    displayCharInfo();
                    
                    
                    
                    
                }
                if (e.getCode().equals(KeyCode.K)){
                    
                    // We want to set knife if it exists
                 
                    Block curB = c.getCurBlock();
                    ArrayList<Block> inventory = c.getInventory(curB);
                    boolean canHit = false;
                    for (int i = 0; i<= inventory.size()-1;i++){
                        if (inventory.get(i).getKey().equals("K")){
                            canHit = true;
                        }
                    }
                  
                    if (canHit && !((Character)c.getCurBlock()).hasStick()){
                        Character c3 = (Character)curB;
                        c3.setStick(true);
                        c.setCurBlock(c3);
                        
                        // Now, we must set the graphic
                        
                        //c3.setStick(true);
                        //c.setCurBlock(c4);
                        Button n2 = new Button("");
                        n2.getStylesheets().add("/utilities/knifeCss.css");
                        
                   
                        n2.setMinWidth(10);
                        n2.setMinHeight(10);
                        //n2.setManaged(false);
                        //n2.setLayoutY(50);
                        Button n1 = (Button)labelList.get(0).getGraphic();
                        n1.setGraphic(n2);
                        
                    }
                    else if (canHit && ((Character)c.getCurBlock()).hasStick()){
                        Button n1 = (Button)labelList.get(0).getGraphic();
                        n1.setGraphic(null);
                        Character c3 = (Character)curB;
                        c3.setStick(false);
                    }
                    
                    
                    
                }
            }
        });
        stage.setScene(scene);
        stage.show();
	}
    
    
    
    
    /*
     Something to help with the end of the game!!
     Should return the 'controller' as a new controller with nothing there
     FIX!! Make prettier!!
     
     
     */
    public Controller2 displayGameOver(Stage stage1) {
        //stage1 = new Stage();
        
        Label l2 = new Label("You Lost :(");
        Button b1 = new Button("Play again?");


        l2.setFont(new Font("ariel", 50));
        l2.setStyle(
                "-fx-border-color: GREY;" +
                    "-fx-border-radius: 10.0;" +
                    "-fx-border-width: 5;" +
                    "-fx-text-fill: red;"+
                    "-fx-background-color: grey;");

        l2.setBackground(new Background(new BackgroundFill(Color.rgb(42, 0, 67), new CornerRadii(6.0), Insets.EMPTY)));

        // l2.setMaxWidth(Double.MAX_VALUE);
        l2.setAlignment(Pos.BASELINE_LEFT);

        b1.setFont(new Font("ariel", 50));
        b1.setStyle("-fx-border-color: GREY;" + "-fx-border-radius: 10.0;" + "-fx-border-width: 5;"
                + "-fx-text-fill: green");
        b1.setBackground(new Background(new BackgroundFill(Color.rgb(42, 0, 67), new CornerRadii(6.0), Insets.EMPTY)));
        b1.setAlignment(Pos.BASELINE_RIGHT);
        // What happends if you start the game over
        b1.setOnAction((e) -> {
            
            mainStage.close();
      
       

        });

        VBox vbox2 = new VBox(3, l2, b1);
        vbox2.setBackground(new Background(new BackgroundFill(Color.rgb(42, 0, 67), CornerRadii.EMPTY, Insets.EMPTY)));
        vbox2.setAlignment(Pos.CENTER);
        Scene sc1 = new Scene(vbox2, SCENE_SIZE_ROW, SCENE_SIZE_COL);

        mainStage.setScene(sc1);
        mainStage.setTitle("Game Over");
        mainStage.show();
        
        
        //c = new Controller2();
        return c; // WIll return the controller value
    }
    
    public GridPane clearButtons(GridPane gp){
        
        ArrayList<MenuButton> bl = new ArrayList<MenuButton>();
        
        for (Node child: gp.getChildren()){
        if (child != null){
            if (child instanceof MenuButton){
                
            
                //gp.getChildren().remove(child);
                // mainGame.getChildren().remove(c.getCurLabel());
            
                if (((MenuButton)child).getId()!= null){
                    System.out.println("The id of child: "+((MenuButton)child).getId());
                    if (!((MenuButton)child).getId().equals(((MenuButton)statButton).getId())){
                        bl.add((MenuButton)child);
                    }else{
                        System.out.println("The id of statButton: "+child.getId());
                        child.setId("important");
                    }
                    
                }else{
                    System.out.println("The id of child: "+((MenuButton)child).getId());
                    bl.add((MenuButton)child);
                }
            }
        }
            }
        
        for (int i = 0; i<= bl.size()-1;i++){
            gp.getChildren().remove(bl.get(i));
        }
        
        return gp;
    }
    
    public void changeLevel(){
        
        c.changeLevel();
        
        
        ArrayList<Block> list = c.constructMap(); // Consturcting the map
        mainGame.getChildren().clear();
        labelList.clear();
        labelList = c.parse(list);
        int col = 0;
        int row = 0;
        int amountCol = c.getAmountCol();
        System.out.println("The columns amount: "+amountCol);
        
        
        // We will have a temp center pane
        centerPane.getChildren().clear();
        centerPane.getChildren().add(mainGame);
        centerPane.setStyle("-fx-background-color:cyan");
                    centerPane.setPrefSize(amountCol*50, labelList.size()*50);
        
        


        // Getting the board
        for (int j = 0; j<= labelList.size()-1;j++){
                Label curLabel = labelList.get(j);
            
                curLabel.prefWidthProperty().bind(Bindings.min(centerPane.widthProperty().divide(amountCol),
                                                                            centerPane.heightProperty().divide(labelList.size()/amountCol)));
                curLabel.prefHeightProperty().bind(Bindings.min(centerPane.widthProperty().divide(amountCol),
                                                                            centerPane.heightProperty().divide(labelList.size()/amountCol)));
            
            
                mainGame.add(labelList.get(j),col,row);
                
                if (col > amountCol){
                    col = 0;
                    row++;
                }else{
                    col++;
                }
        }
        
    }
    
    public void changeLevel(int vals){
        ArrayList<Block> list = null;
        if (vals == -1 && c.inRoom() == false){
            c.changeLevel(vals);
            list = c.constructMap(); // Consturcting the map
        }else if (vals == -1 && c.inRoom() == true){
            c.changeLevel(0);
            list = c.constructMap(); // Consturcting the map
        }
        else{
            c.changeToRoom();
            list = c.constructMap2();
            System.out.println("The list for map2: "+list);
        }
        mainGame.getChildren().clear();
        labelList.clear();
        labelList = c.parse(list);
        int col = 0;
        int row = 0;
        int amountCol = c.getAmountCol();
        System.out.println("The columns amount: "+amountCol);
        
        
        // We will have a temp center pane
        centerPane.getChildren().clear();
        centerPane.getChildren().add(mainGame);
        centerPane.setStyle("-fx-background-color:cyan");
                    centerPane.setPrefSize(amountCol*50, labelList.size()*50);
        
        


        // Getting the board
        for (int j = 0; j<= labelList.size()-1;j++){
                Label curLabel = labelList.get(j);
            
                curLabel.prefWidthProperty().bind(Bindings.min(centerPane.widthProperty().divide(amountCol),
                                                                            centerPane.heightProperty().divide(labelList.size()/amountCol)));
                curLabel.prefHeightProperty().bind(Bindings.min(centerPane.widthProperty().divide(amountCol),
                                                                            centerPane.heightProperty().divide(labelList.size()/amountCol)));
            
            
                mainGame.add(labelList.get(j),col,row);
                
                if (col > amountCol){
                    col = 0;
                    row++;
                }else{
                    col++;
                }
        }
        //int pos = c.getCharPos();
        
    }
    
 
    
    public void translateCharToPos(int pos, Node n){
        double x = n.getLayoutX();
       // System.out.println("what the layout was: "+ x);
        double y = n.getLayoutY();
        n.setLayoutY(y+pos);
        n.setLayoutX(y+pos);
    }
    
    // To construct the proper stats for the game
    public MenuButton constructStats(){
        
        // The status of everything button
        statButton = new MenuButton("=");
        statButton.setAlignment(Pos.BASELINE_RIGHT);
      
        statButton.setFocusTraversable(false);
        statButton.setStyle("-fx-background-color: #2A0043;"+
                "-fx-text-fill: cadetblue;" +
                "-fx-font-family: Courier New;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 15;");
    
        statButton.getStylesheets().add("/model/myButton.css");
        
        // Setting the map, character, and inventory options
        statMap = new MenuItem("Map");
        statMap.setId("important");
    
        statMap.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Map pressed!");
               	displayMap(); 
            }
        });
        
        
        statChar = new MenuItem("Char info");
        statChar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Char info pressed!");
                displayCharInfo();
                
            }
        });
        statInventory = new MenuItem("Inventory");
        statInventory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                
                System.out.println("Inventory pressed!");
                infoDeck = clearButtons(infoDeck);
                curHeader.setText("Inventory: ");
                curDescription.setText("");
                
                Block curB = c.getCurBlock();
                ArrayList<Block> inventory = c.getInventory(curB);
                ArrayList<MenuButton> inventoryButtons = c.parseInventory(inventory,infoDeck,curDescription,curHeader);
                for (int i = 0; i<= inventoryButtons.size()-1;i++){
                    infoDeck.add(inventoryButtons.get(i),i+2,0);
                    
                }
                
            }
        });
        
        
        
        statButton.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                statButton.setStyle("-fx-background-color: #2A0043;"+
                        "-fx-text-fill: cadetblue;" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15;");
            }
            
        });// For exiting the button
                                    
                                    
        statButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                statButton.setStyle("-fx-background-color: cadetblue;"+
                        "-fx-text-fill: cornflowerblue;" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15;");
            }
        });
        
        // Adding options to statButton
        statButton.getItems().add(statMap);
        statButton.getItems().add(statChar);
        statButton.getItems().add(statInventory);
        
        return statButton;
        
        
    }
    
 

    public void displayMap()
    {
	MapGUI2 mapInfo = new MapGUI2();
	Stage mapWindow = new Stage();

	System.out.println("Current Character Position: " + c.getCharPos());
	mapInfo.start(mapWindow, c.getCharPos(), c.getCurrentLevel());
	//mapInfo.start(this.stage1);
    }
    
    
    public void displayCharInfo(){
        infoDeck = clearButtons(infoDeck); //
        curHeader.setText(" ");
        String charLife = "";
        Character ci = (Character)c.getCurBlock();
        String charName = ci.getName();
        for (int p = 0; p<= 9;p++){
            if (p <= ci.getLife()-1){
                charLife += ".";
            }else{
                charLife += "_";
            }
        }
        curHeader.setText(charName);
        curDescription.setText(charLife);
    }
    
    
   
    

}





