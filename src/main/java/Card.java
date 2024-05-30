public class Card {
    private int number;
    private String color;
    private boolean isSkipReverse;
    private boolean isWild;
    private boolean isPlusTwo;
    private boolean isPlusFour;
    public Card(int number, String color, boolean isSkipReverse, boolean isWild, boolean isPlusTwo, boolean isPlusFour){
        this.number = number;
        this.color = color;
        this.isSkipReverse = isSkipReverse;
        this.isWild = isWild;
        this.isPlusTwo = isPlusTwo;
        this.isPlusFour = isPlusFour;
    }
    public int getNumber(){
        return number;
    }
    public String getColor(){
        return color;
    }
    public boolean getIsWild(){
        return isWild;
    }
    public boolean getIsPlusTwo(){
        return isPlusTwo;
    }
    public boolean getIsPlusFour(){
        return isPlusFour;
    }
    public boolean getIsSkipReverse(){
        return isSkipReverse;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void setWild(boolean isWild){
        this.isWild = isWild;
    }
    public void setNumber(int number){
        this.number = number;
    }
    /*override*/
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Card otherCard = (Card) obj;
        return number == otherCard.number && color.equals(otherCard.color);
    }
}
