package com.voxcast.model;

import java.util.ArrayList;
import java.util.List;

public class Comment {

    private Poster poster;
    private String msg;
    private String isDel;
    private List<Comment> comments = new ArrayList<Comment>();

    /**
     * 
     * @return
     *     The poster
     */
    public Poster getPoster() {
        return poster;
    }

    /**
     * 
     * @param poster
     *     The poster
     */
    public void setPoster(Poster poster) {
        this.poster = poster;
    }

    /**
     * 
     * @return
     *     The msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 
     * @param msg
     *     The msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 
     * @return
     *     The isDel
     */
    public String getIsDel() {
        return isDel;
    }

    /**
     * 
     * @param isDel
     *     The isDel
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    /**
     * 
     * @return
     *     The comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * 
     * @param comments
     *     The comments
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
