import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;


public class FXMLDocumentController implements Initializable {
    private Stage stage;
    // the voice effect that appear are from https://youtu.be/-QDCQ9UritI
    private MediaPlayer mediaplayer2;
    private int sec = 5; 
    @FXML private MediaView audio; 
    boolean flag = false ; 
    @FXML AnchorPane an ;
    
    @FXML
    private void keycl(Event e) throws IOException{
        flag =true; 
        Parent root = FXMLLoader.load(getClass().getResource("hh.fxml"));
        stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   
    }
    
    @FXML 
    private void enter(KeyEvent e) throws Exception{
        System.out.println(e.getCode());
        if (e.getCode() == KeyCode.ENTER)
            keycl(e);
    }
    
    @FXML
    private void newVoice()throws IOException{
        mediaplayer2 = new MediaPlayer (new Media (this.getClass().getResource("im/wow.mpeg").toExternalForm()));
        mediaplayer2.setAutoPlay(true);
        audio.setMediaPlayer(mediaplayer2);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            // TODO
            newVoice();
//            Timer t = new Timer (); 
//            t.s
            Timeline timer = new Timeline ();
            timer.setCycleCount(5);//Timeline.INDEFINITE
            if (timer!=null)
                timer.stop();
            KeyFrame frame = new KeyFrame (Duration.seconds(1), e->{
                sec= sec-1; 
                System.out.println(sec);
                if (sec <= 0){
                    timer.stop();
                    
                    Parent root = null;
                    try{
                        root = FXMLLoader.load(getClass().getResource("hh.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    stage =(Stage) an.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    mediaplayer2.stop();
                    stage.show();   
                } 
                if (flag){
                    timer.stop();
                    mediaplayer2.stop();
                }
            });
            timer.getKeyFrames().add(frame);
            timer.playFromStart();
            
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}