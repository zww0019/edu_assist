package com.warzone.edu_assist_service_task.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.warzone.edu_assist_dao_task.dto.TaskComplectionDTO;
import com.warzone.edu_assist_dao_task.dto.TaskDTO;
import com.warzone.edu_assist_domain_task.Task;
import com.warzone.edu_assist_domain_task.Task_Completion;
import com.warzone.edu_assist_repository_classes.repository.ClassesRepository;
import com.warzone.edu_assist_repository_task.repository.TaskRepository;
import com.warzone.edu_assist_service_task.path.TaskAffixPath;
import com.warzone.edu_assist_service_task.path.TaskUPPath;
import com.warzone.edu_assist_service_task.service.TaskService;
import com.warzone.edu_assist_status.TaskDelayStatus;
import com.warzone.edu_assist_status.TaskStatus;
import com.warzone.edu_assist_util.path.FilePathGenerater;
import com.warzone.edu_assist_util.path.Path;
@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private ClassesRepository classrepository;
	@Override
	public List<Task> findTaskReportByClassesid(int classesid) {
		return taskRepository.findTaskReportByClassesid(classesid);
	}

	@Override
	@Transactional
	public int addTask(TaskDTO task) {
		taskRepository.addTask(task);
		int classes_id = task.getClasses_id();
		//设置学生上传作业存储位置
		TaskUPPath task_up_path = new TaskUPPath();
		task_up_path.setClasses_id(String.valueOf(classes_id));
		task_up_path.setTask_id(String.valueOf(task.getTask_id()));
		String temp2 = task.getTask_directory();
		task.setTask_directory(temp2+task_up_path.toString());
		//分别创建这两个目录
		String full_task_UP_path = task.getTask_directory();
		FilePathGenerater.createDir(full_task_UP_path);
		taskRepository.updateTaskPath(task.getTask_id(), full_task_UP_path);
		return task.getTask_id();
	}

	@Override
	public boolean deleteTaskByTaskid(int taskid) {
		return taskRepository.deleteTaskByTaskid(taskid);
	}

	@Override
	public List<Task_Completion> getComplectionStudentList(int classesid,int taskid) {
		return taskRepository.getComplectionStudentList(classesid,taskid);
	}

	@Override
	public List<Task_Completion> getUnComplectionStudentList(int classesid,int taskid) {
		return taskRepository.getUnComplectionStudentList(classesid,taskid);
	}

	@Override
	public boolean updateComplectionStatus(int taskid, String studentid,String position) {
		return taskRepository.updateComplectionStatus(taskid, studentid,position);
	}

	@Override
	public boolean updateContentByTaskid(int taskid, String content) {
		return taskRepository.updateContentByTaskid(taskid, content);
	}

	@Override
	public void updateTaskDelayStatus(int taskid, String studentid, String verifier, TaskDelayStatus taskDelayStatus) {
		taskRepository.updateTaskDelay(taskid, studentid, verifier, taskDelayStatus.getStatus());
		
	}

	@Override
	public List<Task_Completion> geTaskComplectionDTOsByClassesIDAndNaturalClass(int classesid, String classa,
			int taskid) {
		// TODO Auto-generated method stub
		return taskRepository.geTaskComplectionDTOsByClassesIDAndNaturalClass(classesid, classa, taskid);
	}

	@Override
	public List<Task_Completion> getTaskComplectionDTOsByClassesID(int classesid, int taskid) {
		// TODO Auto-generated method stub
		return taskRepository.getTaskComplectionDTOsByClassesID(classesid, taskid);
	}

	@Override
	public HashMap<TaskStatus, List<Task_Completion>> getTaskComplectionDTOsOFNaturalClass(int classesid, int taskid,
			String classname) {
		// TODO Auto-generated method stub
		return taskRepository.getTaskComplectionDTOsOFNaturalClass(classesid, taskid, classname);
	}

	@Override
	public Task getTaskByTaskid(int taskid) {
		// TODO Auto-generated method stub
		return taskRepository.getTaskByTaskid(taskid);
	}

}
