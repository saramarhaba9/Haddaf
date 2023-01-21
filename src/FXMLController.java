
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class FXMLController implements Initializable {

    @FXML private Pane pane ;
    @FXML private ComboBox cBox1;
    @FXML private TextField random_code ;
    @FXML private ComboBox cBox2;
    @FXML private WebView webView;
    @FXML private Label lab;
    @FXML private DatePicker date;
     
    @FXML
    protected void handlerAction(ActionEvent Action){
        pane.setVisible(true);
        WebEngine webEngine = webView.getEngine ();  //        Create a web engine object
        if (cBox1.getSelectionModel().getSelectedIndex()==0)
            webEngine.load ("https://goo.gl/maps/h58H1eit88H6dbLH6");
        else if (cBox1.getSelectionModel().getSelectedIndex()==1)
            webEngine.load ("https://goo.gl/maps/rfiubxzq67ZiaA8Z9");
        else if (cBox1.getSelectionModel().getSelectedIndex()==2)
            webEngine.load ("https://goo.gl/maps/B4H8v3pVwjUD2Dj78");
        else if (cBox1.getSelectionModel().getSelectedIndex()==3)
            webEngine.load ("https://goo.gl/maps/2hoKM3TWN6TqJYJU9");
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
    
    private void End(ActionEvent event) throws Exception{
        random_code.setText(randomId());
        if(mysql.insertToMatch(randomInt(),cBox1.getValue().toString(), cBox2.getValue().toString(),
                "Private", random_code.getText())){
            lab.setVisible(true);
            random_code.setVisible(true);
            Alert alert = new Alert(AlertType.CONFIRMATION, "تم إنشاء المباراة بنجاح أنتقل الى الصفحة الرئسية  " + random_code.getText() ,
                ButtonType.YES);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES){
                newScene(event, "signHome.fxml");
            }
        }else{
            Alert alert = new Alert(AlertType.ERROR, "لقد تم حجز الملعب في هذا الوقت.. اختر وقت أخر أو ملعب أخر   " ,
                ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    protected void handler_Action(ActionEvent Action) throws Exception{
        if (cBox1.getValue()==null|| date.getValue()==null || 
                cBox2.getValue()==null){
            Alert alert = new Alert(AlertType.ERROR, 
                    "الرجاء إكمال ملء الحقول السابقة" ,  ButtonType.OK);
            alert.showAndWait();
            System.out.println("الرجاء إكمال ملء الحقول السابقة");
        } else {
            End(Action);
        }
    }
  
    private String randomId(){
        Random r = new Random();
        char c1 = (char)(r.nextInt(26) + 'A');
        int randomNum = r.nextInt((99999 - 1000) + 1) + 10000;
        char c2 = (char)(r.nextInt(26) + 'A');
        return ""+c1+randomNum+c2;
    }
    
    private int randomInt(){
        double randNumber = Math.random();
        //Type cast double to int
        return (int)(randNumber * 100);
    }
    
    private void newScene(Event event,String pageName) throws IOException {
        Parent Scene = FXMLLoader.load(getClass().getResource(pageName));
        Scene s11 = new Scene (Scene);
        Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setScene(s11);
        window.show();
    }
     
    @FXML
    private void publicMatch(Event e ) throws IOException { 
        System.out.println(cBox1.getValue());
        System.out.println(date.getValue());
        if (cBox1.getValue()==null || date.getValue()== null
                || cBox2.getValue()==null){
            Alert alert = new Alert(AlertType.ERROR, "الرجاء إكمال ملء الحقول السابقة" ,
                ButtonType.OK);
            alert.showAndWait();
            System.out.println("الرجاء إكمال ملء الحقول السابقة");
        }else if(mysql.insertToMatch(randomInt(),cBox1.getValue().toString(), cBox2.getValue().toString(), "Public", random_code.getText()))
            newScene(e, "signHome.fxml");
        else{
            Alert alert = new Alert(AlertType.ERROR, "لقد تم حجز الملعب في هذا الوقت.. اختر وقت أخر أو ملعب أخر   " ,
                ButtonType.OK);
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList ( "ملعب ومبلي ، الحسينية ","ملعب الجوهرة  ، الحسينية","ملعب درة الحسينية ، الحسينية","ملعب الاسباني ، الراشدية");
        cBox1.setItems(list);
        ObservableList<String> list2 = FXCollections.observableArrayList ( "4 : 00 PM","5 : 30 PM","7 : 00 PM","8 : 30 PM","10 : 00 PM","12 : 30 AM","2 : 00 AM");
        cBox2.setItems(list2);
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