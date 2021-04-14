package ru.itis.kpfu.ajsx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.kpfu.ajsx.services.DataGenerator;

import java.util.ArrayList;

@Controller
public class TestController {
    @Autowired
    DataGenerator r;


    @GetMapping("/1")
    public String get(ModelMap map){
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            arr.add("test" + i);
        }
        map.put("brends", arr);
        return "test";
    }

    @GetMapping("/2")
    public String get2(){
        return "test2";
    }

    @GetMapping("/posts")
    public String getPosts(ModelMap map){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put("posts", r.getPosts());
        return "posts";
    }
}
