package chapter3;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-26
 * <p>Version: 1.0
 */
public abstract class BaseTest {


    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//閫�鍑烘椂璇疯В闄ょ粦瀹歋ubject鍒扮嚎绋� 鍚﹀垯瀵逛笅娆℃祴璇曢�犳垚褰卞搷
    }

    protected void login(String configFile, String username, String password) {
        //1銆佽幏鍙朣ecurityManager宸ュ巶锛屾澶勪娇鐢↖ni閰嶇疆鏂囦欢鍒濆鍖朣ecurityManager
        Factory<SecurityManager> factory =new IniSecurityManagerFactory(configFile);

        //2銆佸緱鍒癝ecurityManager瀹炰緥 骞剁粦瀹氱粰SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3銆佸緱鍒癝ubject鍙婂垱寤虹敤鎴峰悕/瀵嗙爜韬唤楠岃瘉Token锛堝嵆鐢ㄦ埛韬唤/鍑瘉锛�
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        subject.login(token);
    }

    public Subject subject() {
        return SecurityUtils.getSubject();
    }
}
