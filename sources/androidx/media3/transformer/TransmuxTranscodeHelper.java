package androidx.media3.transformer;

import android.content.Context;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.transformer.Composition;
import androidx.media3.transformer.EditedMediaItem;
import androidx.media3.transformer.EditedMediaItemSequence;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class TransmuxTranscodeHelper {

    public static final class ResumeMetadata {
        public final ImmutableList<Pair<Integer, Long>> firstMediaItemIndexAndOffsetInfo;
        public final long lastSyncSampleTimestampUs;
        public final Format videoFormat;

        public ResumeMetadata(long j, ImmutableList<Pair<Integer, Long>> immutableList, Format format) {
            this.lastSyncSampleTimestampUs = j;
            this.firstMediaItemIndexAndOffsetInfo = immutableList;
            this.videoFormat = format;
        }
    }

    public static ListenableFuture<Mp4Info> getMp4Info(Context context, String str, long j) {
        SettableFuture create = SettableFuture.create();
        final SettableFuture settableFuture = create;
        final Context context2 = context;
        final String str2 = str;
        final long j2 = j;
        new Thread("TransmuxTranscodeHelper:Mp4Info") {
            public void run() {
                try {
                    settableFuture.set(Mp4Info.create(context2, str2, j2));
                } catch (Exception e) {
                    settableFuture.setException(e);
                }
            }
        }.start();
        return create;
    }

    public static Composition buildUponCompositionForTrimOptimization(Composition composition, long j, long j2, long j3, boolean z, boolean z2) {
        Effects effects;
        EditedMediaItem editedMediaItem = (EditedMediaItem) ((EditedMediaItemSequence) composition.sequences.get(0)).editedMediaItems.get(0);
        MediaItem build = editedMediaItem.mediaItem.buildUpon().setClippingConfiguration(new MediaItem.ClippingConfiguration.Builder().setStartPositionUs(j).setEndPositionUs(j2).setStartsAtKeyFrame(z).build()).build();
        if (z2) {
            effects = new Effects(editedMediaItem.effects.audioProcessors, ImmutableList.of());
        } else {
            effects = editedMediaItem.effects;
        }
        EditedMediaItem build2 = editedMediaItem.buildUpon().setMediaItem(build).setDurationUs(j3).setEffects(effects).build();
        return composition.buildUpon().setSequences(ImmutableList.of(new EditedMediaItemSequence.Builder(build2).build())).build();
    }

    private TransmuxTranscodeHelper() {
    }

    public static Composition createVideoOnlyComposition(String str, long j) {
        return new Composition.Builder(new EditedMediaItemSequence.Builder(new EditedMediaItem.Builder(new MediaItem.Builder().setUri(str).setClippingConfiguration(new MediaItem.ClippingConfiguration.Builder().setEndPositionMs(Util.usToMs(j)).build()).build()).setRemoveAudio(true).build()).build(), new EditedMediaItemSequence[0]).build();
    }

    public static Composition createAudioTranscodeAndVideoTransmuxComposition(Composition composition, String str) {
        Composition buildUponComposition = buildUponComposition((Composition) Assertions.checkNotNull(composition), false, true, (ResumeMetadata) null);
        Composition.Builder buildUpon = buildUponComposition.buildUpon();
        ArrayList arrayList = new ArrayList(buildUponComposition.sequences);
        arrayList.add(new EditedMediaItemSequence.Builder(new EditedMediaItem.Builder(new MediaItem.Builder().setUri(str).build()).build()).build());
        buildUpon.setSequences(arrayList);
        buildUpon.setTransmuxVideo(true);
        return buildUpon.build();
    }

    public static Composition buildUponComposition(Composition composition, boolean z, boolean z2, ResumeMetadata resumeMetadata) {
        long j;
        int i;
        ImmutableList<Pair<Integer, Long>> immutableList;
        Composition.Builder builder;
        ResumeMetadata resumeMetadata2 = resumeMetadata;
        Composition.Builder buildUpon = composition.buildUpon();
        ImmutableList<EditedMediaItemSequence> immutableList2 = composition.sequences;
        ArrayList arrayList = new ArrayList();
        ImmutableList<Pair<Integer, Long>> immutableList3 = resumeMetadata2 != null ? resumeMetadata2.firstMediaItemIndexAndOffsetInfo : null;
        int i2 = 0;
        while (i2 < immutableList2.size()) {
            EditedMediaItemSequence editedMediaItemSequence = (EditedMediaItemSequence) immutableList2.get(i2);
            ImmutableList<EditedMediaItem> immutableList4 = editedMediaItemSequence.editedMediaItems;
            ArrayList arrayList2 = new ArrayList();
            if (immutableList3 != null) {
                i = ((Integer) immutableList3.get(i2).first).intValue();
                j = ((Long) immutableList3.get(i2).second).longValue();
            } else {
                j = 0;
                i = 0;
            }
            int i3 = i;
            while (i3 < immutableList4.size()) {
                EditedMediaItem editedMediaItem = (EditedMediaItem) immutableList4.get(i3);
                EditedMediaItem.Builder buildUpon2 = editedMediaItem.buildUpon();
                if (i3 == i) {
                    immutableList = immutableList3;
                    builder = buildUpon;
                    buildUpon2.setMediaItem(editedMediaItem.mediaItem.buildUpon().setClippingConfiguration(editedMediaItem.mediaItem.clippingConfiguration.buildUpon().setStartPositionMs(editedMediaItem.mediaItem.clippingConfiguration.startPositionMs + Util.usToMs(j)).build()).build());
                } else {
                    immutableList = immutableList3;
                    builder = buildUpon;
                }
                if (z) {
                    buildUpon2.setRemoveAudio(true);
                }
                if (z2) {
                    buildUpon2.setRemoveVideo(true);
                }
                arrayList2.add(buildUpon2.build());
                i3++;
                immutableList3 = immutableList;
                buildUpon = builder;
            }
            arrayList.add(new EditedMediaItemSequence.Builder((List<EditedMediaItem>) arrayList2).setIsLooping(editedMediaItemSequence.isLooping).build());
            i2++;
            immutableList3 = immutableList3;
            buildUpon = buildUpon;
        }
        Composition.Builder builder2 = buildUpon;
        builder2.setSequences(arrayList);
        return builder2.build();
    }

    public static ListenableFuture<ResumeMetadata> getResumeMetadataAsync(Context context, String str, Composition composition) {
        SettableFuture create = SettableFuture.create();
        final SettableFuture settableFuture = create;
        final Context context2 = context;
        final String str2 = str;
        final Composition composition2 = composition;
        new Thread("TransmuxTranscodeHelper:ResumeMetadata") {
            public void run() {
                try {
                    if (!settableFuture.isCancelled()) {
                        Mp4Info create = Mp4Info.create(context2, str2);
                        long j = create.lastSyncSampleTimestampUs;
                        ImmutableList.Builder builder = new ImmutableList.Builder();
                        if (j != C.TIME_UNSET) {
                            for (int i = 0; i < composition2.sequences.size(); i++) {
                                ImmutableList<EditedMediaItem> immutableList = ((EditedMediaItemSequence) composition2.sequences.get(i)).editedMediaItems;
                                long j2 = j;
                                int i2 = 0;
                                while (true) {
                                    if (i2 >= immutableList.size() || j2 <= 0) {
                                        j2 = 0;
                                    } else {
                                        long access$000 = TransmuxTranscodeHelper.getMediaItemDurationUs(context2, ((EditedMediaItem) immutableList.get(i2)).mediaItem);
                                        if (access$000 > j2) {
                                            break;
                                        }
                                        j2 -= access$000;
                                        i2++;
                                    }
                                }
                                builder.add((Object) new Pair(Integer.valueOf(i2), Long.valueOf(j2)));
                            }
                        }
                        settableFuture.set(new ResumeMetadata(j, builder.build(), create.videoFormat));
                    }
                } catch (Exception e) {
                    settableFuture.setException(e);
                }
            }
        }.start();
        return create;
    }

    public static ListenableFuture<Void> copyFileAsync(final File file, final File file2) {
        final SettableFuture create = SettableFuture.create();
        new Thread("TransmuxTranscodeHelper:CopyFile") {
            /* JADX WARNING: Removed duplicated region for block: B:27:0x004f A[SYNTHETIC, Splitter:B:27:0x004f] */
            /* JADX WARNING: Removed duplicated region for block: B:33:0x0059 A[SYNTHETIC, Splitter:B:33:0x0059] */
            /* JADX WARNING: Removed duplicated region for block: B:36:0x005e A[Catch:{ IOException -> 0x0061 }] */
            /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                    com.google.common.util.concurrent.SettableFuture r0 = r0
                    boolean r0 = r0.isCancelled()
                    if (r0 == 0) goto L_0x0009
                    return
                L_0x0009:
                    r0 = 0
                    java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0044, all -> 0x003f }
                    java.io.File r2 = r3     // Catch:{ Exception -> 0x0044, all -> 0x003f }
                    r1.<init>(r2)     // Catch:{ Exception -> 0x0044, all -> 0x003f }
                    java.io.FileInputStream r1 = io.sentry.instrumentation.file.SentryFileInputStream.Factory.create((java.io.FileInputStream) r1, (java.io.File) r2)     // Catch:{ Exception -> 0x0044, all -> 0x003f }
                    java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x003a, all -> 0x0035 }
                    java.io.File r3 = r4     // Catch:{ Exception -> 0x003a, all -> 0x0035 }
                    r2.<init>(r3)     // Catch:{ Exception -> 0x003a, all -> 0x0035 }
                    java.io.FileOutputStream r2 = io.sentry.instrumentation.file.SentryFileOutputStream.Factory.create((java.io.FileOutputStream) r2, (java.io.File) r3)     // Catch:{ Exception -> 0x003a, all -> 0x0035 }
                    com.google.common.io.ByteStreams.copy((java.io.InputStream) r1, (java.io.OutputStream) r2)     // Catch:{ Exception -> 0x0033 }
                    com.google.common.util.concurrent.SettableFuture r3 = r0     // Catch:{ Exception -> 0x0033 }
                    r3.set(r0)     // Catch:{ Exception -> 0x0033 }
                    if (r1 == 0) goto L_0x002d
                    r1.close()     // Catch:{ IOException -> 0x0055 }
                L_0x002d:
                    if (r2 == 0) goto L_0x0055
                L_0x002f:
                    r2.close()     // Catch:{ IOException -> 0x0055 }
                    goto L_0x0055
                L_0x0033:
                    r0 = move-exception
                    goto L_0x0048
                L_0x0035:
                    r2 = move-exception
                    r4 = r2
                    r2 = r0
                    r0 = r4
                    goto L_0x0057
                L_0x003a:
                    r2 = move-exception
                    r4 = r2
                    r2 = r0
                    r0 = r4
                    goto L_0x0048
                L_0x003f:
                    r1 = move-exception
                    r2 = r0
                    r0 = r1
                    r1 = r2
                    goto L_0x0057
                L_0x0044:
                    r1 = move-exception
                    r2 = r0
                    r0 = r1
                    r1 = r2
                L_0x0048:
                    com.google.common.util.concurrent.SettableFuture r3 = r0     // Catch:{ all -> 0x0056 }
                    r3.setException(r0)     // Catch:{ all -> 0x0056 }
                    if (r1 == 0) goto L_0x0052
                    r1.close()     // Catch:{ IOException -> 0x0055 }
                L_0x0052:
                    if (r2 == 0) goto L_0x0055
                    goto L_0x002f
                L_0x0055:
                    return
                L_0x0056:
                    r0 = move-exception
                L_0x0057:
                    if (r1 == 0) goto L_0x005c
                    r1.close()     // Catch:{ IOException -> 0x0061 }
                L_0x005c:
                    if (r2 == 0) goto L_0x0061
                    r2.close()     // Catch:{ IOException -> 0x0061 }
                L_0x0061:
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.TransmuxTranscodeHelper.AnonymousClass3.run():void");
            }
        }.start();
        return create;
    }

    /* access modifiers changed from: private */
    public static long getMediaItemDurationUs(Context context, MediaItem mediaItem) throws IOException {
        long j;
        String uri = ((MediaItem.LocalConfiguration) Assertions.checkNotNull(mediaItem.localConfiguration)).uri.toString();
        long msToUs = Util.msToUs(mediaItem.clippingConfiguration.startPositionMs);
        if (mediaItem.clippingConfiguration.endPositionMs != Long.MIN_VALUE) {
            j = Util.msToUs(mediaItem.clippingConfiguration.endPositionMs);
        } else {
            j = Mp4Info.create(context, uri).durationUs;
        }
        return j - msToUs;
    }
}
