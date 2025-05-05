package expo.modules.contacts;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import androidx.core.text.util.LocalePreferences;
import com.google.firebase.messaging.Constants;
import expo.modules.contacts.models.BaseModel;
import expo.modules.contacts.models.DateModel;
import expo.modules.contacts.models.EmailModel;
import expo.modules.contacts.models.ExtraNameModel;
import expo.modules.contacts.models.ImAddressModel;
import expo.modules.contacts.models.PhoneNumberModel;
import expo.modules.contacts.models.PostalAddressModel;
import expo.modules.contacts.models.RelationshipModel;
import expo.modules.contacts.models.UrlAddressModel;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010k\u001a\u00020l2\u0006\u0010m\u001a\u00020nJ\b\u0010o\u001a\u0004\u0018\u00010\u0003J\u0006\u0010p\u001a\u00020\u0003J\u0006\u0010q\u001a\u00020\u0003J\u0012\u0010r\u001a\u00020s2\b\u0010X\u001a\u0004\u0018\u00010\u0003H\u0002J\u0010\u0010t\u001a\u00020u2\u0006\u0010v\u001a\u00020sH\u0002J\u0016\u0010w\u001a\u0012\u0012\u0004\u0012\u00020x0\u001aj\b\u0012\u0004\u0012\u00020x`\u001cJ\u0014\u0010y\u001a\u00020z2\f\u0010{\u001a\b\u0012\u0004\u0012\u00020\u00030|J\u001a\u0010}\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010x0\u001aj\n\u0012\u0006\u0012\u0004\u0018\u00010x`\u001cR \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR \u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r8BX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0014\"\u0004\b\u0017\u0010\u0004R\u000e\u0010\u0018\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R!\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u001aj\b\u0012\u0004\u0012\u00020\u001b`\u001c8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR \u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\t\"\u0004\b\"\u0010\u000bR\u001c\u0010#\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0014\"\u0004\b%\u0010\u0004R\u0010\u0010&\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R \u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\t\"\u0004\b*\u0010\u000bR \u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\t\"\u0004\b.\u0010\u000bR\u001c\u0010/\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0014\"\u0004\b1\u0010\u0004R\u001a\u00102\u001a\u000203X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R \u00108\u001a\b\u0012\u0004\u0012\u0002090\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\t\"\u0004\b;\u0010\u000bR\u001c\u0010<\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0014\"\u0004\b>\u0010\u0004R\u001c\u0010?\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u0014\"\u0004\bA\u0010\u0004R\u001c\u0010B\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0014\"\u0004\bD\u0010\u0004R\u001c\u0010E\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0014\"\u0004\bG\u0010\u0004R\u001c\u0010H\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u0014\"\u0004\bJ\u0010\u0004R \u0010K\u001a\b\u0012\u0004\u0012\u00020L0\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\t\"\u0004\bN\u0010\u000bR\u001c\u0010O\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\u0014\"\u0004\bQ\u0010\u0004R\u001c\u0010R\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\u0014\"\u0004\bT\u0010\u0004R\u001c\u0010U\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010\u0014\"\u0004\bW\u0010\u0004R\u001c\u0010X\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010\u0014\"\u0004\bZ\u0010\u0004R\u001c\u0010[\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010\u0014\"\u0004\b]\u0010\u0004R\u0010\u0010^\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010_\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R \u0010`\u001a\b\u0012\u0004\u0012\u00020a0\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010\t\"\u0004\bc\u0010\u000bR\u001c\u0010d\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010\u0014\"\u0004\bf\u0010\u0004R \u0010g\u001a\b\u0012\u0004\u0012\u00020h0\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010\t\"\u0004\bj\u0010\u000b¨\u0006~"}, d2 = {"Lexpo/modules/contacts/Contact;", "", "contactId", "", "(Ljava/lang/String;)V", "addresses", "", "Lexpo/modules/contacts/models/PostalAddressModel;", "getAddresses", "()Ljava/util/List;", "setAddresses", "(Ljava/util/List;)V", "baseModels", "", "", "Lexpo/modules/contacts/models/BaseModel;", "getBaseModels", "()[Ljava/util/List;", "company", "getCompany", "()Ljava/lang/String;", "setCompany", "getContactId", "setContactId", "contactType", "contentValues", "Ljava/util/ArrayList;", "Landroid/content/ContentValues;", "Lkotlin/collections/ArrayList;", "getContentValues", "()Ljava/util/ArrayList;", "dates", "Lexpo/modules/contacts/models/DateModel;", "getDates", "setDates", "department", "getDepartment", "setDepartment", "displayName", "emails", "Lexpo/modules/contacts/models/EmailModel;", "getEmails", "setEmails", "extraNames", "Lexpo/modules/contacts/models/ExtraNameModel;", "getExtraNames", "setExtraNames", "firstName", "getFirstName", "setFirstName", "hasPhoto", "", "getHasPhoto", "()Z", "setHasPhoto", "(Z)V", "imAddresses", "Lexpo/modules/contacts/models/ImAddressModel;", "getImAddresses", "setImAddresses", "jobTitle", "getJobTitle", "setJobTitle", "lastName", "getLastName", "setLastName", "lookupKey", "getLookupKey", "setLookupKey", "middleName", "getMiddleName", "setMiddleName", "note", "getNote", "setNote", "phones", "Lexpo/modules/contacts/models/PhoneNumberModel;", "getPhones", "setPhones", "phoneticFirstName", "getPhoneticFirstName", "setPhoneticFirstName", "phoneticLastName", "getPhoneticLastName", "setPhoneticLastName", "phoneticMiddleName", "getPhoneticMiddleName", "setPhoneticMiddleName", "photoUri", "getPhotoUri", "setPhotoUri", "prefix", "getPrefix", "setPrefix", "rawContactId", "rawPhotoUri", "relationships", "Lexpo/modules/contacts/models/RelationshipModel;", "getRelationships", "setRelationships", "suffix", "getSuffix", "setSuffix", "urlAddresses", "Lexpo/modules/contacts/models/UrlAddressModel;", "getUrlAddresses", "setUrlAddresses", "fromCursor", "", "cursor", "Landroid/database/Cursor;", "getFinalDisplayName", "getFinalFirstName", "getFinalLastName", "getThumbnailBitmap", "Landroid/graphics/Bitmap;", "toByteArray", "", "bitmap", "toInsertOperationList", "Landroid/content/ContentProviderOperation;", "toMap", "Landroid/os/Bundle;", "fieldSet", "", "toUpdateOperationList", "expo-contacts_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Contact.kt */
public final class Contact {
    private List<PostalAddressModel> addresses = new ArrayList();
    private String company = "";
    private String contactId;
    private String contactType = "person";
    private List<DateModel> dates = new ArrayList();
    private String department = "";
    private String displayName;
    private List<EmailModel> emails = new ArrayList();
    private List<ExtraNameModel> extraNames = new ArrayList();
    private String firstName = "";
    private boolean hasPhoto;
    private List<ImAddressModel> imAddresses = new ArrayList();
    private String jobTitle = "";
    private String lastName = "";
    private String lookupKey;
    private String middleName = "";
    private String note;
    private List<PhoneNumberModel> phones = new ArrayList();
    private String phoneticFirstName = "";
    private String phoneticLastName = "";
    private String phoneticMiddleName = "";
    private String photoUri;
    private String prefix = "";
    private String rawContactId;
    private String rawPhotoUri;
    private List<RelationshipModel> relationships = new ArrayList();
    private String suffix = "";
    private List<UrlAddressModel> urlAddresses = new ArrayList();

    public Contact(String str) {
        Intrinsics.checkNotNullParameter(str, "contactId");
        this.contactId = str;
    }

    public final String getContactId() {
        return this.contactId;
    }

    public final void setContactId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.contactId = str;
    }

    public final String getLookupKey() {
        return this.lookupKey;
    }

    public final void setLookupKey(String str) {
        this.lookupKey = str;
    }

    public final boolean getHasPhoto() {
        return this.hasPhoto;
    }

    public final void setHasPhoto(boolean z) {
        this.hasPhoto = z;
    }

    public final String getPhotoUri() {
        return this.photoUri;
    }

    public final void setPhotoUri(String str) {
        this.photoUri = str;
    }

    public final String getFirstName() {
        return this.firstName;
    }

    public final void setFirstName(String str) {
        this.firstName = str;
    }

    public final String getMiddleName() {
        return this.middleName;
    }

    public final void setMiddleName(String str) {
        this.middleName = str;
    }

    public final String getLastName() {
        return this.lastName;
    }

    public final void setLastName(String str) {
        this.lastName = str;
    }

    public final String getPrefix() {
        return this.prefix;
    }

    public final void setPrefix(String str) {
        this.prefix = str;
    }

    public final String getSuffix() {
        return this.suffix;
    }

    public final void setSuffix(String str) {
        this.suffix = str;
    }

    public final String getPhoneticFirstName() {
        return this.phoneticFirstName;
    }

    public final void setPhoneticFirstName(String str) {
        this.phoneticFirstName = str;
    }

    public final String getPhoneticMiddleName() {
        return this.phoneticMiddleName;
    }

    public final void setPhoneticMiddleName(String str) {
        this.phoneticMiddleName = str;
    }

    public final String getPhoneticLastName() {
        return this.phoneticLastName;
    }

    public final void setPhoneticLastName(String str) {
        this.phoneticLastName = str;
    }

    public final String getCompany() {
        return this.company;
    }

    public final void setCompany(String str) {
        this.company = str;
    }

    public final String getDepartment() {
        return this.department;
    }

    public final void setDepartment(String str) {
        this.department = str;
    }

    public final String getJobTitle() {
        return this.jobTitle;
    }

    public final void setJobTitle(String str) {
        this.jobTitle = str;
    }

    public final String getNote() {
        return this.note;
    }

    public final void setNote(String str) {
        this.note = str;
    }

    public final List<DateModel> getDates() {
        return this.dates;
    }

    public final void setDates(List<DateModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.dates = list;
    }

    public final List<EmailModel> getEmails() {
        return this.emails;
    }

    public final void setEmails(List<EmailModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.emails = list;
    }

    public final List<ImAddressModel> getImAddresses() {
        return this.imAddresses;
    }

    public final void setImAddresses(List<ImAddressModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.imAddresses = list;
    }

    public final List<PhoneNumberModel> getPhones() {
        return this.phones;
    }

    public final void setPhones(List<PhoneNumberModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.phones = list;
    }

    public final List<PostalAddressModel> getAddresses() {
        return this.addresses;
    }

    public final void setAddresses(List<PostalAddressModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.addresses = list;
    }

    public final List<RelationshipModel> getRelationships() {
        return this.relationships;
    }

    public final void setRelationships(List<RelationshipModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.relationships = list;
    }

    public final List<UrlAddressModel> getUrlAddresses() {
        return this.urlAddresses;
    }

    public final void setUrlAddresses(List<UrlAddressModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.urlAddresses = list;
    }

    public final List<ExtraNameModel> getExtraNames() {
        return this.extraNames;
    }

    public final void setExtraNames(List<ExtraNameModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.extraNames = list;
    }

    public final void fromCursor(Cursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        this.rawContactId = cursor.getString(cursor.getColumnIndexOrThrow("raw_contact_id"));
        String string = cursor.getString(cursor.getColumnIndexOrThrow(Columns.MIMETYPE));
        String string2 = cursor.getString(cursor.getColumnIndexOrThrow(Columns.DISPLAY_NAME));
        if (!TextUtils.isEmpty(string2) && TextUtils.isEmpty(this.displayName)) {
            this.displayName = string2;
        }
        boolean z = true;
        if (TextUtils.isEmpty(this.rawPhotoUri)) {
            String string3 = cursor.getString(cursor.getColumnIndexOrThrow(Columns.PHOTO_URI));
            if (!TextUtils.isEmpty(string3)) {
                this.hasPhoto = true;
                this.rawPhotoUri = string3;
            }
        }
        if (TextUtils.isEmpty(this.photoUri)) {
            String string4 = cursor.getString(cursor.getColumnIndexOrThrow(Columns.PHOTO_THUMBNAIL_URI));
            if (!TextUtils.isEmpty(string4)) {
                this.hasPhoto = true;
                this.photoUri = string4;
            }
        }
        if (string != null) {
            switch (string.hashCode()) {
                case -1569536764:
                    if (string.equals("vnd.android.cursor.item/email_v2")) {
                        EmailModel emailModel = new EmailModel();
                        emailModel.fromCursor(cursor);
                        this.emails.add(emailModel);
                        break;
                    }
                    break;
                case -1328682538:
                    if (string.equals("vnd.android.cursor.item/contact_event")) {
                        DateModel dateModel = new DateModel();
                        dateModel.fromCursor(cursor);
                        this.dates.add(dateModel);
                        break;
                    }
                    break;
                case -1079224304:
                    if (string.equals("vnd.android.cursor.item/name")) {
                        this.lookupKey = cursor.getString(cursor.getColumnIndexOrThrow("lookup"));
                        this.firstName = cursor.getString(cursor.getColumnIndexOrThrow(Columns.TYPE));
                        this.middleName = cursor.getString(cursor.getColumnIndexOrThrow(Columns.DATA_5));
                        this.lastName = cursor.getString(cursor.getColumnIndexOrThrow(Columns.LABEL));
                        this.prefix = cursor.getString(cursor.getColumnIndexOrThrow(Columns.DATA_4));
                        this.suffix = cursor.getString(cursor.getColumnIndexOrThrow(Columns.DATA_6));
                        this.phoneticFirstName = cursor.getString(cursor.getColumnIndexOrThrow(Columns.DATA_7));
                        this.phoneticMiddleName = cursor.getString(cursor.getColumnIndexOrThrow(Columns.DATA_8));
                        this.phoneticLastName = cursor.getString(cursor.getColumnIndexOrThrow(Columns.DATA_9));
                        break;
                    }
                    break;
                case -1079210633:
                    if (string.equals("vnd.android.cursor.item/note")) {
                        this.note = cursor.getString(cursor.getColumnIndexOrThrow(Columns.DATA));
                        break;
                    }
                    break;
                case -601229436:
                    if (string.equals("vnd.android.cursor.item/postal-address_v2")) {
                        PostalAddressModel postalAddressModel = new PostalAddressModel();
                        postalAddressModel.fromCursor(cursor);
                        this.addresses.add(postalAddressModel);
                        break;
                    }
                    break;
                case 456415478:
                    if (string.equals("vnd.android.cursor.item/website")) {
                        UrlAddressModel urlAddressModel = new UrlAddressModel();
                        urlAddressModel.fromCursor(cursor);
                        this.urlAddresses.add(urlAddressModel);
                        break;
                    }
                    break;
                case 684173810:
                    if (string.equals("vnd.android.cursor.item/phone_v2")) {
                        PhoneNumberModel phoneNumberModel = new PhoneNumberModel();
                        phoneNumberModel.fromCursor(cursor);
                        this.phones.add(phoneNumberModel);
                        break;
                    }
                    break;
                case 689862072:
                    if (string.equals("vnd.android.cursor.item/organization")) {
                        this.company = cursor.getString(cursor.getColumnIndexOrThrow(Columns.DATA));
                        this.jobTitle = cursor.getString(cursor.getColumnIndexOrThrow(Columns.DATA_4));
                        this.department = cursor.getString(cursor.getColumnIndexOrThrow(Columns.DATA_5));
                        break;
                    }
                    break;
                case 950831081:
                    if (string.equals("vnd.android.cursor.item/im")) {
                        ImAddressModel imAddressModel = new ImAddressModel();
                        imAddressModel.fromCursor(cursor);
                        this.imAddresses.add(imAddressModel);
                        break;
                    }
                    break;
                case 1409846529:
                    if (string.equals("vnd.android.cursor.item/relation")) {
                        RelationshipModel relationshipModel = new RelationshipModel();
                        relationshipModel.fromCursor(cursor);
                        this.relationships.add(relationshipModel);
                        break;
                    }
                    break;
                case 2034973555:
                    if (string.equals("vnd.android.cursor.item/nickname")) {
                        ExtraNameModel extraNameModel = new ExtraNameModel();
                        extraNameModel.fromCursor(cursor);
                        this.extraNames.add(extraNameModel);
                        break;
                    }
                    break;
            }
        }
        String str = this.company;
        String str2 = "person";
        if (str != null && !Intrinsics.areEqual((Object) str, (Object) "")) {
            String str3 = this.firstName;
            boolean z2 = str3 != null && !Intrinsics.areEqual((Object) str3, (Object) "");
            String str4 = this.middleName;
            boolean z3 = str4 != null && !Intrinsics.areEqual((Object) str4, (Object) "");
            String str5 = this.lastName;
            if (str5 == null || Intrinsics.areEqual((Object) str5, (Object) "")) {
                z = false;
            }
            if (!z2 && !z3 && !z) {
                str2 = "company";
            }
        }
        this.contactType = str2;
    }

    public final String getFinalFirstName() {
        String str = this.firstName;
        if (str != null) {
            return str;
        }
        String str2 = this.displayName;
        if (str2 == null) {
            return "";
        }
        Intrinsics.checkNotNull(str2);
        return str2;
    }

    public final String getFinalLastName() {
        String str = this.lastName;
        if (str != null) {
            return str;
        }
        String str2 = this.displayName;
        if (str2 == null) {
            return "";
        }
        Intrinsics.checkNotNull(str2);
        return str2;
    }

    public final String getFinalDisplayName() {
        String str;
        String str2 = this.displayName;
        if (str2 != null || (str = this.firstName) == null) {
            return str2;
        }
        if (this.lastName == null) {
            return str;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%s %s", Arrays.copyOf(new Object[]{this.firstName, this.lastName}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        CharSequence charSequence = format;
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) charSequence.charAt(!z ? i : length), 32) <= 0;
            if (!z) {
                if (!z2) {
                    z = true;
                } else {
                    i++;
                }
            } else if (!z2) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1).toString();
    }

    private final byte[] toByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }

    public final ArrayList<ContentProviderOperation> toInsertOperationList() {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        ContentProviderOperation.Builder withValue = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", (Object) null).withValue("account_name", (Object) null);
        Intrinsics.checkNotNullExpressionValue(withValue, "withValue(...)");
        arrayList.add(withValue.build());
        ContentProviderOperation.Builder withValue2 = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue(Columns.MIMETYPE, "vnd.android.cursor.item/name").withValue(Columns.DATA, this.displayName).withValue(Columns.TYPE, this.firstName).withValue(Columns.DATA_5, this.middleName).withValue(Columns.LABEL, this.lastName).withValue(Columns.DATA_7, this.phoneticFirstName).withValue(Columns.DATA_8, this.phoneticMiddleName).withValue(Columns.DATA_9, this.phoneticLastName).withValue(Columns.DATA_4, this.prefix).withValue(Columns.DATA_6, this.suffix);
        Intrinsics.checkNotNullExpressionValue(withValue2, "withValue(...)");
        arrayList.add(withValue2.build());
        ContentProviderOperation.Builder withValue3 = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue(Columns.MIMETYPE, "vnd.android.cursor.item/organization").withValue(Columns.DATA, this.company).withValue(Columns.DATA_4, this.jobTitle).withValue(Columns.DATA_5, this.department);
        Intrinsics.checkNotNullExpressionValue(withValue3, "withValue(...)");
        arrayList.add(withValue3.build());
        ContentProviderOperation.Builder withValue4 = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue(Columns.MIMETYPE, "vnd.android.cursor.item/note").withValue(Columns.DATA, this.note);
        Intrinsics.checkNotNullExpressionValue(withValue4, "withValue(...)");
        arrayList.add(withValue4.build());
        withValue4.withYieldAllowed(true);
        if (!TextUtils.isEmpty(this.photoUri) || !TextUtils.isEmpty(this.rawPhotoUri)) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue(Columns.MIMETYPE, "vnd.android.cursor.item/photo").withValue("data15", toByteArray(getThumbnailBitmap(TextUtils.isEmpty(this.rawPhotoUri) ? this.photoUri : this.rawPhotoUri))).build());
        }
        for (List<BaseModel> it : getBaseModels()) {
            for (BaseModel insertOperation : it) {
                arrayList.add(insertOperation.getInsertOperation());
            }
        }
        return arrayList;
    }

    public final ArrayList<ContentProviderOperation> toUpdateOperationList() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%s=? AND %s=?", Arrays.copyOf(new Object[]{Columns.CONTACT_ID, Columns.MIMETYPE}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        String[] strArr = {this.contactId, "vnd.android.cursor.item/name"};
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        ContentProviderOperation.Builder withValue = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection(format, strArr).withValue(Columns.DATA, this.displayName).withValue(Columns.TYPE, this.firstName).withValue(Columns.DATA_5, this.middleName).withValue(Columns.LABEL, this.lastName).withValue(Columns.DATA_7, this.phoneticFirstName).withValue(Columns.DATA_8, this.phoneticMiddleName).withValue(Columns.DATA_9, this.phoneticLastName).withValue(Columns.DATA_4, this.prefix).withValue(Columns.DATA_6, this.suffix);
        Intrinsics.checkNotNullExpressionValue(withValue, "withValue(...)");
        arrayList.add(withValue.build());
        ContentProviderOperation.Builder withValue2 = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection(format, strArr).withValue(Columns.DATA, this.company).withValue(Columns.DATA_4, this.jobTitle).withValue(Columns.DATA_5, this.department);
        Intrinsics.checkNotNullExpressionValue(withValue2, "withValue(...)");
        arrayList.add(withValue2.build());
        ContentProviderOperation.Builder withValue3 = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection(format, new String[]{this.contactId, "vnd.android.cursor.item/note"}).withValue(Columns.DATA, this.note);
        Intrinsics.checkNotNullExpressionValue(withValue3, "withValue(...)");
        arrayList.add(withValue3.build());
        withValue3.withYieldAllowed(true);
        if (!TextUtils.isEmpty(this.photoUri) || !TextUtils.isEmpty(this.rawPhotoUri)) {
            Bitmap thumbnailBitmap = getThumbnailBitmap(TextUtils.isEmpty(this.rawPhotoUri) ? this.photoUri : this.rawPhotoUri);
            arrayList.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI).withSelection(format, new String[]{this.rawContactId, "vnd.android.cursor.item/photo"}).build());
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("raw_contact_id", this.rawContactId).withValue(Columns.MIMETYPE, "vnd.android.cursor.item/photo").withValue("data15", toByteArray(thumbnailBitmap)).build());
        }
        for (List<BaseModel> it : getBaseModels()) {
            for (BaseModel baseModel : it) {
                String str = this.rawContactId;
                Intrinsics.checkNotNull(str);
                arrayList.add(baseModel.getDeleteOperation(str));
                arrayList.add(baseModel.getInsertOperation(this.rawContactId));
            }
        }
        return arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.util.List<expo.modules.contacts.models.BaseModel>[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<expo.modules.contacts.models.BaseModel>[] getBaseModels() {
        /*
            r3 = this;
            r0 = 8
            java.util.List[] r0 = new java.util.List[r0]
            r1 = 0
            java.util.List<expo.modules.contacts.models.DateModel> r2 = r3.dates
            r0[r1] = r2
            r1 = 1
            java.util.List<expo.modules.contacts.models.EmailModel> r2 = r3.emails
            r0[r1] = r2
            r1 = 2
            java.util.List<expo.modules.contacts.models.ImAddressModel> r2 = r3.imAddresses
            r0[r1] = r2
            r1 = 3
            java.util.List<expo.modules.contacts.models.PhoneNumberModel> r2 = r3.phones
            r0[r1] = r2
            r1 = 4
            java.util.List<expo.modules.contacts.models.PostalAddressModel> r2 = r3.addresses
            r0[r1] = r2
            r1 = 5
            java.util.List<expo.modules.contacts.models.RelationshipModel> r2 = r3.relationships
            r0[r1] = r2
            r1 = 6
            java.util.List<expo.modules.contacts.models.UrlAddressModel> r2 = r3.urlAddresses
            r0[r1] = r2
            r1 = 7
            java.util.List<expo.modules.contacts.models.ExtraNameModel> r2 = r3.extraNames
            r0[r1] = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.contacts.Contact.getBaseModels():java.util.List[]");
    }

    public final Bundle toMap(Set<String> set) throws ParseException {
        Iterator<DateModel> it;
        ArrayList arrayList;
        Set<String> set2 = set;
        Intrinsics.checkNotNullParameter(set2, "fieldSet");
        Bundle bundle = new Bundle();
        bundle.putString("lookupKey", this.lookupKey);
        bundle.putString("id", this.contactId);
        CharSequence charSequence = this.displayName;
        bundle.putString("name", !(charSequence == null || charSequence.length() == 0) ? this.displayName : this.firstName + " " + this.lastName);
        String str = this.firstName;
        if (str != null) {
            if (!(str.length() > 0)) {
                str = null;
            }
            if (str != null) {
                bundle.putString("firstName", str);
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            }
        }
        String str2 = this.middleName;
        if (str2 != null) {
            if (!(str2.length() > 0)) {
                str2 = null;
            }
            if (str2 != null) {
                bundle.putString("middleName", str2);
                Unit unit3 = Unit.INSTANCE;
                Unit unit4 = Unit.INSTANCE;
            }
        }
        String str3 = this.lastName;
        if (str3 != null) {
            if (!(str3.length() > 0)) {
                str3 = null;
            }
            if (str3 != null) {
                bundle.putString("lastName", str3);
                Unit unit5 = Unit.INSTANCE;
                Unit unit6 = Unit.INSTANCE;
            }
        }
        String str4 = this.suffix;
        if (str4 != null) {
            if (!(str4.length() > 0)) {
                str4 = null;
            }
            if (str4 != null) {
                bundle.putString("nameSuffix", str4);
                Unit unit7 = Unit.INSTANCE;
                Unit unit8 = Unit.INSTANCE;
            }
        }
        String str5 = this.prefix;
        if (str5 != null) {
            if (!(str5.length() > 0)) {
                str5 = null;
            }
            if (str5 != null) {
                bundle.putString("namePrefix", str5);
                Unit unit9 = Unit.INSTANCE;
                Unit unit10 = Unit.INSTANCE;
            }
        }
        String str6 = this.phoneticFirstName;
        if (str6 != null) {
            if (!(str6.length() > 0)) {
                str6 = null;
            }
            if (str6 != null) {
                bundle.putString("phoneticFirstName", str6);
                Unit unit11 = Unit.INSTANCE;
                Unit unit12 = Unit.INSTANCE;
            }
        }
        String str7 = this.phoneticLastName;
        if (str7 != null) {
            if (!(str7.length() > 0)) {
                str7 = null;
            }
            if (str7 != null) {
                bundle.putString("phoneticLastName", str7);
                Unit unit13 = Unit.INSTANCE;
                Unit unit14 = Unit.INSTANCE;
            }
        }
        String str8 = this.phoneticMiddleName;
        if (str8 != null) {
            if (!(str8.length() > 0)) {
                str8 = null;
            }
            if (str8 != null) {
                bundle.putString("phoneticMiddleName", str8);
                Unit unit15 = Unit.INSTANCE;
                Unit unit16 = Unit.INSTANCE;
            }
        }
        bundle.putString("contactType", this.contactType);
        String str9 = this.company;
        if (str9 != null) {
            if (!(str9.length() > 0)) {
                str9 = null;
            }
            if (str9 != null) {
                bundle.putString("company", str9);
                Unit unit17 = Unit.INSTANCE;
                Unit unit18 = Unit.INSTANCE;
            }
        }
        String str10 = this.jobTitle;
        if (str10 != null) {
            if (!(str10.length() > 0)) {
                str10 = null;
            }
            if (str10 != null) {
                bundle.putString("jobTitle", str10);
                Unit unit19 = Unit.INSTANCE;
                Unit unit20 = Unit.INSTANCE;
            }
        }
        String str11 = this.department;
        if (str11 != null) {
            if (!(str11.length() > 0)) {
                str11 = null;
            }
            if (str11 != null) {
                bundle.putString("department", str11);
                Unit unit21 = Unit.INSTANCE;
                Unit unit22 = Unit.INSTANCE;
            }
        }
        bundle.putBoolean("imageAvailable", this.hasPhoto);
        if (set2.contains("image") && this.photoUri != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("uri", this.photoUri);
            Unit unit23 = Unit.INSTANCE;
            bundle.putBundle("image", bundle2);
        }
        if (set2.contains("rawImage") && this.rawPhotoUri != null) {
            Bundle bundle3 = new Bundle();
            bundle3.putString("uri", this.rawPhotoUri);
            Unit unit24 = Unit.INSTANCE;
            bundle.putBundle("image", bundle3);
        }
        if (set2.contains("note") && !TextUtils.isEmpty(this.note)) {
            bundle.putString("note", this.note);
        }
        if (set2.contains("phoneNumbers") && this.phones.size() > 0) {
            Iterable<PhoneNumberModel> iterable = this.phones;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (PhoneNumberModel map : iterable) {
                arrayList2.add(map.getMap());
            }
            bundle.putParcelableArrayList("phoneNumbers", new ArrayList((List) arrayList2));
        }
        if (set2.contains("emails") && this.emails.size() > 0) {
            Iterable<EmailModel> iterable2 = this.emails;
            Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
            for (EmailModel map2 : iterable2) {
                arrayList3.add(map2.getMap());
            }
            bundle.putParcelableArrayList("emails", new ArrayList((List) arrayList3));
        }
        if (set2.contains("addresses") && this.addresses.size() > 0) {
            Iterable<PostalAddressModel> iterable3 = this.addresses;
            Collection arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
            for (PostalAddressModel map3 : iterable3) {
                arrayList4.add(map3.getMap());
            }
            bundle.putParcelableArrayList("addresses", new ArrayList((List) arrayList4));
        }
        if (set2.contains("instantMessageAddresses") && this.imAddresses.size() > 0) {
            Iterable<ImAddressModel> iterable4 = this.imAddresses;
            Collection arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable4, 10));
            for (ImAddressModel map4 : iterable4) {
                arrayList5.add(map4.getMap());
            }
            bundle.putParcelableArrayList("instantMessageAddresses", new ArrayList((List) arrayList5));
        }
        if (set2.contains("urlAddresses") && this.urlAddresses.size() > 0) {
            Iterable<UrlAddressModel> iterable5 = this.urlAddresses;
            Collection arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable5, 10));
            for (UrlAddressModel map5 : iterable5) {
                arrayList6.add(map5.getMap());
            }
            bundle.putParcelableArrayList("urlAddresses", new ArrayList((List) arrayList6));
        }
        if (set2.contains("relationships") && this.relationships.size() > 0) {
            Iterable<RelationshipModel> iterable6 = this.relationships;
            Collection arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable6, 10));
            for (RelationshipModel map6 : iterable6) {
                arrayList7.add(map6.getMap());
            }
            bundle.putParcelableArrayList("relationships", new ArrayList((List) arrayList7));
        }
        if (this.extraNames.size() > 0) {
            boolean contains = set2.contains("nickname");
            boolean contains2 = set2.contains("maidenName");
            int size = this.extraNames.size();
            for (int i = 0; i < size; i++) {
                ExtraNameModel extraNameModel = this.extraNames.get(i);
                String data = extraNameModel.getData();
                String label = extraNameModel.getLabel();
                if (contains2 && label != null && Intrinsics.areEqual((Object) label, (Object) "maidenName") && !TextUtils.isEmpty(data)) {
                    bundle.putString(label, data);
                }
                if (contains && label != null && Intrinsics.areEqual((Object) label, (Object) "nickname") && !TextUtils.isEmpty(data)) {
                    bundle.putString(label, data);
                }
            }
        }
        boolean contains3 = set2.contains("birthday");
        boolean contains4 = set2.contains("dates");
        if (contains4 || contains3) {
            ArrayList arrayList8 = new ArrayList();
            ArrayList arrayList9 = new ArrayList();
            Iterator<DateModel> it2 = this.dates.iterator();
            while (it2.hasNext()) {
                DateModel next = it2.next();
                Calendar instance = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("--MM-dd", Locale.getDefault());
                Bundle bundle4 = new Bundle();
                String data2 = next.getData();
                String label2 = next.getLabel();
                Bundle bundle5 = new Bundle();
                bundle5.putString("type", label2);
                bundle5.putString("value", data2);
                arrayList8.add(bundle5);
                try {
                    Intrinsics.checkNotNull(data2);
                    arrayList = arrayList8;
                    it = it2;
                    try {
                        boolean z = !StringsKt.startsWith$default(data2, "--", false, 2, (Object) null);
                        if (z) {
                            Date parse = simpleDateFormat.parse(data2);
                            Intrinsics.checkNotNull(parse);
                            instance.setTime(parse);
                        } else {
                            Date parse2 = simpleDateFormat2.parse(data2);
                            Intrinsics.checkNotNull(parse2);
                            instance.setTime(parse2);
                        }
                        if (z) {
                            try {
                                bundle4.putInt("year", instance.get(1));
                            } catch (Exception e) {
                                e = e;
                                SentryLogcatAdapter.w("Contact", e.toString());
                                it2 = it;
                                arrayList8 = arrayList;
                            }
                        }
                        bundle4.putInt("month", instance.get(2));
                        bundle4.putInt("day", instance.get(5));
                        bundle4.putString("format", LocalePreferences.CalendarType.GREGORIAN);
                        if (!contains3 || label2 == null || !Intrinsics.areEqual((Object) label2, (Object) "birthday")) {
                            bundle4.putString(Constants.ScionAnalytics.PARAM_LABEL, label2);
                            arrayList9.add(bundle4);
                            it2 = it;
                            arrayList8 = arrayList;
                        } else {
                            bundle.putBundle("birthday", bundle4);
                            it2 = it;
                            arrayList8 = arrayList;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        SentryLogcatAdapter.w("Contact", e.toString());
                        it2 = it;
                        arrayList8 = arrayList;
                    }
                } catch (Exception e3) {
                    e = e3;
                    arrayList = arrayList8;
                    it = it2;
                    SentryLogcatAdapter.w("Contact", e.toString());
                    it2 = it;
                    arrayList8 = arrayList;
                }
            }
            ArrayList arrayList10 = arrayList8;
            if (contains4 && arrayList9.size() > 0) {
                bundle.putParcelableArrayList("dates", arrayList9);
            }
            if (arrayList10.size() > 0) {
                bundle.putParcelableArrayList("rawDates", arrayList10);
            }
        }
        return bundle;
    }

    public final ArrayList<ContentValues> getContentValues() {
        ArrayList<ContentValues> arrayList = new ArrayList<>();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.MIMETYPE, "vnd.android.cursor.item/identity");
        contentValues.put(Columns.TYPE, this.firstName);
        contentValues.put(Columns.DATA_5, this.middleName);
        contentValues.put(Columns.LABEL, this.lastName);
        contentValues.put(Columns.DATA_4, this.prefix);
        contentValues.put(Columns.DATA_6, this.suffix);
        contentValues.put(Columns.DATA_7, this.phoneticFirstName);
        contentValues.put(Columns.DATA_8, this.phoneticMiddleName);
        contentValues.put(Columns.DATA_9, this.phoneticLastName);
        arrayList.add(contentValues);
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(Columns.MIMETYPE, "vnd.android.cursor.item/organization");
        contentValues2.put(Columns.DATA, this.company);
        contentValues2.put(Columns.DATA_4, this.jobTitle);
        contentValues2.put(Columns.DATA_5, this.department);
        arrayList.add(contentValues2);
        ContentValues contentValues3 = new ContentValues();
        contentValues3.put(Columns.MIMETYPE, "vnd.android.cursor.item/note");
        contentValues3.put(Columns.DATA, this.note);
        arrayList.add(contentValues3);
        CharSequence charSequence = this.photoUri;
        boolean z = true;
        if (!(charSequence == null || StringsKt.isBlank(charSequence))) {
            Bitmap thumbnailBitmap = getThumbnailBitmap(Uri.parse(this.photoUri).getPath());
            ContentValues contentValues4 = new ContentValues();
            contentValues4.put(Columns.MIMETYPE, "vnd.android.cursor.item/photo");
            contentValues4.put("data15", toByteArray(thumbnailBitmap));
            arrayList.add(contentValues4);
        }
        CharSequence charSequence2 = this.rawPhotoUri;
        if (charSequence2 != null && !StringsKt.isBlank(charSequence2)) {
            z = false;
        }
        if (!z) {
            Bitmap thumbnailBitmap2 = getThumbnailBitmap(this.rawPhotoUri);
            ContentValues contentValues5 = new ContentValues();
            contentValues5.put(Columns.MIMETYPE, "vnd.android.cursor.item/photo");
            contentValues5.put("data15", toByteArray(thumbnailBitmap2));
            arrayList.add(contentValues5);
        }
        for (List<BaseModel> it : getBaseModels()) {
            for (BaseModel contentValues6 : it) {
                arrayList.add(contentValues6.getContentValues());
            }
        }
        return arrayList;
    }

    private final Bitmap getThumbnailBitmap(String str) {
        Bitmap decodeFile = BitmapFactory.decodeFile(Uri.parse(str).getPath());
        Intrinsics.checkNotNullExpressionValue(decodeFile, "decodeFile(...)");
        return decodeFile;
    }
}
