package nl.marisabel.Letters.services.words;


import org.springframework.stereotype.Service;

@Service
public class WordDTO {

    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


    @Override
    public String toString() {
        return "WordDTO{" +
                "word='" + word + '\'' +
                '}';
    }
}
