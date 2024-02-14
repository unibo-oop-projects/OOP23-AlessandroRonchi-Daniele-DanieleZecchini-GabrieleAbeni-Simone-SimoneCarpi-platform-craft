package it.unibo.view.impl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.print.DocFlavor.READER;
import javax.swing.JPanel;

import it.unibo.common.EntityType;
import it.unibo.common.SimpleEntity;
import it.unibo.common.impl.SimpleEntityImpl;
import it.unibo.controller.api.Controller;

/**
 * This Panel acts as the container of Canvas, modifying its size
 * so that the aspect ratio of the level remains the same.
 */
public class PaintPanel extends JPanel {

    //private final Controller controller;
    private final double levelWidth;
    private final double levelHeight;
    private final Canvas canvas;

    //private Optional<EntityType> selected;
    //private boolean remove;

    /**
     * The constructor of this PaintPanel.
     * @param controller the controller of the application
     * @param levelWidth the width of the level
     * @param levelHeight the heigth of the level
     * @param mouseEnabled true if the inner Canvas must accept mouse clicks
     */
    public PaintPanel(
        final Controller controller,
        final double levelWidth,
        final double levelHeight
        /*final boolean mouseEnabled*/) {
        //this.controller = controller;
        this.levelHeight = levelHeight;
        this.levelWidth = levelWidth;
        this.canvas = new Canvas(levelWidth, levelHeight);
        //this.selected = Optional.empty();
        //this.remove = false;
        //TODO: Setting hgap and vgap to 0 to see if the canvas is shown correctly.
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        //Component listener that modifies the size of the canvas if this panel is resized.
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeCanvas();
            }
        });
        //TODO: decide a way to tell the View that it needs to display an error in case the entity can't be added/removed
        /*  TODO: remove if not needed
        if (mouseEnabled) {
            this.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    boolean success = false;
                    if (remove) {
                        success = controller.getEditor()
                        .removeEntity(convertToModelX(e.getX()), convertToModelY(e.getY()));
                    } else if (selected.isPresent()) {
                        success = controller.getEditor().addEntity(
                            new SimpleEntityImpl(
                                selected.get(),
                                convertToModelX(e.getX()),
                                convertToModelY(e.getY())));
                    }
                    if(success) {
                        canvas.setDisplayed(
                            controller.getEditor().getCurrentEntities()
                        );
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) { }

                @Override
                public void mouseReleased(MouseEvent e) { }

                @Override
                public void mouseEntered(MouseEvent e) { }

                @Override
                public void mouseExited(MouseEvent e) { }

            });
        }
        */
    }

    /*
     * Converts the specified dimension in the Panel to the dimension in the model.
     * The converted dimension must be on the X axis.
     */
    private double convertToModelX(int dimensionX) {
        return this.levelWidth * dimensionX / this.canvas.getWidth();
    }

    /*
     * Converts the specified dimension in the Panel to the dimension in the model.
     * The converted dimension must be on the Y axis.
     */
    private double convertToModelY(int dimensionY) {
        return this.levelHeight * dimensionY / this.canvas.getHeight();
    }

    private void resizeCanvas() {
        int cwidth = this.getWidth();
        //System.out.println("Container initial width: "+ cwidth);
        int cheight = this.getHeight();
        int pwidth = 0;
        int pheight = 0;
        int correctWidth = (int) (cheight * levelWidth / levelHeight);
        if(cwidth <= correctWidth) {
            pwidth = cwidth;
            pheight =(int) (pwidth * levelHeight / levelWidth);
        }
        else {
            pheight = cheight;
            pwidth = (int) (levelWidth * pheight / levelHeight);
        }
        //System.out.println("Container dimension: "+ this.getSize());
        //System.out.println("Panel dimension: " + canvas.getSize());

        this.canvas.setPreferredSize(new Dimension(pwidth, pheight));
    }

    /**
     * Sets the selected type of entity to add to the level at the next mouse click.
     * Also, after calling this method, this Panel will no longer try to remove an entity until
     * the next call of setRemove()
     * @param type the selected type of entity
     * @throws NullPointerException if type is null
     */
    /*
    public void setSelectedEntity(final EntityType type) {
        this.selected = Optional.of(Objects.requireNonNull(type));
        this.remove = false;
    }*/

    /**
     * Sets this Panel so that the next mouse clicks will try to remove an entity from the level.
     */
    /*
    public void setRemove() {
        this.remove = true;
    }*/

    /**
     * Shows the specified entity on the inner canvas.
     * @param displayed the entities to be shown
     * @throws NullPointerException if displayed is null
    */
    public void render(final Set<SimpleEntity> displayed) {
        this.canvas.setDisplayed(Objects.requireNonNull(displayed));
    }

    /**
     * Tries to add an entity of the specified type and position (relative to the GUI) to the level.
     * If the entity can be added, shows it on screen.
     * @param type the type of entity that must be added
     * @param x the position of the entity on the X axis
     * @param y the position of the entity on the Y axis
     * @return true if the entity can be added to the level
     */
    public boolean addEntity(final EntityType type, final int x, final int y) {
        return false;
    }

    /**
     * Tries to remove an entity from the specified position (relative to the GUI).
     * @param x the position of the entity on the X axis
     * @param y the position of the entity on the Y axis
     * @return true if there is an entity to remove from the level
     */
    public boolean removeEntity(final int x, final int y) {
        return false;
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        this.canvas.addMouseListener(l);
    }

}
