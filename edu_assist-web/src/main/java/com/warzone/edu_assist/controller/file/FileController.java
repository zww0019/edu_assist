package com.warzone.edu_assist.controller.file;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.warzone.edu_assist_import.factory.DataFactory;
import com.warzone.edu_assist_import.rule.Column;
import com.warzone.edu_assist_import.rule.ImportRule;
import com.warzone.edu_assist_import.rule.Row;
import com.warzone.edu_assist_util.excel.POIUtil;
import com.warzone.util.datamapping.MetaData;
import com.warzone.util.datamapping.Model;

@Controller
@RequestMapping("namelist")
public class FileController {
	
	@Autowired
	private HttpServletRequest request;

	@RequestMapping("fileupload")
	public void fileUpload(@RequestParam("file") MultipartFile file) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
						+ file.getOriginalFilename();
				// 转存文件
				System.out.println(filePath);
				file.transferTo(new File(filePath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value="import",method=RequestMethod.POST,produces= {"application/json;charset=UTF-8"})
	public void readlist(@RequestParam("excel_file") MultipartFile file,MultipartHttpServletRequest request,HttpServletResponse response) throws IOException {
		String classes_id = request.getParameter("classes_id");
		System.out.println(classes_id);
		if (!file.isEmpty()) {
			try {
				List<String[]> namelist = POIUtil.readExcel(file);
				List<ImportRule> rules = new ArrayList<>();
				ImportRule rule1 = new ImportRule(new Column(2, 5, new String[] { "行政班级", "学号", "姓名", "性别" }),
						new Row(8, 52));
				ImportRule rule2 = new ImportRule(new Column(2, 5, new String[] { "行政班级", "学号", "姓名", "性别" }),
						new Row(61, 105));
				ImportRule rule3 = new ImportRule(new Column(2, 5, new String[] { "行政班级", "学号", "姓名", "性别" }),
						new Row(114, 158));
				ImportRule rule4 = new ImportRule(new Column(2, 5, new String[] { "行政班级", "学号", "姓名", "性别" }),
						new Row(136, 176));
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
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
