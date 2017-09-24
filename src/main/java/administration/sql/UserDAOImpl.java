
package administration.sql;

import administration.data.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAOImpl implements UserDAO{
    
    private Connection conn = null;
    private final String TableName = ConnectionManager.TableName;
    
    @Override
    public User getUser(String name) {
        conn = ConnectionManager.connect();
        if(conn != null){
            PreparedStatement state;
            ResultSet result;
            String statement = "SELECT * FROM " + TableName + " WHERE name = ?;";
            try{
                state = conn.prepareStatement(statement);
                state.setString(1, name);
                result = state.executeQuery();
                if(result == null)
                    return null;
                if(result.next()){
                    User user = new User();
                    user.setName(result.getString("name"));
                    user.setPassword(result.getString("password"));
                    user.setEmail(result.getString("email"));
                    user.setRegistration_time(new Date((result.getTimestamp("registration_time")).getTime()));
                    user.setLast_edit_time(new Date((result.getTimestamp("last_edit_time")).getTime()));
                    user.setRole(result.getString("role"));
                    return user;
                }else
                    return null;
            }catch(SQLException e){
                return null;
            }finally{
                ConnectionManager.disconnect();
            }
        }
        else{
            return null;
        }
    }

    @Override
    public List <User> getUsers(){
        conn = ConnectionManager.connect();
        if(conn != null){
            ArrayList <User> users = new ArrayList<>();
            PreparedStatement state;
            ResultSet result;
            String statement = "SELECT * FROM " + TableName + " ;";
            try{
                state = conn.prepareStatement(statement);
                result = state.executeQuery();
                if(result == null)
                    return null;
                while(result.next()){
                    User user = new User();
                    user.setName(result.getString("name"));
                    user.setPassword(result.getString("password"));
                    user.setEmail(result.getString("email"));
                    user.setRegistration_time(new Date((result.getTimestamp("registration_time")).getTime()));
                    user.setLast_edit_time(new Date((result.getTimestamp("last_edit_time")).getTime()));
                    user.setRole(result.getString("role"));
                    users.add(user);
                }
            }catch(SQLException e){
                return null;
            }finally{
                ConnectionManager.disconnect();
            }
            return users;
        }
        else
            return null;
    };
    
    @Override
    public String addUser(User user) {
        conn = ConnectionManager.connect();
        String error="";
        if(conn != null){
            PreparedStatement state;
            String statment = "INSERT INTO " + TableName + " VALUES (?,md5(?),?,?,?,?);";
            try{
                conn.setAutoCommit(false);
                state = conn.prepareStatement(statment);
                state.setString(1, user.getName());
                state.setString(2, user.getPassword());
                state.setString(3, user.getEmail());
                Date date = user.getRegistration_time(); 
                Timestamp timestamp = new Timestamp(date.getTime()); 
                state.setTimestamp(4, timestamp);
                date = user.getLast_edit_time(); 
                timestamp = new Timestamp(date.getTime()); 
                state.setTimestamp(5, timestamp);
                state.setString(6, user.getRole());
                state.executeUpdate();
                conn.commit();
            }catch(SQLException e){
                return error + e.toString();
            }finally{
                ConnectionManager.disconnect();
            }
            return "OK";
        }
        else{
            return "Sorry, failed to connect to database";
        }
    }

    @Override
    public String changeUser(User user) {
        conn = ConnectionManager.connect();
        String error="";
        if(conn != null){
            PreparedStatement state;
            String statement;
            if(user.getPassword() != null)
                statement = "UPDATE " + TableName + " SET email=?, "
                        + "registration_time=?, last_edit_time=?, role=?, password=md5(?) WHERE name=?;";
            else
                statement = "UPDATE " + TableName + " SET email=?, "
                        + "registration_time=?, last_edit_time=?, role=? WHERE name=?;";
            try{
                conn.setAutoCommit(false);
                String value;
                state = conn.prepareStatement(statement);
                state.setString(1, user.getEmail());
                Date date = user.getRegistration_time(); 
                Timestamp timestamp = new Timestamp(date.getTime()); 
                state.setTimestamp(2, timestamp);
                date = user.getLast_edit_time(); 
                timestamp = new Timestamp(date.getTime()); 
                state.setTimestamp(3, timestamp);
                state.setString(4, user.getRole());
                if(user.getPassword() != null){
                    state.setString(5, user.getPassword());
                    state.setString(6, user.getName());
                }else{
                    state.setString(5, user.getName());
                }
                state.executeUpdate();
                conn.commit();
            }catch(SQLException e){
                return error + e.toString();
            }finally{
                ConnectionManager.disconnect();
            }
            return "OK";
        }
        else{
            return "Sorry, failed to connect to database";
        }
    }

    @Override
    public String deleteUser(String name) {
        conn = ConnectionManager.connect();
        String error="";
        if(conn != null){
            PreparedStatement state;
            String statement = "DELETE FROM " + TableName + " WHERE name=?;";
            try{
                conn.setAutoCommit(false);
                state = conn.prepareStatement(statement);
                state.setString(1, name);
                state.executeUpdate();
                conn.commit();
            }catch(SQLException e){
                return error + e.toString();
            }catch(Exception e){
                return error + e.toString();
            }finally{
                ConnectionManager.disconnect();
            }
            return "OK";
        }
        else{
            return "Sorry, failed to connect to database";
        }
    }
}
