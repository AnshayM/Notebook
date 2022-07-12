package pers.anshay.notebook.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pers.anshay.notebook.service.ISolutionService;

/**
 * @author machao
 * @date 2022/5/19
 */
@Service
public class SolutionServiceImpl implements ISolutionService {

	/**
	 * 这个logger和@Slf4j的log是同一个类
	 */
	private static final Logger logger = LoggerFactory.getLogger(SolutionServiceImpl.class);

	@Override
	public int getResult(int index) throws Exception {

		int i = doGetResult(index);
		if (i < 0) {
			throw new Exception("请输入正确的序号值！");
		}
		return i;
	}

	// f(n)=f(n-1)+f(n-2)
	private int doGetResult(int index) {
		if (index <= 0) {
			return -1;
		} else if (index == 1) {
			return 0;
		} else if (index == 2) {
			return 1;
		}
		return doGetResult(index - 1) + doGetResult(index - 2);
	}
}
