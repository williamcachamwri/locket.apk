package com.mrousavy.camera.frameprocessors;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;

public class FrameProcessorPluginRegistry {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Map<String, PluginInitializer> Plugins = new HashMap();
    private static final String TAG = "FrameProcessorPluginRegistry";

    public interface PluginInitializer {
        FrameProcessorPlugin initializePlugin(VisionCameraProxy visionCameraProxy, Map<String, Object> map);
    }

    public static void addFrameProcessorPlugin(String str, PluginInitializer pluginInitializer) {
        Plugins.put(str, pluginInitializer);
        Log.i(TAG, "Successfully registered Frame Processor Plugin \"" + str + "\"!");
    }

    public static FrameProcessorPlugin getPlugin(String str, VisionCameraProxy visionCameraProxy, Map<String, Object> map) {
        Log.i(TAG, "Looking up Frame Processor Plugin \"" + str + "\"...");
        PluginInitializer pluginInitializer = Plugins.get(str);
        if (pluginInitializer == null) {
            Log.i(TAG, "Frame Processor Plugin \"" + str + "\" does not exist!");
            return null;
        }
        Log.i(TAG, "Frame Processor Plugin \"" + str + "\" found! Initializing...");
        return pluginInitializer.initializePlugin(visionCameraProxy, map);
    }
}
