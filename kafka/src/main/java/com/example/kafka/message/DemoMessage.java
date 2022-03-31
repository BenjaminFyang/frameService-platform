package com.example.kafka.message;

/**
 * Message 消息
 */
public class DemoMessage {

    public static final String TOPIC = "DEMO_06";

    /**
     * 编号
     */
    private Integer id;

    public DemoMessage setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Demo06Message{" +
                "id=" + id +
                '}';
    }

}
