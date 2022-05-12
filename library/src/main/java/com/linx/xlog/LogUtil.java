package com.linx.xlog;


import com.linx.xlog.sdk.LogBase;
import com.linx.xlog.sdk.wrapper.NimLog;
import com.linx.xlog.storage.ExternalStorage;

public class LogUtil extends NimLog {

    private static final String LOG_FILE_NAME_PREFIX = "LinX";

    public static void init(String logDir, int level) {
        final LogBase.LogInterceptor interceptor = new LogBase.LogInterceptor() {
            @Override
            public boolean checkValidBeforeWrite() {
                return ExternalStorage.getInstance().checkStorageValid();
            }
        };

        NimLog.initDateNLog("LinX_LOG", logDir, LOG_FILE_NAME_PREFIX, level, 0, 0, true, interceptor);
    }

    public static void ui(String msg) {
        getLog().i("ui", buildMessage(msg));
    }

    public static void res(String msg) {
        getLog().i("res", buildMessage(msg));
    }

    public static void audio(String msg) {
        getLog().i("AudioRecorder", buildMessage(msg));
    }
}
