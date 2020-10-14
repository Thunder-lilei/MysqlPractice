package cn.edu.imufe.service;
import java.util.List;

import cn.edu.imufe.entity.*;
public interface AnswerService {
	List<Answer> RandomProblems();//随机出题 
	List<Answer> SelectProblems();
	Answer selectByPrimaryKey(Integer id);
	
	List<Integer> selectAllid();
}
