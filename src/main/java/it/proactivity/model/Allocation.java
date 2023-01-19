package it.proactivity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "allocation")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class Allocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private HumanResource humanResource;

    @ManyToOne
    private Project project;

    @Override
    public String toString() {
        return getHumanResource() + "\n" +
                getProject();
    }
}
