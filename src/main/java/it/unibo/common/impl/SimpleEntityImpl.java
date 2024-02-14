package it.unibo.common.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.common.EntityType;
import it.unibo.common.SimpleEntity;

/**
 * Serializable implementation of a SimpleEntity.
 */
public final class SimpleEntityImpl implements SimpleEntity {

    private final EntityType type;
    private final double x;
    private final double y;

    /**
     * @param type the type of this entity
     * @param x the position of this entity on the x axis (inside the Level)
     * @param y the position of this entity on the y axis (inside the Level)
     */
    public SimpleEntityImpl(
        @JsonProperty("Type") final EntityType type,
        @JsonProperty("X") final double x,
        @JsonProperty("Y") final double y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    @Override
    public EntityType getType() {
        return this.type;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

}
