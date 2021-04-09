package com.f.movie.jbcc;

import cn.tdchain.Trans;
import cn.tdchain.jbcc.Result;
import com.f.movie.jbcc.base.BaseDemo;
import com.f.movie.jbcc.util.Tools;

/**
 * function：query lastest trans by key
 * datetime：2019-03-27 14:33
 * author：warne
 */
public class QueryNewTransByKeyDemo extends BaseDemo {

    public static void main(String[] args) {

        String key = "user1";
        try {
            Result<Trans> result = connection.getNewTransByKey(key);

            if (result.isSuccess()) {
                if (result.getEntity().getKey().equals(key)) {
                    log.info("\n===> query new trans success.");
                }
            } else {
                log.info("\n===> query new trans fail.");
            }
            Tools.printResult(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
