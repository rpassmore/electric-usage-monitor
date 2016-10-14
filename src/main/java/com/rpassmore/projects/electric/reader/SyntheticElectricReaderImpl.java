package com.rpassmore.projects.electric.reader;

import com.rpassmore.projects.data.entity.ElectricReading;
import com.rpassmore.projects.data.repo.ElectricReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * Created by rob on 05/08/16.
 */
@Component
public class SyntheticElectricReaderImpl implements ElectricReader {

  @Autowired
  ElectricReadingRepository repository;

  Random randomReading = new Random();

  @Scheduled(fixedRate = 10000)
  public void reportCurrentReading() {
    ElectricReading electricReading = new ElectricReading(randomReading.nextDouble() * 100, LocalDateTime.now());
    repository.save(electricReading);

    repository.deleteByReadingDateLessThan(LocalDateTime.now().minusDays(3));
  }
}
