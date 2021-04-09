package com.f.movie.jbcc;

import cn.tdchain.Trans;
import cn.tdchain.jbcc.BatchTrans;
import com.f.movie.jbcc.base.BaseDemo;
import com.f.movie.jbcc.util.Tools;

/**
 * function：description
 * datetime：2019-03-27 16:01
 * author：warne
 */
public class OpenTransactionDemo extends BaseDemo {

    public static void main(String[] args) {

        try {

            BatchTrans<Trans> batchTrans = AddBatchTransDemo.transList();
            String[] keys = batchTrans.keyToArray();

            //# open transaction
            boolean success = connection.startTransaction(keys);
            if (success) {
                log.info("\n===> open tansaction success. ");
                connection.addBatchTrans(batchTrans);
            } else {
                log.info("===>\nopen tansaction fail. ");
            }
            //# release lock-transaction
            connection.stopTransaction(keys);

            Tools.printResult(batchTrans);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
