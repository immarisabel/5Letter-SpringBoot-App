package nl.marisabel.Letters.services;

import nl.marisabel.Letters.dao.ScoreDaoImp;
import nl.marisabel.Letters.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static nl.marisabel.Letters.util.LogFormat.log;

@Service
public class ScoreSavingServiceImp implements ScoreSavingService{

   @Autowired
   public ScoreDaoImp scoreDao;

    @Override
    @Transactional
    public List<Score> getScore(int size, int page) {
        return scoreDao.getScore(size, page);
    }

    @Override
    @Transactional
    public void saveScore(Score score) {
        log(ScoreSavingServiceImp.class, "Score stored in database: " + score);
        scoreDao.saveScore(score);
    }
}
