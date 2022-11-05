interface ResultProvider {
    TennisResult getResult();
}

public class Game4 implements Game {
    int    serverScore;
    int    receiverScore;
    String server;
    String receiver;

    public Game4(String player1, String player2) {
        this.server   = player1;
        this.receiver = player2;
    }

    @java.lang.Override
    public void wonPoint(String playerName) {
        if (server.equals(playerName)) {
            this.serverScore += 1;
        } else {
            this.receiverScore += 1;
        }
    }

    @java.lang.Override
    public String getScore() {
        TennisResult result = new Deuce(
                this, new GameServer(
                this, new GameReceiver(
                this, new AdvantageServer(
                this, new AdvantageReceiver(
                this, new DefaultResult(this)))))).getResult();
        return result.format();
    }

    boolean receiverHasAdvantage() {
        return receiverScore >= 4 && (receiverScore - serverScore) == 1;
    }

    boolean receiverHasWon() {
        return receiverScore >= 4 && (receiverScore - serverScore) >= 2;
    }

    boolean serverHasAdvantage() {
        return serverScore >= 4 && (serverScore - receiverScore) == 1;
    }

    boolean serverHasWon() {
        return serverScore >= 4 && (serverScore - receiverScore) >= 2;
    }

    boolean isDeuce() {
        return serverScore >= 3 && receiverScore >= 3 && (serverScore == receiverScore);
    }
}

class TennisResult {
    String serverScore;
    String receiverScore;

    TennisResult(String serverScore, String receiverScore) {
        this.serverScore   = serverScore;
        this.receiverScore = receiverScore;
    }

    String format() {
        if ("".equals(this.receiverScore)) {
            return this.serverScore;
        }
        if (serverScore.equals(receiverScore)) {
            return serverScore + "-All";
        }
        return this.serverScore + "-" + this.receiverScore;
    }
}

class Deuce implements ResultProvider {
    private final Game4          game;
    private final ResultProvider nextResult;

    public Deuce(Game4 game, ResultProvider nextResult) {
        this.game       = game;
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        if (game.isDeuce()) {
            return new TennisResult("Deuce", "");
        }
        return this.nextResult.getResult();
    }
}

class GameServer implements ResultProvider {
    private final Game4          game;
    private final ResultProvider nextResult;

    public GameServer(Game4 game, ResultProvider nextResult) {
        this.game       = game;
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        if (game.serverHasWon()) {
            return new TennisResult("Win for " + game.server, "");
        }
        return this.nextResult.getResult();
    }
}

class GameReceiver implements ResultProvider {
    private final Game4          game;
    private final ResultProvider nextResult;

    public GameReceiver(Game4 game, ResultProvider nextResult) {
        this.game       = game;
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        if (game.receiverHasWon()) {
            return new TennisResult("Win for " + game.receiver, "");
        }
        return this.nextResult.getResult();
    }
}

class AdvantageServer implements ResultProvider {
    private final Game4          game;
    private final ResultProvider nextResult;

    public AdvantageServer(Game4 game, ResultProvider nextResult) {
        this.game       = game;
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        if (game.serverHasAdvantage()) {
            return new TennisResult("Advantage " + game.server, "");
        }
        return this.nextResult.getResult();
    }
}

class AdvantageReceiver implements ResultProvider {

    private final Game4          game;
    private final ResultProvider nextResult;

    public AdvantageReceiver(Game4 game, ResultProvider nextResult) {
        this.game       = game;
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        if (game.receiverHasAdvantage()) {
            return new TennisResult("Advantage " + game.receiver, "");
        }
        return this.nextResult.getResult();
    }
}

class DefaultResult implements ResultProvider {

    private static final String[] scores = { "Love", "Fifteen", "Thirty", "Forty" };

    private final Game4 game;

    public DefaultResult(Game4 game) {
        this.game = game;
    }

    @Override
    public TennisResult getResult() {
        return new TennisResult(scores[game.serverScore], scores[game.receiverScore]);
    }
}
