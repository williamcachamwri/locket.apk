package com.facebook.react.views.traceupdateoverlay;

import android.graphics.RectF;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.traceupdateoverlay.TraceUpdateOverlay;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ReactModule(name = "TraceUpdateOverlay")
public class TraceUpdateOverlayManager extends SimpleViewManager<TraceUpdateOverlay> {
    public static final String REACT_CLASS = "TraceUpdateOverlay";

    public String getName() {
        return REACT_CLASS;
    }

    public void receiveCommand(TraceUpdateOverlay traceUpdateOverlay, String str, ReadableArray readableArray) {
        str.hashCode();
        if (!str.equals("draw")) {
            ReactSoftExceptionLogger.logSoftException(REACT_CLASS, new ReactNoCrashSoftException("Received unexpected command in TraceUpdateOverlayManager"));
        } else if (readableArray != null) {
            String string = readableArray.getString(0);
            if (string != null) {
                try {
                    JSONArray jSONArray = new JSONArray(string);
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        JSONObject jSONObject2 = jSONObject.getJSONObject("rect");
                        float f = (float) jSONObject2.getDouble("left");
                        float f2 = (float) jSONObject2.getDouble(ViewProps.TOP);
                        arrayList.add(new TraceUpdateOverlay.Overlay(jSONObject.getInt("color"), new RectF(f, f2, (float) (((double) f) + jSONObject2.getDouble("width")), (float) (((double) f2) + jSONObject2.getDouble("height")))));
                    }
                    traceUpdateOverlay.setOverlays(arrayList);
                } catch (JSONException e) {
                    FLog.e(REACT_CLASS, "Failed to parse overlays: ", (Throwable) e);
                }
            }
        }
    }

    public TraceUpdateOverlay createViewInstance(ThemedReactContext themedReactContext) {
        return new TraceUpdateOverlay(themedReactContext);
    }
}
