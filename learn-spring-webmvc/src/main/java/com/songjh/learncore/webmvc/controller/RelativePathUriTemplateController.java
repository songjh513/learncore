package com.songjh.learncore.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Writer;

@Controller
public class RelativePathUriTemplateController {

    @RequestMapping(path = "/owners/{ownerId}/pets/{petId}", method = RequestMethod.GET, params="petId=myValue")
    public void findPet(@PathVariable String ownerId, @PathVariable String petId, Model model) {
        // implementation omitted
    }


    @RequestMapping(path = "/something", method = RequestMethod.POST)
    @ResponseBody
    public String  handle(@RequestBody String body, Writer writer) throws IOException {

        return "aaa";
    }


}