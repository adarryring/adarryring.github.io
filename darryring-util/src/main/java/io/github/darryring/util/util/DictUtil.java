package io.github.darryring.util.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.github.darryring.util.vo.LookupItemVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author darryring
 * @since 2020-09-16 15:19
 */
public class DictUtil {

    private static void print(Class<?> clazz) {
        List<LookupItemVO> itemVOS = Lists.newArrayList();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
            if (Objects.isNull(annotation)) continue;
            LookupItemVO itemVO = new LookupItemVO();
            itemVO.setLookupItemKey(field.getName());
            itemVO.setLookupItemName(annotation.value());

            itemVOS.add(itemVO);
        }
        System.out.println(JSON.toJSONString(itemVOS));
    }

    private static void line(String fileName) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            System.out.println(String.join("", lines).replace(" ", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void excel(Class<?> clazz) {
        List<String> a1 = Lists.newArrayList();
        List<String> a2 = Lists.newArrayList();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
            if (Objects.isNull(annotation)) continue;
            LookupItemVO itemVO = new LookupItemVO();
            a1.add(annotation.value());
            a2.add("t." + field.getName());
        }
        System.out.println(String.join("+", a1));
        System.out.println(String.join("+", a2));
    }

    public static void main(String[] args) {
        final String root = "C:\\Users\\haoran.hong\\IdeaProjects\\ips-salary\\db\\v20_1016\\json";
//        print(BaseCaseInfoDO.class);
//        print(SalaryAccountingDO.class);
//        line(root + "/salary-BaseCaseInfoDO-default.json");
//        line(root + "/salary-BaseCaseInfoDO-all.json");

//        excel(SalaryAccountingDO.class);
//        line(root + "/salary-SalaryAccountingDO-all.json");
//        line(root + "/salary-SalaryAccountingDO-default.json");
//        excel(ReportIndividualIncomeTaxLiquidationExportVO.class);
//        excel(ReportOperationDeclarationExportVO.class);
//        excel(ReportSubjectSubdivisionExportVO.class);
    }
}
