package model;

import java.awt.image.BufferedImage;
import java.util.EventObject;

/**
 * Created by qwerty on 03-Apr-17.
 */
public class FoundEvent extends EventObject {
    private BufferedImage bi;

    public BufferedImage getBi() {
        return bi;
    }

    public FoundEvent(Object source, BufferedImage bi) {
        super(source);
        this.bi = bi;
    }
}
