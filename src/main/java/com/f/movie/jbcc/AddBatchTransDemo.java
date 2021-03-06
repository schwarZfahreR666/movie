package com.f.movie.jbcc;

import cn.tdchain.Trans;
import cn.tdchain.TransHead;
import cn.tdchain.jbcc.BatchTrans;
import cn.tdchain.jbcc.Result;
import com.f.movie.jbcc.base.BaseDemo;
import com.f.movie.jbcc.util.Tools;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * function： add  batch trans
 * <p>
 * datetime：2019-03-26 16:03
 * author：warne
 */
public class AddBatchTransDemo extends BaseDemo {


    public static void main(String[] args) {

        try {
            for (int i = 0; i < 3; i++) {
                BatchTrans<Trans> batchTrans = transList();
                //# add batch trade
                Result<BatchTrans<TransHead>> result = connection.addBatchTrans(batchTrans);

                if (result.isSuccess()) {
                    log.info("\n===> batch trans success.");
                } else {
                    log.info("\n===> batch trans fail.");
                }

                Tools.printResult(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 批量交易
     *
     * @return
     */
    public static BatchTrans<Trans> transList() {
        int transCount = 3;
        List<Trans> transList = new ArrayList<>(transCount);
        Trans trans = null;
        for (int i = 0; i < transCount; i++) {
            trans = new Trans();
            trans.setKey("user" + i); //# 同一个batch里，key不能重复
            Map<String, Object> data = new HashMap<>();
            data.put("name", "warne" + i);
            data.put("age", 20 + i);
            data.put("index", i);
            trans.setData(JSON.toJSONString(data));
            trans.setType("BatchTest");

            transList.add(trans);
        }

        BatchTrans<Trans> batchTrans = new BatchTrans<>();
        batchTrans.addTransToBatch(transList);

        return batchTrans;
    }
}
