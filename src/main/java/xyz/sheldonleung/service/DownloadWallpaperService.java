package xyz.sheldonleung.service;

/**
 * <p>
 * Create at 2020/10/31 00:13
 *
 * @author Sheldon Leung
 * @version 0.1
 */
public interface DownloadWallpaperService {

    /**
     * 下载必应壁纸
     *
     * @param savePath 壁纸保存路径
     * @return 壁纸文件路径
     */
    String downloadBingWallpaper(String savePath);
}
