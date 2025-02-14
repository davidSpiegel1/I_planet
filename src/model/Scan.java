// Will be a scanner class
// We take a txt file and build a list of nodes that are blocks
package model;

import java.util.*;
import java.io.*;

import model.Edog;


public class Scan{
    // Some needed instance variables
    private String content = "";
    private ArrayList<String> allowedChars;
    private ArrayList<Block> nodeList;// The needed node list
    private ArrayList<Integer> healthStops;
    private Character c = new Character();// Neded character object (For live/ inventory)
    private int charPos;
    
    // A constructor. I think we will use StringBuilder and FileInputStream to do the scanning
    public Scan(String fileName){
        // Our final node list
      
        
        nodeList = new ArrayList<Block>();
        healthStops = new ArrayList<Integer>();
        
        this.allowedChars = new ArrayList<String>();
        this.allowedChars = this.populateAllowedChars(allowedChars);
        
        try{
            this.content = new String(this.getFile(fileName));
            
            }catch(IOException e){
                System.out.println("Error loading file: "+e); // Change to throw something
            }
    }
    
    
    
    public ArrayList<String> populateAllowedChars(ArrayList<String> al){
        al.add("A");
        al.add("a");
        al.add("C");
        al.add("c");
        al.add(".");
        al.add("T");
        al.add("t");
        al.add("K");
        al.add("W");
        al.add("X"); // For the 'Devil'
        al.add("K");
        al.add("G");
        al.add("g");
        al.add("|");
        al.add("_");
        al.add("p");
        al.add("s");
        al.add("O"); // For door
        al.add("E"); // For enemies
        al.add("d"); // d for dog (Or cow)
        al.add("F");// For big fire
        al.add("b");// For blackish grey background
        al.add("B");// For black ground
        al.add("f");// For a flower
        al.add("w");// For a web
        al.add("o"); // For a room
        al.add("u"); // For spider
        al.add(">");// For a gate
        al.add("e"); // For evil dog
        al.add("-"); // For a three d block
        al.add("#"); // For wood
        al.add("$"); // For wooden weapon
        al.add("^"); // Palm Tree
        //al.add("x"); // For gun
        al.add("v"); // For Alyosha
        al.add("V"); // For Alyosha too (Maybe will need tis char)
        al.add("<"); // For snowy tree
        al.add("*"); // For snow
        al.add(","); // For jacket effect
        al.add("?"); // For bunny
        al.add(";"); // For Dmitri
        al.add("{"); // For ivans father (Fyodor)
        al.add("("); // For Ivans wife
        al.add(")"); // For evil angels
        al.add("}"); // For Pavel
        al.add("z"); // For Iyusha (Young boy in story who dies)
        al.add("Z"); // For Captain Snegiryov (Young boys father)
        al.add("+");  //
        al.add("="); //
        al.add("["); // For stone
        al.add("]"); // For wool
        al.add("N"); // For notes
        
        
    
        
        return al;
        
    }
    
    
    public ArrayList<Block> scan(){
        String content = this.content;
        int curIndex = 0;
        int row = 1;
        int col = 1;
        int simIndex = 0;
        Block finalBlock = null;
        while (curIndex <= content.length()-1){
            
            //System.out.println(content.charAt(curIndex));
            char initText = content.charAt(curIndex); // Getting the char at a specific point
            if (initText == '\n'){
                row++;
                col = 1;
               // curIndex++;
                
            }
            // Allowing for comments in our game maps
            else if(initText == '/'){
                if (curIndex+1 < content.length()){
                    if (content.charAt(curIndex+1) == '/'){
                        while (curIndex < content.length() && content.charAt(curIndex) != '\n'){
                            curIndex ++;
                            col++;
                        }
                        
                    }
                }
                
            }
            // If it is an allowed text, we will determine what it is
            else if (this.allowedChars.contains(String.valueOf(initText))){
                
                if (initText == 'C'){
                    this.nodeList.add(this.c);
                    this.charPos = curIndex;
                    
                }
                else if (initText == 'd'){
                    this.nodeList.add(new Animal(simIndex,"d",20));
                    
                }
                else if (initText == 'p'){
                    this.nodeList.add(new People(simIndex,"p",20));
                }
                else if (initText == 'E'){
                    this.nodeList.add(new Enemies(simIndex,"E",20)); // May need to fix amountCol now that it's used
                }
                else if (initText == 'e'){
                    this.nodeList.add(new Edog(simIndex,"e",20));
                }
                else if (initText == 'a'){
                    this.nodeList.add(new Gabriel(simIndex,"a",20)); // May need to fix amountCol now that it's used
                }
                else if (initText == 'u'){
                    this.nodeList.add(new Spider(simIndex,"u",20));
                }
                else if (initText == 'X'){
                    this.nodeList.add(new Devil(simIndex,"X",20));
                }
                else if (initText == 'v'){
                    this.nodeList.add(new Alyosha(simIndex,"v",20));
                }
                else if (initText == 'N'){
                    this.nodeList.add(new Notes("N"));
                }
                else{
                    if (initText == 'f'){
                        healthStops.add(simIndex);
                    }
                    this.nodeList.add(new Block(String.valueOf(initText)));
                    
                }
                
                simIndex++;
            }
            else{
               simIndex++;
            }
            curIndex++;
            
        }
        
     
        
        
        return this.nodeList;
        
    }
    
    
    public Block findBlock(String initText){
        int simIndex = 0;
        // If it is an allowed text, we will determine what it is
        if (this.allowedChars.contains(String.valueOf(initText))){
            
            if (initText.equals("E")){
                return new Enemies(simIndex,"E",20); // May need to fix amountCol now that it's used
            }
            else if (initText.equals("e")){
                return new Edog(simIndex,"e",20);
            }
            else if (initText.equals("a")){
                return new Gabriel(simIndex,"a",20); // May need to fix amountCol now that it's used
            }
            else if (initText.equals("u")){
                return new Spider(simIndex,"u",20);
            }
            else if (initText.equals("X")){
                return new Devil(simIndex,"X",20);
            }
    
            else{
                return new Block(initText);
            }
        
        
    }else{
        return null;
    }
        
    }
    
    public ArrayList<Block> getNodeList(){
        return this.nodeList;
    }
    
    public ArrayList<Integer>getHealthStops(){
        return this.healthStops;
    }
    
    public String getFile(String fileName) throws IOException{
        // Attempting to open the file
        FileInputStream fis = new FileInputStream(fileName);
        byte[] buffer = new byte[10];
        
        StringBuilder sb = new StringBuilder();
        while (fis.read(buffer) != -1){
            
            sb.append(new String(buffer));
            buffer = new byte[10];
        }
        
        String content = sb.toString();
        return content;
        
    }
    
    // Getters and setters for the character object ot try and keep it consistent
    public void setCharacter(Character c){
        
        this.c = c;
    }
    
    public int getCharPosition(){
        return this.charPos;
    }
    public Character getCharacter(){
        return this.c;
    }
    
    
  
    
   
    
   
    
}

