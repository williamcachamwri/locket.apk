package com.ijzerenhein.sharedelement;

/* compiled from: RNSharedElementTypes */
enum RNSharedElementResize {
    AUTO(0),
    STRETCH(1),
    CLIP(2),
    NONE(3);
    
    private final int value;

    private RNSharedElementResize(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
