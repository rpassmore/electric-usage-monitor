package com.rpassmore.projects.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by rob on 05/08/16.
 */
public interface ElectricReadingRepository extends JpaRepository<ElectricReading, Long> {
  List<ElectricReading> findByReadingDateBetween(LocalDateTime start, LocalDateTime end);

  @Transactional
  List<ElectricReading> deleteByReadingDateLessThan(LocalDateTime before);

}
