
package administration.sql;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionManager {
        static  String DB_URL = "jdbc:postgresql://127.0.0.1:5555/asd";
        static  String USER = "user";
        static  String PASS = "user";
        static  String TableName = "users";
        
        static Connection conn = null;
        
        public static Connection connect(){
            
            if(conn != null)
            {   
                return conn;
            }
            try {
		Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
		return null;
            }
            
            Connection connection = null;
 
            try {
		connection = DriverManager
		.getConnection(DB_URL, USER, PASS);
            } catch (SQLException e) {
                    return null;
            }
 
            if (connection != null) {
                conn = connection;
                return conn;
            } else {
                return null;
            }
        }
        
        public static void disconnect(){
            if(conn != null){
                try{
                    conn.close();              
                }catch(SQLException | NullPointerException e){
                    
                }finally{
                    conn = null;
                }
            }
        }

}
