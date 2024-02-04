package it.unibo.model.entities.impl;

import org.locationtech.jts.geom.CoordinateXY;
import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.Enemy;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;

/**
 * Implementation of the Interface Character, 
 * where it cointains all the necessary to create the character.
 */
public class CharacterImpl implements Character {

    private final Physics physic;
    private CollisionBox box;     //TODO: final
    private PhysicsBuilder physicsBuilder;
    private Direction dir;
    private CoordinateXY position;
    private PlayerCondition condition;
    private boolean collisionState;

    /**
     * Used to list the different states of the character.
     */
    enum PlayerCondition {
        /**
         * It+s alive.
         */
        ALIVE,
        /**
         * It's dead.
         */
        DEAD;
    }

    /**
     * It is the constructor of the class to initialize the character itself.
     * @param position the initial coordinate of the character
     */
    public CharacterImpl(final CoordinateXY position) {
        this.position = position;
        this.collisionState = false;
        this.condition = PlayerCondition.ALIVE;
        this.physic = this.physicsBuilder
                .setGameObject(this)
                .addAccelerationOnX()
                .addFallingPhysics()
                .create();
    }

    @Override
    public void setPosition(final CoordinateXY position) {
        this.position = position;
    }

    @Override
    public CoordinateXY getPosition() {
        return this.position;
    }

    @Override
    public boolean isAlive() {
        return condition.equals(PlayerCondition.ALIVE);    
    }

    @Override
    public void updateState() {
        physic.calculateMovement();
    }

    @Override
    public EntityType getType() {
        return EntityType.CHARACTER;
    }

    @Override
    public void move(final Direction dir) {
        if (checkMove(dir) && this.box.getCollisions().isEmpty()) {
            physic.setMovement(dir);
        } else {
            this.collisionState = true;
            for (var collision : this.box.getCollisions()) {
                if (collision.getGameObject() instanceof Enemy) {
                    this.condition = PlayerCondition.DEAD;
                }
            }
        }
    }

    private boolean checkMove(final Direction dir) {
        for (var movement : Direction.values()) {
            if (movement.equals(dir)) {
                this.dir = dir;
                return true;
            }
        }
        return false;
    }
}
