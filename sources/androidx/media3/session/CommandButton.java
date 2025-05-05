package androidx.media3.session;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.facebook.common.util.UriUtil;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.ImmutableIntArray;
import com.google.errorprone.annotations.CheckReturnValue;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

public final class CommandButton {
    private static final String FIELD_DISPLAY_NAME = Util.intToStringMaxRadix(3);
    private static final String FIELD_ENABLED = Util.intToStringMaxRadix(5);
    private static final String FIELD_EXTRAS = Util.intToStringMaxRadix(4);
    private static final String FIELD_ICON = Util.intToStringMaxRadix(7);
    private static final String FIELD_ICON_RES_ID = Util.intToStringMaxRadix(2);
    private static final String FIELD_ICON_URI = Util.intToStringMaxRadix(6);
    private static final String FIELD_PLAYER_COMMAND = Util.intToStringMaxRadix(1);
    private static final String FIELD_SESSION_COMMAND = Util.intToStringMaxRadix(0);
    private static final String FIELD_SLOTS = Util.intToStringMaxRadix(8);
    public static final int ICON_ALBUM = 57369;
    public static final int ICON_ARTIST = 57370;
    public static final int ICON_BLOCK = 57675;
    public static final int ICON_BOOKMARK_FILLED = 1042534;
    public static final int ICON_BOOKMARK_UNFILLED = 59494;
    public static final int ICON_CHECK_CIRCLE_FILLED = 1042540;
    public static final int ICON_CHECK_CIRCLE_UNFILLED = 59500;
    public static final int ICON_CLOSED_CAPTIONS = 57372;
    public static final int ICON_CLOSED_CAPTIONS_OFF = 61916;
    public static final int ICON_FAST_FORWARD = 57375;
    public static final int ICON_FEED = 57573;
    public static final int ICON_FLAG_FILLED = 1040723;
    public static final int ICON_FLAG_UNFILLED = 57683;
    public static final int ICON_HEART_FILLED = 1042557;
    public static final int ICON_HEART_UNFILLED = 59517;
    public static final int ICON_MINUS = 57691;
    public static final int ICON_MINUS_CIRCLE_FILLED = 1040712;
    public static final int ICON_MINUS_CIRCLE_UNFILLED = 1040713;
    public static final int ICON_NEXT = 57412;
    public static final int ICON_PAUSE = 57396;
    public static final int ICON_PLAY = 57399;
    public static final int ICON_PLAYBACK_SPEED = 57448;
    public static final int ICON_PLAYBACK_SPEED_0_5 = 62690;
    public static final int ICON_PLAYBACK_SPEED_0_8 = 1045730;
    public static final int ICON_PLAYBACK_SPEED_1_0 = 61389;
    public static final int ICON_PLAYBACK_SPEED_1_2 = 62689;
    public static final int ICON_PLAYBACK_SPEED_1_5 = 62688;
    public static final int ICON_PLAYBACK_SPEED_1_8 = 1045728;
    public static final int ICON_PLAYBACK_SPEED_2_0 = 62699;
    public static final int ICON_PLAYLIST_ADD = 57403;
    public static final int ICON_PLAYLIST_REMOVE = 60288;
    public static final int ICON_PLUS = 57669;
    public static final int ICON_PLUS_CIRCLE_FILLED = 1040711;
    public static final int ICON_PLUS_CIRCLE_UNFILLED = 57671;
    public static final int ICON_PREVIOUS = 57413;
    public static final int ICON_QUALITY = 58409;
    public static final int ICON_QUEUE_ADD = 57436;
    public static final int ICON_QUEUE_NEXT = 57446;
    public static final int ICON_QUEUE_REMOVE = 57447;
    public static final int ICON_RADIO = 58654;
    public static final int ICON_REPEAT_ALL = 57408;
    public static final int ICON_REPEAT_OFF = 1040448;
    public static final int ICON_REPEAT_ONE = 57409;
    public static final int ICON_REWIND = 57376;
    public static final int ICON_SETTINGS = 59576;
    public static final int ICON_SHARE = 59405;
    public static final int ICON_SHUFFLE_OFF = 1040452;
    public static final int ICON_SHUFFLE_ON = 57411;
    public static final int ICON_SHUFFLE_STAR = 1040451;
    public static final int ICON_SIGNAL = 61512;
    public static final int ICON_SKIP_BACK = 57410;
    public static final int ICON_SKIP_BACK_10 = 57433;
    public static final int ICON_SKIP_BACK_15 = 1040473;
    public static final int ICON_SKIP_BACK_30 = 57434;
    public static final int ICON_SKIP_BACK_5 = 57435;
    public static final int ICON_SKIP_FORWARD = 63220;
    public static final int ICON_SKIP_FORWARD_10 = 57430;
    public static final int ICON_SKIP_FORWARD_15 = 1040470;
    public static final int ICON_SKIP_FORWARD_30 = 57431;
    public static final int ICON_SKIP_FORWARD_5 = 57432;
    public static final int ICON_STAR_FILLED = 1042488;
    public static final int ICON_STAR_UNFILLED = 59448;
    public static final int ICON_STOP = 57415;
    public static final int ICON_SUBTITLES = 57416;
    public static final int ICON_SUBTITLES_OFF = 61298;
    public static final int ICON_SYNC = 58919;
    public static final int ICON_THUMB_DOWN_FILLED = 1042651;
    public static final int ICON_THUMB_DOWN_UNFILLED = 59611;
    public static final int ICON_THUMB_UP_FILLED = 1042652;
    public static final int ICON_THUMB_UP_UNFILLED = 59612;
    public static final int ICON_UNDEFINED = 0;
    public static final int ICON_VOLUME_DOWN = 57421;
    public static final int ICON_VOLUME_OFF = 57423;
    public static final int ICON_VOLUME_UP = 57424;
    public static final int SLOT_BACK = 2;
    public static final int SLOT_BACK_SECONDARY = 4;
    public static final int SLOT_CENTRAL = 1;
    public static final int SLOT_FORWARD = 3;
    public static final int SLOT_FORWARD_SECONDARY = 5;
    public static final int SLOT_OVERFLOW = 6;
    public final CharSequence displayName;
    public final Bundle extras;
    public final int icon;
    public final int iconResId;
    public final Uri iconUri;
    public final boolean isEnabled;
    public final int playerCommand;
    public final SessionCommand sessionCommand;
    public final ImmutableIntArray slots;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Icon {
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Slot {
    }

    public static int getDefaultSlot(int i, int i2) {
        if (i == 1 || i2 == 57399 || i2 == 57396) {
            return 1;
        }
        if (i == 11 || i == 7 || i == 6 || i2 == 57413 || i2 == 57376 || i2 == 57410 || i2 == 57435 || i2 == 57433 || i2 == 1040473 || i2 == 57434) {
            return 2;
        }
        return (i == 12 || i == 9 || i == 8 || i2 == 57412 || i2 == 57375 || i2 == 63220 || i2 == 57432 || i2 == 57430 || i2 == 1040470 || i2 == 57431) ? 3 : 6;
    }

    public static final class Builder {
        private CharSequence displayName;
        private boolean enabled;
        private Bundle extras;
        private final int icon;
        private int iconResId;
        private Uri iconUri;
        private int playerCommand;
        private SessionCommand sessionCommand;
        private ImmutableIntArray slots;

        public Builder() {
            this(0);
        }

        public Builder(int i) {
            this(i, CommandButton.getIconResIdForIconConstant(i));
        }

        Builder(int i, int i2) {
            this.icon = i;
            this.iconResId = i2;
            this.displayName = "";
            this.extras = Bundle.EMPTY;
            this.playerCommand = -1;
            this.enabled = true;
        }

        public Builder setSessionCommand(SessionCommand sessionCommand2) {
            Assertions.checkNotNull(sessionCommand2, "sessionCommand should not be null.");
            Assertions.checkArgument(this.playerCommand == -1, "playerCommands is already set. Only one of sessionCommand and playerCommand should be set.");
            this.sessionCommand = sessionCommand2;
            return this;
        }

        public Builder setPlayerCommand(int i) {
            Assertions.checkArgument(this.sessionCommand == null, "sessionCommand is already set. Only one of sessionCommand and playerCommand should be set.");
            this.playerCommand = i;
            return this;
        }

        public Builder setIconResId(int i) {
            return setCustomIconResId(i);
        }

        public Builder setCustomIconResId(int i) {
            this.iconResId = i;
            return this;
        }

        public Builder setIconUri(Uri uri) {
            Assertions.checkArgument(Objects.equal(uri.getScheme(), "content") || Objects.equal(uri.getScheme(), UriUtil.QUALIFIED_RESOURCE_SCHEME), "Only content or resource Uris are supported for CommandButton");
            this.iconUri = uri;
            return this;
        }

        public Builder setDisplayName(CharSequence charSequence) {
            this.displayName = charSequence;
            return this;
        }

        public Builder setEnabled(boolean z) {
            this.enabled = z;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.extras = new Bundle(bundle);
            return this;
        }

        public Builder setSlots(int... iArr) {
            Assertions.checkArgument(iArr.length != 0);
            this.slots = ImmutableIntArray.copyOf(iArr);
            return this;
        }

        public CommandButton build() {
            boolean z = true;
            if ((this.sessionCommand == null) == (this.playerCommand == -1)) {
                z = false;
            }
            Assertions.checkState(z, "Exactly one of sessionCommand and playerCommand should be set");
            if (this.slots == null) {
                this.slots = ImmutableIntArray.of(CommandButton.getDefaultSlot(this.playerCommand, this.icon));
            }
            return new CommandButton(this.sessionCommand, this.playerCommand, this.icon, this.iconResId, this.iconUri, this.displayName, this.extras, this.enabled, this.slots);
        }
    }

    private CommandButton(SessionCommand sessionCommand2, int i, int i2, int i3, Uri uri, CharSequence charSequence, Bundle bundle, boolean z, ImmutableIntArray immutableIntArray) {
        this.sessionCommand = sessionCommand2;
        this.playerCommand = i;
        this.icon = i2;
        this.iconResId = i3;
        this.iconUri = uri;
        this.displayName = charSequence;
        this.extras = new Bundle(bundle);
        this.isEnabled = z;
        this.slots = immutableIntArray;
    }

    /* access modifiers changed from: package-private */
    @CheckReturnValue
    public CommandButton copyWithIsEnabled(boolean z) {
        if (this.isEnabled == z) {
            return this;
        }
        return new CommandButton(this.sessionCommand, this.playerCommand, this.icon, this.iconResId, this.iconUri, this.displayName, new Bundle(this.extras), z, this.slots);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommandButton)) {
            return false;
        }
        CommandButton commandButton = (CommandButton) obj;
        if (!Objects.equal(this.sessionCommand, commandButton.sessionCommand) || this.playerCommand != commandButton.playerCommand || this.icon != commandButton.icon || this.iconResId != commandButton.iconResId || !Objects.equal(this.iconUri, commandButton.iconUri) || !TextUtils.equals(this.displayName, commandButton.displayName) || this.isEnabled != commandButton.isEnabled || !this.slots.equals(commandButton.slots)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.sessionCommand, Integer.valueOf(this.playerCommand), Integer.valueOf(this.icon), Integer.valueOf(this.iconResId), this.displayName, Boolean.valueOf(this.isEnabled), this.iconUri, this.slots);
    }

    static ImmutableList<CommandButton> copyWithUnavailableButtonsDisabled(List<CommandButton> list, SessionCommands sessionCommands, Player.Commands commands) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < list.size(); i++) {
            CommandButton commandButton = list.get(i);
            if (isButtonCommandAvailable(commandButton, sessionCommands, commands)) {
                builder.add((Object) commandButton);
            } else {
                builder.add((Object) commandButton.copyWithIsEnabled(false));
            }
        }
        return builder.build();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r1 = r1.playerCommand;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean isButtonCommandAvailable(androidx.media3.session.CommandButton r1, androidx.media3.session.SessionCommands r2, androidx.media3.common.Player.Commands r3) {
        /*
            androidx.media3.session.SessionCommand r0 = r1.sessionCommand
            if (r0 == 0) goto L_0x000a
            boolean r2 = r2.contains((androidx.media3.session.SessionCommand) r0)
            if (r2 != 0) goto L_0x0015
        L_0x000a:
            int r1 = r1.playerCommand
            r2 = -1
            if (r1 == r2) goto L_0x0017
            boolean r1 = r3.contains(r1)
            if (r1 == 0) goto L_0x0017
        L_0x0015:
            r1 = 1
            goto L_0x0018
        L_0x0017:
            r1 = 0
        L_0x0018:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.CommandButton.isButtonCommandAvailable(androidx.media3.session.CommandButton, androidx.media3.session.SessionCommands, androidx.media3.common.Player$Commands):boolean");
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        SessionCommand sessionCommand2 = this.sessionCommand;
        if (sessionCommand2 != null) {
            bundle.putBundle(FIELD_SESSION_COMMAND, sessionCommand2.toBundle());
        }
        int i = this.playerCommand;
        if (i != -1) {
            bundle.putInt(FIELD_PLAYER_COMMAND, i);
        }
        int i2 = this.icon;
        if (i2 != 0) {
            bundle.putInt(FIELD_ICON, i2);
        }
        int i3 = this.iconResId;
        if (i3 != 0) {
            bundle.putInt(FIELD_ICON_RES_ID, i3);
        }
        CharSequence charSequence = this.displayName;
        if (charSequence != "") {
            bundle.putCharSequence(FIELD_DISPLAY_NAME, charSequence);
        }
        if (!this.extras.isEmpty()) {
            bundle.putBundle(FIELD_EXTRAS, this.extras);
        }
        Uri uri = this.iconUri;
        if (uri != null) {
            bundle.putParcelable(FIELD_ICON_URI, uri);
        }
        boolean z = this.isEnabled;
        if (!z) {
            bundle.putBoolean(FIELD_ENABLED, z);
        }
        if (!(this.slots.length() == 1 && this.slots.get(0) == 6)) {
            bundle.putIntArray(FIELD_SLOTS, this.slots.toArray());
        }
        return bundle;
    }

    @Deprecated
    public static CommandButton fromBundle(Bundle bundle) {
        return fromBundle(bundle, 4);
    }

    public static CommandButton fromBundle(Bundle bundle, int i) {
        SessionCommand sessionCommand2;
        Bundle bundle2 = bundle.getBundle(FIELD_SESSION_COMMAND);
        if (bundle2 == null) {
            sessionCommand2 = null;
        } else {
            sessionCommand2 = SessionCommand.fromBundle(bundle2);
        }
        int i2 = bundle.getInt(FIELD_PLAYER_COMMAND, -1);
        int i3 = bundle.getInt(FIELD_ICON_RES_ID, 0);
        CharSequence charSequence = bundle.getCharSequence(FIELD_DISPLAY_NAME, "");
        Bundle bundle3 = bundle.getBundle(FIELD_EXTRAS);
        boolean z = true;
        if (i >= 3 && !bundle.getBoolean(FIELD_ENABLED, true)) {
            z = false;
        }
        Uri uri = (Uri) bundle.getParcelable(FIELD_ICON_URI);
        int i4 = bundle.getInt(FIELD_ICON, 0);
        int[] intArray = bundle.getIntArray(FIELD_SLOTS);
        Builder builder = new Builder(i4, i3);
        if (sessionCommand2 != null) {
            builder.setSessionCommand(sessionCommand2);
        }
        if (i2 != -1) {
            builder.setPlayerCommand(i2);
        }
        if (uri != null && (Objects.equal(uri.getScheme(), "content") || Objects.equal(uri.getScheme(), UriUtil.QUALIFIED_RESOURCE_SCHEME))) {
            builder.setIconUri(uri);
        }
        Builder displayName2 = builder.setDisplayName(charSequence);
        if (bundle3 == null) {
            bundle3 = Bundle.EMPTY;
        }
        Builder enabled = displayName2.setExtras(bundle3).setEnabled(z);
        if (intArray == null) {
            intArray = new int[]{6};
        }
        return enabled.setSlots(intArray).build();
    }

    public static int getIconResIdForIconConstant(int i) {
        switch (i) {
            case ICON_ALBUM /*57369*/:
                return R.drawable.media3_icon_album;
            case ICON_ARTIST /*57370*/:
                return R.drawable.media3_icon_artist;
            case ICON_CLOSED_CAPTIONS /*57372*/:
                return R.drawable.media3_icon_closed_captions;
            case ICON_FAST_FORWARD /*57375*/:
                return R.drawable.media3_icon_fast_forward;
            case ICON_REWIND /*57376*/:
                return R.drawable.media3_icon_rewind;
            case ICON_PAUSE /*57396*/:
                return R.drawable.media3_icon_pause;
            case ICON_PLAY /*57399*/:
                return R.drawable.media3_icon_play;
            case ICON_PLAYLIST_ADD /*57403*/:
                return R.drawable.media3_icon_playlist_add;
            case ICON_REPEAT_ALL /*57408*/:
                return R.drawable.media3_icon_repeat_all;
            case ICON_REPEAT_ONE /*57409*/:
                return R.drawable.media3_icon_repeat_one;
            case ICON_SKIP_BACK /*57410*/:
                return R.drawable.media3_icon_skip_back;
            case ICON_SHUFFLE_ON /*57411*/:
                return R.drawable.media3_icon_shuffle_on;
            case ICON_NEXT /*57412*/:
                return R.drawable.media3_icon_next;
            case ICON_PREVIOUS /*57413*/:
                return R.drawable.media3_icon_previous;
            case ICON_STOP /*57415*/:
                return R.drawable.media3_icon_stop;
            case ICON_SUBTITLES /*57416*/:
                return R.drawable.media3_icon_subtitles;
            case ICON_VOLUME_DOWN /*57421*/:
                return R.drawable.media3_icon_volume_down;
            case ICON_VOLUME_OFF /*57423*/:
                return R.drawable.media3_icon_volume_off;
            case ICON_VOLUME_UP /*57424*/:
                return R.drawable.media3_icon_volume_up;
            case ICON_SKIP_FORWARD_10 /*57430*/:
                return R.drawable.media3_icon_skip_forward_10;
            case ICON_SKIP_FORWARD_30 /*57431*/:
                return R.drawable.media3_icon_skip_forward_30;
            case ICON_SKIP_FORWARD_5 /*57432*/:
                return R.drawable.media3_icon_skip_forward_5;
            case ICON_SKIP_BACK_10 /*57433*/:
                return R.drawable.media3_icon_skip_back_10;
            case ICON_SKIP_BACK_30 /*57434*/:
                return R.drawable.media3_icon_skip_back_30;
            case ICON_SKIP_BACK_5 /*57435*/:
                return R.drawable.media3_icon_skip_back_5;
            case ICON_QUEUE_ADD /*57436*/:
                return R.drawable.media3_icon_queue_add;
            case ICON_QUEUE_NEXT /*57446*/:
                return R.drawable.media3_icon_queue_next;
            case ICON_QUEUE_REMOVE /*57447*/:
                return R.drawable.media3_icon_queue_remove;
            case ICON_PLAYBACK_SPEED /*57448*/:
                return R.drawable.media3_icon_playback_speed;
            case ICON_FEED /*57573*/:
                return R.drawable.media3_icon_feed;
            case ICON_PLUS /*57669*/:
                return R.drawable.media3_icon_plus;
            case ICON_PLUS_CIRCLE_UNFILLED /*57671*/:
                return R.drawable.media3_icon_plus_circle_unfilled;
            case ICON_BLOCK /*57675*/:
                return R.drawable.media3_icon_block;
            case ICON_FLAG_UNFILLED /*57683*/:
                return R.drawable.media3_icon_flag_unfilled;
            case ICON_MINUS /*57691*/:
                return R.drawable.media3_icon_minus;
            case ICON_QUALITY /*58409*/:
                return R.drawable.media3_icon_quality;
            case ICON_RADIO /*58654*/:
                return R.drawable.media3_icon_radio;
            case ICON_SYNC /*58919*/:
                return R.drawable.media3_icon_sync;
            case ICON_SHARE /*59405*/:
                return R.drawable.media3_icon_share;
            case ICON_STAR_UNFILLED /*59448*/:
                return R.drawable.media3_icon_star_unfilled;
            case ICON_BOOKMARK_UNFILLED /*59494*/:
                return R.drawable.media3_icon_bookmark_unfilled;
            case ICON_CHECK_CIRCLE_UNFILLED /*59500*/:
                return R.drawable.media3_icon_check_circle_unfilled;
            case ICON_HEART_UNFILLED /*59517*/:
                return R.drawable.media3_icon_heart_unfilled;
            case ICON_SETTINGS /*59576*/:
                return R.drawable.media3_icon_settings;
            case ICON_THUMB_DOWN_UNFILLED /*59611*/:
                return R.drawable.media3_icon_thumb_down_unfilled;
            case ICON_THUMB_UP_UNFILLED /*59612*/:
                return R.drawable.media3_icon_thumb_up_unfilled;
            case ICON_PLAYLIST_REMOVE /*60288*/:
                return R.drawable.media3_icon_playlist_remove;
            case ICON_SUBTITLES_OFF /*61298*/:
                return R.drawable.media3_icon_subtitles_off;
            case ICON_PLAYBACK_SPEED_1_0 /*61389*/:
                return R.drawable.media3_icon_playback_speed_1_0;
            case ICON_SIGNAL /*61512*/:
                return R.drawable.media3_icon_signal;
            case ICON_CLOSED_CAPTIONS_OFF /*61916*/:
                return R.drawable.media3_icon_closed_captions_off;
            case ICON_PLAYBACK_SPEED_1_5 /*62688*/:
                return R.drawable.media3_icon_playback_speed_1_5;
            case ICON_PLAYBACK_SPEED_1_2 /*62689*/:
                return R.drawable.media3_icon_playback_speed_1_2;
            case ICON_PLAYBACK_SPEED_0_5 /*62690*/:
                return R.drawable.media3_icon_playback_speed_0_5;
            case ICON_PLAYBACK_SPEED_2_0 /*62699*/:
                return R.drawable.media3_icon_playback_speed_2_0;
            case ICON_SKIP_FORWARD /*63220*/:
                return R.drawable.media3_icon_skip_forward;
            case ICON_REPEAT_OFF /*1040448*/:
                return R.drawable.media3_icon_repeat_off;
            case ICON_SHUFFLE_STAR /*1040451*/:
                return R.drawable.media3_icon_shuffle_star;
            case ICON_SHUFFLE_OFF /*1040452*/:
                return R.drawable.media3_icon_shuffle_off;
            case ICON_SKIP_FORWARD_15 /*1040470*/:
                return R.drawable.media3_icon_skip_forward_15;
            case ICON_SKIP_BACK_15 /*1040473*/:
                return R.drawable.media3_icon_skip_back_15;
            case ICON_PLUS_CIRCLE_FILLED /*1040711*/:
                return R.drawable.media3_icon_plus_circle_filled;
            case ICON_MINUS_CIRCLE_FILLED /*1040712*/:
                return R.drawable.media3_icon_minus_circle_filled;
            case ICON_MINUS_CIRCLE_UNFILLED /*1040713*/:
                return R.drawable.media3_icon_minus_circle_unfilled;
            case ICON_FLAG_FILLED /*1040723*/:
                return R.drawable.media3_icon_flag_filled;
            case ICON_STAR_FILLED /*1042488*/:
                return R.drawable.media3_icon_star_filled;
            case ICON_BOOKMARK_FILLED /*1042534*/:
                return R.drawable.media3_icon_bookmark_filled;
            case ICON_CHECK_CIRCLE_FILLED /*1042540*/:
                return R.drawable.media3_icon_check_circle_filled;
            case ICON_HEART_FILLED /*1042557*/:
                return R.drawable.media3_icon_heart_filled;
            case ICON_THUMB_DOWN_FILLED /*1042651*/:
                return R.drawable.media3_icon_thumb_down_filled;
            case ICON_THUMB_UP_FILLED /*1042652*/:
                return R.drawable.media3_icon_thumb_up_filled;
            case ICON_PLAYBACK_SPEED_1_8 /*1045728*/:
                return R.drawable.media3_icon_playback_speed_1_8;
            case ICON_PLAYBACK_SPEED_0_8 /*1045730*/:
                return R.drawable.media3_icon_playback_speed_0_8;
            default:
                return 0;
        }
    }
}
