package com.iab.omid.library.adsbynimbus.adsession.media;

public enum InteractionType {
    CLICK("click"),
    INVITATION_ACCEPTED("invitationAccept");
    
    String interactionType;

    private InteractionType(String str) {
        this.interactionType = str;
    }

    public String toString() {
        return this.interactionType;
    }
}
