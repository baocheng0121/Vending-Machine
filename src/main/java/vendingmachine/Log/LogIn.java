package vendingmachine.Log;

import java.util.List;

//this class has the responsibility of checking the role
//of user as well as check if the username and password matches
public class LogIn {
    //This method is calling when the user click the log in button,
    // if the user information is valid, return true.
    public static User checkUser(String username, String password, String path){
        List<User> users = FileManager.readUserList(path);
        for(User user : users){
            if(username.equals(user.getUserName())){
                if (password.equals(user.getPassword())){
                    return user;
                } else {
                    return null;
                }
            }
        }
        return null;
    }


    public static String checkType(String username){
        List<User> users = FileManager.readUserList("src/main/java/vendingmachine/Log/UserInformation.txt");
        for (User user : users){
            if (username.equals(user.getUserName())){
                return user.getType();
            }
        }
        return null;
    }
}
