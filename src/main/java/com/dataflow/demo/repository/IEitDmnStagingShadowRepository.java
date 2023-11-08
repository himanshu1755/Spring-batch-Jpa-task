package com.dataflow.demo.repository;

import com.dataflow.demo.entities.EitDmnStagingShadow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface IEitDmnStagingShadowRepository extends JpaRepository<EitDmnStagingShadow, String> {
}
