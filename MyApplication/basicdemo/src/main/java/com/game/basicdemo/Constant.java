package com.game.basicdemo;

/**
 * Created by admin on 2017/2/17.
 */

public class Constant {
    public static final String PARAM_KEY_DEVICEID = "deviceid";
    public static final String PARAM_KEY_VERSION = "version";
    public static final String PARAM_KEY_IMSI = "imsi";
    public class NetConstant {

        public static final String RESPONSE_KEY_CODE = "code";
        public static final String RESPONSE_KEY_MSG = "message";
        public static final String RESPONSE_KEY_DATA = "data";

        public static final String RESPONSE_KEY_LIST = "list";

        public static final String RESPONSE_KEY_CHANNELINFO = "channelinfo";
        public static final String RESPONSE_KEY_VEDIOINFO = "videoinfo";
        public static final String RESPONSE_KEY_TOTAL = "total";


        public static final String SUFFIX_URL = "";

        public static final String PARAM_VALUE_BANNER_LIST = "videogetbarnerlistconfig";
        public static final String PARAM_VALUE_CHANNLE_LIST = "videogetvideolistconfig";
        public static final String PARAM_VALUE_CHANNEL_INFO = "videogetchannellistconfig";

        public static final String PARAM_KEY_CMD = "zckjvideocmd";
        public static final String PARAM_KEY_INMAINVIEW = "inmainview";
        public static final String PARAM_KEY_CHANNEL = "channelid";
        public static final String PARAM_KEY_START = "start";
        public static final String PARAM_KEY_PLATFORM = "platform";

        public static final String PLATFORM = "jqyy2";

        public static final String PARAM_KEY_DEVICEID = "deviceid";
        public static final String PARAM_KEY_VERSION = "version";
        public static final String PARAM_KEY_IMSI = "imsi";

        public static final String WEB_NET_ADDRESS = "http://zjyuxing.cn:9092/";//wap地址

        public static final String NET_DOMAIN_NAME = "pageinfo.abccity.info" ;//页面数据
        public static final String SERVER_BASE_URL = "http://videoinfo.abccity.info:7840/";//URL "http://g1.abccity.info:7840/"请求视频列表信息

        public static final String NET_ADDRESS = "http://"+NET_DOMAIN_NAME+":8088/";//http://pageinfo.abccity.info:8088
        public static final String URL_ENABLE = NET_ADDRESS+"earth/enable";
        public static final String URL_PAY = NET_ADDRESS+"earth/pay";
        public static final String URL_SVIP = NET_ADDRESS+"earth/video/svip";
        public static final String URL_SPLASH = NET_ADDRESS+"earth/video/splashpng";
        public static final String URL_NEEDSHOW = NET_ADDRESS+"earth/video/needshow2";
    }
    public class FileConstant {
        public static final String KEY_FILENAME = "ShareFiles";
        public static final String KEY_HISTORY_RECORD = "historyRecord";

        public static final String KEY_CHANNLEINFO ="channelinfo";
        public static final String KEY_CHANNLENUMBER ="channelnumber";
        public static final String KEY_FILE_USERINFO = "userinfo_file";
        public static final String KEY_FILE_SVIP_INFO = "svip_info";

        public static final String KEY_VIP = "vip";
        public static final String KEY_UID = "userid";

        public static final String SPLASH_UPDATE_TIME = "updatetime";

        public static final String NEED_SHOW_WAP = "needshowwap";
        public static final String SHOW_WAP_ICON = "showwapicon";
        public static final String NEED_SHOW_APPSHORTCUT = "needshowappshortcut";
        public static final String SHOW_APP_ICON = "showappicon";
    }
}
