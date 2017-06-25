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
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;

import junit.framework.Assert;


/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-25
 * <p>Version: 1.0
 */
public class LoginLogoutTest {
	
	private static final Log logger = LogFactory.getLog(LoginLogoutTest.class);

    @Test
    public void testHelloworld() {
        //1銆佽幏鍙朣ecurityManager宸ュ巶锛屾澶勪娇鐢↖ni閰嶇疆鏂囦欢鍒濆鍖朣ecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");

        //2銆佸緱鍒癝ecurityManager瀹炰緥 骞剁粦瀹氱粰SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3銆佸緱鍒癝ubject鍙婂垱寤虹敤鎴峰悕/瀵嗙爜韬唤楠岃瘉Token锛堝嵆鐢ㄦ埛韬唤/鍑瘉锛�
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4銆佺櫥褰曪紝鍗宠韩浠介獙璇�
            subject.login(token);
        } catch (AuthenticationException e) {
            //5銆佽韩浠介獙璇佸け璐�
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //鏂█鐢ㄦ埛宸茬粡鐧诲綍

        //6銆侀��鍑�
        subject.logout();
    }


    @Test
    public void testCustomRealm() {
        //1銆佽幏鍙朣ecurityManager宸ュ巶锛屾澶勪娇鐢↖ni閰嶇疆鏂囦欢鍒濆鍖朣ecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        //2銆佸緱鍒癝ecurityManager瀹炰緥 骞剁粦瀹氱粰SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3銆佸緱鍒癝ubject鍙婂垱寤虹敤鎴峰悕/瀵嗙爜韬唤楠岃瘉Token锛堝嵆鐢ㄦ埛韬唤/鍑瘉锛�
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "1231");

        try {
            //4銆佺櫥褰曪紝鍗宠韩浠介獙璇�
            subject.login(token);
        } catch (AuthenticationException e) {
            //5銆佽韩浠介獙璇佸け璐�
            e.printStackTrace();
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //鏂█鐢ㄦ埛宸茬粡鐧诲綍

        //6銆侀��鍑�
        subject.logout();
    }

    @Test
    public void testCustomMultiRealm() {
        //1銆佽幏鍙朣ecurityManager宸ュ巶锛屾澶勪娇鐢↖ni閰嶇疆鏂囦欢鍒濆鍖朣ecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");

        //2銆佸緱鍒癝ecurityManager瀹炰緥 骞剁粦瀹氱粰SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3銆佸緱鍒癝ubject鍙婂垱寤虹敤鎴峰悕/瀵嗙爜韬唤楠岃瘉Token锛堝嵆鐢ㄦ埛韬唤/鍑瘉锛�
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4銆佺櫥褰曪紝鍗宠韩浠介獙璇�
            subject.login(token);
        } catch (AuthenticationException e) {
            //5銆佽韩浠介獙璇佸け璐�
            e.printStackTrace();
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //鏂█鐢ㄦ埛宸茬粡鐧诲綍

        //6銆侀��鍑�
        subject.logout();
    }


    @Test
    public void testJDBCRealm() {
        //1銆佽幏鍙朣ecurityManager宸ュ巶锛屾澶勪娇鐢↖ni閰嶇疆鏂囦欢鍒濆鍖朣ecurityManager
        Factory<SecurityManager> factory =new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");

        //2銆佸緱鍒癝ecurityManager瀹炰緥 骞剁粦瀹氱粰SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3銆佸緱鍒癝ubject鍙婂垱寤虹敤鎴峰悕/瀵嗙爜韬唤楠岃瘉Token锛堝嵆鐢ㄦ埛韬唤/鍑瘉锛�
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wl", "123");

        try {
            //4銆佺櫥褰曪紝鍗宠韩浠介獙璇�
            subject.login(token);
            logger.info("lonin success");
        } catch (AuthenticationException e) {
            //5銆佽韩浠介獙璇佸け璐�
            e.printStackTrace();
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //鏂█鐢ㄦ埛宸茬粡鐧诲綍

        //6銆侀��鍑�
        subject.logout();
    }


    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//閫�鍑烘椂璇疯В闄ょ粦瀹歋ubject鍒扮嚎绋� 鍚﹀垯瀵逛笅娆℃祴璇曢�犳垚褰卞搷
    }

}
