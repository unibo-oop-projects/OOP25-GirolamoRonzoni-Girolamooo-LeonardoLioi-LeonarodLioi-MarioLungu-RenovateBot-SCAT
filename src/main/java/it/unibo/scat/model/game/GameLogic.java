package it.unibo.scat.model.game;

import java.util.List;
import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.Direction;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.common.GameResult;
import it.unibo.scat.model.game.entity.AbstractEntity;
import it.unibo.scat.model.game.entity.Invader;
import it.unibo.scat.model.game.entity.Shot;

/**
 * ...
 */
@SuppressFBWarnings({ "EI2", "DMI_RANDOM_USED_ONLY_ONCE" })
public class GameLogic {
    private final GameWorld gameWorld;

    /**
     * @param gWorld ...
     * 
     */
    public GameLogic(final GameWorld gWorld) {
        this.gameWorld = gWorld;
    }

    /**
     * @return ...
     *
     */
    public CollisionReport checkCollisions() {
        return null;
    }

    /**
     * @param cr ...
     *
     */
    public void handleCollisionReport(final CollisionReport cr) {

    }

    /**
     * ...
     */
    public void addPlayerShot() {

    }

    /**
     * ...
     */
    public void resetEntities() {

    }

    /**
     * @return ...
     *
     */
    public GameResult checkGameEnd() {
        return null;
    }

    /**
     * ...
     */
    public void deleteShots() {

    }

    /**
     * ...
     */
    public void moveEntities() {
        for (final Invader invader : gameWorld.getInvaders()) {
            invader.move();
        }

        for (final Shot shot : gameWorld.getShots()) {
            shot.move();
        }
    }

    /**
     * ...
     */
    public void generateInvaderShot() {
        final Invader invader = getRandomInvader();

        final Shot newShot = new Shot(EntityType.SHOT, invader.getPosition().getX(), invader.getPosition().getY() + 2,
                1, 2, 1, Direction.DOWN);

        gameWorld.addEntity(newShot);
    }

    /**
     * @return ...
     * 
     */
    private Invader getRandomInvader() {
        final List<Invader> aliveInvaders = gameWorld.getInvaders().stream()
                .filter(Invader::isAlive)
                .toList();

        if (aliveInvaders.isEmpty()) {
            return null;
        }

        return aliveInvaders.get(new Random().nextInt(aliveInvaders.size()));
    }

    /**
     * ...
     */
    public void updateLastInvadersShotTime() {
        Invader.setLastShotTime(System.currentTimeMillis());
    }

    /**
     * @param e ...
     * 
     */
    public void addEntity(final AbstractEntity e) {

    }

    /**
     * @param e ...
     * 
     */
    public void removeEntity(final AbstractEntity e) {

    }
}
