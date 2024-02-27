package khoatester.com.constant;

import khoatester.com.helpers.PropertiesHelper;

import java.io.File;

public class ConstantGlobal {

    static {
        PropertiesHelper.loadAllFiles();
    }

    public static final String URL = PropertiesHelper.getValue("url");
    public static final String EMAIL = PropertiesHelper.getValue("email");
    public static final String PASSWORD = PropertiesHelper.getValue("password");
    public static final Boolean HEADLESS = Boolean.parseBoolean(PropertiesHelper.getValue("headless"));

    public static int DEFAULT_TIME_OUT = 30;
    public static int MEDIUM_TIME_OUT = 5;
    public static int SHOT_TIME_OUT = 1;

    public static int PAGE_LOAD_TIME_OUT = 120;


}
