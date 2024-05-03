package org.qxdn.birthdayreminder.utils;

import org.slf4j.Logger;

public class LogUtils {
    public static void trace(Logger logger, String format, Object... objects){
        if (logger.isTraceEnabled()){
            logger.trace(format,objects);
        }
    }

    public static void debug(Logger logger,String format,Object... objects){
        if (logger.isDebugEnabled()){
            logger.debug(format,objects);
        }
    }

    public static void info(Logger logger,String msg){
        if (logger.isInfoEnabled()){
            logger.info(msg);
        }
    }

    public static void info(Logger logger,String format,Object... objects){
        if (logger.isInfoEnabled()){
            logger.info(format,objects);
        }
    }

    public static void warn(Logger logger,String format,Object... objects){
        if (logger.isWarnEnabled()){
            logger.warn(format,objects);
        }
    }

    public static void error(Logger logger,String format,Object... objects){
        if (logger.isErrorEnabled()){
            logger.error(format,objects);
        }
    }
}
