package com.google.mlkit.vision.barcode.common.internal;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.mlkit.vision.barcode.common.Barcode;

/* compiled from: com.google.mlkit:barcode-scanning-common@@17.0.0 */
public interface BarcodeSource {
    Rect getBoundingBox();

    Barcode.CalendarEvent getCalendarEvent();

    Barcode.ContactInfo getContactInfo();

    Point[] getCornerPoints();

    String getDisplayValue();

    Barcode.DriverLicense getDriverLicense();

    Barcode.Email getEmail();

    int getFormat();

    Barcode.GeoPoint getGeoPoint();

    Barcode.Phone getPhone();

    byte[] getRawBytes();

    String getRawValue();

    Barcode.Sms getSms();

    Barcode.UrlBookmark getUrl();

    int getValueType();

    Barcode.WiFi getWifi();
}
