package lima.xiao.thymeleafdemos.controller;

import lima.xiao.thymeleafdemos.entity.FileProcessTask;
import lima.xiao.thymeleafdemos.repository.FileProcessTaskRepository;
import lima.xiao.thymeleafdemos.service.FileProcessingService;
import lima.xiao.thymeleafdemos.service.ProgressTracker;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static lima.xiao.thymeleafdemos.config.FileHandleConfig.STORAGE_DIR_PATH;

@Controller
public class FileController {

    private final FileProcessingService fileProcessingService;

    private final ProgressTracker progressTracker;

    private final FileProcessTaskRepository fileProcessTaskRepository;

    public FileController(FileProcessTaskRepository fileProcessTaskRepository, FileProcessingService fileProcessingService, ProgressTracker progressTracker) {
        this.fileProcessTaskRepository = fileProcessTaskRepository;
        this.fileProcessingService = fileProcessingService;
        this.progressTracker = progressTracker;
    }

    @PostMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {
        String taskId = UUID.randomUUID().toString();
        System.out.println("开始处理：" + Thread.currentThread().getName());
        fileProcessingService.processFile(taskId, file);
        return taskId;

    }

    @GetMapping("/progress")
    @ResponseBody
    public int getProgress(@RequestParam String taskId) {
        return progressTracker.getProgress(taskId);
    }

    @GetMapping("/download")
    public ResponseEntity<FileSystemResource> downloadProcessedFile(@RequestParam("taskId") String taskId) {
        FileProcessTask fileProcessTask = fileProcessTaskRepository.queryProcessByTaskId(taskId);
        String fileName = fileProcessTask.getFileName();
        FileSystemResource file = new FileSystemResource(STORAGE_DIR_PATH + taskId);

        String encodedFileName;
        try {
            encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        } catch (Throwable throwable) {
            encodedFileName = "download";
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file);
    }
}
