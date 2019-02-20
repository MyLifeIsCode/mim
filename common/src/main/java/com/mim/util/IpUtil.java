package com.mim.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author qll
 * @create 2019-02-16 11:50
 * @desc iputil
 **/
public class IpUtil {

    /**
     * 获取本机ip
     * @return
     * @throws Exception
     */
    public static String getLocalIp(){
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return localHost.getHostAddress();
    }

}
