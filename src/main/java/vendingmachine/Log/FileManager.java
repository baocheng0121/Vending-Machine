package vendingmachine.Log;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;

public class FileManager{

//"src/main/java/vendingmachine/Log/UserInformation.txt"
    public static void write(String username, String password, String type, String path){
        String writeIn = username + "," + password + "," + type + ",none,none,none,none,none" + '\n';
        File f = new File(path);
        try(FileWriter fr = new FileWriter(f, true)){
            char[] chars = writeIn.toCharArray();
            fr.write(chars);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //this one is used to set a anonymous user file
    //reminder: only call it once
    public static void writeAnonymousList(String path){
        String writeIn = "none,none,none,none,none";
        File f = new File(path);
        try(FileWriter fr = new FileWriter(f)){
            char[] chars = writeIn.toCharArray();
            fr.write(chars);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //"src/main/java/vendingmachine/Log/CancelLog.txt"
    public static void cancelLog(String username, String reason, String path){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        String writeIn = username + "," + reason + "," + time + '\n';
        File f = new File(path);
        try(FileWriter fr = new FileWriter(f, true)) {
            char[] chars = writeIn.toCharArray();
            fr.write(chars);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //"src/main/java/vendingmachine/Log/CancelLog.txt"
    public static List<String> readCanceledLog(String path){
        List<String> res = new ArrayList<>();
        try{
            FileReader fr = new FileReader(path);
            BufferedReader bf = new BufferedReader(fr);
            String cancelLog;
            while((cancelLog = bf.readLine()) != null){
                res.add(cancelLog);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return res;
    }



    //this method is used to read the users and put them into a list
    public static List<User> readUserList(String path){
        List<User> users = new ArrayList<>();
        try{
            FileReader fr = new FileReader(path);
            BufferedReader bf = new BufferedReader(fr);
            String userInfo;
            while((userInfo = bf.readLine()) != null){
                String[] info = userInfo.split(",");
                users.add(new User(info[2], info[0], info[1]));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return users;
    }

    //usage:
    //when an anonymous user buy an item
    //calling this method with parameter of item name in String format to store the item into database
    //"src/main/java/vendingmachine/Log/AnonymousItem.txt"
    public static void anonymousItem(String item, String path) throws FileNotFoundException {
        String writing = null;
        try{
            FileReader fr = new FileReader(path);
            BufferedReader bf = new BufferedReader(fr);
            String info;
            String[] temp = new String[5];
            while((info = bf.readLine()) != null){
                temp = info.split(",");
                for(int i = temp.length - 1; i > 0; i--){
                     temp[i] = temp[i - 1];
                }
                temp[0] = item;
            }
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < temp.length; i++){
                if(i <= 3){
                    builder.append(temp[i]).append(",");
                }else{
                    builder.append(temp[i]);
                }
            }
            writing = builder.toString();
            bf.close();
            fr.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        PrintWriter pw = new PrintWriter(path);
        pw.close();

        File f = new File(path);
        try(FileWriter fr = new FileWriter(f)){
            char[] chars = writing.toCharArray();
            fr.write(chars);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //usage:
    //when an anonymous user wants to check the previous item list
    //calling this method and receive a list of items written by String format
    public static List<String> checkAnonymousItem(String path){
        List<String> res = new ArrayList<>();
        try{
            FileReader fr = new FileReader(path);
            BufferedReader bf = new BufferedReader(fr);
            String item;
            while((item = bf.readLine()) != null){
                String[] temp = item.split(",");
                for (String s : temp) {
                    res.add(s);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    //usage:
    //when an normal user wants to check the previous item list
    //calling this method by sending the user object and receive the string array
    //"src/main/java/vendingmachine/Log/UserInformation.txt"
    public static String[] checkUserItem(User user, String path){
        String username = user.getUserName();

        String[] items = new String[5];
        try{
            List<String> lines =  Files.readAllLines(Paths.get(path));

            for(String info : lines){
                String[] findName = info.split(",");
                if(findName[0].equals(username)){
                    for(int i = 3; i < findName.length; i++){
                        items[i - 3] = findName[i];
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
    //"src/main/java/vendingmachine/Log/UserInformation.txt"
    public static void refreshUserItem(User user, String itemName, String path) throws IOException {
        List<String> lines = new ArrayList<>();
        try{
            lines = Files.readAllLines(Paths.get(path));
            for(int s = 0; s < lines.size(); s++){
                String[] findName = lines.get(s).split(",");
                if(findName[0].equals(user.getUserName())){
                    for(int i = findName.length - 1; i > 3; i--){
                        findName[i] = findName[i - 1];
                    }
                    findName[3] = itemName;
                    StringBuilder builder = new StringBuilder();
                    for(int i = 0; i < findName.length; i++){
                        if(i != 7){
                            builder.append(findName[i]).append(",");
                        }else{
                            builder.append(findName[i]);
                        }
                    }
                    lines.set(s, builder.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter pw = new PrintWriter(path);
        pw.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for(String line : lines){
            writer.write(line + "\r\n");
        }
        writer.close();

    }

//"src/main/java/vendingmachine/Log/UserInformation.txt"

    public static void deleteUser(User user, String path) throws IOException {
        List<String> lines = new ArrayList<>();
        try{
            lines = Files.readAllLines(Paths.get(path));
            for(int s = 0; s < lines.size(); s++){
                String[] findName = lines.get(s).split(",");
                if(findName[0].equals(user.getUserName())){
                    lines.remove(s);
                    s--;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter pw = new PrintWriter(path);
        pw.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for(String line : lines){
            writer.write(line + "\r\n");
        }
        writer.close();

    }
}