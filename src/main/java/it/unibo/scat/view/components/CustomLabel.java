package it.unibo.scat.view.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import org.w3c.dom.events.MouseEvent;

/**
 * ...
 */
public class CustomLabel extends JLabel {

    /**
     * ...
     */
    public CustomLabel(final String text) {
        super(text);

        setFocusable(false);
        setBorder(new LineBorder(Color.BLACK, 4));
        setFont(new Font("Calibri", Font.BOLD, 25));

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent e) {
                Font font = getFont();
                Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                setFont(font.deriveFont(attributes));
            }

            public void mouseExited(final MouseEvent e) {
                Font font = getFont();
                Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
                attributes.put(TextAttribute.UNDERLINE, null);
                setFont(font.deriveFont(attributes));
            }
        });

    }
}
