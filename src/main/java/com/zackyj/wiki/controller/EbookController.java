package com.zackyj.wiki.controller;

import com.zackyj.wiki.model.pojo.Ebook;
import com.zackyj.wiki.model.resp.CommonResp;
import com.zackyj.wiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(){
        List<Ebook> list = ebookService.list();
        return CommonResp.success(list);
    }
}
