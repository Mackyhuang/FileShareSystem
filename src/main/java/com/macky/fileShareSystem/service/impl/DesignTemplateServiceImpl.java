package com.macky.fileShareSystem.service.impl;

import com.macky.fileShareSystem.common.*;
import com.macky.fileShareSystem.common.fileUtil.PrepareForUploaderUtil;
import com.macky.fileShareSystem.common.fileUtil.UploaderUtil;
import com.macky.fileShareSystem.dao.DesignTemplateDao;
import com.macky.fileShareSystem.dto.ServerResponse;
import com.macky.fileShareSystem.entity.DesignEnclosure;
import com.macky.fileShareSystem.entity.DesignTemplate;
import com.macky.fileShareSystem.enums.state.TemplateState;
import com.macky.fileShareSystem.service.DesignTemplateService;
import com.macky.fileShareSystem.vo.TemplateRecordVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/6 21:17
 */
@Service
public class DesignTemplateServiceImpl implements DesignTemplateService {

    @Resource
    private DesignTemplateDao designTemplateDao;


    /**
     * 根据状态获取模板列表 如果状态为空，就是查找全部
     * @param state
     * @return
     */
    @Override
    public ServerResponse listDesignTemplate(String state) {
        List<TemplateRecordVO> templateList;
        if (state == null || "".equals(state)){
            templateList = designTemplateDao.listDesignTemplate(null);
        }else {
            templateList = designTemplateDao.listDesignTemplate(state);
        }
        if (templateList == null || templateList.size() <= 0){
            return ServerResponse.response(TemplateState.LIST_FAILED);
        } else {
            return ServerResponse.response(TemplateState.SUCCESS, templateList);
        }
    }

    /**
     * 在硬盘和数据库里面添加模板EXCEL文件
     * @param file
     * @param remark
     * @return
     */
    @Override
    public ServerResponse savaCompleteTemplate(CommonsMultipartFile file, String remark, HttpSession session) {
        DesignTemplate designTemplate = new DesignTemplate();
        DesignEnclosure designEnclosure = new DesignEnclosure();
        //开始处理模板部分
        designTemplate.setTid(CommUtil.getUUID());
        //单独取出来，待会需要加入附件表
        String fileId = CommUtil.getUUID();
        designTemplate.setTfileId(fileId);
        designTemplate.setTtime(new Date());
        //TODO 从Session里面获取当前登陆的用户的名字
        designTemplate.setTuser("macky");
        designTemplate.setTremark(remark);
        designTemplate.setTstate(TemplateConstants.VALID);
        //将模板信息插入数据库
        Integer templateResult = designTemplateDao.saveDesignTemplate(designTemplate);
        if (templateResult <= 0){
            return ServerResponse.response(TemplateState.FILE_INFO_ERROR);
        }
        //开始保存文件
        String uploadPath = UploaderUtil.upload(CommUtil.getUUID(), file,
                "Template", session);
        //开始处理附件部分
        designEnclosure.setEnclosureId(CommUtil.getUUID());
        designEnclosure.setBusinessId(TemplateConstants.BUSINESS_ID);
        //将上面模板表的fileId存进附件表做关联
        designEnclosure.setBusiConId(fileId);
        designEnclosure.setEnclosureName(file.getOriginalFilename());
        designEnclosure.setEnclosurePath(uploadPath);
        designEnclosure.setEnclosureOrder("1");
        designEnclosure.setFileSize(Integer.parseInt(Long.toString(file.getSize())));
        designEnclosure.setExtName(PrepareForUploaderUtil.getFileExtension(file));
        Integer EnclosureResult = designTemplateDao.saveDesignEnclosure(designEnclosure);
        if (EnclosureResult <= 0){
            return ServerResponse.response(TemplateState.FILE_INFO_ERROR);
        }
        String jumpPath = PrepareForUploaderUtil.getTypeBasePath(session) + uploadPath;
        ServerResponse serverResponse = readExcelData(jumpPath);
        List resultList = (List)serverResponse.getData();
        //TODO 把图片下载和加入数据库
        for (int i = 0; i < resultList.size(); i++){
            System.out.println(resultList.get(i));
        }
        return ServerResponse.response(TemplateState.SUCCESS);
    }

    /**
     * 修改模板EXCEL文件的状态
     * @param tid
     * @param state 0无效，1有效，2处理中，3已完成
     * @return
     */
    @Override
    public ServerResponse updateTemplateState(String tid, String state) {
        if (tid == null){
            ServerResponse.response(TemplateState.LACK_KEY);
        } else if (state == null){
            ServerResponse.response(TemplateState.LACK_STATE);
        }
        Integer result = designTemplateDao.updateTemplateState(tid, state);
        if (result <= 0){
            return ServerResponse.response(TemplateState.UPDATE_FAULED);
        }else {
            return ServerResponse.response(TemplateState.SUCCESS);
        }
    }

    /**
     * 根据路径读取Excel的信息
     *
     * @param filename
     * @return
     */
    @Override
    public ServerResponse readExcelData(String filename) {
        if (filename == null || "".equals(filename)){
            return ServerResponse.response(TemplateState.LACK_FILENAME);
        }
        List resultList = null;
        try {
            resultList = ExcelAnalysisUtil.ReadExcel(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ServerResponse.response(TemplateState.SUCCESS, resultList);
    }
}
