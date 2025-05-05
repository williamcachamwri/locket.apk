package com.amplitude.api;

import java.util.HashMap;
import java.util.Map;

public enum AmplitudeServerZone {
    US,
    EU;
    
    private static Map<AmplitudeServerZone, String> amplitudeServerZoneDynamicConfigMap;
    private static Map<AmplitudeServerZone, String> amplitudeServerZoneEventLogApiMap;

    static {
        amplitudeServerZoneEventLogApiMap = new HashMap<AmplitudeServerZone, String>() {
            {
                put(AmplitudeServerZone.US, Constants.EVENT_LOG_URL);
                put(AmplitudeServerZone.EU, Constants.EVENT_LOG_EU_URL);
            }
        };
        amplitudeServerZoneDynamicConfigMap = new HashMap<AmplitudeServerZone, String>() {
            {
                put(AmplitudeServerZone.US, Constants.DYNAMIC_CONFIG_URL);
                put(AmplitudeServerZone.EU, Constants.DYNAMIC_CONFIG_EU_URL);
            }
        };
    }

    protected static String getEventLogApiForZone(AmplitudeServerZone amplitudeServerZone) {
        return amplitudeServerZoneEventLogApiMap.containsKey(amplitudeServerZone) ? amplitudeServerZoneEventLogApiMap.get(amplitudeServerZone) : Constants.EVENT_LOG_URL;
    }

    protected static String getDynamicConfigApi(AmplitudeServerZone amplitudeServerZone) {
        return amplitudeServerZoneDynamicConfigMap.containsKey(amplitudeServerZone) ? amplitudeServerZoneDynamicConfigMap.get(amplitudeServerZone) : Constants.DYNAMIC_CONFIG_URL;
    }

    public static AmplitudeServerZone getServerZone(String str) {
        AmplitudeServerZone amplitudeServerZone = US;
        str.hashCode();
        if (str.equals("EU")) {
            return EU;
        }
        boolean equals = str.equals("US");
        return amplitudeServerZone;
    }
}
