package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.UserInfo;

@Repository
public interface UserInfoDAO extends JpaRepository<UserInfo, Integer> {

}
