package com.article.familly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GalleryController {

    @GetMapping("/gellery")
    public String gelleryList (){
        return "gellery/gellery-list";
    }
}

