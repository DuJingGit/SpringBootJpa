package com.itmayiedu.service;

import com.itmayiedu.entity.second.EntityPage;
import com.itmayiedu.entity.second.User;
import com.itmayiedu.entity.second.UserBO;

public interface UserService {

    /**
     *获取所以有效user
     * @return
     */
    EntityPage<UserBO> getUsersForPage(EntityPage<UserBO> page);

    /**
     * 通过名字获取user对象
     * @param userName
     * @return
     */
    User getUserByName(String userName);

    boolean delUserById(Integer id);

    boolean addUser(User user);

    boolean updUser(User user);

    UserBO getUserBOById(Integer id);

    User getUserById(Integer id);
}
