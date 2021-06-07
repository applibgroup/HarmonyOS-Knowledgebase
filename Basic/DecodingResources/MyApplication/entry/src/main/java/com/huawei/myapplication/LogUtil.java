package com.huawei.myapplication;

import ohos.agp.text.Font;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class LogUtil {
    private static final String TAG_LOG = "myApplication";

    private static final int DOMAIN_ID = 0xD000F00;

    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, DOMAIN_ID, LogUtil.TAG_LOG);

    private static final String LOG_FORMAT = "%{public}s: %{public}s";

    private LogUtil() {
        /* Do nothing */
    }

    /**
     * Print debug log
     *
     * @param tag log tag
     * @param msg log message
     */
    public static void debug(String tag, String msg) {
        HiLog.debug(LABEL_LOG, LOG_FORMAT, tag, msg);
    }

    /**
     * Print info log
     *
     * @param tag log tag
     * @param msg log message
     */
    public static void info(String tag, String msg) {
        HiLog.info(LABEL_LOG, LOG_FORMAT, tag, msg);
    }

    /**
     * Print warn log
     *
     * @param tag log tag
     * @param msg log message
     */
    public static void warn(String tag, String msg) {
        HiLog.warn(LABEL_LOG, LOG_FORMAT, tag, msg);
    }

    /**
     * Print error log
     *
     * @param tag log tag
     * @param msg log message
     */
    public static void error(String tag, String msg) {
        HiLog.error(LABEL_LOG, LOG_FORMAT, tag, msg);
    }

    public static class TextUtils {
        /**
         * font file name: digit condensed regular
         */
        public static final String DIGIT_REGULAR = "hw-digit-reg-LL.otf";

        /**
         * font file name: digit condensed medium
         */
        public static final String DIGIT_MEDIUM = "hw-digit-med-LL.otf";

        /**
         * font file name: robot condensed regular
         */
        public static final String ROBOT_CONDENSED_REGULAR = "sans-serif-condensed";

        /**
         * font file name: robot condensed medium
         */
        public static final String ROBOT_CONDENSED_MEDIUM = "sans-serif-condensed-medium";

        /**
         * up to one line
         */
        public static final int MAX_LINE_ONE = 1;

        /**
         * up to two lines
         */
        public static final int MAX_LINE_TWO = 2;

        /**
         * text content max line
         */
        public static final int PRIVACY_TEXT_MAX_LINE = 50;

        /**
         * text content max line
         */
        public static final int STOP_TEXT_MAX_LINE = 10;

        /**
         * font name: default medium
         */
        public static final String MEDIUM = "medium";

        /**
         * font name: default regular
         */
        public static final String REGULAR = "regular";

        private static final int FONT_WEIGHT_MEDIUM = 500;


        private static final String TAG = "TextUtils";

        private static final int DECIMAL_DIGITS = 2;

        private static final float CELSIUS_TO_FAHRENHEIT_CONSTANT = 32f;

        private static final float CELSIUS_TO_FAHRENHEIT_RATIO = 1.8f;

        private static final int BUFFER_LENGTH = 8192;

        private static final long TIME_SHOW_ONE_WIDE_CHAR = 500L;

        private static final String RAW_FILE_PATH = "entry_watch/resources/rawfile/";

        private static final Map<String, Font> FONT_MAP = new HashMap<>();

        private static NumberFormat sNumberFormat;

        private TextUtils() {
        }

        /**
         * check if the input is empty
         *
         * @param input the input string
         * @return the input string is empty
         */
        public static boolean isEmpty(String input) {
            return input == null || input.length() == 0;
        }

        /**
         * check if the input string is empty
         *
         * @param input the input strings
         * @return the input string is empty
         */
        public static boolean isEmpty(String... input) {
            if (input == null) {
                return true;
            }
            for (String oneItem : input) {
                if (isEmpty(oneItem)) {
                    return true;
                }
            }
            return false;
        }


    }
}


