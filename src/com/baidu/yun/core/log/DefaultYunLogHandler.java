package com.baidu.yun.core.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultYunLogHandler implements YunLogHandler {

    private Logger logger = Logger.getLogger(DefaultYunLogHandler.class
            .getName());

    public void onHandle(YunLogEvent event) {
        // TODO Auto-generated method stub
        if (event.getLevel() == YunLogEvent.FATAL) {
            logger.log(Level.SEVERE, event.getMessage());
        } else if (event.getLevel() == YunLogEvent.WARNING) {
            logger.log(Level.WARNING, event.getMessage());
        } else if (event.getLevel() == YunLogEvent.NOTICE) {
            logger.log(Level.INFO, event.getMessage());
        }
    }

}
