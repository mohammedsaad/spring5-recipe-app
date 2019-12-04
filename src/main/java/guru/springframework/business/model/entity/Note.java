package guru.springframework.business.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Lob
    private String recipesNote;
    @OneToOne
    private Recipe recipe;
}
