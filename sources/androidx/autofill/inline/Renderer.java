package androidx.autofill.inline;

import android.app.PendingIntent;
import android.app.slice.Slice;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.autofill.inline.common.SlicedContent;
import androidx.autofill.inline.v1.InlineSuggestionUi;
import io.sentry.android.core.SentryLogcatAdapter;

public final class Renderer {
    private static final String TAG = "Renderer";

    public static Bundle getSupportedInlineUiVersionsAsBundle() {
        Bundle bundle = new Bundle();
        VersionUtils.writeSupportedVersions(bundle);
        return bundle;
    }

    public static View render(Context context, Slice slice, Bundle bundle) {
        String version = SlicedContent.getVersion(slice);
        if (!VersionUtils.isVersionSupported(version)) {
            SentryLogcatAdapter.w(TAG, "Content version unsupported.");
            return null;
        }
        Bundle readStyleByVersion = VersionUtils.readStyleByVersion(bundle, version);
        if (readStyleByVersion == null) {
            SentryLogcatAdapter.w(TAG, "Cannot find a style with the same version as the slice.");
            return null;
        }
        version.hashCode();
        if (!version.equals(UiVersions.INLINE_UI_VERSION_1)) {
            SentryLogcatAdapter.w(TAG, "Renderer does not support the style/content version: " + version);
            return null;
        }
        InlineSuggestionUi.Style fromBundle = InlineSuggestionUi.fromBundle(readStyleByVersion);
        InlineSuggestionUi.Content fromSlice = InlineSuggestionUi.fromSlice(slice);
        if (fromBundle == null || slice == null) {
            return null;
        }
        return InlineSuggestionUi.render(context, fromSlice, fromBundle);
    }

    public static PendingIntent getAttributionIntent(Slice slice) {
        String version = SlicedContent.getVersion(slice);
        if (!VersionUtils.isVersionSupported(version)) {
            SentryLogcatAdapter.w(TAG, "Content version unsupported.");
            return null;
        }
        version.hashCode();
        if (!version.equals(UiVersions.INLINE_UI_VERSION_1)) {
            SentryLogcatAdapter.w(TAG, "Renderer does not support the content version: " + version);
            return null;
        }
        InlineSuggestionUi.Content fromSlice = InlineSuggestionUi.fromSlice(slice);
        if (fromSlice == null) {
            return null;
        }
        return InlineSuggestionUi.getAttributionIntent(fromSlice);
    }

    private Renderer() {
    }
}
