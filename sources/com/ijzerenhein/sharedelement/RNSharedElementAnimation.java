package com.ijzerenhein.sharedelement;

/* compiled from: RNSharedElementTypes */
enum RNSharedElementAnimation {
    MOVE(0),
    FADE(1),
    FADE_IN(2),
    FADE_OUT(3);
    
    private final int value;

    private RNSharedElementAnimation(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
