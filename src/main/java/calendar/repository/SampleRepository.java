package calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import calendar.model.Sample;

public interface SampleRepository extends JpaRepository<Sample, Long> {

}
