package de.movope.cheesechess.web.api;

public class MoveResource {

    private String from;
    private String to;

    public MoveResource() {
    }

    public void setFrom(String from) {

        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {

        return from;
    }
}
