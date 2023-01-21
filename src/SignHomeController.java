import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SignHomeController implements Initializable {
    // sliding window animation 
    // https://www.youtube.com/watch?v=fp-8kpIT8rk
    //https://www.youtube.com/watch?v=cqskg3DYH8g&feature=youtu.be&ab_channel=GenuineCoder  
    @FXML private StackPane parentContainer;
    ImageView[]  ig = {new ImageView(new Image("im/image3_14.gif")),new ImageView(new Image("im/image4_3.png"))};
    int noImage ; 
    int currentImage ; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            sliding();
        } catch (IOException ex) {
            Logger.getLogger(SignHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    @FXML
    private void sliding() throws IOException{
    // try 3 
        ig[0].setId("41");
        ig[1].setId("39");
        noImage= ig.length;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e ->nextSlide()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        
        parentContainer.setOnMouseClicked(e -> {
            FXMLLoader loader = new FXMLLoader (getClass().getResource("match.fxml"));
            Parent rooot=null;
            try {
                rooot = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(SignHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            matchController matchcontroller = loader.getController(); // scene 2 controler 
            if (timeline.getStatus() == Animation.Status.RUNNING) {
                timeline.pause();
                matchcontroller.set(Integer.parseInt(ig[currentImage].getId().trim()));
                Stage window = (Stage)(((Node)e.getSource()).getScene().getWindow());
                window.setScene(new Scene(rooot));
                window.show();
            }

        });
        parentContainer.getChildren().add(ig[currentImage++]);
    }    
    
    private void nextSlide() {
        parentContainer.getChildren().clear();
        parentContainer.getChildren().add(ig[(currentImage++) % ig.length]);
    }
    
    @FXML
    private void slide2(Event event) throws IOException{
        if(currentImage % ig.length==0){
            newScene(event,"playervideo.fxml");
        }else{
            newScene(event,"match.fxml");
        }
    }
    
    @FXML 
    private void matchs (Event event) throws IOException{
        newScene(event,"sc4.fxml");
    }
    
    @FXML 
    private void personal (Event e ) throws IOException{
        FXMLLoader loader = new FXMLLoader (getClass().getResource("profile.fxml"));
        Parent rooot = loader.load();
        Profile1Controller profileController = loader.getController(); // scene 2 controler 
        Stage window = (Stage)(((Node)e.getSource()).getScene().getWindow());
        window.setScene(new Scene(rooot));
        
        profileController.set(window);
        window.show();
    }
    
    private void newScene(Event event,String pageName) throws IOException {
        Parent Scene = FXMLLoader.load(getClass().getResource(pageName));
        Scene s11 = new Scene (Scene);
        Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setScene(s11);
        window.show();
    }
    
    @FXML 
    private void us(Event e) throws IOException{
        newScene(e,"FXML.fxml");
    }
    
    @FXML 
    private void hover (Event e ) { 
        ((Node)e.getSource()).setEffect(new DropShadow (BlurType.GAUSSIAN , Color.rgb(0, 0, 0, 0.3), 10, 0.5 , 0.0, 0.0));
    }
    
    @FXML
    private void realese (Event e ) { 
        ((Node)e.getSource()).setEffect(null);
    }
    
    @FXML 
    private void logout(Event e) throws IOException{
        ((Node)e.getSource()).getScene().getWindow().setUserData(null);
        newScene(e,"hh.fxml");
    }
}