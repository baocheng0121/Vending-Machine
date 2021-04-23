package vendingmachine.Role;

import vendingmachine.Log.FileManager;
import vendingmachine.Log.User;
import vendingmachine.MachineEngine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Owner extends User {
    private MachineEngine machineEngine;
    public Owner(String name, String password, MachineEngine machineEngine) {
        super("owner", name, password);
        this.machineEngine = machineEngine;
    }
    //"src/main/java/vendingmachine/Log/UserInformation.txt"
    public void deleteUser(User user, String path) throws IOException {
        FileManager.deleteUser(user, path);
    }
    //"src/main/java/vendingmachine/Log/UserInformation.txt"
    public void addUser(User user, String path){
        FileManager.write(user.getUserName(), user.getPassword(), user.getType(), path);
    }
    //"src/main/java/vendingmachine/Log/CancelLog.txt"
    public List<String> showCancelTran(String path){
        List<String> log = FileManager.readCanceledLog(path);
        return log;
    }
    //"src/main/java/vendingmachine/Log/UserInformation.txt"
    public Map<String, String> showUserList(String path){
        Map<String, String> users = new HashMap<>();
        List<User> userList = FileManager.readUserList(path);
        for(User user : userList){
            users.put(user.getUserName(), user.getType());
        }
        return users;
    }

    public void changePrice(String name, double price){
        machineEngine.changeSnackPrice(name,price);
    }

    public void showCashList(){
        machineEngine.availableChangesList();
    }

    public void showTransactionList(){
        machineEngine.getTransactionRecord();
    }

    public void itemReport(){
        MachineEngine engine = new MachineEngine();
        engine.generateItemReport();
    }


}
