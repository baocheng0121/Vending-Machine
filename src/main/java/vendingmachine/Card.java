package vendingmachine;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Card {
    private String cardNo;
    private String name;
    private boolean valid;
    public Card(String cardNo, String name) {
        this.cardNo = cardNo;
        this.name = name;
        valid=false;
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("Cards.json"));
            String jsonName=(String)jsonObject.get(cardNo);
            if (jsonName!=null&&jsonName.equals(this.name)){
                valid=true;
            }else{
                valid=false;
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValid(){
        return  valid;
    }

}
