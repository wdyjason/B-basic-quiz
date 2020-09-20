package com.wdyjason.basicquiz.repository;

import com.wdyjason.basicquiz.domain.Education;
import com.wdyjason.basicquiz.entity.EducationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends CrudRepository<EducationEntity, Long> {

    List<EducationEntity> findByUserId(long userId);
}
