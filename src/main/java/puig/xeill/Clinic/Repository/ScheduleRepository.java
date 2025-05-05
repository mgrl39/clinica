package puig.xeill.Clinic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import puig.xeill.Clinic.Model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
