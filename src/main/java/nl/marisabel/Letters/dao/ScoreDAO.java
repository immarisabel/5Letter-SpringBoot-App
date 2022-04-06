package nl.marisabel.Letters.dao;

import nl.marisabel.Letters.entity.Score;

import java.util.List;

public interface ScoreDAO {

    public List<Score> getScore(int size, int page);
    public void saveScore(Score score);

}
