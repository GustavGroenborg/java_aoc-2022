import java.util.*;

public class Round {
    public Symbol player;
    public Symbol opponent;

    public Round(String inputRound) {
        inputRound.chars()
                .mapToObj(c -> (char) c)
                .filter(Character::isAlphabetic)
                .forEach(this::setSymbol);
    }

    private void setSymbol(Character c) {
        final char OPPONENT_ROCK = 'A';
        final char OPPONENT_PAPER = 'B';
        final char OPPONENT_SCISSORS = 'C';
        final char PLAYER_ROCK = 'X';
        final char PLAYER_PAPER = 'Y';
        final char PLAYER_SCISSORS = 'Z';

        Symbol symbol;

        switch(c) {
            case PLAYER_ROCK:
                this.player = Symbol.ROCK;
                break;
            case PLAYER_PAPER:
                this.player = Symbol.PAPER;
                break;
            case PLAYER_SCISSORS:
                this.player = Symbol.SCISSORS;
                break;
            case OPPONENT_ROCK:
                this.opponent = Symbol.ROCK;
                break;
            case OPPONENT_PAPER:
                this.opponent = Symbol.PAPER;
                break;
            case OPPONENT_SCISSORS:
                this.opponent = Symbol.SCISSORS;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }
    }

    public Integer getScore() {
        final Integer WIN_WEIGHT = 6;
        final Integer DRAW_WEIGHT = 3;
        final Integer LOSS_WEIGHT = 0;

        if (playerWon()) {
            return getPlayerWeight() + WIN_WEIGHT;
        } else if (playerDraw()) {
            return getPlayerWeight() + DRAW_WEIGHT;
        } else {
            return getPlayerWeight() + LOSS_WEIGHT;
        }
    }

    private Integer getPlayerWeight() {
        return determineSymbolWeight(this.player);
    }

    private Integer getOpponentWeight() {
        return determineSymbolWeight(this.opponent);
    }

    private Integer determineSymbolWeight(Symbol symbol) {
        final Integer ROCK_WEIGHT = 1;
        final Integer PAPER_WEIGHT = 2; // lol.
        final Integer SCISSOR_WEIGHT = 3;

        switch (symbol) {
            case Symbol.ROCK:
                return ROCK_WEIGHT;
            case Symbol.PAPER:
                return PAPER_WEIGHT;
            case Symbol.SCISSORS:
                return SCISSOR_WEIGHT;
            default:
                return -1;
        }
    }

    public Boolean playerWon() {
        switch(this.player) {
            case Symbol.ROCK:
                return this.opponent == Symbol.SCISSORS;
            case Symbol.PAPER:
                return this.opponent == Symbol.ROCK;
            case Symbol.SCISSORS:
                return this.opponent == Symbol.PAPER;
            default:
                return false;
        }
    }

    public Boolean playerDraw() {
        return this.player == this.opponent;
    }


}
