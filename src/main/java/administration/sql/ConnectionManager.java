
package administration.sql;

import java.sql.*;

public class ConnectionManager {
        static final String DB_URL = "jdbc:postgresql://127.0.0.1:5555/asd";
        static final String USER = "user";
        static final String PASS = "user";
        static final String TableName = "users";
        
        static Connection conn = null;
        
        static String error = "";
        
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
