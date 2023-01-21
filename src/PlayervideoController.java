/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nawal
 */

public class PlayervideoController implements Initializable {
    
    @FXML private ToggleGroup team = new ToggleGroup();
    @FXML private MediaView mv; 
    @FXML private MediaView audio; 
    private MediaPlayer mediaplayer;
    private static final String url = "player.mp4"; 
    private MediaPlayer mediaplayer2;
    // the sound effect are from https://mixkit.co/free-sound-effects/game/?page=2
    private Stage window;
    private int matchid;
    private int teamId;
    private int p;

//    private void stter(){
//        FXMLLoader loader = new FXMLLoader (getClass().getResource("match.fxml"));
//        matchController mathController = loader.getController();
//        
//    }
    
    public void setter(int matchid, int teamid, int p ){
//        this.username = ;
        this.matchid = matchid;
        this.teamId = teamid;
        
        this.p = p;
        System.out.println("P= "+ p +"team ID = " + teamId);
        System.out.println("Match ID = " + matchid);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(location.toString());
        mv.setFitHeight(660);
        mv.setFitWidth(660);
        newVideo("jump" +url);
    }
       
    @FXML 
    private void right (){
        newVideo("left"+url);
        newVoice("ok.mp3");
    }
    
    @FXML 
    private void left (){
        newVideo("right"+url);
        newVoice("ok.mp3");
    }
    
    @FXML 
    private void keyevent (KeyEvent event ) throws IOException{
        KeyCode code = event.getCode();
        if(code== KeyCode.RIGHT )
            right();
        else if(code== KeyCode.LEFT)
            left();
        else if(code== KeyCode.ENTER)
            confirm(event);
        else if (code==KeyCode.ESCAPE)
            cancel(event);
    }
    
    @FXML 
    private void confirm (Event event) throws IOException{
        newVideo("jump"+url);
        // we should change the scene to go to the home screen 
        // we should add the data to the data base 
        window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        String username = (String) window.getUserData();
        System.out.println("Usernaem = " + username );
        mysql.insertPlayer(matchid, teamId, p, username);
        newScene(event,"signHome.fxml");
        newVoice("music.wav");
  
    }
    
    private void newVoice (String aud){
        mediaplayer2 = new MediaPlayer (new Media (this.getClass().getResource("im/"+aud).toExternalForm()));
        mediaplayer2.setAutoPlay(true);
        audio.setMediaPlayer(mediaplayer2);
    }
    
    private void newVideo(String video){
        mediaplayer = new MediaPlayer (new Media (this.getClass().getResource("im/"+video).toExternalForm()));
        mediaplayer.setAutoPlay(true);
        mv.setMediaPlayer(mediaplayer);
    }
    
    @FXML 
    private void cancel (Event e) throws IOException{
        FXMLLoader loader = new FXMLLoader (getClass().getResource("match.fxml"));
        Parent rooot = loader.load();
        matchController matchcontroller = loader.getController(); // scene 2 controler 
        matchcontroller.set(matchid);
        Stage window = (Stage)(((Node)e.getSource()).getScene().getWindow());
        window.setScene(new Scene(rooot));
        window.show();
    }
    
    @FXML 
    private void playerPage(MouseEvent event ) throws Exception{
        newScene(event, "playervideo.fxml");
    }
    
    private void newScene(Event event,String pageName) throws IOException {
        Parent Scene = FXMLLoader.load(getClass().getResource(pageName));
        Scene s11 = new Scene (Scene);
        window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setScene(s11);
        window.show();
    }
    
    @FXML 
    private void btbackgroundenter(Event event){
        ((Node)event.getSource()).setStyle("-fx-background-color:gray;");
    }
    
    @FXML
    private void btbackgroundexit(Event event){
        ((Node)event.getSource()).setStyle("-fx-background-color:transparent;");
    }
    
    @FXML 
    private void hover (Event e ) { 
        ((Node)e.getSource()).setEffect(new DropShadow (BlurType.GAUSSIAN , Color.rgb(0, 0, 0, 0.3), 10, 0.5 , 0.0, 0.0));
    }
    
    @FXML
    private void realese (Event e ) { 
        ((Node)e.getSource()).setEffect(null);
    }
}

