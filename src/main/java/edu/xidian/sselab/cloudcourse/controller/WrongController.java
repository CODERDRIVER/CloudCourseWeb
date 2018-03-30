package edu.xidian.sselab.cloudcourse.controller;

import edu.xidian.sselab.cloudcourse.domain.Record;
import edu.xidian.sselab.cloudcourse.repository.WrongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/wrong")
public class WrongController {
    @Autowired
    private WrongRepository wrongRepository;
    @GetMapping("")
    public String get(Model model) {
        List<Record> wrongList = new ArrayList<>();
        model.addAttribute("title", "错误数据显示");
        wrongList = wrongRepository.findAllByRecord();
        model.addAttribute("wrongList",wrongList);
        return "wrong";
    }
}
