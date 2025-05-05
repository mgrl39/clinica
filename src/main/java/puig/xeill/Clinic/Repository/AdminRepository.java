package puig.xeill.Clinic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import puig.xeill.Clinic.Model.Persons.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
