package androidx.media3.exoplayer;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.DefaultAudioSink;
import androidx.media3.exoplayer.image.ImageDecoder;
import androidx.media3.exoplayer.image.ImageOutput;
import androidx.media3.exoplayer.image.ImageRenderer;
import androidx.media3.exoplayer.mediacodec.DefaultMediaCodecAdapterFactory;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.metadata.MetadataRenderer;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.text.TextRenderer;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.exoplayer.video.spherical.CameraMotionRenderer;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

public class DefaultRenderersFactory implements RenderersFactory {
    public static final long DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS = 5000;
    public static final int EXTENSION_RENDERER_MODE_OFF = 0;
    public static final int EXTENSION_RENDERER_MODE_ON = 1;
    public static final int EXTENSION_RENDERER_MODE_PREFER = 2;
    public static final int MAX_DROPPED_VIDEO_FRAME_COUNT_TO_NOTIFY = 50;
    private static final String TAG = "DefaultRenderersFactory";
    private long allowedVideoJoiningTimeMs = 5000;
    private final DefaultMediaCodecAdapterFactory codecAdapterFactory;
    private final Context context;
    private boolean enableAudioTrackPlaybackParams;
    private boolean enableDecoderFallback;
    private boolean enableFloatOutput;
    private int extensionRendererMode = 0;
    private MediaCodecSelector mediaCodecSelector = MediaCodecSelector.DEFAULT;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ExtensionRendererMode {
    }

    /* access modifiers changed from: protected */
    public void buildMiscellaneousRenderers(Context context2, Handler handler, int i, ArrayList<Renderer> arrayList) {
    }

    public DefaultRenderersFactory(Context context2) {
        this.context = context2;
        this.codecAdapterFactory = new DefaultMediaCodecAdapterFactory(context2);
    }

    public final DefaultRenderersFactory setExtensionRendererMode(int i) {
        this.extensionRendererMode = i;
        return this;
    }

    public final DefaultRenderersFactory forceEnableMediaCodecAsynchronousQueueing() {
        this.codecAdapterFactory.forceEnableAsynchronous();
        return this;
    }

    public final DefaultRenderersFactory forceDisableMediaCodecAsynchronousQueueing() {
        this.codecAdapterFactory.forceDisableAsynchronous();
        return this;
    }

    public final DefaultRenderersFactory experimentalSetMediaCodecAsyncCryptoFlagEnabled(boolean z) {
        this.codecAdapterFactory.experimentalSetAsyncCryptoFlagEnabled(z);
        return this;
    }

    public final DefaultRenderersFactory setEnableDecoderFallback(boolean z) {
        this.enableDecoderFallback = z;
        return this;
    }

    public final DefaultRenderersFactory setMediaCodecSelector(MediaCodecSelector mediaCodecSelector2) {
        this.mediaCodecSelector = mediaCodecSelector2;
        return this;
    }

    public final DefaultRenderersFactory setEnableAudioFloatOutput(boolean z) {
        this.enableFloatOutput = z;
        return this;
    }

    public final DefaultRenderersFactory setEnableAudioTrackPlaybackParams(boolean z) {
        this.enableAudioTrackPlaybackParams = z;
        return this;
    }

    public final DefaultRenderersFactory setAllowedVideoJoiningTimeMs(long j) {
        this.allowedVideoJoiningTimeMs = j;
        return this;
    }

    public Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput) {
        ArrayList arrayList = new ArrayList();
        buildVideoRenderers(this.context, this.extensionRendererMode, this.mediaCodecSelector, this.enableDecoderFallback, handler, videoRendererEventListener, this.allowedVideoJoiningTimeMs, arrayList);
        AudioSink buildAudioSink = buildAudioSink(this.context, this.enableFloatOutput, this.enableAudioTrackPlaybackParams);
        if (buildAudioSink != null) {
            buildAudioRenderers(this.context, this.extensionRendererMode, this.mediaCodecSelector, this.enableDecoderFallback, buildAudioSink, handler, audioRendererEventListener, arrayList);
        }
        ArrayList arrayList2 = arrayList;
        buildTextRenderers(this.context, textOutput, handler.getLooper(), this.extensionRendererMode, arrayList2);
        buildMetadataRenderers(this.context, metadataOutput, handler.getLooper(), this.extensionRendererMode, arrayList2);
        buildCameraMotionRenderers(this.context, this.extensionRendererMode, arrayList);
        buildImageRenderers(arrayList);
        Handler handler2 = handler;
        buildMiscellaneousRenderers(this.context, handler, this.extensionRendererMode, arrayList);
        return (Renderer[]) arrayList.toArray(new Renderer[0]);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0073, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x007b, code lost:
        throw new java.lang.IllegalStateException("Error instantiating VP9 extension", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00be, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00c6, code lost:
        throw new java.lang.IllegalStateException("Error instantiating AV1 extension", r0);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0073 A[ExcHandler: Exception (r0v8 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:7:0x0034] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00be A[ExcHandler: Exception (r0v7 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:20:0x007f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void buildVideoRenderers(android.content.Context r15, int r16, androidx.media3.exoplayer.mediacodec.MediaCodecSelector r17, boolean r18, android.os.Handler r19, androidx.media3.exoplayer.video.VideoRendererEventListener r20, long r21, java.util.ArrayList<androidx.media3.exoplayer.Renderer> r23) {
        /*
            r14 = this;
            r0 = r16
            r11 = r23
            java.lang.String r12 = "DefaultRenderersFactory"
            androidx.media3.exoplayer.video.MediaCodecVideoRenderer r13 = new androidx.media3.exoplayer.video.MediaCodecVideoRenderer
            androidx.media3.exoplayer.mediacodec.MediaCodecAdapter$Factory r3 = r14.getCodecAdapterFactory()
            r10 = 50
            r1 = r13
            r2 = r15
            r4 = r17
            r5 = r21
            r7 = r18
            r8 = r19
            r9 = r20
            r1.<init>(r2, r3, r4, r5, r7, r8, r9, r10)
            r11.add(r13)
            if (r0 != 0) goto L_0x0023
            return
        L_0x0023:
            int r1 = r23.size()
            r2 = 2
            if (r0 != r2) goto L_0x002c
            int r1 = r1 + -1
        L_0x002c:
            r0 = 50
            r3 = 3
            r4 = 0
            r5 = 4
            r6 = 1
            java.lang.String r7 = "androidx.media3.decoder.vp9.LibvpxVideoRenderer"
            java.lang.Class r7 = java.lang.Class.forName(r7)     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            java.lang.Class[] r8 = new java.lang.Class[r5]     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            r8[r4] = r9     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            java.lang.Class<android.os.Handler> r9 = android.os.Handler.class
            r8[r6] = r9     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            java.lang.Class<androidx.media3.exoplayer.video.VideoRendererEventListener> r9 = androidx.media3.exoplayer.video.VideoRendererEventListener.class
            r8[r2] = r9     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            r8[r3] = r9     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            java.lang.reflect.Constructor r7 = r7.getConstructor(r8)     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            java.lang.Object[] r8 = new java.lang.Object[r5]     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            java.lang.Long r9 = java.lang.Long.valueOf(r21)     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            r8[r4] = r9     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            r8[r6] = r19     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            r8[r2] = r20     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r0)     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            r8[r3] = r9     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            java.lang.Object r7 = r7.newInstance(r8)     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            androidx.media3.exoplayer.Renderer r7 = (androidx.media3.exoplayer.Renderer) r7     // Catch:{ ClassNotFoundException -> 0x007c, Exception -> 0x0073 }
            int r8 = r1 + 1
            r11.add(r1, r7)     // Catch:{ ClassNotFoundException -> 0x0071, Exception -> 0x0073 }
            java.lang.String r1 = "Loaded LibvpxVideoRenderer."
            androidx.media3.common.util.Log.i(r12, r1)     // Catch:{ ClassNotFoundException -> 0x0071, Exception -> 0x0073 }
            goto L_0x007d
        L_0x0071:
            r1 = r8
            goto L_0x007c
        L_0x0073:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Error instantiating VP9 extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x007c:
            r8 = r1
        L_0x007d:
            java.lang.String r1 = "androidx.media3.decoder.av1.Libgav1VideoRenderer"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            java.lang.Class[] r7 = new java.lang.Class[r5]     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            r7[r4] = r9     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            java.lang.Class<android.os.Handler> r9 = android.os.Handler.class
            r7[r6] = r9     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            java.lang.Class<androidx.media3.exoplayer.video.VideoRendererEventListener> r9 = androidx.media3.exoplayer.video.VideoRendererEventListener.class
            r7[r2] = r9     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            r7[r3] = r9     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            java.lang.reflect.Constructor r1 = r1.getConstructor(r7)     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            java.lang.Object[] r7 = new java.lang.Object[r5]     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            java.lang.Long r9 = java.lang.Long.valueOf(r21)     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            r7[r4] = r9     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            r7[r6] = r19     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            r7[r2] = r20     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r0)     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            r7[r3] = r9     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            java.lang.Object r1 = r1.newInstance(r7)     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            androidx.media3.exoplayer.Renderer r1 = (androidx.media3.exoplayer.Renderer) r1     // Catch:{ ClassNotFoundException -> 0x00c7, Exception -> 0x00be }
            int r7 = r8 + 1
            r11.add(r8, r1)     // Catch:{ ClassNotFoundException -> 0x00bc, Exception -> 0x00be }
            java.lang.String r1 = "Loaded Libgav1VideoRenderer."
            androidx.media3.common.util.Log.i(r12, r1)     // Catch:{ ClassNotFoundException -> 0x00bc, Exception -> 0x00be }
            goto L_0x00c8
        L_0x00bc:
            r8 = r7
            goto L_0x00c7
        L_0x00be:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Error instantiating AV1 extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x00c7:
            r7 = r8
        L_0x00c8:
            java.lang.String r1 = "androidx.media3.decoder.ffmpeg.ExperimentalFfmpegVideoRenderer"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            java.lang.Class[] r8 = new java.lang.Class[r5]     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            r8[r4] = r9     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            java.lang.Class<android.os.Handler> r9 = android.os.Handler.class
            r8[r6] = r9     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            java.lang.Class<androidx.media3.exoplayer.video.VideoRendererEventListener> r9 = androidx.media3.exoplayer.video.VideoRendererEventListener.class
            r8[r2] = r9     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            r8[r3] = r9     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            java.lang.reflect.Constructor r1 = r1.getConstructor(r8)     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            java.lang.Long r8 = java.lang.Long.valueOf(r21)     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            r5[r4] = r8     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            r5[r6] = r19     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            r5[r2] = r20     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            r5[r3] = r0     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            java.lang.Object r0 = r1.newInstance(r5)     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            androidx.media3.exoplayer.Renderer r0 = (androidx.media3.exoplayer.Renderer) r0     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            r11.add(r7, r0)     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            java.lang.String r0 = "Loaded FfmpegVideoRenderer."
            androidx.media3.common.util.Log.i(r12, r0)     // Catch:{ ClassNotFoundException -> 0x010e, Exception -> 0x0105 }
            goto L_0x010e
        L_0x0105:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Error instantiating FFmpeg extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x010e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.DefaultRenderersFactory.buildVideoRenderers(android.content.Context, int, androidx.media3.exoplayer.mediacodec.MediaCodecSelector, boolean, android.os.Handler, androidx.media3.exoplayer.video.VideoRendererEventListener, long, java.util.ArrayList):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0062, code lost:
        throw new java.lang.IllegalStateException("Error instantiating MIDI extension", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0094, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x009c, code lost:
        throw new java.lang.IllegalStateException("Error instantiating Opus extension", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00cd, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d5, code lost:
        throw new java.lang.IllegalStateException("Error instantiating FLAC extension", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0106, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x010e, code lost:
        throw new java.lang.IllegalStateException("Error instantiating FFmpeg extension", r0);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x005a A[ExcHandler: Exception (r0v13 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:7:0x0035] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0094 A[ExcHandler: Exception (r0v12 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:20:0x0067] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00cd A[ExcHandler: Exception (r0v11 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:33:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0106 A[ExcHandler: Exception (r0v10 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:46:0x00d9] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void buildAudioRenderers(android.content.Context r16, int r17, androidx.media3.exoplayer.mediacodec.MediaCodecSelector r18, boolean r19, androidx.media3.exoplayer.audio.AudioSink r20, android.os.Handler r21, androidx.media3.exoplayer.audio.AudioRendererEventListener r22, java.util.ArrayList<androidx.media3.exoplayer.Renderer> r23) {
        /*
            r15 = this;
            r0 = r17
            r9 = r20
            r10 = r21
            r11 = r22
            r12 = r23
            java.lang.String r13 = "DefaultRenderersFactory"
            androidx.media3.exoplayer.audio.MediaCodecAudioRenderer r14 = new androidx.media3.exoplayer.audio.MediaCodecAudioRenderer
            androidx.media3.exoplayer.mediacodec.MediaCodecAdapter$Factory r3 = r15.getCodecAdapterFactory()
            r1 = r14
            r2 = r16
            r4 = r18
            r5 = r19
            r6 = r21
            r7 = r22
            r8 = r20
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            r12.add(r14)
            if (r0 != 0) goto L_0x0028
            return
        L_0x0028:
            int r1 = r23.size()
            r2 = 2
            if (r0 != r2) goto L_0x0031
            int r1 = r1 + -1
        L_0x0031:
            r0 = 0
            r3 = 1
            java.lang.String r4 = "androidx.media3.decoder.midi.MidiRenderer"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x0063, Exception -> 0x005a }
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x0063, Exception -> 0x005a }
            java.lang.Class<android.content.Context> r6 = android.content.Context.class
            r5[r0] = r6     // Catch:{ ClassNotFoundException -> 0x0063, Exception -> 0x005a }
            java.lang.reflect.Constructor r4 = r4.getConstructor(r5)     // Catch:{ ClassNotFoundException -> 0x0063, Exception -> 0x005a }
            java.lang.Object[] r5 = new java.lang.Object[]{r16}     // Catch:{ ClassNotFoundException -> 0x0063, Exception -> 0x005a }
            java.lang.Object r4 = r4.newInstance(r5)     // Catch:{ ClassNotFoundException -> 0x0063, Exception -> 0x005a }
            androidx.media3.exoplayer.Renderer r4 = (androidx.media3.exoplayer.Renderer) r4     // Catch:{ ClassNotFoundException -> 0x0063, Exception -> 0x005a }
            int r5 = r1 + 1
            r12.add(r1, r4)     // Catch:{ ClassNotFoundException -> 0x0058, Exception -> 0x005a }
            java.lang.String r1 = "Loaded MidiRenderer."
            androidx.media3.common.util.Log.i(r13, r1)     // Catch:{ ClassNotFoundException -> 0x0058, Exception -> 0x005a }
            goto L_0x0064
        L_0x0058:
            r1 = r5
            goto L_0x0063
        L_0x005a:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Error instantiating MIDI extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x0063:
            r5 = r1
        L_0x0064:
            r1 = 3
            java.lang.String r4 = "androidx.media3.decoder.opus.LibopusAudioRenderer"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x009d, Exception -> 0x0094 }
            java.lang.Class[] r6 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException -> 0x009d, Exception -> 0x0094 }
            java.lang.Class<android.os.Handler> r7 = android.os.Handler.class
            r6[r0] = r7     // Catch:{ ClassNotFoundException -> 0x009d, Exception -> 0x0094 }
            java.lang.Class<androidx.media3.exoplayer.audio.AudioRendererEventListener> r7 = androidx.media3.exoplayer.audio.AudioRendererEventListener.class
            r6[r3] = r7     // Catch:{ ClassNotFoundException -> 0x009d, Exception -> 0x0094 }
            java.lang.Class<androidx.media3.exoplayer.audio.AudioSink> r7 = androidx.media3.exoplayer.audio.AudioSink.class
            r6[r2] = r7     // Catch:{ ClassNotFoundException -> 0x009d, Exception -> 0x0094 }
            java.lang.reflect.Constructor r4 = r4.getConstructor(r6)     // Catch:{ ClassNotFoundException -> 0x009d, Exception -> 0x0094 }
            java.lang.Object[] r6 = new java.lang.Object[]{r10, r11, r9}     // Catch:{ ClassNotFoundException -> 0x009d, Exception -> 0x0094 }
            java.lang.Object r4 = r4.newInstance(r6)     // Catch:{ ClassNotFoundException -> 0x009d, Exception -> 0x0094 }
            androidx.media3.exoplayer.Renderer r4 = (androidx.media3.exoplayer.Renderer) r4     // Catch:{ ClassNotFoundException -> 0x009d, Exception -> 0x0094 }
            int r6 = r5 + 1
            r12.add(r5, r4)     // Catch:{ ClassNotFoundException -> 0x0092, Exception -> 0x0094 }
            java.lang.String r4 = "Loaded LibopusAudioRenderer."
            androidx.media3.common.util.Log.i(r13, r4)     // Catch:{ ClassNotFoundException -> 0x0092, Exception -> 0x0094 }
            goto L_0x009e
        L_0x0092:
            r5 = r6
            goto L_0x009d
        L_0x0094:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Error instantiating Opus extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x009d:
            r6 = r5
        L_0x009e:
            java.lang.String r4 = "androidx.media3.decoder.flac.LibflacAudioRenderer"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x00d6, Exception -> 0x00cd }
            java.lang.Class[] r5 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException -> 0x00d6, Exception -> 0x00cd }
            java.lang.Class<android.os.Handler> r7 = android.os.Handler.class
            r5[r0] = r7     // Catch:{ ClassNotFoundException -> 0x00d6, Exception -> 0x00cd }
            java.lang.Class<androidx.media3.exoplayer.audio.AudioRendererEventListener> r7 = androidx.media3.exoplayer.audio.AudioRendererEventListener.class
            r5[r3] = r7     // Catch:{ ClassNotFoundException -> 0x00d6, Exception -> 0x00cd }
            java.lang.Class<androidx.media3.exoplayer.audio.AudioSink> r7 = androidx.media3.exoplayer.audio.AudioSink.class
            r5[r2] = r7     // Catch:{ ClassNotFoundException -> 0x00d6, Exception -> 0x00cd }
            java.lang.reflect.Constructor r4 = r4.getConstructor(r5)     // Catch:{ ClassNotFoundException -> 0x00d6, Exception -> 0x00cd }
            java.lang.Object[] r5 = new java.lang.Object[]{r10, r11, r9}     // Catch:{ ClassNotFoundException -> 0x00d6, Exception -> 0x00cd }
            java.lang.Object r4 = r4.newInstance(r5)     // Catch:{ ClassNotFoundException -> 0x00d6, Exception -> 0x00cd }
            androidx.media3.exoplayer.Renderer r4 = (androidx.media3.exoplayer.Renderer) r4     // Catch:{ ClassNotFoundException -> 0x00d6, Exception -> 0x00cd }
            int r5 = r6 + 1
            r12.add(r6, r4)     // Catch:{ ClassNotFoundException -> 0x00cb, Exception -> 0x00cd }
            java.lang.String r4 = "Loaded LibflacAudioRenderer."
            androidx.media3.common.util.Log.i(r13, r4)     // Catch:{ ClassNotFoundException -> 0x00cb, Exception -> 0x00cd }
            goto L_0x00d7
        L_0x00cb:
            r6 = r5
            goto L_0x00d6
        L_0x00cd:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Error instantiating FLAC extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x00d6:
            r5 = r6
        L_0x00d7:
            java.lang.String r4 = "androidx.media3.decoder.ffmpeg.FfmpegAudioRenderer"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x010f, Exception -> 0x0106 }
            java.lang.Class[] r6 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException -> 0x010f, Exception -> 0x0106 }
            java.lang.Class<android.os.Handler> r7 = android.os.Handler.class
            r6[r0] = r7     // Catch:{ ClassNotFoundException -> 0x010f, Exception -> 0x0106 }
            java.lang.Class<androidx.media3.exoplayer.audio.AudioRendererEventListener> r7 = androidx.media3.exoplayer.audio.AudioRendererEventListener.class
            r6[r3] = r7     // Catch:{ ClassNotFoundException -> 0x010f, Exception -> 0x0106 }
            java.lang.Class<androidx.media3.exoplayer.audio.AudioSink> r7 = androidx.media3.exoplayer.audio.AudioSink.class
            r6[r2] = r7     // Catch:{ ClassNotFoundException -> 0x010f, Exception -> 0x0106 }
            java.lang.reflect.Constructor r4 = r4.getConstructor(r6)     // Catch:{ ClassNotFoundException -> 0x010f, Exception -> 0x0106 }
            java.lang.Object[] r6 = new java.lang.Object[]{r10, r11, r9}     // Catch:{ ClassNotFoundException -> 0x010f, Exception -> 0x0106 }
            java.lang.Object r4 = r4.newInstance(r6)     // Catch:{ ClassNotFoundException -> 0x010f, Exception -> 0x0106 }
            androidx.media3.exoplayer.Renderer r4 = (androidx.media3.exoplayer.Renderer) r4     // Catch:{ ClassNotFoundException -> 0x010f, Exception -> 0x0106 }
            int r6 = r5 + 1
            r12.add(r5, r4)     // Catch:{ ClassNotFoundException -> 0x0104, Exception -> 0x0106 }
            java.lang.String r4 = "Loaded FfmpegAudioRenderer."
            androidx.media3.common.util.Log.i(r13, r4)     // Catch:{ ClassNotFoundException -> 0x0104, Exception -> 0x0106 }
            goto L_0x0110
        L_0x0104:
            r5 = r6
            goto L_0x010f
        L_0x0106:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Error instantiating FFmpeg extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x010f:
            r6 = r5
        L_0x0110:
            java.lang.String r4 = "androidx.media3.decoder.iamf.LibiamfAudioRenderer"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x014b, Exception -> 0x0142 }
            r5 = 4
            java.lang.Class[] r5 = new java.lang.Class[r5]     // Catch:{ ClassNotFoundException -> 0x014b, Exception -> 0x0142 }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r5[r0] = r7     // Catch:{ ClassNotFoundException -> 0x014b, Exception -> 0x0142 }
            java.lang.Class<android.os.Handler> r0 = android.os.Handler.class
            r5[r3] = r0     // Catch:{ ClassNotFoundException -> 0x014b, Exception -> 0x0142 }
            java.lang.Class<androidx.media3.exoplayer.audio.AudioRendererEventListener> r0 = androidx.media3.exoplayer.audio.AudioRendererEventListener.class
            r5[r2] = r0     // Catch:{ ClassNotFoundException -> 0x014b, Exception -> 0x0142 }
            java.lang.Class<androidx.media3.exoplayer.audio.AudioSink> r0 = androidx.media3.exoplayer.audio.AudioSink.class
            r5[r1] = r0     // Catch:{ ClassNotFoundException -> 0x014b, Exception -> 0x0142 }
            java.lang.reflect.Constructor r0 = r4.getConstructor(r5)     // Catch:{ ClassNotFoundException -> 0x014b, Exception -> 0x0142 }
            r1 = r16
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r10, r11, r9}     // Catch:{ ClassNotFoundException -> 0x014b, Exception -> 0x0142 }
            java.lang.Object r0 = r0.newInstance(r1)     // Catch:{ ClassNotFoundException -> 0x014b, Exception -> 0x0142 }
            androidx.media3.exoplayer.Renderer r0 = (androidx.media3.exoplayer.Renderer) r0     // Catch:{ ClassNotFoundException -> 0x014b, Exception -> 0x0142 }
            r12.add(r6, r0)     // Catch:{ ClassNotFoundException -> 0x014b, Exception -> 0x0142 }
            java.lang.String r0 = "Loaded LibiamfAudioRenderer."
            androidx.media3.common.util.Log.i(r13, r0)     // Catch:{ ClassNotFoundException -> 0x014b, Exception -> 0x0142 }
            goto L_0x014b
        L_0x0142:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Error instantiating IAMF extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x014b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.DefaultRenderersFactory.buildAudioRenderers(android.content.Context, int, androidx.media3.exoplayer.mediacodec.MediaCodecSelector, boolean, androidx.media3.exoplayer.audio.AudioSink, android.os.Handler, androidx.media3.exoplayer.audio.AudioRendererEventListener, java.util.ArrayList):void");
    }

    /* access modifiers changed from: protected */
    public void buildTextRenderers(Context context2, TextOutput textOutput, Looper looper, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new TextRenderer(textOutput, looper));
    }

    /* access modifiers changed from: protected */
    public void buildMetadataRenderers(Context context2, MetadataOutput metadataOutput, Looper looper, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new MetadataRenderer(metadataOutput, looper));
    }

    /* access modifiers changed from: protected */
    public void buildCameraMotionRenderers(Context context2, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new CameraMotionRenderer());
    }

    /* access modifiers changed from: protected */
    public void buildImageRenderers(ArrayList<Renderer> arrayList) {
        arrayList.add(new ImageRenderer(getImageDecoderFactory(), (ImageOutput) null));
    }

    /* access modifiers changed from: protected */
    public AudioSink buildAudioSink(Context context2, boolean z, boolean z2) {
        return new DefaultAudioSink.Builder(context2).setEnableFloatOutput(z).setEnableAudioTrackPlaybackParams(z2).build();
    }

    /* access modifiers changed from: protected */
    public MediaCodecAdapter.Factory getCodecAdapterFactory() {
        return this.codecAdapterFactory;
    }

    /* access modifiers changed from: protected */
    public ImageDecoder.Factory getImageDecoderFactory() {
        return ImageDecoder.Factory.DEFAULT;
    }
}
