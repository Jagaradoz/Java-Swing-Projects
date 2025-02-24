package application;

import config.LayoutConfig;
import config.StyleConfig;
import util.StyleUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RecordComponent extends JPanel implements Serializable {
    private final String id;
    private boolean selected = false;

    private TransactionTrackerPanel transactionTrackerPanel;

    private final int income;
    private final int expense;

    private final JLabel dateLabel;
    private final JLabel detailLabel;
    private final JLabel incomeLabel;
    private final JLabel expenseLabel;

    public RecordComponent(TransactionTrackerPanel transactionTrackerPanel, String id, String detail, int income, int expense) {
        // SET DEFAULT VALUES
        this.transactionTrackerPanel = transactionTrackerPanel;
        this.id = id;
        this.income = income;
        this.expense = expense;

        // GENERATE DATE TIME
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String dateStr = date.format(formatter);

        // SET RECORD LAYOUT
        setLayout(new GridLayout(1, 4));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, StyleConfig.LINE_COLOR));
        setPreferredSize(new Dimension(LayoutConfig.SCREEN_WIDTH, 30));
        setMaximumSize(new Dimension(LayoutConfig.SCREEN_WIDTH, 30));

        // GENERATE LABELS
        dateLabel = generateLabel(dateStr);
        detailLabel = generateLabel(detail);
        incomeLabel = generateLabel(income == 0 ? "-" : String.valueOf(income));
        expenseLabel = generateLabel(expense == 0 ? "-" : "-" + expense);

        // SET TEXT COLORS
        incomeLabel.setForeground(Color.GREEN);
        expenseLabel.setForeground(Color.RED);

        add(dateLabel);
        add(detailLabel);
        add(incomeLabel);
        add(expenseLabel);

        // ADD RECORD SELECTION LISTENER
        addMouseListener(new SelectRecordListener());
    }

    public void updateBackgroundColors() {
        Color bgColor = selected ? StyleConfig.SELECTED_RECORD_BG_COLOR : StyleConfig.RECORD_BG_COLOR;
        dateLabel.setBackground(bgColor);
        detailLabel.setBackground(bgColor);
        incomeLabel.setBackground(bgColor);
        expenseLabel.setBackground(bgColor);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        updateBackgroundColors();
    }

    public void setTransactionTrackerPanel(TransactionTrackerPanel transactionTrackerPanel) {
        // SET TRANSACTION TRACKER PANEL VALUE
        this.transactionTrackerPanel = transactionTrackerPanel;
    }

    public int getIncome() {
        return income;
    }

    public int getExpense() {
        return expense;
    }

    public boolean isSelected() {
        return selected;
    }

    public String getId() {
        return id;
    }

    public JLabel getDetailLabel() {
        return detailLabel;
    }

    public JLabel generateLabel(String text) {
        JLabel label = new JLabel(text);
        label.setBackground(StyleConfig.RECORD_BG_COLOR);
        label.setOpaque(true);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }

    public class SelectRecordListener extends MouseAdapter implements Serializable {
        @Override
        public void mouseClicked(MouseEvent e) {
            // SET THIS RECORD TO BE SELECTED
            transactionTrackerPanel.setSelectedRecord(RecordComponent.this);
        }
    }
}
