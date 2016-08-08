package com.rpassmore.projects.electric.reader;


import com.rpassmore.projects.dto.ElectricReadingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by rob on 05/08/16.
 */
@Component
public class DisplayReadings {
  private static final Logger log = LoggerFactory.getLogger(DisplayReadings.class);

  @Autowired
  private ElectricReadingRepository repository;

  @Scheduled(fixedRate = 60000)
  public void displayReadings() {
    log.info(String.format("Readings = %d", repository.count()));
    repository.findAll().forEach(System.out::println);
  }
}
