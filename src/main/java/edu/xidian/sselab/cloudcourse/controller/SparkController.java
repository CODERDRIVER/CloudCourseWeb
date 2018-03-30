package edu.xidian.sselab.cloudcourse.controller;

import edu.xidian.sselab.cloudcourse.domain.SparkRecord;
import edu.xidian.sselab.cloudcourse.repository.SparkRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/spark")
public class SparkController {

    @Autowired
    private SparkRecordRepository sparkRecordRepository;

    @GetMapping("")
    public String get(Model model)
    {
        model.addAttribute("title","spark结果展示");
        return "spark";
    }

    @PostMapping("")
    public String post(Model model,@RequestParam("eid") String eid)
    {
        long id = Long.parseLong(eid);
        List<SparkRecord> sparkRecords = sparkRecordRepository.findAllRecordById(id);
        model.addAttribute("title","spark处理结果展示");
        model.addAttribute("sparkRecords",sparkRecords);
        return "spark";
    }
    /*@PostMapping("")
    public void post(Model model, HttpServletResponse httpServletResponse,@RequestParam("eid") String eid) throws IOException
    {
        long id = Long.parseLong(eid);
        List<SparkRecord> sparkRecords = sparkRecordRepository.findAllRecordById(id);
        Gson gson = new Gson();
        String json =  gson.toJson(sparkRecords);
        httpServletResponse.getWriter().write(json);
    }*/
}
