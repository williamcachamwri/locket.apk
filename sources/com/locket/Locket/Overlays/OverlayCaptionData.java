package com.locket.Locket.Overlays;

import com.locket.Locket.Overlays.CaptionPayload.CaptionPayload;

public class OverlayCaptionData {
    private Background background;
    private Icon icon;
    private int maxLines;
    private CaptionPayload payload;
    private String text;
    private String textColor;
    private CaptionType type;

    public OverlayCaptionData(Background background2, Icon icon2, int i, CaptionPayload captionPayload, String str, String str2, CaptionType captionType) {
        this.background = background2;
        this.icon = icon2;
        this.maxLines = i;
        this.payload = captionPayload;
        this.text = str;
        this.textColor = str2;
        this.type = captionType;
    }

    public Background getBackground() {
        return this.background;
    }

    public void setBackground(Background background2) {
        this.background = background2;
    }

    public Icon getIcon() {
        return this.icon;
    }

    public void setIcon(Icon icon2) {
        this.icon = icon2;
    }

    public int getMaxLines() {
        return this.maxLines;
    }

    public void setMaxLines(int i) {
        this.maxLines = i;
    }

    public CaptionPayload getPayload() {
        return this.payload;
    }

    public void setPayload(CaptionPayload captionPayload) {
        this.payload = captionPayload;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getTextColor() {
        return this.textColor;
    }

    public void setTextColor(String str) {
        this.textColor = str;
    }

    public CaptionType getType() {
        return this.type;
    }

    public void setType(CaptionType captionType) {
        this.type = captionType;
    }
}
