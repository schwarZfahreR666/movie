package com.f.movie.jbcc;

import cn.tdchain.Trans;
import cn.tdchain.jbcc.Result;
import com.f.movie.jbcc.base.BaseDemo;
import com.f.movie.jbcc.util.Tools;

/**
 * function：query trans by hash
 * datetime：2019-03-27 14:33
 * author：warne
 */
public class QueryTransByHashDemo extends BaseDemo {

    public static void main(String[] args) {

        try {
            //# 交易hash
            String hash = "3ee5f300e77beddec41e2ff66d247f0a77db93117231765881e2e2d820c7bff72";
            //# 向云区块链服务执行查询操作
            Result<Trans> result = connection.getTransByHash(hash);

            if (result.isSuccess()) {
                if (result.getEntity().getHash().equals(hash)) {
                    log.info("\n===> query trans success by hash.");
                } else {
                    log.info("\n===> query trans fail by hash.");
                }
            } else {
                log.info("\nnot fount trans .");
            }

            Tools.printResult(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
