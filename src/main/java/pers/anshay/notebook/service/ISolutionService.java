package pers.anshay.notebook.service;

import org.springframework.stereotype.Service;

/**
 * 斐波拉契数列
 *
 * @author machao
 * @date 2022/5/19
 */

public interface ISolutionService {
	/**
	 * @param index
	 * @return
	 */
	int getResult(int index) throws Exception;
}
