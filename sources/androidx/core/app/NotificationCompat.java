package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Person;
import android.app.RemoteInput;
import android.content.Context;
import android.content.LocusId;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.widget.RemoteViews;
import androidx.core.R;
import androidx.core.app.Person;
import androidx.core.content.ContextCompat;
import androidx.core.content.LocusIdCompat;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.text.BidiFormatter;
import androidx.core.view.ViewCompat;
import com.squareup.kotlinpoet.FileSpecKt;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class NotificationCompat {
    public static final int BADGE_ICON_LARGE = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_LOCATION_SHARING = "location_sharing";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_MISSED_CALL = "missed_call";
    public static final String CATEGORY_NAVIGATION = "navigation";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_STOPWATCH = "stopwatch";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    public static final String CATEGORY_WORKOUT = "workout";
    public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_ANSWER_COLOR = "android.answerColor";
    public static final String EXTRA_ANSWER_INTENT = "android.answerIntent";
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_CALL_IS_VIDEO = "android.callIsVideo";
    public static final String EXTRA_CALL_PERSON = "android.callPerson";
    public static final String EXTRA_CALL_PERSON_COMPAT = "android.callPersonCompat";
    public static final String EXTRA_CALL_TYPE = "android.callType";
    public static final String EXTRA_CHANNEL_GROUP_ID = "android.intent.extra.CHANNEL_GROUP_ID";
    public static final String EXTRA_CHANNEL_ID = "android.intent.extra.CHANNEL_ID";
    public static final String EXTRA_CHRONOMETER_COUNT_DOWN = "android.chronometerCountDown";
    public static final String EXTRA_COLORIZED = "android.colorized";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_COMPAT_TEMPLATE = "androidx.core.app.extra.COMPAT_TEMPLATE";
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
    public static final String EXTRA_DECLINE_COLOR = "android.declineColor";
    public static final String EXTRA_DECLINE_INTENT = "android.declineIntent";
    public static final String EXTRA_HANG_UP_INTENT = "android.hangUpIntent";
    public static final String EXTRA_HIDDEN_CONVERSATION_TITLE = "android.hiddenConversationTitle";
    public static final String EXTRA_HISTORIC_MESSAGES = "android.messages.historic";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_MESSAGES = "android.messages";
    public static final String EXTRA_MESSAGING_STYLE_USER = "android.messagingStyleUser";
    public static final String EXTRA_NOTIFICATION_ID = "android.intent.extra.NOTIFICATION_ID";
    public static final String EXTRA_NOTIFICATION_TAG = "android.intent.extra.NOTIFICATION_TAG";
    @Deprecated
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PEOPLE_LIST = "android.people.list";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PICTURE_CONTENT_DESCRIPTION = "android.pictureContentDescription";
    public static final String EXTRA_PICTURE_ICON = "android.pictureIcon";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
    public static final String EXTRA_SHOW_BIG_PICTURE_WHEN_COLLAPSED = "android.showBigPictureWhenCollapsed";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final String EXTRA_VERIFICATION_ICON = "android.verificationIcon";
    public static final String EXTRA_VERIFICATION_ICON_COMPAT = "android.verificationIconCompat";
    public static final String EXTRA_VERIFICATION_TEXT = "android.verificationText";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_BUBBLE = 4096;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;
    @Deprecated
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    public static final int FOREGROUND_SERVICE_DEFAULT = 0;
    public static final int FOREGROUND_SERVICE_DEFERRED = 2;
    public static final int FOREGROUND_SERVICE_IMMEDIATE = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int GROUP_ALERT_SUMMARY = 1;
    public static final String GROUP_KEY_SILENT = "silent";
    public static final String INTENT_CATEGORY_NOTIFICATION_PREFERENCES = "android.intent.category.NOTIFICATION_PREFERENCES";
    public static final int MAX_ACTION_BUTTONS = 3;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    private static final String TAG = "NotifCompat";
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BadgeIconType {
    }

    public interface Extender {
        Builder extend(Builder builder);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface GroupAlertBehavior {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NotificationVisibility {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ServiceNotificationBehavior {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamType {
    }

    public static Bitmap reduceLargeIconSize(Context context, Bitmap bitmap) {
        return bitmap;
    }

    public static class Builder {
        private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
        public ArrayList<Action> mActions;
        boolean mAllowSystemGeneratedContextualActions;
        int mBadgeIcon;
        RemoteViews mBigContentView;
        BubbleMetadata mBubbleMetadata;
        String mCategory;
        String mChannelId;
        boolean mChronometerCountDown;
        int mColor;
        boolean mColorized;
        boolean mColorizedSet;
        CharSequence mContentInfo;
        PendingIntent mContentIntent;
        CharSequence mContentText;
        CharSequence mContentTitle;
        RemoteViews mContentView;
        public Context mContext;
        Bundle mExtras;
        int mFgsDeferBehavior;
        PendingIntent mFullScreenIntent;
        int mGroupAlertBehavior;
        String mGroupKey;
        boolean mGroupSummary;
        RemoteViews mHeadsUpContentView;
        ArrayList<Action> mInvisibleActions;
        IconCompat mLargeIcon;
        boolean mLocalOnly;
        LocusIdCompat mLocusId;
        Notification mNotification;
        int mNumber;
        @Deprecated
        public ArrayList<String> mPeople;
        public ArrayList<Person> mPersonList;
        int mPriority;
        int mProgress;
        boolean mProgressIndeterminate;
        int mProgressMax;
        Notification mPublicVersion;
        CharSequence[] mRemoteInputHistory;
        CharSequence mSettingsText;
        String mShortcutId;
        boolean mShowWhen;
        boolean mSilent;
        Object mSmallIcon;
        String mSortKey;
        Style mStyle;
        CharSequence mSubText;
        RemoteViews mTickerView;
        long mTimeout;
        boolean mUseChronometer;
        int mVisibility;

        public Builder(Context context, Notification notification) {
            this(context, NotificationCompat.getChannelId(notification));
            ArrayList parcelableArrayList;
            Bundle bundle = notification.extras;
            Style extractStyleFromNotification = Style.extractStyleFromNotification(notification);
            setContentTitle(NotificationCompat.getContentTitle(notification)).setContentText(NotificationCompat.getContentText(notification)).setContentInfo(NotificationCompat.getContentInfo(notification)).setSubText(NotificationCompat.getSubText(notification)).setSettingsText(NotificationCompat.getSettingsText(notification)).setStyle(extractStyleFromNotification).setGroup(NotificationCompat.getGroup(notification)).setGroupSummary(NotificationCompat.isGroupSummary(notification)).setLocusId(NotificationCompat.getLocusId(notification)).setWhen(notification.when).setShowWhen(NotificationCompat.getShowWhen(notification)).setUsesChronometer(NotificationCompat.getUsesChronometer(notification)).setAutoCancel(NotificationCompat.getAutoCancel(notification)).setOnlyAlertOnce(NotificationCompat.getOnlyAlertOnce(notification)).setOngoing(NotificationCompat.getOngoing(notification)).setLocalOnly(NotificationCompat.getLocalOnly(notification)).setLargeIcon(notification.largeIcon).setBadgeIconType(NotificationCompat.getBadgeIconType(notification)).setCategory(NotificationCompat.getCategory(notification)).setBubbleMetadata(NotificationCompat.getBubbleMetadata(notification)).setNumber(notification.number).setTicker(notification.tickerText).setContentIntent(notification.contentIntent).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(notification.fullScreenIntent, NotificationCompat.getHighPriority(notification)).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setDefaults(notification.defaults).setPriority(notification.priority).setColor(NotificationCompat.getColor(notification)).setVisibility(NotificationCompat.getVisibility(notification)).setPublicVersion(NotificationCompat.getPublicVersion(notification)).setSortKey(NotificationCompat.getSortKey(notification)).setTimeoutAfter(NotificationCompat.getTimeoutAfter(notification)).setShortcutId(NotificationCompat.getShortcutId(notification)).setProgress(bundle.getInt(NotificationCompat.EXTRA_PROGRESS_MAX), bundle.getInt(NotificationCompat.EXTRA_PROGRESS), bundle.getBoolean(NotificationCompat.EXTRA_PROGRESS_INDETERMINATE)).setAllowSystemGeneratedContextualActions(NotificationCompat.getAllowSystemGeneratedContextualActions(notification)).setSmallIcon(notification.icon, notification.iconLevel).addExtras(getExtrasWithoutDuplicateData(notification, extractStyleFromNotification));
            this.mSmallIcon = Api23Impl.getSmallIcon(notification);
            Icon largeIcon = Api23Impl.getLargeIcon(notification);
            if (largeIcon != null) {
                this.mLargeIcon = IconCompat.createFromIcon(largeIcon);
            }
            if (!(notification.actions == null || notification.actions.length == 0)) {
                for (Notification.Action fromAndroidAction : notification.actions) {
                    addAction(Action.Builder.fromAndroidAction(fromAndroidAction).build());
                }
            }
            List<Action> invisibleActions = NotificationCompat.getInvisibleActions(notification);
            if (!invisibleActions.isEmpty()) {
                for (Action addInvisibleAction : invisibleActions) {
                    addInvisibleAction(addInvisibleAction);
                }
            }
            String[] stringArray = notification.extras.getStringArray(NotificationCompat.EXTRA_PEOPLE);
            if (!(stringArray == null || stringArray.length == 0)) {
                for (String addPerson : stringArray) {
                    addPerson(addPerson);
                }
            }
            if (Build.VERSION.SDK_INT >= 28 && (parcelableArrayList = notification.extras.getParcelableArrayList(NotificationCompat.EXTRA_PEOPLE_LIST)) != null && !parcelableArrayList.isEmpty()) {
                Iterator it = parcelableArrayList.iterator();
                while (it.hasNext()) {
                    addPerson(Person.fromAndroidPerson((Person) it.next()));
                }
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_CHRONOMETER_COUNT_DOWN)) {
                setChronometerCountDown(bundle.getBoolean(NotificationCompat.EXTRA_CHRONOMETER_COUNT_DOWN));
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_COLORIZED)) {
                setColorized(bundle.getBoolean(NotificationCompat.EXTRA_COLORIZED));
            }
        }

        private static Bundle getExtrasWithoutDuplicateData(Notification notification, Style style) {
            if (notification.extras == null) {
                return null;
            }
            Bundle bundle = new Bundle(notification.extras);
            bundle.remove(NotificationCompat.EXTRA_TITLE);
            bundle.remove(NotificationCompat.EXTRA_TEXT);
            bundle.remove(NotificationCompat.EXTRA_INFO_TEXT);
            bundle.remove(NotificationCompat.EXTRA_SUB_TEXT);
            bundle.remove(NotificationCompat.EXTRA_CHANNEL_ID);
            bundle.remove(NotificationCompat.EXTRA_CHANNEL_GROUP_ID);
            bundle.remove(NotificationCompat.EXTRA_SHOW_WHEN);
            bundle.remove(NotificationCompat.EXTRA_PROGRESS);
            bundle.remove(NotificationCompat.EXTRA_PROGRESS_MAX);
            bundle.remove(NotificationCompat.EXTRA_PROGRESS_INDETERMINATE);
            bundle.remove(NotificationCompat.EXTRA_CHRONOMETER_COUNT_DOWN);
            bundle.remove(NotificationCompat.EXTRA_COLORIZED);
            bundle.remove(NotificationCompat.EXTRA_PEOPLE_LIST);
            bundle.remove(NotificationCompat.EXTRA_PEOPLE);
            bundle.remove(NotificationCompatExtras.EXTRA_SORT_KEY);
            bundle.remove(NotificationCompatExtras.EXTRA_GROUP_KEY);
            bundle.remove(NotificationCompatExtras.EXTRA_GROUP_SUMMARY);
            bundle.remove(NotificationCompatExtras.EXTRA_LOCAL_ONLY);
            bundle.remove(NotificationCompatExtras.EXTRA_ACTION_EXTRAS);
            Bundle bundle2 = bundle.getBundle("android.car.EXTENSIONS");
            if (bundle2 != null) {
                Bundle bundle3 = new Bundle(bundle2);
                bundle3.remove("invisible_actions");
                bundle.putBundle("android.car.EXTENSIONS", bundle3);
            }
            if (style != null) {
                style.clearCompatExtraKeys(bundle);
            }
            return bundle;
        }

        public Builder(Context context, String str) {
            this.mActions = new ArrayList<>();
            this.mPersonList = new ArrayList<>();
            this.mInvisibleActions = new ArrayList<>();
            this.mShowWhen = true;
            this.mLocalOnly = false;
            this.mColor = 0;
            this.mVisibility = 0;
            this.mBadgeIcon = 0;
            this.mGroupAlertBehavior = 0;
            this.mFgsDeferBehavior = 0;
            Notification notification = new Notification();
            this.mNotification = notification;
            this.mContext = context;
            this.mChannelId = str;
            notification.when = System.currentTimeMillis();
            this.mNotification.audioStreamType = -1;
            this.mPriority = 0;
            this.mPeople = new ArrayList<>();
            this.mAllowSystemGeneratedContextualActions = true;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        @Deprecated
        public Builder(Context context) {
            this(context, (String) null);
            String str = null;
        }

        public Builder setWhen(long j) {
            this.mNotification.when = j;
            return this;
        }

        public Builder setShowWhen(boolean z) {
            this.mShowWhen = z;
            return this;
        }

        public Builder setSmallIcon(IconCompat iconCompat) {
            this.mSmallIcon = iconCompat.toIcon(this.mContext);
            return this;
        }

        public Builder setUsesChronometer(boolean z) {
            this.mUseChronometer = z;
            return this;
        }

        public Builder setChronometerCountDown(boolean z) {
            this.mChronometerCountDown = z;
            getExtras().putBoolean(NotificationCompat.EXTRA_CHRONOMETER_COUNT_DOWN, z);
            return this;
        }

        public Builder setSmallIcon(int i) {
            this.mNotification.icon = i;
            return this;
        }

        public Builder setSmallIcon(int i, int i2) {
            this.mNotification.icon = i;
            this.mNotification.iconLevel = i2;
            return this;
        }

        @Deprecated
        public Builder setNotificationSilent() {
            this.mSilent = true;
            return this;
        }

        public Builder setSilent(boolean z) {
            this.mSilent = z;
            return this;
        }

        public Builder setContentTitle(CharSequence charSequence) {
            this.mContentTitle = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setContentText(CharSequence charSequence) {
            this.mContentText = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setSubText(CharSequence charSequence) {
            this.mSubText = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setSettingsText(CharSequence charSequence) {
            this.mSettingsText = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setRemoteInputHistory(CharSequence[] charSequenceArr) {
            this.mRemoteInputHistory = charSequenceArr;
            return this;
        }

        public Builder setNumber(int i) {
            this.mNumber = i;
            return this;
        }

        public Builder setContentInfo(CharSequence charSequence) {
            this.mContentInfo = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setProgress(int i, int i2, boolean z) {
            this.mProgressMax = i;
            this.mProgress = i2;
            this.mProgressIndeterminate = z;
            return this;
        }

        public Builder setContent(RemoteViews remoteViews) {
            this.mNotification.contentView = remoteViews;
            return this;
        }

        public Builder setContentIntent(PendingIntent pendingIntent) {
            this.mContentIntent = pendingIntent;
            return this;
        }

        public Builder setDeleteIntent(PendingIntent pendingIntent) {
            this.mNotification.deleteIntent = pendingIntent;
            return this;
        }

        public Builder setFullScreenIntent(PendingIntent pendingIntent, boolean z) {
            this.mFullScreenIntent = pendingIntent;
            setFlag(128, z);
            return this;
        }

        public Builder setTicker(CharSequence charSequence) {
            this.mNotification.tickerText = limitCharSequenceLength(charSequence);
            return this;
        }

        @Deprecated
        public Builder setTicker(CharSequence charSequence, RemoteViews remoteViews) {
            this.mNotification.tickerText = limitCharSequenceLength(charSequence);
            this.mTickerView = remoteViews;
            return this;
        }

        public Builder setLargeIcon(Bitmap bitmap) {
            this.mLargeIcon = bitmap == null ? null : IconCompat.createWithBitmap(NotificationCompat.reduceLargeIconSize(this.mContext, bitmap));
            return this;
        }

        public Builder setLargeIcon(Icon icon) {
            this.mLargeIcon = icon == null ? null : IconCompat.createFromIcon(icon);
            return this;
        }

        public Builder setSound(Uri uri) {
            this.mNotification.sound = uri;
            this.mNotification.audioStreamType = -1;
            AudioAttributes.Builder usage = Api21Impl.setUsage(Api21Impl.setContentType(Api21Impl.createBuilder(), 4), 5);
            this.mNotification.audioAttributes = Api21Impl.build(usage);
            return this;
        }

        public Builder setSound(Uri uri, int i) {
            this.mNotification.sound = uri;
            this.mNotification.audioStreamType = i;
            AudioAttributes.Builder legacyStreamType = Api21Impl.setLegacyStreamType(Api21Impl.setContentType(Api21Impl.createBuilder(), 4), i);
            this.mNotification.audioAttributes = Api21Impl.build(legacyStreamType);
            return this;
        }

        public Builder setVibrate(long[] jArr) {
            this.mNotification.vibrate = jArr;
            return this;
        }

        public Builder setLights(int i, int i2, int i3) {
            this.mNotification.ledARGB = i;
            this.mNotification.ledOnMS = i2;
            this.mNotification.ledOffMS = i3;
            int i4 = (this.mNotification.ledOnMS == 0 || this.mNotification.ledOffMS == 0) ? 0 : 1;
            Notification notification = this.mNotification;
            notification.flags = i4 | (notification.flags & -2);
            return this;
        }

        public Builder setOngoing(boolean z) {
            setFlag(2, z);
            return this;
        }

        public Builder setColorized(boolean z) {
            this.mColorized = z;
            this.mColorizedSet = true;
            return this;
        }

        public Builder setOnlyAlertOnce(boolean z) {
            setFlag(8, z);
            return this;
        }

        public Builder setAutoCancel(boolean z) {
            setFlag(16, z);
            return this;
        }

        public Builder setLocalOnly(boolean z) {
            this.mLocalOnly = z;
            return this;
        }

        public Builder setCategory(String str) {
            this.mCategory = str;
            return this;
        }

        public Builder setDefaults(int i) {
            this.mNotification.defaults = i;
            if ((i & 4) != 0) {
                this.mNotification.flags |= 1;
            }
            return this;
        }

        private void setFlag(int i, boolean z) {
            if (z) {
                Notification notification = this.mNotification;
                notification.flags = i | notification.flags;
                return;
            }
            Notification notification2 = this.mNotification;
            notification2.flags = (~i) & notification2.flags;
        }

        public Builder setPriority(int i) {
            this.mPriority = i;
            return this;
        }

        @Deprecated
        public Builder addPerson(String str) {
            if (str != null && !str.isEmpty()) {
                this.mPeople.add(str);
            }
            return this;
        }

        public Builder addPerson(Person person) {
            if (person != null) {
                this.mPersonList.add(person);
            }
            return this;
        }

        public Builder clearPeople() {
            this.mPersonList.clear();
            this.mPeople.clear();
            return this;
        }

        public Builder setGroup(String str) {
            this.mGroupKey = str;
            return this;
        }

        public Builder setGroupSummary(boolean z) {
            this.mGroupSummary = z;
            return this;
        }

        public Builder setSortKey(String str) {
            this.mSortKey = str;
            return this;
        }

        public Builder addExtras(Bundle bundle) {
            if (bundle != null) {
                Bundle bundle2 = this.mExtras;
                if (bundle2 == null) {
                    this.mExtras = new Bundle(bundle);
                } else {
                    bundle2.putAll(bundle);
                }
            }
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.mExtras = bundle;
            return this;
        }

        public Bundle getExtras() {
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            return this.mExtras;
        }

        public Builder addAction(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this.mActions.add(new Action(i, charSequence, pendingIntent));
            return this;
        }

        public Builder addAction(Action action) {
            if (action != null) {
                this.mActions.add(action);
            }
            return this;
        }

        public Builder clearActions() {
            this.mActions.clear();
            return this;
        }

        public Builder addInvisibleAction(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this.mInvisibleActions.add(new Action(i, charSequence, pendingIntent));
            return this;
        }

        public Builder addInvisibleAction(Action action) {
            if (action != null) {
                this.mInvisibleActions.add(action);
            }
            return this;
        }

        public Builder clearInvisibleActions() {
            this.mInvisibleActions.clear();
            Bundle bundle = this.mExtras.getBundle("android.car.EXTENSIONS");
            if (bundle != null) {
                Bundle bundle2 = new Bundle(bundle);
                bundle2.remove("invisible_actions");
                this.mExtras.putBundle("android.car.EXTENSIONS", bundle2);
            }
            return this;
        }

        public Builder setStyle(Style style) {
            if (this.mStyle != style) {
                this.mStyle = style;
                if (style != null) {
                    style.setBuilder(this);
                }
            }
            return this;
        }

        public Builder setColor(int i) {
            this.mColor = i;
            return this;
        }

        public Builder setVisibility(int i) {
            this.mVisibility = i;
            return this;
        }

        public Builder setPublicVersion(Notification notification) {
            this.mPublicVersion = notification;
            return this;
        }

        private boolean useExistingRemoteView() {
            Style style = this.mStyle;
            return style == null || !style.displayCustomViewInline();
        }

        public RemoteViews createContentView() {
            RemoteViews makeContentView;
            if (this.mContentView != null && useExistingRemoteView()) {
                return this.mContentView;
            }
            NotificationCompatBuilder notificationCompatBuilder = new NotificationCompatBuilder(this);
            Style style = this.mStyle;
            if (style != null && (makeContentView = style.makeContentView(notificationCompatBuilder)) != null) {
                return makeContentView;
            }
            return Api24Impl.createContentView(Api24Impl.recoverBuilder(this.mContext, notificationCompatBuilder.build()));
        }

        public RemoteViews createBigContentView() {
            RemoteViews makeBigContentView;
            if (this.mBigContentView != null && useExistingRemoteView()) {
                return this.mBigContentView;
            }
            NotificationCompatBuilder notificationCompatBuilder = new NotificationCompatBuilder(this);
            Style style = this.mStyle;
            if (style != null && (makeBigContentView = style.makeBigContentView(notificationCompatBuilder)) != null) {
                return makeBigContentView;
            }
            return Api24Impl.createBigContentView(Api24Impl.recoverBuilder(this.mContext, notificationCompatBuilder.build()));
        }

        public RemoteViews createHeadsUpContentView() {
            RemoteViews makeHeadsUpContentView;
            if (this.mHeadsUpContentView != null && useExistingRemoteView()) {
                return this.mHeadsUpContentView;
            }
            NotificationCompatBuilder notificationCompatBuilder = new NotificationCompatBuilder(this);
            Style style = this.mStyle;
            if (style != null && (makeHeadsUpContentView = style.makeHeadsUpContentView(notificationCompatBuilder)) != null) {
                return makeHeadsUpContentView;
            }
            return Api24Impl.createHeadsUpContentView(Api24Impl.recoverBuilder(this.mContext, notificationCompatBuilder.build()));
        }

        public Builder setCustomContentView(RemoteViews remoteViews) {
            this.mContentView = remoteViews;
            return this;
        }

        public Builder setCustomBigContentView(RemoteViews remoteViews) {
            this.mBigContentView = remoteViews;
            return this;
        }

        public Builder setCustomHeadsUpContentView(RemoteViews remoteViews) {
            this.mHeadsUpContentView = remoteViews;
            return this;
        }

        public Builder setChannelId(String str) {
            this.mChannelId = str;
            return this;
        }

        public Builder setTimeoutAfter(long j) {
            this.mTimeout = j;
            return this;
        }

        public Builder setShortcutId(String str) {
            this.mShortcutId = str;
            return this;
        }

        public Builder setShortcutInfo(ShortcutInfoCompat shortcutInfoCompat) {
            if (shortcutInfoCompat == null) {
                return this;
            }
            this.mShortcutId = shortcutInfoCompat.getId();
            if (this.mLocusId == null) {
                if (shortcutInfoCompat.getLocusId() != null) {
                    this.mLocusId = shortcutInfoCompat.getLocusId();
                } else if (shortcutInfoCompat.getId() != null) {
                    this.mLocusId = new LocusIdCompat(shortcutInfoCompat.getId());
                }
            }
            if (this.mContentTitle == null) {
                setContentTitle(shortcutInfoCompat.getShortLabel());
            }
            return this;
        }

        public Builder setLocusId(LocusIdCompat locusIdCompat) {
            this.mLocusId = locusIdCompat;
            return this;
        }

        public Builder setBadgeIconType(int i) {
            this.mBadgeIcon = i;
            return this;
        }

        public Builder setGroupAlertBehavior(int i) {
            this.mGroupAlertBehavior = i;
            return this;
        }

        public Builder setForegroundServiceBehavior(int i) {
            this.mFgsDeferBehavior = i;
            return this;
        }

        public Builder setBubbleMetadata(BubbleMetadata bubbleMetadata) {
            this.mBubbleMetadata = bubbleMetadata;
            return this;
        }

        public Builder extend(Extender extender) {
            extender.extend(this);
            return this;
        }

        public Builder setAllowSystemGeneratedContextualActions(boolean z) {
            this.mAllowSystemGeneratedContextualActions = z;
            return this;
        }

        @Deprecated
        public Notification getNotification() {
            return build();
        }

        public Notification build() {
            return new NotificationCompatBuilder(this).build();
        }

        protected static CharSequence limitCharSequenceLength(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > MAX_CHARSEQUENCE_LENGTH) ? charSequence.subSequence(0, MAX_CHARSEQUENCE_LENGTH) : charSequence;
        }

        public RemoteViews getContentView() {
            return this.mContentView;
        }

        public RemoteViews getBigContentView() {
            return this.mBigContentView;
        }

        public RemoteViews getHeadsUpContentView() {
            return this.mHeadsUpContentView;
        }

        public long getWhenIfShowing() {
            if (this.mShowWhen) {
                return this.mNotification.when;
            }
            return 0;
        }

        public int getPriority() {
            return this.mPriority;
        }

        public int getForegroundServiceBehavior() {
            return this.mFgsDeferBehavior;
        }

        public int getColor() {
            return this.mColor;
        }

        public BubbleMetadata getBubbleMetadata() {
            return this.mBubbleMetadata;
        }

        static class Api21Impl {
            private Api21Impl() {
            }

            static AudioAttributes.Builder createBuilder() {
                return new AudioAttributes.Builder();
            }

            static AudioAttributes.Builder setContentType(AudioAttributes.Builder builder, int i) {
                return builder.setContentType(i);
            }

            static AudioAttributes.Builder setUsage(AudioAttributes.Builder builder, int i) {
                return builder.setUsage(i);
            }

            static AudioAttributes.Builder setLegacyStreamType(AudioAttributes.Builder builder, int i) {
                return builder.setLegacyStreamType(i);
            }

            static AudioAttributes build(AudioAttributes.Builder builder) {
                return builder.build();
            }
        }

        static class Api23Impl {
            private Api23Impl() {
            }

            static Icon getSmallIcon(Notification notification) {
                return notification.getSmallIcon();
            }

            static Icon getLargeIcon(Notification notification) {
                return notification.getLargeIcon();
            }
        }

        static class Api24Impl {
            private Api24Impl() {
            }

            static Notification.Builder recoverBuilder(Context context, Notification notification) {
                return Notification.Builder.recoverBuilder(context, notification);
            }

            static RemoteViews createContentView(Notification.Builder builder) {
                return builder.createContentView();
            }

            static RemoteViews createHeadsUpContentView(Notification.Builder builder) {
                return builder.createHeadsUpContentView();
            }

            static RemoteViews createBigContentView(Notification.Builder builder) {
                return builder.createHeadsUpContentView();
            }
        }
    }

    public static abstract class Style {
        CharSequence mBigContentTitle;
        protected Builder mBuilder;
        CharSequence mSummaryText;
        boolean mSummaryTextSet = false;

        private static float constrain(float f, float f2, float f3) {
            return f < f2 ? f2 : f > f3 ? f3 : f;
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        }

        public boolean displayCustomViewInline() {
            return false;
        }

        /* access modifiers changed from: protected */
        public String getClassName() {
            return null;
        }

        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public void setBuilder(Builder builder) {
            if (this.mBuilder != builder) {
                this.mBuilder = builder;
                if (builder != null) {
                    builder.setStyle(this);
                }
            }
        }

        public Notification build() {
            Builder builder = this.mBuilder;
            if (builder != null) {
                return builder.build();
            }
            return null;
        }

        public void addCompatExtras(Bundle bundle) {
            if (this.mSummaryTextSet) {
                bundle.putCharSequence(NotificationCompat.EXTRA_SUMMARY_TEXT, this.mSummaryText);
            }
            CharSequence charSequence = this.mBigContentTitle;
            if (charSequence != null) {
                bundle.putCharSequence(NotificationCompat.EXTRA_TITLE_BIG, charSequence);
            }
            String className = getClassName();
            if (className != null) {
                bundle.putString(NotificationCompat.EXTRA_COMPAT_TEMPLATE, className);
            }
        }

        /* access modifiers changed from: protected */
        public void restoreFromCompatExtras(Bundle bundle) {
            if (bundle.containsKey(NotificationCompat.EXTRA_SUMMARY_TEXT)) {
                this.mSummaryText = bundle.getCharSequence(NotificationCompat.EXTRA_SUMMARY_TEXT);
                this.mSummaryTextSet = true;
            }
            this.mBigContentTitle = bundle.getCharSequence(NotificationCompat.EXTRA_TITLE_BIG);
        }

        /* access modifiers changed from: protected */
        public void clearCompatExtraKeys(Bundle bundle) {
            bundle.remove(NotificationCompat.EXTRA_SUMMARY_TEXT);
            bundle.remove(NotificationCompat.EXTRA_TITLE_BIG);
            bundle.remove(NotificationCompat.EXTRA_COMPAT_TEMPLATE);
        }

        public static Style extractStyleFromNotification(Notification notification) {
            Bundle extras = NotificationCompat.getExtras(notification);
            if (extras == null) {
                return null;
            }
            return constructStyleForExtras(extras);
        }

        private static Style constructCompatStyleByPlatformName(String str) {
            if (str == null) {
                return null;
            }
            if (str.equals(Notification.BigPictureStyle.class.getName())) {
                return new BigPictureStyle();
            }
            if (str.equals(Notification.BigTextStyle.class.getName())) {
                return new BigTextStyle();
            }
            if (str.equals(Notification.InboxStyle.class.getName())) {
                return new InboxStyle();
            }
            if (str.equals(Notification.MessagingStyle.class.getName())) {
                return new MessagingStyle();
            }
            if (str.equals(Notification.DecoratedCustomViewStyle.class.getName())) {
                return new DecoratedCustomViewStyle();
            }
            return null;
        }

        static Style constructCompatStyleByName(String str) {
            if (str == null) {
                return null;
            }
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -716705180:
                    if (str.equals("androidx.core.app.NotificationCompat$DecoratedCustomViewStyle")) {
                        c = 0;
                        break;
                    }
                    break;
                case -171946061:
                    if (str.equals("androidx.core.app.NotificationCompat$BigPictureStyle")) {
                        c = 1;
                        break;
                    }
                    break;
                case 714386739:
                    if (str.equals("androidx.core.app.NotificationCompat$CallStyle")) {
                        c = 2;
                        break;
                    }
                    break;
                case 912942987:
                    if (str.equals("androidx.core.app.NotificationCompat$InboxStyle")) {
                        c = 3;
                        break;
                    }
                    break;
                case 919595044:
                    if (str.equals("androidx.core.app.NotificationCompat$BigTextStyle")) {
                        c = 4;
                        break;
                    }
                    break;
                case 2090799565:
                    if (str.equals("androidx.core.app.NotificationCompat$MessagingStyle")) {
                        c = 5;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    return new DecoratedCustomViewStyle();
                case 1:
                    return new BigPictureStyle();
                case 2:
                    return new CallStyle();
                case 3:
                    return new InboxStyle();
                case 4:
                    return new BigTextStyle();
                case 5:
                    return new MessagingStyle();
                default:
                    return null;
            }
        }

        static Style constructCompatStyleForBundle(Bundle bundle) {
            Style constructCompatStyleByName = constructCompatStyleByName(bundle.getString(NotificationCompat.EXTRA_COMPAT_TEMPLATE));
            if (constructCompatStyleByName != null) {
                return constructCompatStyleByName;
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_SELF_DISPLAY_NAME) || bundle.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) {
                return new MessagingStyle();
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_PICTURE) || bundle.containsKey(NotificationCompat.EXTRA_PICTURE_ICON)) {
                return new BigPictureStyle();
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_BIG_TEXT)) {
                return new BigTextStyle();
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_TEXT_LINES)) {
                return new InboxStyle();
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_CALL_TYPE)) {
                return new CallStyle();
            }
            return constructCompatStyleByPlatformName(bundle.getString(NotificationCompat.EXTRA_TEMPLATE));
        }

        static Style constructStyleForExtras(Bundle bundle) {
            Style constructCompatStyleForBundle = constructCompatStyleForBundle(bundle);
            if (constructCompatStyleForBundle == null) {
                return null;
            }
            try {
                constructCompatStyleForBundle.restoreFromCompatExtras(bundle);
                return constructCompatStyleForBundle;
            } catch (ClassCastException unused) {
                return null;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:32:0x011c  */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x0143  */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x0167  */
        /* JADX WARNING: Removed duplicated region for block: B:49:0x01b3  */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x01b8  */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x01ba  */
        /* JADX WARNING: Removed duplicated region for block: B:56:0x01c3  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.widget.RemoteViews applyStandardTemplate(boolean r12, int r13, boolean r14) {
            /*
                r11 = this;
                androidx.core.app.NotificationCompat$Builder r0 = r11.mBuilder
                android.content.Context r0 = r0.mContext
                android.content.res.Resources r0 = r0.getResources()
                android.widget.RemoteViews r7 = new android.widget.RemoteViews
                androidx.core.app.NotificationCompat$Builder r1 = r11.mBuilder
                android.content.Context r1 = r1.mContext
                java.lang.String r1 = r1.getPackageName()
                r7.<init>(r1, r13)
                androidx.core.app.NotificationCompat$Builder r13 = r11.mBuilder
                r13.getPriority()
                androidx.core.app.NotificationCompat$Builder r13 = r11.mBuilder
                androidx.core.graphics.drawable.IconCompat r13 = r13.mLargeIcon
                r8 = 0
                if (r13 == 0) goto L_0x0068
                int r13 = androidx.core.R.id.icon
                r7.setViewVisibility(r13, r8)
                int r13 = androidx.core.R.id.icon
                androidx.core.app.NotificationCompat$Builder r1 = r11.mBuilder
                androidx.core.graphics.drawable.IconCompat r1 = r1.mLargeIcon
                android.graphics.Bitmap r1 = r11.createColoredBitmap((androidx.core.graphics.drawable.IconCompat) r1, (int) r8)
                r7.setImageViewBitmap(r13, r1)
                if (r12 == 0) goto L_0x009f
                androidx.core.app.NotificationCompat$Builder r12 = r11.mBuilder
                android.app.Notification r12 = r12.mNotification
                int r12 = r12.icon
                if (r12 == 0) goto L_0x009f
                int r12 = androidx.core.R.dimen.notification_right_icon_size
                int r12 = r0.getDimensionPixelSize(r12)
                int r13 = androidx.core.R.dimen.notification_small_icon_background_padding
                int r13 = r0.getDimensionPixelSize(r13)
                int r13 = r13 * 2
                int r13 = r12 - r13
                androidx.core.app.NotificationCompat$Builder r1 = r11.mBuilder
                android.app.Notification r1 = r1.mNotification
                int r1 = r1.icon
                androidx.core.app.NotificationCompat$Builder r2 = r11.mBuilder
                int r2 = r2.getColor()
                android.graphics.Bitmap r12 = r11.createIconWithBackground(r1, r12, r13, r2)
                int r13 = androidx.core.R.id.right_icon
                r7.setImageViewBitmap(r13, r12)
                int r12 = androidx.core.R.id.right_icon
                r7.setViewVisibility(r12, r8)
                goto L_0x009f
            L_0x0068:
                if (r12 == 0) goto L_0x009f
                androidx.core.app.NotificationCompat$Builder r12 = r11.mBuilder
                android.app.Notification r12 = r12.mNotification
                int r12 = r12.icon
                if (r12 == 0) goto L_0x009f
                int r12 = androidx.core.R.id.icon
                r7.setViewVisibility(r12, r8)
                int r12 = androidx.core.R.dimen.notification_large_icon_width
                int r12 = r0.getDimensionPixelSize(r12)
                int r13 = androidx.core.R.dimen.notification_big_circle_margin
                int r13 = r0.getDimensionPixelSize(r13)
                int r12 = r12 - r13
                int r13 = androidx.core.R.dimen.notification_small_icon_size_as_large
                int r13 = r0.getDimensionPixelSize(r13)
                androidx.core.app.NotificationCompat$Builder r1 = r11.mBuilder
                android.app.Notification r1 = r1.mNotification
                int r1 = r1.icon
                androidx.core.app.NotificationCompat$Builder r2 = r11.mBuilder
                int r2 = r2.getColor()
                android.graphics.Bitmap r12 = r11.createIconWithBackground(r1, r12, r13, r2)
                int r13 = androidx.core.R.id.icon
                r7.setImageViewBitmap(r13, r12)
            L_0x009f:
                androidx.core.app.NotificationCompat$Builder r12 = r11.mBuilder
                java.lang.CharSequence r12 = r12.mContentTitle
                if (r12 == 0) goto L_0x00ae
                int r12 = androidx.core.R.id.title
                androidx.core.app.NotificationCompat$Builder r13 = r11.mBuilder
                java.lang.CharSequence r13 = r13.mContentTitle
                r7.setTextViewText(r12, r13)
            L_0x00ae:
                androidx.core.app.NotificationCompat$Builder r12 = r11.mBuilder
                java.lang.CharSequence r12 = r12.mContentText
                r13 = 1
                if (r12 == 0) goto L_0x00c0
                int r12 = androidx.core.R.id.text
                androidx.core.app.NotificationCompat$Builder r1 = r11.mBuilder
                java.lang.CharSequence r1 = r1.mContentText
                r7.setTextViewText(r12, r1)
                r12 = r13
                goto L_0x00c1
            L_0x00c0:
                r12 = r8
            L_0x00c1:
                androidx.core.app.NotificationCompat$Builder r1 = r11.mBuilder
                java.lang.CharSequence r1 = r1.mContentInfo
                r9 = 8
                if (r1 == 0) goto L_0x00da
                int r12 = androidx.core.R.id.info
                androidx.core.app.NotificationCompat$Builder r1 = r11.mBuilder
                java.lang.CharSequence r1 = r1.mContentInfo
                r7.setTextViewText(r12, r1)
                int r12 = androidx.core.R.id.info
                r7.setViewVisibility(r12, r8)
            L_0x00d7:
                r12 = r13
                r10 = r12
                goto L_0x0116
            L_0x00da:
                androidx.core.app.NotificationCompat$Builder r1 = r11.mBuilder
                int r1 = r1.mNumber
                if (r1 <= 0) goto L_0x0110
                int r12 = androidx.core.R.integer.status_bar_notification_info_maxnum
                int r12 = r0.getInteger(r12)
                androidx.core.app.NotificationCompat$Builder r1 = r11.mBuilder
                int r1 = r1.mNumber
                if (r1 <= r12) goto L_0x00f8
                int r12 = androidx.core.R.id.info
                int r1 = androidx.core.R.string.status_bar_notification_info_overflow
                java.lang.String r1 = r0.getString(r1)
                r7.setTextViewText(r12, r1)
                goto L_0x010a
            L_0x00f8:
                java.text.NumberFormat r12 = java.text.NumberFormat.getIntegerInstance()
                int r1 = androidx.core.R.id.info
                androidx.core.app.NotificationCompat$Builder r2 = r11.mBuilder
                int r2 = r2.mNumber
                long r2 = (long) r2
                java.lang.String r12 = r12.format(r2)
                r7.setTextViewText(r1, r12)
            L_0x010a:
                int r12 = androidx.core.R.id.info
                r7.setViewVisibility(r12, r8)
                goto L_0x00d7
            L_0x0110:
                int r1 = androidx.core.R.id.info
                r7.setViewVisibility(r1, r9)
                r10 = r8
            L_0x0116:
                androidx.core.app.NotificationCompat$Builder r1 = r11.mBuilder
                java.lang.CharSequence r1 = r1.mSubText
                if (r1 == 0) goto L_0x0140
                int r1 = androidx.core.R.id.text
                androidx.core.app.NotificationCompat$Builder r2 = r11.mBuilder
                java.lang.CharSequence r2 = r2.mSubText
                r7.setTextViewText(r1, r2)
                androidx.core.app.NotificationCompat$Builder r1 = r11.mBuilder
                java.lang.CharSequence r1 = r1.mContentText
                if (r1 == 0) goto L_0x013b
                int r1 = androidx.core.R.id.text2
                androidx.core.app.NotificationCompat$Builder r2 = r11.mBuilder
                java.lang.CharSequence r2 = r2.mContentText
                r7.setTextViewText(r1, r2)
                int r1 = androidx.core.R.id.text2
                r7.setViewVisibility(r1, r8)
                r1 = r13
                goto L_0x0141
            L_0x013b:
                int r1 = androidx.core.R.id.text2
                r7.setViewVisibility(r1, r9)
            L_0x0140:
                r1 = r8
            L_0x0141:
                if (r1 == 0) goto L_0x015b
                if (r14 == 0) goto L_0x0151
                int r14 = androidx.core.R.dimen.notification_subtext_size
                int r14 = r0.getDimensionPixelSize(r14)
                float r14 = (float) r14
                int r0 = androidx.core.R.id.text
                r7.setTextViewTextSize(r0, r8, r14)
            L_0x0151:
                int r2 = androidx.core.R.id.line1
                r3 = 0
                r4 = 0
                r5 = 0
                r6 = 0
                r1 = r7
                r1.setViewPadding(r2, r3, r4, r5, r6)
            L_0x015b:
                androidx.core.app.NotificationCompat$Builder r14 = r11.mBuilder
                long r0 = r14.getWhenIfShowing()
                r2 = 0
                int r14 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r14 == 0) goto L_0x01b3
                androidx.core.app.NotificationCompat$Builder r14 = r11.mBuilder
                boolean r14 = r14.mUseChronometer
                if (r14 == 0) goto L_0x01a0
                int r14 = androidx.core.R.id.chronometer
                r7.setViewVisibility(r14, r8)
                int r14 = androidx.core.R.id.chronometer
                androidx.core.app.NotificationCompat$Builder r0 = r11.mBuilder
                long r0 = r0.getWhenIfShowing()
                long r2 = android.os.SystemClock.elapsedRealtime()
                long r4 = java.lang.System.currentTimeMillis()
                long r2 = r2 - r4
                long r0 = r0 + r2
                java.lang.String r2 = "setBase"
                r7.setLong(r14, r2, r0)
                int r14 = androidx.core.R.id.chronometer
                java.lang.String r0 = "setStarted"
                r7.setBoolean(r14, r0, r13)
                androidx.core.app.NotificationCompat$Builder r14 = r11.mBuilder
                boolean r14 = r14.mChronometerCountDown
                if (r14 == 0) goto L_0x01b4
                int r14 = androidx.core.R.id.chronometer
                androidx.core.app.NotificationCompat$Builder r0 = r11.mBuilder
                boolean r0 = r0.mChronometerCountDown
                androidx.core.app.NotificationCompat.Style.Api24Impl.setChronometerCountDown(r7, r14, r0)
                goto L_0x01b4
            L_0x01a0:
                int r14 = androidx.core.R.id.time
                r7.setViewVisibility(r14, r8)
                int r14 = androidx.core.R.id.time
                androidx.core.app.NotificationCompat$Builder r0 = r11.mBuilder
                long r0 = r0.getWhenIfShowing()
                java.lang.String r2 = "setTime"
                r7.setLong(r14, r2, r0)
                goto L_0x01b4
            L_0x01b3:
                r13 = r10
            L_0x01b4:
                int r14 = androidx.core.R.id.right_side
                if (r13 == 0) goto L_0x01ba
                r13 = r8
                goto L_0x01bb
            L_0x01ba:
                r13 = r9
            L_0x01bb:
                r7.setViewVisibility(r14, r13)
                int r13 = androidx.core.R.id.line3
                if (r12 == 0) goto L_0x01c3
                goto L_0x01c4
            L_0x01c3:
                r8 = r9
            L_0x01c4:
                r7.setViewVisibility(r13, r8)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationCompat.Style.applyStandardTemplate(boolean, int, boolean):android.widget.RemoteViews");
        }

        public Bitmap createColoredBitmap(int i, int i2) {
            return createColoredBitmap(i, i2, 0);
        }

        /* access modifiers changed from: package-private */
        public Bitmap createColoredBitmap(IconCompat iconCompat, int i) {
            return createColoredBitmap(iconCompat, i, 0);
        }

        private Bitmap createColoredBitmap(int i, int i2, int i3) {
            return createColoredBitmap(IconCompat.createWithResource(this.mBuilder.mContext, i), i2, i3);
        }

        private Bitmap createColoredBitmap(IconCompat iconCompat, int i, int i2) {
            Drawable loadDrawable = iconCompat.loadDrawable(this.mBuilder.mContext);
            int intrinsicWidth = i2 == 0 ? loadDrawable.getIntrinsicWidth() : i2;
            if (i2 == 0) {
                i2 = loadDrawable.getIntrinsicHeight();
            }
            Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i2, Bitmap.Config.ARGB_8888);
            loadDrawable.setBounds(0, 0, intrinsicWidth, i2);
            if (i != 0) {
                loadDrawable.mutate().setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
            }
            loadDrawable.draw(new Canvas(createBitmap));
            return createBitmap;
        }

        private Bitmap createIconWithBackground(int i, int i2, int i3, int i4) {
            int i5 = R.drawable.notification_icon_background;
            if (i4 == 0) {
                i4 = 0;
            }
            Bitmap createColoredBitmap = createColoredBitmap(i5, i4, i2);
            Canvas canvas = new Canvas(createColoredBitmap);
            Drawable mutate = this.mBuilder.mContext.getResources().getDrawable(i).mutate();
            mutate.setFilterBitmap(true);
            int i6 = (i2 - i3) / 2;
            int i7 = i3 + i6;
            mutate.setBounds(i6, i6, i7, i7);
            mutate.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
            mutate.draw(canvas);
            return createColoredBitmap;
        }

        public void buildIntoRemoteViews(RemoteViews remoteViews, RemoteViews remoteViews2) {
            hideNormalContent(remoteViews);
            remoteViews.removeAllViews(R.id.notification_main_column);
            remoteViews.addView(R.id.notification_main_column, remoteViews2.clone());
            remoteViews.setViewVisibility(R.id.notification_main_column, 0);
            RemoteViews remoteViews3 = remoteViews;
            remoteViews3.setViewPadding(R.id.notification_main_column_container, 0, calculateTopPadding(), 0, 0);
        }

        private void hideNormalContent(RemoteViews remoteViews) {
            remoteViews.setViewVisibility(R.id.title, 8);
            remoteViews.setViewVisibility(R.id.text2, 8);
            remoteViews.setViewVisibility(R.id.text, 8);
        }

        private int calculateTopPadding() {
            Resources resources = this.mBuilder.mContext.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_top_pad);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.notification_top_pad_large_text);
            float constrain = (constrain(resources.getConfiguration().fontScale, 1.0f, 1.3f) - 1.0f) / 0.29999995f;
            return Math.round(((1.0f - constrain) * ((float) dimensionPixelSize)) + (constrain * ((float) dimensionPixelSize2)));
        }

        static class Api24Impl {
            private Api24Impl() {
            }

            static void setChronometerCountDown(RemoteViews remoteViews, int i, boolean z) {
                remoteViews.setChronometerCountDown(i, z);
            }
        }
    }

    public static class BigPictureStyle extends Style {
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$BigPictureStyle";
        private IconCompat mBigLargeIcon;
        private boolean mBigLargeIconSet;
        private CharSequence mPictureContentDescription;
        private IconCompat mPictureIcon;
        private boolean mShowBigPictureWhenCollapsed;

        /* access modifiers changed from: protected */
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        public BigPictureStyle() {
        }

        public BigPictureStyle(Builder builder) {
            setBuilder(builder);
        }

        public BigPictureStyle setBigContentTitle(CharSequence charSequence) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        public BigPictureStyle setSummaryText(CharSequence charSequence) {
            this.mSummaryText = Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }

        public BigPictureStyle setContentDescription(CharSequence charSequence) {
            this.mPictureContentDescription = charSequence;
            return this;
        }

        public BigPictureStyle bigPicture(Bitmap bitmap) {
            this.mPictureIcon = bitmap == null ? null : IconCompat.createWithBitmap(bitmap);
            return this;
        }

        public BigPictureStyle bigPicture(Icon icon) {
            this.mPictureIcon = IconCompat.createFromIcon(icon);
            return this;
        }

        public BigPictureStyle showBigPictureWhenCollapsed(boolean z) {
            this.mShowBigPictureWhenCollapsed = z;
            return this;
        }

        public BigPictureStyle bigLargeIcon(Bitmap bitmap) {
            this.mBigLargeIcon = bitmap == null ? null : IconCompat.createWithBitmap(bitmap);
            this.mBigLargeIconSet = true;
            return this;
        }

        public BigPictureStyle bigLargeIcon(Icon icon) {
            this.mBigLargeIcon = icon == null ? null : IconCompat.createFromIcon(icon);
            this.mBigLargeIconSet = true;
            return this;
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.BigPictureStyle bigContentTitle = new Notification.BigPictureStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.mBigContentTitle);
            Context context = null;
            if (this.mPictureIcon != null) {
                if (Build.VERSION.SDK_INT >= 31) {
                    Api31Impl.setBigPicture(bigContentTitle, this.mPictureIcon.toIcon(notificationBuilderWithBuilderAccessor instanceof NotificationCompatBuilder ? ((NotificationCompatBuilder) notificationBuilderWithBuilderAccessor).getContext() : null));
                } else if (this.mPictureIcon.getType() == 1) {
                    bigContentTitle = bigContentTitle.bigPicture(this.mPictureIcon.getBitmap());
                }
            }
            if (this.mBigLargeIconSet) {
                if (this.mBigLargeIcon == null) {
                    Bitmap bitmap = null;
                    bigContentTitle.bigLargeIcon((Bitmap) null);
                } else {
                    if (notificationBuilderWithBuilderAccessor instanceof NotificationCompatBuilder) {
                        context = ((NotificationCompatBuilder) notificationBuilderWithBuilderAccessor).getContext();
                    }
                    Api23Impl.setBigLargeIcon(bigContentTitle, this.mBigLargeIcon.toIcon(context));
                }
            }
            if (this.mSummaryTextSet) {
                bigContentTitle.setSummaryText(this.mSummaryText);
            }
            if (Build.VERSION.SDK_INT >= 31) {
                Api31Impl.showBigPictureWhenCollapsed(bigContentTitle, this.mShowBigPictureWhenCollapsed);
                Api31Impl.setContentDescription(bigContentTitle, this.mPictureContentDescription);
            }
        }

        /* access modifiers changed from: protected */
        public void restoreFromCompatExtras(Bundle bundle) {
            super.restoreFromCompatExtras(bundle);
            if (bundle.containsKey(NotificationCompat.EXTRA_LARGE_ICON_BIG)) {
                this.mBigLargeIcon = asIconCompat(bundle.getParcelable(NotificationCompat.EXTRA_LARGE_ICON_BIG));
                this.mBigLargeIconSet = true;
            }
            this.mPictureIcon = getPictureIcon(bundle);
            this.mShowBigPictureWhenCollapsed = bundle.getBoolean(NotificationCompat.EXTRA_SHOW_BIG_PICTURE_WHEN_COLLAPSED);
        }

        public static IconCompat getPictureIcon(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            Parcelable parcelable = bundle.getParcelable(NotificationCompat.EXTRA_PICTURE);
            if (parcelable != null) {
                return asIconCompat(parcelable);
            }
            return asIconCompat(bundle.getParcelable(NotificationCompat.EXTRA_PICTURE_ICON));
        }

        private static IconCompat asIconCompat(Parcelable parcelable) {
            if (parcelable == null) {
                return null;
            }
            if (parcelable instanceof Icon) {
                return IconCompat.createFromIcon((Icon) parcelable);
            }
            if (parcelable instanceof Bitmap) {
                return IconCompat.createWithBitmap((Bitmap) parcelable);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void clearCompatExtraKeys(Bundle bundle) {
            super.clearCompatExtraKeys(bundle);
            bundle.remove(NotificationCompat.EXTRA_LARGE_ICON_BIG);
            bundle.remove(NotificationCompat.EXTRA_PICTURE);
            bundle.remove(NotificationCompat.EXTRA_PICTURE_ICON);
            bundle.remove(NotificationCompat.EXTRA_SHOW_BIG_PICTURE_WHEN_COLLAPSED);
        }

        private static class Api23Impl {
            private Api23Impl() {
            }

            static void setBigLargeIcon(Notification.BigPictureStyle bigPictureStyle, Icon icon) {
                bigPictureStyle.bigLargeIcon(icon);
            }
        }

        private static class Api31Impl {
            private Api31Impl() {
            }

            static void showBigPictureWhenCollapsed(Notification.BigPictureStyle bigPictureStyle, boolean z) {
                bigPictureStyle.showBigPictureWhenCollapsed(z);
            }

            static void setContentDescription(Notification.BigPictureStyle bigPictureStyle, CharSequence charSequence) {
                bigPictureStyle.setContentDescription(charSequence);
            }

            static void setBigPicture(Notification.BigPictureStyle bigPictureStyle, Icon icon) {
                bigPictureStyle.bigPicture(icon);
            }
        }
    }

    public static class BigTextStyle extends Style {
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$BigTextStyle";
        private CharSequence mBigText;

        /* access modifiers changed from: protected */
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        public BigTextStyle() {
        }

        public BigTextStyle(Builder builder) {
            setBuilder(builder);
        }

        public BigTextStyle setBigContentTitle(CharSequence charSequence) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        public BigTextStyle setSummaryText(CharSequence charSequence) {
            this.mSummaryText = Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }

        public BigTextStyle bigText(CharSequence charSequence) {
            this.mBigText = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.BigTextStyle bigText = new Notification.BigTextStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
            if (this.mSummaryTextSet) {
                bigText.setSummaryText(this.mSummaryText);
            }
        }

        /* access modifiers changed from: protected */
        public void restoreFromCompatExtras(Bundle bundle) {
            super.restoreFromCompatExtras(bundle);
            this.mBigText = bundle.getCharSequence(NotificationCompat.EXTRA_BIG_TEXT);
        }

        public void addCompatExtras(Bundle bundle) {
            super.addCompatExtras(bundle);
        }

        /* access modifiers changed from: protected */
        public void clearCompatExtraKeys(Bundle bundle) {
            super.clearCompatExtraKeys(bundle);
            bundle.remove(NotificationCompat.EXTRA_BIG_TEXT);
        }
    }

    public static class MessagingStyle extends Style {
        public static final int MAXIMUM_RETAINED_MESSAGES = 25;
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$MessagingStyle";
        private CharSequence mConversationTitle;
        private final List<Message> mHistoricMessages = new ArrayList();
        private Boolean mIsGroupConversation;
        private final List<Message> mMessages = new ArrayList();
        private Person mUser;

        /* access modifiers changed from: protected */
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        MessagingStyle() {
        }

        @Deprecated
        public MessagingStyle(CharSequence charSequence) {
            this.mUser = new Person.Builder().setName(charSequence).build();
        }

        public MessagingStyle(Person person) {
            if (!TextUtils.isEmpty(person.getName())) {
                this.mUser = person;
                return;
            }
            throw new IllegalArgumentException("User's name must not be empty.");
        }

        @Deprecated
        public CharSequence getUserDisplayName() {
            return this.mUser.getName();
        }

        public Person getUser() {
            return this.mUser;
        }

        public MessagingStyle setConversationTitle(CharSequence charSequence) {
            this.mConversationTitle = charSequence;
            return this;
        }

        public CharSequence getConversationTitle() {
            return this.mConversationTitle;
        }

        @Deprecated
        public MessagingStyle addMessage(CharSequence charSequence, long j, CharSequence charSequence2) {
            this.mMessages.add(new Message(charSequence, j, new Person.Builder().setName(charSequence2).build()));
            if (this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }

        public MessagingStyle addMessage(CharSequence charSequence, long j, Person person) {
            addMessage(new Message(charSequence, j, person));
            return this;
        }

        public MessagingStyle addMessage(Message message) {
            if (message != null) {
                this.mMessages.add(message);
                if (this.mMessages.size() > 25) {
                    this.mMessages.remove(0);
                }
            }
            return this;
        }

        public MessagingStyle addHistoricMessage(Message message) {
            if (message != null) {
                this.mHistoricMessages.add(message);
                if (this.mHistoricMessages.size() > 25) {
                    this.mHistoricMessages.remove(0);
                }
            }
            return this;
        }

        public List<Message> getMessages() {
            return this.mMessages;
        }

        public List<Message> getHistoricMessages() {
            return this.mHistoricMessages;
        }

        public MessagingStyle setGroupConversation(boolean z) {
            this.mIsGroupConversation = Boolean.valueOf(z);
            return this;
        }

        public boolean isGroupConversation() {
            if (this.mBuilder == null || this.mBuilder.mContext.getApplicationInfo().targetSdkVersion >= 28 || this.mIsGroupConversation != null) {
                Boolean bool = this.mIsGroupConversation;
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } else if (this.mConversationTitle != null) {
                return true;
            } else {
                return false;
            }
        }

        public static MessagingStyle extractMessagingStyleFromNotification(Notification notification) {
            Style extractStyleFromNotification = Style.extractStyleFromNotification(notification);
            if (extractStyleFromNotification instanceof MessagingStyle) {
                return (MessagingStyle) extractStyleFromNotification;
            }
            return null;
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.MessagingStyle messagingStyle;
            setGroupConversation(isGroupConversation());
            if (Build.VERSION.SDK_INT >= 28) {
                messagingStyle = Api28Impl.createMessagingStyle(this.mUser.toAndroidPerson());
            } else {
                messagingStyle = Api24Impl.createMessagingStyle(this.mUser.getName());
            }
            for (Message androidMessage : this.mMessages) {
                Notification.MessagingStyle messagingStyle2 = messagingStyle;
                Api24Impl.addMessage(messagingStyle, androidMessage.toAndroidMessage());
            }
            for (Message androidMessage2 : this.mHistoricMessages) {
                Notification.MessagingStyle messagingStyle3 = messagingStyle;
                Api26Impl.addHistoricMessage(messagingStyle, androidMessage2.toAndroidMessage());
            }
            if (this.mIsGroupConversation.booleanValue() || Build.VERSION.SDK_INT >= 28) {
                Notification.MessagingStyle messagingStyle4 = messagingStyle;
                Api24Impl.setConversationTitle(messagingStyle, this.mConversationTitle);
            }
            if (Build.VERSION.SDK_INT >= 28) {
                Notification.MessagingStyle messagingStyle5 = messagingStyle;
                Api28Impl.setGroupConversation(messagingStyle, this.mIsGroupConversation.booleanValue());
            }
            messagingStyle.setBuilder(notificationBuilderWithBuilderAccessor.getBuilder());
        }

        private Message findLatestIncomingMessage() {
            for (int size = this.mMessages.size() - 1; size >= 0; size--) {
                Message message = this.mMessages.get(size);
                if (message.getPerson() != null && !TextUtils.isEmpty(message.getPerson().getName())) {
                    return message;
                }
            }
            if (this.mMessages.isEmpty()) {
                return null;
            }
            List<Message> list = this.mMessages;
            return list.get(list.size() - 1);
        }

        private boolean hasMessagesWithoutSender() {
            for (int size = this.mMessages.size() - 1; size >= 0; size--) {
                Message message = this.mMessages.get(size);
                if (message.getPerson() != null && message.getPerson().getName() == null) {
                    return true;
                }
            }
            return false;
        }

        private CharSequence makeMessageLine(Message message) {
            BidiFormatter instance = BidiFormatter.getInstance();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            CharSequence charSequence = "";
            CharSequence name = message.getPerson() == null ? charSequence : message.getPerson().getName();
            boolean isEmpty = TextUtils.isEmpty(name);
            int i = ViewCompat.MEASURED_STATE_MASK;
            if (isEmpty) {
                name = this.mUser.getName();
                if (this.mBuilder.getColor() != 0) {
                    i = this.mBuilder.getColor();
                }
            }
            CharSequence unicodeWrap = instance.unicodeWrap(name);
            spannableStringBuilder.append(unicodeWrap);
            spannableStringBuilder.setSpan(makeFontColorSpan(i), spannableStringBuilder.length() - unicodeWrap.length(), spannableStringBuilder.length(), 33);
            if (message.getText() != null) {
                charSequence = message.getText();
            }
            spannableStringBuilder.append(FileSpecKt.DEFAULT_INDENT).append(instance.unicodeWrap(charSequence));
            return spannableStringBuilder;
        }

        private TextAppearanceSpan makeFontColorSpan(int i) {
            return new TextAppearanceSpan((String) null, 0, 0, ColorStateList.valueOf(i), (ColorStateList) null);
        }

        public void addCompatExtras(Bundle bundle) {
            super.addCompatExtras(bundle);
            bundle.putCharSequence(NotificationCompat.EXTRA_SELF_DISPLAY_NAME, this.mUser.getName());
            bundle.putBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER, this.mUser.toBundle());
            bundle.putCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE, this.mConversationTitle);
            if (this.mConversationTitle != null && this.mIsGroupConversation.booleanValue()) {
                bundle.putCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE, this.mConversationTitle);
            }
            if (!this.mMessages.isEmpty()) {
                bundle.putParcelableArray(NotificationCompat.EXTRA_MESSAGES, Message.getBundleArrayForMessages(this.mMessages));
            }
            if (!this.mHistoricMessages.isEmpty()) {
                bundle.putParcelableArray(NotificationCompat.EXTRA_HISTORIC_MESSAGES, Message.getBundleArrayForMessages(this.mHistoricMessages));
            }
            Boolean bool = this.mIsGroupConversation;
            if (bool != null) {
                bundle.putBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION, bool.booleanValue());
            }
        }

        /* access modifiers changed from: protected */
        public void restoreFromCompatExtras(Bundle bundle) {
            super.restoreFromCompatExtras(bundle);
            this.mMessages.clear();
            if (bundle.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) {
                this.mUser = Person.fromBundle(bundle.getBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER));
            } else {
                this.mUser = new Person.Builder().setName(bundle.getString(NotificationCompat.EXTRA_SELF_DISPLAY_NAME)).build();
            }
            CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE);
            this.mConversationTitle = charSequence;
            if (charSequence == null) {
                this.mConversationTitle = bundle.getCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE);
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray(NotificationCompat.EXTRA_MESSAGES);
            if (parcelableArray != null) {
                this.mMessages.addAll(Message.getMessagesFromBundleArray(parcelableArray));
            }
            Parcelable[] parcelableArray2 = bundle.getParcelableArray(NotificationCompat.EXTRA_HISTORIC_MESSAGES);
            if (parcelableArray2 != null) {
                this.mHistoricMessages.addAll(Message.getMessagesFromBundleArray(parcelableArray2));
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION)) {
                this.mIsGroupConversation = Boolean.valueOf(bundle.getBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION));
            }
        }

        /* access modifiers changed from: protected */
        public void clearCompatExtraKeys(Bundle bundle) {
            super.clearCompatExtraKeys(bundle);
            bundle.remove(NotificationCompat.EXTRA_MESSAGING_STYLE_USER);
            bundle.remove(NotificationCompat.EXTRA_SELF_DISPLAY_NAME);
            bundle.remove(NotificationCompat.EXTRA_CONVERSATION_TITLE);
            bundle.remove(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE);
            bundle.remove(NotificationCompat.EXTRA_MESSAGES);
            bundle.remove(NotificationCompat.EXTRA_HISTORIC_MESSAGES);
            bundle.remove(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION);
        }

        public static final class Message {
            static final String KEY_DATA_MIME_TYPE = "type";
            static final String KEY_DATA_URI = "uri";
            static final String KEY_EXTRAS_BUNDLE = "extras";
            static final String KEY_NOTIFICATION_PERSON = "sender_person";
            static final String KEY_PERSON = "person";
            static final String KEY_SENDER = "sender";
            static final String KEY_TEXT = "text";
            static final String KEY_TIMESTAMP = "time";
            private String mDataMimeType;
            private Uri mDataUri;
            private Bundle mExtras;
            private final Person mPerson;
            private final CharSequence mText;
            private final long mTimestamp;

            public Message(CharSequence charSequence, long j, Person person) {
                this.mExtras = new Bundle();
                this.mText = charSequence;
                this.mTimestamp = j;
                this.mPerson = person;
            }

            @Deprecated
            public Message(CharSequence charSequence, long j, CharSequence charSequence2) {
                this(charSequence, j, new Person.Builder().setName(charSequence2).build());
            }

            public Message setData(String str, Uri uri) {
                this.mDataMimeType = str;
                this.mDataUri = uri;
                return this;
            }

            public CharSequence getText() {
                return this.mText;
            }

            public long getTimestamp() {
                return this.mTimestamp;
            }

            public Bundle getExtras() {
                return this.mExtras;
            }

            @Deprecated
            public CharSequence getSender() {
                Person person = this.mPerson;
                if (person == null) {
                    return null;
                }
                return person.getName();
            }

            public Person getPerson() {
                return this.mPerson;
            }

            public String getDataMimeType() {
                return this.mDataMimeType;
            }

            public Uri getDataUri() {
                return this.mDataUri;
            }

            private Bundle toBundle() {
                Bundle bundle = new Bundle();
                CharSequence charSequence = this.mText;
                if (charSequence != null) {
                    bundle.putCharSequence("text", charSequence);
                }
                bundle.putLong(KEY_TIMESTAMP, this.mTimestamp);
                Person person = this.mPerson;
                if (person != null) {
                    bundle.putCharSequence(KEY_SENDER, person.getName());
                    if (Build.VERSION.SDK_INT >= 28) {
                        bundle.putParcelable(KEY_NOTIFICATION_PERSON, Api28Impl.castToParcelable(this.mPerson.toAndroidPerson()));
                    } else {
                        bundle.putBundle(KEY_PERSON, this.mPerson.toBundle());
                    }
                }
                String str = this.mDataMimeType;
                if (str != null) {
                    bundle.putString("type", str);
                }
                Uri uri = this.mDataUri;
                if (uri != null) {
                    bundle.putParcelable("uri", uri);
                }
                Bundle bundle2 = this.mExtras;
                if (bundle2 != null) {
                    bundle.putBundle(KEY_EXTRAS_BUNDLE, bundle2);
                }
                return bundle;
            }

            static Bundle[] getBundleArrayForMessages(List<Message> list) {
                Bundle[] bundleArr = new Bundle[list.size()];
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    bundleArr[i] = list.get(i).toBundle();
                }
                return bundleArr;
            }

            static List<Message> getMessagesFromBundleArray(Parcelable[] parcelableArr) {
                Message messageFromBundle;
                ArrayList arrayList = new ArrayList(parcelableArr.length);
                for (Bundle bundle : parcelableArr) {
                    if ((bundle instanceof Bundle) && (messageFromBundle = getMessageFromBundle(bundle)) != null) {
                        arrayList.add(messageFromBundle);
                    }
                }
                return arrayList;
            }

            static Message getMessageFromBundle(Bundle bundle) {
                Person person;
                try {
                    if (bundle.containsKey("text")) {
                        if (bundle.containsKey(KEY_TIMESTAMP)) {
                            if (bundle.containsKey(KEY_PERSON)) {
                                person = Person.fromBundle(bundle.getBundle(KEY_PERSON));
                            } else if (!bundle.containsKey(KEY_NOTIFICATION_PERSON) || Build.VERSION.SDK_INT < 28) {
                                person = bundle.containsKey(KEY_SENDER) ? new Person.Builder().setName(bundle.getCharSequence(KEY_SENDER)).build() : null;
                            } else {
                                person = Person.fromAndroidPerson((android.app.Person) bundle.getParcelable(KEY_NOTIFICATION_PERSON));
                            }
                            Message message = new Message(bundle.getCharSequence("text"), bundle.getLong(KEY_TIMESTAMP), person);
                            if (bundle.containsKey("type") && bundle.containsKey("uri")) {
                                message.setData(bundle.getString("type"), (Uri) bundle.getParcelable("uri"));
                            }
                            if (bundle.containsKey(KEY_EXTRAS_BUNDLE)) {
                                message.getExtras().putAll(bundle.getBundle(KEY_EXTRAS_BUNDLE));
                            }
                            return message;
                        }
                    }
                } catch (ClassCastException unused) {
                }
                return null;
            }

            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: android.app.Person} */
            /* JADX WARNING: type inference failed for: r3v0 */
            /* JADX WARNING: type inference failed for: r3v1, types: [java.lang.CharSequence] */
            /* JADX WARNING: type inference failed for: r3v5 */
            /* JADX WARNING: type inference failed for: r3v6 */
            /* access modifiers changed from: package-private */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public android.app.Notification.MessagingStyle.Message toAndroidMessage() {
                /*
                    r6 = this;
                    androidx.core.app.Person r0 = r6.getPerson()
                    int r1 = android.os.Build.VERSION.SDK_INT
                    r2 = 28
                    r3 = 0
                    if (r1 < r2) goto L_0x001f
                    java.lang.CharSequence r1 = r6.getText()
                    long r4 = r6.getTimestamp()
                    if (r0 != 0) goto L_0x0016
                    goto L_0x001a
                L_0x0016:
                    android.app.Person r3 = r0.toAndroidPerson()
                L_0x001a:
                    android.app.Notification$MessagingStyle$Message r0 = androidx.core.app.NotificationCompat.MessagingStyle.Message.Api28Impl.createMessage(r1, r4, r3)
                    goto L_0x0032
                L_0x001f:
                    java.lang.CharSequence r1 = r6.getText()
                    long r4 = r6.getTimestamp()
                    if (r0 != 0) goto L_0x002a
                    goto L_0x002e
                L_0x002a:
                    java.lang.CharSequence r3 = r0.getName()
                L_0x002e:
                    android.app.Notification$MessagingStyle$Message r0 = androidx.core.app.NotificationCompat.MessagingStyle.Message.Api24Impl.createMessage(r1, r4, r3)
                L_0x0032:
                    java.lang.String r1 = r6.getDataMimeType()
                    if (r1 == 0) goto L_0x0043
                    java.lang.String r1 = r6.getDataMimeType()
                    android.net.Uri r2 = r6.getDataUri()
                    androidx.core.app.NotificationCompat.MessagingStyle.Message.Api24Impl.setData(r0, r1, r2)
                L_0x0043:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationCompat.MessagingStyle.Message.toAndroidMessage():android.app.Notification$MessagingStyle$Message");
            }

            static class Api24Impl {
                private Api24Impl() {
                }

                static Notification.MessagingStyle.Message createMessage(CharSequence charSequence, long j, CharSequence charSequence2) {
                    return new Notification.MessagingStyle.Message(charSequence, j, charSequence2);
                }

                static Notification.MessagingStyle.Message setData(Notification.MessagingStyle.Message message, String str, Uri uri) {
                    return message.setData(str, uri);
                }
            }

            static class Api28Impl {
                static Parcelable castToParcelable(android.app.Person person) {
                    return person;
                }

                private Api28Impl() {
                }

                static Notification.MessagingStyle.Message createMessage(CharSequence charSequence, long j, android.app.Person person) {
                    return new Notification.MessagingStyle.Message(charSequence, j, person);
                }
            }
        }

        static class Api24Impl {
            private Api24Impl() {
            }

            static Notification.MessagingStyle createMessagingStyle(CharSequence charSequence) {
                return new Notification.MessagingStyle(charSequence);
            }

            static Notification.MessagingStyle addMessage(Notification.MessagingStyle messagingStyle, Notification.MessagingStyle.Message message) {
                return messagingStyle.addMessage(message);
            }

            static Notification.MessagingStyle setConversationTitle(Notification.MessagingStyle messagingStyle, CharSequence charSequence) {
                return messagingStyle.setConversationTitle(charSequence);
            }
        }

        static class Api26Impl {
            private Api26Impl() {
            }

            static Notification.MessagingStyle addHistoricMessage(Notification.MessagingStyle messagingStyle, Notification.MessagingStyle.Message message) {
                return messagingStyle.addHistoricMessage(message);
            }
        }

        static class Api28Impl {
            private Api28Impl() {
            }

            static Notification.MessagingStyle createMessagingStyle(android.app.Person person) {
                return new Notification.MessagingStyle(person);
            }

            static Notification.MessagingStyle setGroupConversation(Notification.MessagingStyle messagingStyle, boolean z) {
                return messagingStyle.setGroupConversation(z);
            }
        }
    }

    public static class CallStyle extends Style {
        public static final int CALL_TYPE_INCOMING = 1;
        public static final int CALL_TYPE_ONGOING = 2;
        public static final int CALL_TYPE_SCREENING = 3;
        public static final int CALL_TYPE_UNKNOWN = 0;
        private static final String KEY_ACTION_PRIORITY = "key_action_priority";
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$CallStyle";
        private Integer mAnswerButtonColor;
        private PendingIntent mAnswerIntent;
        private int mCallType;
        private Integer mDeclineButtonColor;
        private PendingIntent mDeclineIntent;
        private PendingIntent mHangUpIntent;
        private boolean mIsVideo;
        private Person mPerson;
        private IconCompat mVerificationIcon;
        private CharSequence mVerificationText;

        @Retention(RetentionPolicy.SOURCE)
        public @interface CallType {
        }

        public boolean displayCustomViewInline() {
            return true;
        }

        /* access modifiers changed from: protected */
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        public CallStyle() {
        }

        public CallStyle(Builder builder) {
            setBuilder(builder);
        }

        public static CallStyle forIncomingCall(Person person, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
            return new CallStyle(1, person, (PendingIntent) null, (PendingIntent) Objects.requireNonNull(pendingIntent, "declineIntent is required"), (PendingIntent) Objects.requireNonNull(pendingIntent2, "answerIntent is required"));
        }

        public static CallStyle forOngoingCall(Person person, PendingIntent pendingIntent) {
            return new CallStyle(2, person, (PendingIntent) Objects.requireNonNull(pendingIntent, "hangUpIntent is required"), (PendingIntent) null, (PendingIntent) null);
        }

        public static CallStyle forScreeningCall(Person person, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
            return new CallStyle(3, person, (PendingIntent) Objects.requireNonNull(pendingIntent, "hangUpIntent is required"), (PendingIntent) null, (PendingIntent) Objects.requireNonNull(pendingIntent2, "answerIntent is required"));
        }

        private CallStyle(int i, Person person, PendingIntent pendingIntent, PendingIntent pendingIntent2, PendingIntent pendingIntent3) {
            if (person == null || TextUtils.isEmpty(person.getName())) {
                throw new IllegalArgumentException("person must have a non-empty a name");
            }
            this.mCallType = i;
            this.mPerson = person;
            this.mAnswerIntent = pendingIntent3;
            this.mDeclineIntent = pendingIntent2;
            this.mHangUpIntent = pendingIntent;
        }

        public CallStyle setIsVideo(boolean z) {
            this.mIsVideo = z;
            return this;
        }

        public CallStyle setVerificationIcon(Icon icon) {
            this.mVerificationIcon = icon == null ? null : IconCompat.createFromIcon(icon);
            return this;
        }

        public CallStyle setVerificationIcon(Bitmap bitmap) {
            this.mVerificationIcon = IconCompat.createWithBitmap(bitmap);
            return this;
        }

        public CallStyle setVerificationText(CharSequence charSequence) {
            this.mVerificationText = charSequence;
            return this;
        }

        public CallStyle setAnswerButtonColorHint(int i) {
            this.mAnswerButtonColor = Integer.valueOf(i);
            return this;
        }

        public CallStyle setDeclineButtonColorHint(int i) {
            this.mDeclineButtonColor = Integer.valueOf(i);
            return this;
        }

        /* access modifiers changed from: protected */
        public void restoreFromCompatExtras(Bundle bundle) {
            super.restoreFromCompatExtras(bundle);
            this.mCallType = bundle.getInt(NotificationCompat.EXTRA_CALL_TYPE);
            this.mIsVideo = bundle.getBoolean(NotificationCompat.EXTRA_CALL_IS_VIDEO);
            if (Build.VERSION.SDK_INT >= 28 && bundle.containsKey(NotificationCompat.EXTRA_CALL_PERSON)) {
                this.mPerson = Person.fromAndroidPerson((android.app.Person) bundle.getParcelable(NotificationCompat.EXTRA_CALL_PERSON));
            } else if (bundle.containsKey(NotificationCompat.EXTRA_CALL_PERSON_COMPAT)) {
                this.mPerson = Person.fromBundle(bundle.getBundle(NotificationCompat.EXTRA_CALL_PERSON_COMPAT));
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_VERIFICATION_ICON)) {
                this.mVerificationIcon = IconCompat.createFromIcon((Icon) bundle.getParcelable(NotificationCompat.EXTRA_VERIFICATION_ICON));
            } else if (bundle.containsKey(NotificationCompat.EXTRA_VERIFICATION_ICON_COMPAT)) {
                this.mVerificationIcon = IconCompat.createFromBundle(bundle.getBundle(NotificationCompat.EXTRA_VERIFICATION_ICON_COMPAT));
            }
            this.mVerificationText = bundle.getCharSequence(NotificationCompat.EXTRA_VERIFICATION_TEXT);
            this.mAnswerIntent = (PendingIntent) bundle.getParcelable(NotificationCompat.EXTRA_ANSWER_INTENT);
            this.mDeclineIntent = (PendingIntent) bundle.getParcelable(NotificationCompat.EXTRA_DECLINE_INTENT);
            this.mHangUpIntent = (PendingIntent) bundle.getParcelable(NotificationCompat.EXTRA_HANG_UP_INTENT);
            Integer num = null;
            this.mAnswerButtonColor = bundle.containsKey(NotificationCompat.EXTRA_ANSWER_COLOR) ? Integer.valueOf(bundle.getInt(NotificationCompat.EXTRA_ANSWER_COLOR)) : null;
            if (bundle.containsKey(NotificationCompat.EXTRA_DECLINE_COLOR)) {
                num = Integer.valueOf(bundle.getInt(NotificationCompat.EXTRA_DECLINE_COLOR));
            }
            this.mDeclineButtonColor = num;
        }

        public void addCompatExtras(Bundle bundle) {
            super.addCompatExtras(bundle);
            bundle.putInt(NotificationCompat.EXTRA_CALL_TYPE, this.mCallType);
            bundle.putBoolean(NotificationCompat.EXTRA_CALL_IS_VIDEO, this.mIsVideo);
            if (this.mPerson != null) {
                if (Build.VERSION.SDK_INT >= 28) {
                    bundle.putParcelable(NotificationCompat.EXTRA_CALL_PERSON, Api28Impl.castToParcelable(this.mPerson.toAndroidPerson()));
                } else {
                    bundle.putParcelable(NotificationCompat.EXTRA_CALL_PERSON_COMPAT, this.mPerson.toBundle());
                }
            }
            if (this.mVerificationIcon != null) {
                bundle.putParcelable(NotificationCompat.EXTRA_VERIFICATION_ICON, Api23Impl.castToParcelable(this.mVerificationIcon.toIcon(this.mBuilder.mContext)));
            }
            bundle.putCharSequence(NotificationCompat.EXTRA_VERIFICATION_TEXT, this.mVerificationText);
            bundle.putParcelable(NotificationCompat.EXTRA_ANSWER_INTENT, this.mAnswerIntent);
            bundle.putParcelable(NotificationCompat.EXTRA_DECLINE_INTENT, this.mDeclineIntent);
            bundle.putParcelable(NotificationCompat.EXTRA_HANG_UP_INTENT, this.mHangUpIntent);
            Integer num = this.mAnswerButtonColor;
            if (num != null) {
                bundle.putInt(NotificationCompat.EXTRA_ANSWER_COLOR, num.intValue());
            }
            Integer num2 = this.mDeclineButtonColor;
            if (num2 != null) {
                bundle.putInt(NotificationCompat.EXTRA_DECLINE_COLOR, num2.intValue());
            }
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.CallStyle callStyle = null;
            if (Build.VERSION.SDK_INT >= 31) {
                int i = this.mCallType;
                if (i == 1) {
                    callStyle = Api31Impl.forIncomingCall(this.mPerson.toAndroidPerson(), this.mDeclineIntent, this.mAnswerIntent);
                } else if (i == 2) {
                    callStyle = Api31Impl.forOngoingCall(this.mPerson.toAndroidPerson(), this.mHangUpIntent);
                } else if (i == 3) {
                    callStyle = Api31Impl.forScreeningCall(this.mPerson.toAndroidPerson(), this.mHangUpIntent, this.mAnswerIntent);
                } else if (Log.isLoggable(NotificationCompat.TAG, 3)) {
                    Log.d(NotificationCompat.TAG, "Unrecognized call type in CallStyle: " + String.valueOf(this.mCallType));
                }
                if (callStyle != null) {
                    callStyle.setBuilder(notificationBuilderWithBuilderAccessor.getBuilder());
                    Integer num = this.mAnswerButtonColor;
                    if (num != null) {
                        Api31Impl.setAnswerButtonColorHint(callStyle, num.intValue());
                    }
                    Integer num2 = this.mDeclineButtonColor;
                    if (num2 != null) {
                        Api31Impl.setDeclineButtonColorHint(callStyle, num2.intValue());
                    }
                    Api31Impl.setVerificationText(callStyle, this.mVerificationText);
                    IconCompat iconCompat = this.mVerificationIcon;
                    if (iconCompat != null) {
                        Api31Impl.setVerificationIcon(callStyle, iconCompat.toIcon(this.mBuilder.mContext));
                    }
                    Api31Impl.setIsVideo(callStyle, this.mIsVideo);
                    return;
                }
                return;
            }
            Notification.Builder builder = notificationBuilderWithBuilderAccessor.getBuilder();
            Person person = this.mPerson;
            builder.setContentTitle(person != null ? person.getName() : null);
            if (this.mBuilder.mExtras != null && this.mBuilder.mExtras.containsKey(NotificationCompat.EXTRA_TEXT)) {
                callStyle = this.mBuilder.mExtras.getCharSequence(NotificationCompat.EXTRA_TEXT);
            }
            if (callStyle == null) {
                callStyle = getDefaultText();
            }
            builder.setContentText(callStyle);
            if (this.mPerson != null) {
                if (this.mPerson.getIcon() != null) {
                    Api23Impl.setLargeIcon(builder, this.mPerson.getIcon().toIcon(this.mBuilder.mContext));
                }
                if (Build.VERSION.SDK_INT >= 28) {
                    Api28Impl.addPerson(builder, this.mPerson.toAndroidPerson());
                } else {
                    Api21Impl.addPerson(builder, this.mPerson.getUri());
                }
            }
            Api21Impl.setCategory(builder, NotificationCompat.CATEGORY_CALL);
        }

        private String getDefaultText() {
            int i = this.mCallType;
            if (i == 1) {
                return this.mBuilder.mContext.getResources().getString(R.string.call_notification_incoming_text);
            }
            if (i == 2) {
                return this.mBuilder.mContext.getResources().getString(R.string.call_notification_ongoing_text);
            }
            if (i != 3) {
                return null;
            }
            return this.mBuilder.mContext.getResources().getString(R.string.call_notification_screening_text);
        }

        private Action makeNegativeAction() {
            int i = R.drawable.ic_call_decline_low;
            int i2 = R.drawable.ic_call_decline;
            if (this.mDeclineIntent == null) {
                return makeAction(i2, R.string.call_notification_hang_up_action, this.mDeclineButtonColor, R.color.call_notification_decline_color, this.mHangUpIntent);
            }
            return makeAction(i2, R.string.call_notification_decline_action, this.mDeclineButtonColor, R.color.call_notification_decline_color, this.mDeclineIntent);
        }

        private Action makeAnswerAction() {
            int i;
            int i2 = R.drawable.ic_call_answer_video_low;
            int i3 = R.drawable.ic_call_answer_low;
            int i4 = R.drawable.ic_call_answer_video;
            int i5 = R.drawable.ic_call_answer;
            if (this.mAnswerIntent == null) {
                return null;
            }
            boolean z = this.mIsVideo;
            int i6 = z ? i4 : i5;
            if (z) {
                i = R.string.call_notification_answer_video_action;
            } else {
                i = R.string.call_notification_answer_action;
            }
            return makeAction(i6, i, this.mAnswerButtonColor, R.color.call_notification_answer_color, this.mAnswerIntent);
        }

        private Action makeAction(int i, int i2, Integer num, int i3, PendingIntent pendingIntent) {
            if (num == null) {
                num = Integer.valueOf(ContextCompat.getColor(this.mBuilder.mContext, i3));
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append(this.mBuilder.mContext.getResources().getString(i2));
            spannableStringBuilder.setSpan(new ForegroundColorSpan(num.intValue()), 0, spannableStringBuilder.length(), 18);
            Action build = new Action.Builder(IconCompat.createWithResource(this.mBuilder.mContext, i), (CharSequence) spannableStringBuilder, pendingIntent).build();
            build.getExtras().putBoolean(KEY_ACTION_PRIORITY, true);
            return build;
        }

        private boolean isActionAddedByCallStyle(Action action) {
            return action != null && action.getExtras().getBoolean(KEY_ACTION_PRIORITY);
        }

        public ArrayList<Action> getActionsListWithSystemActions() {
            Action makeNegativeAction = makeNegativeAction();
            Action makeAnswerAction = makeAnswerAction();
            ArrayList<Action> arrayList = new ArrayList<>(3);
            arrayList.add(makeNegativeAction);
            ArrayList<Action> arrayList2 = this.mBuilder.mActions;
            int i = 2;
            if (arrayList2 != null) {
                for (Action next : arrayList2) {
                    if (next.isContextual()) {
                        arrayList.add(next);
                    } else if (!isActionAddedByCallStyle(next) && i > 1) {
                        arrayList.add(next);
                        i--;
                    }
                    if (makeAnswerAction != null && i == 1) {
                        arrayList.add(makeAnswerAction);
                        i--;
                    }
                }
            }
            if (makeAnswerAction != null && i >= 1) {
                arrayList.add(makeAnswerAction);
            }
            return arrayList;
        }

        static class Api20Impl {
            private Api20Impl() {
            }

            static Notification.Action build(Notification.Action.Builder builder) {
                return builder.build();
            }

            static Notification.Action.Builder createActionBuilder(int i, CharSequence charSequence, PendingIntent pendingIntent) {
                return new Notification.Action.Builder(i, charSequence, pendingIntent);
            }

            static Notification.Action.Builder addExtras(Notification.Action.Builder builder, Bundle bundle) {
                return builder.addExtras(bundle);
            }

            static Notification.Action.Builder addRemoteInput(Notification.Action.Builder builder, RemoteInput remoteInput) {
                return builder.addRemoteInput(remoteInput);
            }
        }

        static class Api21Impl {
            private Api21Impl() {
            }

            static Notification.Builder addPerson(Notification.Builder builder, String str) {
                return builder.addPerson(str);
            }

            static Notification.Builder setCategory(Notification.Builder builder, String str) {
                return builder.setCategory(str);
            }
        }

        static class Api23Impl {
            static Parcelable castToParcelable(Icon icon) {
                return icon;
            }

            private Api23Impl() {
            }

            static void setLargeIcon(Notification.Builder builder, Icon icon) {
                builder.setLargeIcon(icon);
            }

            static Notification.Action.Builder createActionBuilder(Icon icon, CharSequence charSequence, PendingIntent pendingIntent) {
                return new Notification.Action.Builder(icon, charSequence, pendingIntent);
            }
        }

        static class Api24Impl {
            private Api24Impl() {
            }

            static Notification.Action.Builder setAllowGeneratedReplies(Notification.Action.Builder builder, boolean z) {
                return builder.setAllowGeneratedReplies(z);
            }
        }

        static class Api28Impl {
            static Parcelable castToParcelable(android.app.Person person) {
                return person;
            }

            private Api28Impl() {
            }

            static Notification.Builder addPerson(Notification.Builder builder, android.app.Person person) {
                return builder.addPerson(person);
            }
        }

        static class Api31Impl {
            private Api31Impl() {
            }

            static Notification.CallStyle forIncomingCall(android.app.Person person, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
                return Notification.CallStyle.forIncomingCall(person, pendingIntent, pendingIntent2);
            }

            static Notification.CallStyle forOngoingCall(android.app.Person person, PendingIntent pendingIntent) {
                return Notification.CallStyle.forOngoingCall(person, pendingIntent);
            }

            static Notification.CallStyle forScreeningCall(android.app.Person person, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
                return Notification.CallStyle.forScreeningCall(person, pendingIntent, pendingIntent2);
            }

            static Notification.CallStyle setIsVideo(Notification.CallStyle callStyle, boolean z) {
                return callStyle.setIsVideo(z);
            }

            static Notification.CallStyle setVerificationIcon(Notification.CallStyle callStyle, Icon icon) {
                return callStyle.setVerificationIcon(icon);
            }

            static Notification.CallStyle setVerificationText(Notification.CallStyle callStyle, CharSequence charSequence) {
                return callStyle.setVerificationText(charSequence);
            }

            static Notification.CallStyle setAnswerButtonColorHint(Notification.CallStyle callStyle, int i) {
                return callStyle.setAnswerButtonColorHint(i);
            }

            static Notification.CallStyle setDeclineButtonColorHint(Notification.CallStyle callStyle, int i) {
                return callStyle.setDeclineButtonColorHint(i);
            }

            static Notification.Action.Builder setAuthenticationRequired(Notification.Action.Builder builder, boolean z) {
                return builder.setAuthenticationRequired(z);
            }
        }
    }

    public static class InboxStyle extends Style {
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$InboxStyle";
        private ArrayList<CharSequence> mTexts = new ArrayList<>();

        /* access modifiers changed from: protected */
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        public InboxStyle() {
        }

        public InboxStyle(Builder builder) {
            setBuilder(builder);
        }

        public InboxStyle setBigContentTitle(CharSequence charSequence) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        public InboxStyle setSummaryText(CharSequence charSequence) {
            this.mSummaryText = Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }

        public InboxStyle addLine(CharSequence charSequence) {
            if (charSequence != null) {
                this.mTexts.add(Builder.limitCharSequenceLength(charSequence));
            }
            return this;
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.InboxStyle bigContentTitle = new Notification.InboxStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.mBigContentTitle);
            if (this.mSummaryTextSet) {
                bigContentTitle.setSummaryText(this.mSummaryText);
            }
            Iterator<CharSequence> it = this.mTexts.iterator();
            while (it.hasNext()) {
                bigContentTitle.addLine(it.next());
            }
        }

        /* access modifiers changed from: protected */
        public void restoreFromCompatExtras(Bundle bundle) {
            super.restoreFromCompatExtras(bundle);
            this.mTexts.clear();
            if (bundle.containsKey(NotificationCompat.EXTRA_TEXT_LINES)) {
                Collections.addAll(this.mTexts, bundle.getCharSequenceArray(NotificationCompat.EXTRA_TEXT_LINES));
            }
        }

        /* access modifiers changed from: protected */
        public void clearCompatExtraKeys(Bundle bundle) {
            super.clearCompatExtraKeys(bundle);
            bundle.remove(NotificationCompat.EXTRA_TEXT_LINES);
        }
    }

    public static class DecoratedCustomViewStyle extends Style {
        private static final int MAX_ACTION_BUTTONS = 3;
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$DecoratedCustomViewStyle";

        public boolean displayCustomViewInline() {
            return true;
        }

        /* access modifiers changed from: protected */
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            notificationBuilderWithBuilderAccessor.getBuilder().setStyle(Api24Impl.createDecoratedCustomViewStyle());
        }

        private RemoteViews createRemoteViews(RemoteViews remoteViews, boolean z) {
            int min;
            boolean z2 = true;
            int i = 0;
            RemoteViews applyStandardTemplate = applyStandardTemplate(true, R.layout.notification_template_custom_big, false);
            applyStandardTemplate.removeAllViews(R.id.actions);
            List<Action> nonContextualActions = getNonContextualActions(this.mBuilder.mActions);
            if (!z || nonContextualActions == null || (min = Math.min(nonContextualActions.size(), 3)) <= 0) {
                z2 = false;
            } else {
                for (int i2 = 0; i2 < min; i2++) {
                    applyStandardTemplate.addView(R.id.actions, generateActionButton(nonContextualActions.get(i2)));
                }
            }
            if (!z2) {
                i = 8;
            }
            applyStandardTemplate.setViewVisibility(R.id.actions, i);
            applyStandardTemplate.setViewVisibility(R.id.action_divider, i);
            buildIntoRemoteViews(applyStandardTemplate, remoteViews);
            return applyStandardTemplate;
        }

        private static List<Action> getNonContextualActions(List<Action> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Action next : list) {
                if (!next.isContextual()) {
                    arrayList.add(next);
                }
            }
            return arrayList;
        }

        private RemoteViews generateActionButton(Action action) {
            int i;
            boolean z = action.actionIntent == null;
            String packageName = this.mBuilder.mContext.getPackageName();
            if (z) {
                i = R.layout.notification_action_tombstone;
            } else {
                i = R.layout.notification_action;
            }
            RemoteViews remoteViews = new RemoteViews(packageName, i);
            IconCompat iconCompat = action.getIconCompat();
            if (iconCompat != null) {
                remoteViews.setImageViewBitmap(R.id.action_image, createColoredBitmap(iconCompat, R.color.notification_action_color_filter));
            }
            remoteViews.setTextViewText(R.id.action_text, action.title);
            if (!z) {
                remoteViews.setOnClickPendingIntent(R.id.action_container, action.actionIntent);
            }
            remoteViews.setContentDescription(R.id.action_container, action.title);
            return remoteViews;
        }

        static class Api24Impl {
            private Api24Impl() {
            }

            static Notification.Style createDecoratedCustomViewStyle() {
                return new Notification.DecoratedCustomViewStyle();
            }
        }
    }

    public static class Action {
        static final String EXTRA_SEMANTIC_ACTION = "android.support.action.semanticAction";
        static final String EXTRA_SHOWS_USER_INTERFACE = "android.support.action.showsUserInterface";
        public static final int SEMANTIC_ACTION_ARCHIVE = 5;
        public static final int SEMANTIC_ACTION_CALL = 10;
        public static final int SEMANTIC_ACTION_DELETE = 4;
        public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
        public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
        public static final int SEMANTIC_ACTION_MUTE = 6;
        public static final int SEMANTIC_ACTION_NONE = 0;
        public static final int SEMANTIC_ACTION_REPLY = 1;
        public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
        public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
        public static final int SEMANTIC_ACTION_UNMUTE = 7;
        public PendingIntent actionIntent;
        @Deprecated
        public int icon;
        private boolean mAllowGeneratedReplies;
        private boolean mAuthenticationRequired;
        private final RemoteInput[] mDataOnlyRemoteInputs;
        final Bundle mExtras;
        private IconCompat mIcon;
        private final boolean mIsContextual;
        private final RemoteInput[] mRemoteInputs;
        private final int mSemanticAction;
        boolean mShowsUserInterface;
        public CharSequence title;

        public interface Extender {
            Builder extend(Builder builder);
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface SemanticAction {
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public Action(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this(i != 0 ? IconCompat.createWithResource((Resources) null, "", i) : null, charSequence, pendingIntent);
        }

        public Action(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent) {
            this(iconCompat, charSequence, pendingIntent, new Bundle(), (RemoteInput[]) null, (RemoteInput[]) null, true, 0, true, false, false);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        Action(int i, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, RemoteInput[] remoteInputArr2, boolean z, int i2, boolean z2, boolean z3, boolean z4) {
            this(i != 0 ? IconCompat.createWithResource((Resources) null, "", i) : null, charSequence, pendingIntent, bundle, remoteInputArr, remoteInputArr2, z, i2, z2, z3, z4);
        }

        Action(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, RemoteInput[] remoteInputArr2, boolean z, int i, boolean z2, boolean z3, boolean z4) {
            this.mShowsUserInterface = true;
            this.mIcon = iconCompat;
            if (iconCompat != null && iconCompat.getType() == 2) {
                this.icon = iconCompat.getResId();
            }
            this.title = Builder.limitCharSequenceLength(charSequence);
            this.actionIntent = pendingIntent;
            this.mExtras = bundle == null ? new Bundle() : bundle;
            this.mRemoteInputs = remoteInputArr;
            this.mDataOnlyRemoteInputs = remoteInputArr2;
            this.mAllowGeneratedReplies = z;
            this.mSemanticAction = i;
            this.mShowsUserInterface = z2;
            this.mIsContextual = z3;
            this.mAuthenticationRequired = z4;
        }

        @Deprecated
        public int getIcon() {
            return this.icon;
        }

        public IconCompat getIconCompat() {
            int i;
            if (this.mIcon == null && (i = this.icon) != 0) {
                this.mIcon = IconCompat.createWithResource((Resources) null, "", i);
            }
            return this.mIcon;
        }

        public CharSequence getTitle() {
            return this.title;
        }

        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public boolean getAllowGeneratedReplies() {
            return this.mAllowGeneratedReplies;
        }

        public boolean isAuthenticationRequired() {
            return this.mAuthenticationRequired;
        }

        public RemoteInput[] getRemoteInputs() {
            return this.mRemoteInputs;
        }

        public int getSemanticAction() {
            return this.mSemanticAction;
        }

        public boolean isContextual() {
            return this.mIsContextual;
        }

        public RemoteInput[] getDataOnlyRemoteInputs() {
            return this.mDataOnlyRemoteInputs;
        }

        public boolean getShowsUserInterface() {
            return this.mShowsUserInterface;
        }

        public static final class Builder {
            private boolean mAllowGeneratedReplies;
            private boolean mAuthenticationRequired;
            private final Bundle mExtras;
            private final IconCompat mIcon;
            private final PendingIntent mIntent;
            private boolean mIsContextual;
            private ArrayList<RemoteInput> mRemoteInputs;
            private int mSemanticAction;
            private boolean mShowsUserInterface;
            private final CharSequence mTitle;

            public static Builder fromAndroidAction(Notification.Action action) {
                Builder builder;
                if (Api23Impl.getIcon(action) != null) {
                    builder = new Builder(IconCompat.createFromIconOrNullIfZeroResId(Api23Impl.getIcon(action)), action.title, action.actionIntent);
                } else {
                    builder = new Builder(action.icon, action.title, action.actionIntent);
                }
                RemoteInput[] remoteInputs = Api20Impl.getRemoteInputs(action);
                if (!(remoteInputs == null || remoteInputs.length == 0)) {
                    for (RemoteInput fromPlatform : remoteInputs) {
                        builder.addRemoteInput(RemoteInput.fromPlatform(fromPlatform));
                    }
                }
                builder.mAllowGeneratedReplies = Api24Impl.getAllowGeneratedReplies(action);
                if (Build.VERSION.SDK_INT >= 28) {
                    builder.setSemanticAction(Api28Impl.getSemanticAction(action));
                }
                if (Build.VERSION.SDK_INT >= 29) {
                    builder.setContextual(Api29Impl.isContextual(action));
                }
                if (Build.VERSION.SDK_INT >= 31) {
                    builder.setAuthenticationRequired(Api31Impl.isAuthenticationRequired(action));
                }
                builder.addExtras(Api20Impl.getExtras(action));
                return builder;
            }

            public Builder(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent) {
                this(iconCompat, charSequence, pendingIntent, new Bundle(), (RemoteInput[]) null, true, 0, true, false, false);
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public Builder(int i, CharSequence charSequence, PendingIntent pendingIntent) {
                this(i != 0 ? IconCompat.createWithResource((Resources) null, "", i) : null, charSequence, pendingIntent, new Bundle(), (RemoteInput[]) null, true, 0, true, false, false);
            }

            public Builder(Action action) {
                this(action.getIconCompat(), action.title, action.actionIntent, new Bundle(action.mExtras), action.getRemoteInputs(), action.getAllowGeneratedReplies(), action.getSemanticAction(), action.mShowsUserInterface, action.isContextual(), action.isAuthenticationRequired());
            }

            private Builder(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, boolean z, int i, boolean z2, boolean z3, boolean z4) {
                ArrayList<RemoteInput> arrayList;
                this.mAllowGeneratedReplies = true;
                this.mShowsUserInterface = true;
                this.mIcon = iconCompat;
                this.mTitle = Builder.limitCharSequenceLength(charSequence);
                this.mIntent = pendingIntent;
                this.mExtras = bundle;
                if (remoteInputArr == null) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList<>(Arrays.asList(remoteInputArr));
                }
                this.mRemoteInputs = arrayList;
                this.mAllowGeneratedReplies = z;
                this.mSemanticAction = i;
                this.mShowsUserInterface = z2;
                this.mIsContextual = z3;
                this.mAuthenticationRequired = z4;
            }

            public Builder addExtras(Bundle bundle) {
                if (bundle != null) {
                    this.mExtras.putAll(bundle);
                }
                return this;
            }

            public Bundle getExtras() {
                return this.mExtras;
            }

            public Builder addRemoteInput(RemoteInput remoteInput) {
                if (this.mRemoteInputs == null) {
                    this.mRemoteInputs = new ArrayList<>();
                }
                if (remoteInput != null) {
                    this.mRemoteInputs.add(remoteInput);
                }
                return this;
            }

            public Builder setAllowGeneratedReplies(boolean z) {
                this.mAllowGeneratedReplies = z;
                return this;
            }

            public Builder setSemanticAction(int i) {
                this.mSemanticAction = i;
                return this;
            }

            public Builder setContextual(boolean z) {
                this.mIsContextual = z;
                return this;
            }

            public Builder setAuthenticationRequired(boolean z) {
                this.mAuthenticationRequired = z;
                return this;
            }

            public Builder setShowsUserInterface(boolean z) {
                this.mShowsUserInterface = z;
                return this;
            }

            public Builder extend(Extender extender) {
                extender.extend(this);
                return this;
            }

            private void checkContextualActionNullFields() {
                if (this.mIsContextual && this.mIntent == null) {
                    throw new NullPointerException("Contextual Actions must contain a valid PendingIntent");
                }
            }

            /* JADX WARNING: type inference failed for: r1v5, types: [java.lang.Object[]] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public androidx.core.app.NotificationCompat.Action build() {
                /*
                    r17 = this;
                    r0 = r17
                    r17.checkContextualActionNullFields()
                    java.util.ArrayList r1 = new java.util.ArrayList
                    r1.<init>()
                    java.util.ArrayList r2 = new java.util.ArrayList
                    r2.<init>()
                    java.util.ArrayList<androidx.core.app.RemoteInput> r3 = r0.mRemoteInputs
                    if (r3 == 0) goto L_0x0031
                    java.util.Iterator r3 = r3.iterator()
                L_0x0017:
                    boolean r4 = r3.hasNext()
                    if (r4 == 0) goto L_0x0031
                    java.lang.Object r4 = r3.next()
                    androidx.core.app.RemoteInput r4 = (androidx.core.app.RemoteInput) r4
                    boolean r5 = r4.isDataOnly()
                    if (r5 == 0) goto L_0x002d
                    r1.add(r4)
                    goto L_0x0017
                L_0x002d:
                    r2.add(r4)
                    goto L_0x0017
                L_0x0031:
                    boolean r3 = r1.isEmpty()
                    r4 = 0
                    if (r3 == 0) goto L_0x003a
                    r11 = r4
                    goto L_0x0047
                L_0x003a:
                    int r3 = r1.size()
                    androidx.core.app.RemoteInput[] r3 = new androidx.core.app.RemoteInput[r3]
                    java.lang.Object[] r1 = r1.toArray(r3)
                    androidx.core.app.RemoteInput[] r1 = (androidx.core.app.RemoteInput[]) r1
                    r11 = r1
                L_0x0047:
                    boolean r1 = r2.isEmpty()
                    if (r1 == 0) goto L_0x004e
                    goto L_0x005b
                L_0x004e:
                    int r1 = r2.size()
                    androidx.core.app.RemoteInput[] r1 = new androidx.core.app.RemoteInput[r1]
                    java.lang.Object[] r1 = r2.toArray(r1)
                    r4 = r1
                    androidx.core.app.RemoteInput[] r4 = (androidx.core.app.RemoteInput[]) r4
                L_0x005b:
                    r10 = r4
                    androidx.core.app.NotificationCompat$Action r1 = new androidx.core.app.NotificationCompat$Action
                    androidx.core.graphics.drawable.IconCompat r6 = r0.mIcon
                    java.lang.CharSequence r7 = r0.mTitle
                    android.app.PendingIntent r8 = r0.mIntent
                    android.os.Bundle r9 = r0.mExtras
                    boolean r12 = r0.mAllowGeneratedReplies
                    int r13 = r0.mSemanticAction
                    boolean r14 = r0.mShowsUserInterface
                    boolean r15 = r0.mIsContextual
                    boolean r2 = r0.mAuthenticationRequired
                    r5 = r1
                    r16 = r2
                    r5.<init>((androidx.core.graphics.drawable.IconCompat) r6, (java.lang.CharSequence) r7, (android.app.PendingIntent) r8, (android.os.Bundle) r9, (androidx.core.app.RemoteInput[]) r10, (androidx.core.app.RemoteInput[]) r11, (boolean) r12, (int) r13, (boolean) r14, (boolean) r15, (boolean) r16)
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationCompat.Action.Builder.build():androidx.core.app.NotificationCompat$Action");
            }

            static class Api20Impl {
                private Api20Impl() {
                }

                static RemoteInput[] getRemoteInputs(Notification.Action action) {
                    return action.getRemoteInputs();
                }

                static Bundle getExtras(Notification.Action action) {
                    return action.getExtras();
                }
            }

            static class Api23Impl {
                private Api23Impl() {
                }

                static Icon getIcon(Notification.Action action) {
                    return action.getIcon();
                }
            }

            static class Api24Impl {
                private Api24Impl() {
                }

                static boolean getAllowGeneratedReplies(Notification.Action action) {
                    return action.getAllowGeneratedReplies();
                }
            }

            static class Api28Impl {
                private Api28Impl() {
                }

                static int getSemanticAction(Notification.Action action) {
                    return action.getSemanticAction();
                }
            }

            static class Api29Impl {
                private Api29Impl() {
                }

                static boolean isContextual(Notification.Action action) {
                    return action.isContextual();
                }
            }

            static class Api31Impl {
                private Api31Impl() {
                }

                static boolean isAuthenticationRequired(Notification.Action action) {
                    return action.isAuthenticationRequired();
                }
            }
        }

        public static final class WearableExtender implements Extender {
            private static final int DEFAULT_FLAGS = 1;
            private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
            private static final int FLAG_AVAILABLE_OFFLINE = 1;
            private static final int FLAG_HINT_DISPLAY_INLINE = 4;
            private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
            private static final String KEY_CANCEL_LABEL = "cancelLabel";
            private static final String KEY_CONFIRM_LABEL = "confirmLabel";
            private static final String KEY_FLAGS = "flags";
            private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
            private CharSequence mCancelLabel;
            private CharSequence mConfirmLabel;
            private int mFlags = 1;
            private CharSequence mInProgressLabel;

            public WearableExtender() {
            }

            public WearableExtender(Action action) {
                Bundle bundle = action.getExtras().getBundle(EXTRA_WEARABLE_EXTENSIONS);
                if (bundle != null) {
                    this.mFlags = bundle.getInt("flags", 1);
                    this.mInProgressLabel = bundle.getCharSequence(KEY_IN_PROGRESS_LABEL);
                    this.mConfirmLabel = bundle.getCharSequence(KEY_CONFIRM_LABEL);
                    this.mCancelLabel = bundle.getCharSequence(KEY_CANCEL_LABEL);
                }
            }

            public Builder extend(Builder builder) {
                Bundle bundle = new Bundle();
                int i = this.mFlags;
                if (i != 1) {
                    bundle.putInt("flags", i);
                }
                CharSequence charSequence = this.mInProgressLabel;
                if (charSequence != null) {
                    bundle.putCharSequence(KEY_IN_PROGRESS_LABEL, charSequence);
                }
                CharSequence charSequence2 = this.mConfirmLabel;
                if (charSequence2 != null) {
                    bundle.putCharSequence(KEY_CONFIRM_LABEL, charSequence2);
                }
                CharSequence charSequence3 = this.mCancelLabel;
                if (charSequence3 != null) {
                    bundle.putCharSequence(KEY_CANCEL_LABEL, charSequence3);
                }
                builder.getExtras().putBundle(EXTRA_WEARABLE_EXTENSIONS, bundle);
                return builder;
            }

            public WearableExtender clone() {
                WearableExtender wearableExtender = new WearableExtender();
                wearableExtender.mFlags = this.mFlags;
                wearableExtender.mInProgressLabel = this.mInProgressLabel;
                wearableExtender.mConfirmLabel = this.mConfirmLabel;
                wearableExtender.mCancelLabel = this.mCancelLabel;
                return wearableExtender;
            }

            public WearableExtender setAvailableOffline(boolean z) {
                setFlag(1, z);
                return this;
            }

            public boolean isAvailableOffline() {
                return (this.mFlags & 1) != 0;
            }

            private void setFlag(int i, boolean z) {
                if (z) {
                    this.mFlags = i | this.mFlags;
                    return;
                }
                this.mFlags = (~i) & this.mFlags;
            }

            @Deprecated
            public WearableExtender setInProgressLabel(CharSequence charSequence) {
                this.mInProgressLabel = charSequence;
                return this;
            }

            @Deprecated
            public CharSequence getInProgressLabel() {
                return this.mInProgressLabel;
            }

            @Deprecated
            public WearableExtender setConfirmLabel(CharSequence charSequence) {
                this.mConfirmLabel = charSequence;
                return this;
            }

            @Deprecated
            public CharSequence getConfirmLabel() {
                return this.mConfirmLabel;
            }

            @Deprecated
            public WearableExtender setCancelLabel(CharSequence charSequence) {
                this.mCancelLabel = charSequence;
                return this;
            }

            @Deprecated
            public CharSequence getCancelLabel() {
                return this.mCancelLabel;
            }

            public WearableExtender setHintLaunchesActivity(boolean z) {
                setFlag(2, z);
                return this;
            }

            public boolean getHintLaunchesActivity() {
                return (this.mFlags & 2) != 0;
            }

            public WearableExtender setHintDisplayActionInline(boolean z) {
                setFlag(4, z);
                return this;
            }

            public boolean getHintDisplayActionInline() {
                return (this.mFlags & 4) != 0;
            }
        }
    }

    public static final class WearableExtender implements Extender {
        private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
        private static final int DEFAULT_FLAGS = 1;
        private static final int DEFAULT_GRAVITY = 80;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
        private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
        private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
        private static final int FLAG_HINT_HIDE_ICON = 2;
        private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        private static final int FLAG_START_SCROLL_BOTTOM = 8;
        private static final String KEY_ACTIONS = "actions";
        private static final String KEY_BACKGROUND = "background";
        private static final String KEY_BRIDGE_TAG = "bridgeTag";
        private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        private static final String KEY_CONTENT_ICON = "contentIcon";
        private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        private static final String KEY_DISMISSAL_ID = "dismissalId";
        private static final String KEY_DISPLAY_INTENT = "displayIntent";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_GRAVITY = "gravity";
        private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
        private static final String KEY_PAGES = "pages";
        @Deprecated
        public static final int SCREEN_TIMEOUT_LONG = -1;
        @Deprecated
        public static final int SCREEN_TIMEOUT_SHORT = 0;
        @Deprecated
        public static final int SIZE_DEFAULT = 0;
        @Deprecated
        public static final int SIZE_FULL_SCREEN = 5;
        @Deprecated
        public static final int SIZE_LARGE = 4;
        @Deprecated
        public static final int SIZE_MEDIUM = 3;
        @Deprecated
        public static final int SIZE_SMALL = 2;
        @Deprecated
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList<Action> mActions = new ArrayList<>();
        private Bitmap mBackground;
        private String mBridgeTag;
        private int mContentActionIndex = -1;
        private int mContentIcon;
        private int mContentIconGravity = 8388613;
        private int mCustomContentHeight;
        private int mCustomSizePreset = 0;
        private String mDismissalId;
        private PendingIntent mDisplayIntent;
        private int mFlags = 1;
        private int mGravity = 80;
        private int mHintScreenTimeout;
        private ArrayList<Notification> mPages = new ArrayList<>();

        public WearableExtender() {
        }

        public WearableExtender(Notification notification) {
            Bundle extras = NotificationCompat.getExtras(notification);
            Bundle bundle = extras != null ? extras.getBundle(EXTRA_WEARABLE_EXTENSIONS) : null;
            if (bundle != null) {
                ArrayList parcelableArrayList = bundle.getParcelableArrayList(KEY_ACTIONS);
                if (parcelableArrayList != null) {
                    int size = parcelableArrayList.size();
                    Action[] actionArr = new Action[size];
                    for (int i = 0; i < size; i++) {
                        actionArr[i] = Api20Impl.getActionCompatFromAction(parcelableArrayList, i);
                    }
                    Collections.addAll(this.mActions, actionArr);
                }
                this.mFlags = bundle.getInt("flags", 1);
                this.mDisplayIntent = (PendingIntent) bundle.getParcelable(KEY_DISPLAY_INTENT);
                Notification[] notificationArrayFromBundle = NotificationCompat.getNotificationArrayFromBundle(bundle, KEY_PAGES);
                if (notificationArrayFromBundle != null) {
                    Collections.addAll(this.mPages, notificationArrayFromBundle);
                }
                this.mBackground = (Bitmap) bundle.getParcelable("background");
                this.mContentIcon = bundle.getInt(KEY_CONTENT_ICON);
                this.mContentIconGravity = bundle.getInt(KEY_CONTENT_ICON_GRAVITY, 8388613);
                this.mContentActionIndex = bundle.getInt(KEY_CONTENT_ACTION_INDEX, -1);
                this.mCustomSizePreset = bundle.getInt(KEY_CUSTOM_SIZE_PRESET, 0);
                this.mCustomContentHeight = bundle.getInt(KEY_CUSTOM_CONTENT_HEIGHT);
                this.mGravity = bundle.getInt(KEY_GRAVITY, 80);
                this.mHintScreenTimeout = bundle.getInt(KEY_HINT_SCREEN_TIMEOUT);
                this.mDismissalId = bundle.getString(KEY_DISMISSAL_ID);
                this.mBridgeTag = bundle.getString(KEY_BRIDGE_TAG);
            }
        }

        public Builder extend(Builder builder) {
            Bundle bundle = new Bundle();
            if (!this.mActions.isEmpty()) {
                ArrayList arrayList = new ArrayList(this.mActions.size());
                Iterator<Action> it = this.mActions.iterator();
                while (it.hasNext()) {
                    arrayList.add(getActionFromActionCompat(it.next()));
                }
                bundle.putParcelableArrayList(KEY_ACTIONS, arrayList);
            }
            int i = this.mFlags;
            if (i != 1) {
                bundle.putInt("flags", i);
            }
            PendingIntent pendingIntent = this.mDisplayIntent;
            if (pendingIntent != null) {
                bundle.putParcelable(KEY_DISPLAY_INTENT, pendingIntent);
            }
            if (!this.mPages.isEmpty()) {
                ArrayList<Notification> arrayList2 = this.mPages;
                bundle.putParcelableArray(KEY_PAGES, (Parcelable[]) arrayList2.toArray(new Notification[arrayList2.size()]));
            }
            Bitmap bitmap = this.mBackground;
            if (bitmap != null) {
                bundle.putParcelable("background", bitmap);
            }
            int i2 = this.mContentIcon;
            if (i2 != 0) {
                bundle.putInt(KEY_CONTENT_ICON, i2);
            }
            int i3 = this.mContentIconGravity;
            if (i3 != 8388613) {
                bundle.putInt(KEY_CONTENT_ICON_GRAVITY, i3);
            }
            int i4 = this.mContentActionIndex;
            if (i4 != -1) {
                bundle.putInt(KEY_CONTENT_ACTION_INDEX, i4);
            }
            int i5 = this.mCustomSizePreset;
            if (i5 != 0) {
                bundle.putInt(KEY_CUSTOM_SIZE_PRESET, i5);
            }
            int i6 = this.mCustomContentHeight;
            if (i6 != 0) {
                bundle.putInt(KEY_CUSTOM_CONTENT_HEIGHT, i6);
            }
            int i7 = this.mGravity;
            if (i7 != 80) {
                bundle.putInt(KEY_GRAVITY, i7);
            }
            int i8 = this.mHintScreenTimeout;
            if (i8 != 0) {
                bundle.putInt(KEY_HINT_SCREEN_TIMEOUT, i8);
            }
            String str = this.mDismissalId;
            if (str != null) {
                bundle.putString(KEY_DISMISSAL_ID, str);
            }
            String str2 = this.mBridgeTag;
            if (str2 != null) {
                bundle.putString(KEY_BRIDGE_TAG, str2);
            }
            builder.getExtras().putBundle(EXTRA_WEARABLE_EXTENSIONS, bundle);
            return builder;
        }

        private static Notification.Action getActionFromActionCompat(Action action) {
            Icon icon;
            Bundle bundle;
            IconCompat iconCompat = action.getIconCompat();
            if (iconCompat == null) {
                icon = null;
            } else {
                icon = iconCompat.toIcon();
            }
            Notification.Action.Builder createBuilder = Api23Impl.createBuilder(icon, action.getTitle(), action.getActionIntent());
            if (action.getExtras() != null) {
                bundle = new Bundle(action.getExtras());
            } else {
                bundle = new Bundle();
            }
            bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
            Api24Impl.setAllowGeneratedReplies(createBuilder, action.getAllowGeneratedReplies());
            if (Build.VERSION.SDK_INT >= 31) {
                Api31Impl.setAuthenticationRequired(createBuilder, action.isAuthenticationRequired());
            }
            Api20Impl.addExtras(createBuilder, bundle);
            RemoteInput[] remoteInputs = action.getRemoteInputs();
            if (remoteInputs != null) {
                for (RemoteInput addRemoteInput : RemoteInput.fromCompat(remoteInputs)) {
                    Api20Impl.addRemoteInput(createBuilder, addRemoteInput);
                }
            }
            return Api20Impl.build(createBuilder);
        }

        public WearableExtender clone() {
            WearableExtender wearableExtender = new WearableExtender();
            wearableExtender.mActions = new ArrayList<>(this.mActions);
            wearableExtender.mFlags = this.mFlags;
            wearableExtender.mDisplayIntent = this.mDisplayIntent;
            wearableExtender.mPages = new ArrayList<>(this.mPages);
            wearableExtender.mBackground = this.mBackground;
            wearableExtender.mContentIcon = this.mContentIcon;
            wearableExtender.mContentIconGravity = this.mContentIconGravity;
            wearableExtender.mContentActionIndex = this.mContentActionIndex;
            wearableExtender.mCustomSizePreset = this.mCustomSizePreset;
            wearableExtender.mCustomContentHeight = this.mCustomContentHeight;
            wearableExtender.mGravity = this.mGravity;
            wearableExtender.mHintScreenTimeout = this.mHintScreenTimeout;
            wearableExtender.mDismissalId = this.mDismissalId;
            wearableExtender.mBridgeTag = this.mBridgeTag;
            return wearableExtender;
        }

        public WearableExtender addAction(Action action) {
            this.mActions.add(action);
            return this;
        }

        public WearableExtender addActions(List<Action> list) {
            this.mActions.addAll(list);
            return this;
        }

        public WearableExtender clearActions() {
            this.mActions.clear();
            return this;
        }

        public List<Action> getActions() {
            return this.mActions;
        }

        @Deprecated
        public WearableExtender setDisplayIntent(PendingIntent pendingIntent) {
            this.mDisplayIntent = pendingIntent;
            return this;
        }

        @Deprecated
        public PendingIntent getDisplayIntent() {
            return this.mDisplayIntent;
        }

        @Deprecated
        public WearableExtender addPage(Notification notification) {
            this.mPages.add(notification);
            return this;
        }

        @Deprecated
        public WearableExtender addPages(List<Notification> list) {
            this.mPages.addAll(list);
            return this;
        }

        @Deprecated
        public WearableExtender clearPages() {
            this.mPages.clear();
            return this;
        }

        @Deprecated
        public List<Notification> getPages() {
            return this.mPages;
        }

        @Deprecated
        public WearableExtender setBackground(Bitmap bitmap) {
            this.mBackground = bitmap;
            return this;
        }

        @Deprecated
        public Bitmap getBackground() {
            return this.mBackground;
        }

        @Deprecated
        public WearableExtender setContentIcon(int i) {
            this.mContentIcon = i;
            return this;
        }

        @Deprecated
        public int getContentIcon() {
            return this.mContentIcon;
        }

        @Deprecated
        public WearableExtender setContentIconGravity(int i) {
            this.mContentIconGravity = i;
            return this;
        }

        @Deprecated
        public int getContentIconGravity() {
            return this.mContentIconGravity;
        }

        public WearableExtender setContentAction(int i) {
            this.mContentActionIndex = i;
            return this;
        }

        public int getContentAction() {
            return this.mContentActionIndex;
        }

        @Deprecated
        public WearableExtender setGravity(int i) {
            this.mGravity = i;
            return this;
        }

        @Deprecated
        public int getGravity() {
            return this.mGravity;
        }

        @Deprecated
        public WearableExtender setCustomSizePreset(int i) {
            this.mCustomSizePreset = i;
            return this;
        }

        @Deprecated
        public int getCustomSizePreset() {
            return this.mCustomSizePreset;
        }

        @Deprecated
        public WearableExtender setCustomContentHeight(int i) {
            this.mCustomContentHeight = i;
            return this;
        }

        @Deprecated
        public int getCustomContentHeight() {
            return this.mCustomContentHeight;
        }

        public WearableExtender setStartScrollBottom(boolean z) {
            setFlag(8, z);
            return this;
        }

        public boolean getStartScrollBottom() {
            return (this.mFlags & 8) != 0;
        }

        public WearableExtender setContentIntentAvailableOffline(boolean z) {
            setFlag(1, z);
            return this;
        }

        public boolean getContentIntentAvailableOffline() {
            return (this.mFlags & 1) != 0;
        }

        @Deprecated
        public WearableExtender setHintHideIcon(boolean z) {
            setFlag(2, z);
            return this;
        }

        @Deprecated
        public boolean getHintHideIcon() {
            return (this.mFlags & 2) != 0;
        }

        @Deprecated
        public WearableExtender setHintShowBackgroundOnly(boolean z) {
            setFlag(4, z);
            return this;
        }

        @Deprecated
        public boolean getHintShowBackgroundOnly() {
            return (this.mFlags & 4) != 0;
        }

        @Deprecated
        public WearableExtender setHintAvoidBackgroundClipping(boolean z) {
            setFlag(16, z);
            return this;
        }

        @Deprecated
        public boolean getHintAvoidBackgroundClipping() {
            return (this.mFlags & 16) != 0;
        }

        @Deprecated
        public WearableExtender setHintScreenTimeout(int i) {
            this.mHintScreenTimeout = i;
            return this;
        }

        @Deprecated
        public int getHintScreenTimeout() {
            return this.mHintScreenTimeout;
        }

        @Deprecated
        public WearableExtender setHintAmbientBigPicture(boolean z) {
            setFlag(32, z);
            return this;
        }

        @Deprecated
        public boolean getHintAmbientBigPicture() {
            return (this.mFlags & 32) != 0;
        }

        public WearableExtender setHintContentIntentLaunchesActivity(boolean z) {
            setFlag(64, z);
            return this;
        }

        public boolean getHintContentIntentLaunchesActivity() {
            return (this.mFlags & 64) != 0;
        }

        public WearableExtender setDismissalId(String str) {
            this.mDismissalId = str;
            return this;
        }

        public String getDismissalId() {
            return this.mDismissalId;
        }

        public WearableExtender setBridgeTag(String str) {
            this.mBridgeTag = str;
            return this;
        }

        public String getBridgeTag() {
            return this.mBridgeTag;
        }

        private void setFlag(int i, boolean z) {
            if (z) {
                this.mFlags = i | this.mFlags;
                return;
            }
            this.mFlags = (~i) & this.mFlags;
        }

        static class Api20Impl {
            private Api20Impl() {
            }

            static Notification.Action.Builder createBuilder(int i, CharSequence charSequence, PendingIntent pendingIntent) {
                return new Notification.Action.Builder(i, charSequence, pendingIntent);
            }

            static Notification.Action.Builder addExtras(Notification.Action.Builder builder, Bundle bundle) {
                return builder.addExtras(bundle);
            }

            static Notification.Action.Builder addRemoteInput(Notification.Action.Builder builder, RemoteInput remoteInput) {
                return builder.addRemoteInput(remoteInput);
            }

            static Notification.Action build(Notification.Action.Builder builder) {
                return builder.build();
            }

            public static Action getActionCompatFromAction(ArrayList<Parcelable> arrayList, int i) {
                return NotificationCompat.getActionCompatFromAction((Notification.Action) arrayList.get(i));
            }
        }

        static class Api23Impl {
            private Api23Impl() {
            }

            static Notification.Action.Builder createBuilder(Icon icon, CharSequence charSequence, PendingIntent pendingIntent) {
                return new Notification.Action.Builder(icon, charSequence, pendingIntent);
            }
        }

        static class Api24Impl {
            private Api24Impl() {
            }

            static Notification.Action.Builder setAllowGeneratedReplies(Notification.Action.Builder builder, boolean z) {
                return builder.setAllowGeneratedReplies(z);
            }
        }

        static class Api31Impl {
            private Api31Impl() {
            }

            static Notification.Action.Builder setAuthenticationRequired(Notification.Action.Builder builder, boolean z) {
                return builder.setAuthenticationRequired(z);
            }
        }
    }

    public static final class CarExtender implements Extender {
        static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
        private static final String EXTRA_COLOR = "app_color";
        private static final String EXTRA_CONVERSATION = "car_conversation";
        static final String EXTRA_INVISIBLE_ACTIONS = "invisible_actions";
        private static final String EXTRA_LARGE_ICON = "large_icon";
        private static final String KEY_AUTHOR = "author";
        private static final String KEY_MESSAGES = "messages";
        private static final String KEY_ON_READ = "on_read";
        private static final String KEY_ON_REPLY = "on_reply";
        private static final String KEY_PARTICIPANTS = "participants";
        private static final String KEY_REMOTE_INPUT = "remote_input";
        private static final String KEY_TEXT = "text";
        private static final String KEY_TIMESTAMP = "timestamp";
        private int mColor = 0;
        private Bitmap mLargeIcon;
        private UnreadConversation mUnreadConversation;

        public CarExtender() {
        }

        public CarExtender(Notification notification) {
            Bundle bundle;
            if (NotificationCompat.getExtras(notification) == null) {
                bundle = null;
            } else {
                bundle = NotificationCompat.getExtras(notification).getBundle(EXTRA_CAR_EXTENDER);
            }
            if (bundle != null) {
                this.mLargeIcon = (Bitmap) bundle.getParcelable(EXTRA_LARGE_ICON);
                this.mColor = bundle.getInt(EXTRA_COLOR, 0);
                this.mUnreadConversation = getUnreadConversationFromBundle(bundle.getBundle(EXTRA_CONVERSATION));
            }
        }

        private static UnreadConversation getUnreadConversationFromBundle(Bundle bundle) {
            String[] strArr;
            boolean z;
            Bundle bundle2 = bundle;
            RemoteInput remoteInput = null;
            if (bundle2 == null) {
                return null;
            }
            Parcelable[] parcelableArray = bundle2.getParcelableArray(KEY_MESSAGES);
            int i = 0;
            if (parcelableArray != null) {
                int length = parcelableArray.length;
                String[] strArr2 = new String[length];
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        z = true;
                        break;
                    }
                    Parcelable parcelable = parcelableArray[i2];
                    if (!(parcelable instanceof Bundle)) {
                        break;
                    }
                    String string = ((Bundle) parcelable).getString("text");
                    strArr2[i2] = string;
                    if (string == null) {
                        break;
                    }
                    i2++;
                }
                z = false;
                if (!z) {
                    return null;
                }
                strArr = strArr2;
            } else {
                strArr = null;
            }
            PendingIntent pendingIntent = (PendingIntent) bundle2.getParcelable(KEY_ON_READ);
            PendingIntent pendingIntent2 = (PendingIntent) bundle2.getParcelable(KEY_ON_REPLY);
            RemoteInput remoteInput2 = (RemoteInput) bundle2.getParcelable(KEY_REMOTE_INPUT);
            String[] stringArray = bundle2.getStringArray(KEY_PARTICIPANTS);
            if (stringArray == null || stringArray.length != 1) {
                return null;
            }
            if (remoteInput2 != null) {
                String resultKey = Api20Impl.getResultKey(remoteInput2);
                CharSequence label = Api20Impl.getLabel(remoteInput2);
                CharSequence[] choices = Api20Impl.getChoices(remoteInput2);
                boolean allowFreeFormInput = Api20Impl.getAllowFreeFormInput(remoteInput2);
                if (Build.VERSION.SDK_INT >= 29) {
                    i = Api29Impl.getEditChoicesBeforeSending(remoteInput2);
                }
                remoteInput = new RemoteInput(resultKey, label, choices, allowFreeFormInput, i, Api20Impl.getExtras(remoteInput2), (Set<String>) null);
            }
            return new UnreadConversation(strArr, remoteInput, pendingIntent2, pendingIntent, stringArray, bundle2.getLong("timestamp"));
        }

        private static Bundle getBundleForUnreadConversation(UnreadConversation unreadConversation) {
            Bundle bundle = new Bundle();
            String str = (unreadConversation.getParticipants() == null || unreadConversation.getParticipants().length <= 1) ? null : unreadConversation.getParticipants()[0];
            int length = unreadConversation.getMessages().length;
            Parcelable[] parcelableArr = new Parcelable[length];
            for (int i = 0; i < length; i++) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("text", unreadConversation.getMessages()[i]);
                bundle2.putString(KEY_AUTHOR, str);
                parcelableArr[i] = bundle2;
            }
            bundle.putParcelableArray(KEY_MESSAGES, parcelableArr);
            RemoteInput remoteInput = unreadConversation.getRemoteInput();
            if (remoteInput != null) {
                RemoteInput.Builder createBuilder = Api20Impl.createBuilder(remoteInput.getResultKey());
                Api20Impl.setLabel(createBuilder, remoteInput.getLabel());
                Api20Impl.setChoices(createBuilder, remoteInput.getChoices());
                Api20Impl.setAllowFreeFormInput(createBuilder, remoteInput.getAllowFreeFormInput());
                Api20Impl.addExtras(createBuilder, remoteInput.getExtras());
                bundle.putParcelable(KEY_REMOTE_INPUT, Api20Impl.castToParcelable(Api20Impl.build(createBuilder)));
            }
            bundle.putParcelable(KEY_ON_REPLY, unreadConversation.getReplyPendingIntent());
            bundle.putParcelable(KEY_ON_READ, unreadConversation.getReadPendingIntent());
            bundle.putStringArray(KEY_PARTICIPANTS, unreadConversation.getParticipants());
            bundle.putLong("timestamp", unreadConversation.getLatestTimestamp());
            return bundle;
        }

        public Builder extend(Builder builder) {
            Bundle bundle = new Bundle();
            Bitmap bitmap = this.mLargeIcon;
            if (bitmap != null) {
                bundle.putParcelable(EXTRA_LARGE_ICON, bitmap);
            }
            int i = this.mColor;
            if (i != 0) {
                bundle.putInt(EXTRA_COLOR, i);
            }
            UnreadConversation unreadConversation = this.mUnreadConversation;
            if (unreadConversation != null) {
                bundle.putBundle(EXTRA_CONVERSATION, getBundleForUnreadConversation(unreadConversation));
            }
            builder.getExtras().putBundle(EXTRA_CAR_EXTENDER, bundle);
            return builder;
        }

        public CarExtender setColor(int i) {
            this.mColor = i;
            return this;
        }

        public int getColor() {
            return this.mColor;
        }

        public CarExtender setLargeIcon(Bitmap bitmap) {
            this.mLargeIcon = bitmap;
            return this;
        }

        public Bitmap getLargeIcon() {
            return this.mLargeIcon;
        }

        @Deprecated
        public CarExtender setUnreadConversation(UnreadConversation unreadConversation) {
            this.mUnreadConversation = unreadConversation;
            return this;
        }

        @Deprecated
        public UnreadConversation getUnreadConversation() {
            return this.mUnreadConversation;
        }

        @Deprecated
        public static class UnreadConversation {
            private final long mLatestTimestamp;
            private final String[] mMessages;
            private final String[] mParticipants;
            private final PendingIntent mReadPendingIntent;
            private final RemoteInput mRemoteInput;
            private final PendingIntent mReplyPendingIntent;

            UnreadConversation(String[] strArr, RemoteInput remoteInput, PendingIntent pendingIntent, PendingIntent pendingIntent2, String[] strArr2, long j) {
                this.mMessages = strArr;
                this.mRemoteInput = remoteInput;
                this.mReadPendingIntent = pendingIntent2;
                this.mReplyPendingIntent = pendingIntent;
                this.mParticipants = strArr2;
                this.mLatestTimestamp = j;
            }

            public String[] getMessages() {
                return this.mMessages;
            }

            public RemoteInput getRemoteInput() {
                return this.mRemoteInput;
            }

            public PendingIntent getReplyPendingIntent() {
                return this.mReplyPendingIntent;
            }

            public PendingIntent getReadPendingIntent() {
                return this.mReadPendingIntent;
            }

            public String[] getParticipants() {
                return this.mParticipants;
            }

            public String getParticipant() {
                String[] strArr = this.mParticipants;
                if (strArr.length > 0) {
                    return strArr[0];
                }
                return null;
            }

            public long getLatestTimestamp() {
                return this.mLatestTimestamp;
            }

            public static class Builder {
                private long mLatestTimestamp;
                private final List<String> mMessages = new ArrayList();
                private final String mParticipant;
                private PendingIntent mReadPendingIntent;
                private RemoteInput mRemoteInput;
                private PendingIntent mReplyPendingIntent;

                public Builder(String str) {
                    this.mParticipant = str;
                }

                public Builder addMessage(String str) {
                    if (str != null) {
                        this.mMessages.add(str);
                    }
                    return this;
                }

                public Builder setReplyAction(PendingIntent pendingIntent, RemoteInput remoteInput) {
                    this.mRemoteInput = remoteInput;
                    this.mReplyPendingIntent = pendingIntent;
                    return this;
                }

                public Builder setReadPendingIntent(PendingIntent pendingIntent) {
                    this.mReadPendingIntent = pendingIntent;
                    return this;
                }

                public Builder setLatestTimestamp(long j) {
                    this.mLatestTimestamp = j;
                    return this;
                }

                public UnreadConversation build() {
                    List<String> list = this.mMessages;
                    return new UnreadConversation((String[]) list.toArray(new String[list.size()]), this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, new String[]{this.mParticipant}, this.mLatestTimestamp);
                }
            }
        }

        static class Api20Impl {
            static Parcelable castToParcelable(RemoteInput remoteInput) {
                return remoteInput;
            }

            private Api20Impl() {
            }

            static RemoteInput.Builder createBuilder(String str) {
                return new RemoteInput.Builder(str);
            }

            static RemoteInput build(RemoteInput.Builder builder) {
                return builder.build();
            }

            static String getResultKey(RemoteInput remoteInput) {
                return remoteInput.getResultKey();
            }

            static CharSequence[] getChoices(RemoteInput remoteInput) {
                return remoteInput.getChoices();
            }

            static RemoteInput.Builder setChoices(RemoteInput.Builder builder, CharSequence[] charSequenceArr) {
                return builder.setChoices(charSequenceArr);
            }

            static CharSequence getLabel(RemoteInput remoteInput) {
                return remoteInput.getLabel();
            }

            static RemoteInput.Builder setLabel(RemoteInput.Builder builder, CharSequence charSequence) {
                return builder.setLabel(charSequence);
            }

            static boolean getAllowFreeFormInput(RemoteInput remoteInput) {
                return remoteInput.getAllowFreeFormInput();
            }

            static RemoteInput.Builder setAllowFreeFormInput(RemoteInput.Builder builder, boolean z) {
                return builder.setAllowFreeFormInput(z);
            }

            static Bundle getExtras(RemoteInput remoteInput) {
                return remoteInput.getExtras();
            }

            static RemoteInput.Builder addExtras(RemoteInput.Builder builder, Bundle bundle) {
                return builder.addExtras(bundle);
            }
        }

        static class Api29Impl {
            private Api29Impl() {
            }

            static int getEditChoicesBeforeSending(RemoteInput remoteInput) {
                return remoteInput.getEditChoicesBeforeSending();
            }
        }
    }

    public static final class TvExtender implements Extender {
        static final String EXTRA_CHANNEL_ID = "channel_id";
        static final String EXTRA_CONTENT_INTENT = "content_intent";
        static final String EXTRA_DELETE_INTENT = "delete_intent";
        private static final String EXTRA_FLAGS = "flags";
        static final String EXTRA_SUPPRESS_SHOW_OVER_APPS = "suppressShowOverApps";
        static final String EXTRA_TV_EXTENDER = "android.tv.EXTENSIONS";
        private static final int FLAG_AVAILABLE_ON_TV = 1;
        private static final String TAG = "TvExtender";
        private String mChannelId;
        private PendingIntent mContentIntent;
        private PendingIntent mDeleteIntent;
        private int mFlags;
        private boolean mSuppressShowOverApps;

        public TvExtender() {
            this.mFlags = 1;
        }

        public TvExtender(Notification notification) {
            Bundle bundle;
            if (notification.extras == null) {
                bundle = null;
            } else {
                bundle = notification.extras.getBundle(EXTRA_TV_EXTENDER);
            }
            if (bundle != null) {
                this.mFlags = bundle.getInt("flags");
                this.mChannelId = bundle.getString(EXTRA_CHANNEL_ID);
                this.mSuppressShowOverApps = bundle.getBoolean(EXTRA_SUPPRESS_SHOW_OVER_APPS);
                this.mContentIntent = (PendingIntent) bundle.getParcelable(EXTRA_CONTENT_INTENT);
                this.mDeleteIntent = (PendingIntent) bundle.getParcelable(EXTRA_DELETE_INTENT);
            }
        }

        public Builder extend(Builder builder) {
            Bundle bundle = new Bundle();
            bundle.putInt("flags", this.mFlags);
            bundle.putString(EXTRA_CHANNEL_ID, this.mChannelId);
            bundle.putBoolean(EXTRA_SUPPRESS_SHOW_OVER_APPS, this.mSuppressShowOverApps);
            PendingIntent pendingIntent = this.mContentIntent;
            if (pendingIntent != null) {
                bundle.putParcelable(EXTRA_CONTENT_INTENT, pendingIntent);
            }
            PendingIntent pendingIntent2 = this.mDeleteIntent;
            if (pendingIntent2 != null) {
                bundle.putParcelable(EXTRA_DELETE_INTENT, pendingIntent2);
            }
            builder.getExtras().putBundle(EXTRA_TV_EXTENDER, bundle);
            return builder;
        }

        public boolean isAvailableOnTv() {
            return (this.mFlags & 1) != 0;
        }

        public TvExtender setChannelId(String str) {
            this.mChannelId = str;
            return this;
        }

        public String getChannelId() {
            return this.mChannelId;
        }

        public TvExtender setContentIntent(PendingIntent pendingIntent) {
            this.mContentIntent = pendingIntent;
            return this;
        }

        public PendingIntent getContentIntent() {
            return this.mContentIntent;
        }

        public TvExtender setDeleteIntent(PendingIntent pendingIntent) {
            this.mDeleteIntent = pendingIntent;
            return this;
        }

        public PendingIntent getDeleteIntent() {
            return this.mDeleteIntent;
        }

        public TvExtender setSuppressShowOverApps(boolean z) {
            this.mSuppressShowOverApps = z;
            return this;
        }

        public boolean isSuppressShowOverApps() {
            return this.mSuppressShowOverApps;
        }
    }

    public static final class BubbleMetadata {
        private static final int FLAG_AUTO_EXPAND_BUBBLE = 1;
        private static final int FLAG_SUPPRESS_NOTIFICATION = 2;
        private PendingIntent mDeleteIntent;
        private int mDesiredHeight;
        private int mDesiredHeightResId;
        private int mFlags;
        private IconCompat mIcon;
        private PendingIntent mPendingIntent;
        private String mShortcutId;

        private BubbleMetadata(PendingIntent pendingIntent, PendingIntent pendingIntent2, IconCompat iconCompat, int i, int i2, int i3, String str) {
            this.mPendingIntent = pendingIntent;
            this.mIcon = iconCompat;
            this.mDesiredHeight = i;
            this.mDesiredHeightResId = i2;
            this.mDeleteIntent = pendingIntent2;
            this.mFlags = i3;
            this.mShortcutId = str;
        }

        public PendingIntent getIntent() {
            return this.mPendingIntent;
        }

        public String getShortcutId() {
            return this.mShortcutId;
        }

        public PendingIntent getDeleteIntent() {
            return this.mDeleteIntent;
        }

        public IconCompat getIcon() {
            return this.mIcon;
        }

        public int getDesiredHeight() {
            return this.mDesiredHeight;
        }

        public int getDesiredHeightResId() {
            return this.mDesiredHeightResId;
        }

        public boolean getAutoExpandBubble() {
            return (this.mFlags & 1) != 0;
        }

        public boolean isNotificationSuppressed() {
            return (this.mFlags & 2) != 0;
        }

        public void setFlags(int i) {
            this.mFlags = i;
        }

        public static Notification.BubbleMetadata toPlatform(BubbleMetadata bubbleMetadata) {
            if (bubbleMetadata == null) {
                return null;
            }
            if (Build.VERSION.SDK_INT >= 30) {
                return Api30Impl.toPlatform(bubbleMetadata);
            }
            if (Build.VERSION.SDK_INT == 29) {
                return Api29Impl.toPlatform(bubbleMetadata);
            }
            return null;
        }

        public static BubbleMetadata fromPlatform(Notification.BubbleMetadata bubbleMetadata) {
            if (bubbleMetadata == null) {
                return null;
            }
            if (Build.VERSION.SDK_INT >= 30) {
                return Api30Impl.fromPlatform(bubbleMetadata);
            }
            if (Build.VERSION.SDK_INT == 29) {
                return Api29Impl.fromPlatform(bubbleMetadata);
            }
            return null;
        }

        public static final class Builder {
            private PendingIntent mDeleteIntent;
            private int mDesiredHeight;
            private int mDesiredHeightResId;
            private int mFlags;
            private IconCompat mIcon;
            private PendingIntent mPendingIntent;
            private String mShortcutId;

            @Deprecated
            public Builder() {
            }

            public Builder(String str) {
                if (!TextUtils.isEmpty(str)) {
                    this.mShortcutId = str;
                    return;
                }
                throw new NullPointerException("Bubble requires a non-null shortcut id");
            }

            public Builder(PendingIntent pendingIntent, IconCompat iconCompat) {
                if (pendingIntent == null) {
                    throw new NullPointerException("Bubble requires non-null pending intent");
                } else if (iconCompat != null) {
                    this.mPendingIntent = pendingIntent;
                    this.mIcon = iconCompat;
                } else {
                    throw new NullPointerException("Bubbles require non-null icon");
                }
            }

            public Builder setIntent(PendingIntent pendingIntent) {
                if (this.mShortcutId != null) {
                    throw new IllegalStateException("Created as a shortcut bubble, cannot set a PendingIntent. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
                } else if (pendingIntent != null) {
                    this.mPendingIntent = pendingIntent;
                    return this;
                } else {
                    throw new NullPointerException("Bubble requires non-null pending intent");
                }
            }

            public Builder setIcon(IconCompat iconCompat) {
                if (this.mShortcutId != null) {
                    throw new IllegalStateException("Created as a shortcut bubble, cannot set an Icon. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
                } else if (iconCompat != null) {
                    this.mIcon = iconCompat;
                    return this;
                } else {
                    throw new NullPointerException("Bubbles require non-null icon");
                }
            }

            public Builder setDesiredHeight(int i) {
                this.mDesiredHeight = Math.max(i, 0);
                this.mDesiredHeightResId = 0;
                return this;
            }

            public Builder setDesiredHeightResId(int i) {
                this.mDesiredHeightResId = i;
                this.mDesiredHeight = 0;
                return this;
            }

            public Builder setAutoExpandBubble(boolean z) {
                setFlag(1, z);
                return this;
            }

            public Builder setSuppressNotification(boolean z) {
                setFlag(2, z);
                return this;
            }

            public Builder setDeleteIntent(PendingIntent pendingIntent) {
                this.mDeleteIntent = pendingIntent;
                return this;
            }

            public BubbleMetadata build() {
                String str = this.mShortcutId;
                if (str == null && this.mPendingIntent == null) {
                    throw new NullPointerException("Must supply pending intent or shortcut to bubble");
                } else if (str == null && this.mIcon == null) {
                    throw new NullPointerException("Must supply an icon or shortcut for the bubble");
                } else {
                    BubbleMetadata bubbleMetadata = new BubbleMetadata(this.mPendingIntent, this.mDeleteIntent, this.mIcon, this.mDesiredHeight, this.mDesiredHeightResId, this.mFlags, this.mShortcutId);
                    bubbleMetadata.setFlags(this.mFlags);
                    return bubbleMetadata;
                }
            }

            private Builder setFlag(int i, boolean z) {
                if (z) {
                    this.mFlags = i | this.mFlags;
                } else {
                    this.mFlags = (~i) & this.mFlags;
                }
                return this;
            }
        }

        private static class Api29Impl {
            private Api29Impl() {
            }

            static Notification.BubbleMetadata toPlatform(BubbleMetadata bubbleMetadata) {
                if (bubbleMetadata == null || bubbleMetadata.getIntent() == null) {
                    return null;
                }
                Notification.BubbleMetadata.Builder suppressNotification = new Notification.BubbleMetadata.Builder().setIcon(bubbleMetadata.getIcon().toIcon()).setIntent(bubbleMetadata.getIntent()).setDeleteIntent(bubbleMetadata.getDeleteIntent()).setAutoExpandBubble(bubbleMetadata.getAutoExpandBubble()).setSuppressNotification(bubbleMetadata.isNotificationSuppressed());
                if (bubbleMetadata.getDesiredHeight() != 0) {
                    suppressNotification.setDesiredHeight(bubbleMetadata.getDesiredHeight());
                }
                if (bubbleMetadata.getDesiredHeightResId() != 0) {
                    suppressNotification.setDesiredHeightResId(bubbleMetadata.getDesiredHeightResId());
                }
                return suppressNotification.build();
            }

            static BubbleMetadata fromPlatform(Notification.BubbleMetadata bubbleMetadata) {
                if (bubbleMetadata == null || bubbleMetadata.getIntent() == null) {
                    return null;
                }
                Builder suppressNotification = new Builder(bubbleMetadata.getIntent(), IconCompat.createFromIcon(bubbleMetadata.getIcon())).setAutoExpandBubble(bubbleMetadata.getAutoExpandBubble()).setDeleteIntent(bubbleMetadata.getDeleteIntent()).setSuppressNotification(bubbleMetadata.isNotificationSuppressed());
                if (bubbleMetadata.getDesiredHeight() != 0) {
                    suppressNotification.setDesiredHeight(bubbleMetadata.getDesiredHeight());
                }
                if (bubbleMetadata.getDesiredHeightResId() != 0) {
                    suppressNotification.setDesiredHeightResId(bubbleMetadata.getDesiredHeightResId());
                }
                return suppressNotification.build();
            }
        }

        private static class Api30Impl {
            private Api30Impl() {
            }

            static Notification.BubbleMetadata toPlatform(BubbleMetadata bubbleMetadata) {
                Notification.BubbleMetadata.Builder builder;
                if (bubbleMetadata == null) {
                    return null;
                }
                if (bubbleMetadata.getShortcutId() != null) {
                    builder = new Notification.BubbleMetadata.Builder(bubbleMetadata.getShortcutId());
                } else {
                    builder = new Notification.BubbleMetadata.Builder(bubbleMetadata.getIntent(), bubbleMetadata.getIcon().toIcon());
                }
                builder.setDeleteIntent(bubbleMetadata.getDeleteIntent()).setAutoExpandBubble(bubbleMetadata.getAutoExpandBubble()).setSuppressNotification(bubbleMetadata.isNotificationSuppressed());
                if (bubbleMetadata.getDesiredHeight() != 0) {
                    builder.setDesiredHeight(bubbleMetadata.getDesiredHeight());
                }
                if (bubbleMetadata.getDesiredHeightResId() != 0) {
                    builder.setDesiredHeightResId(bubbleMetadata.getDesiredHeightResId());
                }
                return builder.build();
            }

            static BubbleMetadata fromPlatform(Notification.BubbleMetadata bubbleMetadata) {
                Builder builder;
                if (bubbleMetadata == null) {
                    return null;
                }
                if (bubbleMetadata.getShortcutId() != null) {
                    builder = new Builder(bubbleMetadata.getShortcutId());
                } else {
                    builder = new Builder(bubbleMetadata.getIntent(), IconCompat.createFromIcon(bubbleMetadata.getIcon()));
                }
                builder.setAutoExpandBubble(bubbleMetadata.getAutoExpandBubble()).setDeleteIntent(bubbleMetadata.getDeleteIntent()).setSuppressNotification(bubbleMetadata.isNotificationSuppressed());
                if (bubbleMetadata.getDesiredHeight() != 0) {
                    builder.setDesiredHeight(bubbleMetadata.getDesiredHeight());
                }
                if (bubbleMetadata.getDesiredHeightResId() != 0) {
                    builder.setDesiredHeightResId(bubbleMetadata.getDesiredHeightResId());
                }
                return builder.build();
            }
        }
    }

    static Notification[] getNotificationArrayFromBundle(Bundle bundle, String str) {
        Parcelable[] parcelableArray = bundle.getParcelableArray(str);
        if ((parcelableArray instanceof Notification[]) || parcelableArray == null) {
            return (Notification[]) parcelableArray;
        }
        Notification[] notificationArr = new Notification[parcelableArray.length];
        for (int i = 0; i < parcelableArray.length; i++) {
            notificationArr[i] = (Notification) parcelableArray[i];
        }
        bundle.putParcelableArray(str, notificationArr);
        return notificationArr;
    }

    public static Bundle getExtras(Notification notification) {
        return notification.extras;
    }

    public static int getActionCount(Notification notification) {
        if (notification.actions != null) {
            return notification.actions.length;
        }
        return 0;
    }

    public static Action getAction(Notification notification, int i) {
        return getActionCompatFromAction(notification.actions[i]);
    }

    public static BubbleMetadata getBubbleMetadata(Notification notification) {
        if (Build.VERSION.SDK_INT >= 29) {
            return BubbleMetadata.fromPlatform(Api29Impl.getBubbleMetadata(notification));
        }
        return null;
    }

    static Action getActionCompatFromAction(Notification.Action action) {
        RemoteInput[] remoteInputArr;
        int i;
        Notification.Action action2 = action;
        RemoteInput[] remoteInputs = Api20Impl.getRemoteInputs(action);
        IconCompat iconCompat = null;
        boolean z = false;
        if (remoteInputs == null) {
            remoteInputArr = null;
        } else {
            RemoteInput[] remoteInputArr2 = new RemoteInput[remoteInputs.length];
            for (int i2 = 0; i2 < remoteInputs.length; i2++) {
                RemoteInput remoteInput = remoteInputs[i2];
                remoteInputArr2[i2] = new RemoteInput(Api20Impl.getResultKey(remoteInput), Api20Impl.getLabel(remoteInput), Api20Impl.getChoices(remoteInput), Api20Impl.getAllowFreeFormInput(remoteInput), Build.VERSION.SDK_INT >= 29 ? Api29Impl.getEditChoicesBeforeSending(remoteInput) : 0, Api20Impl.getExtras(remoteInput), (Set<String>) null);
            }
            remoteInputArr = remoteInputArr2;
        }
        boolean z2 = Api20Impl.getExtras(action).getBoolean("android.support.allowGeneratedReplies") || Api24Impl.getAllowGeneratedReplies(action);
        boolean z3 = Api20Impl.getExtras(action).getBoolean("android.support.action.showsUserInterface", true);
        if (Build.VERSION.SDK_INT >= 28) {
            i = Api28Impl.getSemanticAction(action);
        } else {
            i = Api20Impl.getExtras(action).getInt("android.support.action.semanticAction", 0);
        }
        int i3 = i;
        boolean isContextual = Build.VERSION.SDK_INT >= 29 ? Api29Impl.isContextual(action) : false;
        if (Build.VERSION.SDK_INT >= 31) {
            z = Api31Impl.isAuthenticationRequired(action);
        }
        boolean z4 = z;
        if (Api23Impl.getIcon(action) == null && action2.icon != 0) {
            return new Action(action2.icon, action2.title, action2.actionIntent, Api20Impl.getExtras(action), remoteInputArr, (RemoteInput[]) null, z2, i3, z3, isContextual, z4);
        }
        if (Api23Impl.getIcon(action) != null) {
            iconCompat = IconCompat.createFromIconOrNullIfZeroResId(Api23Impl.getIcon(action));
        }
        return new Action(iconCompat, action2.title, action2.actionIntent, Api20Impl.getExtras(action), remoteInputArr, (RemoteInput[]) null, z2, i3, z3, isContextual, z4);
    }

    public static List<Action> getInvisibleActions(Notification notification) {
        Bundle bundle;
        ArrayList arrayList = new ArrayList();
        Bundle bundle2 = notification.extras.getBundle("android.car.EXTENSIONS");
        if (!(bundle2 == null || (bundle = bundle2.getBundle("invisible_actions")) == null)) {
            for (int i = 0; i < bundle.size(); i++) {
                arrayList.add(NotificationCompatJellybean.getActionFromBundle(bundle.getBundle(Integer.toString(i))));
            }
        }
        return arrayList;
    }

    public static List<Person> getPeople(Notification notification) {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 28) {
            ArrayList parcelableArrayList = notification.extras.getParcelableArrayList(EXTRA_PEOPLE_LIST);
            if (parcelableArrayList != null && !parcelableArrayList.isEmpty()) {
                Iterator it = parcelableArrayList.iterator();
                while (it.hasNext()) {
                    arrayList.add(Person.fromAndroidPerson((android.app.Person) it.next()));
                }
            }
        } else {
            String[] stringArray = notification.extras.getStringArray(EXTRA_PEOPLE);
            if (!(stringArray == null || stringArray.length == 0)) {
                for (String uri : stringArray) {
                    arrayList.add(new Person.Builder().setUri(uri).build());
                }
            }
        }
        return arrayList;
    }

    public static CharSequence getContentTitle(Notification notification) {
        return notification.extras.getCharSequence(EXTRA_TITLE);
    }

    public static CharSequence getContentText(Notification notification) {
        return notification.extras.getCharSequence(EXTRA_TEXT);
    }

    public static CharSequence getContentInfo(Notification notification) {
        return notification.extras.getCharSequence(EXTRA_INFO_TEXT);
    }

    public static CharSequence getSubText(Notification notification) {
        return notification.extras.getCharSequence(EXTRA_SUB_TEXT);
    }

    public static String getCategory(Notification notification) {
        return notification.category;
    }

    public static boolean getLocalOnly(Notification notification) {
        return (notification.flags & 256) != 0;
    }

    public static String getGroup(Notification notification) {
        return Api20Impl.getGroup(notification);
    }

    public static boolean getShowWhen(Notification notification) {
        return notification.extras.getBoolean(EXTRA_SHOW_WHEN);
    }

    public static boolean getUsesChronometer(Notification notification) {
        return notification.extras.getBoolean(EXTRA_SHOW_CHRONOMETER);
    }

    public static boolean getOnlyAlertOnce(Notification notification) {
        return (notification.flags & 8) != 0;
    }

    public static boolean getAutoCancel(Notification notification) {
        return (notification.flags & 16) != 0;
    }

    public static boolean getOngoing(Notification notification) {
        return (notification.flags & 2) != 0;
    }

    public static int getColor(Notification notification) {
        return notification.color;
    }

    public static int getVisibility(Notification notification) {
        return notification.visibility;
    }

    public static Notification getPublicVersion(Notification notification) {
        return notification.publicVersion;
    }

    static boolean getHighPriority(Notification notification) {
        return (notification.flags & 128) != 0;
    }

    public static boolean isGroupSummary(Notification notification) {
        return (notification.flags & 512) != 0;
    }

    public static String getSortKey(Notification notification) {
        return Api20Impl.getSortKey(notification);
    }

    public static String getChannelId(Notification notification) {
        return Api26Impl.getChannelId(notification);
    }

    public static long getTimeoutAfter(Notification notification) {
        return Api26Impl.getTimeoutAfter(notification);
    }

    public static int getBadgeIconType(Notification notification) {
        return Api26Impl.getBadgeIconType(notification);
    }

    public static String getShortcutId(Notification notification) {
        return Api26Impl.getShortcutId(notification);
    }

    public static CharSequence getSettingsText(Notification notification) {
        return Api26Impl.getSettingsText(notification);
    }

    public static LocusIdCompat getLocusId(Notification notification) {
        LocusId locusId;
        if (Build.VERSION.SDK_INT < 29 || (locusId = Api29Impl.getLocusId(notification)) == null) {
            return null;
        }
        return LocusIdCompat.toLocusIdCompat(locusId);
    }

    public static int getGroupAlertBehavior(Notification notification) {
        return Api26Impl.getGroupAlertBehavior(notification);
    }

    public static boolean getAllowSystemGeneratedContextualActions(Notification notification) {
        if (Build.VERSION.SDK_INT >= 29) {
            return Api29Impl.getAllowSystemGeneratedContextualActions(notification);
        }
        return false;
    }

    static class Api20Impl {
        private Api20Impl() {
        }

        static boolean getAllowFreeFormInput(RemoteInput remoteInput) {
            return remoteInput.getAllowFreeFormInput();
        }

        static CharSequence[] getChoices(RemoteInput remoteInput) {
            return remoteInput.getChoices();
        }

        static CharSequence getLabel(RemoteInput remoteInput) {
            return remoteInput.getLabel();
        }

        static String getResultKey(RemoteInput remoteInput) {
            return remoteInput.getResultKey();
        }

        static RemoteInput[] getRemoteInputs(Notification.Action action) {
            return action.getRemoteInputs();
        }

        static String getSortKey(Notification notification) {
            return notification.getSortKey();
        }

        static String getGroup(Notification notification) {
            return notification.getGroup();
        }

        static Bundle getExtras(Notification.Action action) {
            return action.getExtras();
        }

        static Bundle getExtras(RemoteInput remoteInput) {
            return remoteInput.getExtras();
        }
    }

    static class Api23Impl {
        private Api23Impl() {
        }

        static Icon getIcon(Notification.Action action) {
            return action.getIcon();
        }
    }

    static class Api24Impl {
        private Api24Impl() {
        }

        static boolean getAllowGeneratedReplies(Notification.Action action) {
            return action.getAllowGeneratedReplies();
        }
    }

    static class Api26Impl {
        private Api26Impl() {
        }

        static int getGroupAlertBehavior(Notification notification) {
            return notification.getGroupAlertBehavior();
        }

        static CharSequence getSettingsText(Notification notification) {
            return notification.getSettingsText();
        }

        static String getShortcutId(Notification notification) {
            return notification.getShortcutId();
        }

        static int getBadgeIconType(Notification notification) {
            return notification.getBadgeIconType();
        }

        static long getTimeoutAfter(Notification notification) {
            return notification.getTimeoutAfter();
        }

        static String getChannelId(Notification notification) {
            return notification.getChannelId();
        }
    }

    static class Api28Impl {
        private Api28Impl() {
        }

        static int getSemanticAction(Notification.Action action) {
            return action.getSemanticAction();
        }
    }

    static class Api29Impl {
        private Api29Impl() {
        }

        static boolean getAllowSystemGeneratedContextualActions(Notification notification) {
            return notification.getAllowSystemGeneratedContextualActions();
        }

        static LocusId getLocusId(Notification notification) {
            return notification.getLocusId();
        }

        static boolean isContextual(Notification.Action action) {
            return action.isContextual();
        }

        static int getEditChoicesBeforeSending(RemoteInput remoteInput) {
            return remoteInput.getEditChoicesBeforeSending();
        }

        static Notification.BubbleMetadata getBubbleMetadata(Notification notification) {
            return notification.getBubbleMetadata();
        }
    }

    static class Api31Impl {
        private Api31Impl() {
        }

        static boolean isAuthenticationRequired(Notification.Action action) {
            return action.isAuthenticationRequired();
        }
    }
}
