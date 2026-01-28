package it.unibo.scat.view.components;

import java.awt.Dimension;
import java.awt.FontMetrics;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import it.unibo.scat.common.UIConstants;

/**
 * ...
 */
public final class CustomTextField extends JTextField {
    private static final long serialVersionUID = 1L;

    /**
     * ...
     */
    public CustomTextField() {
        setFont(UIConstants.MEDIUM_FONT);

        final FontMetrics fm = getFontMetrics(UIConstants.MEDIUM_FONT);
        final int maxWidth = fm.charWidth('W') * 20 + getInsets().left
                + getInsets().right;
        final int maxHeight = fm.getHeight() * 2 + getInsets().top
                + getInsets().bottom;

        final Dimension d = new Dimension(maxWidth, maxHeight);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);

        setDocument(new PlainDocument() {
            @Override
            public void insertString(final int offs, final String str, final AttributeSet a)
                    throws BadLocationException {
                final int maxLength = 15;

                if (getLength() + str.length() <= maxLength) {
                    super.insertString(offs, str, a);
                }
            }
        });

    }
}
