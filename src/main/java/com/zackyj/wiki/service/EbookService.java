package com.zackyj.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zackyj.wiki.mapper.EbookMapper;
import com.zackyj.wiki.model.pojo.Ebook;
import com.zackyj.wiki.model.req.EbookReq;
import com.zackyj.wiki.model.resp.EbookResp;
import com.zackyj.wiki.model.resp.PageResp;
import com.zackyj.wiki.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;

    private static final Logger Log = LoggerFactory.getLogger(EbookService.class);

    public List<Ebook> selectAll(){
        return ebookMapper.selectAll();
    }

    public PageResp<EbookResp> list(EbookReq ebookReq) {
        PageHelper.startPage(ebookReq.getPage(), ebookReq.getSize());

        List<Ebook> ebookList = ebookMapper.selectByName(ebookReq.getName());
        List<EbookResp> ebookRespList = CopyUtil.copyList(ebookList, EbookResp.class);
        PageInfo<Ebook> ebookRespPageInfo = new PageInfo<>(ebookList);
        Log.info("总行数{}",ebookRespPageInfo.getTotal());
        Log.info("总页数{}",ebookRespPageInfo.getPages());
        return new PageResp<>(ebookRespPageInfo.getTotal(),ebookRespList);
    }
}
