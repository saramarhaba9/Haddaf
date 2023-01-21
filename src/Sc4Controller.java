import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Sc4Controller implements Initializable {
    private int matchid ;
    @FXML private VBox vbox;
    @FXML ScrollPane sc; 
    
    @FXML
    private void playerPage(MouseEvent event) throws IOException {
        
    }
    
    @FXML
    private void buForCreMatch (ActionEvent e) throws IOException{
        newScene(e, "View.fxml");
    }
    
    @FXML
    private void enteryForPriMatch (ActionEvent e) throws IOException{
        newScene(e,"sc5.fxml");
    }
    
    @FXML
    private void textForSea (ActionEvent e) throws IOException{
        System.out.println("nee"); 
    }
    
    @FXML
    private void buForSea (ActionEvent e) throws IOException{
        System.out.println("nee"); 
    }
    
    @FXML 
    private void book(Event e) throws IOException{  // نحطها على الاكشن للمبارة ولازم نعرف الايدي تبع المبارة 
        //this method will pass the id of the match to the next scene 
        FXMLLoader loader = new FXMLLoader (getClass().getResource("match.fxml"));
        Parent rooot = loader.load();
        matchController matchcontroller = loader.getController(); // scene 2 controler 
        matchcontroller.set(matchid);
        Stage window = (Stage)(((Node)e.getSource()).getScene().getWindow());
        window.setScene(new Scene(rooot));
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
    private void back(MouseEvent e) throws IOException{
        Stage stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        String page ;
        page = "signHome.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(page));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
    List<Match0> yy= mysql.getMatch();
   // list have all the match 
        for (int i = 0; i < yy.size(); i++) {
            if(!yy.get(i).getType().equalsIgnoreCase("Private")){
            BorderPane bpMatch = new BorderPane();
            
            Label l1 = new Label();
            Integer id =yy.get(i).getId();
            l1.setText(id.toString());
            ImageView igId = new ImageView (new Image("im/id.png"));
            igId.setFitHeight(20);
            igId.setFitWidth(20);
            l1.setGraphic(igId);
            
            
            Label l2 = new Label();
            l2.setText(yy.get(i).getStadiumName());
            l2.setFont(Font.font(15));
            ImageView igst = new ImageView (new Image("im/stadium.png"));
            igst.setFitHeight(30);
            igst.setFitWidth(30);
            l2.setContentDisplay(ContentDisplay.RIGHT);
            l2.setGraphic(igst);
          
            ImageView igTime = new ImageView(new Image("im/time.png"));
            Label l3 = new Label();
            l3.setText(yy.get(i).getTime());
            igTime.setFitHeight(30);
            l3.setFont(Font.font(16));
            igTime.setFitWidth(30);
            l3.setGraphic(igTime);
            
            l3.setContentDisplay(ContentDisplay.RIGHT);
            
            Circle c = new Circle (); 
//            ip.getImage().isPreserveRatio();
            c.setFill(new ImagePattern (new Image("im/stadum.jpg")));
            c.setStrokeWidth(0);
            c.autosize();
            
            c.setRadius(40);
            
            bpMatch.setAlignment(l2, Pos.CENTER);
            BorderPane.setAlignment(l3, Pos.CENTER_RIGHT);
//            bpMatch.setAlignment(l1, Pos.CENTER);
            bpMatch.setMinWidth(320);
            bpMatch.setMaxWidth(320);
            
            bpMatch.setBottom(l2);
            bpMatch.setCenter(l3);
//            bpMatch.setCenter(l1);
            bpMatch.setLeft(c);
//            bpMatch.setAlignment(igId, Pos.CENTER);
            
//            ImageView ig = new ImageView (new Image ("im/match1.jpg")); 
            bpMatch.setStyle("-fx-background-color:#DBECE2;"+ 
                    "-fx-border-color: #1C7947;"
            +"-fx-padding:10px;");
            bpMatch.setId(id.toString());
            bpMatch.cursorProperty().set(Cursor.HAND);
            bpMatch.setOnMouseEntered(e-> hover(e));
            bpMatch.setOnMouseExited( e-> realese(e));
            bpMatch.setOnMouseClicked(e-> {
                try {
                    matchid = id; 
                    book(e);
                } catch (IOException ex){
                    Logger.getLogger(Sc4Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            vbox.getChildren().addAll(bpMatch);
            vbox.setSpacing(10);
            vbox.getChildren().sorted();
            vbox.prefHeightProperty().bind( sc.heightProperty());
        }
       }
       
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