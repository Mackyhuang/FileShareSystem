package com.macky.fileShareSystem.service;

import com.macky.fileShareSystem.dto.ServerResponse;
import com.macky.fileShareSystem.entity.SharedFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/10 20:27
 */
public interface SharedFileService {

    /**
     * 查找当前用户的所有文件
     * @param userId
     * @return
     */
    ServerResponse listFileByUser(String userId);

    /**
     * 查找当前系统所有共享的文件（不包括当前用户的共享文件）
     * @param
     * @return
     */
    ServerResponse listFileByShared();

    /**
     * 上传文件
     * @param file
     * @return
     */
    ServerResponse saveFile(CommonsMultipartFile file, HttpSession session);

    /**
     * 更新文件的状态
     * @param id
     * @param state 0:无效 1:私有 2:共享
     * @return
     */
    ServerResponse updateFileState(String id, String state);

    /**
     * 用户文件数量
     * @param userId
     * @return
     */
    ServerResponse countUserFile(String userId);

    /**
     * 用户分享的文件数量
     * @param userId
     * @return
     */
    ServerResponse countUserSharedFile(String userId);

    /**
     * 系统总的文件数量
     * @return
     */
    ServerResponse countSharedFile();
}
