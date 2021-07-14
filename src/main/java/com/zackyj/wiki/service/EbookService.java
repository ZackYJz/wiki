package com.zackyj.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.zackyj.wiki.mapper.EbookMapper;
import com.zackyj.wiki.model.pojo.Ebook;
import com.zackyj.wiki.model.req.EbookReq;
import com.zackyj.wiki.model.resp.EbookResp;
import com.zackyj.wiki.model.resp.PageResp;
import com.zackyj.wiki.util.CopyUtil;
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

    public List<Ebook> selectAll(){
        return ebookMapper.selectAll();
    }

    public PageResp<EbookResp> list(EbookReq ebookReq) {
        PageHelper.startPage(ebookReq.getPage(), ebookReq.getSize());
        List<Ebook> ebookList = ebookMapper.selectByName(ebookReq.getName());
        List<EbookResp> ebookRespList = CopyUtil.copyList(ebookList, EbookResp.class);
        PageInfo<EbookResp> ebookRespPageInfo = new PageInfo<>(ebookRespList);
        return new PageResp<>(ebookRespPageInfo.getTotal(),ebookRespList);
    }
}
