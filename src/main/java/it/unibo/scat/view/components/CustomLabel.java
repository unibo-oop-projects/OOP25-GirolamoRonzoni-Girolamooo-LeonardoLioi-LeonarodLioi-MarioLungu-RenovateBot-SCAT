package it.unibo.scat.view.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

/**
 * ...
 */
public final class CustomLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    private final String baseText;
    private final String hoverText;

    /**
     * @param text ...
     */
    public CustomLabel(final String text) {
        super(text);

        baseText = text;
        // hoverText = baseText + " ‹";
        // hoverText = "› " + baseText;
        hoverText = "› " + baseText + " ‹";
        // hoverText = "» " + baseText + " «";
        // hoverText = "✦︎" + baseText + "✦︎";
        // hoverText = baseText;

        final Font baseFont = new Font("Calibri", Font.BOLD, 80);
        final Font hoverFont = new Font("Calibri", Font.BOLD, 85);
        /* .deriveFont(Map.of(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)); */

        final FontMetrics fm = getFontMetrics(hoverFont);
        final int w = fm.stringWidth(hoverText);
        final int h = fm.getHeight();

        final Dimension fixed = new Dimension(w, h);
        setPreferredSize(fixed);
        setMaximumSize(fixed);
        setMinimumSize(fixed);

        setFocusable(false);
        setFont(baseFont);
        setForeground(Color.WHITE);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                setForeground(Color.RED);
                setFont(hoverFont);
                setText(hoverText);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                setForeground(Color.WHITE);
                setFont(baseFont);
                setText(baseText);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }
        });

    }
}
