package lima.xiao.thymeleafdemos.repository;

import lima.xiao.thymeleafdemos.entity.FileProcessTask;
import lima.xiao.thymeleafdemos.entity.FileProcessTaskExample;
import lima.xiao.thymeleafdemos.mapper.FileProcessTaskDao;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class FileProcessTaskRepository {
    private final FileProcessTaskDao fileProcessTaskDao;


    public FileProcessTaskRepository(FileProcessTaskDao fileProcessTaskDao) {
        this.fileProcessTaskDao = fileProcessTaskDao;
    }


    public void addTask(FileProcessTask fileProcessTask) {
        fileProcessTask.setGmtCreated(new Date());
        fileProcessTask.setGmtModified(new Date());
        fileProcessTaskDao.insert(fileProcessTask);
    }

    public FileProcessTask queryProcessByTaskId(String taskId) {
        FileProcessTaskExample example = new FileProcessTaskExample();
        FileProcessTaskExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskId);
        List<FileProcessTask> fileProcessTasks = fileProcessTaskDao.selectByExample(example);
        return CollectionUtils.isEmpty(fileProcessTasks) ? null : fileProcessTasks.get(0);
    }

    public void removeProcessByTaskId(String taskId) {
        FileProcessTaskExample example = new FileProcessTaskExample();
        FileProcessTaskExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskId);
        fileProcessTaskDao.deleteByExample(example);
    }


    public void updateProgress(FileProcessTask oldTask) {
        oldTask.setGmtModified(new Date());
        FileProcessTaskExample example = new FileProcessTaskExample();
        FileProcessTaskExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(oldTask.getTaskId());
        fileProcessTaskDao.updateByExample(oldTask, example);
    }
}
