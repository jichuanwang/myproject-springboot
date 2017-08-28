package com.dolphin.mylearn;

import com.dangdang.ddframe.rdb.sharding.api.MasterSlaveDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.keygen.DefaultKeyGenerator;
import com.dangdang.ddframe.rdb.sharding.routing.strategy.SingleKeyShardingAlgorithm;
import com.dolphin.mylearn.springboot.sharding.strategy.ModuloDataBaseShardingAlgorithm;
import com.dolphin.mylearn.springboot.sharding.strategy.ModuloTableShardingAlgorithm;
import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.Configuration;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by jichuan.wang on 2017/8/28.
 */
public class ShardingTest {
    private Connection connection;
    @Before
    public void  init(){
        Map<String,DataSource> dataSourceMap = new HashMap<String, DataSource>();
        dataSourceMap.put("sharding1",createDataSource("sharding1"));
        dataSourceMap.put("sharding2",createDataSource("sharding2"));
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap);
        TableRule orderTableRule = TableRule.builder("t_order")
                .actualTables(Arrays.asList("t_order_1","t_order_2"))
                .generateKeyColumn("order_id")
                .dataSourceRule(dataSourceRule).build();
        TableRule orderItemTableRule = TableRule.builder("t_order_item")
                .actualTables(Arrays.asList("t_order_item_1","t_order_item_2"))
                .dataSourceRule(dataSourceRule).build();
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(orderTableRule,orderItemTableRule))
                .databaseShardingStrategy(new DatabaseShardingStrategy("user_id",
                        new ModuloDataBaseShardingAlgorithm()))
                .tableShardingStrategy(new TableShardingStrategy("order_id",new ModuloTableShardingAlgorithm()))
                .keyGenerator(DefaultKeyGenerator.class)
                .build();
        try {
            Properties properties = new Properties();
            properties.setProperty("sql.show", "true");
            DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule, properties);
            connection = dataSource.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Test
    public void testQuery() throws Exception{
        String sql = "select * from t_order where order_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,3);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSet resultSet1 = preparedStatement.getGeneratedKeys();
        while (resultSet.next()){
            PrintUtil.print(resultSet1.getInt("order_id"));
        }
        while (resultSet.next()){
            PrintUtil.print(resultSet.getInt("user_id"));
        }
        resultSet.close();
    }

    @Test
    public void testInsert() throws Exception{
        String sql = "insert into t_order (user_id,order_id) values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,12);
        preparedStatement.setInt(2,13);
        int result = preparedStatement.executeUpdate();
        PrintUtil.print(result);

    }

    /**
     * 读写分离
     */
    @Test
    public void test02(){
        DataSource masterDataSource = MasterSlaveDataSourceFactory.createDataSource("ms_0",null,null);
    }
    public DataSource createDataSource(String dataSourceName){
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
        result.setUrl(String.format("jdbc:mysql://localhost:3307/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",dataSourceName));
        result.setUsername("root");
        result.setPassword("wwww");
        return result;
    }
}
