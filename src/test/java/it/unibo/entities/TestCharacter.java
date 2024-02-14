package it.unibo.entities;
/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.entities.api.MapElement;
import it.unibo.model.entities.impl.CharacterImpl;
import it.unibo.model.entities.impl.EnemyImpl;
import it.unibo.model.entities.impl.MapElementImpl;
import it.unibo.model.entities.impl.SimpleEnemyImpl;
import it.unibo.model.entities.impl.TrapImpl;
import it.unibo.model.level.api.GameState;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.api.SpeedLevels;
import it.unibo.model.physics.impl.Position2D;
*/
//TODO: bisogna attendere prima le collisioni e le posizioni devono essere dei double
/**
 * Class for testing the behaviour of the character.
 */
/*
public class TestCharacter {

    private static final long TIMER = 3000;
    private Character player;
    private MapElement map1;
    private MapElement map2;
    private EnemyImpl enemy;
    private TrapImpl trap;
    private Level level = new Lv();

   
    @Test
    void testCharacterEnemy() {
        //Right collision.
        this.player = new CharacterImpl(new Position2D(0, 0), level); //y> scendi           //Inizio personaggio in altro a sinistra
        this.enemy = new SimpleEnemyImpl(new Position2D(1, 0), level);
        this.map1 = new MapElementImpl(new Position2D(0, 1), level);
        this.map2 = new MapElementImpl(new Position2D(1, 1), level);
        //this.player.move(Direction.RIGHT);
        this.level.addGameEntity(player);
        this.level.addGameEntity(enemy);
        this.level.addGameEntity(map1);
        this.level.addGameEntity(map2);
        this.player.updateState();
        assertFalse(this.player.isAlive());
        assertTrue(this.enemy.isAlive());

        //Down collision.
        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(1, 0), level);
        this.enemy = new SimpleEnemyImpl(new Position2D(1, 1), level);
        this.map1 = new MapElementImpl(new Position2D(1, 3), level);
        //this.player.move(Direction.DOWN);
        this.level.addGameEntity(player);
        this.level.addGameEntity(enemy);
        this.level.addGameEntity(map1);
        this.player.updateState();
        assertTrue(this.player.isAlive());
        assertFalse(this.enemy.isAlive());

        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(1, 0), level);
        this.enemy = new SimpleEnemyImpl(new Position2D(0, 0), level);
        this.map1 = new MapElementImpl(new Position2D(0, 1), level);
        this.map2 = new MapElementImpl(new Position2D(1, 1), level);
        //this.player.move(Direction.RIGHT);
        this.level.addGameEntity(player);
        this.level.addGameEntity(enemy);
        this.level.addGameEntity(map1);
        this.level.addGameEntity(map2);
        this.player.updateState();
        assertFalse(this.player.isAlive());
        assertTrue(this.enemy.isAlive());

        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(0, 1), level);
        this.enemy = new SimpleEnemyImpl(new Position2D(0, 0), level);
        this.map1 = new MapElementImpl(new Position2D(0, 2), level);
        //this.player.move(Direction.UP);
        this.level.addGameEntity(player);
        this.level.addGameEntity(enemy);
        this.level.addGameEntity(map1);
        this.player.updateState();
        assertFalse(this.player.isAlive());
        assertTrue(this.enemy.isAlive());
    }

    @Test
    void testCharacterTrap() throws InterruptedException {          //TODO: migliorare
        this.player = new CharacterImpl(new Position2D(1, 1), level);
        this.trap = new TrapImpl(new Position2D(2, 1), level);
        this.map1 = new MapElementImpl(new Position2D(1, 2), level);
        this.map2 = new MapElementImpl(new Position2D(2, 2), level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.trap);
        this.level.addGameEntity(map1);
        this.level.addGameEntity(map2);
        this.trap.updateState();
        this.player.updateState();
        assertFalse(this.trap.isLethal());
        Thread.sleep(TIMER);
        this.trap.updateState();
        this.player.updateState();
        assertFalse(this.player.isAlive());
        assertTrue(this.trap.isAlive());
        Thread.sleep(TIMER);
        this.trap.updateState();
        assertFalse(this.trap.isAlive());

        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(1, 1), level);
        this.trap = new TrapImpl(new Position2D(2, 1), level);
        this.map1 = new MapElementImpl(new Position2D(1, 2), level);
        this.map2 = new MapElementImpl(new Position2D(2, 2), level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.trap);
        this.level.addGameEntity(map1);
        this.level.addGameEntity(map2);
        this.trap.updateState();
        this.player.updateState();
        assertFalse(this.trap.isAlive());
        Thread.sleep(TIMER/2);
        this.trap.updateState();
        this.player.updateState();
        assertTrue(this.player.isAlive());
        assertTrue(this.trap.isAlive());
    }

    private static final class Lv implements Level {

        private Set<GameEntity> st = new HashSet<>();

        @Override
        public Set<GameEntity> getGameEntities() {
            return st; 
        }

        @Override
        public void addGameEntity(final GameEntity entity) {
            this.st.add(entity);
        }

        @Override
        public void computeChanges() {
            //Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'computeChanges'");
        }

        @Override
        public void moveCharacter(final Direction dir) {
            //Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'moveCharacter'");
        }

        @Override
        public void addFinishLocation(final Position position) {
            //Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addFinishLocation'");
        }

        @Override
        public GameState getGameState() {
            //Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getGameState'");
        }

        @Override
        public GameEntity getCharacter() {
            //Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getCharacter'");
        }
    }
}
*/