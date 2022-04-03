package nl.marisabel.Letters.dao;

import nl.marisabel.Letters.entity.Score;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static nl.marisabel.Letters.util.LogFormat.log;

@Repository
public class ScoreDaoImp implements ScoreDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Score> getScore() {
        Session session = sessionFactory.openSession();
        Query<Score> query = session.createQuery("from Score", Score.class);
        List<Score> scores = query.getResultList();
        scores.forEach(System.out::println);
        return scores;
    }

    @Override
    public void saveScore(Score score) {
        Session session = sessionFactory.openSession();
        session.save(score);
        log(ScoreDaoImp.class, "(OK) Session score saved : " + score);
    }
}
