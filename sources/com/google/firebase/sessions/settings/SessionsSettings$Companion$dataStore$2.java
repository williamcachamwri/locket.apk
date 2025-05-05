package com.google.firebase.sessions.settings;

import androidx.datastore.core.CorruptionException;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesFactory;
import com.google.firebase.sessions.ProcessDetailsProvider;
import io.sentry.android.core.SentryLogcatAdapter;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.apache.commons.io.FilenameUtils;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/datastore/preferences/core/Preferences;", "ex", "Landroidx/datastore/core/CorruptionException;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionsSettings.kt */
final class SessionsSettings$Companion$dataStore$2 extends Lambda implements Function1<CorruptionException, Preferences> {
    public static final SessionsSettings$Companion$dataStore$2 INSTANCE = new SessionsSettings$Companion$dataStore$2();

    SessionsSettings$Companion$dataStore$2() {
        super(1);
    }

    public final Preferences invoke(CorruptionException corruptionException) {
        Intrinsics.checkNotNullParameter(corruptionException, "ex");
        SentryLogcatAdapter.w("SessionsSettings", "CorruptionException in settings DataStore in " + ProcessDetailsProvider.INSTANCE.getProcessName$com_google_firebase_firebase_sessions() + FilenameUtils.EXTENSION_SEPARATOR, corruptionException);
        return PreferencesFactory.createEmpty();
    }
}
