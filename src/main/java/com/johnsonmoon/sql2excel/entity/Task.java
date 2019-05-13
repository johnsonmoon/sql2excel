package com.johnsonmoon.sql2excel.entity;


/**
 * Create by xuyh at 2019/5/11 15:43.
 */
public class Task {
    /**
     * id for identify the task
     */
    private String taskId;
    /**
     * whether the task has done
     */
    private Boolean finished = false;
    /**
     * whether the task was interrupted
     */
    private Boolean interrupted = false;
    /**
     * whether the task was timeout
     */
    private Boolean timeout = false;
    /**
     * message for the task which was finally finished
     */
    private String errorMessage;
    /**
     * data row count for the task
     */
    private Long count = 0L;
    /**
     * current data row index for the task
     */
    private Long index = 0L;
    /**
     * storage excel file for the task which was finally finished
     */
    private String storageFilePathName;
    /**
     * task creation time
     */
    private Long createTime = System.currentTimeMillis();

    /**
     * JDBC driver class name for the task
     */
    private String className;
    /**
     * db connection url for the task
     */
    private String url;
    /**
     * user name for connecting the db
     */
    private String userName;
    /**
     * user password for connecting the db
     */
    private String password;
    /**
     * query sql statement string
     */
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

    public Boolean getTimeout() {
        return timeout;
    }

    public void setTimeout(Boolean timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", finished=" + finished +
                ", interrupted=" + interrupted +
                ", timeout=" + timeout +
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
