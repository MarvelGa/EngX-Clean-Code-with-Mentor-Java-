package com.epam.engx.cleancode.finaltask.task1;

import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.Command;
import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.DataSet;
import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.View;
import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.DatabaseManager;

import java.util.List;

public class TablePrinter implements Command {

    private static final Integer VALID_INPUTTED_COMMAND_AMOUNT = 2;
    private static final String GAP_SIGN = " ";
    private static final String NEW_LINE = "\n";
    private static final String DOUBLE_LINE = "═";
    private static final String VERTICAL_LINE = "║";
    private static final String TABLE_START = (" Table '");
    private static final String TABLE_TEXT = ("' is empty or does not exist ");

    private final View view;
    private final DatabaseManager databaseManager;
    private String tableName;

    public TablePrinter(View view, DatabaseManager manager) {
        this.view = view;
        this.databaseManager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("print ");
    }

    @Override
    public void process(String inputtedCommand) {
        String[] commands = inputtedCommand.split(GAP_SIGN);
        validateCommandLength(commands);
        tableName = commands[1];
        List<DataSet> data = databaseManager.getTableData(tableName);
        view.write(getTableString(data));
    }

    private void validateCommandLength(String[] commands) {
        if (commands.length != VALID_INPUTTED_COMMAND_AMOUNT) {
            throw new IllegalArgumentException("incorrect number of parameters. Expected 1, but is " + (commands.length - 1));
        }
    }

    private String getTableString(List<DataSet> data) {
        int maxColumnSize = getCompensatedMaxColumnSize(data);
        return maxColumnSize == 0 ? getEmptyTable(tableName) : getHeaderOfTheTable(data) + getStringTableData(data);
    }

    private String getEmptyTable(String tableName) {
        String textEmptyTable = VERTICAL_LINE + TABLE_START + tableName + TABLE_TEXT + VERTICAL_LINE;
        int length = textEmptyTable.length() - 2;
        return Border.TOP.left + generateTable(length, DOUBLE_LINE)
                + Border.TOP.right + NEW_LINE + textEmptyTable + NEW_LINE + Border.BOTTOM.left
                + generateTable(length, DOUBLE_LINE) + Border.BOTTOM.right + NEW_LINE;
    }

    private String generateTable(int length, String symbol) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(symbol);
        }
        return result.toString();
    }

    private int getCompensatedMaxColumnSize(List<DataSet> dataSets) {
        int maxLength = 0;
        if (dataSets.size() > 0) {
            for (DataSet dataSet : dataSets) {
                maxLength = Math.max(maxLength, getMaxLength(dataSet));
            }
        }
        return maxLength;
    }

    private int getMaxLength(DataSet dataSet) {
        int maxLength = 0;
        List<Object> values = dataSet.getValues();
        for (Object value : values) {
            maxLength = Math.max(maxLength, String.valueOf(value).length());
        }
        return maxLength;
    }

    private String getStringTableData(List<DataSet> dataSets) {
        int maxColumnSize = getCompensatedMaxColumnSize(dataSets);
        maxColumnSize = getCompensatedMaxColumnSize(maxColumnSize);
        int columnCount = getColumnCount(dataSets);
        return generateString(dataSets, maxColumnSize, columnCount) + buildOnRequest(maxColumnSize, columnCount, Border.BOTTOM);
    }

    private String generateString(List<DataSet> dataSets, int maxColumnSize, int columnCount) {
        StringBuilder result = new StringBuilder("");
        int rowsCount = dataSets.size();
        for (int row = 0; row < rowsCount; row++) {
            List<Object> values = dataSets.get(row).getValues();
            result.append(VERTICAL_LINE);
            result.append(generateColumn(maxColumnSize, columnCount, values));
            result.append(NEW_LINE);
            if (row < rowsCount - 1) {
                result.append(buildOnRequest(maxColumnSize, columnCount, Border.MIDDLE));
            }
        }
        return result.toString();
    }

    private String generateColumn(int maxColumnSize, int columnCount, List<Object> values) {
        StringBuilder result = new StringBuilder();
        for (int column = 0; column < columnCount; column++) {
            int valuesLength = String.valueOf(values.get(column)).length();
            int actualLengthOfColumn = (maxColumnSize - valuesLength) / 2;
            result.append(generateTable(actualLengthOfColumn, GAP_SIGN));
            result.append(String.valueOf(values.get(column)));
            result.append(generateStringWithPadding(valuesLength, actualLengthOfColumn));
            result.append(VERTICAL_LINE);
        }
        return result.toString();
    }

    private String generateStringWithPadding(int valuesLength, int actualLengthOfColumn) {
        StringBuilder result = new StringBuilder();
        if (valuesLength % 2 == 0) {
            result.append(generateTable(actualLengthOfColumn, GAP_SIGN));
        } else {
            for (int j = 0; j <= actualLengthOfColumn; j++) {
                result.append(GAP_SIGN);
            }
        }
        return result.toString();
    }

    private int getCompensatedMaxColumnSize(int maxColumnSize) {
        return maxColumnSize + ((maxColumnSize % 2 == 0) ? 2 : 3);
    }

    private int getColumnCount(List<DataSet> dataSets) {
        return dataSets.size() > 0 ? dataSets.get(0).getColumnNames().size() : 0;
    }

    private String getHeaderOfTheTable(List<DataSet> dataSets) {
        int maxColumnSize = getCompensatedMaxColumnSize(dataSets);
        int columnCount = getColumnCount(dataSets);
        maxColumnSize = getCompensatedMaxColumnSize(maxColumnSize);
        List<String> columnNames = dataSets.get(0).getColumnNames();
        return Border.TOP.left + getColumn(maxColumnSize, columnCount, Border.TOP.middle)
                + generateTable(maxColumnSize, DOUBLE_LINE) + Border.TOP.right + NEW_LINE
                + getStringWithPadding(maxColumnSize, columnCount, columnNames) + VERTICAL_LINE + NEW_LINE
                + getMiddleOrBottomOfTable(dataSets, maxColumnSize, columnCount);
    }

    private String getMiddleOrBottomOfTable(List<DataSet> dataSets, int maxColumnSize, int columnCount) {
        return dataSets.size() > 0 ? buildOnRequest(maxColumnSize, columnCount, Border.MIDDLE) : buildOnRequest(maxColumnSize, columnCount, Border.BOTTOM);
    }

    private String getStringWithPadding(int maxColumnSize, int columnCount, List<String> columnNames) {
        StringBuilder result = new StringBuilder();
        for (int column = 0; column < columnCount; column++) {
            result.append(generateWithPadding(maxColumnSize, columnNames, column));
        }
        return result.toString();
    }

    private String getColumn(int maxColumnSize, int columnCount, String symbol) {
        StringBuilder result = new StringBuilder();
        for (int j = 1; j < columnCount; j++) {
            result.append(generateTable(maxColumnSize, DOUBLE_LINE));
            result.append(symbol);
        }
        return result.toString();
    }

    private String generateWithPadding(int maxColumnSize, List<String> columnNames, int column) {
        int columnNamesLength = columnNames.get(column).length();
        return VERTICAL_LINE
                + generateTable((maxColumnSize - columnNamesLength) / 2, GAP_SIGN)
                + columnNames.get(column)
                + generateWithTheLongestValue(maxColumnSize, columnNamesLength);
    }

    private String buildOnRequest(int maxColumnSize, int columnCount, Border border) {
        return border.left + getColumn(maxColumnSize, columnCount, border.middle)
                + generateTable(maxColumnSize, DOUBLE_LINE) + border.right + NEW_LINE;
    }

    private String generateWithTheLongestValue(int maxColumnSize, int columnNamesLength) {
        return generateStringWithPadding(columnNamesLength, (maxColumnSize - columnNamesLength) / 2);
    }
}