import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Random;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){

      String FilePath = "src/text.txt";
      ArrayList<String> words = new ArrayList<>();

      try(BufferedReader reader = new BufferedReader(new FileReader(FilePath))){
          String line;

          while((line = reader.readLine()) != null){
//            System.out.println(line);
            words.add(line.trim());
          }
      }
      catch(FileNotFoundException e){
        System.out.println("File not found");
      }
      catch(IOException e){
        System.out.println("Something went wrong");
      }
      Random random = new Random();
      String word = words.get(random.nextInt(words.size()));

      System.out.println(word);
      Scanner sc = new Scanner(System.in);
      ArrayList<Character> wordState = new ArrayList<>();
      int wrongGuesses = 0;

      for(int i = 0; i < word.length(); i++){
        wordState.add('_');
      }

      System.out.println("Welcome to Hangman game");

      while(wrongGuesses < word.length()){

        System.out.println(getHangmanArt(wrongGuesses));


        System.out.print("Word: ");
        for(char c : wordState){
          System.out.print(c + " ");
        }
        System.out.println();

        System.out.print("Guess a letter: ");
        char guess = sc.next().toLowerCase().charAt(0);//convert to char and make it lowercase

        if(word.indexOf(guess) >= 0){
          System.out.println("Correct guess ");

          for(int i = 0; i < word.length(); i++){
            if(word.toLowerCase().charAt(i) == guess){
              wordState.set(i, guess);
            }
          }
          if(!wordState.contains('_')){
            System.out.println("You win");
            System.out.println(getHangmanArt(wrongGuesses));
            break;
          }
        }
        else {
          wrongGuesses++;
          System.out.println("WRONG guess ");
          System.out.println("Guesses: "+wrongGuesses);

        }
      }
      if(wrongGuesses>=word.length()){
        System.out.println(getHangmanArt(wrongGuesses));
        System.out.println("GAME OVER");
        System.out.println("The word was : "+ word);
      }

      sc.close();

    }
    static String getHangmanArt(int wrongGuesses){
      return switch(wrongGuesses){
        case 1 ->"O";
        case 2 -> """
                O
                |
                """;
        case 3 -> """
                O
               /|
               """;
        case 4 -> """
                O
               /|\\
               """;
        case 5 -> """
                  O
                 /|\\
                 / \\
                 """;
        default -> "";
      };
    }
}
