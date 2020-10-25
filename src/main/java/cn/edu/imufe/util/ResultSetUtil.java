package cn.edu.imufe.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author soulocean
 */

public class ResultSetUtil {
    private final ResultSet resultSet;
    private final ArrayList<Long> rowSet = new ArrayList<>();
    private final ArrayList<Long> firstRowSet = new ArrayList<>();
    private final ArrayList<Long> firstColumnSet = new ArrayList<>();
    private final ArrayList<Integer> columnTypeSet = new ArrayList<>();
    private final ArrayList<String> columnNameSet = new ArrayList<>();
    private final ArrayList<ArrayList<Object>> TableSetByColumn = new ArrayList<>();
    private final ArrayList<ArrayList<Object>> TableSetByRow = new ArrayList<>();
    private long firstColumnSum = 0;
    private long firstRowSum = 0;
    private long allTableSum = 0;
    private Integer columnSize = 0;
    private Integer rowSum = 0;
    private boolean getTableSetFlag = false;
    private boolean getTableSetFlag2 = false;

    public ResultSetUtil(ResultSet resultSet) throws SQLException {
        this.resultSet = resultSet;
            ResultSetMetaData rsmd = this.resultSet.getMetaData();
            columnSize = rsmd.getColumnCount();
            ArrayList<Object> arrayListByRow = new ArrayList<>();
            for (int i = 1; i <= columnSize; i++) {
                columnTypeSet.add(rsmd.getColumnType(i));
                columnNameSet.add(rsmd.getColumnName(i));
                ArrayList<Object> arrayList = new ArrayList<>();
                arrayList.add(rsmd.getColumnName(i));
                arrayListByRow.add(rsmd.getColumnName(i));
                TableSetByColumn.add(arrayList);
            }
            TableSetByRow.add(arrayListByRow);
    }


    public Integer getRowSize()throws SQLException {
        getTableSetFlag = true;
        getTableSetFlag2 = true;
        rowSum = 0;
            while (resultSet.next()) {
                long temp = 0;
                long rowsum = 0;
                ArrayList<Object> arrayListByRow = new ArrayList<>();
                for (int i = 0; i < getColumnSize(); i++) {
                	if(resultSet.getObject(columnNameSet.get(i))!=null) {
                		TableSetByColumn.get(i).add(resultSet.getObject(columnNameSet.get(i)).toString());
                        arrayListByRow.add(resultSet.getObject(columnNameSet.get(i)).toString());
                	}
                    if (i == 0) {
                        firstColumnSum += getAscii(resultSet.getObject(columnNameSet.get(0)));
                        firstColumnSet.add(getAscii(resultSet.getObject(columnNameSet.get(0))));
                    }
                    if (rowSum == 0) {
                        firstRowSum += getAscii(resultSet.getObject(columnNameSet.get(i)));
                        firstRowSet.add(getAscii(resultSet.getObject(columnNameSet.get(i))));
                    }
                    temp = getAscii(resultSet.getObject(columnNameSet.get(i)));
                    allTableSum += temp;
                    rowsum += temp;
                }
                TableSetByRow.add(arrayListByRow);
                rowSet.add(rowsum);
                rowSum++;
            }
        return rowSum;
    }


    public ArrayList<ArrayList<Object>> getTableSetByColumn() throws SQLException{
        if (!getTableSetFlag) {
            getRowSize();
        }
        return TableSetByColumn;
    }

    public ArrayList<ArrayList<Object>> getTableSetByRow() throws SQLException{
        if (!getTableSetFlag2) {
            getRowSize();
        }
        return TableSetByRow;
    }


    public long getFirstColumnSum()throws SQLException {
        if (firstColumnSum == 0) {
            getRowSize();
        }
        return firstColumnSum;
    }

    public long getFirstRowSum() throws SQLException{
        if (firstRowSum == 0) {
            getRowSize();
        }
        return firstRowSum;
    }


    public long getAllTableSum()throws SQLException {
        if (allTableSum == 0) {
            getRowSize();
        }
        return allTableSum;
    }

    public int getColumnSize() {
        return columnSize;
    }


    public ArrayList<Long> getFirstRowSet() throws SQLException{
        if (firstRowSet.size() == 0) {
            getRowSize();
        }
        return firstRowSet;
    }

    public ArrayList<Long> getFirstColumnSet()throws SQLException {
        if (firstColumnSet.size() == 0) {
            getRowSize();
        }
        return firstColumnSet;
    }

    public ArrayList<Long> getRowSet() throws SQLException{
        if (rowSet.size() == 0) {
            getRowSize();
        }
        return rowSet;
    }

    public ArrayList<String> getTableColumnNameSet() {
        return columnNameSet;
    }

    public ArrayList<Integer> getColumnTypeSet() {
        return columnTypeSet;
        /*
        类型清单如下：
        -7 BIT
        -6 TINYINT
        -5 BIGINT
        -4 LONGVARBINARY
        -3 VARBINARY
        -2 BINARY
        -1 LONGVARCHAR
        0 NULL
        1 CHAR
        2 NUMERIC
        3 DECIMAL
        4 INTEGER
        5 SMALLINT
        6 FLOAT
        7 REAL
        8 DOUBLE
        12 VARCHAR
        91 DATE
        92 TIME
        93 TIMESTAMP
        1111 OTHER*/
    }


    private long getAscii(Object object) {
        long sum = 0;
        int digit = 1;
        if (object == null) {
            return 1;
        }
        char[] ch = object.toString().toCharArray();
        for (char c : ch) {
            sum += c * (Math.pow(10, digit - 1));
            digit++;
        }
        return sum;
    }

}