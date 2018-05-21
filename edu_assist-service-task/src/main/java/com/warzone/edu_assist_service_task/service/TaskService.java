package com.warzone.edu_assist_service_task.service;

import java.util.HashMap;
import java.util.List;

import com.warzone.edu_assist_dao_task.dto.TaskComplectionDTO;
import com.warzone.edu_assist_dao_task.dto.TaskDTO;
import com.warzone.edu_assist_domain_task.Task;
import com.warzone.edu_assist_domain_task.Task_Completion;
import com.warzone.edu_assist_status.TaskDelayStatus;
import com.warzone.edu_assist_status.TaskStatus;

public interface TaskService {
	public List<Task> findTaskReportByClassesid(int classesid);
	public int addTask(TaskDTO task);
	public boolean deleteTaskByTaskid(int taskid);
	public List<Task_Completion> getComplectionStudentList(int classesid,int taskid);
	public List<Task_Completion> getUnComplectionStudentList(int classesid,int taskid);
	public boolean updateComplectionStatus(int taskid,String studentid,String position);
	public boolean updateContentByTaskid(int taskid,String content);
	public void updateTaskDelayStatus(int taskid,String studentid,String verifier,TaskDelayStatus taskDelayStatus);
	public List<Task_Completion> geTaskComplectionDTOsByClassesIDAndNaturalClass(int classesid,String classa,int taskid);
	public List<Task_Completion> getTaskComplectionDTOsByClassesID(int classesid,int taskid);
	public HashMap<TaskStatus, List<Task_Completion>> getTaskComplectionDTOsOFNaturalClass(int classesid,int taskid,String classname);
	public Task getTaskByTaskid(int taskid);
}
