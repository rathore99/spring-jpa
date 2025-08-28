## Log4j2 in Spring Boot – Quick Guide

### Why logging
- **Debugging**: trace application flow and failures
- **Audit/Compliance**: persistent record of key events
- **Observability**: feed log aggregators (ELK, Loki, CloudWatch)

### Key concepts
- **Logger**: TRACE < DEBUG < INFO < WARN < ERROR < FATAL
- **Appender**: Console, File, RollingFile, Socket, JDBC
- **Layout**: PatternLayout, JsonLayout
- **Async logging**: LMAX Disruptor to offload I/O
- **MDC/ThreadContext**: per-request context (e.g., requestId)
- **Markers**: tag events for filtering (e.g., SECURITY)

### Quick start
- Added `spring-boot-starter-log4j2` and excluded `spring-boot-starter-logging`.
- Config file: `src/main/resources/log4j2.xml`.
- Demo endpoint: `GET /api/logs/demo` logs across levels.

### Important configurations (log4j2.xml)
- **Console appender** with `%X{requestId}` in pattern (MDC)
- **RollingFile** with daily + size rollover, keep last 7 files
- **JSON logs** via `JsonLayout` for machine parsing
- **Async appender** for non-blocking console output
- **Logger levels**: `com.rr.example.spring_jpa` at INFO, root at WARN

### Best practices
- **Use parameterized logs**: `log.info("User {} logged in", userId)`
- **Avoid sensitive data**: redact PII/credentials/tokens
- **Use MDC** for request correlation
- **Pick appropriate levels**; DEBUG for dev-only details
- **Prefer JSON** in production
- **Rotate/retain** logs; avoid unbounded files
- **Async logging** for throughput; test under load

### Change levels at runtime
- `monitorInterval` picks up `log4j2.xml` changes automatically
- With Spring Boot Actuator, use `GET/POST /actuator/loggers/{name}`

### Troubleshooting
- Ensure `log4j2.xml` is on classpath and named correctly
- Exclude `spring-boot-starter-logging` to avoid conflicts
- Raise package level to reduce verbosity
- Avoid string concatenation; use placeholders

### Code sample
```java
private static final Logger log = LogManager.getLogger(MyClass.class);

log.info("User {} created", userId);
log.warn("Low disk space: {} MB left", freeMb);
log.error("Failed to save", ex);

ThreadContext.put("requestId", reqId);
try {
  log.info("Processing request");
} finally {
  ThreadContext.clearMap();
}
```

### Files to review
- `pom.xml`: Log4j2 dependency and exclusions
- `src/main/resources/log4j2.xml`: appenders, layouts, policies, levels
- `LoggingDemoService`, `LoggingDemoController`: usage examples


