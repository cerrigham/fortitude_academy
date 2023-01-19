package it.proactivity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "project")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "reporting_id")
    private String reportingId;

    @ManyToOne
    private Customer customer;

    @OneToMany
    @JoinColumn(name="project_id")
    private List<Allocation> allocations;

    @Override
    public String toString() {
        return "project name : " + getName() + ", Expire date : " + getEndDate();
    }
}
