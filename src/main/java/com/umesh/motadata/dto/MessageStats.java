package com.umesh.motadata.dto;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author umesh.b
 * ProducerConsumer
 */
public class MessageStats {
    //AtomicInteger is used in case of multiple consumer threads are created
    private AtomicInteger success;
    private AtomicInteger fail;
    private AtomicInteger total;

    public MessageStats() {
        this.success = new AtomicInteger(0) ;
        this.fail = new AtomicInteger(0) ;
        this.total = new AtomicInteger(0) ;
    }

    public Integer getSuccess() {
        return success.get();
    }


    public Integer getFail() {
        return fail.get();
    }

    public Integer getTotal() {
        return total.get();
    }

    /**
     * updates the fail count
     *
     * @return updated messageStats
     */
    public MessageStats updateFailAndGet() {
        this.fail.incrementAndGet();
        this.total.incrementAndGet();
        return this;
    }

    /**
     * updates the success count
     *
     * @return updated messageStats
     */
    public MessageStats updateSuccessAndGet() {
        this.success.incrementAndGet();
        this.total.incrementAndGet();
        return this;
    }

    @Override
    public String toString() {
        return "MessageStats{" +
                "success=" + success +
                ", fail=" + fail +
                ", total=" + total +
                '}';
    }
}
