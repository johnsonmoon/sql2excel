package com.johnsonmoon.sql2excel.entity.param;

/**
 * Create by xuyh at 2019/5/11 15:48.
 */
public class TaskCreateParam {
    private String className;
    private String url;
    private String userName;
    private String password;
    private String sql;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "TaskCreateParam{" +
                "className='" + className + '\'' +
                ", url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}
