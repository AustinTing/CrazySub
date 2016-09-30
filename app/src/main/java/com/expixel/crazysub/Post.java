package com.expixel.crazysub;

/**
 * Created by cellbody on 2016/9/30.
 */

public class Post {
    public String userImgUrl;
    public Long postTime;
    public String subtitle;
    public String description;
    public int startCount;
    public int commentCount;

    public Post(String userImgUrl, Long postTime, String subtitle, String description, int startCount, int commentCount) {
        this.userImgUrl = userImgUrl;
        this.postTime = postTime;
        this.subtitle = subtitle;
        this.description = description;
        this.startCount = startCount;
        this.commentCount = commentCount;
    }
}
