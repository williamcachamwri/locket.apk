package androidx.media3.exoplayer.offline;

public interface DownloaderFactory {
    Downloader createDownloader(DownloadRequest downloadRequest);
}
