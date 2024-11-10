package lima.xiao.thymeleafdemos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("lima.xiao.thymeleafdemos.mapper")
public class ThymeleafDemosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafDemosApplication.class, args);
    }

}
