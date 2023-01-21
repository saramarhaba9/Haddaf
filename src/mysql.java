/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author Manar
 */
public class mysql {
    
    //insert user
    public static String insertToUser(String username,String name,String password,String mobile,String userLevel){
        
        if(checkUser(username)){
            User u = new User(username,name,password,mobile,userLevel);
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(u);
            tx.commit();
            session.close();
            return("تم إنشاء الحساب ");
        }
        
        return("يوجد حساب بنفس الاسم");
    }
    public static String updateUser(String username,String name,String mobile,String userLevel){
        
        if(checkUser(username)){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update User set name= '"+name+"',mobile='"+mobile+"',userLevel='"+userLevel+"' where username = '"+username+"'");
            query.executeUpdate();
            tx.commit();
            session.close();
            return("تم التغيير ");
        }
        
        return("يوجد خطأ");
    }
    
    //cheack if username exit or not
    private static boolean checkUser(String username){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from User where username = '"+ username+"' ");
        tx.commit();
        List<User> user = query.list();
        session.close();
        
        if(!user.isEmpty()){
            if(user.get(0).getUsername().equals(username))
            return true;
        }
        return false;
    }
    
    //cheack if username exit or not
    public static User UserData(String username){ //******
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from User where username = '"+ username+"' ");
        tx.commit();
        List<User> user = query.list();
        session.close();
        if(!user.isEmpty())
            return (User)user.get(0);
        return null;
    }
    
    //validation for login will return true 
    public static boolean validation(String username ,String password){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from User where username = '"+username+"' and password = '"+password+"' ");
        tx.commit();
        List<User> user = query.list();
        session.close();
        
        if(!user.isEmpty()){
            User u = user.get(0);
            if(username.equals(u.getUsername()) && password.equals(u.getPassword()))
                return true;
        }
        return false;
    }
    
    public static boolean updatepass(String username ,String password){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from User where username = '"+username+"'");
        tx.commit();
        List<User> user = query.list();
        session.close();
        if(!user.isEmpty()){
            User u = user.get(0);
            if(username.equals(u.getUsername())){
                session = HibernateUtil.getSessionFactory().openSession();
                tx = session.beginTransaction();
                query = session.createQuery("update User set password = '"+password+"' where username = '"+username+"'");
                query.executeUpdate();
                tx.commit();
                session.close();
                return true;
            }
       }
       return false;
    }

    //insert if there is no pre-booking
    public static boolean  insertToMatch(int id,String stadiumName, String time, String type, String code){
        if(checkMatch(stadiumName,time)){
            Match0 m = new Match0(id , stadiumName , time , type , code);
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(m);
            tx.commit();
            session.close();
            insertToTeam(id,1);
            insertToTeam(id,2);
            return true;
        }
        return false;
    }
    
    //check if stadiumName is blooked
    private static boolean checkMatch(String stadiumName , String time){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Match0 where stadiumName = '"+stadiumName+"' and time0 = '"+time+"' ");
        tx.commit();
        List<Match0> m = query.list();
        session.close();
        if(!m.isEmpty()){
            if(stadiumName.equals(m.get(0).getStadiumName()) && time.equals(m.get(0).getTime()))
                return false;
        }
        return true;
    }
    
    //if code correct return true
    public static boolean cheackCode(String code){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Match0 where code = '"+ code+"' ");
        tx.commit();
        List<Match0> match = query.list();
        session.close();
         if(!match.isEmpty()){
            if(code.equals(match.get(0).getCode()))
                return true;
        }
        return false;
    }
    
    
    //to create team
    public static String insertToTeam(int match_id, int team_id /*, int p,String username*/ ){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("insert into Team(match_id,team_id" // ,p"+p+") "
                + ")values("+match_id+","+team_id+")");
                //,'"+username+"')");
        query.executeUpdate();
        tx.commit();
        session.close();
        return("team created");
    }
    
    //insert player -- hint p = button no.
    public static String insertPlayer(int match_id, int team_id, int p, String username){
        if(checkavailablty( match_id , team_id , p )){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery("update team set p"+p+"= '"+username+"' where match_id = "+ match_id+" and team_id = "+team_id);
            query.executeUpdate();
            tx.commit();
            session.close();
            return ("added");
        }
        return ("booked");
    }
    
    //check if plyer no. available -- true if availble , false unavailble
    public static boolean checkavailablty(int match_id, int team_id, int p){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("select p"+p+" from team where match_id = "+ match_id+" and team_id = "+team_id);
        tx.commit();
        List<Team> team = query.list();
        session.close();
         if(!team.isEmpty()){
            if(team.get(0) == null)
                return true;
        }
        return false;
    }
    public static boolean Evalidation(String username){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from User where username = '"+ username+"' ");
        tx.commit();
        List<User> user = query.list();
        session.close();
        
        if(!user.isEmpty()){
            User u = user.get(0);
            if(username.equals(u.getUsername()))
                return true;
        }
        
        return false;
    }
    public static String checkplayer(int match_id, int team_id, int p){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("select p"+p+" from team where match_id = '"+ match_id
                +"' and team_id = '"+team_id+"';");
        String tea = (String)query.list().get(0);
        tx.commit();
        session.close();
        if(tea!=null)
            return tea;
        return null;
    }
    
    public static int getMatchId(String code){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Match0 where code = '"+ code+"' ");
        List <Match0> match = query.list();
        tx.commit();
        session.close();
        int id= -1;
        if(!match.isEmpty()){
            id= match.get(0).getId();
        }
        return id;
    }
    
    public static List<Match0> getMatch(){
        List<Match0> list = null; 
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Match0");
        tx.commit();
        list = query.list();
        session.close();
        return list;
    } 
}