package nl.marisabel.Letters.dto;

public class GuessDTO {

    private String guess;

    public String getGuess() {
        return guess;
    }

    public String getGuess(String words) {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    @Override
    public String toString() {
        return "GuessDTO{" +
                "guess='" + guess + '\'' +
                '}';
    }
}
