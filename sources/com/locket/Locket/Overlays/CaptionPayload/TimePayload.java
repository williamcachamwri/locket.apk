package com.locket.Locket.Overlays.CaptionPayload;

public class TimePayload implements CaptionPayload {
    private long date;

    public TimePayload(long j) {
        this.date = j;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long j) {
        this.date = j;
    }
}
