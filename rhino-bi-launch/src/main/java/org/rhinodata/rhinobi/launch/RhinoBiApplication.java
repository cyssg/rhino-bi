package org.rhinodata.rhinobi.launch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = {"org.rhinodata.rhinobi" })
@EnableCaching
@ServletComponentScan
public class RhinoBiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RhinoBiApplication.class, args);
    }

}
