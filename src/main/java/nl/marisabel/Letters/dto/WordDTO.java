package nl.marisabel.Letters.dto;


import org.springframework.stereotype.Service;

@Service
public class WordDTO {

    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
        System.out.println("new word set!");
    }


    @Override
    public String toString() {
        return "WordDTO{" +
                "word='" + word + '\'' +
                '}';
    }
}
