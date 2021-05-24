package com.today.roc.go.understand.sqlutil;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @name: MysqlGenerator
 * @description: MyBatisPlus逆向工程类
 * @type: JAVA
 * @since: 2020/05/18 18:03
 * @author: FoeniX
 */
public class MysqlGenerator {

    private static final String database = "nextop";

    private static final String url = "jdbc:p6spy:mysql://rm-wz9i165ek36246t6m.mysql.rds.aliyuncs.com:3306/" + database +
            "?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "nextop";
    private static final String word = "Max0gl1Daup0nUR6";
    private static String basePath = "";
    private static String mapperPath = "";
    private static String filePrefix = "I";

    public static void main(String[] args) {
        List<String> tableNames = Arrays.asList("nextop_goods_third_warehouse_elife", "nextop_goods_third_warehouse_edaeu");
        generate("roc.zou", "com.nextop", "goods", tableNames, "nextop");
    }

    /**
     * @param author      作者
     * @param packageName 包名
     * @param moduleName  模块名
     * @param tableNames  表名
     * @param tablePrefix 表前缀
     * @name: generate
     * @description: 代码生成类
     * @return: void
     * @since: 2020/05/18 18:03
     * @author: FoeniX
     */
    public static void generate(String author, String packageName, String moduleName, List<String> tableNames, String... tablePrefix) {
        // 全局配置
        GlobalConfig gc = initGlobalConfig(author, packageName);
        // 数据源配置
        DataSourceConfig dsc = initDataSourceConfig();
        // 包配置
        PackageConfig pc = new PackageConfig().setParent(packageName).setEntity("entity." + moduleName)
                .setMapper("mapper." + moduleName).setXml("xml." + moduleName);
        // 模板引擎配置
        FreemarkerTemplateEngine templateEngine = new FreemarkerTemplateEngine();
        //每一个entity都需要单独设置InjectionConfig, StrategyConfig和TemplateConfig
        Map<String, String> names = new JdbcRepository().getEntityNames(tableNames, tablePrefix);
        if (names == null || names.isEmpty()) {
            return;
        }
        for (String tableName : names.keySet()) {
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();
            mpg.setGlobalConfig(gc);
            mpg.setDataSource(dsc);
            mpg.setPackageInfo(pc);
            mpg.setTemplateEngine(templateEngine);

            // 自定义配置
            InjectionConfig cfg = initInjectionConfig(packageName, moduleName);
            mpg.setCfg(cfg);

            // 策略配置
            StrategyConfig strategy = initStrategyConfig(tableName, tablePrefix);
            mpg.setStrategy(strategy);

            // 模板配置
            // mapper文件
            String mapperFile = mapperPath + "/" + filePrefix + names.get(tableName) + "Mapper.java";
            TemplateConfig tc = initTemplateConfig(mapperFile);
            mpg.setTemplate(tc);

            //开始执行
            mpg.execute();
        }
    }

    /**
     * @param
     * @name: initDataSourceConfig
     * @description: 配置数据源
     * @return: DataSourceConfig
     * @since: 2020/05/18 18:03
     * @author: FoeniX
     */
    private static DataSourceConfig initDataSourceConfig() {
        return new DataSourceConfig()
                .setUrl(url)
                .setDriverName(driverName)
                .setUsername(userName)
                .setPassword(word);
    }

    /**
     * @param author      作者
     * @param packageName 包名
     * @name: initGlobalConfig
     * @description: 全局配置
     * @return: GlobalConfig
     * @since: 2020/05/18 18:03
     * @author: FoeniX
     */
    private static GlobalConfig initGlobalConfig(String author, String packageName) {
        GlobalConfig gc = new GlobalConfig();
        String tmp = MysqlGenerator.class.getResource("").getPath();
        String codeDir = "";
        if (tmp != null) {
            codeDir = tmp.substring(0, tmp.indexOf("/target"));
        }
        basePath = codeDir + "/src/main/java";
        mapperPath = basePath + "/" + packageName.replace(".", "/") + "/mapper";
        System.out.println("basePath = " + basePath + "\nmapperPath = " + mapperPath);
        gc.setOutputDir(basePath);
        gc.setAuthor(author);
        gc.setEnableCache(false);
        gc.setOpen(false);
        gc.setServiceName(filePrefix + "%sService");
        gc.setServiceImplName(filePrefix + "%sServiceImpl");
        gc.setMapperName(filePrefix + "%sMapper");
        gc.setFileOverride(true);
        gc.setBaseResultMap(true);
        gc.setSwagger2(true);
        gc.setBaseColumnList(true);
        gc.setActiveRecord(false);
        return gc;
    }

    /**
     * @param packageName
     * @param moduleName
     * @name: initInjectionConfig
     * @description: 自定义配置
     * @return: InjectionConfig
     * @since: 2020/05/18 18:03
     * @author: FoeniX
     */
    private static InjectionConfig initInjectionConfig(String packageName, String moduleName) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("superColums", this.getConfig().getStrategyConfig().getSuperEntityColumns());
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //自定义输入文件名称
                return mapperPath + "/xml/" + moduleName + "/" + filePrefix + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * @param tableName
     * @param tablePrefix
     * @name: initStrategyConfig
     * @description: 策略配置
     * @return: StrategyConfig
     * @since: 2020/05/18 18:03
     * @author: FoeniX
     */
    private static StrategyConfig initStrategyConfig(String tableName, String... tablePrefix) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setTablePrefix(tablePrefix);
        strategy.setInclude(tableName);
        strategy.setRestControllerStyle(true);
        strategy.setSuperEntityClass("com.nextop.base.entity.BaseEntity");
        strategy.setSuperEntityColumns("update_time", "create_time", "create_id", "update_id");
        strategy.setEntityTableFieldAnnotationEnable(true);
        return strategy;
    }

    /**
     * @param mapperFile
     * @name: initTemplateConfig
     * @description: 覆盖Entity以及xml
     * @return: TemplateConfig
     * @since: 2020/05/18 18:03
     * @author: FoeniX
     */
    private static TemplateConfig initTemplateConfig(String mapperFile) {
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        tc.setController(null); // 不生成对应的controller
        tc.setServiceImpl(null);
        tc.setService(null);
        //如果当前Entity已经存在,那么仅仅覆盖Entity和MapperXml
        File file = new File(mapperFile);
        if (file.exists()) {
            tc.setController(null);
            tc.setMapper(null);
            tc.setService(null);
            tc.setServiceImpl(null);
        }
        return tc;
    }

    public static class JdbcRepository {
        private static Pattern linePattern = Pattern.compile("_(\\w)");
        private JdbcOperations jdbcOperations;

        public JdbcRepository() {
            DataSource dataSource = DataSourceBuilder.create()
                    //如果不指定类型，那么默认使用连接池，会存在连接不能回收而最终被耗尽的问题
                    .type(DriverManagerDataSource.class)
                    .driverClassName(driverName)
                    .url(url)
                    .username(userName)
                    .password(word)
                    .build();
            this.jdbcOperations = new JdbcTemplate(dataSource);
        }

        /**
         * @param tableNames
         * @param tablePrefixArray
         * @name: getEntityNames
         * @description: 获取所有实体类的名字, 实体类由数据库表名转换而来. 例如: 表前缀为auth,完整表名为auth_first_second,那么entity则为FirstSecond
         * @return: java.util.Map<String, String>
         * @since: 2020/05/18 18:03
         * @author: FoeniX
         */
        public Map<String, String> getEntityNames(List<String> tableNames, String... tablePrefixArray) {
            if (CollectionUtils.isEmpty(tableNames)) {
                return new HashMap<>();
            }
            Map<String, String> result = new HashMap<>();
            tableNames.forEach(
                    tableName -> {
                        if (StringUtils.isNotBlank(tableName)) {
                            String entityName = underlineToCamel(tableName);
                            if (tablePrefixArray != null && tablePrefixArray.length != 0) {
                                for (String prefix : tablePrefixArray) {
                                    //如果有前缀,需要去掉前缀
                                    if (StringUtils.isNotBlank(prefix) && tableName.startsWith(prefix)) {
                                        String tableNameRemovePrefix = tableName.substring((prefix + "_").length());
                                        entityName = underlineToCamel(tableNameRemovePrefix);
                                    }
                                }
                            }
                            result.put(tableName, entityName);
                        }
                    }
            );
            return result;
        }

        /**
         * @param str
         * @name: underlineToCamel
         * @description: 下划线转驼峰
         * @return: String
         * @since: 2020/05/18 18:03
         * @author: FoeniX
         */
        private static String underlineToCamel(String str) {
            if (null == str || "".equals(str)) {
                return str;
            }
            str = str.toLowerCase();
            Matcher matcher = linePattern.matcher(str);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            }
            matcher.appendTail(sb);
            str = sb.toString();
            str = str.substring(0, 1).toUpperCase() + str.substring(1);
            return str;
        }

    }
}
