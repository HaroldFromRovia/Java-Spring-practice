package ru.itis.kpfu.ajsx.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DataGenerator {
    public String generateHTML(int id){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++){
            sb.append("<option name=\"" + id + "\">" + id + "</option>\n");
        }
        return sb.toString();
    }

    public ArrayList<String> getPosts(){
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            arr.add("Post" + i);
        }
        return arr;
    }
}
