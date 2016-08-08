package com.rpassmore.projects.electric.reader;

import com.rpassmore.projects.dto.DailyAverageElectric;
import com.rpassmore.projects.dto.DailyAverageElectricRepository;
import com.rpassmore.projects.dto.ElectricReading;
import com.rpassmore.projects.dto.ElectricReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by rob on 06/08/16.
 */
@Component
public class UpdateDailyAverage {

  @Autowired
  private DailyAverageElectricRepository dailyAverageElectricRepository;

  @Autowired
  private ElectricReadingRepository electricReadingRepository;

  //schedule for midnight every day
  @Scheduled(cron = "0 0 0 * * *")
  public void calculateDailyAverage() {
    //get all readings in the last 24hrs
    List<ElectricReading> readings = electricReadingRepository.findByReadingDateBetween(LocalDateTime.now().minusHours(24), LocalDateTime.now());

    //calculate daily averages
    double averageCurrent = readings.stream().mapToDouble(ElectricReading::getCurrent).average().getAsDouble();
    double averageVoltage = readings.stream().mapToDouble(ElectricReading::getVoltage).average().getAsDouble();
    double averagePower = readings.stream().mapToDouble(ElectricReading::getPower).average().getAsDouble();

    //store daily average
    DailyAverageElectric dailyAverageElectric = new DailyAverageElectric();
    dailyAverageElectric.setCurrent(averageCurrent);
    dailyAverageElectric.setVoltage(averageVoltage);
    dailyAverageElectric.setPower(averagePower);
    dailyAverageElectric.setDate(LocalDateTime.now());

    dailyAverageElectricRepository.saveAndFlush(dailyAverageElectric);
  }
}
