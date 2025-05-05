package io.grpc.internal;

import com.google.common.base.Verify;
import io.grpc.internal.DnsNameResolver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

final class JndiResourceResolverFactory implements DnsNameResolver.ResourceResolverFactory {
    /* access modifiers changed from: private */
    @Nullable
    public static final Throwable JNDI_UNAVAILABILITY_CAUSE = initJndi();

    interface RecordFetcher {
        List<String> getAllRecords(String str, String str2) throws NamingException;
    }

    @Nullable
    private static Throwable initJndi() {
        try {
            Class.forName("javax.naming.directory.InitialDirContext");
            Class.forName("com.sun.jndi.dns.DnsContextFactory");
            return null;
        } catch (ClassNotFoundException e) {
            return e;
        } catch (RuntimeException e2) {
            return e2;
        } catch (Error e3) {
            return e3;
        }
    }

    @Nullable
    public DnsNameResolver.ResourceResolver newResourceResolver() {
        if (unavailabilityCause() != null) {
            return null;
        }
        return new JndiResourceResolver(new JndiRecordFetcher());
    }

    @Nullable
    public Throwable unavailabilityCause() {
        return JNDI_UNAVAILABILITY_CAUSE;
    }

    static final class JndiResourceResolver implements DnsNameResolver.ResourceResolver {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final Logger logger = Logger.getLogger(JndiResourceResolver.class.getName());
        private static final Pattern whitespace = Pattern.compile("\\s+");
        private final RecordFetcher recordFetcher;

        static {
            Class<JndiResourceResolverFactory> cls = JndiResourceResolverFactory.class;
        }

        public JndiResourceResolver(RecordFetcher recordFetcher2) {
            this.recordFetcher = recordFetcher2;
        }

        public List<String> resolveTxt(String str) throws NamingException {
            Logger logger2 = logger;
            if (logger2.isLoggable(Level.FINER)) {
                logger2.log(Level.FINER, "About to query TXT records for {0}", new Object[]{str});
            }
            List<String> allRecords = this.recordFetcher.getAllRecords("TXT", "dns:///" + str);
            if (logger2.isLoggable(Level.FINER)) {
                logger2.log(Level.FINER, "Found {0} TXT records", new Object[]{Integer.valueOf(allRecords.size())});
            }
            ArrayList arrayList = new ArrayList(allRecords.size());
            for (String unquote : allRecords) {
                arrayList.add(unquote(unquote));
            }
            return Collections.unmodifiableList(arrayList);
        }

        public List<DnsNameResolver.SrvRecord> resolveSrv(String str) throws Exception {
            Logger logger2 = logger;
            if (logger2.isLoggable(Level.FINER)) {
                logger2.log(Level.FINER, "About to query SRV records for {0}", new Object[]{str});
            }
            List<String> allRecords = this.recordFetcher.getAllRecords("SRV", "dns:///" + str);
            if (logger2.isLoggable(Level.FINER)) {
                logger2.log(Level.FINER, "Found {0} SRV records", new Object[]{Integer.valueOf(allRecords.size())});
            }
            ArrayList arrayList = new ArrayList(allRecords.size());
            Level level = Level.WARNING;
            RuntimeException runtimeException = null;
            for (String next : allRecords) {
                try {
                    String[] split = whitespace.split(next, 5);
                    Verify.verify(split.length == 4, "Bad SRV Record: %s", (Object) next);
                    if (split[3].endsWith(".")) {
                        arrayList.add(new DnsNameResolver.SrvRecord(split[3], Integer.parseInt(split[2])));
                    } else {
                        throw new RuntimeException("Returned SRV host does not end in period: " + split[3]);
                    }
                } catch (RuntimeException e) {
                    logger.log(level, "Failed to construct SRV record " + next, e);
                    if (runtimeException == null) {
                        level = Level.FINE;
                        runtimeException = e;
                    }
                }
            }
            if (!arrayList.isEmpty() || runtimeException == null) {
                return Collections.unmodifiableList(arrayList);
            }
            throw runtimeException;
        }

        static String unquote(String str) {
            StringBuilder sb = new StringBuilder(str.length());
            int i = 0;
            boolean z = false;
            while (i < str.length()) {
                char charAt = str.charAt(i);
                if (!z) {
                    if (charAt != ' ') {
                        if (charAt == '\"') {
                            z = true;
                        }
                    }
                    i++;
                } else if (charAt == '\"') {
                    z = false;
                    i++;
                } else if (charAt == '\\') {
                    i++;
                    charAt = str.charAt(i);
                }
                sb.append(charAt);
                i++;
            }
            return sb.toString();
        }
    }

    static final class JndiRecordFetcher implements RecordFetcher {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<JndiResourceResolverFactory> cls = JndiResourceResolverFactory.class;
        }

        JndiRecordFetcher() {
        }

        public List<String> getAllRecords(String str, String str2) throws NamingException {
            checkAvailable();
            String[] strArr = {str};
            ArrayList arrayList = new ArrayList();
            Hashtable hashtable = new Hashtable();
            hashtable.put("com.sun.jndi.ldap.connect.timeout", "5000");
            hashtable.put("com.sun.jndi.ldap.read.timeout", "5000");
            InitialDirContext initialDirContext = new InitialDirContext(hashtable);
            try {
                NamingEnumeration all = initialDirContext.getAttributes(str2, strArr).getAll();
                while (all.hasMore()) {
                    try {
                        NamingEnumeration all2 = ((Attribute) all.next()).getAll();
                        while (all2.hasMore()) {
                            try {
                                arrayList.add(String.valueOf(all2.next()));
                            } catch (NamingException e) {
                                closeThenThrow((NamingEnumeration<?>) all2, e);
                            }
                        }
                        all2.close();
                    } catch (NamingException e2) {
                        closeThenThrow((NamingEnumeration<?>) all, e2);
                    }
                }
                all.close();
            } catch (NamingException e3) {
                closeThenThrow((DirContext) initialDirContext, e3);
            }
            initialDirContext.close();
            return arrayList;
        }

        private static void closeThenThrow(NamingEnumeration<?> namingEnumeration, NamingException namingException) throws NamingException {
            try {
                namingEnumeration.close();
            } catch (NamingException unused) {
            }
            throw namingException;
        }

        private static void closeThenThrow(DirContext dirContext, NamingException namingException) throws NamingException {
            try {
                dirContext.close();
            } catch (NamingException unused) {
            }
            throw namingException;
        }

        private static void checkAvailable() {
            if (JndiResourceResolverFactory.JNDI_UNAVAILABILITY_CAUSE != null) {
                throw new UnsupportedOperationException("JNDI is not currently available", JndiResourceResolverFactory.JNDI_UNAVAILABILITY_CAUSE);
            }
        }
    }
}
