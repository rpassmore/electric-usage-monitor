package com.rpassmore.projects.data.repo;

import com.rpassmore.projects.data.entity.DailyAverageElectric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by rob on 06/08/16.
 */
@RepositoryRestResource(collectionResourceRel = "dailyreadings", path = "dailyreadings")
public interface DailyAverageElectricRepository extends JpaRepository<DailyAverageElectric, Long> {
}
