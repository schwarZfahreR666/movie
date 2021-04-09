package com.f.movie.jbcc;

import cn.tdchain.Trans;
import cn.tdchain.jbcc.Result;
import com.f.movie.jbcc.base.BaseDemo;
import com.f.movie.jbcc.util.Tools;

import java.util.List;

/**
 * function：query trans by type
 * datetime：2019-03-27 14:33
 * author：warne
 */
public class QueryTransByTypeDemo extends BaseDemo {

    public static void main(String[] args) {

        String type = "Test";
        try {
            Result<List<Trans>> result = connection.getTransListByType(type);

            log.info("\n===> query result: ");

            Tools.printResult(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
