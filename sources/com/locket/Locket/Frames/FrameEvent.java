package com.locket.Locket.Frames;

import android.util.Log;
import com.locket.Locket.Util;
import com.locket.Locket.Widgets.WidgetSize;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FrameEvent {
    private final LocalDateTime endAt;
    private final LocalDateTime startAt;
    private final String url;
    private final boolean useLocalTime;
    private final List<String> variants;

    public static String getImageSize(int i) {
        return i >= 480 ? "@3x" : i >= 240 ? "@2x" : "";
    }

    public FrameEvent(String str, String str2, boolean z, String str3, String str4, List<String> list) {
        this.url = str2;
        this.startAt = parseDate(str3);
        this.endAt = parseDate(str4);
        this.variants = list;
        this.useLocalTime = z;
    }

    public String getImage(boolean z, WidgetSize widgetSize, int i) {
        String str = this.url;
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return this.url;
        }
        return Util.getImageUrl(this.url.substring(0, lastIndexOf) + getFrameSize(widgetSize) + ((!z || !this.variants.contains("badge")) ? "" : "_badged") + getImageSize(i) + this.url.substring(lastIndexOf));
    }

    public boolean enabled(ZonedDateTime zonedDateTime) {
        LocalDateTime localDateTime = this.startAt;
        boolean z = false;
        if (!(localDateTime == null || this.endAt == null)) {
            ZonedDateTime parseDate = parseDate(localDateTime, this.useLocalTime);
            ZonedDateTime parseDate2 = parseDate(this.endAt, this.useLocalTime);
            if (zonedDateTime.isAfter(parseDate) && zonedDateTime.isBefore(parseDate2)) {
                z = true;
            }
            Log.d("Locket", "Widget Frame " + (z ? "Enabled" : "Not Enabled (Start: " + this.startAt + " End:" + this.endAt + ")"));
        }
        return z;
    }

    public static ZonedDateTime parseDate(LocalDateTime localDateTime, boolean z) {
        if (localDateTime == null) {
            return null;
        }
        if (z) {
            return localDateTime.atZone(ZoneId.systemDefault());
        }
        return localDateTime.atZone(ZoneId.of("UTC"));
    }

    public static LocalDateTime parseDate(String str) {
        if (str != null && !str.isEmpty()) {
            try {
                return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static String getFrameSize(WidgetSize widgetSize) {
        return widgetSize == WidgetSize.LG ? "_systemLarge" : "_systemSmall";
    }
}
