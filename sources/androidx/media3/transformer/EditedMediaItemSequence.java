package androidx.media3.transformer;

import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.transformer.EditedMediaItem;
import com.google.common.collect.ImmutableList;
import java.util.List;

public final class EditedMediaItemSequence {
    public final ImmutableList<EditedMediaItem> editedMediaItems;
    public final boolean isLooping;

    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean isLooping;
        /* access modifiers changed from: private */
        public final ImmutableList.Builder<EditedMediaItem> items;

        public Builder(EditedMediaItem... editedMediaItemArr) {
            this.items = new ImmutableList.Builder().add((Object[]) editedMediaItemArr);
        }

        public Builder(List<EditedMediaItem> list) {
            this.items = new ImmutableList.Builder().addAll((Iterable) list);
        }

        public Builder addItem(EditedMediaItem editedMediaItem) {
            this.items.add((Object) editedMediaItem);
            return this;
        }

        public Builder addItems(EditedMediaItem... editedMediaItemArr) {
            this.items.add((Object[]) editedMediaItemArr);
            return this;
        }

        public Builder addItems(List<EditedMediaItem> list) {
            this.items.addAll((Iterable) list);
            return this;
        }

        public Builder addGap(long j) {
            this.items.add((Object) new EditedMediaItem.Builder(new MediaItem.Builder().setMediaId("androidx-media3-GapMediaItem").build()).setDurationUs(j).build());
            return this;
        }

        public Builder setIsLooping(boolean z) {
            this.isLooping = z;
            return this;
        }

        public EditedMediaItemSequence build() {
            return new EditedMediaItemSequence(this);
        }
    }

    @Deprecated
    public EditedMediaItemSequence(EditedMediaItem editedMediaItem, EditedMediaItem... editedMediaItemArr) {
        this(new Builder(new EditedMediaItem[0]).addItem(editedMediaItem).addItems(editedMediaItemArr));
    }

    @Deprecated
    public EditedMediaItemSequence(List<EditedMediaItem> list) {
        this(new Builder(new EditedMediaItem[0]).addItems(list));
    }

    @Deprecated
    public EditedMediaItemSequence(List<EditedMediaItem> list, boolean z) {
        this(new Builder(new EditedMediaItem[0]).addItems(list).setIsLooping(z));
    }

    private EditedMediaItemSequence(Builder builder) {
        ImmutableList<EditedMediaItem> build = builder.items.build();
        this.editedMediaItems = build;
        Assertions.checkArgument(!build.isEmpty(), "The sequence must contain at least one EditedMediaItem.");
        this.isLooping = builder.isLooping;
    }

    /* access modifiers changed from: package-private */
    public boolean hasGaps() {
        for (int i = 0; i < this.editedMediaItems.size(); i++) {
            if (((EditedMediaItem) this.editedMediaItems.get(i)).isGap()) {
                return true;
            }
        }
        return false;
    }
}
