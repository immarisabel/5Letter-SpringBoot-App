package nl.marisabel.Letters.services;

import nl.marisabel.Letters.entity.Score;

import java.util.List;

public interface ScoreSavingService{


    public List<Score> getScores();
    public void saveScore(Score score);
}
