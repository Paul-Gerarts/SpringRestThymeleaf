package com.springrestthymeleaf.excercise.repositories;

import com.springrestthymeleaf.excercise.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    List<Member> getAllMembers();

    Member getMemberBy(Integer id);
}
