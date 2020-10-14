package cn.edu.imufe.pojo;

import java.util.ArrayList;
import java.util.Collections;

public class Table {
	private Integer rows;
	private Integer columns;
	private Long allnum;
	private Long frownum;
	private ArrayList<Long> frow;
	private ArrayList<Long> fcolumn;
	private ArrayList<Long> erow;

	public void setAll(Integer rows,Integer columns,Long allnum,Long frownum,ArrayList<Long> frow,ArrayList<Long> fcolumn,ArrayList<Long> erow) 
	{
		this.rows = rows;
		this.columns = columns;
		this.allnum = allnum;
		this.frownum = frownum;
		this.frow = frow;
		this.fcolumn = fcolumn;
		this.erow = erow;
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

	public Long getAllnum() {
		return allnum;
	}

	public void setAllnum(Long allnum) {
		this.allnum = allnum;
	}

	public Long getFrownum() {
		return frownum;
	}

	public void setFrownum(Long frownum) {
		this.frownum = frownum;
	}

	public ArrayList<Long> getFrow() {
		return frow;
	}

	public void setFrow(ArrayList<Long> frow) {
		this.frow = frow;
	}

	public ArrayList<Long> getFcolumn() {
		return fcolumn;
	}

	public void setFcolumn(ArrayList<Long> fcolumn) {
		this.fcolumn = fcolumn;
	}

	public ArrayList<Long> getErow() {
		return erow;
	}

	public void setErow(ArrayList<Long> erow) {
		this.erow = erow;
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
	    if (allnum.longValue() != other.allnum.longValue())
	        return false;
	    if (allnum == null) {
	        if (other.allnum != null)
	            return false;
	    } else if (!allnum.equals(other.allnum))
	        return false;
	    System.out.println("总和相同");
	    //首行和
	    if (frownum.longValue() != other.frownum.longValue())
	        return false;
	    if (frownum == null) {
	        if (other.frownum != null)
	            return false;
	    } else if (!frownum.equals(other.frownum))
	        return false;
	    System.out.println("首行和相同");
	    //首行首列
	    for(int i=0;i<frow.size();i++)//行数 
	  		{
			if(frow.get(i).longValue() != other.frow.get(i).longValue()) 
			{
				return false;
			}
			if (frow.get(i) == null) {
		        if (other.frow.get(i) != null)
		            return false;
		    } else if (!frow.get(i).equals(other.frow.get(i)))
		        return false;
		}
	    for(int i=0;i<fcolumn.size();i++)//列数 
		{
			if(fcolumn.get(i).longValue()!=other.fcolumn.get(i).longValue()) 
			{
				return false;
			}
			if (fcolumn.get(i) == null) {
		        if (other.fcolumn.get(i) != null)
		            return false;
		    } else if (!fcolumn.get(i).equals(other.fcolumn.get(i)))
		        return false;
		}
	    System.out.println("首行首列相同");
	    //每行和
	    Collections.sort(erow);
	    for(int i=0;i<erow.size();i++) 
		{
	    	System.out.println("排序："+erow.get(i).longValue());
			if(erow.get(i).longValue()!=other.erow.get(i).longValue()) 
			{
				return false;
			}
			if (erow.get(i) == null) {
		        if (other.erow.get(i)!= null)
		            return false;
		    } else if (!erow.get(i).equals(other.erow.get(i)))
		        return false;
		}
	    System.out.println("每行和相同");
	    return true;
	}
}