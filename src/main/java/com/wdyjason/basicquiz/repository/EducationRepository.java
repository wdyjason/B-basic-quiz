package com.wdyjason.basicquiz.repository;

import com.wdyjason.basicquiz.domain.Education;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EducationRepository {
    private final List<Education> educationDataSource = new ArrayList<>();
    private static AtomicLong nextId = new AtomicLong(0);

    public Education save(Education receivedEdu) {
        Long newId = nextId.getAndIncrement();
        receivedEdu.setId(newId);
        educationDataSource.add(receivedEdu);
        return receivedEdu;
    }
}
