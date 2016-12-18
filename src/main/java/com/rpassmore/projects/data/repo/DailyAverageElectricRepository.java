package com.rpassmore.projects.data.repo;

import com.rpassmore.projects.data.entity.DailyAverageElectric;
import com.rpassmore.projects.data.entity.ElectricReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by rob on 06/08/16.
 */
@RepositoryRestResource(collectionResourceRel = "dailyreadings", path = "dailyreadings")
@CrossOrigin
public interface DailyAverageElectricRepository extends JpaRepository<DailyAverageElectric, Long> {
  List<DailyAverageElectric> findByDateBetween(@Param("start")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                          @Param("end")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end);
}
