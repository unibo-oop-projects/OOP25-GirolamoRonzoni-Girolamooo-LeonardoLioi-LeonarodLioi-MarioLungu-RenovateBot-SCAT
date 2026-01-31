package it.unibo.scat.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.view.api.MenuActionsInterface;
import it.unibo.scat.view.menu.api.MenuPanelInterface;
import it.unibo.scat.common.GameRecord;

/**
 * This class handles the leaderboard panel.
 */
@SuppressFBWarnings("SE_TRANSIENT_FIELD_NOT_RESTORED")

public final class LeaderboardPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final transient MenuPanelInterface menuInterface;
    private final transient MenuActionsInterface menuActionsInterface;
    private List<GameRecord> records;
    private static final Color ARCADE_BLACK = Color.BLACK;
    private static final Color ARCADE_GREEN = new Color(51, 255, 51);
    private static final Color ARCADE_CYAN = Color.CYAN;
    private static final Font MONO_FONT = new Font("Monospaced", Font.BOLD, 16);

    /**
     * @param mInterface ...
     * 
     */
    public LeaderboardPanel(final MenuPanelInterface mInterface, final MenuActionsInterface mActionInterface) {
        this.menuInterface = mInterface;
        this.menuActionsInterface = mActionInterface;
        records = menuActionsInterface.fetchLeaderboard();
        // final Color background = new Color(0, 0, 0, 150);
        // setBackground(background);

        final JLabel label = new JLabel("GLOBAL RANKING");
        add(label);
        setLayout();
        initContentTable();

        final JButton backButton = new JButton("BACK");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                menuInterface.showSettingsPanel();
            }

        });
        add(backButton, BorderLayout.SOUTH);
    }

    public void setLayout() {
        this.setLayout(new BorderLayout());
        final JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        this.add(centerPanel, BorderLayout.CENTER);
        centerPanel.add(Box.createVerticalGlue());

        centerPanel.add(new JLabel("LEADERBOARD"));
    }

    public void initContentTable() {
        String[] columnNames = { "RANK", "NAME", "SCORE", "LEVEL", "DATE" };

        Object[][] data = new Object[records.size()][5];
        for (GameRecord record : records) {
            int index = records.indexOf(record);
            data[index][0] = index + 1;
            data[index][1] = record.getName();
            data[index][2] = record.getScore();
            data[index][3] = record.getLevel();
            data[index][4] = record.getDate().toString();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        table.setBackground(ARCADE_BLACK);
        table.setForeground(Color.WHITE);
        table.setFont(MONO_FONT);
        table.setGridColor(ARCADE_BLACK);
        table.setRowHeight(30);
        table.setEnabled(false);

        JTableHeader header = table.getTableHeader();
        header.setBackground(ARCADE_BLACK);
        header.setForeground(ARCADE_GREEN);
        header.setFont(MONO_FONT);
        header.setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(ARCADE_BLACK);
        scrollPane.setBorder(null);

        this.add(scrollPane, BorderLayout.CENTER);
    }

}
