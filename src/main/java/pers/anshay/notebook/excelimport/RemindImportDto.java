package pers.anshay.notebook.excelimport;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@HeadRowHeight(49)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemindImportDto {

	/**
	 * id
	 */
	@ColumnWidth(19)
	@ExcelProperty(index = 0, value = "编号")
	@HeadFontStyle(color = 4, fontName = "微软雅黑", bold = false, fontHeightInPoints = 11)
	private String id;

	/**
	 * 所在excel的行数
	 */
	@ExcelIgnore
	private Integer line;

	@ExcelIgnore
	private Integer index;

	public void setIndex(Integer index) {
		this.index = index;
		this.line = index + 2;
	}

	/**
	 * 提醒名称
	 */
	@ColumnWidth(25)
	@ExcelProperty(index = 1, value = "提醒名称*")
	@HeadFontStyle(color = 10, fontName = "微软雅黑", bold = false, fontHeightInPoints = 11)
	private String messageName;

	/**
	 * 1级分类
	 */
	@ColumnWidth(16)
	@ExcelProperty(index = 2, value = "1级分类*")
	@HeadFontStyle(color = 10, fontName = "微软雅黑", bold = false, fontHeightInPoints = 11)
	private String categoryName;

	/**
	 * 2级分类
	 */
	@ColumnWidth(16)
	@ExcelProperty(index = 3, value = "2级分类")
	@HeadFontStyle(color = 4, fontName = "微软雅黑", bold = false, fontHeightInPoints = 11)
	private String categoryName2;

	/**
	 * 3级分类
	 */
	@ColumnWidth(16)
	@ExcelProperty(index = 4, value = "3级分类")
	@HeadFontStyle(color = 4, fontName = "微软雅黑", bold = false, fontHeightInPoints = 11)
	private String categoryName3;

	/**
	 * 提醒程度：致命违规/一般违规/常规提醒
	 */
	@ColumnWidth(60)
	@ExcelProperty(index = 5, value = "提醒程度*")
	@HeadFontStyle(color = 10, fontName = "微软雅黑", bold = false, fontHeightInPoints = 11)
	private String remainderClassify;

	@ExcelIgnore
	private Integer remainderClassifyLevel;

	/**
	 * 提醒内容
	 */
	@ColumnWidth(39)
	@ExcelProperty(index = 6, value = "提醒内容*")
	@HeadFontStyle(color = 10, fontName = "微软雅黑", bold = false, fontHeightInPoints = 11)
	private String messageDescription;

	/**
	 * 命中规则内容
	 */
	@ColumnWidth(29)
	@ExcelProperty(index = 7, value = "命中规则内容")
	@HeadFontStyle(color = 4, fontName = "微软雅黑", bold = false, fontHeightInPoints = 11)
	private String hitRules;


	public void setRemainderClassify(String remainderClassify) {
		this.remainderClassify = remainderClassify;
		getRemainderClassifyLevel();
	}

	public void setRemainderClassifyLevel(Integer remainderClassifyLevel) {
		this.remainderClassifyLevel = remainderClassifyLevel;
		getRemainderClassifyLevel();
	}

	public Integer getRemainderClassifyLevel() {
		if (remainderClassifyLevel == null) {
			// remainderClassifyLevel = RemainderClassifyEnum.getCodeByMsg(remainderClassify);
		}
		return remainderClassifyLevel;
	}

	public String getRemainderClassify() {
		if (remainderClassify == null) {
			// remainderClassify = RemainderClassifyEnum.getMsgByCode(remainderClassifyLevel);
		}
		return remainderClassify;
	}

	public static Integer getIndexByLine(Integer line) {
		return line - 2;
	}
}