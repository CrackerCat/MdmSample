package com.spd.mdmsample.bean;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

/**
 * @author xuyan
 */
public class AllApnBean {

    /**
     * 增加APN
     *
     * @param apnInfo APN信息，采用JSON格式。
     *                "name"：APN描述（用于显示标题）；
     *                "apn"：APN名称；
     *                "type"：APN类型，如"default,supl"；
     *                "numeric"：运营商网络码，一般通过getSimOperator获取；
     *                "mcc"：MCC；
     *                "mnc"：MNC；
     *                "proxy"：代理；
     *                "port"：端口；
     *                "mmsproxy"：彩信代理；
     *                "mmsport"：彩信端口；
     *                "user"：用户名；
     *                "server"：服务器；
     *                "password"：密码；
     *                "mmsc"：MMSC。
     * @return 成功返回新创建APN ID；失败返回-1
     */

    private String name;
    private String apn;
    private String type;
    private String numeric;
    private String mcc;
    private String mnc;
    private String proxy;
    private String port;
    private String mmsproxy;
    private String mmsport;
    private String user;
    private String server;
    private String password;
    private String mmsc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumeric() {
        return numeric;
    }

    public void setNumeric(String numeric) {
        this.numeric = numeric;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMmsproxy() {
        return mmsproxy;
    }

    public void setMmsproxy(String mmsproxy) {
        this.mmsproxy = mmsproxy;
    }

    public String getMmsport() {
        return mmsport;
    }

    public void setMmsport(String mmsport) {
        this.mmsport = mmsport;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMmsc() {
        return mmsc;
    }

    public void setMmsc(String mmsc) {
        this.mmsc = mmsc;
    }

    @NotNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
