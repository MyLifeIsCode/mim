package com.mim.repository;


//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;

import com.mim.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long id);

}