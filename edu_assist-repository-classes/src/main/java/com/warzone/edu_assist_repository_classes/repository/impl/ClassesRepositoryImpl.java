package com.warzone.edu_assist_repository_classes.repository.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.warzone.edu_assist_dao_classes.dao.ClassesDAO;
import com.warzone.edu_assist_dao_classes.dto.ClassesDTO;
import com.warzone.edu_assist_dao_student.dto.StudentDTO;
import com.warzone.edu_assist_dao_task.dto.TaskComplectionDTO;
import com.warzone.edu_assist_dao_task.dto.TaskDTO;
import com.warzone.edu_assist_domain_classes.Classes;
import com.warzone.edu_assist_domain_classes.NaturalClasses;
import com.warzone.edu_assist_domain_student.Student;
import com.warzone.edu_assist_domain_task.Task;
import com.warzone.edu_assist_domain_task.Task_Completion;
import com.warzone.edu_assist_repository_classes.repository.ClassesRepository;
import com.warzone.edu_assist_repository_student.repository.StudentRepository;
import com.warzone.edu_assist_repository_task.repository.TaskRepository;
@Repository
public class ClassesRepositoryImpl implements ClassesRepository {
	private HashMap<Integer, List<Classes>> sessionid_classes  = new HashMap<>();
	@Autowired
	private ClassesDAO classesDao;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TaskRepository taskRepository;
	private void initClasses(int session_id) {
		List<ClassesDTO> classes_DTO = classesDao.selectClassBySessionID(session_id);
		List<NaturalClasses> naturalClasses = new ArrayList<>();
		List<Classes> classess = new ArrayList<>();
		HashMap<String, List<Student>> naturalclasses_student = null;
		for(ClassesDTO classesDTO : classes_DTO) {
			naturalclasses_student=new HashMap<>();
			List<Student> allstudents = studentRepository.getStudentByClassid(classesDTO.getClasses_id());
			//将学生以自然班为单位，分组
			for(Student student:allstudents) {
				if(naturalclasses_student.containsKey(student.getClassa())) {
					naturalclasses_student.get(student.getClassa()).add(student);
				}else {
					naturalclasses_student.put(student.getClassa(), new ArrayList<>());
					naturalclasses_student.get(student.getClassa()).add(student);
				}
			}
			//教学班与作业整合
//			List<TaskDTO> taskDTOs = taskRepository.findTaskReportByClassesid(classesDTO.getClasses_id());
//			List<Task> domain_tasks = new ArrayList<>();
//			if(taskDTOs!=null) {
//			//DTO对象转换为domain对象，这里需要优化，出现这个问题的原因是DTO和domain的理解不到位
//			for(TaskDTO taskDTO : taskDTOs) {
//				Task task = new Task();
//				task.setAffix_position(taskDTO.getAffix_position());
//				task.setTask_directory(taskDTO.getTask_directory());
//				task.setClasses_id(taskDTO.getClasses_id());
//				task.setEnd_date(taskDTO.getEnd_date());
//				task.setPub_date(taskDTO.getPub_date());
//				List<Task_Completion> task_Completions = new ArrayList<>();
//				for(TaskComplectionDTO taskComplectionDTO : taskRepository.getTaskComplectionDTOsByClassesID(classesDTO.getClasses_id(), taskDTO.getTask_id())) {
//					Task_Completion task_Completion = new Task_Completion();
//					task_Completion.setComplete_status(taskComplectionDTO.getComplete_status());;
//					task_Completion.setStudent_id(taskComplectionDTO.getStudent_id());
//					task_Completion.setStudent_name(taskComplectionDTO.getStudent_name());
//					task_Completion.setTask_id(taskComplectionDTO.getTask_id());
//					task_Completion.setTask_position(taskComplectionDTO.getTask_position());
//					task_Completions.add(task_Completion);
//				}
//				task.setTask_Completions(task_Completions);
//				task.setTask_content(taskDTO.getTask_content());
//				task.setTask_id(taskDTO.getTask_id());
//				task.setTask_name(taskDTO.getTask_name());
//				domain_tasks.add(task);
//			}
//			}
			//把自然班与教学班整合到一起
			Classes classes = new Classes();
			classes.setClasses_id(classesDTO.getClasses_id());
			classes.setClasses_name(classesDTO.getClasses_name());
			//classes.setTasks(domain_tasks);
			int students_count = 0;
			for(String naturalclass : naturalclasses_student.keySet()) {
				System.out.println(naturalclass);
				System.out.println(naturalclasses_student.get(naturalclass));
				students_count+=naturalclasses_student.get(naturalclass).size();
				NaturalClasses naturalClass = new NaturalClasses();
				naturalClass.setName(naturalclass);
				naturalClass.setStudents(naturalclasses_student.get(naturalclass));
				naturalClasses.add(naturalClass);
			}
			classes.setSize(students_count);
			classes.setNaturalClasses(naturalClasses);
			classess.add(classes);
			
		}
		this.sessionid_classes.put(session_id, classess);
	}
	private void clear() {
		this.sessionid_classes.clear();
	}
	@Override
	public List<Classes> getDetailClasses(int sessionid){
		if(!this.sessionid_classes.containsKey(sessionid)) {
			initClasses(sessionid);
		}
		return this.sessionid_classes.get(sessionid);
	}
	@Override
	public int getStudentsSize(int session_id,int classesid) {
		for(Classes classes : sessionid_classes.get(session_id)) {
			if(classes.getClasses_id()==classesid) {
				return classes.getSize();
			}
		}
		return -1;
	}
	@Override
	public List<Classes> getSimplyClasses(int sessionid) {
		// TODO Auto-generated method stub
		List<ClassesDTO> classesDTOs = classesDao.selectClassBySessionID(sessionid);
		List<Classes> simplyclassess = new ArrayList<>();
		for(ClassesDTO classesDTO:classesDTOs) {
			Classes classes = new Classes();
			classes.setClasses_id(classesDTO.getClasses_id());
			classes.setClasses_name(classesDTO.getClasses_name());
			classes.setSize(getStudentsSize(sessionid,classesDTO.getClasses_id()));
			simplyclassess.add(classes);
		}
		return simplyclassess;
	}
	@Override
	public void createClasses(ClassesDTO classesDTO,int session_id) {
		classesDTO.setSession_id(session_id);
		classesDao.insertClasses(classesDTO);
		clear();
	}
	private void refresh() {
		this.sessionid_classes.clear();
		
	}
	@Override
	@Transactional
	public void batchInsertStudentToClasses(int classesid, List<StudentDTO> studentDTOs) {
		// TODO Auto-generated method stub
		studentRepository.batchInsertStudentInfo(studentDTOs, classesid);
		refresh();
	}
	@Override
	public void updateTaskAffix(String path, int taskid) {
		taskRepository.updateTaskAffix(taskid, path);
		refresh();
	}
	@Override
	public List<Student> getStudentByClassa(int sessionid,int classesid,String classa) {
		if(!this.sessionid_classes.containsKey(sessionid)) {
			initClasses(sessionid);
		}
		for(Classes classes : sessionid_classes.get(sessionid)) {
			for(NaturalClasses naturalClasses : classes.getNaturalClasses()) {
				if(naturalClasses.getName().equals(classa)) {
					return naturalClasses.getStudents();
				}
			}
		}
		return null;
	}
	@Override
	public Classes getClassesByClassesID(int sessionid, int classesid) {
		if(!this.sessionid_classes.containsKey(sessionid)) {
			initClasses(sessionid);
		}
		for(Classes classes : sessionid_classes.get(sessionid)) {
			if(classes.getClasses_id()==classesid) {
				return classes;
			}
		}
		return null;
	}
	@Override
	public void publishTask(TaskDTO taskDTO) {
		// TODO Auto-generated method stub
		taskRepository.addTask(taskDTO);
		sessionid_classes.clear();
	}
	@Override
	public void updateTaskPath(int taskid, String task_directory) {
		// TODO Auto-generated method stub
		taskRepository.updateTaskPath(taskid, task_directory);
		sessionid_classes.clear();
	}
	@Override
	public List<ClassesDTO> getClassesByStudentid(String studentid){
		return classesDao.selectClassesByStudentid(studentid);
	}
	@Override
	public HashMap<Integer, List<Task>> getSessionidAndTasksByStudentid(String studentid) {
		HashMap<Integer, List<Task>> hashMap = new HashMap<>();
		for(ClassesDTO classesDTO : getClassesByStudentid(studentid)) {
			int sessionid =classesDTO.getSession_id();
			int classesid = classesDTO.getClasses_id();
			if(!sessionid_classes.containsKey(sessionid)) {
				initClasses(sessionid);
			}
		}
		for(Integer id : sessionid_classes.keySet()) {
			for(Classes classes : sessionid_classes.get(id)) {
				for(NaturalClasses naturalClasses : classes.getNaturalClasses()) {
					for(Student student : naturalClasses.getStudents()) {
						if(student.getStudentid().equals(studentid)) {
							hashMap.put(id, classes.getTasks());
						}
					}
				}
			}
		}
		return hashMap;
	}
	@Override
	public ClassesDTO getClasssesBySessionid(int sessionid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
