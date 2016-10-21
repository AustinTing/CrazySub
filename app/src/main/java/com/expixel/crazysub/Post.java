package com.expixel.crazysub;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cellbody on 2016/9/30.
 */

public class Post {
    public String userImgUrl;
    public String userName;
    public String imgUrl;
    public Long postTime;
    public String subtitle;
    public String description;
    public int likeCount;
    public int commentCount;
    public Map<String, Boolean> likes = new HashMap<>();


    public Post() {
    }

    public Post(String userImgUrl, String userName, String imgUrl, Long postTime, String subtitle, String description, int likeCount, int commentCount) {
        this.userImgUrl = userImgUrl;
        this.userName = userName;
        this.imgUrl = imgUrl;
        this.postTime = postTime;
        this.subtitle = subtitle;
        this.description = description;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }
}
