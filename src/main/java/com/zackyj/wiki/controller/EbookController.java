package com.zackyj.wiki.controller;

import com.zackyj.wiki.model.pojo.Ebook;
import com.zackyj.wiki.model.req.EbookReq;
import com.zackyj.wiki.model.resp.CommonResp;
import com.zackyj.wiki.model.resp.EbookResp;
import com.zackyj.wiki.model.resp.PageResp;
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

    @GetMapping("/allbook")
    public CommonResp allbook(){
        List<Ebook> list = ebookService.selectAll();
        return CommonResp.success(list);
    }

    @GetMapping("/list")
    public CommonResp list(EbookReq ebookReq){
        PageResp<EbookResp> list = ebookService.list(ebookReq);
        return CommonResp.success(list);
    }
}
