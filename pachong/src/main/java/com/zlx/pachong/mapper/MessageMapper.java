package com.zlx.pachong.mapper;

import com.zlx.pachong.entity.Subject;
import com.zlx.pachong.entity.Title;

public interface MessageMapper {
    /**
     * //插入主题
     * @param subject
     */
    void insertSubject(Subject subject);

    /**
     * 插入主题头部
     * @param title
     */
    void insertTitle(Title title);
}
