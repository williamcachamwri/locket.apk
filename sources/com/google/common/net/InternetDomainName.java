package com.google.common.net;

import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.Immutable;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.thirdparty.publicsuffix.PublicSuffixPatterns;
import com.google.thirdparty.publicsuffix.PublicSuffixType;
import java.util.List;
import javax.annotation.CheckForNull;
import org.apache.commons.io.FilenameUtils;

@Immutable
@ElementTypesAreNonnullByDefault
public final class InternetDomainName {
    private static final CharMatcher DASH_MATCHER;
    private static final CharMatcher DIGIT_MATCHER;
    private static final CharMatcher DOTS_MATCHER = CharMatcher.anyOf(".。．｡");
    private static final Joiner DOT_JOINER = Joiner.on((char) FilenameUtils.EXTENSION_SEPARATOR);
    private static final Splitter DOT_SPLITTER = Splitter.on((char) FilenameUtils.EXTENSION_SEPARATOR);
    private static final CharMatcher LETTER_MATCHER;
    private static final int MAX_DOMAIN_PART_LENGTH = 63;
    private static final int MAX_LENGTH = 253;
    private static final int MAX_PARTS = 127;
    private static final int NO_SUFFIX_FOUND = -1;
    private static final CharMatcher PART_CHAR_MATCHER;
    private static final int SUFFIX_NOT_INITIALIZED = -2;
    private final String name;
    private final ImmutableList<String> parts;
    @LazyInit
    private int publicSuffixIndexCache = -2;
    @LazyInit
    private int registrySuffixIndexCache = -2;

    static {
        CharMatcher anyOf = CharMatcher.anyOf("-_");
        DASH_MATCHER = anyOf;
        CharMatcher inRange = CharMatcher.inRange('0', '9');
        DIGIT_MATCHER = inRange;
        CharMatcher or = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z'));
        LETTER_MATCHER = or;
        PART_CHAR_MATCHER = inRange.or(or).or(anyOf);
    }

    InternetDomainName(String str) {
        String lowerCase = Ascii.toLowerCase(DOTS_MATCHER.replaceFrom((CharSequence) str, (char) FilenameUtils.EXTENSION_SEPARATOR));
        boolean z = true;
        lowerCase = lowerCase.endsWith(".") ? lowerCase.substring(0, lowerCase.length() - 1) : lowerCase;
        Preconditions.checkArgument(lowerCase.length() <= MAX_LENGTH, "Domain name too long: '%s':", (Object) lowerCase);
        this.name = lowerCase;
        ImmutableList<String> copyOf = ImmutableList.copyOf(DOT_SPLITTER.split(lowerCase));
        this.parts = copyOf;
        Preconditions.checkArgument(copyOf.size() > 127 ? false : z, "Domain has too many parts: '%s'", (Object) lowerCase);
        Preconditions.checkArgument(validateSyntax(copyOf), "Not a valid domain name: '%s'", (Object) lowerCase);
    }

    private InternetDomainName(String str, ImmutableList<String> immutableList) {
        Preconditions.checkArgument(!immutableList.isEmpty(), "Cannot create an InternetDomainName with zero parts.");
        this.name = str;
        this.parts = immutableList;
    }

    private int publicSuffixIndex() {
        int i = this.publicSuffixIndexCache;
        if (i != -2) {
            return i;
        }
        int findSuffixOfType = findSuffixOfType(Optional.absent());
        this.publicSuffixIndexCache = findSuffixOfType;
        return findSuffixOfType;
    }

    private int registrySuffixIndex() {
        int i = this.registrySuffixIndexCache;
        if (i != -2) {
            return i;
        }
        int findSuffixOfType = findSuffixOfType(Optional.of(PublicSuffixType.REGISTRY));
        this.registrySuffixIndexCache = findSuffixOfType;
        return findSuffixOfType;
    }

    private int findSuffixOfType(Optional<PublicSuffixType> optional) {
        int size = this.parts.size();
        for (int i = 0; i < size; i++) {
            String join = DOT_JOINER.join((Iterable<? extends Object>) this.parts.subList(i, size));
            if (i > 0 && matchesType(optional, Optional.fromNullable(PublicSuffixPatterns.UNDER.get(join)))) {
                return i - 1;
            }
            if (matchesType(optional, Optional.fromNullable(PublicSuffixPatterns.EXACT.get(join)))) {
                return i;
            }
            if (PublicSuffixPatterns.EXCLUDED.containsKey(join)) {
                return i + 1;
            }
        }
        return -1;
    }

    public static InternetDomainName from(String str) {
        return new InternetDomainName((String) Preconditions.checkNotNull(str));
    }

    private static boolean validateSyntax(List<String> list) {
        int size = list.size() - 1;
        if (!validatePart(list.get(size), true)) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!validatePart(list.get(i), false)) {
                return false;
            }
        }
        return true;
    }

    private static boolean validatePart(String str, boolean z) {
        if (str.length() >= 1 && str.length() <= 63) {
            if (!PART_CHAR_MATCHER.matchesAllOf(CharMatcher.ascii().retainFrom(str))) {
                return false;
            }
            CharMatcher charMatcher = DASH_MATCHER;
            if (!charMatcher.matches(str.charAt(0)) && !charMatcher.matches(str.charAt(str.length() - 1))) {
                return !z || !DIGIT_MATCHER.matches(str.charAt(0));
            }
        }
        return false;
    }

    public ImmutableList<String> parts() {
        return this.parts;
    }

    public boolean isPublicSuffix() {
        return publicSuffixIndex() == 0;
    }

    public boolean hasPublicSuffix() {
        return publicSuffixIndex() != -1;
    }

    @CheckForNull
    public InternetDomainName publicSuffix() {
        if (hasPublicSuffix()) {
            return ancestor(publicSuffixIndex());
        }
        return null;
    }

    public boolean isUnderPublicSuffix() {
        return publicSuffixIndex() > 0;
    }

    public boolean isTopPrivateDomain() {
        return publicSuffixIndex() == 1;
    }

    public InternetDomainName topPrivateDomain() {
        if (isTopPrivateDomain()) {
            return this;
        }
        Preconditions.checkState(isUnderPublicSuffix(), "Not under a public suffix: %s", (Object) this.name);
        return ancestor(publicSuffixIndex() - 1);
    }

    public boolean isRegistrySuffix() {
        return registrySuffixIndex() == 0;
    }

    public boolean hasRegistrySuffix() {
        return registrySuffixIndex() != -1;
    }

    @CheckForNull
    public InternetDomainName registrySuffix() {
        if (hasRegistrySuffix()) {
            return ancestor(registrySuffixIndex());
        }
        return null;
    }

    public boolean isUnderRegistrySuffix() {
        return registrySuffixIndex() > 0;
    }

    public boolean isTopDomainUnderRegistrySuffix() {
        return registrySuffixIndex() == 1;
    }

    public InternetDomainName topDomainUnderRegistrySuffix() {
        if (isTopDomainUnderRegistrySuffix()) {
            return this;
        }
        Preconditions.checkState(isUnderRegistrySuffix(), "Not under a registry suffix: %s", (Object) this.name);
        return ancestor(registrySuffixIndex() - 1);
    }

    public boolean hasParent() {
        return this.parts.size() > 1;
    }

    public InternetDomainName parent() {
        Preconditions.checkState(hasParent(), "Domain '%s' has no parent", (Object) this.name);
        return ancestor(1);
    }

    private InternetDomainName ancestor(int i) {
        ImmutableList<String> immutableList = this.parts;
        ImmutableList<String> subList = immutableList.subList(i, immutableList.size());
        int i2 = i;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += ((String) this.parts.get(i3)).length();
        }
        return new InternetDomainName(this.name.substring(i2), subList);
    }

    public InternetDomainName child(String str) {
        return from(((String) Preconditions.checkNotNull(str)) + "." + this.name);
    }

    public static boolean isValid(String str) {
        try {
            from(str);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    private static boolean matchesType(Optional<PublicSuffixType> optional, Optional<PublicSuffixType> optional2) {
        return optional.isPresent() ? optional.equals(optional2) : optional2.isPresent();
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InternetDomainName) {
            return this.name.equals(((InternetDomainName) obj).name);
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }
}
