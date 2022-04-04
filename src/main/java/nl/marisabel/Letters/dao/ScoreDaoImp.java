package nl.marisabel.Letters.dao;

import nl.marisabel.Letters.entity.Score;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScoreDaoImp implements ScoreDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Score> getScore() {
        Session session = sessionFactory.openSession();
        Query<Score> query = session.createQuery("from Score", Score.class);
        List<Score> score = query.getResultList();
        score.forEach(System.out::println);
        return score;
    }

    @Override
    public void saveScore(Score score) {
        Session session = sessionFactory.openSession();
        session.save(score);
    }
}
