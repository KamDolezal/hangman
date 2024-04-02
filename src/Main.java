import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final Random random = new Random();
        final Scanner scanner = new Scanner(System.in);
        final String[]words={"java","skillmea","programming","python","webinar"};
        final String wordToGuess = selectWord(words,random);
        String hiddenWord = generateHiddenWord(wordToGuess);    // tuto promennou chci updatovat, proto nemuze mit klicove slovo final

        System.out.println("Welcome to game Hangman!");
        System.out.println("Guess the word> " + hiddenWord);

        int MAX_INCORRECT_GUESSES = 6;      // KONSTANTY PISEME TAKTO
        int incorrectGuesses = 0;

        while(incorrectGuesses<MAX_INCORRECT_GUESSES && hiddenWord.contains("_")){
            System.out.println("Enter a letter> ");
            char guess = scanLetter(scanner);

            if(wordToGuess.contains(String.valueOf(guess))){
                hiddenWord = revealLetters(wordToGuess,hiddenWord,guess);
                System.out.println("Correct guess! Updated word: " + hiddenWord);
            } else{
                incorrectGuesses++;
                System.out.println("Incorrect guess! You have " + (MAX_INCORRECT_GUESSES - incorrectGuesses) + " guesses left.");
            }
        }
        if(wordToGuess.equals(hiddenWord)){
            System.out.println("Good job!");
        }else{
            System.out.println("Unfortunately, you are not good guesser!");
        }

    }
    public static char scanLetter(Scanner scanner){
        char guess;
        while(true) {

            try {
                String line = scanner.nextLine();
                if (line.length() != 1) {
                    throw new Exception("Line length is not 1. Please enter a single letter.");
                }

                guess = line.charAt(0);
                if (!Character.isLetter(guess)) {
                    throw new Exception("Character is not a letter. Please enter a single letter.");
                }
                break;

            } catch (Exception e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
        return guess;
    }
    public static String selectWord(String[]words, Random random){
        return words[random.nextInt(words.length)];     //random da 0 - length-1 ..exclusive
    }
    public static String generateHiddenWord(String word){
        return "_".repeat(word.length());
    }
    public static String revealLetters(String wordToGuess, String hiddenWord, char letter){
        char[]hiddenWordChars = hiddenWord.toCharArray();

        for (int i=0; i<wordToGuess.length(); i++){
            if(wordToGuess.charAt(i) == letter){
                hiddenWordChars[i]=letter;
            }
        }
        return String.valueOf(hiddenWordChars);
    }
}