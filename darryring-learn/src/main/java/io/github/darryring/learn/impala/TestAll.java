package io.github.darryring.learn.impala;

import io.github.darryring.learn.util.SortUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;
import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestAll {

    //    https://www.cloudera.com/documentation/enterprise/latest/topics/impala_jdbc.html#impala_jdbc @official
//    https://github.com/onefoursix/Cloudera-Impala-JDBC-Example @example
//    https://www.cloudera.com/downloads/connectors/impala/jdbc/2-5-43.html @download
//    https://blog.csdn.net/wumiqing1/article/details/53925677 @example impala-jdbcimport @delete
//    https://downloads.cloudera.com/connectors/impala_jdbc_2.5.43.1063.zip @download
    private void impala() {

//        ArrayList
        String path_conf = "D:/Deposit/Temp/kerberos/xgdw";
        String JDBC_DRIVER = "com.cloudera.impala.jdbc41.Driver";
//        String CONNECTION_URL = "jdbc:impala://13.229.60.149:21050/default;AuthMech=1;KrbRealm=CLOUDERA.COM;KrbHostFQDN=ip-172-31-26-102.ap-southeast-1.compute.internal;KrbServiceName=impala";
        final String CONNECTION_URL = "jdbc:impala://192.168.8.221:21050/;AuthMech=1;KrbServiceName=impala;KrbRealm=MEDIPORTALDATA.TEST.COM;KrbHostFQDN=bdtest01;";

//        192.168.8.225 bdtest05

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //登录Kerberos账号
        try {
            System.setProperty("java.security.krb5.conf", path_conf + "/krb5.conf");
            Configuration configuration = new Configuration();
            configuration.set("hadoop.security.authentication", "Kerberos");
            UserGroupInformation.setConfiguration(configuration);
            UserGroupInformation.loginUserFromKeytab("xgdw@MEDIPORTALDATA.TEST.COM", path_conf + "/xgdw.keytab");
            System.out.println(UserGroupInformation.getCurrentUser() + "------" + UserGroupInformation.getLoginUser());

            UserGroupInformation loginUser = UserGroupInformation.getLoginUser();

            loginUser.doAs(new PrivilegedAction<Object>() {

                public Object run() {
                    Connection connection = null;
                    try {
                        Class.forName(JDBC_DRIVER);
                        connection = DriverManager.getConnection(CONNECTION_URL);
                        System.out.println(connection == null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            assert connection != null;
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

//        query
//                    ResultSet rs = null;
//                    PreparedStatement ps = null;
//                        ps = connection.prepareStatement("select * from test_table");
//                        rs = ps.executeQuery();
//                        rs = ps.executeQuery();
//                        while (rs.next()) {
//                            System.out.println(rs.getInt(1));
//                        }
    }


    private void time() {
//        BigInteger

        String a = "2016-03-02"; // 1456848000000
        a = "2016-03-02 10:07"; // 1456848000000
        a = "2015 53(25)";
        a = "2011年03月18日";
        System.out.println(a.length());

//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        DateFormat df = new SimpleDateFormat("yyyy");
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            Date date = df.parse(a);
            System.out.println(date.getTime());

            Calendar c = Calendar.getInstance();
            c.setTime(date);
            System.out.println(c.get(Calendar.YEAR));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void putAll() {
        List<Map<String, Object>> list_e = new ArrayList<>();
        List<Map<String, Object>> list_x = new ArrayList<>();
        Map<String, Object> map_e = new HashMap<>();
        map_e.put("a", 1);
        list_x.add(map_e);

        list_x.forEach(x -> {
            Map<String, Object> map_ee = new HashMap<>();
            map_ee.putAll(x);
            map_ee.put("b", 2);
            list_e.add(map_ee);
            x.put("c", 3);
        });

        list_e.forEach(x -> System.out.println(x));
        list_x.forEach(x -> System.out.println(x));
    }

    private void sort(Comparable[] arr) {
        SortUtil.sort(arr);
        Arrays.asList(arr).forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    public static void main(String[] args) {
        TestAll t = new TestAll();
//        t.impala();
//        t.time();
//        t.putAll();
//        t.str();

        t.sort(new Integer[]{1, 2, 6, 8, 4});
        t.sort(new String[]{"1", "2", "666", "8", "4"});

        Integer[] arr = new Integer[]{1, 2, 6, 8, 4};
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        arr[0] = 10;
        System.out.println(arr2[0]);

//        String a= "do_countReference";
//        System.out.println(a.substring(3));

    }

    private void str() {
        String a = "柴胡安心胶囊对心血管神经症的疗效和安全性观察.pdf";
        System.out.println(a.substring(0, a.lastIndexOf(".")));
        System.out.println(a.substring(1 + a.lastIndexOf(".")));
    }

}
