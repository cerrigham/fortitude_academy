package it.proactivity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "job_description")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class JobDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "technology_id")
    private List<Technology> technologyList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + id + "]" + " ");
        sb.append(project);
        return sb.toString();
    }


}
