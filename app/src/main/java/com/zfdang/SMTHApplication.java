package com.zfdang;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.zfdang.zsmth_android.helpers.GEODatabase;
import com.zfdang.zsmth_android.newsmth.SMTHHelper;
import com.zfdang.zsmth_android.newsmth.UserStatus;

import okhttp3.OkHttpClient;

/**
 * Created by zfdang on 2016-3-18.
 */
public class SMTHApplication extends Application {
    // http://blog.csdn.net/lieren666/article/details/7598288
    // Android Application的作用
    private static Context context;
    public static String App_Title_Prefix = "zSMTH - ";

    public static final String FROM_BOARD = "From_Board";
    public static final String FROM_BOARD_HOT = "FROM_HOTTOPICS";
    public static final String FROM_BOARD_BOARD = "FROM_BOARDTOPICS";
    public static final String ATTACHMENT_URLS = "ATTACHMENT_URLS";
    public static final String ATTACHMENT_CURRENT_POS = "ATTACHMENT_CURRENT_POS";
    public static final String QUERY_USER_INFO = "QUERY_USER_ID";
    public static final String BOARD_OBJECT = "BOARD_OBJECT";
    public static final String TOPIC_OBJECT = "TOPIC_OBJECT";

    public static final String MAIL_URL_OBJECT = "MAIL_URL_OBJECT";

    public static final String MAIN_TARGET_FRAGMENT = "MAIN_TARGET_FRAGMENT";
    public static final String SERVICE_NOTIFICATION_MESSAGE = "SERVICE_NOTIFICATION_MESSAGE";


    public static final String USER_SERVICE_RECEIVER = "USER_SERVICE_RECEIVER";

    public static final String COMPOSE_POST_CONTEXT = "Compose_Post_Context";



    // IP database
    public static GEODatabase geoDB;

    public static boolean isValidUser() {
        if(activeUser != null && !activeUser.getId().equals("guest")){
            return true;
        } else {
            return false;
        }
    }

    // current logined user
    public static UserStatus activeUser;

    public void onCreate() {
        super.onCreate();
        SMTHApplication.context = getApplicationContext();

        // init IP lookup database
        geoDB = new GEODatabase(this);

//        Set<RequestListener> requestListeners = new HashSet<>();
//        requestListeners.add(new RequestLoggingListener());
        // init Fresco
        OkHttpClient httpClient = SMTHHelper.getInstance().mHttpClient;
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(context, httpClient)
//                .setRequestListeners(requestListeners)
//                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(context, config);
//        FLog.setMinimumLoggingLevel(FLog.VERBOSE);
    }

    public static Context getAppContext() {
        return SMTHApplication.context;
    }
}
