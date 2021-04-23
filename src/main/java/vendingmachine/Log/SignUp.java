package vendingmachine.Log;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class SignUp {
    //this class has the responsibility of write the new user into file
    //and check whether the database has same username as well.
    public static User writeInFile(String username, String password, String type){
        List<User> users = FileManager.readUserList("src/main/java/vendingmachine/Log/UserInformation.txt");
        for(User user : users){
            if(username.equals(user.getUserName())){
                System.out.println("the user name has been signed");
                return null;
            }
        }
        FileManager.write(username, password, type, "src/main/java/vendingmachine/Log/UserInformation.txt");
        return new User(type, username, password);
    }
}
