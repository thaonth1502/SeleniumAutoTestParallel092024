package com.thaonth.contants;

import com.thaonth.helpers.PropertiesHelper;

public class DataConfig {
        public static String URL = PropertiesHelper.getValue("URL");
        public static String PASSWORD = PropertiesHelper.getValue("PASSWORD");
        public static String EMAIL = PropertiesHelper.getValue("EMAIL");

        public static int EXPLICIT_WAIT_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("EXPLICIT_WAIT_TIMEOUT"));
        public static double STEP_TIME = Double.parseDouble(PropertiesHelper.getValue("STEP_TIME"));
        public static int PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("PAGE_LOAD_TIMEOUT"));
        public static String SCREENSHOT_PATH =  PropertiesHelper.getValue("SCREENSHOT_PATH");
        public static String RECORD_VIDEO_PATH = PropertiesHelper.getValue("RECORD_VIDEO_PATH");
    }
