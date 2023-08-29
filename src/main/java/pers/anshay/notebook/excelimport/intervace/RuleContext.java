package pers.anshay.notebook.excelimport.intervace;

import java.util.List;

/**
 * @author anshay
 * @date 2023/7/4
 */
public interface RuleContext<T> {
	/**
	 * 获取任务
	 *
	 * @return
	 */
	String taskId();

	/**
	 * 设置任务id
	 *
	 * @param taskId 任务id
	 */
	default void setTaskId(String taskId) {

	}

	/**
	 * 获取规则
	 *
	 * @return
	 */
	List<T> getRules();

	/**
	 * 获取附加信息
	 *
	 * @param key
	 * @return
	 */
	default Object getAttach(String key) {
		return null;
	}

	/**
	 * 添加附加信息
	 *
	 * @param key   key
	 * @param value value
	 */
	default void addAttach(String key, Object value) {
	}
}
