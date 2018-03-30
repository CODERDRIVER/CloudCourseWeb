package edu.xidian.sselab.cloudcourse.controller;

import com.google.gson.Gson;
import edu.xidian.sselab.cloudcourse.domain.Record;
import edu.xidian.sselab.cloudcourse.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/track")
public class TrackController {

    @Autowired
    private TrackRepository trackRepository;


    @GetMapping("")
    public String track(Model model) {
        model.addAttribute("title", "轨迹重现");
//        List<Record> list = trackRepository.findAllRecordByEid(eid);
          List list = new ArrayList<>();
//        list.add(new Record("12342",new Date().getTime(),12313,"西电",120.715963,30.808332));
//        list.add(new Record("12343",new Date().getTime(),12313,"西电1",120.721932,30.889239));
//        list.add(new Record("12344",new Date().getTime(),12313,"西电2",120.734351,30.624624));
//        list.add(new Record("12345",new Date().getTime(),12313,"西电3",120.703535,30.782247));
//        list.add(new Record("12346",new Date().getTime(),12313,"西电4",120.74173,30.694405));
        model.addAttribute("list",list);
        return "track";
    }

    @PostMapping("")
    public void track(HttpServletResponse httpServletResponse, @RequestParam("eid") String eid) throws IOException {
        List<Record> list = new ArrayList<>();
        list = trackRepository.findAllRecordByEid(eid);
        Collections.sort(list, new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-type","text/html;charset=UTF-8");
        Gson gson = new Gson();
        String json = gson.toJson(list);
        httpServletResponse.getWriter().write(json);
        System.out.println(list.get(0));
    }
    
}
