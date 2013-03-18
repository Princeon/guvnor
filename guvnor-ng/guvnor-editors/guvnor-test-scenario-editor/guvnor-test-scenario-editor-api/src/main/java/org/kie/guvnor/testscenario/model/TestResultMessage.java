package org.kie.guvnor.testscenario.model;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class TestResultMessage {

    private String message;
    private Long timestamp;
    private boolean successful;

    public TestResultMessage() {
    }

    public TestResultMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
