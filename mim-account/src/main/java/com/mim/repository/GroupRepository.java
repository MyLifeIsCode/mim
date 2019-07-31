package com.mim.repository;


//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;

import com.mim.domain.group.Group;
import com.mim.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Override
    Optional<Group> findById(Long id);
}