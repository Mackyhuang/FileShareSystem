package com.macky.fileShareSystem.dao;

import com.macky.fileShareSystem.entity.DesignEnclosure;
import com.macky.fileShareSystem.entity.DesignTemplate;
import com.macky.fileShareSystem.vo.TemplateRecordVO;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/6 19:18
 */

public class DesignTemplateDaoTest extends BaseTest{

    @Resource
    private DesignTemplateDao designTemplateDao;

    @Test
    public void listDesignTemplate() {
        List<TemplateRecordVO> templateRecordVOS = designTemplateDao.listDesignTemplate(null);
        for (TemplateRecordVO templateRecordVO : templateRecordVOS) {
            System.out.println(templateRecordVO);
        }
    }

    @Test
    public void saveDesignTemplate() {
        DesignTemplate designTemplate = new DesignTemplate();
        designTemplate.setTfileId("112311");
        designTemplate.setTid("212322");
        designTemplate.setTremark("mackyhuang test");
        designTemplate.setTstate("1");
        designTemplate.setTuser("macky");
        designTemplate.setTtime(new Date());
        Integer integer = designTemplateDao.saveDesignTemplate(designTemplate);
        System.out.println(integer);
    }

    @Test
    public void saveDesignEnclosure() {
        DesignEnclosure designEnclosure = new DesignEnclosure();
        designEnclosure.setEnclosureId("123123123123123");
        designEnclosure.setBusinessId("模板");
        designEnclosure.setBusiConId("112311");
        designEnclosure.setEnclosureName("testJunit");
        designEnclosure.setEnclosurePath("/index");
        designEnclosure.setEnclosureOrder("12");
        designEnclosure.setFileSize(123);
        designEnclosure.setExtName(".xlsx");
        Integer integer = designTemplateDao.saveDesignEnclosure(designEnclosure);
        System.out.println(integer);
    }

    @Test
    public void updateTemplateState() {
        String id = "222";
        String state = "0";
        designTemplateDao.updateTemplateState(id, state);
    }

}