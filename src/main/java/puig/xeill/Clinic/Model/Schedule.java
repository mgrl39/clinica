package puig.xeill.Clinic.Model;

import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "firstDay")
    private WeekDay firstDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "lastDay")
    private WeekDay lastDay;


    @Column(name = "firstHour")
    private Date firstHour;

    @Column(name = "lastHour")
    private Date lastHour;
}
