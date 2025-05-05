package com.google.android.material.color;

import com.google.android.material.R;

public class HarmonizedColorsOptions {
    private final int colorAttributeToHarmonizeWith;
    private final HarmonizedColorAttributes colorAttributes;
    private final int[] colorResourceIds;

    public static HarmonizedColorsOptions createMaterialDefaults() {
        return new Builder().setColorAttributes(HarmonizedColorAttributes.createMaterialDefaults()).build();
    }

    private HarmonizedColorsOptions(Builder builder) {
        this.colorResourceIds = builder.colorResourceIds;
        this.colorAttributes = builder.colorAttributes;
        this.colorAttributeToHarmonizeWith = builder.colorAttributeToHarmonizeWith;
    }

    public int[] getColorResourceIds() {
        return this.colorResourceIds;
    }

    public HarmonizedColorAttributes getColorAttributes() {
        return this.colorAttributes;
    }

    public int getColorAttributeToHarmonizeWith() {
        return this.colorAttributeToHarmonizeWith;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public int colorAttributeToHarmonizeWith = R.attr.colorPrimary;
        /* access modifiers changed from: private */
        public HarmonizedColorAttributes colorAttributes;
        /* access modifiers changed from: private */
        public int[] colorResourceIds = new int[0];

        public Builder setColorResourceIds(int[] iArr) {
            this.colorResourceIds = iArr;
            return this;
        }

        public Builder setColorAttributes(HarmonizedColorAttributes harmonizedColorAttributes) {
            this.colorAttributes = harmonizedColorAttributes;
            return this;
        }

        public Builder setColorAttributeToHarmonizeWith(int i) {
            this.colorAttributeToHarmonizeWith = i;
            return this;
        }

        public HarmonizedColorsOptions build() {
            return new HarmonizedColorsOptions(this);
        }
    }

    /* access modifiers changed from: package-private */
    public int getThemeOverlayResourceId(int i) {
        HarmonizedColorAttributes harmonizedColorAttributes = this.colorAttributes;
        return (harmonizedColorAttributes == null || harmonizedColorAttributes.getThemeOverlay() == 0) ? i : this.colorAttributes.getThemeOverlay();
    }
}
