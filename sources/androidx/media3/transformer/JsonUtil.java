package androidx.media3.transformer;

import android.os.Build;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Log;
import androidx.media3.transformer.ExportResult;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import io.sentry.SentryEvent;
import io.sentry.protocol.Device;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class JsonUtil {
    private JsonUtil() {
    }

    public static JSONObject getDeviceDetailsAsJsonObject() throws JSONException {
        return new JSONObject().put(Device.JsonKeys.MANUFACTURER, Build.MANUFACTURER).put(Device.JsonKeys.MODEL, Build.MODEL).put(RemoteConfigConstants.RequestFieldKey.SDK_VERSION, Build.VERSION.SDK_INT).put(SentryEvent.JsonKeys.FINGERPRINT, Build.FINGERPRINT);
    }

    public static JSONArray processedInputsAsJsonArray(ImmutableList<ExportResult.ProcessedInput> immutableList) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        UnmodifiableIterator<ExportResult.ProcessedInput> it = immutableList.iterator();
        while (it.hasNext()) {
            ExportResult.ProcessedInput next = it.next();
            JSONObject jSONObject = new JSONObject();
            MediaItem.LocalConfiguration localConfiguration = next.mediaItem.localConfiguration;
            if (localConfiguration != null) {
                jSONObject.put("mediaItemUri", localConfiguration.uri);
            }
            jSONObject.putOpt("audioDecoderName", next.audioDecoderName);
            jSONObject.putOpt("videoDecoderName", next.videoDecoderName);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public static JSONObject exceptionAsJsonObject(Exception exc) throws JSONException {
        if (exc == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("message", exc.getMessage());
        jSONObject.put("type", exc.getClass());
        if (exc instanceof ExportException) {
            jSONObject.put("errorCode", ((ExportException) exc).errorCode);
        }
        jSONObject.put("stackTrace", Log.getThrowableString(exc));
        return jSONObject;
    }

    public static JSONObject exportResultAsJsonObject(ExportResult exportResult) throws JSONException {
        JSONObject putOpt = new JSONObject().putOpt("audioEncoderName", exportResult.audioEncoderName).putOpt("colorInfo", exportResult.colorInfo).putOpt("videoEncoderName", exportResult.videoEncoderName).putOpt("testException", exceptionAsJsonObject(exportResult.exportException));
        if (!exportResult.processedInputs.isEmpty()) {
            putOpt.put("processedInputs", processedInputsAsJsonArray(exportResult.processedInputs));
        }
        if (exportResult.averageAudioBitrate != -2147483647) {
            putOpt.put("averageAudioBitrate", exportResult.averageAudioBitrate);
        }
        if (exportResult.averageVideoBitrate != -2147483647) {
            putOpt.put("averageVideoBitrate", exportResult.averageVideoBitrate);
        }
        if (exportResult.channelCount != -1) {
            putOpt.put("channelCount", exportResult.channelCount);
        }
        if (exportResult.durationMs != C.TIME_UNSET) {
            putOpt.put("durationMs", exportResult.durationMs);
        }
        if (exportResult.fileSizeBytes != -1) {
            putOpt.put("fileSizeBytes", exportResult.fileSizeBytes);
        }
        if (exportResult.height != -1) {
            putOpt.put("height", exportResult.height);
        }
        if (exportResult.sampleRate != -2147483647) {
            putOpt.put("sampleRate", exportResult.sampleRate);
        }
        if (exportResult.videoFrameCount > 0) {
            putOpt.put("videoFrameCount", exportResult.videoFrameCount);
        }
        if (exportResult.width != -1) {
            putOpt.put("width", exportResult.width);
        }
        return putOpt;
    }
}
