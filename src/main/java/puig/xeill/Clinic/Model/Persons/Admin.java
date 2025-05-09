package puig.xeill.Clinic.Model.Persons;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Admins")
public class Admin extends User {

}
