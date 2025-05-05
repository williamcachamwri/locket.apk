package com.brentvatne.receiver;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/brentvatne/receiver/BecomingNoisyListener;", "", "onAudioBecomingNoisy", "", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BecomingNoisyListener.kt */
public interface BecomingNoisyListener {
    public static final Companion Companion = Companion.$$INSTANCE;

    void onAudioBecomingNoisy();

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/brentvatne/receiver/BecomingNoisyListener$Companion;", "", "()V", "NO_OP", "Lcom/brentvatne/receiver/BecomingNoisyListener;", "getNO_OP", "()Lcom/brentvatne/receiver/BecomingNoisyListener;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BecomingNoisyListener.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final BecomingNoisyListener NO_OP = new BecomingNoisyListener$Companion$NO_OP$1();

        private Companion() {
        }

        public final BecomingNoisyListener getNO_OP() {
            return NO_OP;
        }
    }
}
