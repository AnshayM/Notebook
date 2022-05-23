package anshay.notebook.service.impl;

import anshay.notebook.service.ISolutionService;
import org.springframework.stereotype.Service;

/**
 * @author machao
 * @date 2022/5/19
 */
@Service
public class SolutionServiceImpl implements ISolutionService {
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
