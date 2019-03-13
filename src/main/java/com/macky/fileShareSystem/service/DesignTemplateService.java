package com.macky.fileShareSystem.service;

import com.macky.fileShareSystem.dto.ServerResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/6 19:49
 */
public interface DesignTemplateService {

    /**
     * 根据状态获取模板列表 如果状态为空，就是查找全部
     * @param state
     * @return
     */
    ServerResponse listDesignTemplate(String state);

    /**
     * 在硬盘和数据库里面添加模板EXCEL文件
     * @param file
     * @param remark
     * @return
     */
    ServerResponse savaCompleteTemplate(CommonsMultipartFile file, String remark, HttpSession session);

    /**
     * 修改模板EXCEL文件的状态
     * @param tid
     * @param state 0无效，1有效，2处理中，3已完成
     * @return
     */
    ServerResponse updateTemplateState(String tid, String state);

    /**
     * 根据路径读取Excel的信息
     * @param filename
     * @return
     */
    ServerResponse readExcelData(String filename);
}
