package com.rpassmore.projects.data.repo;

import com.rpassmore.projects.data.entity.ElectricReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by rob on 05/08/16.
 */

@RepositoryRestResource(collectionResourceRel = "readings", path = "readings")
public interface ElectricReadingRepository extends JpaRepository<ElectricReading, Long> {
  List<ElectricReading> findByReadingDateBetween(LocalDateTime start, LocalDateTime end);

  @Transactional
  List<ElectricReading> deleteByReadingDateLessThan(LocalDateTime before);

}
