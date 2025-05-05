package androidx.media3.effect;

import android.util.JsonWriter;
import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.SystemClock;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import me.leolin.shortcutbadger.impl.NewHtcHomeBadger;

public final class DebugTraceUtil {
    private static final ImmutableMap<String, List<String>> COMPONENTS_TO_EVENTS = ImmutableMap.builder().put(COMPONENT_TRANSFORMER_INTERNAL, ImmutableList.of(EVENT_START)).put(COMPONENT_ASSET_LOADER, ImmutableList.of(EVENT_INPUT_FORMAT, EVENT_OUTPUT_FORMAT)).put(COMPONENT_AUDIO_DECODER, ImmutableList.of(EVENT_INPUT_FORMAT, EVENT_OUTPUT_FORMAT, EVENT_ACCEPTED_INPUT, EVENT_PRODUCED_OUTPUT, EVENT_INPUT_ENDED, EVENT_OUTPUT_ENDED)).put(COMPONENT_AUDIO_GRAPH, ImmutableList.of(EVENT_REGISTER_NEW_INPUT_STREAM, EVENT_OUTPUT_ENDED)).put(COMPONENT_AUDIO_MIXER, ImmutableList.of(EVENT_REGISTER_NEW_INPUT_STREAM, EVENT_OUTPUT_FORMAT, EVENT_PRODUCED_OUTPUT)).put(COMPONENT_AUDIO_ENCODER, ImmutableList.of(EVENT_INPUT_FORMAT, EVENT_OUTPUT_FORMAT, EVENT_ACCEPTED_INPUT, EVENT_PRODUCED_OUTPUT, EVENT_INPUT_ENDED, EVENT_OUTPUT_ENDED)).put(COMPONENT_VIDEO_DECODER, ImmutableList.of(EVENT_INPUT_FORMAT, EVENT_OUTPUT_FORMAT, EVENT_ACCEPTED_INPUT, EVENT_PRODUCED_OUTPUT, EVENT_INPUT_ENDED, EVENT_OUTPUT_ENDED)).put(COMPONENT_VFP, ImmutableList.of(EVENT_REGISTER_NEW_INPUT_STREAM, EVENT_SURFACE_TEXTURE_INPUT, EVENT_QUEUE_FRAME, EVENT_QUEUE_BITMAP, EVENT_QUEUE_TEXTURE, EVENT_RENDERED_TO_OUTPUT_SURFACE, EVENT_OUTPUT_TEXTURE_RENDERED, EVENT_RECEIVE_END_OF_ALL_INPUT, EVENT_SIGNAL_ENDED)).put(COMPONENT_EXTERNAL_TEXTURE_MANAGER, ImmutableList.of(EVENT_SIGNAL_EOS, EVENT_SURFACE_TEXTURE_TRANSFORM_FIX)).put(COMPONENT_BITMAP_TEXTURE_MANAGER, ImmutableList.of(EVENT_SIGNAL_EOS)).put(COMPONENT_TEX_ID_TEXTURE_MANAGER, ImmutableList.of(EVENT_SIGNAL_EOS)).put(COMPONENT_COMPOSITOR, ImmutableList.of(EVENT_OUTPUT_TEXTURE_RENDERED)).put(COMPONENT_VIDEO_ENCODER, ImmutableList.of(EVENT_INPUT_FORMAT, EVENT_OUTPUT_FORMAT, EVENT_ACCEPTED_INPUT, EVENT_PRODUCED_OUTPUT, EVENT_INPUT_ENDED, EVENT_OUTPUT_ENDED)).put(COMPONENT_MUXER, ImmutableList.of(EVENT_INPUT_FORMAT, EVENT_CAN_WRITE_SAMPLE, EVENT_ACCEPTED_INPUT, EVENT_INPUT_ENDED, EVENT_OUTPUT_ENDED)).buildOrThrow();
    public static final String COMPONENT_ASSET_LOADER = "AssetLoader";
    public static final String COMPONENT_AUDIO_DECODER = "AudioDecoder";
    public static final String COMPONENT_AUDIO_ENCODER = "AudioEncoder";
    public static final String COMPONENT_AUDIO_GRAPH = "AudioGraph";
    public static final String COMPONENT_AUDIO_MIXER = "AudioMixer";
    public static final String COMPONENT_BITMAP_TEXTURE_MANAGER = "BitmapTextureManager";
    public static final String COMPONENT_COMPOSITOR = "Compositor";
    public static final String COMPONENT_EXTERNAL_TEXTURE_MANAGER = "ExternalTextureManager";
    public static final String COMPONENT_MUXER = "Muxer";
    public static final String COMPONENT_TEX_ID_TEXTURE_MANAGER = "TexIdTextureManager";
    public static final String COMPONENT_TRANSFORMER_INTERNAL = "TransformerInternal";
    public static final String COMPONENT_VFP = "VFP";
    public static final String COMPONENT_VIDEO_DECODER = "VideoDecoder";
    public static final String COMPONENT_VIDEO_ENCODER = "VideoEncoder";
    private static final boolean ENABLE_TRACES_IN_LOGCAT = false;
    public static final String EVENT_ACCEPTED_INPUT = "AcceptedInput";
    public static final String EVENT_CAN_WRITE_SAMPLE = "CanWriteSample";
    public static final String EVENT_INPUT_ENDED = "InputEnded";
    public static final String EVENT_INPUT_FORMAT = "InputFormat";
    public static final String EVENT_OUTPUT_ENDED = "OutputEnded";
    public static final String EVENT_OUTPUT_FORMAT = "OutputFormat";
    public static final String EVENT_OUTPUT_TEXTURE_RENDERED = "OutputTextureRendered";
    public static final String EVENT_PRODUCED_OUTPUT = "ProducedOutput";
    public static final String EVENT_QUEUE_BITMAP = "QueueBitmap";
    public static final String EVENT_QUEUE_FRAME = "QueueFrame";
    public static final String EVENT_QUEUE_TEXTURE = "QueueTexture";
    public static final String EVENT_RECEIVE_END_OF_ALL_INPUT = "ReceiveEndOfAllInput";
    public static final String EVENT_RECEIVE_EOS = "ReceiveEOS";
    public static final String EVENT_REGISTER_NEW_INPUT_STREAM = "RegisterNewInputStream";
    public static final String EVENT_RENDERED_TO_OUTPUT_SURFACE = "RenderedToOutputSurface";
    public static final String EVENT_SIGNAL_ENDED = "SignalEnded";
    public static final String EVENT_SIGNAL_EOS = "SignalEOS";
    public static final String EVENT_START = "Start";
    public static final String EVENT_SURFACE_TEXTURE_INPUT = "SurfaceTextureInput";
    public static final String EVENT_SURFACE_TEXTURE_TRANSFORM_FIX = "SurfaceTextureTransformFix";
    private static final int MAX_FIRST_LAST_LOGS = 10;
    private static final Map<String, Map<String, EventLogger>> componentsToEventsToLogs = new LinkedHashMap();
    public static boolean enableTracing = false;
    private static long startTimeMs = SystemClock.DEFAULT.elapsedRealtime();

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Component {
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Event {
    }

    private static String getCodecComponent(boolean z, boolean z2) {
        return z ? z2 ? COMPONENT_VIDEO_DECODER : COMPONENT_AUDIO_DECODER : z2 ? COMPONENT_VIDEO_ENCODER : COMPONENT_AUDIO_ENCODER;
    }

    public static synchronized void reset() {
        synchronized (DebugTraceUtil.class) {
            componentsToEventsToLogs.clear();
            startTimeMs = SystemClock.DEFAULT.elapsedRealtime();
        }
    }

    public static synchronized void logEvent(String str, String str2, long j, String str3, Object... objArr) {
        String str4 = str;
        String str5 = str2;
        synchronized (DebugTraceUtil.class) {
            if (enableTracing) {
                long elapsedRealtime = SystemClock.DEFAULT.elapsedRealtime() - startTimeMs;
                Map<String, Map<String, EventLogger>> map = componentsToEventsToLogs;
                if (!map.containsKey(str)) {
                    map.put(str, new LinkedHashMap());
                }
                Map map2 = map.get(str);
                if (!map2.containsKey(str2)) {
                    map2.put(str2, new EventLogger());
                }
                ((EventLogger) map2.get(str2)).addLog(new EventLog(j, elapsedRealtime, Util.formatInvariant(str3, objArr)));
            }
        }
    }

    public static synchronized void logEvent(String str, String str2, long j) {
        synchronized (DebugTraceUtil.class) {
            logEvent(str, str2, j, "", new Object[0]);
        }
    }

    public static synchronized void logCodecEvent(boolean z, boolean z2, String str, long j, String str2, Object... objArr) {
        synchronized (DebugTraceUtil.class) {
            logEvent(getCodecComponent(z, z2), str, j, str2, objArr);
        }
    }

    public static synchronized String generateTraceSummary() {
        synchronized (DebugTraceUtil.class) {
            if (!enableTracing) {
                return "\"Tracing disabled\"";
            }
            StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            try {
                jsonWriter.beginObject();
                UnmodifiableIterator<Map.Entry<String, List<String>>> it = COMPONENTS_TO_EVENTS.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry next = it.next();
                    String str = (String) next.getKey();
                    jsonWriter.name(str);
                    Map map = componentsToEventsToLogs.get(str);
                    jsonWriter.beginObject();
                    for (String str2 : (List) next.getValue()) {
                        jsonWriter.name(str2);
                        if (map == null || !map.containsKey(str2)) {
                            jsonWriter.value("No events");
                        } else {
                            ((EventLogger) Assertions.checkNotNull((EventLogger) map.get(str2))).toJson(jsonWriter);
                        }
                    }
                    jsonWriter.endObject();
                }
                jsonWriter.endObject();
                String stringWriter2 = stringWriter.toString();
                Util.closeQuietly(jsonWriter);
                return stringWriter2;
            } catch (IOException unused) {
                Util.closeQuietly(jsonWriter);
                return "\"Error generating trace summary\"";
            }
        }
    }

    public static synchronized void dumpTsv(Writer writer) throws IOException {
        synchronized (DebugTraceUtil.class) {
            if (!enableTracing) {
                writer.write("Tracing disabled");
                return;
            }
            writer.write("component\tevent\ttimestamp\tpresentation\textra\n");
            for (Map.Entry next : componentsToEventsToLogs.entrySet()) {
                String str = (String) next.getKey();
                for (Map.Entry entry : ((Map) next.getValue()).entrySet()) {
                    String str2 = (String) entry.getKey();
                    UnmodifiableIterator<EventLog> it = ((EventLogger) entry.getValue()).getLogs().iterator();
                    while (it.hasNext()) {
                        EventLog next2 = it.next();
                        writer.write(Util.formatInvariant("%s\t%s\t%dms\t%s\t%s\n", str, str2, Long.valueOf(next2.eventTimeMs), presentationTimeToString(next2.presentationTimeUs), next2.extra));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static String presentationTimeToString(long j) {
        if (j == C.TIME_UNSET) {
            return "UNSET";
        }
        return j == Long.MIN_VALUE ? "EOS" : j + "us";
    }

    private static final class EventLog {
        public final long eventTimeMs;
        public final String extra;
        public final long presentationTimeUs;

        private EventLog(long j, long j2, String str) {
            this.presentationTimeUs = j;
            this.eventTimeMs = j2;
            this.extra = str;
        }

        public String toString() {
            return Util.formatInvariant("%s@%dms", DebugTraceUtil.presentationTimeToString(this.presentationTimeUs), Long.valueOf(this.eventTimeMs)) + (this.extra.isEmpty() ? "" : Util.formatInvariant("(%s)", this.extra));
        }
    }

    private static final class EventLogger {
        private final List<EventLog> firstLogs = new ArrayList(10);
        private final Queue<EventLog> lastLogs = new ArrayDeque(10);
        private int totalCount = 0;

        public void addLog(EventLog eventLog) {
            if (this.firstLogs.size() < 10) {
                this.firstLogs.add(eventLog);
            } else {
                this.lastLogs.add(eventLog);
                if (this.lastLogs.size() > 10) {
                    this.lastLogs.remove();
                }
            }
            this.totalCount++;
        }

        public ImmutableList<EventLog> getLogs() {
            return new ImmutableList.Builder().addAll((Iterable) this.firstLogs).addAll((Iterable) this.lastLogs).build();
        }

        public void toJson(JsonWriter jsonWriter) throws IOException {
            jsonWriter.beginObject().name(NewHtcHomeBadger.COUNT).value((long) this.totalCount).name("first").beginArray();
            for (EventLog eventLog : this.firstLogs) {
                jsonWriter.value(eventLog.toString());
            }
            jsonWriter.endArray();
            if (!this.lastLogs.isEmpty()) {
                jsonWriter.name("last").beginArray();
                for (EventLog eventLog2 : this.lastLogs) {
                    jsonWriter.value(eventLog2.toString());
                }
                jsonWriter.endArray();
            }
            jsonWriter.endObject();
        }
    }
}
