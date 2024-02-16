package it.unibo.controller.impl;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.Command;
import it.unibo.controller.api.LevelRunner;
import it.unibo.model.engine.api.Engine;
//import it.unibo.model.engine.impl.EditorImpl; TODO: uncomment once EditorImpl has been added
import it.unibo.view.api.View;

/**
 * Implementation of a LevelRunner.
 */
public final class LevelRunnerImpl implements LevelRunner {
    //private Engine engine;
    private final View view;
    private Optional<RunnerAgent> runner;
    private Engine engine;
    //True if a level has been correctly loaded, false otherwise.
    private boolean hasLoaded;

    /**
     * The constructor of this LevelRunner.
     * @param view the application view
     */
    public LevelRunnerImpl(final View view) {
        this.view = view;
        this.hasLoaded = false;
        this.runner = Optional.empty();
    }

    @Override
    public void run() {
        if (this.hasLoaded) {
            this.runner = Optional.of(new RunnerAgent(this.engine, this.view));
            this.runner.get().start();
        } else {
            throw new IllegalStateException("No level has been correctly loaded in this LevelRunner");
        }
    }

    @Override
    public void notifyCommand(final Command command) {
        if (this.runner.isPresent()) {
            this.runner.get().notifyCommand(Objects.requireNonNull(command));
        } else {
            throw new IllegalStateException("Cannot invoke notifyCommand: no level has been loaded yet");
        }
    }

    @Override
    public boolean loadLevel(final File file) {
        try {
            Set<SimpleEntity> levelEntities = new SerializerImpl().loadLevel(file);
            Optional<Engine> created = Optional.empty(); // = new EditorImpl(levelEntities).createLevel();
            //TODO:uncomment once EditorImpl has been added
            if (created.isPresent()) {
                this.engine = created.get();
                this.hasLoaded = true;
                return true;
            }
            return false;
        } catch (final IOException e) {
            return false;
        }
    }

}
