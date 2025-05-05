package androidx.media3.exoplayer.source;

import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.LoadingInfo;
import com.google.common.collect.ImmutableList;
import java.util.Collections;
import java.util.List;

public final class CompositeSequenceableLoader implements SequenceableLoader {
    private long lastAudioVideoBufferedPositionUs;
    private final ImmutableList<SequenceableLoaderWithTrackTypes> loadersWithTrackTypes;

    @Deprecated
    public CompositeSequenceableLoader(SequenceableLoader[] sequenceableLoaderArr) {
        this(ImmutableList.copyOf((E[]) sequenceableLoaderArr), Collections.nCopies(sequenceableLoaderArr.length, ImmutableList.of(-1)));
    }

    public CompositeSequenceableLoader(List<? extends SequenceableLoader> list, List<List<Integer>> list2) {
        ImmutableList.Builder builder = ImmutableList.builder();
        Assertions.checkArgument(list.size() == list2.size());
        for (int i = 0; i < list.size(); i++) {
            builder.add((Object) new SequenceableLoaderWithTrackTypes((SequenceableLoader) list.get(i), list2.get(i)));
        }
        this.loadersWithTrackTypes = builder.build();
        this.lastAudioVideoBufferedPositionUs = C.TIME_UNSET;
    }

    public long getBufferedPositionUs() {
        long j = Long.MAX_VALUE;
        long j2 = Long.MAX_VALUE;
        for (int i = 0; i < this.loadersWithTrackTypes.size(); i++) {
            SequenceableLoaderWithTrackTypes sequenceableLoaderWithTrackTypes = (SequenceableLoaderWithTrackTypes) this.loadersWithTrackTypes.get(i);
            long bufferedPositionUs = sequenceableLoaderWithTrackTypes.getBufferedPositionUs();
            if ((sequenceableLoaderWithTrackTypes.getTrackTypes().contains(1) || sequenceableLoaderWithTrackTypes.getTrackTypes().contains(2) || sequenceableLoaderWithTrackTypes.getTrackTypes().contains(4)) && bufferedPositionUs != Long.MIN_VALUE) {
                j = Math.min(j, bufferedPositionUs);
            }
            if (bufferedPositionUs != Long.MIN_VALUE) {
                j2 = Math.min(j2, bufferedPositionUs);
            }
        }
        if (j != Long.MAX_VALUE) {
            this.lastAudioVideoBufferedPositionUs = j;
            return j;
        } else if (j2 == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        } else {
            long j3 = this.lastAudioVideoBufferedPositionUs;
            return j3 != C.TIME_UNSET ? j3 : j2;
        }
    }

    public long getNextLoadPositionUs() {
        long j = Long.MAX_VALUE;
        for (int i = 0; i < this.loadersWithTrackTypes.size(); i++) {
            long nextLoadPositionUs = ((SequenceableLoaderWithTrackTypes) this.loadersWithTrackTypes.get(i)).getNextLoadPositionUs();
            if (nextLoadPositionUs != Long.MIN_VALUE) {
                j = Math.min(j, nextLoadPositionUs);
            }
        }
        if (j == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    public void reevaluateBuffer(long j) {
        for (int i = 0; i < this.loadersWithTrackTypes.size(); i++) {
            ((SequenceableLoaderWithTrackTypes) this.loadersWithTrackTypes.get(i)).reevaluateBuffer(j);
        }
    }

    public boolean continueLoading(LoadingInfo loadingInfo) {
        boolean z;
        boolean z2 = false;
        do {
            long nextLoadPositionUs = getNextLoadPositionUs();
            if (nextLoadPositionUs == Long.MIN_VALUE) {
                break;
            }
            z = false;
            for (int i = 0; i < this.loadersWithTrackTypes.size(); i++) {
                long nextLoadPositionUs2 = ((SequenceableLoaderWithTrackTypes) this.loadersWithTrackTypes.get(i)).getNextLoadPositionUs();
                boolean z3 = nextLoadPositionUs2 != Long.MIN_VALUE && nextLoadPositionUs2 <= loadingInfo.playbackPositionUs;
                if (nextLoadPositionUs2 == nextLoadPositionUs || z3) {
                    z |= ((SequenceableLoaderWithTrackTypes) this.loadersWithTrackTypes.get(i)).continueLoading(loadingInfo);
                }
            }
            z2 |= z;
        } while (z);
        return z2;
    }

    public boolean isLoading() {
        for (int i = 0; i < this.loadersWithTrackTypes.size(); i++) {
            if (((SequenceableLoaderWithTrackTypes) this.loadersWithTrackTypes.get(i)).isLoading()) {
                return true;
            }
        }
        return false;
    }

    private static final class SequenceableLoaderWithTrackTypes implements SequenceableLoader {
        private final SequenceableLoader loader;
        private final ImmutableList<Integer> trackTypes;

        public SequenceableLoaderWithTrackTypes(SequenceableLoader sequenceableLoader, List<Integer> list) {
            this.loader = sequenceableLoader;
            this.trackTypes = ImmutableList.copyOf(list);
        }

        public ImmutableList<Integer> getTrackTypes() {
            return this.trackTypes;
        }

        public long getBufferedPositionUs() {
            return this.loader.getBufferedPositionUs();
        }

        public long getNextLoadPositionUs() {
            return this.loader.getNextLoadPositionUs();
        }

        public boolean continueLoading(LoadingInfo loadingInfo) {
            return this.loader.continueLoading(loadingInfo);
        }

        public boolean isLoading() {
            return this.loader.isLoading();
        }

        public void reevaluateBuffer(long j) {
            this.loader.reevaluateBuffer(j);
        }
    }
}
