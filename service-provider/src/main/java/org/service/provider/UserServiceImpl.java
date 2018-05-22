package org.service.provider;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.service.api.UserService;

import com.sample.data.User;

public class UserServiceImpl implements UserService {
	
	private static SqlSessionFactory factory = initFactory();
	
	private static SqlSessionFactory initFactory() {
		try (Reader reader = Resources.getResourceAsReader("META-INF/mybatis-config.xml")) {
			return new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public User getUser(String id) {
		try(SqlSession session = factory.openSession()) {
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.create();
			mapper.insert(new User("a", "sensitiveA", "nonSensitiveA"));
			mapper.insert(new User("b", "sensitiveB", "nonSensitiveB"));
			return mapper.selectUser(id);
		}		
	}
}
