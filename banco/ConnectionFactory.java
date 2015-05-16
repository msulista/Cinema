package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String DRIVER="org.postgresql.Driver";
    private static final String HOSTNAME="localhost";
    private static final String PORT="5432";
    private static final String DATABASE="labII_Cinema";
    private static final String URL="jdbc:postgresql://"+HOSTNAME+":"+PORT+"/"+DATABASE;
    private static final String USER="postgres";
    private static final String PASS="1234";
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);
        return(DriverManager.getConnection(URL, USER, PASS));
    } 
            
}
