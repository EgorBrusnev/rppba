package by.rppba.production.util;

public enum Status {
    CREATED(null),
    PLANNED(CREATED),
    IN_PROGRESS(PLANNED),
    DONE(IN_PROGRESS),
    DEPARTED(DONE);

    private Status prevStatus;

    Status(Status prevStatus) {
        this.prevStatus = prevStatus;
    }

    public Status getPrevStatus() {
        return prevStatus;
    }
}
