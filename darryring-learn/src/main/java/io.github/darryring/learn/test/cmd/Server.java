package io.github.darryring.learn.test.cmd;

import io.github.darryring.util.cmd.RuntimeUtil;

public class Server {
    public static void main(String[] args) {
        String[] cmds = new String[]{
                "C:\\Users\\xh\\Documents\\Run\\nginx.cmd"
                ,"D:\\Deposit\\DataMan\\server\\8519-apache-tomcat-8.0.39/clean-start.bat"
        };
        for (String s : cmds) {
            RuntimeUtil.startByCmd(s);
            break;
        }
    }
}
