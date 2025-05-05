package expo.modules.notifications.notifications.presentation.builders;

import android.app.PendingIntent;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;
import expo.modules.notifications.notifications.model.NotificationAction;
import expo.modules.notifications.notifications.model.NotificationCategory;
import expo.modules.notifications.notifications.model.NotificationContent;
import expo.modules.notifications.notifications.model.TextInputNotificationAction;
import expo.modules.notifications.service.NotificationsService;
import expo.modules.notifications.service.delegates.SharedPreferencesNotificationCategoriesStore;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CategoryAwareNotificationBuilder extends ExpoNotificationBuilder {
    protected SharedPreferencesNotificationCategoriesStore mStore;

    public CategoryAwareNotificationBuilder(Context context, SharedPreferencesNotificationCategoriesStore sharedPreferencesNotificationCategoriesStore) {
        super(context);
        this.mStore = sharedPreferencesNotificationCategoriesStore;
    }

    /* access modifiers changed from: protected */
    public NotificationCompat.Builder createBuilder() {
        NotificationCompat.Builder createBuilder = super.createBuilder();
        NotificationContent notificationContent = getNotificationContent();
        String categoryId = notificationContent.getCategoryId();
        if (categoryId != null) {
            addActionsToBuilder(createBuilder, categoryId);
        }
        if (notificationContent.getBadgeCount() != null) {
            createBuilder.setNumber(notificationContent.getBadgeCount().intValue());
        }
        return createBuilder;
    }

    /* access modifiers changed from: protected */
    public void addActionsToBuilder(NotificationCompat.Builder builder, String str) {
        List<NotificationAction> emptyList = Collections.emptyList();
        try {
            NotificationCategory notificationCategory = this.mStore.getNotificationCategory(str);
            if (notificationCategory != null) {
                emptyList = notificationCategory.getActions();
            }
        } catch (IOException | ClassNotFoundException e) {
            SentryLogcatAdapter.e("expo-notifications", String.format("Could not read category with identifier: %s. %s", new Object[]{str, e.getMessage()}));
            e.printStackTrace();
        }
        for (NotificationAction next : emptyList) {
            if (next instanceof TextInputNotificationAction) {
                builder.addAction(buildTextInputAction((TextInputNotificationAction) next));
            } else {
                builder.addAction(buildButtonAction(next));
            }
        }
    }

    /* access modifiers changed from: protected */
    public NotificationCompat.Action buildButtonAction(NotificationAction notificationAction) {
        return new NotificationCompat.Action.Builder(super.getIcon(), (CharSequence) notificationAction.getTitle(), NotificationsService.Companion.createNotificationResponseIntent(getContext(), getNotification(), notificationAction)).build();
    }

    /* access modifiers changed from: protected */
    public NotificationCompat.Action buildTextInputAction(TextInputNotificationAction textInputNotificationAction) {
        PendingIntent createNotificationResponseIntent = NotificationsService.Companion.createNotificationResponseIntent(getContext(), getNotification(), textInputNotificationAction);
        return new NotificationCompat.Action.Builder(super.getIcon(), (CharSequence) textInputNotificationAction.getTitle(), createNotificationResponseIntent).addRemoteInput(new RemoteInput.Builder(NotificationsService.USER_TEXT_RESPONSE_KEY).setLabel(textInputNotificationAction.getPlaceholder()).build()).build();
    }
}
