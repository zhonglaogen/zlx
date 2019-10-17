package cn.rong.zlx.test.entity;

public class Title {

    private Integer titleId;
    private String indexNum;
    private String sort;
    private String publishOrgan;
    private String publishDate;
    private String name;
    private String symbol;
    private Integer subjectWordId;


    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(String indexNum) {
        this.indexNum = indexNum;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPublishOrgan() {
        return publishOrgan;
    }

    public void setPublishOrgan(String publishOrgan) {
        this.publishOrgan = publishOrgan;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getSubjectWordId() {
        return subjectWordId;
    }

    public void setSubjectWordId(Integer subjectWordId) {
        this.subjectWordId = subjectWordId;
    }

    @Override
    public String toString() {
        return "Title{" +
                "titleId=" + titleId +
                ", indexNum='" + indexNum + '\'' +
                ", sort='" + sort + '\'' +
                ", publishOrgan='" + publishOrgan + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", subjectWordId=" + subjectWordId +
                '}';
    }
}
