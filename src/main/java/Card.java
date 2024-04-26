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
}
