/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nawal
 */
public class matchController implements Initializable {

    @FXML private ToggleButton p2;
    @FXML private ToggleGroup team;
    @FXML private ToggleButton p1;
    @FXML private Circle p11;
    @FXML private Circle p12;
    @FXML private Circle p13;
    @FXML private Circle p14;
    @FXML private Circle p15;
    @FXML private Circle p16;
    @FXML private Circle p17;
    @FXML private Circle p18;
    @FXML private Circle p19;
    @FXML private Circle p110;
    @FXML private Circle p111;
    @FXML private Circle p21;
    @FXML private Circle p22;
    @FXML private Circle p23;
    @FXML private Circle p24;
    @FXML private Circle p25;
    @FXML private Circle p26;
    @FXML private Circle p27;
    @FXML private Circle p28;
    @FXML private Circle p29;
    @FXML private Circle p210;
    @FXML private Circle p211;
    @FXML private Circle p112;
    @FXML private Circle notAvaialble;
    @FXML private Circle[] m;
    @FXML private Pane stadium;
    private int matchid;
    private int teamid;
    private int p;
    Stage window;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        notAvaialble.setFill(new ImagePattern(new Image("im/playe.png")));
    }    

    public void set(int matchid){
        this.matchid = matchid ;
    }
    
    @FXML
    private void playerPage(MouseEvent event) throws IOException {
        Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
        for (int l = 1; l <= 2; l++) {
            for (int j = 1; j < 12; j++) {
                String name = mysql.checkplayer(matchid, l, j);
                if(name!=null && name.equals((String)s.getUserData())){
                    Alert alert= new Alert (Alert.AlertType.WARNING, "! لا يمكنك التسجيل أكثر من مرة " );
                    alert.setHeaderText("لقد سجلت في هذه المبارة مسبقا");
                    alert.setTitle("خطأ");
                    alert.showAndWait();
                    return ;
                }
            }
        }        
        String temp = ((Node)event.getSource()).getId().substring(2);
        p = Integer.parseInt(temp);
        FXMLLoader loader = new FXMLLoader (getClass().getResource("playervideo.fxml"));
        Parent rooot = loader.load();
        // https://www.youtube.com/watch?v=wxhGKR3PQpo&ab_channel=BroCode
        PlayervideoController videoController = loader.getController(); // scene 2 controler 
        videoController.setter(matchid, teamid, p);
        window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setScene(new Scene(rooot));
        window.show();
    }
 
    @FXML
    private void ChooseTeam (Event event){
        String c = ((Node)event.getSource()).getId(); // p1 p2 
        if (c.equals("p1")){ // team blue 
            System.out.println("team 1 blue ");
            setTeam(1, true);
            p112.setFill(Color.rgb(30, 144, 255));
            p112.setStroke(Color.rgb(30, 144, 255));
            setTeam(2, false);
            teamid = 1;
        } else if (c.equals("p2")){ // team yellow 
            System.out.println("team 2 ");
            setTeam(2, true);
            p112.setFill(Color.YELLOW);
            p112.setStroke(Color.YELLOW);
            setTeam(1,false);
            teamid = 2;
        } 
    }
    
   @FXML
   private void hoverColor(MouseEvent event){
       ((Node)event.getSource()).setStyle("-fx-background-color:white;");
   }
    
   @FXML
   private void hover(MouseEvent event){
        ((Node)event.getSource()).setStyle("-fx-background-radius:9;"
               + "-fx-background-color: #045225;"
                + "-fx-text-fill: white;");
   }
    
   @FXML
   private void release(MouseEvent event){
       ((Node)event.getSource()).setStyle("-fx-background-color:  transparent;"
               + "-fx-border-radius:9;"
               + "-fx-border-color:#045225;"
               +  "-fx-text-fill: #045225;");
   }

   @FXML
    private void releasedColor(MouseEvent event){
       ((Node)event.getSource()).setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0.5, 0.0, 0.0);");
   }

    private void setTeam(int no, boolean visible){
        if (visible)
            loopout:
            for (int i = 1; i < 12; i++) {
                Circle c = select(no,i);
                if (!mysql.checkavailablty(matchid, no, i)){
                    // the image is from https://favpng.com/png_view/animation-football-player-jersey-animation-png/BbfH61vX
                    ImagePattern k = new ImagePattern (new Image("im/playe.png"));
                    c.setFill(k);
                    c.setOnMouseClicked(e-> disable());
                    c.setStrokeWidth(0);
                }
                c.setVisible(visible);
            }
        else
            for (int i = 1; i < 12; i++) 
            select(no,i).setVisible(visible);
    }
    
    @FXML
    private void back(MouseEvent e) throws IOException{
        Stage stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        String page ;
        page = "sc4.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(page));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   }
    
    private Circle select (int teamId,int p ){
        if (teamId==1){
            if (p==1)
                return p11;
            else if (p==2)
                return p12;
            else if (p==3)
                return p13;
            else if (p==4)
                return p14;
            else if (p==5)
                return p15;
            else if (p==6)
                return p16;
            else if (p==7)
                return p17;
            else if (p==8)
                return p18;
            else if (p==9)
                return p19;
            else if (p==10)
                return p110;
            else if (p==11)
                return p111;
            else
                return null;
        } else if (teamId == 2 ){
            if (p==1)
                return p21;
            else if (p==2)
                return p22;
            else if (p==3)
                return p23;
            else if (p==4)
                return p24;
            else if (p==5)
                return p25;
            else if (p==6)
                return p26;
            else if (p==7)
                return p27;
            else if (p==8)
                return p28;
            else if (p==9)
                return p29;
            else if (p==10)
                return p210;
            else if (p==11)
                return p211;
            else
                return null;
        } else 
            return null;
    }
    
    private void disable (){
        Alert alert = new Alert (Alert.AlertType.WARNING, " اختر موقع آخر " ,
                ButtonType.OK);
        alert.setHeaderText("تم حجز هذا الموقع مسبقا");
        alert.setTitle("خطأ");
        alert.showAndWait();
    }
    
}