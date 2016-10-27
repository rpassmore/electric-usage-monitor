package com.rpassmore.projects.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Created by rob on 05/08/16.
 */
@Entity
public class ElectricReading {
  @Id
  @GeneratedValue
  private Long id;

  private LocalDateTime date;

  private Double current;

  private Double voltage = 240d;

  public ElectricReading() {
  }

  public ElectricReading(Double current, LocalDateTime readingDate) {
    this.current = current;
    this.date = readingDate;
  }

  @Override
  public String toString() {
    return String.format("%d current:%f power:%f time:%s", id, current, getPower(), date.toString());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public LocalDateTime getReadingDate() {
    return date;
  }

  public void setReadingDate(LocalDateTime readingDate) {
    this.date = readingDate;
  }

  public Double getPower() {
    return current * voltage;
  }
}
