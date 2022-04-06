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
    public List<Score> getScore(int size, int page) {
        Session session = sessionFactory.openSession();
        Query<Score> query = session.createQuery("from Score order by game_score desc", Score.class);
        query.setFirstResult((size - 1) * page);
        query.setMaxResults(size);
        List<Score> score = query.list();
        score.forEach(System.out::println);
        return score;
    }

    @Override
    public void saveScore(Score score) {
        Session session = sessionFactory.openSession();
        session.save(score);
    }
}
