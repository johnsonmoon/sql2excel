package com.johnsonmoon.sql2excel.entity;

/**
 * Create by xuyh at 2019/5/11 15:20.
 */
public enum DBType {
    MYSQL("mysql", com.mysql.cj.jdbc.Driver.class.getName(), "8.0.15"),
    POSTGRESQL("postgresql", org.postgresql.Driver.class.getName(), "42.2.5"),
    H2("h2", org.h2.Driver.class.getName(), "1.4.199"),
    SQLITE("sqlite", org.sqlite.JDBC.class.getName(), "3.25.2"),
    MSSQL("mssql", com.microsoft.sqlserver.jdbc.SQLServerDriver.class.getName(), "6.4.8"),
    HSQLDB("hsqldb", org.hsqldb.jdbc.JDBCDriver.class.getName(), "2.4.1");
    private String code;
    private String className;
    private String version;

    DBType(String code, String className, String version) {
        this.code = code;
        this.className = className;
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "DBType{" +
                "code='" + code + '\'' +
                ", className='" + className + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
