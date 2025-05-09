package puig.xeill.Clinic.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import puig.xeill.Clinic.Model.Enums.WeekDay;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedules")
public class Schedule {
    @Id
    private Long id;

    @Column(name = "firstDay")
    private WeekDay firstDay;

    @Column(name = "lastDay")
    private WeekDay lastDay;

    @Column(name = "firstHour")
    private Date firstHour;

    @Column(name = "lastHour")
    private Date lastHour;
}
