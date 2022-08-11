package ru.javarush.golf.pasetskayaelena.islandmodel.utils;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LogExceptionScheduledExecutorService {
    private final ScheduledExecutorService scheduledExecutorService;
    private static final String DEFAULT_FAILURE_MSG = "Failure occurred when running scheduled task.";

    public LogExceptionScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public CompletableFuture<Object> scheduleWithFixedDelay(Callable callable, String onFailureMsg, long initialDelay, long delay, TimeUnit unit) {
        final String msg = StringUtilsisEmpty(onFailureMsg) ? DEFAULT_FAILURE_MSG : onFailureMsg;
        CompletableFuture<Object> delayed = new CompletableFuture<>();

        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                Object result = callable.call();
                delayed.complete(result);
            } catch (Throwable th) {
                System.out.println(msg);
                th.printStackTrace();

                delayed.completeExceptionally(th);
            }
        }, initialDelay, delay, unit);

        return delayed;
    }

    public CompletableFuture<Void> scheduleWithFixedDelay(Runnable runnable, String onFailureMsg, long initialDelay, long delay, TimeUnit unit) {
        final String msg = StringUtilsisEmpty(onFailureMsg) ? DEFAULT_FAILURE_MSG : onFailureMsg;
        CompletableFuture<Void> delayed = new CompletableFuture<>();

        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                runnable.run();
                delayed.complete(null);
            } catch (Throwable th) {
                System.out.println(msg);
                th.printStackTrace();

                delayed.completeExceptionally(th);
            }
        }, initialDelay, delay, unit);

        return delayed;
    }

    public CompletableFuture<Object> schedule(Callable callable, String failureMsg, long delay, TimeUnit unit) {
        final String msg = StringUtilsisEmpty(failureMsg) ? DEFAULT_FAILURE_MSG : failureMsg;
        CompletableFuture<Object> delayed = new CompletableFuture<>();

        scheduledExecutorService.schedule(() -> {
            try {
                Object result = callable.call();
                delayed.complete(result);
            } catch (Throwable th) {
                System.out.println(msg);
                th.printStackTrace();

                delayed.completeExceptionally(th);
            }
        }, delay, unit);

        return delayed;
    }

    public CompletableFuture<Void> schedule(Runnable runnable, String failureMsg, long delay, TimeUnit unit) {
        final String msg = StringUtilsisEmpty(failureMsg) ? DEFAULT_FAILURE_MSG : failureMsg;
        CompletableFuture<Void> delayed = new CompletableFuture<>();

        scheduledExecutorService.schedule(() -> {
            try {
                runnable.run();
                delayed.complete(null);
            } catch (Throwable th) {
                System.out.println(msg);
                th.printStackTrace();

                delayed.completeExceptionally(th);
            }
        }, delay, unit);

        return delayed;
    }

    public CompletableFuture<Object> scheduleAtFixedRate(Callable callable, String failureMsg, long initialDelay, long period, TimeUnit unit) {
        final String msg = StringUtilsisEmpty(failureMsg) ? DEFAULT_FAILURE_MSG : failureMsg;
        CompletableFuture<Object> delayed = new CompletableFuture<>();

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                Object result = callable.call();
                delayed.complete(result);
            } catch (Throwable th) {
                System.out.println(msg);
                th.printStackTrace();

                delayed.completeExceptionally(th);
            }
        }, initialDelay, period, unit);

        return delayed;
    }

    private static boolean StringUtilsisEmpty(String string) {
        return string == null ? true : string.isBlank();
    }

    public CompletableFuture<Void> scheduleAtFixedRate(Runnable runnable, String failureMsg, long initialDelay, long period, TimeUnit unit) {
        final String msg = StringUtilsisEmpty(failureMsg) ? DEFAULT_FAILURE_MSG : failureMsg;
        CompletableFuture<Void> delayed = new CompletableFuture<>();

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                runnable.run();
                delayed.complete(null);
            } catch (Throwable th) {
                System.out.println(msg);
                th.printStackTrace();

                delayed.completeExceptionally(th);
            }
        }, initialDelay, period, unit);

        return delayed;
    }

    public CompletableFuture<Object> execute(Callable callable, String failureMsg) {
        final String msg = StringUtilsisEmpty(failureMsg) ? DEFAULT_FAILURE_MSG : failureMsg;
        CompletableFuture<Object> delayed = new CompletableFuture<>();

        scheduledExecutorService.execute(() -> {
            try {
                Object result = callable.call();
                delayed.complete(result);
            } catch (Throwable th) {
                System.out.println(msg);
                th.printStackTrace();

                delayed.completeExceptionally(th);
            }
        });

        return delayed;
    }

    public CompletableFuture<Void> execute(Runnable runnable, String failureMsg) {
        final String msg = StringUtilsisEmpty(failureMsg) ? DEFAULT_FAILURE_MSG : failureMsg;
        CompletableFuture<Void> delayed = new CompletableFuture<>();

        scheduledExecutorService.execute(() -> {
            try {
                runnable.run();
                delayed.complete(null);
            } catch (Throwable th) {
                System.out.println(msg);
                th.printStackTrace();

                delayed.completeExceptionally(th);
            }
        });

        return delayed;
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return scheduledExecutorService.awaitTermination(timeout, unit);
    }

    public List<Runnable> shutdownNow() {
        return scheduledExecutorService.shutdownNow();
    }

    public void shutdown() {
        scheduledExecutorService.shutdown();
    }
}
