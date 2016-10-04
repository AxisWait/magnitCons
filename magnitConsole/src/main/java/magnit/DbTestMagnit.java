package magnit;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/**
 * Created by Антон on 27.08.2016.
 */
public class DbTestMagnit {
   private Connection connection;



    public DbTestMagnit()
       {
           user.setURL("jdbc:mysql://localhost:3306/testmagnit");
           user.setUSERNAME("root");
           user.setPASSWORD("root");
           try {
               Driver driver = new FabricMySQLDriver();
               DriverManager.registerDriver(driver);
               connection = DriverManager.getConnection(user.getURL(),user.getUSERNAME(),user.getPASSWORD());

           } catch (SQLException e)
           {
               System.err.println("Опаньки");
           }
       }
    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
