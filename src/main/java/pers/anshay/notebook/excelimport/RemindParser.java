package pers.anshay.notebook.excelimport;

import com.alibaba.excel.EasyExcel;
import org.springframework.stereotype.Service;
import pers.anshay.notebook.excelimport.intervace.RuleContext;
import pers.anshay.notebook.excelimport.intervace.RuleParser;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Service
public class RemindParser extends CommonParser implements RuleParser<RemindImportDto> {

    @Override
    public RuleContext<RemindImportDto> parse(InputStream in) throws Exception {
        // ExcelListener listener = new ExcelListener<>(getStencilHead());
        // EasyExcel.read(in,RemindImportDto.class,listener).sheet().doRead();
        // List<RemindImportDto> list = listener.getList();
        // checkEmpty(list);
        // for (int i = 0; i < list.size(); i++) {
        //     list.get(i).setIndex(i);
        // }
        // return new RemindRuleContext(list);
        return null;
    }

    @Override
    public HashMap<Integer, String> initStencilHead() {
        return new HashMap<Integer, String>() {{
            put(0, "编号");
            put(1, "提醒名称*");
            put(2, "1级分类*");
            put(3, "2级分类");
            put(4, "3级分类");
            put(5, "提醒程度*");
            put(6, "提醒内容*");
            put(7, "命中规则内容");
        }};
    }

}
