package com.umesh.motadata.threads;

import com.umesh.motadata.dto.MessageData;
import com.umesh.motadata.dto.MessageQueue;
import com.umesh.motadata.dto.MessageStats;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author umesh.b
 * ProducerConsumer
 */
public class Consumer extends Thread {

    private MessageQueue queue;
    private Double dataSize;
    AtomicReference<MessageStats> stats;

    public Consumer(MessageQueue queue, AtomicReference<MessageStats> stats, double dataSize) {
        this.queue = queue;
        this.stats = stats;
        this.dataSize = dataSize;
    }

    @Override
    public void run() {
        System.out.println(Instant.now() + " : " + "C-thread started");
        processData();
    }

    private void processData() {
        try {
            while (true) {
                System.out.println(Thread.currentThread().getName());
                if (queue.isEmpty() && stats.get().getTotal() == dataSize.intValue()) {
                    System.out.println(Instant.now() + " : " + stats.get());
                    System.out.println("closed: "+Thread.currentThread().getName());
                    break;
                }
                MessageData data = queue.poll();
                if (data != null) {
                    if (data.getData() > 100 && data.getData() < 500) {
                        stats.updateAndGet(messageStats -> messageStats.updateFailAndGet());
                        data.setProcessedCode(400);
                    } else {
                        stats.updateAndGet(messageStats -> messageStats.updateSuccessAndGet());
                        data.setProcessedCode(200);
                    }
                    System.out.println(Instant.now() + " : " + "Consumer: " + Instant.now() + " : " + data);
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
