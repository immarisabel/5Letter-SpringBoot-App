package nl.marisabel.Letters.util;

import nl.marisabel.Letters.controller.GameController;
import nl.marisabel.Letters.services.RandomWordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.Util;
import org.springframework.beans.factory.annotation.Autowired;

public class LogFormat {


    public static void log(Class<?> clazz, String txt) {
        Class<?> autoComputedCallingClass = Util.getCallingClass();
        Logger LOGGER = LoggerFactory.getLogger(clazz);
        LOGGER.info(":::::::::: " + txt);
    }
}


