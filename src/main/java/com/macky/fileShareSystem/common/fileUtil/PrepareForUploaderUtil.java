package com.macky.fileShareSystem.common.fileUtil;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/5 20:09
 *
 * 文件处理小工具类，服务于UploaderUtil类
 */
public class PrepareForUploaderUtil {

    //随机数生成器
    private static Random random = new Random();
    //时间格式化器
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    //获取当前系统的文件分隔符
    private static String seperator = System.getProperty("file.separator");

    /**
     * 工具方法  给文件生成一个随机的名字
     * 当前时间加上五位随机数
     * @return
     */
    public static String getRandomFileName() {
        // 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
        String nowTimeStr = simpleDateFormat.format(new Date()); // 当前时间
        return nowTimeStr + rannum;
    }

    /**
     * 取得文件的拓展名
     * @param cFile
     * @return
     */
    public static String getFileExtension(CommonsMultipartFile cFile) {
        String originalFileName = cFile.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * 工具方法  创建一个不存在的目录
     * 可以传入一个参数决定目录
     * @param targetAddr
     * @param session
     */
    public static void makeDirPath(String targetAddr, HttpSession session) {
        String realFileParentPath = PrepareForUploaderUtil.getTypeBasePath(session) + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     * 根据传入文件类型获取图片的绝对路径（前面半部分）
     * @return
     */
    public static String getTypeBasePath(HttpSession session) {
//        String os = System.getProperty("os.name");
//        String basePath = "";
//        if (os.toLowerCase().startsWith("win")) {
//            basePath = "D:/project/FileSystem/" + fileType + "/";
//        } else {
//            basePath = "/home/" + fileType + "/";
//        }
//        basePath = basePath.replace("/", seperator);
        return SessionUtil.getBaseDirFromSession(session);
    }

    /**
     * 获取图片的相对路径（后面半部分）
     * @param uniqueId
     * @param module
     * @return
     */
    public static String getModulePath(String uniqueId, String module) {
        StringBuilder shopImagePathBuilder = new StringBuilder();
        shopImagePathBuilder.append("/upload/" + module + "/");
        shopImagePathBuilder.append(uniqueId);
        shopImagePathBuilder.append("/");
        String shopImagePath = shopImagePathBuilder.toString().replace("/",
                seperator);
        return shopImagePath;
    }
}
