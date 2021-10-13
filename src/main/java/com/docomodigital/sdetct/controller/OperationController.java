package com.docomodigital.sdetct.controller;

import com.docomodigital.sdetct.exception.ResourceInUseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@RestController
@RequestMapping("/operations")
@Slf4j
public class OperationController {

    private final RedisLockRegistry lockRegistry;

    public OperationController(RedisLockRegistry lockRegistry) {
        this.lockRegistry = lockRegistry;
    }

    @PostMapping(value = "/{id}/refund", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void refund(@PathVariable(name = "id", required = true) UUID id) {
        final Lock lock = lockRegistry.obtain(id.toString());

        try {
            final boolean acquired = lock.tryLock(500, TimeUnit.MILLISECONDS);
            if (acquired) {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    throw new RuntimeException("Error during refund");
                } finally {
                    lock.unlock();
                }
            } else {
                throw new RuntimeException("Error acquiring lock");
            }
        } catch (Exception e) {
            throw new ResourceInUseException("Operation " + id + " is in use", e);
        }
    }
}

@ControllerAdvice
class ResourceInUseAdvice {
    @ResponseBody
    @ExceptionHandler(ResourceInUseException.class)
    @ResponseStatus(HttpStatus.LOCKED)
    String employeeNotFoundHandler(ResourceInUseException ex) {
        return ex.getMessage();
    }
}
