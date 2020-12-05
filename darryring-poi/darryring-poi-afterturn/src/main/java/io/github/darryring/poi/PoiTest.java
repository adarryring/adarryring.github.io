package io.github.darryring.poi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author darryring
 * @since 2020-09-14 11:34
 */
public class PoiTest {
    public static void main(String[] args) throws IOException {
        String path = "D:\\Deposit\\000\\x-professional\\x-professional-learn\\x-easypoi\\src\\main\\resources/test.xlsx";

        TemplateExportParams params = new TemplateExportParams(path);
        Map<String, Object> map = new HashMap<>();
        map.put("date", "2014-12-25");
        map.put("money", 2000000.00);
        map.put("upperMoney", "贰佰万");
        map.put("company", "执笔潜行科技有限公司");
        map.put("bureau", "财政局");
        map.put("person", "JueYue");
        map.put("phone", "1879740****");
        List<Map<String, String>> listMap = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Map<String, String> lm = new HashMap<>();
            lm.put("id", i + 1 + "");
            lm.put("name", "darryring" + i + 1);
            lm.put("no", i + 1 + "");

            listMap.add(lm);
        }
        map.put("maplist", listMap);

        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            boolean b = savefile.mkdirs();
            System.out.println(String.format("创建文件%s", b ? "成功" : "失败"));
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/专项支出用款申请书_map.xls");
        workbook.write(fos);
        fos.close();
    }
}
