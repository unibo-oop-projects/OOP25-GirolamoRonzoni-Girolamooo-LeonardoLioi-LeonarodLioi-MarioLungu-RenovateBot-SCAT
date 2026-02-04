package it.unibo.scat.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.GameRecord;
import it.unibo.scat.view.UIConstants;
import it.unibo.scat.view.api.MenuActionsInterface;
import it.unibo.scat.view.menu.api.MenuPanelInterface;

/**
 * This class handles the leaderboard panel.
 */
@SuppressFBWarnings({ "SE_TRANSIENT_FIELD_NOT_RESTORED", "EI_EXPOSE_REP2" })
public final class LeaderboardPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int COLUMN_COUNT = 5;
    private static final int TABLE_ROW_HEIGHT = 25;
    private final transient MenuPanelInterface menuInterface;
    private final transient MenuActionsInterface menuActionsInterface;
    private final transient List<GameRecord> records;

    /**
     * @param mInterface       ...
     * @param mActionInterface ...
     * 
     */
    public LeaderboardPanel(final MenuPanelInterface mInterface, final MenuActionsInterface mActionInterface) {
        this.menuInterface = mInterface;
        this.menuActionsInterface = mActionInterface;
        records = menuActionsInterface.fetchLeaderboard();

        setLayout(new BorderLayout());
        this.setBackground(UIConstants.ARCADE_BLACK);
        final JLabel titleLabel = new JLabel("GLOBAL RANKING", JLabel.CENTER);
        titleLabel.setFont(UIConstants.SMALL_FONT);
        titleLabel.setForeground(UIConstants.ARCADE_GREEN);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        add(titleLabel, BorderLayout.NORTH);
        initContentTable();
        initBackButton();
    }

    /**
     * ...
     */
    private void initBackButton() {
        final JButton backButton = new JButton("BACK");
        backButton.setFont(UIConstants.SMALL_FONT);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(UIConstants.ARCADE_BLACK);
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                menuInterface.showSettingsPanel();
            }

        });
        add(backButton, BorderLayout.SOUTH);
    }

    /**
     * ...
     */
    private void initContentTable() {
        // @formatter:off
        final String[] columnNames = {"RANK", "NAME", "SCORE", "LEVEL", "DATE"};
        // @formatter:on
        final Object[][] data = new Object[records.size()][COLUMN_COUNT];

        for (final GameRecord record : records) {

            final int index = records.indexOf(record);
            data[index][0] = index + 1;
            data[index][1] = record.getName();
            data[index][2] = record.getScore();
            data[index][3] = record.getLevel();
            data[index][4] = record.getDate().toString();
        }

        final DefaultTableModel model = new DefaultTableModel(data, columnNames);
        final JTable table = new JTable(model);

        final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        table.setBackground(UIConstants.ARCADE_BLACK);
        table.setForeground(Color.WHITE);
        table.setFont(UIConstants.SMALL_FONT);
        table.setGridColor(UIConstants.ARCADE_BLACK);
        table.setRowHeight(TABLE_ROW_HEIGHT);
        table.setEnabled(false);

        final JTableHeader header = table.getTableHeader();
        header.setBackground(UIConstants.ARCADE_BLACK);
        header.setForeground(UIConstants.ARCADE_GREEN);
        header.setFont(UIConstants.SMALL_FONT);
        header.setReorderingAllowed(false);

        final JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(UIConstants.ARCADE_BLACK);
        scrollPane.setBorder(null);

        this.add(scrollPane, BorderLayout.CENTER);
    }

}
