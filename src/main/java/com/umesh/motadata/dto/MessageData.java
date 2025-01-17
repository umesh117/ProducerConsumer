package com.umesh.motadata.dto;

/**
 * @author umesh.b
 * ProducerConsumer
 */
public class MessageData {

    private String id;

    private Double data;

    private Integer processedCode;

    public MessageData(String id, Double data, Integer processedCode) {
        this.id = id;
        this.data = data;
        this.processedCode = processedCode;
    }

    public String getId() {
        return id;
    }

    public Double getData() {
        return data;
    }

    public Integer getProcessedCode() {
        return processedCode;
    }

    public void setProcessedCode(Integer processedCode) {
        this.processedCode = processedCode;
    }

    @Override
    public String toString() {
        return "MessageData{" +
                "id='" + id + '\'' +
                ", data='" + data + '\'' +
                ", processedCode=" + processedCode +
                '}';
    }
}
