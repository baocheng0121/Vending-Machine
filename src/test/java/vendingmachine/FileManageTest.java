package vendingmachine;
import org.junit.Test;
import vendingmachine.Log.FileManager;
import vendingmachine.Log.User;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import static org.junit.Assert.*;

public class FileManageTest {

    public static String readLast(String path){
        File file=new File(path);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(fileReader);
        String line = null;
        while((sc.hasNextLine()&&(line=sc.nextLine())!=null)){
            if(!sc.hasNextLine()){
                sc.close();
                return line;
            }
        }
        sc.close();
        return null;
    }

    @Test
    public void writeTest() throws FileNotFoundException {
        String path = "src/test/java/vendingmachine/userInfoTest.txt";
        FileManager.write("user", "password", "normal", path);
        String a = readLast(path);
        assertEquals("user,password,normal,none,none,none,none,none", a);
        PrintWriter pw = new PrintWriter(path);
        pw.close();
    }

    @Test
    public void readListTest() throws FileNotFoundException {
        String path = "src/test/java/vendingmachine/userInfoTest.txt";
        FileManager.write("user", "password", "normal", path);
        User user = FileManager.readUserList(path).get(0);
        assertEquals(user.getUserName(), "user");
        assertEquals(user.getPassword(), "password");
        assertEquals(user.getType(), "normal");
        PrintWriter pw = new PrintWriter(path);
        pw.close();
    }

    @Test
    public void CheckAnonymousItemTest() throws IOException {
        String path = "src/test/java/vendingmachine/machineListTest.txt";
        FileManager.writeAnonymousList(path);
        List<String> res = FileManager.checkAnonymousItem(path);
        assertEquals("none", res.get(0));
        PrintWriter pw = new PrintWriter(path);
        pw.close();
    }

    @Test
    public void anonymousBuyTest() throws IOException{
        String path = "src/test/java/vendingmachine/machineListTest.txt";
        FileManager.writeAnonymousList(path);
        FileManager.anonymousItem("cola", path);
        List<String> res = FileManager.checkAnonymousItem(path);
        assertEquals("cola", res.get(0));
        PrintWriter pw = new PrintWriter(path);
        pw.close();
    }

    @Test
    public void normalUserListTest() throws IOException{
        String path = "src/test/java/vendingmachine/userInfoTest.txt";
        FileManager.write("user", "password", "normal", path);
        String[] res = FileManager.checkUserItem(new User("normal", "user", "password"), path);
        assertEquals("none", res[0]);
        PrintWriter pw = new PrintWriter(path);
        pw.close();
    }

    @Test
    public void normalUserBuyTest() throws IOException{
        String path = "src/test/java/vendingmachine/userInfoTest.txt";
        FileManager.write("user", "password", "normal", path);
        FileManager.refreshUserItem(new User("normal", "user", "password"), "cola", path);
        String[] res = FileManager.checkUserItem(new User("normal", "user", "password"), path);
        assertEquals("cola", res[0]);
        PrintWriter pw = new PrintWriter(path);
        pw.close();

    }

    @Test
    public void deleteUserTest() throws IOException{
        String path = "src/test/java/vendingmachine/userInfoTest.txt";
        FileManager.write("user1", "password1", "normal", path);
        FileManager.write("user2", "password2", "normal", path);
        FileManager.deleteUser(new User("normal", "user1", "password1"), path);
        List<User> user = FileManager.readUserList(path);
        assertEquals(user.size() , 1);
        PrintWriter pw = new PrintWriter(path);
        pw.close();
    }

    @Test
    public void cancelLogTest() throws IOException{
        String path = "src/test/java/vendingmachine/CancelTestLogTest.txt";
        FileManager.cancelLog("alan", "timeout", path);
        List<String> log = FileManager.readCanceledLog("src/test/java/vendingmachine/CancelTestLogTest.txt");
        assertEquals(log.get(0).split(",")[1], "timeout");
        PrintWriter pw = new PrintWriter(path);
        pw.close();
    }
}
