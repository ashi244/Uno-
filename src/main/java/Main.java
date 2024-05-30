import javax.swing.text.Element;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.util.Random;

public class Main {
    static ArrayList<Card> playerDeck = new ArrayList<>();
    static ArrayList<Card> computerDeck = new ArrayList<>();
    static ArrayList<Card> avaliableCards = new ArrayList<>();
    static ArrayList<Card> discardedCards = new ArrayList<>();
    static Card currentCard = new Card(-1,"black", false, false, false, false);
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello! Welcome to my game: UNO");
        System.out.println("Hopefully you should know the rules of this game. You will be dealt a deck of cards at the start, along with a starting card");
        System.out.println("On your turn, you may play any card in your deck if they have the same color, number, or is a WildCard");
        System.out.println("There are also special cards such as Skip, Reverse, and Plus 2 or 4");
        System.out.println("You will be playing against the computer! You win when all your cards are done with");
        boolean answer = GameSetup();
        while (answer){
            System.out.println("Thanks for playing again! You should already know the rules so let's start");
            answer = GameSetup();
        }

    }
    public static boolean GameSetup() throws FileNotFoundException {
        File file = new File("src/main/resources/cardList.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            boolean skipReverse = false;
            boolean plusTwo = false;
            boolean plusFour = false;
            boolean wild = false;
            String color = "black";
            int number = -1;
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()){
                String word = lineScanner.next();
                if (!isInteger(word)){
                    if (word.equals("wild")){
                        wild = true;
                    }
                    else if (word.equals("plusTwo")){
                        plusTwo = true;
                    }
                    else if (word.equals("plusFour")){
                        plusFour = true;
                    }
                    else if ((word.equals("skip") || (word.equals("reverse")))){
                        skipReverse = true;
                    }
                    else{
                        color = word;
                    }
                }
                else{
                    number = Integer.parseInt(word);
                }
            }
            Card card = new Card(number, color, skipReverse, wild, plusTwo, plusFour);
            avaliableCards.add(card);
        }
        /*for (int i = 0; i < avaliableCards.size(); i++){
            System.out.println(avaliableCards.get(i).getIsSkipReverse());
        }*/ // Code for me to check that the stuff from the file is correctly stored
        shuffle(avaliableCards);
        Random random = new Random();
        for (int i = 0; i < 7; i++){
            int randomNumber = random.nextInt(avaliableCards.size() - 0 + 1) + 0;
            playerDeck.add(avaliableCards.get(randomNumber));
            avaliableCards.remove(avaliableCards.get(randomNumber));
        }
        for (int i = 0; i < 7; i++){
            int randomNumber = random.nextInt(avaliableCards.size() - 0 + 1) + 0;
            computerDeck.add(avaliableCards.get(randomNumber));
            avaliableCards.remove(avaliableCards.get(randomNumber));
        }
        currentCard = avaliableCards.get(0);
        avaliableCards.remove(avaliableCards.get(0));
        /*The cards are randomly distributed to the players*/
        gameStart();
        System.out.println("Would you like to play again? Please type yes or no");
        Scanner scanner1 = new Scanner(System.in);
        String response = scanner1.nextLine();
        if (response.equals("yes")){
            return true;
        }
        return false;
    }
    public static void gameStart(){
        Scanner scanner = new Scanner(System.in);
        while (playerDeck.size() != 0){
            while (computerDeck.size() != 0){
                if (avaliableCards.size() == 0){
                    int size = discardedCards.size();
                    shuffle(discardedCards);
                    for (int i = 0; i < size; i++){
                        avaliableCards.add(discardedCards.get(i));
                        discardedCards.remove(discardedCards.get(i));
                    }
                    System.out.println("The deck has been reshuffled!");
                }
                if (playerDeck.size() == 1){
                    System.out.println("You have ONE card left!");
                    String uno = scanner.nextLine();
                    if (!(uno.equals("uno"))){
                        System.out.println("You forgot to say UNO! Pick up two");
                        for (int i = 0; i < 2; i++){
                            playerDeck.add(avaliableCards.get(i));
                            avaliableCards.remove(avaliableCards.get(i));
                        }
                    }
                    else{
                        System.out.println("Phew, you remembered to say uno! No need to pick up two cards now");
                    }

                }
                else if(computerDeck.size() == 1){
                    System.out.println("The computer only has one card ");
                    try {
                        // Pause for 1 second (1000 milliseconds)
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Random random = new Random();
                    int compSaysUno = random.nextInt(0, 1);
                    if (compSaysUno == 0){
                        System.out.println("UNO!");
                    }
                    else{
                        System.out.println("Looks like the computer's forgotten to say Uno! They will have to pick up two cards");
                        for (int i = 0; i < 2; i++){
                            computerDeck.add(avaliableCards.get(i));
                            avaliableCards.remove(avaliableCards.get(i));
                        }
                    }
                }
                String currentCardColor = currentCard.getColor();
                int currentCardNum = currentCard.getNumber();
                boolean currentCardIsWild = currentCard.getIsWild();
                boolean currentCardIsSkipReverse = currentCard.getIsSkipReverse();
                boolean currentCardIsPlusTwo = currentCard.getIsPlusTwo();
                boolean currentCardIsPlusFour = currentCard.getIsPlusFour();
                /*Lets the user know what the current card is*/
                if (!(currentCardColor.equals("black"))){
                    if (!(currentCardNum == -1)){
                        System.out.println("The current card is " + currentCardColor + " " + currentCardNum);
                    }
                    else if (currentCardIsPlusTwo){
                        System.out.println("The current card is " + currentCardColor + " +2");
                    }
                    else if (currentCardIsSkipReverse){
                        System.out.println("The current card is " + currentCardColor + " skip");
                    }
                    else{
                        System.out.println("The current card is " + currentCardColor);
                    }
                }
                else{
                    if (currentCardIsPlusFour){
                        System.out.println("The current card is a wild +4");
                    }
                    else {
                        System.out.println("The current card is a wild");
                    }
                }
                System.out.println("Please choose a card from your deck to play");
                printUserDeck();
                System.out.println("Please type out the card you would like to play from the list above");
                System.out.println("You must type it exactly, or the computer cannot register your input");
                System.out.println("If you do not have a card you can play, please type out draw");

                Scanner userPlays = new Scanner(System.in);
                /*Stores the information from user input into a card object*/
                boolean skipReverse = false;
                boolean plusTwo = false;
                boolean plusFour = false;
                boolean wild = false;
                boolean drewCard = false;
                String color = "black";
                int number = -1;
                String line = userPlays.nextLine();
                if (line.equals("draw")){
                    drawCard();
                    drewCard = true;
                }
                else{
                    Scanner lineScanner = new Scanner(line);
                    for (int i = 0; i < 2; i++){
                        String word = lineScanner.next();
                        if (!isInteger(word)){
                            if (word.equals("wild")){
                                wild = true;
                            }
                            else if (word.equals("+2")){
                                plusTwo = true;
                            }
                            else if (word.equals("+4")){
                                plusFour = true;
                            }
                            else if ((word.equals("skip") || (word.equals("reverse")))){
                                skipReverse = true;
                            }
                            else{
                                color = word;
                            }
                        }
                        else{
                            number = Integer.parseInt(word);
                        }
                    }
                }
                Card userInput = new Card(number, color, skipReverse, wild, plusTwo, plusFour);
                System.out.println(userInput.getIsWild());
                System.out.println(userInput.getIsPlusTwo());
                if (!(drewCard)){
                    if (isPlayable(userInput)){
                        if (inPlayerDeck(userInput)){
                            playerDeck.remove(userInput);
                            currentCard = userInput;
                            playCard(userInput);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                // Handle the exception here, if needed
                                e.printStackTrace();
                            }
                            discardedCards.add(userInput);
                            if (!(userInput.getIsSkipReverse())){
                                computerPlay();
                            }
                        }
                        else{
                            System.out.println("The card that you have entered is not in your deck of cards. Please enter again");
                            gameStart();
                        }

                    }
                    else{
                        System.out.println("The card you have entered is not playable. Please try again");
                        gameStart();
                    }
                }
                /*System.out.println(userInput.getNumber());/* Test to make sure the right card has been created*/
                else{
                    computerPlay();
                }
                printComputerDeck();
            }
            System.out.println("You've lost! Better luck next time.");
            break;
        }
        System.out.println("CONGRATULATIONS! You've won the game ");
    }
    public static void playCard(Card card){
        if (card.getIsWild()){
            Scanner scanner = new Scanner(System.in);
            boolean validInput = false;
            while (!validInput) {
                try {
                    System.out.println("You have played a wild card! Please enter the color (red, green, yellow or blue), for what you would like the color to be");
                    String color = scanner.nextLine();
                    if (color.equalsIgnoreCase("red") || color.equalsIgnoreCase("yellow") || color.equalsIgnoreCase("green") || color.equalsIgnoreCase("blue")) {
                        currentCard.setColor(color);
                        currentCard.setNumber(-1);
                        validInput = true;
                        System.out.println("The current color is " + color);
                        if (card.getIsPlusFour()){
                            System.out.println("You have also played a +4. The computer has to pick up 4 cards");
                            for (int i = 0; i < 4; i++){
                                Card addedCard = avaliableCards.get(i);
                                computerDeck.add(addedCard);
                                avaliableCards.remove(addedCard);
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("Invalid color entered. Please enter red, green, yellow or blue.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        else if (card.getIsPlusTwo()){
            System.out.println("You have played a +2. The computer has to pick up 2 cards");
            for (int i = 0; i < 2; i++){
                Card addedCard = avaliableCards.get(i);
                computerDeck.add(addedCard);
                avaliableCards.remove(addedCard);
            }
        }
        else if (card.getIsSkipReverse()){
            System.out.println("The computer's turn has been skipped. It is your turn again");
        }
        else{
            System.out.println("You have successfully played your card");
        }
    }
    public static void computerPlay(){
        for (int i = 0; i < computerDeck.size(); i++){
            Card card = computerDeck.get(i);
            if (isPlayable(card)){
                if (inComputerDeck(card)){
                    currentCard = card;
                    playComputerCard(card);
                    discardedCards.add(card);
                    computerDeck.remove(card);
                    return;
                }
            }
        }
        Card drawnCard = avaliableCards.get(0);
        System.out.println("The computer has drawn a card");
        computerDeck.add(drawnCard);
        avaliableCards.remove(avaliableCards.get(0));
    }
    public static void playComputerCard(Card card){
        if (card.getIsWild()){
            int redNum = 0;
            int blueNum = 0;
            int greenNum = 0;
            int yellowNum = 0;
            for (int i = 0; i < computerDeck.size(); i++){
                String color = computerDeck.get(i).getColor();
                if (color.equals("red")){
                    redNum++;
                }
                else if (color.equals("blue")){
                    blueNum++;
                }
                else if (color.equals("green")){
                    greenNum++;
                }
                else if (color.equals("yellow")){
                    yellowNum++;
                }
            }
            int mostColor = largeNum(redNum, blueNum, greenNum, yellowNum);
            if (mostColor == redNum){
                currentCard.setColor("red");
                System.out.println("The computer has played a wild card. The color has been changed to red");
            }
            else if(mostColor == blueNum){
                currentCard.setColor("blue");
                System.out.println("The computer has played a wild card. The color has been changed to blue");
            }
            else if(mostColor == greenNum){
                currentCard.setColor("green");
                System.out.println("The computer has played a wild card. The color has been changed to green");
            }
            else{
                currentCard.setColor("yellow");
                System.out.println("The computer has played a wild card. The color has been changed to yellow");
            }
            if (card.getIsPlusFour()){
               System.out.println("The computer has also played a +4. You must pick up four. Sorry!");
                for (int i = 0; i < 4; i++){
                    Card addedCard = avaliableCards.get(i);
                    playerDeck.add(addedCard);
                    avaliableCards.remove(addedCard);
                }
            }
        }
        else if (card.getIsPlusTwo()){
            System.out.println("The computer has played a +2. You must pick up two cards");
            for (int i = 0; i < 2; i++){
                Card addedCard = avaliableCards.get(i);
                playerDeck.add(addedCard);
                avaliableCards.remove(addedCard);
            }
        }
        else if (card.getIsSkipReverse()){
            System.out.println("The computer has played a skip card. Your turn has been skipped. Sorry!");
        }
        else{
            System.out.println("The computer has played their card");
        }

    }
    private static void printUserDeck(){
        System.out.println("Your current deck composes of ");
        for (int i = 0; i < playerDeck.size(); i++){
            Card card = playerDeck.get(i);
            printCard(card);
        }
    }
    private static void printComputerDeck(){
        System.out.println("The computer's deck composes of ");
        for (int i = 0; i < computerDeck.size(); i++){
            Card card = computerDeck.get(i);
            printCard(card);
        }
    }
    private static void printCard(Card card){
        if (!(card.getColor().equals("black"))){
            if (!(card.getNumber() == -1)){
                System.out.println(card.getColor() + " " + card.getNumber());
            }
            if (card.getIsPlusTwo()){
                System.out.println(card.getColor() + " +2");
            }
            if (card.getIsSkipReverse()){
                System.out.println(card.getColor() + " skip");
            }
        }
        else{
            if (card.getIsPlusFour()){
                System.out.println("wild +4");
            }
            else {
                System.out.println("wild");
            }
        }
    }
    public static void drawCard() {
        Card drawnCard = avaliableCards.get(0);
        System.out.println("The card you have drawn is ");
        printCard(drawnCard);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Handle the exception here, if needed
            e.printStackTrace();
        }
        playerDeck.add(drawnCard);
        avaliableCards.remove(avaliableCards.get(0));
    }
    private static boolean isPlayable(Card card){
        String cardColor = card.getColor();
        String currentCardColor = currentCard.getColor();
        if (card.getIsWild()){
            return true;
        }
        else if (cardColor.equals(currentCardColor)){
            return true;
        }
        else if (card.getNumber() == currentCard.getNumber()){
            return true;
        }
        return false;
    }
    private static boolean inComputerDeck(Card card){
        for (int i = 0; i < computerDeck.size(); i++){
            if (card.equals(computerDeck.get(i))){
                return true;
            }
        }
        return false;
    }
    private static boolean inPlayerDeck(Card card){
        for (int i = 0; i < playerDeck.size(); i++){
            if (card.equals(playerDeck.get(i))){
                return true;
            }
        }
        return false;
    }
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true; // It is an integer
        } catch (NumberFormatException e) {
            return false; // It is not an integer
        }
    }
    private static void shuffle(ArrayList<Card> arraylist){
        for (int i = 0; i < arraylist.size(); i++){
            int j = (int)(Math.random() * (i + 1));
            Collections.swap(arraylist, i, j);
        }
    }
    private static int largeNum(int num1, int num2, int num3, int num4) {
        int largest = num1;  // Assume the first number is the largest
        if (num2 > largest) {
            largest = num2;
        }
        if (num3 > largest) {
            largest = num3;
        }
        if (num4 > largest) {
            largest = num4;
        }
        return largest;
    }
}
