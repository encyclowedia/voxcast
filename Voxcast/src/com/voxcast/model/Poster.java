
package com.voxcast.model;

import com.google.gson.annotations.Expose;

public class Poster {

    private String uId;
    private String usrImg;
    private String name;

    /**
     * 
     * @return
     *     The uId
     */
    public String getUId() {
        return uId;
    }

    /**
     * 
     * @param uId
     *     The uId
     */
    public void setUId(String uId) {
        this.uId = uId;
    }

    /**
     * 
     * @return
     *     The usrImg
     */
    public String getUsrImg() {
        return usrImg;
    }

    /**
     * 
     * @param usrImg
     *     The usrImg
     */
    public void setUsrImg(String usrImg) {
        this.usrImg = usrImg;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

}
