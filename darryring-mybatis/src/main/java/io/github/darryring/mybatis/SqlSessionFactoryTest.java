package io.github.darryring.mybatis;

import io.github.darryring.mybatis.entity.UserDO;
import io.github.darryring.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 *
 * </p>
 *
 * @author darryring
 * @since 2020-12-05 14:07
 */
public class SqlSessionFactoryTest {
    private static SqlSessionFactory xmlReader() throws IOException {
        String resource = "io/github/darryring/mybatis/mapper/UserMapper.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    private static SqlSessionFactory classReader() {
        DataSource dataSource = UserDataSourceFactory.getUserDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);
        return new SqlSessionFactoryBuilder().build(configuration);
    }

    private static void run(SqlSessionFactory sqlSessionFactory) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserDO blog = (UserDO) session.selectOne("io.github.darryring.mybatis.mapper.UserMapper.selectUser", 101);

            UserMapper mapper = session.getMapper(UserMapper.class);
            blog = mapper.selectUser(101);
        }
    }

    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = xmlReader();
//        SqlSessionFactory sqlSessionFactory = classReader();
        run(sqlSessionFactory);
    }

    private static class UserDataSourceFactory {
        public static DataSource getUserDataSource() {
            return null;
        }
    }
}
