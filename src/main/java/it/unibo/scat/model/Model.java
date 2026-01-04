package it.unibo.scat.model;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.model.entity.Invader;
import it.unibo.scat.model.entity.Player;
import it.unibo.scat.model.entity.Shot;
import it.unibo.scat.model.leaderboard.Leaderboard;

/**
 * The main class for the "Model" section of the MVC pattern.
 */
@SuppressFBWarnings(value = "UUF_UNUSED_FIELD", justification = "Fields will be used by upcoming game logic")
public class Model {

    private List<Entity> entities;
    private Player player;
    private List<Invader> invaders;
    private List<Shot> shots;
    private int score;
    private Leaderboard leaderboard;
    private int gameState;
    private int lastInvaderShootTime;
    private int invaderShootCooldown;
    private int level;
    private String username;

}
