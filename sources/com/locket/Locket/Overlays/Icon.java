package com.locket.Locket.Overlays;

public class Icon {
    private String color;
    private String data;
    private IconSource source;
    private IconType type;

    public Icon(String str, String str2, IconSource iconSource, IconType iconType) {
        this.color = str;
        this.data = str2;
        this.source = iconSource;
        this.type = iconType;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String str) {
        this.color = str;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public IconSource getSource() {
        return this.source;
    }

    public void setSource(IconSource iconSource) {
        this.source = iconSource;
    }

    public IconType getType() {
        return this.type;
    }

    public void setType(IconType iconType) {
        this.type = iconType;
    }
}
