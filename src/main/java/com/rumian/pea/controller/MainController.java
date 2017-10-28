package com.rumian.pea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

   @RequestMapping("/")
   public String getMainPage(Model model) throws InterruptedException {
      model.addAttribute("message","X");
      return "resultPage";
   }
}
