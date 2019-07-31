package com.mim.repository;


//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;

import com.mim.domain.group.Group;
import com.mim.domain.usergroup.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    @Override
    Optional<UserGroup> findById(Long id);

    List<UserGroup> findByGroupId(Long groupdId);

    List<UserGroup> findByUid(Long uid);

}