package com.project.samuliak.psychogram.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Questionnaire {
    @SerializedName("ask1")
    @Expose
    public String ask1;
    @SerializedName("ask2")
    @Expose
    public String ask2;
    @SerializedName("ask3")
    @Expose
    public Integer ask3;
    @SerializedName("ask4")
    @Expose
    public Integer ask4;
    @SerializedName("ask5")
    @Expose
    public String ask5;
    @SerializedName("ask6")
    @Expose
    public String ask6;
    @SerializedName("ask7_1")
    @Expose
    public String ask71;
    @SerializedName("ask7_2")
    @Expose
    public String ask72;
    @SerializedName("ask8")
    @Expose
    public String ask8;
    @SerializedName("ask9")
    @Expose
    public String ask9;
    @SerializedName("ask10")
    @Expose
    public String ask10;
    @SerializedName("ask11_1")
    @Expose
    public String ask111;
    @SerializedName("ask11_2")
    @Expose
    public String ask112;
    @SerializedName("ask12")
    @Expose
    public String ask12;
    @SerializedName("ask13")
    @Expose
    public String ask13;
    @SerializedName("ask14")
    @Expose
    public String ask14;
    @SerializedName("ask15")
    @Expose
    public String ask15;
    @SerializedName("ask16")
    @Expose
    public String ask16;
    @SerializedName("ask17")
    @Expose
    public String ask17;
    @SerializedName("ask18")
    @Expose
    public String ask18;
    @SerializedName("ask19")
    @Expose
    public String ask19;
    @SerializedName("ask20")
    @Expose
    public String ask20;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("clientid")
    @Expose
    public String clientid;

    public Questionnaire(){}

    public Questionnaire(String ask1, String ask2, Integer ask3, Integer ask4,
                         String ask5, String ask6, String ask71, String ask72,
                         String ask8, String ask9, String ask10, String ask111,
                         String ask112, String ask12, String ask13, String ask14,
                         String ask15, String ask16, String ask17, String ask18,
                         String ask19, String ask20, String clientid) {
        this.ask1 = ask1;
        this.ask2 = ask2;
        this.ask3 = ask3;
        this.ask4 = ask4;
        this.ask5 = ask5;
        this.ask6 = ask6;
        this.ask71 = ask71;
        this.ask72 = ask72;
        this.ask8 = ask8;
        this.ask9 = ask9;
        this.ask10 = ask10;
        this.ask111 = ask111;
        this.ask112 = ask112;
        this.ask12 = ask12;
        this.ask13 = ask13;
        this.ask14 = ask14;
        this.ask15 = ask15;
        this.ask16 = ask16;
        this.ask17 = ask17;
        this.ask18 = ask18;
        this.ask19 = ask19;
        this.ask20 = ask20;
        this.clientid = clientid;
    }

    public String getAsk1() {
        return ask1;
    }

    public void setAsk1(String ask1) {
        this.ask1 = ask1;
    }

    public String getAsk2() {
        return ask2;
    }

    public void setAsk2(String ask2) {
        this.ask2 = ask2;
    }

    public Integer getAsk3() {
        return ask3;
    }

    public void setAsk3(Integer ask3) {
        this.ask3 = ask3;
    }

    public Integer getAsk4() {
        return ask4;
    }

    public void setAsk4(Integer ask4) {
        this.ask4 = ask4;
    }

    public String getAsk5() {
        return ask5;
    }

    public void setAsk5(String ask5) {
        this.ask5 = ask5;
    }

    public String getAsk6() {
        return ask6;
    }

    public void setAsk6(String ask6) {
        this.ask6 = ask6;
    }

    public String getAsk71() {
        return ask71;
    }

    public void setAsk71(String ask71) {
        this.ask71 = ask71;
    }

    public String getAsk72() {
        return ask72;
    }

    public void setAsk72(String ask72) {
        this.ask72 = ask72;
    }

    public String getAsk8() {
        return ask8;
    }

    public void setAsk8(String ask8) {
        this.ask8 = ask8;
    }

    public String getAsk9() {
        return ask9;
    }

    public void setAsk9(String ask9) {
        this.ask9 = ask9;
    }

    public String getAsk10() {
        return ask10;
    }

    public void setAsk10(String ask10) {
        this.ask10 = ask10;
    }

    public String getAsk111() {
        return ask111;
    }

    public void setAsk111(String ask111) {
        this.ask111 = ask111;
    }

    public String getAsk112() {
        return ask112;
    }

    public void setAsk112(String ask112) {
        this.ask112 = ask112;
    }

    public String getAsk12() {
        return ask12;
    }

    public void setAsk12(String ask12) {
        this.ask12 = ask12;
    }

    public String getAsk13() {
        return ask13;
    }

    public void setAsk13(String ask13) {
        this.ask13 = ask13;
    }

    public String getAsk14() {
        return ask14;
    }

    public void setAsk14(String ask14) {
        this.ask14 = ask14;
    }

    public String getAsk15() {
        return ask15;
    }

    public void setAsk15(String ask15) {
        this.ask15 = ask15;
    }

    public String getAsk16() {
        return ask16;
    }

    public void setAsk16(String ask16) {
        this.ask16 = ask16;
    }

    public String getAsk17() {
        return ask17;
    }

    public void setAsk17(String ask17) {
        this.ask17 = ask17;
    }

    public String getAsk18() {
        return ask18;
    }

    public void setAsk18(String ask18) {
        this.ask18 = ask18;
    }

    public String getAsk19() {
        return ask19;
    }

    public void setAsk19(String ask19) {
        this.ask19 = ask19;
    }

    public String getAsk20() {
        return ask20;
    }

    public void setAsk20(String ask20) {
        this.ask20 = ask20;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }
}
