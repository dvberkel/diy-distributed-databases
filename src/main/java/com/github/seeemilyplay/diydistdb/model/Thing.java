package com.github.seeemilyplay.diydistdb.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;

public class Thing 
{
    private int id;
    private String value;
    private long timestamp;

    public Thing() {
        this(0, null);
    }

    public Thing(int id,
                 String value) {
        this(id, value, System.currentTimeMillis());
    }

    public Thing(int id,
                 String value,
                 long timestamp) {
        this.id = id;
        this.value = value;
        this.timestamp = timestamp;
    }

    @JsonProperty("Id")
    public int getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("Value")
    public String getValue() {
        return value;
    }

    @JsonProperty("Value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty("Timestamp")
    public long getTimestamp() {
        return timestamp;
    }

    @JsonProperty("Timestamp")
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
    }
}
