package vendingmachine;

import org.junit.Before;
import org.junit.Test;
import vendingmachine.Log.FileManager;
import vendingmachine.Log.User;
import vendingmachine.MachineEngine;
import vendingmachine.Role.Owner;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class OwnerTest {
    Owner owner;
    User user;
    String userPath;
    String logPath;
    @Before
    public void setUp() {
        MachineEngine machineEngine = new MachineEngine();
        owner = new Owner("name", "password", machineEngine);
        user = new User("owner", "username", "password");
        userPath = "src/test/java/vendingmachine/userInfoTest.txt";
        logPath = "src/test/java/vendingmachine/CancelTestLogTest.txt";
    }

    @Test
    public void deleteUser() throws IOException {
        FileManager.write(user.getUserName(), user.getPassword(), user.getType(), userPath);
        owner.deleteUser(user, userPath);
        List<User> users = FileManager.readUserList(userPath);
        assertEquals(users.size(), 0);
        PrintWriter pw = new PrintWriter(userPath);
        pw.close();
    }

    @Test
    public void addUser() {
        owner.addUser(user, userPath);
        List<User> users = FileManager.readUserList(userPath);
        assertEquals(users.get(0).getUserName(), user.getUserName());
    }

    @Test
    public void showCancelTran() {
        List<String> log = owner.showCancelTran(logPath);
        assertNotNull(log);
    }

    @Test
    public void showUserList() {
        Map<String, String> log = owner.showUserList(userPath);
        assertNotNull(log);
    }

    @Test
    public void changePrice() {

    }

    @Test
    public void showCashList() {
    }

    @Test
    public void showTransactionList() {
    }

    @Test
    public void itemReport() {

    }
}