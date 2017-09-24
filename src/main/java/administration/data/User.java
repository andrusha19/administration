
package administration.data;

import java.util.Date;

public class User{

    
    private String name;
    private String password;
    private String email;
    private Date registration_time;
    private Date last_edit_time;
    private String role;
    
    public User(String name,String password, String email, Date registration_time, Date last_edit_time, String role){
        this.name=name;
        this.password=password;
        this.email=email;
        this.registration_time=registration_time;
        this.last_edit_time=last_edit_time;
        this.role=role;
    }
    
    public User(){
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegistration_time(Date registration_time) {
        this.registration_time = registration_time;
    }

    public void setLast_edit_time(Date last_edit_time) {
        this.last_edit_time = last_edit_time;
    }
    
    public void setRole(String role){
        this.role=role;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getRegistration_time() {
        return registration_time;
    }

    public Date getLast_edit_time() {
        return last_edit_time;
    }
    public String getRole(){
        return role;
    }
}
