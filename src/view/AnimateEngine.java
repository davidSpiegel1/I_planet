// A class for animating 
package view;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
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
import javafx.scene.canvas.*;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.*;
//import java.awt.*;
//import java.awt.event.*;
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
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;

//import javafx.scene.layout.StackPane;
import model.Block;
import model.Character;
import model.Enemy;
import model.Person;
import model.Enemies;

import model.Parse;
import model.Scan;
import controller.Controller2;


public class AnimateEngine {
    
    private Button curButton;
    private Node oldGraphic;
    // Constructor for the animation engine
    public AnimateEngine(){
        System.out.println("Starting the animation engine");
    }
    
    
    
    public Node moveDueToEnv(Node n, String env){
        System.out.println("The ENV: "+env);
        if (env.equals("E")){
            
            //hitAnimation(n);
            slightMove0(n);
        }
        if (env.equals("G")){
            //drinkAnimation(n);
            slightMove1(n);
        }
        if (env.equals("g")){
            slightMove2(n);
        }
        if (env.equals("t")){
            slightMove3(n);
        }
        if (env.equals("|") || env.equals("_")){
            slightMove4(n);
            //n = translateUp(n);
        }
        return n;
    }
    
    public void slightMove4(Node n){
        double x = n.getLayoutX();
        System.out.println("what the layout was: "+ x);
        double y = n.getLayoutY();
    
        n.setLayoutX(x+1);
        slightMove1(n);
        
        
        
    }
    public void slightMove1(Node n){
        //Button b = (Button)n;
        RotateTransition rt = new RotateTransition(Duration.millis(100), n);
        rt.setFromAngle(5);
        rt.setToAngle(1);
        
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(rt/*,st3*/);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        
    }
    // Make diffrent
    public void slightMove0(Node n){
        //Button b = (Button)n;
        double x = n.getLayoutX();
        System.out.println("what the layout was: "+ x);
        double y = n.getLayoutY();
    
        n.setLayoutY(y+1);
        slightMove1(n);
        
    }
    
    public void slightMove2(Node n){
        //Button b = (Button)n;
        RotateTransition rt = new RotateTransition(Duration.millis(100), n);
        rt.setFromAngle(-5);
        rt.setToAngle(1);
        
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(rt/*,st3*/);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        
    }
    
    public void slightMove3(Node n){
        //Button b = (Button)n;
        Path path = new Path();
        path.getElements().add(new MoveTo(5,5));
        QuadCurveTo qt = new QuadCurveTo();
        qt.setX(2);
        qt.setY(5);
        qt.setControlX(5);
        qt.setControlY(6);
        
        
        
        path.getElements().add(qt);
        //path.getElements().add(st);
        PathTransition pathT = new PathTransition();
        pathT.setDuration(Duration.millis(100));
        //pathT.setDuration(Duration.millis(250));
        pathT.setPath(path);
        pathT.setNode(n);
        
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(pathT/*,st3*/);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        
    }
    
    
    public void hitAnimation(Node n){
        FadeTransition ft = new FadeTransition(Duration.millis(3000), n);
        ft.setFromValue(0.5);
        ft.setToValue(1.0);
        ft.setCycleCount(1);
        //ft.setAutoReverse(true);
        ft.setOnFinished((e) -> {
            n.setStyle("-fx-background-color: #C7C7C7;");
            

                         });
        ft.play();
    }
    public void deadAnimation(Node n){
        RotateTransition rt = new RotateTransition(Duration.millis(300), n);
        rt.setByAngle(90);
        rt.setCycleCount(1);
        rt.setAutoReverse(true);
        rt.play();
        
    }
    
    public void animateDoorOpen(Node n){
        
        ScaleTransition st = new ScaleTransition(Duration.millis(1500),n);
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(.01);
        st.setToY(1);
        st.setCycleCount(1);
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(st);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
    
    }
    
    
    public void grabAnimation(Node n){
        
        RotateTransition rt = new RotateTransition(Duration.millis(200), n);
        rt.setFromAngle(60);
        rt.setToAngle(0);
        
        ScaleTransition st = new ScaleTransition(Duration.millis(290),n);
        st.setFromX(.01);
        st.setFromY(1);
        st.setToX(1);
        st.setToY(1);
        st.setCycleCount(1);
        
        ScaleTransition st2 = new ScaleTransition(Duration.millis(200),n);
        st2.setFromX(1.3f);
        st2.setFromY(1);
        st2.setToX(1);
        st2.setToY(1);
        st2.setCycleCount(1);
    
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(st2,rt);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();

    }
    
    // An animation that makes the character drink
    
    public void drinkAnimation(Node n){
        Button b = (Button)n;
        // Will be mouth of animal
        Ellipse el = new Ellipse();
        //el.focusTraversable(false);
        //el.setManaged(false);
        el.setCenterX(7);
        el.setCenterY(7);
        el.setRadiusX(5.0f);
        el.setRadiusY(3.0f);
        
        
        //el.setLayoutX(20);
        el.setTranslateX(-10);
        el.setTranslateY(-4);
        el.setFill(Color.rgb(74,74,74));
        
        // Scale transition
        ScaleTransition st3 = new ScaleTransition(Duration.millis(900),el);
        st3.setFromX(1.3f);
        st3.setFromY(1);
        st3.setToX(0);
        st3.setToY(0);
        st3.setCycleCount(1);
        
        b.setGraphic(el);
        
        
        RotateTransition rt = new RotateTransition(Duration.millis(200), n);
        rt.setFromAngle(-30);
        rt.setToAngle(0);
        
      
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(rt,st3);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
    
    }
    
    public void lookUpEat(Node n){
        
        Button b = (Button)n;
        RotateTransition rt = new RotateTransition(Duration.millis(600), n);
        rt.setFromAngle(30);
        rt.setToAngle(10);
        
        Ellipse el = new Ellipse();
        //el.focusTraversable(false);
        //el.setManaged(false);
        el.setCenterX(7);
        el.setCenterY(7);
        el.setRadiusX(3.0f);
        el.setRadiusY(1.0f);
        
        
        //el.setLayoutX(20);
        el.setTranslateX(-10);
        el.setTranslateY(-4);
        el.setFill(Color.rgb(74,74,74));
        
        ScaleTransition st3 = new ScaleTransition(Duration.millis(900),el);
        st3.setFromX(1.3f);
        st3.setFromY(1);
        st3.setToX(0);
        st3.setToY(0);
        st3.setCycleCount(1);
        
        b.setGraphic(el);
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(rt,st3);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
    }
    
    
    public VBox getCoverImage(Node n){
        
        VBox vb = new VBox();
        
        // The top
        HBox hb = new HBox(120);
        
        
        Polygon poly = new Polygon();
        poly.getPoints().addAll(new Double[]{
            0.0, 0.0,
            20.0, 10.0,
            10.0, 20.0 });
        
        Button dog = new Button();
        dog.getStylesheets().add("/utilities/skins/cowCss.css");
        
        Button flower = new Button();
        flower.getStylesheets().add("/utilities/skins/flowerCss.css");
        
        Button grass = new Button();
        grass.getStylesheets().add("/utilities/skins/gabrielCss.css");
        
        Button fire = new Button();
        fire.getStylesheets().add("/utilities/skins/fireCss.css");
        
        
        
        hb.getChildren().addAll(poly,n,flower,dog,fire,grass);
        
        
        
        // The floor
        Rectangle floor = new Rectangle();
        floor.setX(5);
        floor.setY(-5);
        floor.setWidth(850);
        floor.setHeight(500);
        floor.setArcWidth(20);
        floor.setArcHeight(20);
        floor.setFill(Color.rgb(110, 151, 125));
        
        vb.getChildren().addAll(hb,floor);
        
        
        return vb;
        
    }
    
    public void moveUpset(Node n){
        
        Button b = (Button)n;
        StackPane sp = new StackPane();
        // Will eye of animal
        Ellipse eye1 = new Ellipse();
        Ellipse eyeB1 = new Ellipse();
        Ellipse eye2 = new Ellipse();
        Ellipse eyeB2 = new Ellipse();
       

        eye1.setCenterX(-7);
        eye1.setCenterY(-11);
        eye1.setRadiusX(5.0f);
        eye1.setRadiusY(5.0f);
        

        eye1.setTranslateX(-7.0f);
        eye1.setTranslateY(-11);
        eye1.setFill(Color.rgb(83,83,83));
        
        eyeB1.setCenterX(-7);
        eyeB1.setCenterY(11);
        eyeB1.setRadiusX(1.5f);
        eyeB1.setRadiusY(1.5f);
        

        eyeB1.setTranslateX(-7.0f);
        eyeB1.setTranslateY(-11);
        eyeB1.setFill(Color.rgb(234,234,234));
        
        
        
        eye2.setCenterX(-7);
        eye2.setCenterY(-11);
        eye2.setRadiusX(5.0f);
        eye2.setRadiusY(5.0f);

        eye2.setTranslateX(-13);
        eye2.setTranslateY(-11);
        eye2.setFill(Color.rgb(83,83,83));
        
        eyeB2.setCenterX(-7);
        eyeB2.setCenterY(-11);
        eyeB2.setRadiusX(1.5f);
        eyeB2.setRadiusY(1.5f);

        eyeB2.setTranslateX(-13);
        eyeB2.setTranslateY(-11);
        eyeB2.setFill(Color.rgb(234,234,234));
        
        /*
         
         The SVG PATH for guy:
         "M 20 20 A 2 2 90 0 0 14 20 A 2 2 90 0 0 20 20 M 18 24 A 2 2 90 0 0 16 26 Q 14 28 16 30 Q 24 30 18 24 M 20 20 A 2 2 90 0 0 20 20 M 15 21 A 1 1 0 0 0 17 21 L 15 21 Q 16 16 17 21 M 18 21 A 1 1 0 0 0 19 21 L 17 21 Q 18 16 19 21"
         */
        
        sp.getChildren().addAll(eye1,eye2,eyeB1,eyeB2);
        
        RotateTransition rt = new RotateTransition(Duration.millis(200), eye1);
        RotateTransition rt2 = new RotateTransition(Duration.millis(200), eye2);
        
        RotateTransition lt = new RotateTransition(Duration.millis(200), eyeB1);
        RotateTransition lt2 = new RotateTransition(Duration.millis(200), eyeB2);
        
        rt.setFromAngle(60);
        rt.setToAngle(0);
        
        
        rt2.setFromAngle(60);
        rt2.setToAngle(0);
        
        lt.setFromAngle(-60);
        lt.setToAngle(0);
        
        
        lt2.setFromAngle(-60);
        lt2.setToAngle(0);
        
        b.setGraphic(sp);
        
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(rt,rt2,lt,lt2);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        
    }
    
    public void moveNormalEye(Node n){
        
        Button b = (Button)n;
        StackPane sp = new StackPane();
        // Will eye of animal
        Ellipse eye1 = new Ellipse();
        Ellipse eyeB1 = new Ellipse();
        Ellipse eye2 = new Ellipse();
        Ellipse eyeB2 = new Ellipse();
       

        eye1.setCenterX(-7);
        eye1.setCenterY(-11);
        eye1.setRadiusX(3.0f);
        eye1.setRadiusY(5.0f);
        

        eye1.setTranslateX(-7.0f);
        eye1.setTranslateY(-11);
        eye1.setFill(Color.rgb(83,83,83));
        
        eyeB1.setCenterX(-7);
        eyeB1.setCenterY(11);
        eyeB1.setRadiusX(0.5f);
        eyeB1.setRadiusY(1.5f);
        

        eyeB1.setTranslateX(-7.0f);
        eyeB1.setTranslateY(-11);
        eyeB1.setFill(Color.rgb(234,234,234));
        
        
        
        eye2.setCenterX(-7);
        eye2.setCenterY(-11);
        eye2.setRadiusX(3.0f);
        eye2.setRadiusY(5.0f);

        eye2.setTranslateX(-13);
        eye2.setTranslateY(-11);
        eye2.setFill(Color.rgb(83,83,83));
        
        eyeB2.setCenterX(-7);
        eyeB2.setCenterY(-11);
        eyeB2.setRadiusX(0.5f);
        eyeB2.setRadiusY(1.5f);

        eyeB2.setTranslateX(-13);
        eyeB2.setTranslateY(-11);
        eyeB2.setFill(Color.rgb(234,234,234));
        
        /*
         
         The SVG PATH for guy:
         "M 20 20 A 2 2 90 0 0 14 20 A 2 2 90 0 0 20 20 M 18 24 A 2 2 90 0 0 16 26 Q 14 28 16 30 Q 24 30 18 24 M 20 20 A 2 2 90 0 0 20 20 M 15 21 A 1 1 0 0 0 17 21 L 15 21 Q 16 16 17 21 M 18 21 A 1 1 0 0 0 19 21 L 17 21 Q 18 16 19 21"
         */
        
        sp.getChildren().addAll(eye1,eye2,eyeB1,eyeB2);
        
        RotateTransition rt = new RotateTransition(Duration.millis(200), eye1);
        RotateTransition rt2 = new RotateTransition(Duration.millis(200), eye2);
        
        RotateTransition lt = new RotateTransition(Duration.millis(200), eyeB1);
        RotateTransition lt2 = new RotateTransition(Duration.millis(200), eyeB2);
        
        rt.setFromAngle(60);
        rt.setToAngle(0);
        
        
        rt2.setFromAngle(60);
        rt2.setToAngle(0);
        
        lt.setFromAngle(-60);
        lt.setToAngle(0);
        
        
        lt2.setFromAngle(-60);
        lt2.setToAngle(0);
        
        b.setGraphic(sp);
        
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(rt,rt2,lt,lt2);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        
    }
    
    
    
    public void translateArmsSpider(Node n){
        Button b = (Button)n;
        StackPane sp = new StackPane();
        // Will be mouth of animal
        Ellipse armLeft = new Ellipse();
        Ellipse armRight = new Ellipse();
        Ellipse armRight2 = new Ellipse();
        Ellipse armLeft2 = new Ellipse();
        //el.focusTraversable(false);
        //el.setManaged(false);
        armLeft.setCenterX(9);
        armLeft.setCenterY(9);
        armLeft.setRadiusX(9.0f);
        armLeft.setRadiusY(3.0f);
        

        armLeft.setTranslateX(-10.0f);
        armLeft.setTranslateY(4.0f);
        armLeft.setFill(Color.rgb(227,227,227));
        
        armLeft2.setCenterX(6);
        armLeft2.setCenterY(5);
        armLeft2.setRadiusX(6.0f);
        armLeft2.setRadiusY(2.0f);
        

        armLeft2.setTranslateX(-10.0f);
        armLeft2.setTranslateY(-5.0f);
        armLeft2.setFill(Color.rgb(227,227,227));
        
        
        
        armRight.setCenterX(9);
        armRight.setCenterY(9);
        armRight.setRadiusX(9.0f);
        armRight.setRadiusY(3.0f);

        armRight.setTranslateX(10);
        armRight.setTranslateY(4.0f);
        armRight.setFill(Color.rgb(227,227,227));
        
        armRight2.setCenterX(6);
        armRight2.setCenterY(5);
        armRight2.setRadiusX(6.0f);
        armRight2.setRadiusY(2.0f);

        armRight2.setTranslateX(10);
        armRight2.setTranslateY(-5.0f);
        armRight2.setFill(Color.rgb(227,227,227));
        
        
       
        
        
        
        sp.getChildren().addAll(armLeft,armRight,armLeft2,armRight2);
        
        RotateTransition rt = new RotateTransition(Duration.millis(200), armLeft);
        RotateTransition rt2 = new RotateTransition(Duration.millis(200), armLeft2);
        
        RotateTransition lt = new RotateTransition(Duration.millis(200), armRight);
        RotateTransition lt2 = new RotateTransition(Duration.millis(200), armRight2);
        
        rt.setFromAngle(60);
        rt.setToAngle(0);
        
        
        rt2.setFromAngle(60);
        rt2.setToAngle(0);
        
        lt.setFromAngle(-60);
        lt.setToAngle(0);
        
        
        lt2.setFromAngle(-60);
        lt2.setToAngle(0);
        
        b.setGraphic(sp);
        
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(rt,rt2,lt,lt2);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        
        
    }
    
    public void translateBack(Node n){
        Button b = (Button)n;
        b.setScaleX(-1);
    }
    
    public void translateBack2(Node n){
        Button b = (Button)n;
        b.setScaleX(1);
    }
    
    
    
    public Node translateEat(Node n){
        
        RotateTransition rt = new RotateTransition(Duration.millis(200), n);
        rt.setFromAngle(-60);
        rt.setToAngle(0);
        
        ScaleTransition st = new ScaleTransition(Duration.millis(290),n);
        st.setFromX(.01);
        st.setFromY(1);
        st.setToX(1);
        st.setToY(1);
        st.setCycleCount(1);
        
        ScaleTransition st2 = new ScaleTransition(Duration.millis(200),n);
        st2.setFromX(1.3f);
        st2.setFromY(1);
        st2.setToX(1);
        st2.setToY(1);
        st2.setCycleCount(1);
    
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(st2,rt);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        return n;

    }
    
    
    
    // Translate up to emulate movement
    public void moveNaturalAnimals(Node n) {
        
        Path path = new Path();
        path.getElements().add(new MoveTo(5,5));
        //path.getElements().add(new CubicCurveTo(5,0,3,1,2,1));
        QuadCurveTo qt = new QuadCurveTo();
        qt.setX(3);
        qt.setY(12);
        qt.setControlX(12);
        qt.setControlY(6);
        //qt.setX(20);
        //qt.setY(15);
        //qt.setControlX(15);
        //qt.setControlY(20);
        
        
        
        path.getElements().add(qt);
        //path.getElements().add(st);
        PathTransition pathT = new PathTransition();
        pathT.setDuration(Duration.millis(600));
        //pathT.setDuration(Duration.millis(250));
        pathT.setPath(path);
        pathT.setNode(n);
        
        // Would be infefinite if breathing or something
        // pathT.setCycleCount(Timeline.INDEFINITE);
        pathT.setCycleCount(1);
        //pathT.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathT.setAutoReverse(true);
   
        
   
        
  
        
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(pathT);
        
        
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        //st2.play();
    }
    
    public void translateLeftRight(Node n, double dur, double up, double down, double ogDown, double ogUp) {
        
        Path path = new Path();
        
        MoveTo moveTo = new MoveTo();
        moveTo.setX(10.0);
        moveTo.setY(10.0);
    
    
        ArcTo arcTo = new ArcTo();
        arcTo.setX(13.0);
        arcTo.setY(15.0);
        arcTo.setRadiusX(3.0);
        arcTo.setRadiusY(1.0);
        arcTo.setLargeArcFlag(true);
        
        path.getElements().add(moveTo);
        path.getElements().add(arcTo);
      
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(dur));
        pathTransition.setNode(n);
        pathTransition.setPath(path);
        pathTransition.play();
    }
    
   
    
    public void shootNode(Node n, double dur, double up, double down, double ogDown, double ogUp) {
        
        
        Path path = new Path();
        
        MoveTo moveTo = new MoveTo();
        moveTo.setX(0.0f);
        moveTo.setY(50.0f);
        path.getElements().add(moveTo);
    
        path.getElements().add(new QuadCurveTo(25.0f,0.0f,50.0f,50.0f));
      
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(dur));
        pathTransition.setNode(n);
        pathTransition.setPath(path);
        pathTransition.play();
    }
    
    public void shootNode2(Node n, double dur) {
        Path path = new Path();
        path.getElements().add(new MoveTo(0.0f, 0.0f));
        path.getElements().add(new HLineTo(80.0f));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(dur));
        pathTransition.setNode(n);
        pathTransition.setPath(path);
        pathTransition.play();
    }
    
    public Node translateUp(Node n,double scaler) {

        double x = n.getLayoutX();
        System.out.println("what the layout was: "+ x);
        double y = n.getLayoutY();
    
        n.setLayoutY(y-scaler);
      
        double x2 = n.getLayoutY();
        System.out.println("what th layout is: "+x2);
        
        return n;
    }
    
    public Node translateDown(Node n,double scaler) {

        double x = n.getLayoutX();
        System.out.println("what the layout was: "+ x);
        double y = n.getLayoutY();
        // Used to be: 32
        n.setLayoutY(y+scaler);
      
        double x2 = n.getLayoutY();
        System.out.println("what th layout is: "+x2);
        
        return n;
    }
    
    public Node translateLeft(Node n,double scaler) {

        double x = n.getLayoutX();
        System.out.println("what the layout was: "+ x);
        double y = n.getLayoutY();
    
        n.setLayoutX(x-scaler);
      
        double x2 = n.getLayoutX();
        System.out.println("what th layout is: "+x2);
        
        return n;
    }
    
    public Node translateRight(Node n, double scaler) {

        double x = n.getLayoutX();
        System.out.println("what the layout was: "+ x);
        double y = n.getLayoutY();
    
        n.setLayoutX(x+scaler);
      
        double x2 = n.getLayoutX();
        System.out.println("what th layout is: "+x2);
        
        return n;
    }
    
    public void swimmingDown(Node n){
        
        
        
        Path path = new Path();
        path.getElements().add(new MoveTo(5,5));
        QuadCurveTo qt = new QuadCurveTo();
        qt.setX(20);
        qt.setY(15);
        qt.setControlX(15);
        qt.setControlY(20);
        path.getElements().add(qt);
        PathTransition pathT = new PathTransition();
        pathT.setDuration(Duration.millis(800));
        pathT.setPath(path);
        pathT.setNode(n);
        pathT.setCycleCount(1);
        pathT.setAutoReverse(true);
        
        
        RotateTransition rt = new RotateTransition(Duration.millis(800), n);
        rt.setFromAngle(-15);
        rt.setToAngle(2);
        
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(rt,pathT);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        //return n;
        
    }
    
    public void swimmingUp(Node n){
        
        
        Path path = new Path();
        path.getElements().add(new MoveTo(5,5));
        QuadCurveTo qt = new QuadCurveTo();
        qt.setX(20);
        qt.setY(15);
        qt.setControlX(15);
        qt.setControlY(20);
        path.getElements().add(qt);
        PathTransition pathT = new PathTransition();
        pathT.setDuration(Duration.millis(800));
        pathT.setPath(path);
        pathT.setNode(n);
        pathT.setCycleCount(1);
        pathT.setAutoReverse(true);
        
        RotateTransition rt = new RotateTransition(Duration.millis(800), n);
        rt.setFromAngle(15);
        rt.setToAngle(-2);
        
        
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(rt,pathT);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        //return n;
        
    }
    
    public void moveNatural(Node n){
        Path path = new Path();
        path.getElements().add(new MoveTo(5,5));
        //path.getElements().add(new CubicCurveTo(5,0,3,1,2,1));
        QuadCurveTo qt = new QuadCurveTo();
        qt.setX(20);
        qt.setY(15);
        qt.setControlX(15);
        qt.setControlY(20);
        
        
        
        path.getElements().add(qt);
        //path.getElements().add(st);
        PathTransition pathT = new PathTransition();
        pathT.setDuration(Duration.millis(250));
        //pathT.setDuration(Duration.millis(250));
        pathT.setPath(path);
        pathT.setNode(n);
        
        // Would be infefinite if breathing or something
        // pathT.setCycleCount(Timeline.INDEFINITE);
        pathT.setCycleCount(1);
        //pathT.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathT.setAutoReverse(true);
   
        
        ScaleTransition st = new ScaleTransition(Duration.millis(250),n);
        st.setFromX(.01);
        st.setFromY(1);
        st.setToX(1);
        st.setToY(1);
        st.setCycleCount(1);
        
        ScaleTransition st2 = new ScaleTransition(Duration.millis(300),n);
        st2.setFromX(1.3f);
        st2.setFromY(1);
        st2.setToX(1);
        st2.setToY(1);
        st2.setCycleCount(1);
        
  
        
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(pathT,st2);
        //st2.play();
        
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        //st2.play();
        
        
    }
    
    public void jumpNatural(Node n){
        Path path = new Path();
        path.getElements().add(new MoveTo(5,5));
        //path.getElements().add(new CubicCurveTo(5,0,3,1,2,1));
        QuadCurveTo qt = new QuadCurveTo();
        qt.setX(15);
        qt.setY(10);
        qt.setControlX(15);
        qt.setControlY(-30);
        
        
        
        path.getElements().add(qt);
        //path.getElements().add(st);
        PathTransition pathT = new PathTransition();
        pathT.setDuration(Duration.millis(350));
        //pathT.setDuration(Duration.millis(250));
        pathT.setPath(path);
        pathT.setNode(n);
        
        // Would be infefinite if breathing or something
        pathT.setCycleCount(1);
        //pathT.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathT.setAutoReverse(true);
   
        
        ScaleTransition st = new ScaleTransition(Duration.millis(250),n);
        st.setFromX(.01);
        st.setFromY(1);
        st.setToX(1);
        st.setToY(1);
        st.setCycleCount(1);
        
        ScaleTransition st2 = new ScaleTransition(Duration.millis(300),n);
        // Used to be 3 - gone to 5
        st2.setFromX(1.5f);
        st2.setFromY(1);
        st2.setToX(1);
        st2.setToY(1);
        st2.setCycleCount(1);
        
        
        // Setting the old graphic, so we can have a
        // shaddow come up and back down
        curButton = (Button)n;
        oldGraphic = curButton.getGraphic();
        
        // Shaddow
        Ellipse el = new Ellipse();
        el.setCenterX(0);
        el.setCenterY(-20);
        el.setRadiusX(7.0f);
        el.setRadiusY(1.7f);
        el.setTranslateY(23);
        el.setStyle("-fx-opacity: 0.3;");
        
        ScaleTransition st3 = new ScaleTransition(Duration.millis(720),el);
        // Used to be 3 - gone to 5
        st3.setFromX(1.0f);
        st3.setFromY(0.9);
        st3.setToX(0);
        st3.setToY(0);

        st3.setCycleCount(1);
        curButton.setGraphic(el);
  
        
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(pathT,st2,st3);
        //st2.play();
        
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        //b1.setGraphic(oldGraphic);
        parallelTransition.setOnFinished((e)->{
            
            curButton.setGraphic(oldGraphic);
        });
        //st2.play();
        
        
    }
    
    
    
    
    
}
