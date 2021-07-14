package com.zackyj.wiki.controller;

import com.zackyj.wiki.model.pojo.Ebook;
import com.zackyj.wiki.model.req.EbookQueryReq;
import com.zackyj.wiki.model.req.EbookUpdateReq;
import com.zackyj.wiki.model.resp.CommonResp;
import com.zackyj.wiki.model.resp.EbookResp;
import com.zackyj.wiki.model.resp.PageResp;
import com.zackyj.wiki.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/allbook")
    public CommonResp<List<Ebook>> allbook(){
        List<Ebook> list = ebookService.selectAll();
        return CommonResp.success(list);
    }

    @GetMapping("/list")
    public CommonResp<PageResp<EbookResp>> list(EbookQueryReq ebookReq){
        PageResp<EbookResp> list = ebookService.list(ebookReq);
        return CommonResp.success(list);
    }

    @PostMapping("/save")
    public CommonResp updateBookInfo(@RequestBody EbookUpdateReq ebookReq){
        ebookService.updateBookInfo(ebookReq);
        return CommonResp.success();
    }
}
