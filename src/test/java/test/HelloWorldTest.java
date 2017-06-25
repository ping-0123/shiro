package test;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class HelloWorldTest {
	private static Log logger = LogFactory.getLog(HelloWorldTest.class);

	@Test
	public void testHelloWorld(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		try{
			subject.login(token);
			System.out.println("login success: " + token);
		}catch (AuthenticationException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(true, subject.isAuthenticated());
		subject.logout();
	}
}
