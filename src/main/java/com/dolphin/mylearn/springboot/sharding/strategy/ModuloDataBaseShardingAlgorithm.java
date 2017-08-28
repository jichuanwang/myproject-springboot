package com.dolphin.mylearn.springboot.sharding.strategy;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.routing.strategy.SingleKeyShardingAlgorithm;

import java.util.Collection;

/**
 * Created by jichuan.wang on 2017/8/28.
 */
public class ModuloDataBaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {
    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        String result = null;
        for(String database : availableTargetNames){
            if(database.endsWith(shardingValue.getValue() % 2+1+"")){
                result = database;
            }
        }
        return result;
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        return null;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        return null;
    }
}
