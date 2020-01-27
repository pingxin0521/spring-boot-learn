package com.hyp.learn.docker.repository;

import com.hyp.learn.docker.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.docker.repository
 * hyp create at 20-1-26
 **/
public interface VisitorRepository extends JpaRepository<Visitor,Long> {
    Visitor findByIp(String ip);

}
