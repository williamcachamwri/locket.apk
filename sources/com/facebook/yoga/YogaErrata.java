package com.facebook.yoga;

public enum YogaErrata {
    NONE(0),
    STRETCH_FLEX_BASIS(1),
    ALL(Integer.MAX_VALUE),
    CLASSIC(2147483646);
    
    private final int mIntValue;

    private YogaErrata(int i) {
        this.mIntValue = i;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaErrata fromInt(int i) {
        if (i == 0) {
            return NONE;
        }
        if (i == 1) {
            return STRETCH_FLEX_BASIS;
        }
        switch (i) {
            case 2147483646:
                return CLASSIC;
            case Integer.MAX_VALUE:
                return ALL;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + i);
        }
    }
}
