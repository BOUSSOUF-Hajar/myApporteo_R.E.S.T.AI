package myApporteo.entities;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name ="affaire")
public class affaire extends AbstractEntity{
    @ManyToOne
    private Apporteur apporteur;
    @ManyToOne
    private Partenaire partenaire;
}
