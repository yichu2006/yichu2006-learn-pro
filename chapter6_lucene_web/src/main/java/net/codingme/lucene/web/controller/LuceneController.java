package net.codingme.lucene.web.controller;

import com.alibaba.fastjson.JSON;
import net.codingme.lucene.web.model.FileModel;
import net.codingme.lucene.web.service.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * lucene查询控制类
 */
@Controller
@RequestMapping(value = "/search")
public class LuceneController {

    @Autowired
    private LuceneService luceneService;

    @GetMapping(value = "/list")
    public String searchList(String keyword, Integer num, Model model) throws Exception {
        List<FileModel> fileModelList = luceneService.getTopN(keyword, num);
//        System.out.println(JSON.toJSONString(fileModelList));
        model.addAttribute("fileModelList", fileModelList);
        model.addAttribute("pageNum", fileModelList.size());
        return "result";
    }

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
}

