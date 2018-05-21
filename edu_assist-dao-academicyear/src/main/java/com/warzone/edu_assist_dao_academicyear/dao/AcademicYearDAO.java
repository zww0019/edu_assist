package com.warzone.edu_assist_dao_academicyear.dao;

import com.warzone.edu_assist_dao_academicyear.dto.AcademicYearDTO;
import com.warzone.util.dao.BaseDAO;

@BaseDAO
public interface AcademicYearDAO {
	public int insertAcademicYear(AcademicYearDTO academicYearDTO);
	public AcademicYearDTO selectAcadeYear(AcademicYearDTO academicYearDTO);
}
