package xyz.sheldonleung.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import xyz.sheldonleung.service.DownloadWallpaperService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * 壁纸下载
 * <p>
 * Create at 2020/10/31 00:14
 *
 * @author Sheldon Leung
 * @version 0.1
 */
public class DownloadWallpaperServiceImpl implements DownloadWallpaperService {

    @Override
    public String downloadBingWallpaper(String savePath) {
        URL url;
        String imgFile;
        InputStream inputStream = null;
        FileOutputStream wallpaperOutputStream = null;
        String api = "https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN";
        try {
            System.out.println("Start to download wallpaper...");
            url = new URL(api);
            URLConnection conn = url.openConnection();
            inputStream = conn.getInputStream();
            String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            String imgUrl = JSON.parseObject(json).getJSONArray("images").getJSONObject(0).getString("url");
            imgUrl = "https://cn.bing.com" + imgUrl.split("&")[0];

            System.out.println("Downloading from \"" + imgUrl + "\"");
            conn = new URL(imgUrl).openConnection();
            inputStream = conn.getInputStream();
            imgFile = savePath + imgUrl.split("\\?")[1].split("\\.")[1].split("_")[0] + ".jpg";
            wallpaperOutputStream = new FileOutputStream(new File(imgFile));
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                wallpaperOutputStream.write(bytes, 0, len);
            }
            System.out.println("Downloaded image file : " + imgFile);
            System.out.println("Done");
            return imgFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (wallpaperOutputStream != null) {
                    wallpaperOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
