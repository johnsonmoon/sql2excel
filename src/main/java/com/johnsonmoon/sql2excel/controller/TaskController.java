package com.johnsonmoon.sql2excel.controller;

import com.johnsonmoon.sql2excel.common.ext.WebException;
import com.johnsonmoon.sql2excel.entity.DBType;
import com.johnsonmoon.sql2excel.entity.Task;
import com.johnsonmoon.sql2excel.entity.param.TaskCreateParam;
import com.johnsonmoon.sql2excel.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * Create by xuyh at 2019/5/11 15:09.
 */
@Api("Task sql2excel")
@RestController
public class TaskController {
    private static Logger logger = LoggerFactory.getLogger(TaskController.class);

    private static final List<Map<String, Object>> dbTypes;

    @Autowired
    private TaskService taskService;

    static {
        dbTypes = new ArrayList<>();
        for (DBType dbType : DBType.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", dbType.getCode());
            map.put("className", dbType.getClassName());
            map.put("version", dbType.getVersion());
            dbTypes.add(map);
        }
    }

    @GetMapping("/db/supported")
    @ApiOperation("List system supported DB types information.")
    public List<Map<String, Object>> listSupportedDBTypes() {
        return dbTypes;
    }

    @PostMapping(value = "/task/create", consumes = "application/json")
    @ApiOperation("Create a task for querying data and generate a excel file.")
    public Task createTask(@RequestBody TaskCreateParam param) {
        if (param.getClassName() == null || param.getClassName().isEmpty()
                || param.getPassword() == null || param.getPassword().isEmpty()
                || param.getSql() == null || param.getSql().isEmpty()
                || param.getUrl() == null || param.getUrl().isEmpty()
                || param.getUserName() == null || param.getUserName().isEmpty()) {
            throw new WebException("Param error.");
        }
        return taskService.create(param);
    }

    @GetMapping("/task/status")
    @ApiOperation("Status of a task.")
    public Task status(@RequestParam("taskId") String taskId) {
        if (taskId == null || taskId.isEmpty()) {
            throw new WebException("Param error.");
        }
        return taskService.status(taskId);
    }

    @ApiOperation("Download file.")
    @GetMapping("/download")
    public void downloadFile(@RequestParam("file") String file,
                             HttpServletResponse httpServletResponse) {
        if (file == null || file.isEmpty()) {
            throw new WebException("File path name is null!");
        }
        File fileF = new File(file);
        if (!fileF.exists()) {
            throw new WebException("File not exists!");
        }
        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        httpServletResponse.setHeader("Content-Type", "application/octet-stream");
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + fileF.getName());
        try {
            fileInputStream = new FileInputStream(fileF);
            outputStream = httpServletResponse.getOutputStream();
            byte[] bytes = new byte[32];
            int length;
            while ((length = fileInputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, length);
            }
            outputStream.flush();
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            throw new WebException(e.getMessage(), e);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                logger.warn(e.getMessage(), e);
            }
        }
    }
}
