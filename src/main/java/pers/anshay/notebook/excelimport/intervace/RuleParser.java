package pers.anshay.notebook.excelimport.intervace;

import java.io.InputStream;

/**
 * @author anshay
 * @date 2023/7/4
 */
public interface RuleParser<T> {
	/**
	 * 解析器
	 *
	 * @param in in
	 * @return
	 * @throws Exception
	 */
	RuleContext<T> parse(InputStream in) throws Exception;
}
