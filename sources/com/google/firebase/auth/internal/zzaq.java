package com.google.firebase.auth.internal;

import android.text.TextUtils;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.extractor.ts.TsExtractor;
import com.facebook.soloader.Elf64;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.iid.InstanceID;
import com.google.firebase.FirebaseError;
import java.util.Arrays;
import java.util.List;
import kotlin.text.Typography;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzaq {
    private static Status zza(String str, String str2) {
        int i;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2130504259:
                if (str.equals("USER_CANCELLED")) {
                    c = 0;
                    break;
                }
                break;
            case -2065866930:
                if (str.equals("INVALID_RECIPIENT_EMAIL")) {
                    c = 1;
                    break;
                }
                break;
            case -2014808264:
                if (str.equals("WEB_CONTEXT_ALREADY_PRESENTED")) {
                    c = 2;
                    break;
                }
                break;
            case -2005236790:
                if (str.equals("INTERNAL_SUCCESS_SIGN_OUT")) {
                    c = 3;
                    break;
                }
                break;
            case -2001169389:
                if (str.equals("INVALID_IDP_RESPONSE")) {
                    c = 4;
                    break;
                }
                break;
            case -1944433728:
                if (str.equals("DYNAMIC_LINK_NOT_ACTIVATED")) {
                    c = 5;
                    break;
                }
                break;
            case -1800638118:
                if (str.equals("QUOTA_EXCEEDED")) {
                    c = 6;
                    break;
                }
                break;
            case -1774756919:
                if (str.equals("WEB_NETWORK_REQUEST_FAILED")) {
                    c = 7;
                    break;
                }
                break;
            case -1699246888:
                if (str.equals("INVALID_RECAPTCHA_VERSION")) {
                    c = 8;
                    break;
                }
                break;
            case -1603818979:
                if (str.equals("RECAPTCHA_NOT_ENABLED")) {
                    c = 9;
                    break;
                }
                break;
            case -1587614300:
                if (str.equals("EXPIRED_OOB_CODE")) {
                    c = 10;
                    break;
                }
                break;
            case -1584641425:
                if (str.equals("UNAUTHORIZED_DOMAIN")) {
                    c = 11;
                    break;
                }
                break;
            case -1583894766:
                if (str.equals("INVALID_OOB_CODE")) {
                    c = 12;
                    break;
                }
                break;
            case -1458751677:
                if (str.equals("MISSING_EMAIL")) {
                    c = 13;
                    break;
                }
                break;
            case -1421414571:
                if (str.equals("INVALID_CODE")) {
                    c = 14;
                    break;
                }
                break;
            case -1368998244:
                if (str.equals("INVALID_HOSTING_LINK_DOMAIN")) {
                    c = 15;
                    break;
                }
                break;
            case -1345867105:
                if (str.equals("TOKEN_EXPIRED")) {
                    c = 16;
                    break;
                }
                break;
            case -1340100504:
                if (str.equals("INVALID_TENANT_ID")) {
                    c = 17;
                    break;
                }
                break;
            case -1242922234:
                if (str.equals("ALTERNATE_CLIENT_IDENTIFIER_REQUIRED")) {
                    c = 18;
                    break;
                }
                break;
            case -1232010689:
                if (str.equals("INVALID_SESSION_INFO")) {
                    c = 19;
                    break;
                }
                break;
            case -1202691903:
                if (str.equals("SECOND_FACTOR_EXISTS")) {
                    c = 20;
                    break;
                }
                break;
            case -1112393964:
                if (str.equals("INVALID_EMAIL")) {
                    c = 21;
                    break;
                }
                break;
            case -1063710844:
                if (str.equals("ADMIN_ONLY_OPERATION")) {
                    c = 22;
                    break;
                }
                break;
            case -974503964:
                if (str.equals("MISSING_OR_INVALID_NONCE")) {
                    c = 23;
                    break;
                }
                break;
            case -863830559:
                if (str.equals("INVALID_CERT_HASH")) {
                    c = 24;
                    break;
                }
                break;
            case -828507413:
                if (str.equals("NO_SUCH_PROVIDER")) {
                    c = 25;
                    break;
                }
                break;
            case -749743758:
                if (str.equals("MFA_ENROLLMENT_NOT_FOUND")) {
                    c = 26;
                    break;
                }
                break;
            case -736207500:
                if (str.equals("MISSING_PASSWORD")) {
                    c = 27;
                    break;
                }
                break;
            case -646022241:
                if (str.equals("CREDENTIAL_TOO_OLD_LOGIN_AGAIN")) {
                    c = 28;
                    break;
                }
                break;
            case -595928767:
                if (str.equals(InstanceID.ERROR_TIMEOUT)) {
                    c = 29;
                    break;
                }
                break;
            case -505579581:
                if (str.equals("INVALID_REQ_TYPE")) {
                    c = 30;
                    break;
                }
                break;
            case -406804866:
                if (str.equals("INVALID_LOGIN_CREDENTIALS")) {
                    c = 31;
                    break;
                }
                break;
            case -380728810:
                if (str.equals("INVALID_RECAPTCHA_ACTION")) {
                    c = ' ';
                    break;
                }
                break;
            case -333672188:
                if (str.equals("OPERATION_NOT_ALLOWED")) {
                    c = '!';
                    break;
                }
                break;
            case -294485423:
                if (str.equals("WEB_INTERNAL_ERROR")) {
                    c = '\"';
                    break;
                }
                break;
            case -217128228:
                if (str.equals("SECOND_FACTOR_LIMIT_EXCEEDED")) {
                    c = '#';
                    break;
                }
                break;
            case -122667194:
                if (str.equals("MISSING_MFA_ENROLLMENT_ID")) {
                    c = Typography.dollar;
                    break;
                }
                break;
            case -75433118:
                if (str.equals("USER_NOT_FOUND")) {
                    c = '%';
                    break;
                }
                break;
            case -52772551:
                if (str.equals("CAPTCHA_CHECK_FAILED")) {
                    c = Typography.amp;
                    break;
                }
                break;
            case -40686718:
                if (str.equals("WEAK_PASSWORD")) {
                    c = '\'';
                    break;
                }
                break;
            case 15352275:
                if (str.equals("EMAIL_NOT_FOUND")) {
                    c = '(';
                    break;
                }
                break;
            case 210308040:
                if (str.equals("UNSUPPORTED_FIRST_FACTOR")) {
                    c = ')';
                    break;
                }
                break;
            case 269327773:
                if (str.equals("INVALID_SENDER")) {
                    c = '*';
                    break;
                }
                break;
            case 278802867:
                if (str.equals("MISSING_PHONE_NUMBER")) {
                    c = '+';
                    break;
                }
                break;
            case 408411681:
                if (str.equals("INVALID_DYNAMIC_LINK_DOMAIN")) {
                    c = AbstractJsonLexerKt.COMMA;
                    break;
                }
                break;
            case 423563023:
                if (str.equals("MISSING_MFA_PENDING_CREDENTIAL")) {
                    c = '-';
                    break;
                }
                break;
            case 429251986:
                if (str.equals("UNSUPPORTED_PASSTHROUGH_OPERATION")) {
                    c = FilenameUtils.EXTENSION_SEPARATOR;
                    break;
                }
                break;
            case 483847807:
                if (str.equals("EMAIL_EXISTS")) {
                    c = IOUtils.DIR_SEPARATOR_UNIX;
                    break;
                }
                break;
            case 491979549:
                if (str.equals("INVALID_ID_TOKEN")) {
                    c = '0';
                    break;
                }
                break;
            case 492072102:
                if (str.equals("WEB_STORAGE_UNSUPPORTED")) {
                    c = '1';
                    break;
                }
                break;
            case 492515765:
                if (str.equals("MISSING_CLIENT_TYPE")) {
                    c = '2';
                    break;
                }
                break;
            case 530628231:
                if (str.equals("MISSING_RECAPTCHA_VERSION")) {
                    c = '3';
                    break;
                }
                break;
            case 542728406:
                if (str.equals("PASSWORD_LOGIN_DISABLED")) {
                    c = '4';
                    break;
                }
                break;
            case 582457886:
                if (str.equals("UNVERIFIED_EMAIL")) {
                    c = '5';
                    break;
                }
                break;
            case 605031096:
                if (str.equals("REJECTED_CREDENTIAL")) {
                    c = '6';
                    break;
                }
                break;
            case 745638750:
                if (str.equals("INVALID_MFA_PENDING_CREDENTIAL")) {
                    c = '7';
                    break;
                }
                break;
            case 786916712:
                if (str.equals("INVALID_VERIFICATION_PROOF")) {
                    c = '8';
                    break;
                }
                break;
            case 799258561:
                if (str.equals("INVALID_PROVIDER_ID")) {
                    c = '9';
                    break;
                }
                break;
            case 819646646:
                if (str.equals("CREDENTIAL_MISMATCH")) {
                    c = AbstractJsonLexerKt.COLON;
                    break;
                }
                break;
            case 844240628:
                if (str.equals("WEB_CONTEXT_CANCELED")) {
                    c = ';';
                    break;
                }
                break;
            case 886186878:
                if (str.equals("REQUIRES_SECOND_FACTOR_AUTH")) {
                    c = Typography.less;
                    break;
                }
                break;
            case 895302372:
                if (str.equals("MISSING_CLIENT_IDENTIFIER")) {
                    c = '=';
                    break;
                }
                break;
            case 922685102:
                if (str.equals("INVALID_MESSAGE_PAYLOAD")) {
                    c = Typography.greater;
                    break;
                }
                break;
            case 989000548:
                if (str.equals("RESET_PASSWORD_EXCEED_LIMIT")) {
                    c = '?';
                    break;
                }
                break;
            case 1034932393:
                if (str.equals("INVALID_PENDING_TOKEN")) {
                    c = '@';
                    break;
                }
                break;
            case 1072360691:
                if (str.equals("INVALID_CUSTOM_TOKEN")) {
                    c = 'A';
                    break;
                }
                break;
            case 1094975491:
                if (str.equals("INVALID_PASSWORD")) {
                    c = 'B';
                    break;
                }
                break;
            case 1107081238:
                if (str.equals("<<Network Error>>")) {
                    c = 'C';
                    break;
                }
                break;
            case 1113992697:
                if (str.equals("INVALID_RECAPTCHA_TOKEN")) {
                    c = 'D';
                    break;
                }
                break;
            case 1141576252:
                if (str.equals("SESSION_EXPIRED")) {
                    c = 'E';
                    break;
                }
                break;
            case 1199811910:
                if (str.equals("MISSING_CODE")) {
                    c = 'F';
                    break;
                }
                break;
            case 1226505451:
                if (str.equals("FEDERATED_USER_ID_ALREADY_LINKED")) {
                    c = 'G';
                    break;
                }
                break;
            case 1308491624:
                if (str.equals("MISSING_RECAPTCHA_TOKEN")) {
                    c = 'H';
                    break;
                }
                break;
            case 1388786705:
                if (str.equals("INVALID_IDENTIFIER")) {
                    c = 'I';
                    break;
                }
                break;
            case 1433767024:
                if (str.equals("USER_DISABLED")) {
                    c = 'J';
                    break;
                }
                break;
            case 1442968770:
                if (str.equals("INVALID_PHONE_NUMBER")) {
                    c = 'K';
                    break;
                }
                break;
            case 1494923453:
                if (str.equals("INVALID_APP_CREDENTIAL")) {
                    c = 'L';
                    break;
                }
                break;
            case 1497901284:
                if (str.equals("TOO_MANY_ATTEMPTS_TRY_LATER")) {
                    c = 'M';
                    break;
                }
                break;
            case 1803454477:
                if (str.equals("MISSING_CONTINUE_URI")) {
                    c = 'N';
                    break;
                }
                break;
            case 1898790704:
                if (str.equals("MISSING_SESSION_INFO")) {
                    c = 'O';
                    break;
                }
                break;
            case 2063209097:
                if (str.equals("EMAIL_CHANGE_NEEDS_VERIFICATION")) {
                    c = 'P';
                    break;
                }
                break;
            case 2082564316:
                if (str.equals("UNSUPPORTED_TENANT_OPERATION")) {
                    c = 'Q';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                i = 18001;
                break;
            case 1:
                i = 17033;
                break;
            case 2:
                i = 17057;
                break;
            case 3:
                i = 17091;
                break;
            case 4:
            case 31:
            case '@':
                i = FirebaseError.ERROR_INVALID_CREDENTIAL;
                break;
            case 5:
                i = 17068;
                break;
            case 6:
                i = 17052;
                break;
            case 7:
                i = 17061;
                break;
            case 8:
                i = 17206;
                break;
            case 9:
                i = 17200;
                break;
            case 10:
                i = 17029;
                break;
            case 11:
                i = 17038;
                break;
            case 12:
                i = 17030;
                break;
            case 13:
                i = 17034;
                break;
            case 14:
                i = 17044;
                break;
            case 15:
                i = 17214;
                break;
            case 16:
                i = FirebaseError.ERROR_USER_TOKEN_EXPIRED;
                break;
            case 17:
                i = 17079;
                break;
            case 18:
                i = 18002;
                break;
            case 19:
                i = 17046;
                break;
            case 20:
                i = 17087;
                break;
            case 21:
            case 'I':
                i = FirebaseError.ERROR_INVALID_EMAIL;
                break;
            case 22:
                i = 17085;
                break;
            case 23:
                i = 17094;
                break;
            case 24:
                i = 17064;
                break;
            case 25:
                i = FirebaseError.ERROR_NO_SUCH_PROVIDER;
                break;
            case 26:
                i = 17084;
                break;
            case 27:
                i = 17035;
                break;
            case 28:
                i = FirebaseError.ERROR_REQUIRES_RECENT_LOGIN;
                break;
            case 29:
            case 'C':
                i = FirebaseError.ERROR_NETWORK_REQUEST_FAILED;
                break;
            case 30:
                i = 17207;
                break;
            case ' ':
                i = 17203;
                break;
            case '!':
            case '4':
                i = FirebaseError.ERROR_OPERATION_NOT_ALLOWED;
                break;
            case '\"':
                i = 17062;
                break;
            case '#':
                i = 17088;
                break;
            case '$':
                i = 17082;
                break;
            case '%':
            case '(':
                i = FirebaseError.ERROR_USER_NOT_FOUND;
                break;
            case '&':
                i = 17056;
                break;
            case '\'':
                i = FirebaseError.ERROR_WEAK_PASSWORD;
                break;
            case ')':
                i = 17089;
                break;
            case '*':
                i = 17032;
                break;
            case '+':
                i = 17041;
                break;
            case ',':
                i = 17074;
                break;
            case '-':
                i = 17081;
                break;
            case '.':
                i = 17095;
                break;
            case '/':
                i = FirebaseError.ERROR_EMAIL_ALREADY_IN_USE;
                break;
            case '0':
                i = FirebaseError.ERROR_INVALID_USER_TOKEN;
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX:
                i = 17065;
                break;
            case '2':
                i = 17204;
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG:
                i = 17205;
                break;
            case '5':
                i = 17086;
                break;
            case '6':
                i = 17075;
                break;
            case '7':
                i = 17083;
                break;
            case '8':
                i = 17049;
                break;
            case '9':
                i = 17071;
                break;
            case Elf64.Ehdr.E_SHENTSIZE /*58*/:
                i = FirebaseError.ERROR_CUSTOM_TOKEN_MISMATCH;
                break;
            case ';':
                i = 17058;
                break;
            case '<':
                i = 17078;
                break;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /*61*/:
                i = 17093;
                break;
            case Elf64.Ehdr.E_SHSTRNDX /*62*/:
                i = 17031;
                break;
            case HtmlCompat.FROM_HTML_MODE_COMPACT:
            case 'M':
                i = FirebaseError.ERROR_TOO_MANY_REQUESTS;
                break;
            case 'A':
                i = FirebaseError.ERROR_INVALID_CUSTOM_TOKEN;
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT:
                i = FirebaseError.ERROR_WRONG_PASSWORD;
                break;
            case 'D':
                i = 17202;
                break;
            case 'E':
                i = 17051;
                break;
            case 'F':
                i = 17043;
                break;
            case TsExtractor.TS_SYNC_BYTE /*71*/:
                i = FirebaseError.ERROR_CREDENTIAL_ALREADY_IN_USE;
                break;
            case 'H':
                i = 17201;
                break;
            case 'J':
                i = FirebaseError.ERROR_USER_DISABLED;
                break;
            case MdtaMetadataEntry.TYPE_INDICATOR_8_BIT_UNSIGNED_INT:
                i = 17042;
                break;
            case 'L':
                i = FirebaseError.ERROR_APP_NOT_AUTHORIZED;
                break;
            case MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64:
                i = 17040;
                break;
            case 'O':
                i = 17045;
                break;
            case 'P':
                i = 17090;
                break;
            case 'Q':
                i = 17073;
                break;
            default:
                i = 17499;
                break;
        }
        if (i != 17499) {
            return new Status(i, str2);
        }
        if (str2 != null) {
            return new Status(i, str + ":" + str2);
        }
        return new Status(i, str);
    }

    public static Status zza(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return new Status(FirebaseError.ERROR_INTERNAL_ERROR);
        }
        String[] split = str.split(":", 2);
        split[0] = split[0].trim();
        if (split.length > 1 && (str2 = split[1]) != null) {
            split[1] = str2.trim();
        }
        List asList = Arrays.asList(split);
        if (asList.size() > 1) {
            return zza((String) asList.get(0), (String) asList.get(1));
        }
        return zza((String) asList.get(0), (String) null);
    }
}
