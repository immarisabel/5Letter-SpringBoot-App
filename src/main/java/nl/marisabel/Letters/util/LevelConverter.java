package nl.marisabel.Letters.util;


import nl.marisabel.Letters.dto.Level;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class LevelConverter implements AttributeConverter<Level, String> {

    @Override
    public String convertToDatabaseColumn(Level level) {
        if (level == null) {
            return null;
        }
        return level.getLvlName();    }

    @Override
    public Level convertToEntityAttribute(String lvlName) {
        if (lvlName == null) {
            return null;
        }

        return Stream.of(Level.values())
                .filter(c -> c.getLvlName().equals(lvlName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
