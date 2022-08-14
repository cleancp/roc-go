package com.today.sqlutil;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataTableDocumentGenerator {

    //数据库静态配置
    private static final String database = "nextop";
    private static final String url = "jdbc:mysql://rm-wz9i165ek36246t6m.mysql.rds.aliyuncs.com:3306/" + database
            + "?useUnicode=true&useSSL=false&characterEncoding=utf8&tinyInt1isBit=false";
    private static final String driverName = "com.p6spy.engine.spy.P6SpyDriver";
    private static final String userName = "nextop";
    private static final String password = "Max0gl1Daup0nUR6";

    //文件生成静态配置
    //文件本地路径
    private static final String filePath = "F:\\dataTable";
    //文件格式（HTML,WORD,MD）
    private static final EngineFileType fileFormat = EngineFileType.MD;
    //版本号
    private static final String versionNum = "1.0.0";
    //文件名称
    private static final String fileName = database + "_数据库设计文档生成_" + versionNum;
    //文件描述
    private static final String description = "数据库设计文档生成";

    public static void main(String[] args) {
        Map<String, List<String>> tableHashMap = new HashMap<>();
        //忽略表名列表
        List<String> ignoreTableName = new ArrayList<>();
        //忽略表前缀列表
        List<String> ignorePrefix = new ArrayList<>();
        //忽略表后缀列表
        List<String> ignoreSuffix = new ArrayList<>();
        //指定生成表名列表
        List<String> designatedTableName = new ArrayList<>();
        //指定生成表前缀列表
        List<String> designatedTablePrefix = Arrays.asList("nextop_");
        //指定生成表后缀列表
        List<String> designatedTableSuffix = new ArrayList<>();
        tableHashMap.put("ignoreTableName", ignoreTableName);
        tableHashMap.put("ignorePrefix", ignorePrefix);
        tableHashMap.put("ignoreSuffix", ignoreSuffix);
        tableHashMap.put("designatedTableName", designatedTableName);
        tableHashMap.put("designatedTablePrefix", designatedTablePrefix);
        tableHashMap.put("designatedTableSuffix", designatedTableSuffix);

        documentGenerate(tableHashMap);
    }

    /**
     * @param tableHashMap:
     * @name: documentGenerate
     * @description: 执行生成文件
     * @return: void
     * @since: 2020/8/13 10:24
     * @author: zhangjiaming
     */
    public static void documentGenerate(Map<String, List<String>> tableHashMap) {
        // 初始化数据源配置
        DataSource dataSource = initDataSourceConfig();
        // 生成配置
        EngineConfig engineConfig = initEngineConfig();
        // 生成数据表的配置
        ProcessConfig processConfig = initProcessConfig(tableHashMap);
        //配置
        Configuration config = Configuration.builder()
                //版本
                .version(versionNum)
                //描述
                .description(description)
                //数据源
                .dataSource(dataSource)
                //生成配置
                .engineConfig(engineConfig)
                //生成配置
                .produceConfig(processConfig).build();
        //执行生成
        new DocumentationExecute(config).execute();
    }

    /**
     * @name: initDataSourceConfig
     * @description: 配置数据源
     * @return: javax.sql.DataSource
     * @since: 2020/8/12 19:57
     * @author: zhangjiaming
     */
    private static DataSource initDataSourceConfig() {
        //数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverName);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(userName);
        hikariConfig.setPassword(password);
        //设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        return new HikariDataSource(hikariConfig);
    }

    /**
     * @name: initEngineConfig
     * @description: 配置文件生成路径及格式
     * @return: cn.smallbun.screw.core.engine.EngineConfig
     * @since: 2020/8/13 9:59
     * @author: zhangjiaming
     */
    private static EngineConfig initEngineConfig() {
        //生成配置
        return EngineConfig.builder()
                //生成文件路径
                .fileOutputDir(filePath)
                //打开目录
                .openOutputDir(true)
                //文件类型
                .fileType(fileFormat)
                //文件名
                .fileName(fileName)
                //生成模板实现
                .produceType(EngineTemplateType.freemarker).build();
    }

    /**
     * @name: initProcessConfig
     * @description: 初始化生成的表的范围
     * @return: cn.smallbun.screw.core.process.ProcessConfig
     * @since: 2020/8/13 10:08
     * @author: zhangjiaming
     */
    private static ProcessConfig initProcessConfig(Map<String, List<String>> tableHashMap) {
        List<String> ignoreTableName = tableHashMap.get("ignoreTableName");
        List<String> ignorePrefix = tableHashMap.get("ignorePrefix");
        List<String> ignoreSuffix = tableHashMap.get("ignoreSuffix");
        List<String> designatedTableName = tableHashMap.get("designatedTableName");
        List<String> designatedTablePrefix = tableHashMap.get("designatedTablePrefix");
        List<String> designatedTableSuffix = tableHashMap.get("designatedTableSuffix");

        return ProcessConfig.builder()
                //忽略表名
                .ignoreTableName(ignoreTableName)
                //忽略表前缀
                .ignoreTablePrefix(ignorePrefix)
                //忽略表后缀
                .ignoreTableSuffix(ignoreSuffix)
                //指定表名
                .designatedTableName(designatedTableName)
                //指定表前缀
                .designatedTablePrefix(designatedTablePrefix)
                //指定表后缀
                .designatedTableSuffix(designatedTableSuffix).build();
    }

}
