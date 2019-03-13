package com.macky.fileShareSystem.dao;

import com.macky.fileShareSystem.entity.DesignEnclosure;
import com.macky.fileShareSystem.entity.DesignTemplate;
import com.macky.fileShareSystem.vo.TemplateRecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/5 15:37
 */
public interface DesignTemplateDao {

    List<TemplateRecordVO> listDesignTemplate(@Param("state") String state);

    Integer saveDesignTemplate(DesignTemplate designTemplate);

    Integer saveDesignEnclosure(DesignEnclosure designEnclosure);

    Integer updateTemplateState(@Param("tid") String tid, @Param("state") String state);

}
