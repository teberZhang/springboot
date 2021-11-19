package com.example.springboot.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@EnableScheduling
@Component
@Slf4j
public class TestTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(initialDelay = 3 * 1000, fixedDelay = 60 * 1000)
    public void testTodo() {

        log.info("testTodo begin-----：" + dateFormat.format(new Date()));
        try {
            log.info("testTask todo");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        log.info("testTodo end-------：" + dateFormat.format(new Date()));
    }
}
