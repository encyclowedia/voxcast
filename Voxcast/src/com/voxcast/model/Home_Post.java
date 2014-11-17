
package com.voxcast.model;

import com.google.gson.annotations.Expose;

public class Home_Post {

    private String statusCode;
    private Result result;

    /**
     * 
     * @return
     *     The statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * 
     * @param statusCode
     *     The statusCode
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * 
     * @return
     *     The result
     */
    public Result getResult() {
        return result;
    }

    /**
     * 
     * @param result
     *     The result
     */
    public void setResult(Result result) {
        this.result = result;
    }

}
