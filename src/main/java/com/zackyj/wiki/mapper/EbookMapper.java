package com.zackyj.wiki.mapper;

import com.zackyj.wiki.model.pojo.Ebook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EbookMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Ebook record);

    int insertSelective(Ebook record);

    Ebook selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Ebook record);

    int updateByPrimaryKey(Ebook record);

    List<Ebook> selectAll();
}