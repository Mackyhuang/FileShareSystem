package com.macky.fileShareSystem.service.impl;

import com.macky.fileShareSystem.common.CommUtil;
import com.macky.fileShareSystem.common.FileConstants;
import com.macky.fileShareSystem.common.PageUtil;
import com.macky.fileShareSystem.common.fileUtil.PrepareForUploaderUtil;
import com.macky.fileShareSystem.common.fileUtil.UploaderUtil;
import com.macky.fileShareSystem.dao.SharedFileDao;
import com.macky.fileShareSystem.dto.ServerResponse;
import com.macky.fileShareSystem.entity.SharedFile;
import com.macky.fileShareSystem.entity.SharedUser;
import com.macky.fileShareSystem.enums.state.SharedState;
import com.macky.fileShareSystem.service.SharedFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/10 20:28
 */
@Service
public class SharedFileServiceImpl implements SharedFileService {

    @Resource
    private SharedFileDao sharedFileDao;

    /**
     * 查找当前用户的所有文件
     *
     * @param userId
     * @return
     */
    @Override
    public ServerResponse listFileByUser(String userId) {
        if (userId == null || "".equals(userId)) {
            return ServerResponse.response(SharedState.LACK_STATE);
        }
//        PageUtil.toPage(page);
        List<SharedFile> sharedFiles = sharedFileDao.listFileByUser(userId);
        if (sharedFiles == null || sharedFiles.size() <= 0) {
            return ServerResponse.response(SharedState.NO_RECORD);
        }
        return ServerResponse.response(SharedState.SUCCESS, PageUtil.pageInfo(sharedFiles));
    }

    /**
     * 查找当前系统所有共享的文件（不包括当前用户的共享文件）
     *
     * @param
     * @return
     */
    @Override
    public ServerResponse listFileByShared() {
//        PageUtil.toPage(page);
        List<SharedFile> sharedFiles = sharedFileDao.listFileByShared();
        if (sharedFiles == null || sharedFiles.size() <= 0) {
            return ServerResponse.response(SharedState.NO_RECORD);
        }
        return ServerResponse.response(SharedState.SUCCESS, PageUtil.pageInfo(sharedFiles));
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @Override
    public ServerResponse saveFile(CommonsMultipartFile file, HttpSession session) {
        String resultPath = null;
        SharedFile sharedFile = new SharedFile();
        String fileId = CommUtil.getUUID();
        //上传文件
        try {
            resultPath = UploaderUtil.upload(fileId, file, "FileSys", session);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.response(SharedState.FILE_UPLOAD_FAILED);
        }

        sharedFile.setfId(fileId);
        sharedFile.setFileName(file.getOriginalFilename());
        sharedFile.setFileSize((int) file.getSize());
        sharedFile.setFilePath(resultPath);
        sharedFile.setExtName(PrepareForUploaderUtil.getFileExtension(file));
        sharedFile.setfState(FileConstants.PRIVATE);
        SharedUser user = (SharedUser) session.getAttribute("user");
        String userId = user.getuId();
        String username = user.getuName();
        sharedFile.setfUser(userId);
        sharedFile.setfUsername(username);
        sharedFile.setCreateTime(new Date());
        Integer result = sharedFileDao.saveFile(sharedFile);
        if (result <= 0){
            return ServerResponse.response(SharedState.FILE_DBWRITE_FAILED);
        }
        return ServerResponse.response(SharedState.SUCCESS);
    }

    /**
     * 更新文件的状态
     *
     * @param id
     * @param state 0:无效 1:私有 2:共享
     * @return
     */
    @Override
    public ServerResponse updateFileState(String id, String state) {
        if (id == null || "".equals(id) || state == null || "".equals(state)){
            return ServerResponse.response(SharedState.LACK_STATE);
        }
        Integer result = sharedFileDao.updateFileState(id, state);
        if (result <= 0){
            return ServerResponse.response(SharedState.UPDATE_FAULED);
        }
        return ServerResponse.response(SharedState.SUCCESS);
    }

    /**
     * 用户文件数量
     *
     * @param userId
     * @return
     */
    @Override
    public ServerResponse countUserFile(String userId) {
        Integer integer = sharedFileDao.countUserFile(userId);
        return ServerResponse.response(SharedState.SUCCESS, integer);
    }

    /**
     * 用户分享的文件数量
     *
     * @param userId
     * @return
     */
    @Override
    public ServerResponse countUserSharedFile(String userId) {
        Integer integer = sharedFileDao.countUserSharedFile(userId);
        return ServerResponse.response(SharedState.SUCCESS, integer);
    }

    /**
     * 系统总的文件数量
     *
     * @return
     */
    @Override
    public ServerResponse countSharedFile() {
        Integer integer = sharedFileDao.countSharedFile();
        return ServerResponse.response(SharedState.SUCCESS, integer);
    }
}
