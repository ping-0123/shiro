package test.realm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-25
 * <p>Version: 1.0
 */
public class MyRealm4 implements Realm {

	private Log logger = LogFactory.getLog(MyRealm3.class);
	
    public String getName() {
    	logger.info("MyRealm4.getName is executing......");
        return "myrealm4";
    }

    public boolean supports(AuthenticationToken token) {
    	logger.info("MyRealm4.supports is executing......");
        return token instanceof UsernamePasswordToken; //浠呮敮鎸乁sernamePasswordToken绫诲瀷鐨凾oken
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	logger.info("MyRealm4.getAuthenticationInfo is executing......");

        String username = (String)token.getPrincipal();  //寰楀埌鐢ㄦ埛鍚�
        String password = new String((char[])token.getCredentials()); //寰楀埌瀵嗙爜
        logger.info(username);
        logger.info(password);
        if(!"zhang".equals(username)) {
        	logger.info("UnknownAccountException");
            throw new UnknownAccountException(); //濡傛灉鐢ㄦ埛鍚嶉敊璇�
        }
        if(!"123".equals(password)) {
        	logger.info("IncorrectCredentialsException");
            throw new IncorrectCredentialsException(); //濡傛灉瀵嗙爜閿欒
        }
        //濡傛灉韬唤璁よ瘉楠岃瘉鎴愬姛锛岃繑鍥炰竴涓狝uthenticationInfo瀹炵幇锛�
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
