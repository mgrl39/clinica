package puig.xeill.Clinic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import puig.xeill.Clinic.Model.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
