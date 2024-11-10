package lima.xiao.thymeleafdemos.service;


import lima.xiao.thymeleafdemos.entity.FileProcessTask;
import lima.xiao.thymeleafdemos.repository.FileProcessTaskRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProgressTracker {
    private final FileProcessTaskRepository fileProcessTaskRepository;

    public ProgressTracker(FileProcessTaskRepository fileProcessTaskRepository) {
        this.fileProcessTaskRepository = fileProcessTaskRepository;
    }

    public void setProgress(String taskId, int progress) {
        FileProcessTask oldTask = fileProcessTaskRepository.queryProcessByTaskId(taskId);
        if (oldTask == null) {
            FileProcessTask fileProcessTask = new FileProcessTask();
            fileProcessTask.setTaskId(taskId);
            fileProcessTask.setProgress(progress);
            fileProcessTask.setOperator("admin");
            fileProcessTaskRepository.addTask(fileProcessTask);
        } else {
            oldTask.setProgress(progress);
            fileProcessTaskRepository.updateProgress(oldTask);
        }
    }

    public void initTaskProgress(String taskId, String fileName, String operator, Long fileSize) {
        FileProcessTask fileProcessTask = new FileProcessTask();
        fileProcessTask.setTaskId(taskId);
        fileProcessTask.setFileName(fileName);
        fileProcessTask.setOperator(operator);
        fileProcessTask.setFileSize(fileSize);
        fileProcessTask.setProgress(0);
        fileProcessTask.setGmtCreated(new Date());
        fileProcessTask.setGmtCreated(new Date());
        fileProcessTaskRepository.addTask(fileProcessTask);
    }

    public int getProgress(String taskId) {
        FileProcessTask fileProcessTask = fileProcessTaskRepository.queryProcessByTaskId(taskId);
        if (fileProcessTask == null) {
            throw new RuntimeException("not fond file task info! task: " + taskId);
        }
        return fileProcessTask.getProgress();
    }

    public void removeTask(String taskId) {
        fileProcessTaskRepository.removeProcessByTaskId(taskId);
    }
}
