package org.service.provider;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sample.data.User;

public interface UserMapper {

	@Update("CREATE TABLE IF NOT EXISTS USER (id varchar, sensitiveData varchar, nonSensitiveData varchar)")
	void create();
	
	@Select("SELECT * FROM USER WHERE id = #{id}")
	User selectUser(String id);
	
	@Insert({"INSERT INTO USER (id, sensitiveData, nonSensitiveData) VALUES (#{id}, #{sensitiveData}, #{nonSensitiveData})"})
	int insert(User user);
}
