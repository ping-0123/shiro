package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.shiro.entity.Organization;
import com.yinzhiwu.shiro.entity.User;
import com.yinzhiwu.shiro.service.OrganizationService;
import com.yinzhiwu.shiro.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTest {

	@Autowired UserService userService;
	@Autowired OrganizationService organizationService;
	
	@Test
	public void test(){
		Organization org = new Organization();
		org.setName("音之舞");
		organizationService.save(org);
	}
	
	@Test
	public void newUser(){
		User user = new User();
		user.setUsername("admin");
		user.setPassword("admin");
		user.setOrganization(organizationService.get(2l));
		userService.save(user);
	}
}
