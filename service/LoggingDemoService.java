package com.rr.example.spring_jpa.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoggingDemoService {

    private static final Logger log = LogManager.getLogger(LoggingDemoService.class);
    private static final Marker SECURITY = MarkerManager.getMarker("SECURITY");

    public void runDemo() {
        ThreadContext.put("requestId", UUID.randomUUID().toString());
        try {
            log.trace("Trace-level detail");
            log.debug("Debug context loaded");
            log.info("Business event occurred");
            log.warn("Something looks suspicious");
            log.error("Recoverable error, continuing");
            log.fatal(SECURITY, "Security-critical event");

            tryOperation();
        } catch (Exception ex) {
            log.error("Operation failed", ex);
        } finally {
            ThreadContext.clearMap();
        }
    }

    private void tryOperation() {
        log.debug("Trying operation...");
        if (System.currentTimeMillis() % 2 == 0) {
            throw new IllegalStateException("Random failure for demo");
        }
        log.info("Operation succeeded");
    }
}


