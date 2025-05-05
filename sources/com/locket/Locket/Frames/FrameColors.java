package com.locket.Locket.Frames;

import java.util.HashMap;
import java.util.Map;

public class FrameColors {
    private static final Map<String, String[]> frameColors;

    static {
        HashMap hashMap = new HashMap();
        frameColors = hashMap;
        hashMap.put("none", new String[]{"transparent", "transparent"});
        hashMap.put("pink", new String[]{"#FF879D", "#FF567F"});
        hashMap.put("orange", new String[]{"#FFB84E", "#F97A1F"});
        hashMap.put("yellow", new String[]{"#FFF9C1", "#FFEA2E"});
        hashMap.put("green", new String[]{"#AAFFA8", "#5FC55D"});
        hashMap.put("aqua", new String[]{"#7EFFD8", "#11BEB4"});
        hashMap.put("blue", new String[]{"#3EAEFF", "#3D67FF"});
        hashMap.put("purple", new String[]{"#CA86FF", "#A430FF"});
        hashMap.put("pastelpurple", new String[]{"#E7C8FF", "#D6B6FF"});
        hashMap.put("pastelyellow", new String[]{"#FFFDED", "#FFEFB6"});
        hashMap.put("pastelgreen", new String[]{"#EDFFEE", "#C6FFCB"});
        hashMap.put("pastelblue", new String[]{"#B8EEFF", "#AFCFFF"});
        hashMap.put("pastelpink", new String[]{"#FFDBF5", "#FFAFE4"});
        hashMap.put("bluepurple", new String[]{"#A7EFFF", "#8169DF"});
        hashMap.put("pinkgreen", new String[]{"#D376FF", "#A0BEFF", "#77FFCE", "#77FFCE"});
        hashMap.put("pinkyellow", new String[]{"#FF76B0", "#FFD9A0", "#FEFDC3", "#FEFFC5"});
        hashMap.put("bluegreen", new String[]{"#48B2FF", "#4FFF80", "#FCFF6F"});
        hashMap.put("darkpink", new String[]{"#553C73", "#A02DAA", "#FF629A"});
        hashMap.put("bluepink", new String[]{"#81CAFF", "#FF69CC"});
        hashMap.put("greenpeach", new String[]{"#61ECFF", "#FFE485", "#FF8E8E"});
    }

    public static String[] getColors(String str) {
        return frameColors.getOrDefault(str, new String[]{"transparent", "transparent"});
    }
}
