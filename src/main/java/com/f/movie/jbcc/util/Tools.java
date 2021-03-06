package com.f.movie.jbcc.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * function：tools
 * datetime：2019-03-26 16:37
 * author：warne
 */
public abstract class Tools {
    protected final static Logger log = LoggerFactory.getLogger("jbcc-samples");

    /**
     * foamat josn
     *
     * @param obj
     */
    public static String printResult(Object obj) {
        StringBuffer result = new StringBuffer();
        if (obj == null) {
            log.info("result:\r\n {} \r\n" , "null" );
            return null;
        }
        String json = JSON.toJSONString(obj);
        int level = 0;
        int length = json.length();
        for (int index = 0; index < length; index++) {
            char c = json.charAt(index);
            if (level > 0 && '\n' == result.charAt(result.length() - 1)) {
                result.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    result.append(c + "\n");
                    level++;
                    break;
                case ',':
                    result.append(c + "\n");
                    break;
                case '}':
                case ']':
                    result.append("\n");
                    level--;
                    result.append(getLevelStr(level));
                    result.append(c);
                    break;
                default:
                    result.append(c);
                    break;
            }
        }

        log.info("result:\r\n {} \r\n" , result );

        return result.toString();
    }

    /**
     * @param level
     * @return
     */
    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }

}
