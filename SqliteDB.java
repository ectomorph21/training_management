import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class SqliteDB {
    Connection c = null;
    Statement stmt = null;
    
    SqliteDB () {
        //try connect to db
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Connected to DB");
            c.close();
        }catch(Exception e){
            System.out.println("Error "+e.getMessage());
        }
    }

    public void connect () {

    }

}
