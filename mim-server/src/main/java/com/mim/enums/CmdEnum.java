package com.mim.enums;

import lombok.Data;

public enum CmdEnum {
    Login("login"),
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
