package boggle;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class TwoPlayerMode implements BoggleGameMode{
    @Override
    public void opMove(BoggleGrid board, Map<String, ArrayList<Position>> allWords, BoggleStats gameStats) {
        System.out.println("It's P2's turn to find some words!");
        System.out.println(board);
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("")) break;
            for (String word: allWords.keySet()) {
                if (!gameStats.getPlayerWords().contains(word.toUpperCase()) && word.equalsIgnoreCase(input)) {
                    gameStats.addWord(input.toUpperCase(), BoggleStats.Player.Computer);
                }
            }
        }
    }
}
