
package administration.sql;

import administration.data.User;
import java.util.List;

public interface UserDAO {
    User getUser(String name);
    List <User> getUsers();
    String addUser(User user);
    String changeUser(User user);
    String deleteUser(String name);  
}
