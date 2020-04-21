package com.jackson.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jackson.model.Member;

@Repository
public interface MemberRepo extends JpaRepository <Member, Integer>{
	public List<Member> findByName(String name);
	}