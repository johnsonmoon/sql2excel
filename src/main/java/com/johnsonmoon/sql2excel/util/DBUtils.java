package com.johnsonmoon.sql2excel.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

/**
 * Database operation utilities.
 * <p>
 * Created by johnsonmoon at 2019-05-11
 */
public class DBUtils {
    private static Logger logger = LoggerFactory.getLogger(DBUtils.class);

    /**
     * 创建数据库连接
     *
     * @param driverClassName jdbcDriver类名称
     * @param url             数据库连接地址
     * @param username        用户名
     * @param password        密码
     * @return {@link Connection}
     */
    public static Connection createConnection(String driverClassName, String url, String username, String password) {
        try {
            Class.forName(driverClassName);
        } catch (Throwable e) {
            logger.warn(e.getMessage(), e);
            throw new DBException(e.getMessage(), e);
        }
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (Throwable e) {
            logger.warn(e.getMessage(), e);
            throw new DBException(e.getMessage(), e);
        }
    }

    /**
     * 执行一个数据库查询，返回一个二维表格
     *
     * @param connection {@link Connection}
     * @param sql        查询语句
     * @return 表数据
     */
    public static List<Map<String, Object>> query(Connection connection, String sql) {
        if (connection == null) {
            logger.warn("Connection is null.");
            return new ArrayList<>();
        }
        if (sql == null || sql.isEmpty()) {
            logger.warn("Sql statement is null.");
            return new ArrayList<>();
        }
        Long count = count(connection, sql);
        if (count == 0) {
            logger.warn("Sql statement query result count is 0;");
            return new ArrayList<>();
        }
        List<Map<String, Object>> table = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            int columnCount = resultSet.getMetaData().getColumnCount();
            String[] columns = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columns[i] = resultSet.getMetaData().getColumnName(i + 1).toLowerCase();
            }

            while (resultSet.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 0; i < columnCount; i++) {
                    row.put(columns[i], resultSet.getObject(i + 1));
                }
                table.add(row);
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
            throw new DBException(e.getMessage(), e);
        } finally {
            close(statement, resultSet);
        }
        return table;
    }

    /**
     * 执行一个数据库查询，将行数据map传递给消费者
     *
     * @param connection {@link Connection}
     * @param sql        查询语句
     * @param consumer   {@link Consumer} 消费者
     * @return 表数据
     */
    public static void query(Connection connection, String sql, Consumer consumer) {
        if (connection == null) {
            logger.warn("Connection is null.");
            return;
        }
        if (sql == null || sql.isEmpty()) {
            logger.warn("Sql statement is null.");
            return;
        }
        Long count = count(connection, sql);
        if (count == 0) {
            logger.warn("Sql statement query result count is 0;");
            return;
        }
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            int columnCount = resultSet.getMetaData().getColumnCount();
            String[] columns = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columns[i] = resultSet.getMetaData().getColumnName(i + 1).toLowerCase();
            }
            Long rowIndex = -1L;
            while (resultSet.next()) {
                rowIndex++;
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 0; i < columnCount; i++) {
                    row.put(columns[i], resultSet.getObject(i + 1));
                }
                if (!consumer.consume(count, rowIndex, row)) {
                    logger.warn(String.format("Consume row data failed, rowIndex: %s, row data: %s, total count: %s", rowIndex, row, count));
                }
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
            throw new DBException(e.getMessage(), e);
        } finally {
            close(statement, resultSet);
        }
    }

    /**
     * 统计查询语句结果个数
     *
     * @param connection {@link Connection}
     * @param sql        查询语句
     */
    public static Long count(Connection connection, String sql) {
        if (connection == null) {
            logger.warn("Connection is null.");
            return 0L;
        }
        if (sql == null || sql.isEmpty()) {
            logger.warn("Sql statement is null.");
            return 0L;
        }
        Long count = 0L;
        ResultSet resultSetCount = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSetCount = statement.executeQuery(String.format("select count(*) as count from (%s) t", sql));
            if (resultSetCount.next()) {
                count = Long.parseLong(String.valueOf(resultSetCount.getObject("count")));
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
            throw new DBException(e.getMessage(), e);
        } finally {
            close(statement, resultSetCount);
        }
        return count;
    }

    /**
     * Consumer for receive row data map query from database.
     */
    public interface Consumer {
        /**
         * 逐行消费查询结果
         *
         * @param count    总行数
         * @param rowIndex 当前行位置 0
         * @param row      行数据
         * @return true/false
         */
        boolean consume(Long count, Long rowIndex, Map<String, Object> row);
    }

    /**
     * 执行一个更新语句
     *
     * @param connection {@link Connection}
     * @param sql        执行语句
     * @return 返回更新语句影响的记录数
     */
    public static int execute(Connection connection, String sql) {
        if (connection == null) {
            logger.warn("Connection is null.");
            return 0;
        }
        if (sql == null || sql.isEmpty()) {
            logger.warn("Sql statement is null.");
            return 0;
        }
        Statement statement = null;
        try {
            statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
            throw new DBException(e.getMessage(), e);
        } finally {
            close(statement);
        }
    }

    /**
     * 关闭各种SQL执行资源
     */
    public static void close(Object... objects) {
        try {
            for (Object obj : objects) {
                if (obj != null) {
                    if (obj instanceof ResultSet) {
                        ((ResultSet) obj).close();
                    } else if (obj instanceof Connection) {
                        ((Connection) obj).close();
                    } else if (obj instanceof Statement) {
                        ((Statement) obj).close();
                    } else {
                        throw new IllegalArgumentException("无法识别的DB资源：" + obj);
                    }
                }
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
            throw new DBException(e.getMessage(), e);
        }
    }
}
