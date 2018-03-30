package edu.xidian.sselab.cloudcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @PathVaribale 获取url中的数据
 * @RequestParam 获取请求参数的值
 * @GetMapping组合注解，是@RequestMapping(method=RequestMethod.GET)的缩写
 */
@Controller
@RequestMapping("/alarm")
public class AlarmController {
    
    @GetMapping("")
    public String alarm(Model model) {
        model.addAttribute("title", "实时报警");
        model.addAttribute("hint", "此为加分项，动手能力强的同学可以尝试完成。");
        return "alarm";
    }
    
}
