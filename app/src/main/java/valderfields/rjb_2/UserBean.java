package valderfields.rjb_2;

/**
 * 用户实体
 * Created by 11650 on 2017/5/26.
 */

public class UserBean {

    private String UID;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String hobbies;

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UID:["+UID+"],Name:["+name+"],Phone:["+phone+"],Email:["+email+"],Password:["
                +password+"],Hobbies:["+hobbies+"]";
    }
}
