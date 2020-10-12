package com.nephew.jk.manager;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//省略了import
public class MysqlGenerator {

    /**
     * 包名
     */
    private static final String PACKAGE_NAME = "com.nephew.jk";
    /**
     * 模块名称
     */
    private static final String MODULE_NAME = "biz";
    /**
     * 输出文件的路径
     */
    private static final String OUT_PATH = "E:\\mybatis-plus";
    /**
     * 代码生成者
     */
    private static final String AUTHOR = "";

    /**
     * JDBC相关配置
     */
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    // 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中

        /**
         * <p>
         * 读取控制台内容
         * </p>
         */
        public static String scanner(String tip) {
            Scanner scanner = new Scanner(System.in);
            StringBuilder help = new StringBuilder();
            help.append("请输入" + tip + "：");
            System.out.println(help.toString());
            if (scanner.hasNext()) {
                String ipt = scanner.next();
                if (StringUtils.isNotEmpty(ipt)) {
                    return ipt;
                }
            }
            throw new MybatisPlusException("请输入正确的" + tip + "！");
        }

        public static void main(String[] args) {
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();

            // 全局配置
            GlobalConfig gc = new GlobalConfig();
            String projectPath = System.getProperty("user.dir");
            //gc.setOutputDir(projectPath + "/src/main/java");
            gc.setOutputDir(OUT_PATH);
            gc.setAuthor("");
            gc.setOpen(false);
            gc.setBaseColumnList(true);
            gc.setBaseResultMap(true);
            gc.setControllerName("%sController");
            gc.setServiceName("%sService");
            gc.setServiceImplName("%sServiceImpl");
            gc.setMapperName("%sMapper");
            //gc.setSwagger2(true); //实体属性 Swagger2 注解
            gc.setIdType(IdType.INPUT);
            mpg.setGlobalConfig(gc);

            // 数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8");
            // dsc.setSchemaName("public");
            dsc.setDriverName("com.mysql.jdbc.Driver");
            dsc.setUsername("root");
            dsc.setPassword("root");
            mpg.setDataSource(dsc);

            // 包配置
            PackageConfig pc = new PackageConfig();
            pc.setParent("com.nephew.jk");
            pc.setController("controller");
            pc.setEntity("domain");
            pc.setMapper("mapper");
            pc.setService("service");
            pc.setServiceImpl("service");
            mpg.setPackageInfo(pc);

            // 自定义配置
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    // to do nothing
                }
            };

            // 如果模板引擎是 freemarker
            //String templatePath = "/templates/mapper.xml.ftl";
            // 如果模板引擎是 velocity
              String templatePath = "/templates/mapper.xml.vm";

            // 自定义输出配置
            List<FileOutConfig> focList = new ArrayList<>();
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    //return projectPath + "/src/main/resources/mapper/" //+ pc.getModuleName()
                    //        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                    return "E:/mybatis-plus/xml" //+ pc.getModuleName()
                            + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });

        /*cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("F:\\mybatis-plus");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });*/

            cfg.setFileOutConfigList(focList);
            mpg.setCfg(cfg);

            // 配置模板
            TemplateConfig templateConfig = new TemplateConfig();

            // 配置自定义输出模板
            //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
            /*templateConfig.setEntity("templates/entity.java");
            templateConfig.setService("templates/service.java");
            templateConfig.setController("templates/controller.java");
            templateConfig.setServiceImpl("templates/serviceImpl.java");
            templateConfig.setMapper("templates/mapper.java");*/
            templateConfig.setXml(null);
            mpg.setTemplate(templateConfig);

            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            strategy.setNaming(NamingStrategy.underline_to_camel);
            strategy.setColumnNaming(NamingStrategy.underline_to_camel);
            //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
            strategy.setEntityLombokModel(true);
            strategy.setRestControllerStyle(true);
            // 公共父类
            //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
            // 写于父类中的公共字段
            //strategy.setSuperEntityColumns("id");

            // 自定义需要填充的字段
            List<TableFill> tableFillList = new ArrayList<TableFill>();
            //如 每张表都有一个创建时间、修改时间
            //而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
            //修改时，修改时间会修改，
            //虽然像Mysql数据库有自动更新几只，但像ORACLE的数据库就没有了，
            //使用公共字段填充功能，就可以实现，自动按场景更新了。
            //如下是配置
            TableFill createField = new TableFill("gmt_create", FieldFill.INSERT);
            TableFill modifiedField = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
            tableFillList.add(createField);
            tableFillList.add(modifiedField);
            strategy.setTableFillList(tableFillList);

            //strategy.setInclude(scanner("user").split(","));
            strategy.setInclude("user");
            strategy.setControllerMappingHyphenStyle(true);
            strategy.setCapitalMode(true);
            //strategy.setTablePrefix(pc.getModuleName() + "_");
            mpg.setStrategy(strategy);
            mpg.setTemplateEngine(new VelocityTemplateEngine());
            mpg.execute();
        }

}