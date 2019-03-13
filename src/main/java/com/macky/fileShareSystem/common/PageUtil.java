package com.macky.fileShareSystem.common;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分页常用工具
 *
 * @author Fish
 */
public final class PageUtil {

    // 一页显示多少条
    private static final int ONE_PAGE_SIZE = 80;

    // 显示页码数
    private static final int SHOW_PAGES = 5;

    /**
     * pageHelper 每进行一次分页就得调用一次这个方法
     *
     * @param pn 到第几页
     */
    public static void toPage(Integer pn) {
        toPage(pn, ONE_PAGE_SIZE);
    }

    /**
     * 返回 pageInfo 对象，包含分页的信息
     *
     * @param objs 要被分页的集合
     */
    public static <T> PageInfo<T> pageInfo(List<T> objs) {
        //第一个参数为分页对象，第二个为每次显示的页数
        return new PageInfo<T>(objs, SHOW_PAGES);
    }

    /**
     * pageHelper 每进行一次分页就得调用一次这个方法
     *
     * @param pn 到第几页
     * @param size 一次显示多少条数据
     */
    public static void toPage(Integer pn, Integer size) {
        PageHelper.startPage(pn, size);
    }
}
