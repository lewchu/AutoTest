package com.lewchu.utils;

import com.lewchu.enums.InterFaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFileUtil {
    
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);
    
    public static String getUrl(InterFaceName interFaceName){
        String address = bundle.getString("test.url");
        String uri = "";
        String result;
        if (interFaceName == InterFaceName.GETUSERINFO) {
            uri = bundle.getString("getUserInfo.uri");
        }
        if (interFaceName == InterFaceName.GETUSERLIST) {
            uri = bundle.getString("getUserList.uri");
        }
        if (interFaceName == InterFaceName.ADDUSER) {
            uri = bundle.getString("addUser.uri");
        }
        if (interFaceName == InterFaceName.UPDATEUSERINFO) {
            uri = bundle.getString("updateUserInfo.uri");
        }
        if (interFaceName == InterFaceName.LOGIN) {
            uri = bundle.getString("login.uri");
        }
        result = address + uri;
        return result;
    }
}
