package com.springrestthymeleaf.excercise.repositories;

import com.springrestthymeleaf.excercise.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
