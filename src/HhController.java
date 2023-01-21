import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author jomanah
 */

public class HhController implements Initializable {
    @FXML private StackPane parentContainer;
    ImageView[]  ig = {new ImageView(new Image("im/image3_14.gif")),
        new ImageView(new Image("im/image4_3.png"))};
    int noImage ; 
    int currentImage ; 
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sliding();
    }  
    
    @FXML
    private void sliding(){
        // try 3 
        noImage= ig.length;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e ->nextSlide()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        parentContainer.setOnMouseClicked(e -> {
            try {
                Alert a = new Alert (Alert.AlertType.WARNING,"يجب عليك تسجيل الدخول أولا"); 
                a.setTitle("تنيه");
                a.setHeaderText(" لم تسجل دخولك بعد   !  ");
                a.showAndWait();
                sgin(e);
            } catch (IOException ex) {
                Logger.getLogger(HhController.class.getName()).log(Level.SEVERE, null, ex);
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
        if(currentImage % ig.length==0)
            newScene(event,"playervideo.fxml");
        else
            newScene(event,"match.fxml");
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
    private void sgin(Event e) throws IOException{
        newScene(e,"sc2.fxml");
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
