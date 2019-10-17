package cn.rong.zlx.test.entity;

public class Subject {

    private Integer subjectWordId;
    private String content;
    private String joinPeople1;
    private String publishPeople1;
    private String joinPeople2;
    private String publishPeople2;
    private String joinPeople3;
    private String publishPeople3;

    public Integer getSubjectWordId() {
        return subjectWordId;
    }

    public void setSubjectWordId(Integer subjectWordId) {
        this.subjectWordId = subjectWordId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJoinPeople1() {
        return joinPeople1;
    }

    public void setJoinPeople1(String joinPeople1) {
        this.joinPeople1 = joinPeople1;
    }

    public String getPublishPeople1() {
        return publishPeople1;
    }

    public void setPublishPeople1(String publishPeople1) {
        this.publishPeople1 = publishPeople1;
    }

    public String getJoinPeople2() {
        return joinPeople2;
    }

    public void setJoinPeople2(String joinPeople2) {
        this.joinPeople2 = joinPeople2;
    }

    public String getPublishPeople2() {
        return publishPeople2;
    }

    public void setPublishPeople2(String publishPeople2) {
        this.publishPeople2 = publishPeople2;
    }

    public String getJoinPeople3() {
        return joinPeople3;
    }

    public void setJoinPeople3(String joinPeople3) {
        this.joinPeople3 = joinPeople3;
    }

    public String getPublishPeople3() {
        return publishPeople3;
    }

    public void setPublishPeople3(String publishPeople3) {
        this.publishPeople3 = publishPeople3;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectWordId=" + subjectWordId +
                ", content='" + content + '\'' +
                ", joinPeople1='" + joinPeople1 + '\'' +
                ", publishPeople1='" + publishPeople1 + '\'' +
                ", joinPeople2='" + joinPeople2 + '\'' +
                ", publishPeople2='" + publishPeople2 + '\'' +
                ", joinPeople3='" + joinPeople3 + '\'' +
                ", publishPeople3='" + publishPeople3 + '\'' +
                '}';
    }
}
