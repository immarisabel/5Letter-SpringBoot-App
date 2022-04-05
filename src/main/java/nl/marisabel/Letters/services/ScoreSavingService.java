package nl.marisabel.Letters.services;

import nl.marisabel.Letters.entity.Score;

import java.util.List;

public interface ScoreSavingService{


    public List<Score> getScore(int size, int page);
    public void saveScore(Score score);
}
