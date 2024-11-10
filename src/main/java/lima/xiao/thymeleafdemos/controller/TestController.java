package lima.xiao.thymeleafdemos.controller;

import lima.xiao.thymeleafdemos.entity.FileProcessTask;
import lima.xiao.thymeleafdemos.entity.FileProcessTaskExample;
import lima.xiao.thymeleafdemos.mapper.FileProcessTaskDao;
import lima.xiao.thymeleafdemos.repository.FileProcessTaskRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    private final FileProcessTaskDao fileProcessTaskDao;

    private final FileProcessTaskRepository fileProcessTaskRepository;

    public TestController(FileProcessTaskDao fileProcessTaskMapper, FileProcessTaskRepository fileProcessTaskRepository) {
        this.fileProcessTaskDao = fileProcessTaskMapper;
        this.fileProcessTaskRepository = fileProcessTaskRepository;
    }

    @GetMapping("/dbtest")
    public String dbTest() {
        FileProcessTaskExample fileProcessTaskExample = new FileProcessTaskExample();
        List<FileProcessTask> fileProcessTasks = fileProcessTaskDao.selectByExample(fileProcessTaskExample);

        return fileProcessTasks.toString();
    }

    @GetMapping("/task")
    public Object queryTaskTest(@RequestParam("taskId") String taskId) {
        return fileProcessTaskRepository.queryProcessByTaskId(taskId);
    }

}
