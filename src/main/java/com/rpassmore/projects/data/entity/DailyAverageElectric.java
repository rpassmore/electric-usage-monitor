package com.rpassmore.projects.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Created by rob on 06/08/16.
 */
@Entity
public class DailyAverageElectric {
  @Id
  @GeneratedValue
  private Long id;

  private LocalDateTime date;

  private Double current;

  private Double voltage;

  private Double power;

  public DailyAverageElectric() {
  }

  public DailyAverageElectric(Double current, LocalDateTime date) {
    this.current = current;
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public Double getCurrent() {
    return current;
  }

  public void setCurrent(Double current) {
    this.current = current;
  }

  public Double getVoltage() {
    return voltage;
  }

  public void setVoltage(Double voltage) {
    this.voltage = voltage;
  }

  public Double getPower() {
    return power;
  }

  public void setPower(Double power) {
    this.power = power;
  }
}
