package com.johnsonmoon.sql2excel.entity;


/**
 * Create by xuyh at 2019/5/11 15:43.
 */
public class Task {
    private String taskId;
    private Boolean finished = false;
    private Boolean interrupted = false;
    private String errorMessage;
    private Long count = 0L;
    private Long index = 0L;
    private String storageFilePathName;
    private Long createTime = System.currentTimeMillis();

    private String className;
    private String url;
    private String userName;
    private String password;
    private String sql;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getStorageFilePathName() {
        return storageFilePathName;
    }

    public void setStorageFilePathName(String storageFilePathName) {
        this.storageFilePathName = storageFilePathName;
    }

    public Boolean getInterrupted() {
        return interrupted;
    }

    public void setInterrupted(Boolean interrupted) {
        this.interrupted = interrupted;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", finished=" + finished +
                ", interrupted=" + interrupted +
                ", errorMessage='" + errorMessage + '\'' +
                ", count=" + count +
                ", index=" + index +
                ", storageFilePathName='" + storageFilePathName + '\'' +
                ", createTime=" + createTime +
                ", className='" + className + '\'' +
                ", url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}
