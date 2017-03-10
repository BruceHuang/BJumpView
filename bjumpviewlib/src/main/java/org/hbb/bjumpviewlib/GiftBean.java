package org.hbb.bjumpviewlib;

/**
 * Created by huangxueqing on 17/2/2.
 */


import java.io.Serializable;

public class GiftBean implements Serializable {

    private String gfid = "";
    private String giftname = "";
    private String nkName="";
    private String hits = "1";

    private long time;
    private String type;
    private String uid="";

    public String getNkName() {
        return nkName;
    }

    public void setNkName(String nkName) {
        this.nkName = nkName;
    }

    public GiftBean() {

    }

    public String getGfid() {
        return gfid;
    }

    public void setGfid(String gfid) {
        this.gfid = gfid;
    }

    public String getGiftname() {
        return giftname;
    }

    public void setGiftname(String giftname) {
        this.giftname = giftname;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "GiftBean{" +
                "gfid='" + gfid + '\'' +
                ", giftname='" + giftname + '\'' +
                ", hits='" + hits + '\'' +
                ", time=" + time +
                ", type='" + type + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}