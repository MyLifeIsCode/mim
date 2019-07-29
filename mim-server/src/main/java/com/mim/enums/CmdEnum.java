package com.mim.enums;

import lombok.Data;

import java.util.Optional;

public enum CmdEnum {
    Login("login"),
    OneToOne("oneToOne"),
    Group("group"),
    ;
    private String cmd;

    CmdEnum(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

}
