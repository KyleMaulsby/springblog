package com.codeup.springblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RollDieController {
    @GetMapping("/roll-dice")
    public String showDice(Model model){
        return "roll-dice";
    }
    @GetMapping("/roll-dice/{num}")
    public String showRoll(@PathVariable int num, Model model){
        model.addAttribute("guess", num);
        int roll1 = (int)Math.ceil(Math.random()*6);
        int roll2 = (int)Math.ceil(Math.random()*6);
        int roll3 = (int)Math.ceil(Math.random()*6);
        int count = 0;
        if (roll1 == num){
            count += 1;
        }
        if (roll2 == num){
            count += 1;
        }
        if (roll3 == num){
            count += 1;
        }
        model.addAttribute("result", roll1+", "+roll2+", and "+roll3);
        model.addAttribute("count",count);
        return "Roll";
    }
//    @PostMapping("roll-dice/{num}")
//    public int rollDice(@PathVariable int num, Model model){
//        model.addAttribute("roll",num);
//        return num;
//    }
}
