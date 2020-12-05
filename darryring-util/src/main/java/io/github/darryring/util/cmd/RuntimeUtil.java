package io.github.darryring.util.cmd;

import io.github.darryring.util.io.StreamUtil;
import io.github.darryring.util.string.StringUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author darryring
 * @date 2017年2月25日 下午1:30:53
 * @explain 进程操作
 */
public class RuntimeUtil {

    private static final String cmd_window_title = "cmd /c tasklist /FI \"WINDOWTITLE eq [[cmd_title]]\"";

    // 执行 cmd 命令并返回控制台信息
    public static String executeCmd(String str_cmd) {
        String str_result = new String();
        InputStream is = null;

        try {
            Process process = Runtime.getRuntime().exec(str_cmd);
            // 读取流
            is = process.getInputStream();

            str_result = StreamUtil.getStringByReader(is, StringUtil.encoding_gbk); // windows os

            if (str_result.toString().isEmpty()) {
                str_result = StreamUtil.getStringByByte(process.getErrorStream());
            }
        } catch (IOException e) {
            str_result = e.getMessage();
            System.err.println(str_result);
        } finally {
            StreamUtil.close(is);
        }
        return str_result;
    }

    /**
     * 根据title判断cmd进程是否存在
     **/
    public static boolean existByTitle(String cmd_title) {
        String s = executeCmd(cmd_window_title.replace("[[cmd_title]]", cmd_title)).trim();
        return !(s.equals("INFO: No tasks are running which match the specified criteria.") || s.equals("信息: 没有运行的任务匹配指定标准。"));
    }

    /**
     * 根据路径执行脚本
     **/
    public static void startByCmd(String path_cmd) {
        String cmd_start = "cmd /c start [[path_cmd]]";
        try {
            Runtime.getRuntime().exec(cmd_start.replace("[[path_cmd]]", path_cmd));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
