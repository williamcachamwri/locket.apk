package expo.modules.contacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import androidx.tracing.Trace;
import expo.modules.contacts.models.BaseModel;
import expo.modules.contacts.models.DateModel;
import expo.modules.contacts.models.EmailModel;
import expo.modules.contacts.models.ExtraNameModel;
import expo.modules.contacts.models.ImAddressModel;
import expo.modules.contacts.models.PhoneNumberModel;
import expo.modules.contacts.models.PostalAddressModel;
import expo.modules.contacts.models.RelationshipModel;
import expo.modules.contacts.models.UrlAddressModel;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.ActivityEventListener;
import expo.modules.core.interfaces.ActivityProvider;
import expo.modules.interfaces.permissions.Permissions;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001BB\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0002JQ\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010&2\b\u0010'\u001a\u0004\u0018\u00010\u00192\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\b\u0010(\u001a\u0004\u0018\u00010\u0019H\u0002¢\u0006\u0002\u0010)J\u0012\u0010*\u001a\u0004\u0018\u00010!2\u0006\u0010+\u001a\u00020,H\u0002J\"\u0010-\u001a\u0004\u0018\u00010.2\b\u0010/\u001a\u0004\u0018\u00010\u00192\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J*\u00100\u001a\u0004\u0018\u00010!2\u0006\u00101\u001a\u00020\u00192\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\b\u0010(\u001a\u0004\u0018\u00010\u0019H\u0002J\u0014\u00102\u001a\u0004\u0018\u00010\u00192\b\u0010/\u001a\u0004\u0018\u00010\u0019H\u0002J\u001c\u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020.042\u0006\u00105\u001a\u000206H\u0002J&\u00107\u001a\u00020.2\b\u00108\u001a\u0004\u0018\u00010.2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020:04H\u0002J\u0018\u0010;\u001a\u00020\u001d2\u0006\u00108\u001a\u00020.2\u0006\u0010<\u001a\u00020\fH\u0002J\u0010\u0010=\u001a\u00020\u001d2\u0006\u00108\u001a\u00020.H\u0002J:\u0010>\u001a\u0012\u0012\u0004\u0012\u00020.0?j\b\u0012\u0004\u0012\u00020.`@2\u0016\u0010A\u001a\u0012\u0012\u0004\u0012\u00020.0?j\b\u0012\u0004\u0012\u00020.`@2\b\u0010(\u001a\u0004\u0018\u00010\u0019H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128BX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006C"}, d2 = {"Lexpo/modules/contacts/ContactsModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "activity", "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "mActivityEventListener", "Lexpo/modules/core/interfaces/ActivityEventListener;", "mModuleRegistry", "Lexpo/modules/core/ModuleRegistry;", "mPendingPromise", "Lexpo/modules/kotlin/Promise;", "permissionsManager", "Lexpo/modules/interfaces/permissions/Permissions;", "getPermissionsManager", "()Lexpo/modules/interfaces/permissions/Permissions;", "resolver", "Landroid/content/ContentResolver;", "getResolver", "()Landroid/content/ContentResolver;", "createProjectionForQuery", "Lexpo/modules/contacts/QueryArguments;", "keysToFetch", "", "", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "ensurePermissions", "", "ensureReadPermission", "ensureWritePermission", "fetchContacts", "Lexpo/modules/contacts/ContactPage;", "pageOffset", "", "pageSize", "queryStrings", "", "queryField", "sortOrder", "(II[Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;Ljava/lang/String;)Lexpo/modules/contacts/ContactPage;", "getAllContactsAsync", "options", "Lexpo/modules/contacts/ContactQuery;", "getContactById", "Lexpo/modules/contacts/Contact;", "contactId", "getContactByName", "query", "getLookupKeyForContactId", "loadContactsFrom", "", "cursor", "Landroid/database/Cursor;", "mutateContact", "contact", "data", "", "presentEditForm", "promise", "presentForm", "sortContactsBy", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "input", "ContactsActivityEventListener", "expo-contacts_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContactsModule.kt */
public final class ContactsModule extends Module {
    /* access modifiers changed from: private */
    public final ActivityEventListener mActivityEventListener = new ContactsActivityEventListener();
    private ModuleRegistry mModuleRegistry;
    /* access modifiers changed from: private */
    public Promise mPendingPromise;

    /* access modifiers changed from: private */
    public final Permissions getPermissionsManager() {
        Permissions permissions = getAppContext().getPermissions();
        if (permissions != null) {
            return permissions;
        }
        throw new Exceptions.PermissionsModuleNotFound();
    }

    /* access modifiers changed from: private */
    public final Activity getActivity() {
        ActivityProvider activityProvider = getAppContext().getActivityProvider();
        Activity currentActivity = activityProvider != null ? activityProvider.getCurrentActivity() : null;
        if (currentActivity != null) {
            return currentActivity;
        }
        throw new Exceptions.MissingActivity();
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        AsyncFunction asyncFunction3;
        AsyncFunction asyncFunction4;
        AsyncFunction asyncFunction5;
        AsyncFunction asyncFunction6;
        AsyncFunction asyncFunction7;
        AsyncFunction asyncFunction8;
        AsyncFunction asyncFunction9;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoContacts");
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new ContactsModule$definition$lambda$13$$inlined$OnCreate$1(this)));
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new ContactsModule$definition$lambda$13$$inlined$OnDestroy$1(this)));
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("requestPermissionsAsync", new AnyType[0], new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("requestPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$2.INSTANCE))}, new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("requestPermissionsAsync", asyncFunction);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("getPermissionsAsync", new AnyType[0], new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$4(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("getPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$5.INSTANCE))}, new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$6(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("getPermissionsAsync", asyncFunction2);
            ObjectDefinitionBuilder objectDefinitionBuilder3 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction3 = new AsyncFunctionWithPromiseComponent("getContactsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ContactQuery.class), false, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$7.INSTANCE))}, new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$8(this));
            } else {
                asyncFunction3 = new AsyncFunctionComponent("getContactsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ContactQuery.class), false, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$9.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$10.INSTANCE))}, new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$11(this));
            }
            objectDefinitionBuilder3.getAsyncFunctions().put("getContactsAsync", asyncFunction3);
            ObjectDefinitionBuilder objectDefinitionBuilder4 = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction4 = new AsyncFunctionWithPromiseComponent("addContactAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Map.class), false, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$12.INSTANCE))}, new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$13(this));
            } else {
                asyncFunction4 = new AsyncFunctionComponent("addContactAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Map.class), false, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$14.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$15.INSTANCE))}, new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$16(this));
            }
            objectDefinitionBuilder4.getAsyncFunctions().put("addContactAsync", asyncFunction4);
            ObjectDefinitionBuilder objectDefinitionBuilder5 = moduleDefinitionBuilder;
            if (Map.class == Promise.class) {
                asyncFunction5 = new AsyncFunctionWithPromiseComponent("updateContactAsync", new AnyType[0], new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$17(this));
            } else {
                asyncFunction5 = new AsyncFunctionComponent("updateContactAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Map.class), false, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$18.INSTANCE))}, new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$19(this));
            }
            objectDefinitionBuilder5.getAsyncFunctions().put("updateContactAsync", asyncFunction5);
            ObjectDefinitionBuilder objectDefinitionBuilder6 = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction6 = new AsyncFunctionWithPromiseComponent("removeContactAsync", new AnyType[0], new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$20(this));
            } else {
                asyncFunction6 = new AsyncFunctionComponent("removeContactAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$21.INSTANCE))}, new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$22(this));
            }
            objectDefinitionBuilder6.getAsyncFunctions().put("removeContactAsync", asyncFunction6);
            ObjectDefinitionBuilder objectDefinitionBuilder7 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction7 = new AsyncFunctionWithPromiseComponent("shareContactAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$23.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$24.INSTANCE))}, new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$25(this));
            } else {
                asyncFunction7 = new AsyncFunctionComponent("shareContactAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$26.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$27.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$28.INSTANCE))}, new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$29(this));
            }
            objectDefinitionBuilder7.getAsyncFunctions().put("shareContactAsync", asyncFunction7);
            ObjectDefinitionBuilder objectDefinitionBuilder8 = moduleDefinitionBuilder;
            if (Map.class == Promise.class) {
                asyncFunction8 = new AsyncFunctionWithPromiseComponent("writeContactToFileAsync", new AnyType[0], new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$30(this));
            } else {
                asyncFunction8 = new AsyncFunctionComponent("writeContactToFileAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Map.class), false, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$31.INSTANCE))}, new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$32(this));
            }
            objectDefinitionBuilder8.getAsyncFunctions().put("writeContactToFileAsync", asyncFunction8);
            ObjectDefinitionBuilder objectDefinitionBuilder9 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction9 = new AsyncFunctionWithPromiseComponent("presentFormAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$33.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Map.class), false, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$34.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Map.class), true, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$35.INSTANCE))}, new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$36(this));
            } else {
                asyncFunction9 = new AsyncFunctionComponent("presentFormAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$37.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Map.class), false, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$38.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Map.class), true, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$39.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ContactsModule$definition$lambda$13$$inlined$AsyncFunction$40.INSTANCE))}, new ContactsModule$definition$lambda$13$$inlined$AsyncFunction$41(this));
            }
            objectDefinitionBuilder9.getAsyncFunctions().put("presentFormAsync", asyncFunction9);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public final void presentForm(Contact contact) {
        Intent intent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
        intent.putExtra("name", contact.getFinalDisplayName());
        intent.putParcelableArrayListExtra("data", contact.getContentValues());
        intent.setFlags(268435456);
        ModuleRegistry moduleRegistry = this.mModuleRegistry;
        Intrinsics.checkNotNull(moduleRegistry);
        ((ActivityProvider) moduleRegistry.getModule(ActivityProvider.class)).getCurrentActivity().startActivity(intent);
    }

    /* access modifiers changed from: private */
    public final void presentEditForm(Contact contact, Promise promise) {
        Uri lookupUri = ContactsContract.Contacts.getLookupUri(Long.parseLong(contact.getContactId()), contact.getLookupKey());
        Intent intent = new Intent("android.intent.action.EDIT");
        intent.setDataAndType(lookupUri, "vnd.android.cursor.item/contact");
        ModuleRegistry moduleRegistry = this.mModuleRegistry;
        Intrinsics.checkNotNull(moduleRegistry);
        this.mPendingPromise = promise;
        ((ActivityProvider) moduleRegistry.getModule(ActivityProvider.class)).getCurrentActivity().startActivityForResult(intent, ContactsModuleKt.RC_EDIT_CONTACT);
    }

    /* access modifiers changed from: private */
    public final ContentResolver getResolver() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            ContentResolver contentResolver = reactContext.getContentResolver();
            Intrinsics.checkNotNullExpressionValue(contentResolver, "getContentResolver(...)");
            return contentResolver;
        }
        throw new Exceptions.ReactContextLost();
    }

    /* access modifiers changed from: private */
    public final Contact mutateContact(Contact contact, Map<String, ? extends Object> map) {
        if (contact == null) {
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
            contact = new Contact(uuid);
        }
        String str = (String) ContactsModuleKt.safeGet(map, "firstName");
        if (str != null) {
            contact.setFirstName(str);
        }
        String str2 = (String) ContactsModuleKt.safeGet(map, "middleName");
        if (str2 != null) {
            contact.setMiddleName(str2);
        }
        String str3 = (String) ContactsModuleKt.safeGet(map, "lastName");
        if (str3 != null) {
            contact.setLastName(str3);
        }
        String str4 = (String) ContactsModuleKt.safeGet(map, "namePrefix");
        if (str4 != null) {
            contact.setPrefix(str4);
        }
        String str5 = (String) ContactsModuleKt.safeGet(map, "nameSuffix");
        if (str5 != null) {
            contact.setSuffix(str5);
        }
        String str6 = (String) ContactsModuleKt.safeGet(map, "phoneticFirstName");
        if (str6 != null) {
            contact.setPhoneticFirstName(str6);
        }
        String str7 = (String) ContactsModuleKt.safeGet(map, "phoneticMiddleName");
        if (str7 != null) {
            contact.setPhoneticMiddleName(str7);
        }
        String str8 = (String) ContactsModuleKt.safeGet(map, "phoneticLastName");
        if (str8 != null) {
            contact.setPhoneticLastName(str8);
        }
        String str9 = (String) ContactsModuleKt.safeGet(map, "company");
        if (str9 != null) {
            contact.setCompany(str9);
        }
        String str10 = (String) ContactsModuleKt.safeGet(map, "jobTitle");
        if (str10 != null) {
            contact.setJobTitle(str10);
        }
        String str11 = (String) ContactsModuleKt.safeGet(map, "department");
        if (str11 != null) {
            contact.setDepartment(str11);
        }
        String str12 = (String) ContactsModuleKt.safeGet(map, "note");
        if (str12 != null) {
            contact.setNote(str12);
        }
        if (map.containsKey("image")) {
            Object obj = map.get("image");
            if (obj instanceof String) {
                contact.setPhotoUri((String) obj);
                contact.setHasPhoto(true);
            } else if (obj instanceof Map) {
                Map map2 = (Map) obj;
                if (map2.containsKey("uri")) {
                    contact.setPhotoUri((String) map2.get("uri"));
                    contact.setHasPhoto(true);
                }
            }
        }
        List<PostalAddressModel> decodeList = BaseModel.Companion.decodeList((List) ContactsModuleKt.safeGet(map, "addresses"), PostalAddressModel.class);
        if (decodeList != null) {
            contact.setAddresses(decodeList);
        }
        List<PhoneNumberModel> decodeList2 = BaseModel.Companion.decodeList((List) ContactsModuleKt.safeGet(map, "phoneNumbers"), PhoneNumberModel.class);
        if (decodeList2 != null) {
            contact.setPhones(decodeList2);
        }
        List<EmailModel> decodeList3 = BaseModel.Companion.decodeList((List) ContactsModuleKt.safeGet(map, "emails"), EmailModel.class);
        if (decodeList3 != null) {
            contact.setEmails(decodeList3);
        }
        List<ImAddressModel> decodeList4 = BaseModel.Companion.decodeList((List) ContactsModuleKt.safeGet(map, "instantMessageAddresses"), ImAddressModel.class);
        if (decodeList4 != null) {
            contact.setImAddresses(decodeList4);
        }
        List<UrlAddressModel> decodeList5 = BaseModel.Companion.decodeList((List) ContactsModuleKt.safeGet(map, "urlAddresses"), UrlAddressModel.class);
        if (decodeList5 != null) {
            contact.setUrlAddresses(decodeList5);
        }
        List<ExtraNameModel> decodeList6 = BaseModel.Companion.decodeList((List) ContactsModuleKt.safeGet(map, "extraNames"), ExtraNameModel.class);
        if (decodeList6 != null) {
            contact.setExtraNames(decodeList6);
        }
        List<DateModel> decodeList7 = BaseModel.Companion.decodeList((List) ContactsModuleKt.safeGet(map, "dates"), DateModel.class);
        if (decodeList7 != null) {
            contact.setDates(decodeList7);
        }
        List<RelationshipModel> decodeList8 = BaseModel.Companion.decodeList((List) ContactsModuleKt.safeGet(map, "relationships"), RelationshipModel.class);
        if (decodeList8 != null) {
            contact.setRelationships(decodeList8);
        }
        return contact;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003e, code lost:
        kotlin.io.CloseableKt.closeFinally(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0041, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getLookupKeyForContactId(java.lang.String r7) {
        /*
            r6 = this;
            android.content.ContentResolver r0 = r6.getResolver()
            android.net.Uri r1 = android.provider.ContactsContract.Contacts.CONTENT_URI
            java.lang.String r2 = "lookup"
            java.lang.String[] r2 = new java.lang.String[]{r2}
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "_id = "
            r3.<init>(r4)
            java.lang.StringBuilder r7 = r3.append(r7)
            java.lang.String r3 = r7.toString()
            r4 = 0
            r5 = 0
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5)
            r0 = 0
            if (r7 == 0) goto L_0x0042
            java.io.Closeable r7 = (java.io.Closeable) r7
            r1 = r7
            android.database.Cursor r1 = (android.database.Cursor) r1     // Catch:{ all -> 0x003b }
            boolean r2 = r1.moveToFirst()     // Catch:{ all -> 0x003b }
            if (r2 == 0) goto L_0x0035
            r2 = 0
            java.lang.String r1 = r1.getString(r2)     // Catch:{ all -> 0x003b }
            goto L_0x0036
        L_0x0035:
            r1 = r0
        L_0x0036:
            kotlin.io.CloseableKt.closeFinally(r7, r0)
            r0 = r1
            goto L_0x0042
        L_0x003b:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x003d }
        L_0x003d:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r7, r0)
            throw r1
        L_0x0042:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.contacts.ContactsModule.getLookupKeyForContactId(java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0047, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0048, code lost:
        kotlin.io.CloseableKt.closeFinally(r7, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004b, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final expo.modules.contacts.Contact getContactById(java.lang.String r7, java.util.Set<java.lang.String> r8) {
        /*
            r6 = this;
            expo.modules.contacts.QueryArguments r8 = r6.createProjectionForQuery(r8)
            java.lang.String r3 = "contact_id = ?"
            android.content.ContentResolver r0 = r6.getResolver()
            android.net.Uri r1 = android.provider.ContactsContract.Data.CONTENT_URI
            java.lang.String[] r2 = r8.getProjection()
            java.lang.String[] r4 = new java.lang.String[]{r7}
            r5 = 0
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5)
            r8 = 0
            if (r7 == 0) goto L_0x004c
            java.io.Closeable r7 = (java.io.Closeable) r7
            r0 = r7
            android.database.Cursor r0 = (android.database.Cursor) r0     // Catch:{ all -> 0x0045 }
            java.util.Map r0 = r6.loadContactsFrom(r0)     // Catch:{ all -> 0x0045 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0045 }
            java.util.Collection r0 = r0.values()     // Catch:{ all -> 0x0045 }
            r1.<init>(r0)     // Catch:{ all -> 0x0045 }
            int r0 = r1.size()     // Catch:{ all -> 0x0045 }
            if (r0 <= 0) goto L_0x003f
            r0 = 0
            java.lang.Object r0 = r1.get(r0)     // Catch:{ all -> 0x0045 }
            expo.modules.contacts.Contact r0 = (expo.modules.contacts.Contact) r0     // Catch:{ all -> 0x0045 }
            kotlin.io.CloseableKt.closeFinally(r7, r8)
            return r0
        L_0x003f:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0045 }
            kotlin.io.CloseableKt.closeFinally(r7, r8)
            goto L_0x004c
        L_0x0045:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r7, r8)
            throw r0
        L_0x004c:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.contacts.ContactsModule.getContactById(java.lang.String, java.util.Set):expo.modules.contacts.Contact");
    }

    /* access modifiers changed from: private */
    public final ContactPage getContactByName(String str, Set<String> set, String str2) {
        return fetchContacts(0, 9999, new String[]{str}, Columns.DISPLAY_NAME, set, str2);
    }

    /* access modifiers changed from: private */
    public final ContactPage getAllContactsAsync(ContactQuery contactQuery) {
        return fetchContacts(contactQuery.getPageOffset(), contactQuery.getPageSize(), (String[]) null, (String) null, contactQuery.getFields(), contactQuery.getSort());
    }

    private final QueryArguments createProjectionForQuery(Set<String> set) {
        String str;
        Set<String> set2 = set;
        List arrayList = new ArrayList(ContactsModuleKt.DEFAULT_PROJECTION);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.listOf("vnd.android.cursor.item/name", "vnd.android.cursor.item/organization"));
        if (set2.contains("phoneNumbers")) {
            arrayList.add(Columns.DATA);
            arrayList.add(Columns.TYPE);
            arrayList.add(Columns.LABEL);
            arrayList.add(Columns.IS_PRIMARY);
            arrayList.add(Columns.ID);
            arrayList2.add("vnd.android.cursor.item/phone_v2");
            str = "mimetype=? OR mimetype=? OR mimetype=?";
        } else {
            str = "mimetype=? OR mimetype=?";
        }
        if (set2.contains("emails")) {
            arrayList.add(Columns.DATA);
            arrayList.add(Columns.DATA);
            arrayList.add(Columns.TYPE);
            arrayList.add(Columns.LABEL);
            arrayList.add(Columns.IS_PRIMARY);
            arrayList.add(Columns.ID);
            str = str + " OR mimetype=?";
            arrayList2.add("vnd.android.cursor.item/email_v2");
        }
        if (set2.contains("addresses")) {
            arrayList.add(Columns.DATA);
            arrayList.add(Columns.TYPE);
            arrayList.add(Columns.LABEL);
            arrayList.add(Columns.DATA_4);
            arrayList.add(Columns.DATA_5);
            arrayList.add(Columns.DATA_6);
            arrayList.add(Columns.DATA_7);
            arrayList.add(Columns.DATA_8);
            arrayList.add(Columns.DATA_9);
            arrayList.add(Columns.DATA_10);
            str = str + " OR mimetype=?";
            arrayList2.add("vnd.android.cursor.item/postal-address_v2");
        }
        if (set2.contains("note")) {
            str = str + " OR mimetype=?";
            arrayList2.add("vnd.android.cursor.item/note");
        }
        if (set2.contains("birthday") || set2.contains("dates")) {
            str = str + " OR mimetype=?";
            arrayList2.add("vnd.android.cursor.item/contact_event");
        }
        if (set2.contains("instantMessageAddresses")) {
            arrayList.add(Columns.DATA);
            arrayList.add(Columns.TYPE);
            arrayList.add(Columns.DATA_5);
            arrayList.add(Columns.ID);
            str = str + " OR mimetype=?";
            arrayList2.add("vnd.android.cursor.item/im");
        }
        if (set2.contains("urlAddresses")) {
            arrayList.add(Columns.DATA);
            arrayList.add(Columns.TYPE);
            arrayList.add(Columns.ID);
            str = str + " OR mimetype=?";
            arrayList2.add("vnd.android.cursor.item/website");
        }
        if (set2.contains("extraNames")) {
            str = str + " OR mimetype=?";
            arrayList2.add("vnd.android.cursor.item/nickname");
        }
        if (set2.contains("relationships")) {
            arrayList.add(Columns.DATA);
            arrayList.add(Columns.TYPE);
            arrayList.add(Columns.ID);
            str = str + " OR mimetype=?";
            arrayList2.add("vnd.android.cursor.item/relation");
        }
        if (set2.contains("phoneticFirstName")) {
            arrayList.add(Columns.DATA_7);
        }
        if (set2.contains("phoneticLastName")) {
            arrayList.add(Columns.DATA_9);
        }
        if (set2.contains("phoneticMiddleName")) {
            arrayList.add(Columns.DATA_8);
        }
        if (set2.contains("namePrefix")) {
            arrayList.add(Columns.DATA_4);
        }
        if (set2.contains("nameSuffix")) {
            arrayList.add(Columns.DATA_6);
        }
        return new QueryArguments((String[]) arrayList.toArray(new String[0]), str, (String[]) arrayList2.toArray(new String[0]));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ae, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00af, code lost:
        kotlin.io.CloseableKt.closeFinally(r12, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00b2, code lost:
        throw r11;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00b3 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final expo.modules.contacts.ContactPage fetchContacts(int r10, int r11, java.lang.String[] r12, java.lang.String r13, java.util.Set<java.lang.String> r14, java.lang.String r15) {
        /*
            r9 = this;
            if (r13 != 0) goto L_0x0004
            java.lang.String r13 = "contact_id"
        L_0x0004:
            r0 = 1
            r1 = 0
            if (r11 != 0) goto L_0x000a
            r2 = r0
            goto L_0x000b
        L_0x000a:
            r2 = r1
        L_0x000b:
            expo.modules.contacts.QueryArguments r14 = r9.createProjectionForQuery(r14)
            android.content.ContentResolver r3 = r9.getResolver()
            if (r12 == 0) goto L_0x0020
            int r4 = r12.length
            if (r4 != 0) goto L_0x001a
            r4 = r0
            goto L_0x001b
        L_0x001a:
            r4 = r1
        L_0x001b:
            if (r4 == 0) goto L_0x001e
            goto L_0x0020
        L_0x001e:
            r4 = r1
            goto L_0x0021
        L_0x0020:
            r4 = r0
        L_0x0021:
            if (r4 != 0) goto L_0x0043
            java.lang.String[] r5 = r14.getProjection()
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.StringBuilder r13 = r14.append(r13)
            java.lang.String r14 = " LIKE ?"
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r6 = r13.toString()
            android.net.Uri r4 = android.provider.ContactsContract.Data.CONTENT_URI
            r8 = 0
            r7 = r12
            android.database.Cursor r12 = r3.query(r4, r5, r6, r7, r8)
            goto L_0x0056
        L_0x0043:
            android.net.Uri r4 = android.provider.ContactsContract.Data.CONTENT_URI
            java.lang.String[] r5 = r14.getProjection()
            java.lang.String r6 = r14.getSelection()
            java.lang.String[] r7 = r14.getSelectionArgs()
            r8 = 0
            android.database.Cursor r12 = r3.query(r4, r5, r6, r7, r8)
        L_0x0056:
            r13 = 0
            if (r12 == 0) goto L_0x00b3
            java.io.Closeable r12 = (java.io.Closeable) r12
            r14 = r12
            android.database.Cursor r14 = (android.database.Cursor) r14     // Catch:{ all -> 0x00ac }
            java.util.Map r14 = r9.loadContactsFrom(r14)     // Catch:{ all -> 0x00ac }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x00ac }
            r3.<init>()     // Catch:{ all -> 0x00ac }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x00ac }
            java.util.Collection r14 = r14.values()     // Catch:{ all -> 0x00ac }
            r4.<init>(r14)     // Catch:{ all -> 0x00ac }
            java.util.ArrayList r14 = r9.sortContactsBy(r4, r15)     // Catch:{ all -> 0x00ac }
            int r15 = r14.size()     // Catch:{ all -> 0x00ac }
            if (r2 == 0) goto L_0x007c
            r4 = r1
            goto L_0x007d
        L_0x007c:
            r4 = r10
        L_0x007d:
            if (r4 >= r15) goto L_0x0097
            java.lang.Object r5 = r14.get(r4)     // Catch:{ all -> 0x00ac }
            java.lang.String r6 = "get(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)     // Catch:{ all -> 0x00ac }
            expo.modules.contacts.Contact r5 = (expo.modules.contacts.Contact) r5     // Catch:{ all -> 0x00ac }
            if (r2 != 0) goto L_0x0091
            int r6 = r4 - r10
            if (r6 < r11) goto L_0x0091
            goto L_0x0097
        L_0x0091:
            r3.add(r5)     // Catch:{ all -> 0x00ac }
            int r4 = r4 + 1
            goto L_0x007d
        L_0x0097:
            expo.modules.contacts.ContactPage r14 = new expo.modules.contacts.ContactPage     // Catch:{ all -> 0x00ac }
            java.util.List r3 = (java.util.List) r3     // Catch:{ all -> 0x00ac }
            if (r10 <= 0) goto L_0x009f
            r2 = r0
            goto L_0x00a0
        L_0x009f:
            r2 = r1
        L_0x00a0:
            int r10 = r10 + r11
            if (r10 >= r15) goto L_0x00a4
            goto L_0x00a5
        L_0x00a4:
            r0 = r1
        L_0x00a5:
            r14.<init>(r3, r2, r0, r15)     // Catch:{ all -> 0x00ac }
            kotlin.io.CloseableKt.closeFinally(r12, r13)
            return r14
        L_0x00ac:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x00ae }
        L_0x00ae:
            r11 = move-exception
            kotlin.io.CloseableKt.closeFinally(r12, r10)
            throw r11
        L_0x00b3:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.contacts.ContactsModule.fetchContacts(int, int, java.lang.String[], java.lang.String, java.util.Set, java.lang.String):expo.modules.contacts.ContactPage");
    }

    private final ArrayList<Contact> sortContactsBy(ArrayList<Contact> arrayList, String str) {
        if (Intrinsics.areEqual((Object) str, (Object) "firstName")) {
            CollectionsKt.sortWith(arrayList, new ContactsModule$$ExternalSyntheticLambda0(ContactsModule$sortContactsBy$1.INSTANCE));
        } else if (Intrinsics.areEqual((Object) str, (Object) "lastName")) {
            CollectionsKt.sortWith(arrayList, new ContactsModule$$ExternalSyntheticLambda1(ContactsModule$sortContactsBy$2.INSTANCE));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static final int sortContactsBy$lambda$37(Function2 function2, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(function2, "$tmp0");
        return ((Number) function2.invoke(obj, obj2)).intValue();
    }

    /* access modifiers changed from: private */
    public static final int sortContactsBy$lambda$38(Function2 function2, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(function2, "$tmp0");
        return ((Number) function2.invoke(obj, obj2)).intValue();
    }

    private final Map<String, Contact> loadContactsFrom(Cursor cursor) {
        Map<String, Contact> linkedHashMap = new LinkedHashMap<>();
        while (cursor.moveToNext()) {
            String string = cursor.getString(cursor.getColumnIndex(Columns.CONTACT_ID));
            if (!linkedHashMap.containsKey(string)) {
                Intrinsics.checkNotNull(string);
                linkedHashMap.put(string, new Contact(string));
            }
            Contact contact = linkedHashMap.get(string);
            Intrinsics.checkNotNull(contact);
            contact.fromCursor(cursor);
        }
        return linkedHashMap;
    }

    /* access modifiers changed from: private */
    public final void ensureReadPermission() {
        if (!getPermissionsManager().hasGrantedPermissions("android.permission.READ_CONTACTS")) {
            throw new MissingPermissionException("android.permission.READ_CONTACTS");
        }
    }

    private final void ensureWritePermission() {
        if (!getPermissionsManager().hasGrantedPermissions("android.permission.WRITE_CONTACTS")) {
            throw new MissingPermissionException("android.permission.WRITE_CONTACTS");
        }
    }

    /* access modifiers changed from: private */
    public final void ensurePermissions() {
        ensureReadPermission();
        ensureWritePermission();
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\r"}, d2 = {"Lexpo/modules/contacts/ContactsModule$ContactsActivityEventListener;", "Lexpo/modules/core/interfaces/ActivityEventListener;", "(Lexpo/modules/contacts/ContactsModule;)V", "onActivityResult", "", "activity", "Landroid/app/Activity;", "requestCode", "", "resultCode", "intent", "Landroid/content/Intent;", "onNewIntent", "expo-contacts_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ContactsModule.kt */
    private final class ContactsActivityEventListener implements ActivityEventListener {
        public void onNewIntent(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
        }

        public ContactsActivityEventListener() {
        }

        public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Promise access$getMPendingPromise$p = ContactsModule.this.mPendingPromise;
            if (access$getMPendingPromise$p != null && i == 2137) {
                access$getMPendingPromise$p.resolve(0);
            }
        }
    }
}
