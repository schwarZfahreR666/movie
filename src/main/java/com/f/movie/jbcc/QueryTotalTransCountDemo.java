/*
 *
 *  *   @project        jbcc-samples
 *  *   @file           QueryTotalTransCountDemo
 *  *   @author         warne
 *  *   @date           19-4-1 下午5:58
 *
 */

package com.f.movie.jbcc;

import cn.tdchain.jbcc.Result;
import com.f.movie.jbcc.base.BaseDemo;
import com.f.movie.jbcc.util.Tools;

/**
 * function：query total trans count
 * datetime：2019-03-27 14:33
 * author：warne
 */
public class QueryTotalTransCountDemo extends BaseDemo {

    public static void main(String[] args) {

        try {
            Result<Long> result = connection.getBlockChainTransCount();
            Tools.printResult(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
