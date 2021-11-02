package nl.marisabel.Letters.dto;

public class AttemptsDTO {

    private int attempts;

    public int getAttempts() {
        System.out.println("Getter");
        return attempts;
    }

    public void setAttempts(int attempts) {
        System.out.println("Setter");
        this.attempts = attempts;
    }
}
