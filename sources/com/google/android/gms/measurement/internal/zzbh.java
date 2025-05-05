package com.google.android.gms.measurement.internal;

import android.content.Context;
import androidx.media3.common.C;
import androidx.media3.extractor.AacUtil;
import com.amplitude.api.Constants;
import com.google.android.gms.internal.measurement.zzgu;
import com.google.android.gms.internal.measurement.zzhk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzbh {
    public static final zzfz<Long> zza = zzb("measurement.ad_id_cache_time", 10000L, new zzbj());
    public static final zzfz<Long> zzaa = zzb("measurement.upload.refresh_blacklisted_config_interval", 604800000L, new zzdi());
    public static final zzfz<Long> zzab = zzb("measurement.upload.initial_upload_delay_time", Long.valueOf(C.DEFAULT_SEEK_FORWARD_INCREMENT_MS), new zzdh());
    public static final zzfz<Long> zzac = zzb("measurement.upload.retry_time", Long.valueOf(Constants.SESSION_TIMEOUT_MILLIS), new zzdk());
    public static final zzfz<Integer> zzad = zzb("measurement.upload.retry_count", 6, new zzdm());
    public static final zzfz<Long> zzae = zzb("measurement.upload.max_queue_time", 518400000L, new zzdo());
    public static final zzfz<Long> zzaf = zzb("measurement.upload.google_signal_max_queue_time", 300000L, new zzdn());
    public static final zzfz<Integer> zzag = zzb("measurement.lifetimevalue.max_currency_tracked", 4, new zzdq());
    public static final zzfz<Integer> zzah = zzb("measurement.audience.filter_result_max_count", 200, new zzdp());
    public static final zzfz<Integer> zzai = zza("measurement.upload.max_public_user_properties", 100);
    public static final zzfz<Integer> zzaj = zza("measurement.upload.max_event_name_cardinality", 2000);
    public static final zzfz<Integer> zzak = zza("measurement.upload.max_public_event_params", 100);
    public static final zzfz<Long> zzal = zzb("measurement.service_client.idle_disconnect_millis", 5000L, new zzds());
    public static final zzfz<Boolean> zzam = zzb("measurement.test.boolean_flag", false, new zzdr());
    public static final zzfz<String> zzan = zzb("measurement.test.string_flag", "---", new zzdu());
    public static final zzfz<Long> zzao = zzb("measurement.test.long_flag", -1L, new zzdt());
    public static final zzfz<Integer> zzap = zzb("measurement.test.int_flag", -2, new zzdy());
    public static final zzfz<Double> zzaq = zzb("measurement.test.double_flag", Double.valueOf(-3.0d), new zzea());
    public static final zzfz<Integer> zzar = zzb("measurement.experiment.max_ids", 50, new zzdz());
    public static final zzfz<Integer> zzas = zzb("measurement.upload.max_item_scoped_custom_parameters", 27, new zzec());
    public static final zzfz<Integer> zzat = zza("measurement.upload.max_event_parameter_value_length", 500, new zzeb());
    public static final zzfz<Integer> zzau = zzb("measurement.max_bundles_per_iteration", 100, new zzee());
    public static final zzfz<Long> zzav = zzb("measurement.sdk.attribution.cache.ttl", 604800000L, new zzed());
    public static final zzfz<Long> zzaw = zzb("measurement.redaction.app_instance_id.ttl", 7200000L, new zzeg());
    public static final zzfz<Integer> zzax = zzb("measurement.rb.attribution.client.min_ad_services_version", 7, new zzei());
    public static final zzfz<Integer> zzay = zzb("measurement.dma_consent.max_daily_dcu_realtime_events", 1, new zzeh());
    public static final zzfz<String> zzaz = zzb("measurement.rb.attribution.uri_scheme", "https", new zzek());
    public static final zzfz<Long> zzb = zzb("measurement.app_uninstalled_additional_ad_id_cache_time", 3600000L, new zzcz());
    public static final zzfz<String> zzba = zzb("measurement.rb.attribution.uri_authority", "google-analytics.com", new zzem());
    public static final zzfz<String> zzbb = zzb("measurement.rb.attribution.uri_path", "privacy-sandbox/register-app-conversion", new zzel());
    public static final zzfz<Long> zzbc = zzb("measurement.session.engagement_interval", 3600000L, new zzeo());
    public static final zzfz<String> zzbd = zzb("measurement.rb.attribution.app_allowlist", "com.labpixies.flood,com.sofascore.results,games.spearmint.triplecrush,com.block.juggle,io.supercent.linkedcubic,com.cdtg.gunsound,com.corestudios.storemanagementidle,com.cdgames.fidget3d,io.supercent.burgeridle,io.supercent.pizzaidle,jp.ne.ibis.ibispaintx.app,com.dencreak.dlcalculator,com.ebay.kleinanzeigen,de.wetteronline.wetterapp,com.game.shape.shift,com.champion.cubes,bubbleshooter.orig,com.wolt.android,com.master.hotelmaster,com.games.bus.arrival,com.playstrom.dop2,com.huuuge.casino.slots,com.ig.spider.fighting,com.jura.coloring.page,com.rikkogame.ragdoll2,com.ludo.king,com.sigma.prank.sound.haircut,com.crazy.block.robo.monster.cliffs.craft,com.fugo.wow,com.maps.locator.gps.gpstracker.phone,com.gamovation.tileclub,com.pronetis.ironball2,com.meesho.supply,pdf.pdfreader.viewer.editor.free,com.dino.race.master,com.ig.moto.racing,ai.photo.enhancer.photoclear,com.duolingo,com.candle.magic_piano,com.free.vpn.super.hotspot.open,sg.bigo.live,com.cdg.tictactoe,com.zhiliaoapp.musically.go,com.wildspike.wormszone,com.mast.status.video.edit,com.vyroai.photoeditorone,com.pujiagames.deeeersimulator,com.superbinogo.jungleboyadventure,com.trustedapp.pdfreaderpdfviewer,com.artimind.aiart.artgenerator.artavatar,de.cellular.ottohybrid,com.zeptolab.cats.google,in.crossy.daily_crossword", new zzen());
    public static final zzfz<String> zzbe = zzb("measurement.rb.attribution.user_properties", "_npa,npa|_fot,fot", new zzeq());
    public static final zzfz<String> zzbf = zzb("measurement.rb.attribution.event_params", "value|currency", new zzep());
    public static final zzfz<String> zzbg = zzb("measurement.rb.attribution.query_parameters_to_remove", "", new zzer());
    public static final zzfz<Long> zzbh = zzb("measurement.rb.attribution.max_queue_time", 1209600000L, new zzeu());
    public static final zzfz<Integer> zzbi = zzb("measurement.rb.max_trigger_registrations_per_day", 0, new zzew());
    public static final zzfz<Boolean> zzbj = zzb("measurement.config.bundle_for_all_apps_on_backgrounded", true, new zzey());
    public static final zzfz<Boolean> zzbk = zzb("measurement.config.notify_trigger_uris_on_backgrounded", true, new zzex());
    public static final zzfz<Boolean> zzbl = zzb("measurement.collection.log_event_and_bundle_v2", true, new zzfa());
    public static final zzfz<Boolean> zzbm = zza("measurement.quality.checksum", false);
    public static final zzfz<Boolean> zzbn = zzb("measurement.audience.use_bundle_end_timestamp_for_non_sequence_property_filters", false, new zzez());
    public static final zzfz<Boolean> zzbo = zzb("measurement.audience.refresh_event_count_filters_timestamp", false, new zzfc());
    public static final zzfz<Boolean> zzbp = zza("measurement.audience.use_bundle_timestamp_for_event_count_filters", false, new zzfe());
    public static final zzfz<Boolean> zzbq = zzb("measurement.sdk.collection.last_deep_link_referrer_campaign2", false, new zzfd());
    public static final zzfz<Boolean> zzbr = zzb("measurement.integration.disable_firebase_instance_id", false, new zzfg());
    public static final zzfz<Boolean> zzbs = zzb("measurement.collection.service.update_with_analytics_fix", false, new zzff());
    public static final zzfz<Integer> zzbt = zzb("measurement.service.storage_consent_support_version", 203600, new zzfi());
    public static final zzfz<Boolean> zzbu = zzb("measurement.service.store_null_safelist", true, new zzfk());
    public static final zzfz<Boolean> zzbv = zzb("measurement.service.store_safelist", true, new zzfj());
    public static final zzfz<Boolean> zzbw = zzb("measurement.session_stitching_token_enabled", false, new zzfm());
    public static final zzfz<Boolean> zzbx = zza("measurement.sgtm.service", true, new zzfl());
    public static final zzfz<Boolean> zzby = zza("measurement.sgtm.preview_mode_enabled", true, new zzfn());
    public static final zzfz<Boolean> zzbz = zza("measurement.sgtm.rollout_percentage_fix", false, new zzfq());
    public static final zzfz<Long> zzc = zzb("measurement.monitoring.sample_period_millis", 86400000L, new zzdl());
    public static final zzfz<String> zzca = zza("measurement.sgtm.app_allowlist", "de.zalando.mobile.internal,de.zalando.mobile.internal.debug,de.zalando.lounge.dev,grit.storytel.app,com.rbc.mobile.android,com.rbc.mobile.android,com.dylvian.mango.activities,com.home24.android,com.home24.android.staging,se.lf.mobile.android,se.lf.mobile.android.beta,se.lf.mobile.android.rc,se.lf.mobile.android.test,se.lf.mobile.android.test.debug,com.boots.flagship.android,com.boots.flagshiproi.android,de.zalando.mobile,com.trivago,com.getyourguide.android,es.mobail.meliarewards,se.nansen.coop.debug,se.nansen.coop,se.coop.coop.qa,com.booking,com.google.firebaseengage,com.mse.mseapp.dev,com.mse.mseapp,pl.eobuwie.eobuwieapp,br.com.eventim.mobile.app.Android,ch.ticketcorner.mobile.app.Android,de.eventim.mobile.app.Android,dk.billetlugen.mobile.app.Android,nl.eventim.mobile.app.Android,com.asos.app,com.blueshieldca.prod,dk.magnetix.tivoliapp,matas.matas.internal,nl.omoda,com.thetrainline,com.simo.androidtest,de.aboutyou.mobile.app,com.hometogo,de.casamundo.casamundomobile,it.casevacanz,eu.coolblue.shop,com.stihl.app,com.indeed.android.jobsearch,com.homeretailgroup.argos.android,com.dylvian.mango.activities.pre,se.nansen.coop.qa", new zzfp());
    public static final zzfz<Boolean> zzcb = zzb("measurement.sgtm.upload_queue", false, new zzfs());
    public static final zzfz<Boolean> zzcc = zzb("measurement.sgtm.google_signal.enable", false, new zzfr());
    public static final zzfz<Boolean> zzcd = zzb("measurement.gmscore_feature_tracking", true, new zzfu());
    public static final zzfz<Boolean> zzce = zzb("measurement.gmscore_client_telemetry", false, new zzfw());
    public static final zzfz<Boolean> zzcf = zzb("measurement.gmscore_network_migration", false, new zzfv());
    public static final zzfz<Boolean> zzcg = zza("measurement.fix_health_monitor_stack_trace", true, new zzfy());
    public static final zzfz<Boolean> zzch = zza("measurement.rb.attribution.service", true, new zzbl());
    public static final zzfz<Boolean> zzci = zza("measurement.rb.attribution.client2", true, new zzbo());
    public static final zzfz<Boolean> zzcj = zzb("measurement.rb.attribution.uuid_generation", true, new zzbn());
    public static final zzfz<Boolean> zzck = zzb("measurement.rb.attribution.enable_trigger_redaction", true, new zzbq());
    public static final zzfz<Boolean> zzcl = zzb("measurement.rb.attribution.retry_disposition", false, new zzbs());
    public static final zzfz<Boolean> zzcm = zzb("measurement.rb.attribution.ad_campaign_info", false, new zzbr());
    public static final zzfz<Boolean> zzcn = zza("measurement.rb.attribution.improved_retry", true, new zzbt());
    public static final zzfz<Boolean> zzco = zzb("measurement.client.sessions.enable_fix_background_engagement", false, new zzbw());
    public static final zzfz<Boolean> zzcp = zzb("measurement.client.sessions.enable_pause_engagement_in_background", true, new zzby());
    public static final zzfz<Boolean> zzcq = zzb("measurement.dma_consent.service_dcu_event2", true, new zzbx());
    public static final zzfz<Boolean> zzcr = zzb("measurement.dma_consent.services_database_update_fix", true, new zzca());
    public static final zzfz<Boolean> zzcs = zzb("measurement.dma_consent.setting_npa_inline_fix", true, new zzbz());
    public static final zzfz<Boolean> zzct = zza("measurement.gbraid_campaign.gbraid.client", true, new zzcc());
    public static final zzfz<Boolean> zzcu = zza("measurement.gbraid_campaign.gbraid.service", true, new zzcb());
    public static final zzfz<Boolean> zzcv = zzb("measurement.service.consent.pfo_on_fx", true, new zzce());
    public static final zzfz<Boolean> zzcw = zzb("measurement.service.consent.params_on_fx", true, new zzcd());
    public static final zzfz<Boolean> zzcx = zza("measurement.consent.stop_reset_on_storage_denied.client", true, new zzcn());
    public static final zzfz<Boolean> zzcy = zza("measurement.consent.stop_reset_on_storage_denied.service", true, new zzda());
    public static final zzfz<Boolean> zzcz = zzb("measurement.consent.scrub_audience_data_analytics_consent", true, new zzdj());
    public static final zzfz<Long> zzd = zza("measurement.config.cache_time", 86400000L, 3600000L, new zzdx(), false);
    public static final zzfz<Boolean> zzda = zzb("measurement.consent.fix_first_open_count_from_snapshot", true, new zzdw());
    public static final zzfz<Boolean> zzdb = zzb("measurement.fix_engagement_on_reset_analytics_data", true, new zzef());
    public static final zzfz<Boolean> zzdc = zzb("measurement.rb.attribution.service.bundle_on_backgrounded", true, new zzes());
    public static final zzfz<Boolean> zzdd = zzb("measurement.rb.attribution.client.bundle_on_backgrounded", true, new zzfb());
    public static final zzfz<Boolean> zzde = zzb("measurement.set_default_event_parameters_propagate_clear.service.dev", false, new zzfo());
    public static final zzfz<Boolean> zzdf = zzb("measurement.set_default_event_parameters_propagate_clear.client.dev", false, new zzbm());
    public static final zzfz<Boolean> zzdg = zzb("measurement.set_default_event_parameters_with_backfill.service", false, new zzbv());
    public static final zzfz<Boolean> zzdh = zzb("measurement.defensively_copy_bundles_validate_default_params", true, new zzch());
    public static final zzfz<Boolean> zzdi = zzb("measurement.fix_origin_in_upload_utils.service", true, new zzck());
    public static final zzfz<Boolean> zzdj = zzb("measurement.service.ad_impression.convert_value_to_double", true, new zzcm());
    public static final zzfz<Boolean> zzdk = zzb("measurement.persisted_config_defensive_copies", false, new zzcl());
    public static final zzfz<Boolean> zzdl = zzb("measurement.currency.escape_underscore_fix", true, new zzcq());
    /* access modifiers changed from: private */
    public static final List<zzfz<?>> zzdm = Collections.synchronizedList(new ArrayList());
    public static final zzfz<String> zze = zzb("measurement.config.url_scheme", "https", new zzej());
    public static final zzfz<String> zzf = zzb("measurement.config.url_authority", "app-measurement.com", new zzev());
    public static final zzfz<Integer> zzg = zzb("measurement.upload.max_bundles", 100, new zzfh());
    public static final zzfz<Integer> zzh = zzb("measurement.upload.max_batch_size", 65536, new zzft());
    public static final zzfz<Integer> zzi = zzb("measurement.upload.max_bundle_size", 65536, new zzbu());
    public static final zzfz<Integer> zzj = zzb("measurement.upload.max_events_per_bundle", 1000, new zzcg());
    public static final zzfz<Integer> zzk;
    public static final zzfz<Integer> zzl = zzb("measurement.upload.max_error_events_per_day", 1000, new zzcp());
    public static final zzfz<Integer> zzm = zzb("measurement.upload.max_public_events_per_day", 50000, new zzcs());
    public static final zzfz<Integer> zzn = zzb("measurement.upload.max_conversions_per_day", 10000, new zzcr());
    public static final zzfz<Integer> zzo = zzb("measurement.upload.max_realtime_events_per_day", 10, new zzcu());
    public static final zzfz<Integer> zzp;
    public static final zzfz<String> zzq = zzb("measurement.upload.url", "https://app-measurement.com/a", new zzcw());
    public static final zzfz<String> zzr = zzb("measurement.sgtm.google_signal.url", "https://app-measurement.com/s", new zzcv());
    public static final zzfz<Long> zzs = zzb("measurement.upload.backoff_period", 43200000L, new zzcy());
    public static final zzfz<Long> zzt = zzb("measurement.upload.window_interval", 3600000L, new zzcx());
    public static final zzfz<Long> zzu = zzb("measurement.upload.interval", 3600000L, new zzdc());
    public static final zzfz<Long> zzv = zzb("measurement.upload.realtime_upload_interval", 10000L, new zzdb());
    public static final zzfz<Long> zzw = zzb("measurement.upload.debug_upload_interval", 1000L, new zzde());
    public static final zzfz<Long> zzx = zzb("measurement.upload.minimum_delay", 500L, new zzdd());
    public static final zzfz<Long> zzy = zzb("measurement.alarm_manager.minimum_interval", 60000L, new zzdg());
    public static final zzfz<Long> zzz = zzb("measurement.upload.stale_data_deletion_interval", 86400000L, new zzdf());

    private static <V> zzfz<V> zza(String str, V v, zzfx<V> zzfx) {
        return zza(str, v, v, zzfx, true);
    }

    private static <V> zzfz<V> zza(String str, V v, V v2, zzfx<V> zzfx, boolean z) {
        zzfz zzfz = new zzfz(str, v, v2, zzfx);
        if (z) {
            zzdm.add(zzfz);
        }
        return zzfz;
    }

    private static <V> zzfz<V> zza(String str, V v) {
        return zza(str, v, v, (zzfx) null, false);
    }

    private static <V> zzfz<V> zzb(String str, V v, zzfx<V> zzfx) {
        return zza(str, v, v, zzfx, false);
    }

    public static Map<String, String> zza(Context context) {
        zzgu zza2 = zzgu.zza(context.getContentResolver(), zzhk.zza("com.google.android.gms.measurement"), new zzbk());
        return zza2 == null ? Collections.emptyMap() : zza2.zza();
    }

    static {
        Collections.synchronizedSet(new HashSet());
        Integer valueOf = Integer.valueOf(AacUtil.AAC_LC_MAX_RATE_BYTES_PER_SECOND);
        zzk = zzb("measurement.upload.max_events_per_day", valueOf, new zzcf());
        zzp = zzb("measurement.store.max_stored_events_per_app", valueOf, new zzct());
        zza("measurement.test.cached_long_flag", -1L, new zzdv());
        zzb("measurement.rb.attribution.max_trigger_uris_queried_at_once", 0, new zzet());
        zzb("measurement.rb.attribution.followup1.service", false, new zzbp());
        zzb("measurement.set_default_event_parameters_with_backfill.client.dev", false, new zzci());
        zzb("measurement.chimera.parameter.service", true, new zzcj());
        zzb("measurement.rb.attribution.service.enable_max_trigger_uris_queried_at_once", true, new zzco());
    }
}
