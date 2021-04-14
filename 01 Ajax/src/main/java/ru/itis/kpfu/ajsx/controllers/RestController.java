package ru.itis.kpfu.ajsx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.kpfu.ajsx.dto.JSONResp;
import ru.itis.kpfu.ajsx.services.DataGenerator;

import java.awt.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    DataGenerator r;

    @RequestMapping(value = "/models/{brand-id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public JSONResp getModels(@PathVariable("brand-id") Integer id){
        return JSONResp.builder().data(r.generateHTML(id)).build();
    }
}
