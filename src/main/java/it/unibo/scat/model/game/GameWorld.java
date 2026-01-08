package it.unibo.scat.model.game;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.model.game.entity.Invader;
import it.unibo.scat.model.game.entity.Player;
import it.unibo.scat.model.game.entity.Shot;

/**
 * ...
 */
@SuppressFBWarnings(value = "UUF_UNUSED_FIELD", justification = "Fields will be used by upcoming game logic")
public class GameWorld {
    private List<Entity> entities;
    private Player player;
    private List<Invader> invaders;
    private List<Shot> shots;
}
