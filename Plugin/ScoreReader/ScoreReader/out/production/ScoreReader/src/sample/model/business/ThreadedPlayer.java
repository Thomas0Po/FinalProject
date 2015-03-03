package sample.model.business;

import org.jfugue.Pattern;
import org.jfugue.Player;


/**
 * Created by favre on 16/10/2014.
 */
public class ThreadedPlayer extends Thread{

    Player  player = new Player();
    Pattern pattern;
    @Override
    public void run() {

        player.play(pattern);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}
