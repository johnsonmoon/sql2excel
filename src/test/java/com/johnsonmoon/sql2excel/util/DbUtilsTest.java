package com.johnsonmoon.sql2excel.util;

import org.junit.Test;


/**
 * Create by xuyh at 2019/5/11 14:30.
 */
public class DbUtilsTest {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://192.168.100.36:3306/sql2excel_test?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    private String name = "dbuser";
    private String password = "DBUser123!";

    @Test
    public void testCreateTable() {
        System.out.println(DBUtils.execute(
                DBUtils.createConnection(driver, url, name, password),
                "CREATE TABLE `plugin` (\n" +
                        "  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '数据库表中的唯一ID',\n" +
                        "  `code` varchar(100) NOT NULL COMMENT '插件编码',\n" +
                        "  `name` varchar(255) DEFAULT NULL COMMENT '插件名',\n" +
                        "  `description` varchar(255) DEFAULT NULL COMMENT '插件描述',\n" +
                        "  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',\n" +
                        "  `modify_time` timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',\n" +
                        "  PRIMARY KEY (`id`)\n" +
                        ") ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '插件表';"));
    }

    @Test
    public void testInsertData() {
        System.out.println(DBUtils.execute(
                DBUtils.createConnection(driver, url, name, password),
                "insert into `plugin`(`code`, `name`, `description`) values('C002', 'name_002', 'desc_002')"));
        System.out.println(DBUtils.execute(
                DBUtils.createConnection(driver, url, name, password),
                "insert into `plugin`(`code`, `name`, `description`) values('C003', 'name_003', 'desc_003')"));
        System.out.println(DBUtils.execute(
                DBUtils.createConnection(driver, url, name, password),
                "insert into `plugin`(`code`, `name`, `description`) values('C004', 'name_004', 'desc_004')"));
        System.out.println(DBUtils.execute(
                DBUtils.createConnection(driver, url, name, password),
                "insert into `plugin`(`code`, `name`, `description`) values('C005', 'name_005', 'desc_005')"));
    }

    @Test
    public void testQuery() {
        System.out.println(DBUtils.query(
                DBUtils.createConnection(driver, url, name, password),
                "select * from `plugin`"
        ));
    }

    @Test
    public void testCount() {
        System.out.println(
                DBUtils.count(
                        DBUtils.createConnection(driver, url, name, password),
                        "select * from plugin"
                )
        );
    }

    @Test
    public void testConsume() {
        DBUtils.query(
                DBUtils.createConnection(driver, url, name, password),
                "select * from plugin",
                (count, index, row) -> {
                    System.out.println(String.format("[%s --> %s], %s", count, index, row));
                    return true;
                }
        );
    }
}
