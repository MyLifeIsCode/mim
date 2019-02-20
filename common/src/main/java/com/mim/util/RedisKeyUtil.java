package com.mim.util;

public class RedisKeyUtil {

    private static final String UID_SERVER = "uid_server:";
    private static final String UID_GROUP = "uid_group:";
    private static final String GROUP_UID = "group_uid:";


    public static String getUidServer(Long uid) {
        return UID_SERVER + uid.toString();
    }

    public static String getUidGroup(Long uid) {
        return UID_GROUP + uid.toString();
    }

    public static String getGroupUid(Long groupId) {
        return GROUP_UID + groupId.toString();
    }
}
