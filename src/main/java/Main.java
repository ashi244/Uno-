import java.util.Scanner;
public class Main {
    public static void main(String[] args){
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
    public static boolean GameSetup(){
        System.out.println("Would you like to play again? Please type yes or no");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        if (response.equals("yes")){
            return true;
        }
        return false;
    }
}
