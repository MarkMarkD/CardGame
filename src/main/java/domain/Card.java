package domain;

public class Card {
    private String suit;
    private int num;

    public Card(int num, String suit) {
        this.suit = suit;
        this.num = num;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getFullCardName() {
        if (num == 11)
            return ("jack of " + suit);
        else if (num == 12)
            return ("queen of " + suit);
        else if (num == 13)
            return ("king of " + suit);
        else if (num == 14)
            return ("ace of " + suit);
        else return (num + " of " + suit);

    }

    @Override
    public String toString() {
        return getFullCardName();
    }
}
