package com.zackyj.wiki.service;

import com.zackyj.wiki.mapper.EbookMapper;
import com.zackyj.wiki.model.pojo.Ebook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;

    public List<Ebook> list(){
        return ebookMapper.selectAll();
    }
}
