package androidx.media3.exoplayer.video;

import android.os.Handler;
import android.os.SystemClock;
import androidx.media3.common.Format;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;

public interface VideoRendererEventListener {
    void onDroppedFrames(int i, long j) {
    }

    void onRenderedFirstFrame(Object obj, long j) {
    }

    void onVideoCodecError(Exception exc) {
    }

    void onVideoDecoderInitialized(String str, long j, long j2) {
    }

    void onVideoDecoderReleased(String str) {
    }

    void onVideoDisabled(DecoderCounters decoderCounters) {
    }

    void onVideoEnabled(DecoderCounters decoderCounters) {
    }

    void onVideoFrameProcessingOffset(long j, int i) {
    }

    void onVideoInputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
    }

    void onVideoSizeChanged(VideoSize videoSize) {
    }

    public static final class EventDispatcher {
        private final Handler handler;
        private final VideoRendererEventListener listener;

        public EventDispatcher(Handler handler2, VideoRendererEventListener videoRendererEventListener) {
            this.handler = videoRendererEventListener != null ? (Handler) Assertions.checkNotNull(handler2) : null;
            this.listener = videoRendererEventListener;
        }

        public void enabled(DecoderCounters decoderCounters) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda4(this, decoderCounters));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$enabled$0$androidx-media3-exoplayer-video-VideoRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m896lambda$enabled$0$androidxmedia3exoplayervideoVideoRendererEventListener$EventDispatcher(DecoderCounters decoderCounters) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoEnabled(decoderCounters);
        }

        public void decoderInitialized(String str, long j, long j2) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda6(this, str, j, j2));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$decoderInitialized$1$androidx-media3-exoplayer-video-VideoRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m892lambda$decoderInitialized$1$androidxmedia3exoplayervideoVideoRendererEventListener$EventDispatcher(String str, long j, long j2) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoDecoderInitialized(str, j, j2);
        }

        public void inputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda7(this, format, decoderReuseEvaluation));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$inputFormatChanged$2$androidx-media3-exoplayer-video-VideoRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m897lambda$inputFormatChanged$2$androidxmedia3exoplayervideoVideoRendererEventListener$EventDispatcher(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoInputFormatChanged(format, decoderReuseEvaluation);
        }

        public void droppedFrames(int i, long j) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda0(this, i, j));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$droppedFrames$3$androidx-media3-exoplayer-video-VideoRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m895lambda$droppedFrames$3$androidxmedia3exoplayervideoVideoRendererEventListener$EventDispatcher(int i, long j) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onDroppedFrames(i, j);
        }

        public void reportVideoFrameProcessingOffset(long j, int i) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda3(this, j, i));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$reportVideoFrameProcessingOffset$4$androidx-media3-exoplayer-video-VideoRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m899lambda$reportVideoFrameProcessingOffset$4$androidxmedia3exoplayervideoVideoRendererEventListener$EventDispatcher(long j, int i) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoFrameProcessingOffset(j, i);
        }

        public void videoSizeChanged(VideoSize videoSize) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda8(this, videoSize));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$videoSizeChanged$5$androidx-media3-exoplayer-video-VideoRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m901lambda$videoSizeChanged$5$androidxmedia3exoplayervideoVideoRendererEventListener$EventDispatcher(VideoSize videoSize) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoSizeChanged(videoSize);
        }

        public void renderedFirstFrame(Object obj) {
            if (this.handler != null) {
                this.handler.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda1(this, obj, SystemClock.elapsedRealtime()));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$renderedFirstFrame$6$androidx-media3-exoplayer-video-VideoRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m898lambda$renderedFirstFrame$6$androidxmedia3exoplayervideoVideoRendererEventListener$EventDispatcher(Object obj, long j) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onRenderedFirstFrame(obj, j);
        }

        public void decoderReleased(String str) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda9(this, str));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$decoderReleased$7$androidx-media3-exoplayer-video-VideoRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m893lambda$decoderReleased$7$androidxmedia3exoplayervideoVideoRendererEventListener$EventDispatcher(String str) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoDecoderReleased(str);
        }

        public void disabled(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda5(this, decoderCounters));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$disabled$8$androidx-media3-exoplayer-video-VideoRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m894lambda$disabled$8$androidxmedia3exoplayervideoVideoRendererEventListener$EventDispatcher(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoDisabled(decoderCounters);
        }

        public void videoCodecError(Exception exc) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda2(this, exc));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$videoCodecError$9$androidx-media3-exoplayer-video-VideoRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m900lambda$videoCodecError$9$androidxmedia3exoplayervideoVideoRendererEventListener$EventDispatcher(Exception exc) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoCodecError(exc);
        }
    }
}
