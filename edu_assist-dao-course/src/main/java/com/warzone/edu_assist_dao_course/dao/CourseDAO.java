package com.warzone.edu_assist_dao_course.dao;

import com.warzone.edu_assist_dao_course.dto.CourseDTO;
import com.warzone.edu_assist_domain_course.Course;
import com.warzone.util.dao.BaseDAO;

@BaseDAO
public interface CourseDAO{
	public int insertCourse(CourseDTO course);
	public CourseDTO selectCourseByID(int courseID);
	public int deleteCourseByID(int courseID);
	public int updateCourse(CourseDTO course);
	public CourseDTO selectCourseByCourseName(String courseName);
}
