package com.locket.Locket.Overlays;

public class MomentOverlay {
    private String altText;
    private OverlayCaptionData data;
    private String overlayId;
    private OverlayType overlayType;

    public MomentOverlay(String str, OverlayCaptionData overlayCaptionData, String str2, OverlayType overlayType2) {
        this.altText = str;
        this.data = overlayCaptionData;
        this.overlayId = str2;
        this.overlayType = overlayType2;
    }

    public String getAltText() {
        return this.altText;
    }

    public void setAltText(String str) {
        this.altText = str;
    }

    public OverlayCaptionData getData() {
        return this.data;
    }

    public void setData(OverlayCaptionData overlayCaptionData) {
        this.data = overlayCaptionData;
    }

    public String getOverlayId() {
        return this.overlayId;
    }

    public void setOverlayId(String str) {
        this.overlayId = str;
    }

    public OverlayType getOverlayType() {
        return this.overlayType;
    }

    public void setOverlayType(OverlayType overlayType2) {
        this.overlayType = overlayType2;
    }
}
