package androidx.media3.exoplayer.audio;

import android.os.Handler;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.audio.AudioSink;

public interface AudioRendererEventListener {
    void onAudioCodecError(Exception exc) {
    }

    void onAudioDecoderInitialized(String str, long j, long j2) {
    }

    void onAudioDecoderReleased(String str) {
    }

    void onAudioDisabled(DecoderCounters decoderCounters) {
    }

    void onAudioEnabled(DecoderCounters decoderCounters) {
    }

    void onAudioInputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
    }

    void onAudioPositionAdvancing(long j) {
    }

    void onAudioSinkError(Exception exc) {
    }

    void onAudioTrackInitialized(AudioSink.AudioTrackConfig audioTrackConfig) {
    }

    void onAudioTrackReleased(AudioSink.AudioTrackConfig audioTrackConfig) {
    }

    void onAudioUnderrun(int i, long j, long j2) {
    }

    void onSkipSilenceEnabledChanged(boolean z) {
    }

    public static final class EventDispatcher {
        private final Handler handler;
        private final AudioRendererEventListener listener;

        public EventDispatcher(Handler handler2, AudioRendererEventListener audioRendererEventListener) {
            this.handler = audioRendererEventListener != null ? (Handler) Assertions.checkNotNull(handler2) : null;
            this.listener = audioRendererEventListener;
        }

        public void enabled(DecoderCounters decoderCounters) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda6(this, decoderCounters));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$enabled$0$androidx-media3-exoplayer-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m497lambda$enabled$0$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(DecoderCounters decoderCounters) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioEnabled(decoderCounters);
        }

        public void decoderInitialized(String str, long j, long j2) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda9(this, str, j, j2));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$decoderInitialized$1$androidx-media3-exoplayer-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m494lambda$decoderInitialized$1$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(String str, long j, long j2) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioDecoderInitialized(str, j, j2);
        }

        public void inputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda1(this, format, decoderReuseEvaluation));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$inputFormatChanged$2$androidx-media3-exoplayer-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m498lambda$inputFormatChanged$2$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioInputFormatChanged(format, decoderReuseEvaluation);
        }

        public void positionAdvancing(long j) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda7(this, j));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$positionAdvancing$3$androidx-media3-exoplayer-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m499lambda$positionAdvancing$3$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(long j) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioPositionAdvancing(j);
        }

        public void underrun(int i, long j, long j2) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda2(this, i, j, j2));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$underrun$4$androidx-media3-exoplayer-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m501lambda$underrun$4$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(int i, long j, long j2) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioUnderrun(i, j, j2);
        }

        public void decoderReleased(String str) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda11(this, str));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$decoderReleased$5$androidx-media3-exoplayer-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m495lambda$decoderReleased$5$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(String str) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioDecoderReleased(str);
        }

        public void disabled(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda5(this, decoderCounters));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$disabled$6$androidx-media3-exoplayer-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m496lambda$disabled$6$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioDisabled(decoderCounters);
        }

        public void skipSilenceEnabledChanged(boolean z) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda8(this, z));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$skipSilenceEnabledChanged$7$androidx-media3-exoplayer-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m500lambda$skipSilenceEnabledChanged$7$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(boolean z) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onSkipSilenceEnabledChanged(z);
        }

        public void audioSinkError(Exception exc) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda10(this, exc));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$audioSinkError$8$androidx-media3-exoplayer-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m491lambda$audioSinkError$8$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(Exception exc) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioSinkError(exc);
        }

        public void audioCodecError(Exception exc) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda3(this, exc));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$audioCodecError$9$androidx-media3-exoplayer-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m490lambda$audioCodecError$9$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(Exception exc) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioCodecError(exc);
        }

        public void audioTrackInitialized(AudioSink.AudioTrackConfig audioTrackConfig) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda4(this, audioTrackConfig));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$audioTrackInitialized$10$androidx-media3-exoplayer-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m492lambda$audioTrackInitialized$10$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(AudioSink.AudioTrackConfig audioTrackConfig) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioTrackInitialized(audioTrackConfig);
        }

        public void audioTrackReleased(AudioSink.AudioTrackConfig audioTrackConfig) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda0(this, audioTrackConfig));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$audioTrackReleased$11$androidx-media3-exoplayer-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m493lambda$audioTrackReleased$11$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(AudioSink.AudioTrackConfig audioTrackConfig) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioTrackReleased(audioTrackConfig);
        }
    }
}
