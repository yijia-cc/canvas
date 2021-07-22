package com.yijiacc.canvas.app.retry;

@FunctionalInterface
public interface RetryCallback {
    void execute() throws Exception;
}
