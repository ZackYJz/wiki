package com.zackyj.wiki.model.req;

public class EbookUpdateReq {
    private Long id;

    private String name;

    private Long category1Id;

    private Long category2Id;

    private String description;

    private String cover;

    @Override
    public String toString() {
        return "EbookUpdateReq{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       ", category1Id=" + category1Id +
                       ", category2Id=" + category2Id +
                       ", description='" + description + '\'' +
                       ", cover='" + cover + '\'' +
                       '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(Long category1Id) {
        this.category1Id = category1Id;
    }

    public Long getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(Long category2Id) {
        this.category2Id = category2Id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}