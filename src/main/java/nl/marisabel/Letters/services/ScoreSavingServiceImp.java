package nl.marisabel.Letters.services;

import nl.marisabel.Letters.dao.ScoreDaoImp;
import nl.marisabel.Letters.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScoreSavingServiceImp implements ScoreSavingService{

   @Autowired
   public ScoreDaoImp scoreDao;

    @Override
    @Transactional
    public List<Score> getScore() {
        return scoreDao.getScore();
    }

    @Override
    @Transactional
    public void saveScore(Score score) {
        scoreDao.saveScore(score);
    }
}
