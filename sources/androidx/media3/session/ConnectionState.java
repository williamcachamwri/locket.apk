package androidx.media3.session;

import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import androidx.core.app.BundleCompat;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import androidx.media3.session.IMediaSession;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;

class ConnectionState {
    private static final String FIELD_COMMAND_BUTTONS_FOR_MEDIA_ITEMS = Util.intToStringMaxRadix(13);
    private static final String FIELD_CUSTOM_LAYOUT = Util.intToStringMaxRadix(9);
    private static final String FIELD_IN_PROCESS_BINDER = Util.intToStringMaxRadix(10);
    private static final String FIELD_LIBRARY_VERSION = Util.intToStringMaxRadix(0);
    private static final String FIELD_MEDIA_BUTTON_PREFERENCES = Util.intToStringMaxRadix(14);
    private static final String FIELD_PLATFORM_TOKEN = Util.intToStringMaxRadix(12);
    private static final String FIELD_PLAYER_COMMANDS_FROM_PLAYER = Util.intToStringMaxRadix(5);
    private static final String FIELD_PLAYER_COMMANDS_FROM_SESSION = Util.intToStringMaxRadix(4);
    private static final String FIELD_PLAYER_INFO = Util.intToStringMaxRadix(7);
    private static final String FIELD_SESSION_ACTIVITY = Util.intToStringMaxRadix(2);
    private static final String FIELD_SESSION_BINDER = Util.intToStringMaxRadix(1);
    private static final String FIELD_SESSION_COMMANDS = Util.intToStringMaxRadix(3);
    private static final String FIELD_SESSION_EXTRAS = Util.intToStringMaxRadix(11);
    private static final String FIELD_SESSION_INTERFACE_VERSION = Util.intToStringMaxRadix(8);
    private static final String FIELD_TOKEN_EXTRAS = Util.intToStringMaxRadix(6);
    public final ImmutableList<CommandButton> commandButtonsForMediaItems;
    public final ImmutableList<CommandButton> customLayout;
    public final int libraryVersion;
    public final ImmutableList<CommandButton> mediaButtonPreferences;
    public final MediaSession.Token platformToken;
    public final Player.Commands playerCommandsFromPlayer;
    public final Player.Commands playerCommandsFromSession;
    public final PlayerInfo playerInfo;
    public final PendingIntent sessionActivity;
    public final IMediaSession sessionBinder;
    public final SessionCommands sessionCommands;
    public final Bundle sessionExtras;
    public final int sessionInterfaceVersion;
    public final Bundle tokenExtras;

    public ConnectionState(int i, int i2, IMediaSession iMediaSession, PendingIntent pendingIntent, ImmutableList<CommandButton> immutableList, ImmutableList<CommandButton> immutableList2, ImmutableList<CommandButton> immutableList3, SessionCommands sessionCommands2, Player.Commands commands, Player.Commands commands2, Bundle bundle, Bundle bundle2, PlayerInfo playerInfo2, MediaSession.Token token) {
        this.libraryVersion = i;
        this.sessionInterfaceVersion = i2;
        this.sessionBinder = iMediaSession;
        this.sessionActivity = pendingIntent;
        this.customLayout = immutableList;
        this.mediaButtonPreferences = immutableList2;
        this.commandButtonsForMediaItems = immutableList3;
        this.sessionCommands = sessionCommands2;
        this.playerCommandsFromSession = commands;
        this.playerCommandsFromPlayer = commands2;
        this.tokenExtras = bundle;
        this.sessionExtras = bundle2;
        this.playerInfo = playerInfo2;
        this.platformToken = token;
    }

    public Bundle toBundleForRemoteProcess(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_LIBRARY_VERSION, this.libraryVersion);
        BundleCompat.putBinder(bundle, FIELD_SESSION_BINDER, this.sessionBinder.asBinder());
        bundle.putParcelable(FIELD_SESSION_ACTIVITY, this.sessionActivity);
        if (!this.customLayout.isEmpty()) {
            bundle.putParcelableArrayList(FIELD_CUSTOM_LAYOUT, BundleCollectionUtil.toBundleArrayList(this.customLayout, new ConnectionState$$ExternalSyntheticLambda0()));
        }
        if (!this.mediaButtonPreferences.isEmpty()) {
            if (i >= 7) {
                bundle.putParcelableArrayList(FIELD_MEDIA_BUTTON_PREFERENCES, BundleCollectionUtil.toBundleArrayList(this.mediaButtonPreferences, new ConnectionState$$ExternalSyntheticLambda0()));
            } else {
                bundle.putParcelableArrayList(FIELD_CUSTOM_LAYOUT, BundleCollectionUtil.toBundleArrayList(this.mediaButtonPreferences, new ConnectionState$$ExternalSyntheticLambda0()));
            }
        }
        if (!this.commandButtonsForMediaItems.isEmpty()) {
            bundle.putParcelableArrayList(FIELD_COMMAND_BUTTONS_FOR_MEDIA_ITEMS, BundleCollectionUtil.toBundleArrayList(this.commandButtonsForMediaItems, new ConnectionState$$ExternalSyntheticLambda0()));
        }
        bundle.putBundle(FIELD_SESSION_COMMANDS, this.sessionCommands.toBundle());
        bundle.putBundle(FIELD_PLAYER_COMMANDS_FROM_SESSION, this.playerCommandsFromSession.toBundle());
        bundle.putBundle(FIELD_PLAYER_COMMANDS_FROM_PLAYER, this.playerCommandsFromPlayer.toBundle());
        bundle.putBundle(FIELD_TOKEN_EXTRAS, this.tokenExtras);
        bundle.putBundle(FIELD_SESSION_EXTRAS, this.sessionExtras);
        bundle.putBundle(FIELD_PLAYER_INFO, this.playerInfo.filterByAvailableCommands(MediaUtils.intersect(this.playerCommandsFromSession, this.playerCommandsFromPlayer), false, false).toBundleForRemoteProcess(i));
        bundle.putInt(FIELD_SESSION_INTERFACE_VERSION, this.sessionInterfaceVersion);
        MediaSession.Token token = this.platformToken;
        if (token != null) {
            bundle.putParcelable(FIELD_PLATFORM_TOKEN, token);
        }
        return bundle;
    }

    public Bundle toBundleInProcess() {
        Bundle bundle = new Bundle();
        bundle.putBinder(FIELD_IN_PROCESS_BINDER, new InProcessBinder());
        return bundle;
    }

    public static ConnectionState fromBundle(Bundle bundle) {
        ImmutableList immutableList;
        ImmutableList immutableList2;
        ImmutableList immutableList3;
        SessionCommands sessionCommands2;
        Player.Commands commands;
        Player.Commands commands2;
        PlayerInfo playerInfo2;
        Bundle bundle2 = bundle;
        IBinder binder = bundle2.getBinder(FIELD_IN_PROCESS_BINDER);
        if (binder instanceof InProcessBinder) {
            return ((InProcessBinder) binder).getConnectionState();
        }
        int i = bundle2.getInt(FIELD_LIBRARY_VERSION, 0);
        int i2 = bundle2.getInt(FIELD_SESSION_INTERFACE_VERSION, 0);
        IBinder iBinder = (IBinder) Assertions.checkNotNull(BundleCompat.getBinder(bundle2, FIELD_SESSION_BINDER));
        PendingIntent pendingIntent = (PendingIntent) bundle2.getParcelable(FIELD_SESSION_ACTIVITY);
        ArrayList parcelableArrayList = bundle2.getParcelableArrayList(FIELD_CUSTOM_LAYOUT);
        if (parcelableArrayList != null) {
            immutableList = BundleCollectionUtil.fromBundleList(new ConnectionState$$ExternalSyntheticLambda1(i2), parcelableArrayList);
        } else {
            immutableList = ImmutableList.of();
        }
        ImmutableList immutableList4 = immutableList;
        ArrayList parcelableArrayList2 = bundle2.getParcelableArrayList(FIELD_MEDIA_BUTTON_PREFERENCES);
        if (parcelableArrayList2 != null) {
            immutableList2 = BundleCollectionUtil.fromBundleList(new ConnectionState$$ExternalSyntheticLambda2(i2), parcelableArrayList2);
        } else {
            immutableList2 = ImmutableList.of();
        }
        ImmutableList immutableList5 = immutableList2;
        ArrayList parcelableArrayList3 = bundle2.getParcelableArrayList(FIELD_COMMAND_BUTTONS_FOR_MEDIA_ITEMS);
        if (parcelableArrayList3 != null) {
            immutableList3 = BundleCollectionUtil.fromBundleList(new ConnectionState$$ExternalSyntheticLambda3(i2), parcelableArrayList3);
        } else {
            immutableList3 = ImmutableList.of();
        }
        ImmutableList immutableList6 = immutableList3;
        Bundle bundle3 = bundle2.getBundle(FIELD_SESSION_COMMANDS);
        if (bundle3 == null) {
            sessionCommands2 = SessionCommands.EMPTY;
        } else {
            sessionCommands2 = SessionCommands.fromBundle(bundle3);
        }
        SessionCommands sessionCommands3 = sessionCommands2;
        Bundle bundle4 = bundle2.getBundle(FIELD_PLAYER_COMMANDS_FROM_PLAYER);
        if (bundle4 == null) {
            commands = Player.Commands.EMPTY;
        } else {
            commands = Player.Commands.fromBundle(bundle4);
        }
        Player.Commands commands3 = commands;
        Bundle bundle5 = bundle2.getBundle(FIELD_PLAYER_COMMANDS_FROM_SESSION);
        if (bundle5 == null) {
            commands2 = Player.Commands.EMPTY;
        } else {
            commands2 = Player.Commands.fromBundle(bundle5);
        }
        Player.Commands commands4 = commands2;
        Bundle bundle6 = bundle2.getBundle(FIELD_TOKEN_EXTRAS);
        Bundle bundle7 = bundle2.getBundle(FIELD_SESSION_EXTRAS);
        Bundle bundle8 = bundle2.getBundle(FIELD_PLAYER_INFO);
        if (bundle8 == null) {
            playerInfo2 = PlayerInfo.DEFAULT;
        } else {
            playerInfo2 = PlayerInfo.fromBundle(bundle8, i2);
        }
        return new ConnectionState(i, i2, IMediaSession.Stub.asInterface(iBinder), pendingIntent, immutableList4, immutableList5, immutableList6, sessionCommands3, commands4, commands3, bundle6 == null ? Bundle.EMPTY : bundle6, bundle7 == null ? Bundle.EMPTY : bundle7, playerInfo2, (MediaSession.Token) bundle2.getParcelable(FIELD_PLATFORM_TOKEN));
    }

    private final class InProcessBinder extends Binder {
        private InProcessBinder() {
        }

        public ConnectionState getConnectionState() {
            return ConnectionState.this;
        }
    }
}
