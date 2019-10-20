package cn.ihep.jdy.release;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
public class ReleaseApplication {
    private static final Logger logger = LoggerFactory.getLogger(ReleaseApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ReleaseApplication.class, args);
        logger.warn("warning msg");
        logger.error("error msg");
    }

}
