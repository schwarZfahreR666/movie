package com.f.movie.jbcc;

import cn.tdchain.Trans;
import cn.tdchain.jbcc.Result;
import com.f.movie.jbcc.base.BaseDemo;
import com.f.movie.jbcc.util.Tools;

import java.util.List;

/**
 * function：query lastest trans history by key and version
 * datetime：2019-03-27 14:33
 * author：warne
 */
public class QueryTransHistoryByKeyAndVersionDemo extends BaseDemo {

    public static void main(String[] args) {

        String key = "user0";
        int startIndex = 0;
        int endIndex = 10;
        try {
            Result<List<Trans>> result = connection.getTransHistoryByKey(key, startIndex, endIndex);
            log.info("\n===> query result: ");

            Tools.printResult(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
