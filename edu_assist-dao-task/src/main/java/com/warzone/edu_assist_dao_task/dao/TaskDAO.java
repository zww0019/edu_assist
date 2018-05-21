package com.warzone.edu_assist_dao_task.dao;

import java.sql.Date;
import java.util.List;


import com.warzone.edu_assist_dao_task.dto.TaskComplectionDTO;
import com.warzone.edu_assist_dao_task.dto.TaskDTO;
import com.warzone.edu_assist_dao_task.dto.TaskDelayDTO;
import com.warzone.util.dao.BaseDAO;
@BaseDAO
public interface TaskDAO {
	public int insertTask(TaskDTO taskDTO);
	public int deleteTaskByTask_id(int taskID);
	public List<TaskDTO> findTaskByClassid(int classesId);
	public int updateContent(String content,int task_id);
	public int updateTaskComplectionStatus(int task_id,String studentid,String position);
	public int batchInsertTaskComplectionMeasure(List<TaskComplectionDTO> taskComplectionDTOs);
	public int updateTaskEndDate(int task_id,Date date);
	public int updateTaskDelayStatus(int task_id,String student_id,String verifier,String status);
	public List<TaskDelayDTO> findTaskDelayDTOsByClassesid(int classesID);
	public TaskDelayDTO findTaskDelayDTOByTask_id(int task_id);
	public int updatePath(int task_id,String task_directory);
	public int updateTaskAffix(int task_id,String task_affix_path);
	public TaskDTO selectTaskByTaskid(int taskid);
}	
