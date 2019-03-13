package com.macky.fileShareSystem.common.fileUtil;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/5 20:09
 *
 * 文件上传类 封装俩个对外的方法
 */
public class UploaderUtil {

    // 图片类型 0
    public final static String FILETYPE_IMAGE = "image";
    public final static Integer FILETYPE_IMAGE_CODE = 0;
    // 文件类型 1
    public final static String FILETYPE_FILE = "file";
    public final static Integer FILETYPE_FILE_CODE = 1;

    /**
     * 上传文件或图片
     * @param file
     * @param targetAddr
     * @param session 用于补充基本地址
     * @return
     */
    private static String uploadFile(CommonsMultipartFile file, String targetAddr, HttpSession session){

        //获取文件的随机名
        String realFileName = PrepareForUploaderUtil.getRandomFileName();
        //获取文件的拓展名
        String extension = PrepareForUploaderUtil.getFileExtension(file);
        //如果传进来的路径不存在，那么就创建一个目录
        PrepareForUploaderUtil.makeDirPath(targetAddr, session);
        //将 路径 文件随机名 扩展名 拼接 生成将要使用于存储的后半部分主要路径名
        String relativeAddr = targetAddr + realFileName + extension;
        //将 图片存储在服务器里面的真实根路径，和上面的得到的相对路径进行拼接
        //获得的是最后的图片存储地址，用于thumbnailator的存储
        File dest = new File(PrepareForUploaderUtil.getTypeBasePath(session) + relativeAddr);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    private static String uploadFileCompress(CommonsMultipartFile file, String targetAddr, HttpSession session){
        //获取文件的随机名
        String realFileName = PrepareForUploaderUtil.getRandomFileName();
        //获取文件的拓展名
        String extension = PrepareForUploaderUtil.getFileExtension(file);
        //如果传进来的路径不存在，那么就创建一个目录
        PrepareForUploaderUtil.makeDirPath(targetAddr, session);
        //将 路径 文件随机名 扩展名 拼接 生成将要使用于存储的后半部分主要路径名
        String relativeAddr = targetAddr + realFileName + extension;
        //将 图片存储在服务器里面的真实根路径，和上面的得到的相对路径进行拼接
        //获得的是最后的图片存储地址，用于thumbnailator的存储
        File dest = new File(PrepareForUploaderUtil.getTypeBasePath(session) + relativeAddr);
        try {
            Thumbnails.of(file.getInputStream()).size(200, 200).outputQuality(0.25f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    /**
     * 对外的调用的 上传文件或图片  函数
     * @param uniqueId 唯一的标识符 一般是数据库里面这个文件的主键id 用于生成唯一文件夹
     * @param file 上传上来的文件
     * @param module 你需要的上传以后的文件的文件夹名字 一般是业务模块名
     * @param session  获取基本路径
     * @return
     */
    public static String upload(String uniqueId, CommonsMultipartFile file, String module, HttpSession session) {
        String dest = PrepareForUploaderUtil.getModulePath(uniqueId, module);
        String realPath = UploaderUtil.uploadFile(file, dest, session);
        return realPath;
    }

    /**
     * 对外的调用的 上传压缩图片  函数
     * @param uniqueId 唯一的标识符 一般是数据库里面这个文件的主键id 用于生成唯一文件夹
     * @param file 上传上来的文件
     * @param module 你需要的上传以后的文件的文件夹名字 一般是业务模块名
     * @return
     */
    public static String uploadImageCompress(String uniqueId, CommonsMultipartFile file, String module, HttpSession session) {
        //获取图片存储的相对路径
        String dest = PrepareForUploaderUtil.getModulePath(uniqueId, module);
        String realPath = UploaderUtil.uploadFileCompress(file, dest, session);
        return realPath;
    }
}
