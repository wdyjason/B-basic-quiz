package com.wdyjason.basicquiz.repository;

import com.wdyjason.basicquiz.domain.Education;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public interface EducationRepository extends CrudRepository<Education, Long> {

    List<Education> findByUserId(long userId);
}
