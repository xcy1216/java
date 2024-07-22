package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.UsersEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * 新加的
 * 用户
 * 后端返回视图实体辅助类
 */
@TableName("users")
public class UsersView extends UsersEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public UsersView() {
    }

    public UsersView(UsersEntity usersEntity) {
        try {
            BeanUtils.copyProperties(this, usersEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
