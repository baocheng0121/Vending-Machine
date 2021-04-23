package vendingmachine.Log;

public class User {
    private String type;
    private String userName;
    private String password;

    public User(String type, String userName, String password) {
        this.type = type;
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType(){
        return this.type;
    }



}
