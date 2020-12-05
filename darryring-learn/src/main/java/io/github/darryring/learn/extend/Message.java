package io.github.darryring.learn.extend;

public class Message {
    /**
     * 发布日期，以毫秒为单位的当前时间戳
     */
    private long time;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 跳转链接
     */
    private String url;

    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
