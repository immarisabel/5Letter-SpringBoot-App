package nl.marisabel.Letters.entity;

import lombok.Data;
import nl.marisabel.Letters.dto.Level;

import javax.annotation.Priority;
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
    private int gameScore;
    private String name;
    private String selectedLevelName;

}
