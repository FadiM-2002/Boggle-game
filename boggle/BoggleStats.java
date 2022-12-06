package boggle;

import java.util.HashSet;
import java.util.Set;

/**
 * The BoggleStats class for the first Assignment in CSC207, Fall 2022
 * The BoggleStats will contain statsitics related to game play Boggle 
 */
public class BoggleStats {

    /**
     * set of words the player finds in a given round 
     */  
    private Set<String> playerWords = new HashSet<String>();  
    /**
     * set of words the computer finds in a given round 
     */  
    private Set<String> computerWords = new HashSet<String>();  
    /**
     * the player's score for the current round
     */  
    private int pScore; 
    /**
     * the computer's score for the current round
     */  
    private int cScore; 
    /**
     * the player's total score across every round
     */  
    private int pScoreTotal; 
    /**
     * the computer's total score across every round
     */  
    private int cScoreTotal; 
    /**
     * the average number of words, per round, found by the player
     */  
    private double pAverageWords; 
    /**
     * the average number of words, per round, found by the computer
     */  
    private double cAverageWords; 
    /**
     * the current round being played
     */  
    private int round;

    /**
     * the time the user played in a given round
     */
    private int pTime;

    /**
     * the time the opponent played in a given round
     */
    private int oTime;

    /**
     * the total time the user played
     */
    private int pTotalTime;

    /**
     * the total time the opponent played
     */
    private int oTotalTime;

    /**
     * the user's number of words per minute
     */
    private double pWordsPerMin;

    /**
     * the opponent's number of words per minute
     */
    private double oWordsPerMin;

    /**
     * enumarable types of players (human or computer)
     */


    public enum Player {
        Human("Human"),
        Computer("Computer");
        private final String player;
        Player(final String player) {
            this.player = player;
        }
    }

    /** BoggleStats constructor
     * ----------------------
     * Sets round, totals and averages to 0.
     * Initializes word lists (which are sets) for computer and human players.
     */
    public BoggleStats() {
        this.round = 0;
        this.cAverageWords = 0.0;
        this.pAverageWords = 0.0;
        this.cScoreTotal = 0;
        this.pScoreTotal = 0;
        this.cScore = 0;
        this.pScore = 0;
        this.pTime = 0;
        this.oTime = 0;
        this.pWordsPerMin = 0;
        this.oWordsPerMin = 0;
        this.pTotalTime = 0;
        this.oTotalTime = 0;
        this.computerWords = new HashSet<>();
        this.playerWords = new HashSet<>();
    }

    /**
     * Add a word to a given player's word list for the current round.
     * You will also want to increment the player's score, as words are added.
     *
     * @param word     The word to be added to the list
     * @param player  The player to whom the word was awarded
     */
    public void addWord(String word, Player player) {
        if (player.player.equals("Human")){
            playerWords.add(word);
            pScore += (word.length() - 3);
        }
        else {
            computerWords.add(word);
            cScore += (word.length() - 3);
        }
    }

    /**
     * End a given round.
     * This will clear out the human and computer word lists, so we can begin again.
     * The function will also update each player's total scores, average words per round, and
     * reset the current scores for each player to zero.
     * Finally, increment the current round number by 1.
     */
    public void endRound() {
        round++;
        pTotalTime += pTime;
        oTotalTime += oTime;
        pAverageWords = (pAverageWords * (round - 1) + playerWords.size()) / round;
        cAverageWords = (cAverageWords * (round - 1) + computerWords.size()) / round;
        pWordsPerMin = ((pWordsPerMin * ((double) (pTotalTime - pTime) / 60)) + playerWords.size()) / ((double) pTotalTime / 60);
        oWordsPerMin = ((oWordsPerMin * ((double) (oTotalTime - oTime) / 60)) + computerWords.size()) / ((double) oTotalTime / 60);
        playerWords.clear();
        computerWords.clear();
        pScoreTotal += pScore;
        cScoreTotal += cScore;
        pScore = 0;
        cScore = 0;
    }

    /**
     * Summarize one round of boggle.  Print out:
     * The words each player found this round.
     * Each number of words each player found this round.
     * Each player's score this round.
     */
    public void summarizeRound() {
        System.out.println("Words found by P1: " + playerWords);
        System.out.println("Number of words found by P1: " + playerWords.size());
        System.out.println("P1's score: " + pScore);
        System.out.println("P1's time played: " + pTime);
        System.out.println("Words found by P1's opponent: " + computerWords);
        System.out.println("Number of words found by P1's opponent: " + computerWords.size());
        System.out.println("P1 opponent's score: " + cScore);
        System.out.println("P1 opponent's time played: " + oTime);
    }

    /**
     * Summarize the entire boggle game.  Print out:
     * The total number of rounds played.
     * The total score for either player.
     * The average number of words found by each player per round.
     */
    public void summarizeGame() {
        System.out.println("Number of rounds: " + round);
        System.out.println("P1's total score: " + pScoreTotal);
        System.out.println("P1's average number of words per round: " + pAverageWords);
        System.out.println("P1's total time played: " + pTotalTime);
        System.out.println("P1's Average number of words per minute: " + pWordsPerMin);
        System.out.println("P1 Opponent's total score: " + cScoreTotal);
        if (cAverageWords != 0) {
            System.out.println("P1 Opponent's average number of words per round: " + cAverageWords);
        }
        System.out.println("P1 opponent's total time played: " + oTotalTime);
        System.out.println("P1 opponent's Average number of words per Min: " + oWordsPerMin);
    }

    /**
     * @return Set<String> The player's word list
     */
    public Set<String> getPlayerWords() {
        return this.playerWords;
    }

    /**
     * @return int The number of rounds played
     */
    public int getRound() {
        return this.round;
    }

    /**
    * @return int The current player score
    */
    public int getScore() {
        return this.pScore;
    }

    public void setPlayerTime(int time) {pTime = time;}

    public void setOpponentTime(int time) {oTime = time;}
}
