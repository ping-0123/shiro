package test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
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
public class AuthenticatorTest {
	
	private static final Log logger = LogFactory.getLog(AuthenticatorTest.class);

    @Test
    public void testAllSuccessfulStrategyWithSuccess() {
        login("classpath:shiro-authenticator-all-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //寰楀埌涓�涓韩浠介泦鍚堬紝鍏跺寘鍚簡Realm楠岃瘉鎴愬姛鐨勮韩浠戒俊鎭�
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
    }

    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStrategyWithFail() {
        login("classpath:shiro-authenticator-all-fail.ini");
    }

    @Test
    public void testAtLeastOneSuccessfulStrategyWithSuccess() {
        login("classpath:shiro-authenticator-atLeastOne-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //寰楀埌涓�涓韩浠介泦鍚堬紝鍏跺寘鍚簡Realm楠岃瘉鎴愬姛鐨勮韩浠戒俊鎭�
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
    }

    @Test
    public void testFirstOneSuccessfulStrategyWithSuccess() {
        login("classpath:shiro-authenticator-first-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //寰楀埌涓�涓韩浠介泦鍚堬紝鍏跺寘鍚簡绗竴涓猂ealm楠岃瘉鎴愬姛鐨勮韩浠戒俊鎭�
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(1, principalCollection.asList().size());
    }

    @Test
    public void testAtLeastTwoStrategyWithSuccess() {
        login("classpath:shiro-authenticator-atLeastTwo-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //寰楀埌涓�涓韩浠介泦鍚堬紝鍥犱负myRealm1鍜宮yRealm4杩斿洖鐨勮韩浠戒竴鏍锋墍浠ヨ緭鍑烘椂鍙繑鍥炰竴涓�
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(1, principalCollection.asList().size());
    }

    @Test
    public void testOnlyOneStrategyWithSuccess() {
        login("classpath:shiro-authenticator-onlyone-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //寰楀埌涓�涓韩浠介泦鍚堬紝鍥犱负myRealm1鍜宮yRealm4杩斿洖鐨勮韩浠戒竴鏍锋墍浠ヨ緭鍑烘椂鍙繑鍥炰竴涓�
        PrincipalCollection principalCollection = subject.getPrincipals();
        logger.info(principalCollection.asList().size());
        Assert.assertEquals(1, principalCollection.asList().size());
    }

    private void login(String configFile) {
        //1銆佽幏鍙朣ecurityManager宸ュ巶锛屾澶勪娇鐢↖ni閰嶇疆鏂囦欢鍒濆鍖朣ecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory(configFile);

        //2銆佸緱鍒癝ecurityManager瀹炰緥 骞剁粦瀹氱粰SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3銆佸緱鍒癝ubject鍙婂垱寤虹敤鎴峰悕/瀵嗙爜韬唤楠岃瘉Token锛堝嵆鐢ㄦ埛韬唤/鍑瘉锛�
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        subject.login(token);
    }

    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//閫�鍑烘椂璇疯В闄ょ粦瀹歋ubject鍒扮嚎绋� 鍚﹀垯瀵逛笅娆℃祴璇曢�犳垚褰卞搷
    }

}
