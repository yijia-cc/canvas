package com.yijiacc.canvas.app.retry;

public class RetryStrategy {
    public static boolean instant(RetryCallback callBack, int retryLimit) {
        for (int retry = 0; retry < retryLimit; retry++) {
            try {
                callBack.execute();
                return true;
            } catch (InterruptedException e) {
                return false;
            } catch (Exception ignore) {
            }
        }
        return false;
    }
}
