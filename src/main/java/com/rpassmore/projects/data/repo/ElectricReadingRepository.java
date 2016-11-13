package com.rpassmore.projects.data.repo;

import com.rpassmore.projects.data.entity.ElectricReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by rob on 05/08/16.
 */

@RepositoryRestResource(collectionResourceRel = "readings", path = "readings")
@CrossOrigin
public interface ElectricReadingRepository extends JpaRepository<ElectricReading, Long> {
  List<ElectricReading> findByDateBetween(@Param("start")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                          @Param("end")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end);

  ElectricReading findFirstByOrderByDateDesc();

  @Transactional
  List<ElectricReading> deleteByDateLessThan(LocalDateTime before);

}
