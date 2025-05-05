package com.google.android.material.datepicker;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.google.android.material.R;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import kotlin.text.Typography;

abstract class DateFormatTextWatcher extends TextWatcherAdapter {
    private static final int VALIDATION_DELAY = 1000;
    private final CalendarConstraints constraints;
    private final DateFormat dateFormat;
    private final String outOfRange;
    private final Runnable setErrorCallback;
    private Runnable setRangeErrorCallback;
    private final TextInputLayout textInputLayout;

    /* access modifiers changed from: package-private */
    public void onInvalidDate() {
    }

    /* access modifiers changed from: package-private */
    public abstract void onValidDate(Long l);

    DateFormatTextWatcher(String str, DateFormat dateFormat2, TextInputLayout textInputLayout2, CalendarConstraints calendarConstraints) {
        this.dateFormat = dateFormat2;
        this.textInputLayout = textInputLayout2;
        this.constraints = calendarConstraints;
        this.outOfRange = textInputLayout2.getContext().getString(R.string.mtrl_picker_out_of_range);
        this.setErrorCallback = new DateFormatTextWatcher$$ExternalSyntheticLambda0(this, str);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-google-android-material-datepicker-DateFormatTextWatcher  reason: not valid java name */
    public /* synthetic */ void m2133lambda$new$0$comgoogleandroidmaterialdatepickerDateFormatTextWatcher(String str) {
        TextInputLayout textInputLayout2 = this.textInputLayout;
        DateFormat dateFormat2 = this.dateFormat;
        Context context = textInputLayout2.getContext();
        String string = context.getString(R.string.mtrl_picker_invalid_format);
        String format = String.format(context.getString(R.string.mtrl_picker_invalid_format_use), new Object[]{sanitizeDateString(str)});
        textInputLayout2.setError(string + "\n" + format + "\n" + String.format(context.getString(R.string.mtrl_picker_invalid_format_example), new Object[]{sanitizeDateString(dateFormat2.format(new Date(UtcDates.getTodayCalendar().getTimeInMillis())))}));
        onInvalidDate();
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.textInputLayout.removeCallbacks(this.setErrorCallback);
        this.textInputLayout.removeCallbacks(this.setRangeErrorCallback);
        this.textInputLayout.setError((CharSequence) null);
        onValidDate((Long) null);
        if (!TextUtils.isEmpty(charSequence)) {
            try {
                Date parse = this.dateFormat.parse(charSequence.toString());
                this.textInputLayout.setError((CharSequence) null);
                long time = parse.getTime();
                if (!this.constraints.getDateValidator().isValid(time) || !this.constraints.isWithinBounds(time)) {
                    Runnable createRangeErrorCallback = createRangeErrorCallback(time);
                    this.setRangeErrorCallback = createRangeErrorCallback;
                    runValidation(this.textInputLayout, createRangeErrorCallback);
                    return;
                }
                onValidDate(Long.valueOf(parse.getTime()));
            } catch (ParseException unused) {
                runValidation(this.textInputLayout, this.setErrorCallback);
            }
        }
    }

    private Runnable createRangeErrorCallback(long j) {
        return new DateFormatTextWatcher$$ExternalSyntheticLambda1(this, j);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createRangeErrorCallback$1$com-google-android-material-datepicker-DateFormatTextWatcher  reason: not valid java name */
    public /* synthetic */ void m2132lambda$createRangeErrorCallback$1$comgoogleandroidmaterialdatepickerDateFormatTextWatcher(long j) {
        this.textInputLayout.setError(String.format(this.outOfRange, new Object[]{sanitizeDateString(DateStrings.getDateString(j))}));
        onInvalidDate();
    }

    private String sanitizeDateString(String str) {
        return str.replace(' ', Typography.nbsp);
    }

    public void runValidation(View view, Runnable runnable) {
        view.postDelayed(runnable, 1000);
    }
}
