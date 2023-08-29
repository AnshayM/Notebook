package pers.anshay.notebook.excelimport;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author machao
 * @date 2021/11/25
 */
public abstract class CommonParser {

	/**
	 * 模版头部信息，必填
	 */
	private Map<Integer, String> stencilHead;

	public abstract Map<Integer, String> initStencilHead();

	public Map<Integer, String> getStencilHead() {
		if (stencilHead == null) {
			stencilHead = initStencilHead();
		}
		return stencilHead;
	}

	/**
	 * 验空
	 *
	 * @param list 数据列表
	 */
	public void checkEmpty(List list) throws Exception {
		if (CollectionUtils.isEmpty(list)) {
			throw new Exception("文件内容不能为空");
		}
	}
}
