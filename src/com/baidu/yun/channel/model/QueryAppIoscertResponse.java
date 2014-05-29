package com.baidu.yun.channel.model;

import com.baidu.yun.core.annotation.JSonPath;

public class QueryAppIoscertResponse extends ChannelResponse {

    @JSonPath(path = "response_params\\name")
    private String name;

    @JSonPath(path = "response_params\\description")
    private String description;

    @JSonPath(path = "response_params\\release_cert")
    private String releaseCert;

    @JSonPath(path = "response_params\\dev_cert")
    private String devCert;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseCert() {
        return releaseCert;
    }

    public void setReleaseCert(String releaseCert) {
        this.releaseCert = releaseCert;
    }

    public String getDevCert() {
        return devCert;
    }

    public void setDevCert(String devCert) {
        this.devCert = devCert;
    }

}
