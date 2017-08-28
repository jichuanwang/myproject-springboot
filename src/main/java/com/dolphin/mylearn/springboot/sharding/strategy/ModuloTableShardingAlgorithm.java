package com.dolphin.mylearn.springboot.sharding.strategy;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by jichuan.wang on 2017/8/28.
 */
public class ModuloTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Integer> {
    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        for(String table : availableTargetNames){
            if(table.endsWith(shardingValue.getValue()%2+1+"")){
                return table;
            }
        }
        return null;
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
       Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
       for(Integer value : shardingValue.getValues()){
           for(String table : availableTargetNames){
               if(table.endsWith(value%2+"")){
                   result.add(table);
               }
           }
       }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        return null;
    }
}
