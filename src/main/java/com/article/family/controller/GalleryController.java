package com.article.family.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GalleryController {

    @GetMapping("/gellery/list")
    public String gelleryList (){
        return "gellery/gellery-list";
    }
}

