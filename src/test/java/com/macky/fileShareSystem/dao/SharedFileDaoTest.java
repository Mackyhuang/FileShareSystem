package com.macky.fileShareSystem.dao;

import com.macky.fileShareSystem.common.CommUtil;
import com.macky.fileShareSystem.entity.SharedFile;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/10 20:12
 */
public class SharedFileDaoTest extends BaseTest{

    @Resource
    private SharedFileDao sharedFileDao;

    @Test
    public void listFileByUser() {
        String id = "714D745CF96A4DC0B7525329D404E271";
        List<SharedFile> sharedFiles = sharedFileDao.listFileByUser(id);
        for (SharedFile file: sharedFiles) {
            System.out.println(file.toString());
        }
    }

//    @Test
//    public void listFileByShared() {
//        String id = "714D745CF96A4DC0B7525329D404E271";
//        List<SharedFile> sharedFiles = sharedFileDao.listFileByShared(id);
//        for (SharedFile file: sharedFiles) {
//            System.out.println(file.toString());
//        }
//    }

    @Test
    public void saveFile() {
        String id = "714D745CF96A4DC0B7525329D404E271";
        SharedFile file = new SharedFile();
        file.setfId(CommUtil.getUUID());
        file.setFileName("编程");
        file.setFileSize(1123);
        file.setFilePath("/upload/file");
        file.setExtName(".txt");
        file.setfState("1");
        file.setfUser(id);
        file.setCreateTime(new Date());
        file.setfUsername("macky");
        sharedFileDao.saveFile(file);
    }

    @Test
    public void updateFileState() {
        String id = "DB4B1F8E26884A6DBE99CBF7D99FE401";
        sharedFileDao.updateFileState(id, "2");
    }
}