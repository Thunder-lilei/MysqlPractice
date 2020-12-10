package cn.edu.imufe.pojo;

import java.util.ArrayList;
import java.util.Collections;

public class Table {
    private Integer rows;
    private Integer columns;
    private Long allNum;
    private Long fRowNum;
    private ArrayList<Long> fRow;
    private ArrayList<Long> fColumn;
    private ArrayList<Long> eRow;

    public void setAll(Integer rows, Integer columns, Long allnum, Long frownum, ArrayList<Long> frow, ArrayList<Long> fcolumn, ArrayList<Long> erow) {
        this.rows = rows;
        this.columns = columns;
        this.allNum = allnum;
        this.fRowNum = frownum;
        this.fRow = frow;
        this.fColumn = fcolumn;
        this.eRow = erow;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public Long getAllNum() {
        return allNum;
    }

    public void setAllNum(Long allNum) {
        this.allNum = allNum;
    }

    public Long getfRowNum() {
        return fRowNum;
    }

    public void setfRowNum(Long fRowNum) {
        this.fRowNum = fRowNum;
    }

    public ArrayList<Long> getfRow() {
        return fRow;
    }

    public void setfRow(ArrayList<Long> fRow) {
        this.fRow = fRow;
    }

    public ArrayList<Long> getfColumn() {
        return fColumn;
    }

    public void setfColumn(ArrayList<Long> fColumn) {
        this.fColumn = fColumn;
    }

    public ArrayList<Long> geteRow() {
        return eRow;
    }

    public void seteRow(ArrayList<Long> eRow) {
        this.eRow = eRow;
    }

    @Override  //此关键字可以帮助我们检查是否重写合乎要求
    public boolean equals(Object obj) {
        if (this == obj)    //检测this与obj是否指向同一对象。这条语句是一个优化，避免直接去比较同一对象的各个域
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass()) // 比较this和obj是否属于同一个类 若是两个对象都不是同一个类的 则不相等
            return false;

        Table other = (Table) obj;  //将obj转换成相应的Student类型
        //对所有需要比较的域进行比较 基本类型使用== 对象域使用equal 数组类型的域，可以使用静态的Arrays.equals方法检测相应的数组元素是否相等
        //行数
        if (rows != other.rows)
            return false;
        if (rows == null) {
            if (other.rows != null)
                return false;
        } else if (!rows.equals(other.rows))
            return false;
        System.out.println("行数相同");
        //列数
        if (columns != other.columns)
            return false;
        if (columns == null) {
            if (other.columns != null)
                return false;
        } else if (!columns.equals(other.columns))
            return false;
        System.out.println("列数相同");
        //总和
        if (allNum.longValue() != other.allNum.longValue())
            return false;
        if (allNum == null) {
            if (other.allNum != null)
                return false;
        } else if (!allNum.equals(other.allNum))
            return false;
        System.out.println("总和相同");
        //首行和
        if (fRowNum.longValue() != other.fRowNum.longValue())
            return false;
        if (fRowNum == null) {
            if (other.fRowNum != null)
                return false;
        } else if (!fRowNum.equals(other.fRowNum))
            return false;
        System.out.println("首行和相同");
        //首行首列
        for (int i = 0; i < fRow.size(); i++)//行数
        {
            if (fRow.get(i).longValue() != other.fRow.get(i).longValue()) {
                return false;
            }
            if (fRow.get(i) == null) {
                if (other.fRow.get(i) != null)
                    return false;
            } else if (!fRow.get(i).equals(other.fRow.get(i)))
                return false;
        }
        for (int i = 0; i < fColumn.size(); i++)//列数
        {
            if (fColumn.get(i).longValue() != other.fColumn.get(i).longValue()) {
                return false;
            }
            if (fColumn.get(i) == null) {
                if (other.fColumn.get(i) != null)
                    return false;
            } else if (!fColumn.get(i).equals(other.fColumn.get(i)))
                return false;
        }
        System.out.println("首行首列相同");
        //每行和
        Collections.sort(eRow);
        Collections.sort(other.eRow);
        for (int i = 0; i < eRow.size(); i++) {
            if (eRow.get(i).longValue() != other.eRow.get(i).longValue()) {
                return false;
            }
            if (eRow.get(i) == null) {
                if (other.eRow.get(i) != null)
                    return false;
            } else if (!eRow.get(i).equals(other.eRow.get(i)))
                return false;
        }
        System.out.println("每行和相同");
        return true;
    }
}