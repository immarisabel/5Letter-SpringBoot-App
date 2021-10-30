package nl.marisabel.Letters.sessions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener {

    @Component
    public class SessionListenerHM implements HttpSessionListener {

        private final Logger logger = LoggerFactory.getLogger(SessionListenerHM.class);

        @Override
        public void sessionCreated(HttpSessionEvent arg0) {
            logger.debug("======= sessionCreated =======");
            logger.debug(">>>>>> SESSION ID: " + arg0.getSession().getId());
        }

        @Override
        public void sessionDestroyed(HttpSessionEvent arg0) {
            logger.debug("======= sessionDestroyed =======");
            logger.debug(">>>>>> SESSION EXPIRED: " + arg0.getSession().getId());
        }

    }
}
