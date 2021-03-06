package com.f.movie.jbcc;

import cn.tdchain.Block;
import cn.tdchain.Trans;
import cn.tdchain.jbcc.Result;
import com.f.movie.jbcc.base.BaseDemo;
import com.f.movie.jbcc.util.Tools;

/**
 * function：query max block
 * datetime：2019-03-27 14:33
 * author：warne
 */
public class QueryMaxBlockDemo extends BaseDemo {

    public static void main(String[] args) {

        for (int i = 1; i < 3; i++) {
            maxBlock();
        }
    }

    public static void maxBlock() {
        try {
            Result<Block<Trans>> result = connection.getMaxBlock();

            if (result.isSuccess()) {
                log.info("\n===> query max block success.");
            } else {
                log.info("\n===> query max block fail.");
            }

            Tools.printResult(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
