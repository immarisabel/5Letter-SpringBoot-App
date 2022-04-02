package nl.marisabel.Letters.entity;

import lombok.Data;
import nl.marisabel.Letters.services.Level;

import javax.persistence.*;

@Data
@Entity(name = "Score")
@Table(schema = "5Letters")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private String score;
    private String name;
    private Level level;
}
