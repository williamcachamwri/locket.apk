package com.locket.Locket.Overlays;

import java.util.List;

public class Background {
    private List<String> colors;
    private String materialBlur;

    public Background(List<String> list, String str) {
        this.colors = list;
        this.materialBlur = str;
    }

    public List<String> getColors() {
        return this.colors;
    }

    public void setColors(List<String> list) {
        this.colors = list;
    }

    public String getMaterialBlur() {
        return this.materialBlur;
    }

    public void setMaterialBlur(String str) {
        this.materialBlur = str;
    }
}
