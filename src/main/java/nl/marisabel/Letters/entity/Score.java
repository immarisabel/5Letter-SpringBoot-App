package nl.marisabel.Letters.entity;

import lombok.Data;
import nl.marisabel.Letters.dto.Level;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "5Letters")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private int score;
    private String name ="test";
    private String lvl;

}
