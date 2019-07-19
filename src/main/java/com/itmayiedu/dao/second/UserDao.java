
package com.itmayiedu.dao.second;

import com.itmayiedu.entity.second.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User>{

    User findByName(String name);
}
