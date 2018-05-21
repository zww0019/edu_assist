package com.warzone.edu_assist_util.path;

public class TaskAffixPath extends Path{
	
	@Override
	protected Catalogue catalogue() {
		// TODO Auto-generated method stub
		return Catalogue.教师作业要求附件类;
	}

	@Override
	protected Director directorRule() {
		Director root = new Director();
		root.setName("/a");
		Director sub = new Director();
		root.setSub(sub);
		sub.setName("/b");
		Director sub2 = new Director();
		sub.setSub(sub2);
		sub2.setName("/c");
		Director sub3 = new Director();
		sub2.setSub(sub3);
		sub3.setName("/d");
		Director sub4 = new Director();
		sub3.setSub(sub4);
		sub4.setName("/c");
		return root;
	}
	
	
	public static void main(String[] args) {
		Path taskAffixPath = new TaskAffixPath();
		System.out.println(taskAffixPath.toString());
	}
}
