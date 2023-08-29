package pers.anshay.notebook.excelimport.intervace;

import java.io.InputStream;

/**
 * @author anshay
 * @date 2023/7/4
 */
public interface ImportRule<T> {

	/**
	 * 导入规则
	 * @param in 规则
	 * @return 任务id
	 * @throws Exception
	 */
	String load(InputStream in)throws Exception;
}
