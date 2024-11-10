package lima.xiao.thymeleafdemos.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static lima.xiao.thymeleafdemos.config.FileHandleConfig.STORAGE_DIR_PATH;

@Slf4j
@Service
public class FileProcessingService {


    private final ProgressTracker progressTracker;


    public FileProcessingService(ProgressTracker progressTracker) {
        this.progressTracker = progressTracker;
    }

    @Async
    public void processFile(String taskId, MultipartFile file) throws Exception {
        log.info("start handle file {}", file.getName());
        try (InputStream inputStream = file.getInputStream()) {
            progressTracker.initTaskProgress(taskId, file.getOriginalFilename(), "admin", file.getSize());

            // 假设有100个数据要处理，每次处理1个数据模拟进度
            for (int i = 1; i <= 100; i++) {
                // 模拟处理时间
                Thread.sleep(30);

                // 更新进度
                progressTracker.setProgress(taskId, i);
            }

            // 文件处理完成，将文件保存到文件系统
            Files.copy(inputStream, Path.of(STORAGE_DIR_PATH + taskId));
        }
        log.info("{} file handle complete", file.getName());
    }


}
