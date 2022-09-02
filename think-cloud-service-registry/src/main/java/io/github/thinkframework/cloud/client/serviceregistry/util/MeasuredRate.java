package io.github.thinkframework.cloud.client.serviceregistry.util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 统计单位时间内的次数
 * @author lixiaobin
 */
public class MeasuredRate {

    /**
     * 定时器
     */
    private Timer timer;

    /**
     * 调用间隔
     */
    private long interval;

    /**
     * 最后一分钟的请求数
     */
    private AtomicInteger lastBucket = new AtomicInteger(); // LongAdder

    /**
     * 当前这一分钟的请求数
     */
    private AtomicInteger currentBucket = new AtomicInteger(); // LongAdder

    /**
     * 活跃
     */
    private boolean active;

    public MeasuredRate(long interval){
        this.interval = interval;
        this.timer = new Timer("service-registry-MeasuredRateTimer");
    }

    public synchronized void start(){
        if(!active) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // 清空这一分钟的请求数,并设置最后一分钟的请求数
                    lastBucket.set(currentBucket.getAndSet(0));
                }
            }, interval, interval);
            active = true; // 活跃
        }
    }

    public synchronized void stop() {
        if(active){
            timer.cancel();
            active = !active; // 取消
        }
    }

    public /* synchronized */ int count() {
        return lastBucket.get();
    }

    public /* synchronized */ void increment(){
        // FIXME 线程安全问题,最近修复
        currentBucket.incrementAndGet();
    }
}
