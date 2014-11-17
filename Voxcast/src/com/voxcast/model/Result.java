
package com.voxcast.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result {

    private String Id;
    private Poster poster;
    private String msg;
    private String city;
    private List<UpVoter> upVoters = new ArrayList<UpVoter>();
    private List<DownVoter> downVoters = new ArrayList<DownVoter>();
    private String ts;
    private List<String> pics = new ArrayList<String>();
    private List<String> vid = new ArrayList<String>();
    private List<Comment> comments = new ArrayList<Comment>();

    /**
     * 
     * @return
     *     The Id
     */
    public String getId() {
        return Id;
    }

    /**
     * 
     * @param Id
     *     The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

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
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The upVoters
     */
    public List<UpVoter> getUpVoters() {
        return upVoters;
    }

    /**
     * 
     * @param upVoters
     *     The upVoters
     */
    public void setUpVoters(List<UpVoter> upVoters) {
        this.upVoters = upVoters;
    }

    /**
     * 
     * @return
     *     The downVoters
     */
    public List<DownVoter> getDownVoters() {
        return downVoters;
    }

    /**
     * 
     * @param downVoters
     *     The downVoters
     */
    public void setDownVoters(List<DownVoter> downVoters) {
        this.downVoters = downVoters;
    }

    /**
     * 
     * @return
     *     The ts
     */
    public String getTs() {
        return ts;
    }

    /**
     * 
     * @param ts
     *     The ts
     */
    public void setTs(String ts) {
        this.ts = ts;
    }

    /**
     * 
     * @return
     *     The pics
     */
    public List<String> getPics() {
        return pics;
    }

    /**
     * 
     * @param pics
     *     The pics
     */
    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    /**
     * 
     * @return
     *     The vid
     */
    public List<String> getVid() {
        return vid;
    }

    /**
     * 
     * @param vid
     *     The vid
     */
    public void setVid(List<String> vid) {
        this.vid = vid;
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
