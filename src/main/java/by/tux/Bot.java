package by.tux.Lesson_25;

import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "OveroneWeather_TuXbot";
    }

    @Override
    public String getBotToken() {
        return "5484876352:AAEwjpJ-S-Zph0l2GHSaAAxbj2Z3I5eQj5U";
    }

    @Override
    public void onUpdateReceived(Update update) {
        try{
            List<City> list = new ArrayList<>();
            list.add(new City("����", "53.891667", "25.302254"));
            list.add(new City("�����", "53.893009", "27.567444"));
            list.add(new City("������", "53.669353", "23.813131"));
            list.add(new City("�������", "55.184217" , "30.202878"));
            list.add(new City("������", "52.424160" , "31.014281"));
            list.add(new City("������", "53.894548" , "30.330654"));
            list.add(new City("�����", "52.093754" , "23.685107"));
            String lat = "" , lon = "";
            for (City c : list){
                if (c.getName().toLowerCase().equals(update.getMessage().getText().toLowerCase())){
                    URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+ c.getLat() +"&lon="+ c.getLon() +"&appid=d51a78d090fa7653e461eac94138aca2");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                    String data = bufferedReader.readLine();
                    JSONObject jsonObject = new JSONObject(data);
                    String main = jsonObject.get("main").toString();
                    jsonObject = new JSONObject(main);
                    String temp = jsonObject.get("temp").toString();

                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(update.getMessage().getChatId());
                    sendMessage.setText( " ������ � " + c.getName() + " " +  new DecimalFormat("##.#").format(Double.valueOf(temp)-273.00) + " �C");
                    execute(sendMessage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
