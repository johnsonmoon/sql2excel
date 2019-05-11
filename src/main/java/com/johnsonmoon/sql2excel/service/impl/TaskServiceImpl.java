package com.johnsonmoon.sql2excel.service.impl;

import com.github.johnsonmoon.java2excel.core.FreeWriter;
import com.github.johnsonmoon.java2excel.core.entity.common.CellStyle;
import com.github.johnsonmoon.java2excel.core.entity.free.CellData;
import com.johnsonmoon.sql2excel.entity.Task;
import com.johnsonmoon.sql2excel.entity.param.TaskCreateParam;
import com.johnsonmoon.sql2excel.service.TaskService;
import com.johnsonmoon.sql2excel.util.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create by xuyh at 2019/5/11 16:36.
 */
@Component
public class TaskServiceImpl implements TaskService {
    private static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Value("${sql2excel.excel.file-dir}")
    private String fileDir;
    private int columnWidth = 4800;
    private static Map<String, Task> taskCache = new HashMap<>();
    private static ExecutorService taskThreadPool = Executors.newFixedThreadPool(4);

    @Override
    public Task create(TaskCreateParam param) {
        final Task task = new Task();
        task.setClassName(param.getClassName());
        task.setUrl(param.getUrl());
        task.setUserName(param.getUserName());
        task.setPassword(param.getPassword());
        task.setSql(param.getSql());

        String taskId = UUID.randomUUID().toString().replaceAll("-", "");
        String filePathName = fileDir + File.separator + taskId + ".xlsx";
        task.setTaskId(taskId);
        task.setStorageFilePathName(filePathName);
        taskCache.put(taskId, task);

        taskThreadPool.submit(() -> {
            String errorMessage = "Succeeded.";
            final FreeWriter freeWriter;
            try {
                freeWriter = new FreeWriter(filePathName);
                List<String> columns = new ArrayList<>();
                DBUtils.query(
                        DBUtils.createConnection(param.getClassName(), param.getUrl(), param.getUserName(), param.getPassword()),
                        param.getSql(),
                        (count, index, rowDataMap) -> {
                            task.setCount(count);
                            task.setIndex(index);
                            int row = Integer.parseInt(String.valueOf(index));
                            if (index == 0) {//header
                                List<CellData> header = new ArrayList<>();
                                int column = 0;
                                for (Map.Entry<String, Object> entry : rowDataMap.entrySet()) {
                                    columns.add(entry.getKey());
                                    CellData cellData = new CellData(row, column, entry.getKey(), CellStyle.CELL_STYLE_TYPE_COLUMN_HEADER);
                                    freeWriter.setExcelColumnWidth(0, column, columnWidth);
                                    header.add(cellData);
                                    column++;
                                }
                                if (!freeWriter.createExcel(header, 0, "DATA")) {
                                    return false;
                                }
                            }
                            //data
                            List<CellData> data = new ArrayList<>();
                            for (int column = 0; column < columns.size(); column++) {
                                CellData cellData = new CellData(row + 1, column, rowDataMap.get(columns.get(column)), CellStyle.CELL_STYLE_TYPE_COLUMN_HEADER);
                                data.add(cellData);
                                freeWriter.setExcelColumnWidth(0, column, columnWidth);
                            }
                            return freeWriter.writeExcelData(data, 0);
                        }
                );
                freeWriter.flush();
                freeWriter.close();
            } catch (Exception e) {
                logger.warn(e.getMessage(), e);
                errorMessage = e.getMessage();
                task.setInterrupted(true);
            } finally {
                task.setFinished(true);
                task.setErrorMessage(errorMessage);
            }
        });

        return task;
    }

    @Override
    public Task stop(String taskId) {
        //TODO
        return null;
    }

    @Override
    public Task pause(String taskId) {
        //TODO
        return null;
    }

    @Override
    public Task continueRun(String taskId) {
        //TODO
        return null;
    }

    @Override
    public Task status(String taskId) {
        return taskCache.get(taskId);
    }
}
