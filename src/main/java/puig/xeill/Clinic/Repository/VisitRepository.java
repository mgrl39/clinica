package puig.xeill.Clinic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Model.Visit;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByIdDentist(Dentist dentist);
}
