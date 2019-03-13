package com.macky.fileShareSystem.dao;

import com.macky.fileShareSystem.entity.SharedFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/10 19:29
 */
public interface SharedFileDao {

    List<SharedFile> listFileByUser(@Param("userId") String userId);

    Integer countUserFile(@Param("userId") String userId);

    Integer countUserSharedFile(@Param("userId") String userId);

    Integer countSharedFile();

    List<SharedFile> listFileByShared();

    Integer saveFile(SharedFile file);

    Integer updateFileState(@Param("id") String id, @Param("state") String state);

}
