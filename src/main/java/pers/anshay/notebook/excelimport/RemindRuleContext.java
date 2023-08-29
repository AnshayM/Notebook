package pers.anshay.notebook.excelimport;

import pers.anshay.notebook.excelimport.intervace.RuleContext;

import java.util.List;

public class RemindRuleContext implements RuleContext<RemindImportDto> {
	private String taskId;


	@Override
	public String taskId() {
		return taskId;
	}

	@Override
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Override
	public List<RemindImportDto> getRules() {
		return null;
	}

}