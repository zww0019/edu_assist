package com.warzone.edu_assist.controller.teacher;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.warzone.edu_assist.controller.teacher.viewhelper.VSessionParemterDTO;
import com.warzone.edu_assist.controller.teacher.viewhelper.ViewClassesDTO;
import com.warzone.edu_assist.controller.teacher.viewhelper.ViewNaturalClassesDTO;
import com.warzone.edu_assist.controller.teacher.viewhelper.ViewStudentDTO;
import com.warzone.edu_assist.controller.teacher.viewhelper.ViewTask_statusDTO;
import com.warzone.edu_assist_dao_classes.dto.ClassesDTO;
import com.warzone.edu_assist_dao_task.dto.TaskDTO;
import com.warzone.edu_assist_domain_classes.Classes;
import com.warzone.edu_assist_domain_classes.NaturalClasses;
import com.warzone.edu_assist_domain_session.Academic_year;
import com.warzone.edu_assist_domain_task.Task;
import com.warzone.edu_assist_domain_task.Task_Completion;
import com.warzone.edu_assist_domain_user.User;
import com.warzone.edu_assist_import.factory.DataFactory;
import com.warzone.edu_assist_import.rule.Column;
import com.warzone.edu_assist_import.rule.ImportRule;
import com.warzone.edu_assist_import.rule.Row;
import com.warzone.edu_assist_service_classes.service.ClassesService;
import com.warzone.edu_assist_service_session.service.SessionService;
import com.warzone.edu_assist_service_session.vo.AcademicYearVO;
import com.warzone.edu_assist_service_student.service.StudentService;
import com.warzone.edu_assist_service_task.service.TaskService;
import com.warzone.edu_assist_service_teacher.service.TeacherService;
import com.warzone.edu_assist_status.TaskStatus;
import com.warzone.edu_assist_util.excel.POIUtil;
import com.warzone.util.datamapping.MetaData;
import com.warzone.util.datamapping.Model;

@Controller
@RequestMapping("teacher")
public class TeacherController {

	@Autowired
	private SessionService sessionService;

	@Autowired
	private ClassesService classesService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private TaskService taskService;

	@RequestMapping("mainpage")
	public String main() {
		return "/teacher/teacher_main";
	}

	@RequestMapping("sessionpage")
	public String sessionpage() {
		return "teacher/session/session_main";
	}

	@RequestMapping("semesterpage")
	public ModelAndView getSemesterClassess(HttpServletRequest request, VSessionParemterDTO vSessionDTO) {
		AcademicYearVO academicYearVO = new AcademicYearVO();
		academicYearVO.setEnd_year(vSessionDTO.getEnd_year());
		academicYearVO.setStart_year(vSessionDTO.getStart_year());
		academicYearVO.setSemester(vSessionDTO.getSemester());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("academicyear", vSessionDTO.getStart_year() + "-" + vSessionDTO.getEnd_year());
		modelAndView.addObject("semester", vSessionDTO.getSemester());
		modelAndView.addObject("sessions", sessionService.getSessionByAcademicYearSemester(academicYearVO));
		modelAndView.setViewName("teacher/session/semester_main/semester_main");
		return modelAndView;
	}

	@RequestMapping("classespage")
	public ModelAndView classpage(HttpServletRequest request, VSessionParemterDTO vSessionDTO) {
		ModelAndView modelAndView = new ModelAndView();
		User user = (User) request.getSession().getAttribute("user");
		int session_id = sessionService.getSessionID(user.getUserid(), vSessionDTO.getStart_year(),
				vSessionDTO.getEnd_year(), vSessionDTO.getSemester(), vSessionDTO.getCourse());
		List<Classes> classess = classesService.getDetailClassesBySessionID(session_id);
		vSessionDTO.setStatus(sessionService.getSessionStatus(user.getUserid(), vSessionDTO.getStart_year(), vSessionDTO.getEnd_year(), vSessionDTO.getSemester(), vSessionDTO.getCourse()));
		List<ViewClassesDTO> viewClassesDTOs = new ArrayList<>();
		for (Classes classes : classess) {
			ViewClassesDTO viewClassesDTO = new ViewClassesDTO();
			viewClassesDTO.setClasses_id(classes.getClasses_id());
			viewClassesDTO.setClasses_name(classes.getClasses_name());
			viewClassesDTO.setStudent_sum(classes.getSize());
			viewClassesDTO.setNaturalClasses(classes.getNaturalClasses());

			List<ViewTask_statusDTO> task_statuss = new ArrayList<>();
			List<Task> tasks = taskService.findTaskReportByClassesid(classes.getClasses_id());
			if(tasks!=null) {
			for (Task task : tasks) {
				List<Task_Completion> complete = taskService.getComplectionStudentList(classes.getClasses_id(),
						task.getTask_id());
				List<Task_Completion> uncomplete = taskService.getUnComplectionStudentList(classes.getClasses_id(),
						task.getTask_id());
				ViewTask_statusDTO task_status = new ViewTask_statusDTO();

				task_status.setComplete_count(complete.size());

				task_status.setUncomplete_count(uncomplete.size());
				// 设置已完成上交的学生列表
				List<ViewStudentDTO> v_complete_Student = new ArrayList<>();

				for (Task_Completion taskComplectionDTO : complete) {
					ViewStudentDTO student = new ViewStudentDTO();
					student.setStudentid(taskComplectionDTO.getStudent_id());
					student.setStudentname(taskComplectionDTO.getStudent_name());
					v_complete_Student.add(student);
				}
				// 设置未完成上交的学生列表
				List<ViewStudentDTO> v_uncomplete_Student = new ArrayList<>();

				for (Task_Completion taskComplectionDTO2 : uncomplete) {
					ViewStudentDTO student = new ViewStudentDTO();
					student.setStudentid(taskComplectionDTO2.getStudent_id());
					student.setStudentname(taskComplectionDTO2.getStudent_name());
					v_uncomplete_Student.add(student);
				}
				task_status.setComplete_namelist(v_complete_Student);
				task_status.setUncomplete_namelist(v_uncomplete_Student);
				task_status.setTask_content(task.getTask_content());
				task_status.setTask_id(task.getTask_id());
				task_status.setTask_name(task.getTask_name());
				task_statuss.add(task_status);

			}
			}
			viewClassesDTO.setTask_statuses(task_statuss);
			viewClassesDTOs.add(viewClassesDTO);

		}
		modelAndView.addObject("session", vSessionDTO);
		if (classess.isEmpty()) {
			modelAndView.setViewName("teacher/session/semester_main/classes/no_classes");
		} else {
			vSessionDTO.setTeachername(teacherService.getTeacherByid(user.getUserid()).getName());
			modelAndView.addObject("classes", viewClassesDTOs);
			modelAndView.setViewName("teacher/session/semester_main/classes/classes_main");
		}
		return modelAndView;
	}

	@RequestMapping(value = "{start_year}/{end_year}/sessions", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	public ModelAndView getSessionByteacheAndAcademicyear(@PathVariable String start_year,
			@PathVariable String end_year, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		User user = (User) request.getSession().getAttribute("user");
		Academic_year vAcademic_year = sessionService.getAcademicYearByTeacheridAndYear(user.getUserid(), start_year,
				end_year);
		modelAndView.addObject("academic_year", vAcademic_year);
		modelAndView.setViewName("teacher/session/semester_main/academicyear_main");
		return modelAndView;
	}

	@RequestMapping(value = "{start_year}/{end_year}/{semester}/sessions", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	public ModelAndView getCourseByteacherAndSemester(@PathVariable String start_year, @PathVariable String end_year,
			@PathVariable String semester, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		User user = (User) request.getSession().getAttribute("user");
		Academic_year academic_year = sessionService.getSemester(user.getUserid(), start_year, end_year, semester);
		modelAndView.addObject("academic_year", academic_year);
		modelAndView.setViewName("teacher/session/semester_main/semester_main");
		return modelAndView;
	}

	@RequestMapping(value = "classes", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public void createClasses(HttpServletResponse response, VSessionParemterDTO vSessionParemterDTO,
			String classes_name, HttpServletRequest request) throws IOException {
		Writer writer = response.getWriter();
		User user = (User) request.getSession().getAttribute("user");
		int session_id = sessionService.getSessionID(user.getUserid(), vSessionParemterDTO.getStart_year(),
				vSessionParemterDTO.getEnd_year(), vSessionParemterDTO.getSemester(), vSessionParemterDTO.getCourse());
		if (session_id == -1)
			writer.write("fail");
		else {
			ClassesDTO classesDTO = new ClassesDTO();
			classesDTO.setSession_id(session_id);
			classesDTO.setClasses_name(classes_name);
			classesService.createClasses(session_id, classesDTO);
			writer.write("success");
			writer.close();
		}
	}

	@RequestMapping(value = "classes", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public ModelAndView entireClasses(HttpServletRequest request, VSessionParemterDTO vSessionParemterDTO, String classes_id,
			String classes_name) {
		ModelAndView modelAndView = new ModelAndView();
		User user = (User) request.getSession().getAttribute("user");
		int sessionid = sessionService.getSessionID(user.getUserid(), vSessionParemterDTO.getStart_year(),
				vSessionParemterDTO.getEnd_year(), vSessionParemterDTO.getSemester(), vSessionParemterDTO.getCourse());
		Classes classes = classesService.getClassesByClassesid(sessionid, Integer.valueOf(classes_id));
		HashMap<Integer, List<ViewNaturalClassesDTO>> hashMap = new HashMap<>();
		List<Task> taskDTOs = taskService.findTaskReportByClassesid(Integer.valueOf(classes_id));
		if(taskDTOs!=null) {
		for (Task task : taskDTOs) {
			List<ViewNaturalClassesDTO> viewNaturalClassesDTOs = new ArrayList<>();
			for (NaturalClasses naturalClasses : classes.getNaturalClasses()) {
				List<ViewTask_statusDTO> viewTask_statusDTOs = new ArrayList<>();
				ViewNaturalClassesDTO viewNaturalClassesDTO = new ViewNaturalClassesDTO();
				viewNaturalClassesDTO.setName(naturalClasses.getName());
				ViewTask_statusDTO viewTask_statusDTO = new ViewTask_statusDTO();
				viewTask_statusDTO.setTask_id(task.getTask_id());
				viewTask_statusDTO.setTask_name(task.getTask_name());
				viewTask_statusDTO.setTask_content(task.getTask_content());
				List<Task_Completion> complete = taskService.getTaskComplectionDTOsOFNaturalClass(
						Integer.valueOf(classes_id), task.getTask_id(), naturalClasses.getName()).get(TaskStatus.完成);
				List<Task_Completion> uncomplete = taskService.getTaskComplectionDTOsOFNaturalClass(
						Integer.valueOf(classes_id), task.getTask_id(), naturalClasses.getName()).get(TaskStatus.未完成);
				List<ViewStudentDTO> complete_student = new ArrayList<>();
				List<ViewStudentDTO> uncomplete_student = new ArrayList<>();
				for (Task_Completion taskComplectionDTO : complete) {
					ViewStudentDTO viewStudentDTO = new ViewStudentDTO();
					viewStudentDTO.setClassa(taskComplectionDTO.getClassa());
					viewStudentDTO.setStudentid(taskComplectionDTO.getStudent_id());
					viewStudentDTO.setStudentname(taskComplectionDTO.getStudent_name());
					complete_student.add(viewStudentDTO);
				}
				for (Task_Completion taskComplectionDTO : uncomplete) {
					ViewStudentDTO viewStudentDTO = new ViewStudentDTO();
					viewStudentDTO.setClassa(taskComplectionDTO.getClassa());
					viewStudentDTO.setStudentid(taskComplectionDTO.getStudent_id());
					viewStudentDTO.setStudentname(taskComplectionDTO.getStudent_name());
					uncomplete_student.add(viewStudentDTO);
				}
				viewTask_statusDTO.setComplete_count(complete_student.size());
				viewTask_statusDTO.setComplete_namelist(complete_student);
				viewTask_statusDTO.setUncomplete_count(uncomplete_student.size());
				viewTask_statusDTO.setUncomplete_namelist(uncomplete_student);
				viewTask_statusDTOs.add(viewTask_statusDTO);
				viewNaturalClassesDTO.setTask_statusDTOs(viewTask_statusDTOs);
				viewNaturalClassesDTOs.add(viewNaturalClassesDTO);
			}
			hashMap.put(task.getTask_id(),viewNaturalClassesDTOs);
		}

//		List<NaturalClasses> naturalClassess = classes.getNaturalClasses();
//		List<ViewNaturalClassesDTO> viewNaturalClassesDTOs = new ArrayList<>();
//
//		for (NaturalClasses naturalClasses : naturalClassess) {
//			ViewNaturalClassesDTO naturalClassesDTO = new ViewNaturalClassesDTO();
//			naturalClassesDTO.setName(naturalClasses.getName());
//			List<ViewTask_statusDTO> task_statusDTOs = new ArrayList<>();
//			for (Task task : classes.getTasks()) {
//				List<ViewStudentDTO> complete_list = new ArrayList<>();
//				List<ViewStudentDTO> uncomplete_list = new ArrayList<>();
//				ViewTask_statusDTO task_statusDTO = new ViewTask_statusDTO();
//				for (TaskComplectionDTO taskComplectionDTO : taskService
//						.geTaskComplectionDTOsByClassesIDAndNaturalClass(Integer.valueOf(classes_id),
//								naturalClasses.getName(), task.getTask_id())) {
//					ViewStudentDTO viewStudentDTO = new ViewStudentDTO();
//					viewStudentDTO.setClassa(taskComplectionDTO.getClassa());
//					viewStudentDTO.setStudentid(taskComplectionDTO.getStudent_id());
//					viewStudentDTO.setStudentname(taskComplectionDTO.getStudent_name());
//					if (taskComplectionDTO.getComplete_status().equals(TaskStatus.完成.getStatus())) {
//						complete_list.add(viewStudentDTO);
//					} else {
//						uncomplete_list.add(viewStudentDTO);
//					}
//				}
//				task_statusDTO.setComplete_count(complete_list.size());
//				task_statusDTO.setComplete_namelist(complete_list);
//				task_statusDTO.setUncomplete_count(uncomplete_list.size());
//				task_statusDTO.setUncomplete_namelist(uncomplete_list);
//				task_statusDTO.setTask_id(task.getTask_id());
//				task_statusDTO.setTask_name(task.getTask_name());
//				task_statusDTO.setTask_content(task.getTask_content());
//			}
//			naturalClassesDTO.setTask_statusDTOs(task_statusDTOs);
//			viewNaturalClassesDTOs.add(naturalClassesDTO);
//		}
		
		}
		modelAndView.addObject("session", vSessionParemterDTO);
		modelAndView.addObject("task_list", hashMap);
		modelAndView.addObject("tasks", taskDTOs);
		modelAndView.addObject("classesname", classes_name);
		modelAndView.addObject("classesid", classes_id);
		modelAndView.setViewName("teacher/session/semester_main/classes/classes_in/task_detail");
		return modelAndView; 
		}

	@RequestMapping(value = "students", method = RequestMethod.POST)
	public void importStudents(@RequestParam("excel_file") MultipartFile file, String classes_name,
			@RequestParam("classes_id") int classes_id) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException {
		if (!file.isEmpty()) {
			try {
				List<String[]> namelist = POIUtil.readExcel(file);
				List<ImportRule> rules = new ArrayList<>();
				ImportRule rule1 = new ImportRule(
						new Column(2, 5, new String[] { "classa", "studentid", "name", "sex" }), new Row(8, 52));
				ImportRule rule2 = new ImportRule(
						new Column(2, 5, new String[] { "classa", "studentid", "name", "sex" }), new Row(61, 105));
				ImportRule rule3 = new ImportRule(
						new Column(2, 5, new String[] { "classa", "studentid", "name", "sex" }), new Row(114, 158));
				ImportRule rule4 = new ImportRule(
						new Column(2, 5, new String[] { "classa", "studentid", "name", "sex" }), new Row(167, 176));
				rules.add(rule1);
				rules.add(rule2);
				rules.add(rule3);
				rules.add(rule4);
				DataFactory.setRules(rules);
				List<Model> models = DataFactory.importDatafromList(namelist);
				for (Model model : models) {
					System.out.println("第" + model.getLine() + "行");
					for (MetaData metaData : model.getMetaDatas()) {
						System.out.print(metaData.getValue() + "  ");
					}
					System.out.println();
				}
				classesService.importStudentToClasses(models, classes_id);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "task", method = RequestMethod.POST)
	public void uploadTaskAffix(@RequestParam("classes_id") int classes_id, String task_name, String task_content,
			Date end_date, String captcha, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String sion_captcha = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		Writer writer = response.getWriter();
		if (captcha.equals(sion_captcha)) {
			TaskDTO task = new TaskDTO();
			task.setClasses_id(classes_id);
			task.setEnd_date(end_date);
			task.setPub_date(new Date(new java.util.Date().getTime()));
			task.setTask_content(task_content);
			task.setTask_name(task_name);
			task.setAffix_position(request.getSession().getServletContext().getRealPath("/"));
			task.setTask_directory(request.getSession().getServletContext().getRealPath("/"));
			taskService.addTask(task);
			writer.write("success");
			writer.close();
		} else {
			writer.write("验证码错误");
		}

	}
}
