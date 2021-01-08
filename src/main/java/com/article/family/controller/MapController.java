package com.article.family.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @GetMapping("/map/wedding-map")
    public String weddingMap(){
        return "map/wedding-map";
    }
}
