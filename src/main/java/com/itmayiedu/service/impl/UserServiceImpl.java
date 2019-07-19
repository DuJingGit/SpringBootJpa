package com.itmayiedu.service.impl;

import com.itmayiedu.dao.second.UserDao;
import com.itmayiedu.entity.second.*;
import com.itmayiedu.realm.AES;
import com.itmayiedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
//    @Transactional
    public EntityPage<UserBO> getUsersForPage(EntityPage<UserBO> page) {
        List<User> sumuserList = userDao.findAll();
        List<UserBO> users = new ArrayList<>();
        page.setPageSum(sumuserList.size());
        Integer pageSize = page.getPageSize();
        Double pageNumberDouble = Math.floor(page.getPageNumber()/pageSize);
        Integer pageNumber = pageNumberDouble.intValue();
        System.out.println(page.getPageNumber()+"--"+page.getPageSize());
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        		Specification<User> specification = new Specification<User>() {
			/**
			 * @param *root: 代表查询的实体类.
			 * @param query: 可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
			 * 来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象.
			 * @param *cb: CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到 Predicate 对象
			 * @return: *Predicate 类型, 代表一个查询条件.
			 */
			@Override
			public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
			    if(page.getSearchName()!=null&&page.getSearchName()!=""){
				    Predicate predicate = cb.like(root.get("name"),"%" + page.getSearchName() + "%");
				    return predicate;
			    }else {
			        return null;
                }
			}
		};
        Page<User> userPage=userDao.findAll(specification,pageable);
//        Page<User> userPage = userDao.findAll(pageable);
        System.out.println(userPage.getSize()+"----");
        List<User> userList = userPage.getContent();
        for (User user : userList) {
            UserBO userBO = new UserBO();
            userBO.setId(user.getId());
            userBO.setName(user.getName());
            userBO.setPassword(user.getPassword());
            userBO.setHeadurl(user.getHeadurl());
            System.out.println(userBO.toString()+"---------------------");
            users.add(userBO);
        }
        page.setTs(users);
        return page;
    }

    @Override
//    @Transactional
    public User getUserByName(String name) {
        return userDao.findByName(name);
    }

    @Transactional(value = "transactionManagerSecond")
    @Override
    public boolean delUserById(Integer id) {
        try {
            userDao.deleteById(id);
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional(value = "transactionManagerSecond")
    @Override
    public boolean addUser(User user) {
        try {
            userDao.save(user);
//            int i = 1/ 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional(value = "transactionManagerSecond")
    @Override
    public boolean updUser(User user) {
        try {
            userDao.save(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public UserBO getUserBOById(Integer id) {
        Optional<User> userOptional = userDao.findById(id);
        User user = userOptional.get();
        UserBO userBO = new UserBO();
        userBO.setId(user.getId());
        userBO.setName(user.getName());
        userBO.setHeadurl(user.getHeadurl());
        String password = user.getPassword();
        String cKey = "1234567890123456";
        try {
            password = AES.Decrypt(password,cKey);
        }catch (Exception e){
            e.printStackTrace();
        }
        userBO.setPassword(password);
        List<Role> roleList = user.getRoleList();
        List<RoleBO> roleBOList = new ArrayList<>();
        for (Role role : roleList) {
            RoleBO roleBO = new RoleBO();
            roleBO.setId(role.getId());
            roleBO.setRole(role.getRolename());
            List<Permission> permissionList = role.getPermissionList();
            List<PermissionBO> permissionBOList = new ArrayList<>();
            for (Permission permission : permissionList) {
                PermissionBO permissionBO = new PermissionBO();
                permissionBO.setId(permission.getId());
                permissionBO.setMethod(permission.getMethod());
                permissionBO.setPermission(permission.getPermissionname());
                permissionBO.setUrl(permission.getUrl());
                permissionBOList.add(permissionBO);
            }
            roleBO.setPermissionList(permissionBOList);
            roleBOList.add(roleBO);
        }
        userBO.setRoleList(roleBOList);
        return userBO;
    }

    @Override
    public User getUserById(Integer id) {
        Optional<User> userOptional = userDao.findById(id);
        User user = userOptional.get();
        return user;
    }
}
