package puig.xeill.Clinic.Model.Persons;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class User extends Person {
    private String user;
    private String password;
}
