package chapter3.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import chapter3.permission.BitPermission;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-26
 * <p>Version: 1.0
 */
public class MyRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("role1");
        authorizationInfo.addRole("role2");
        authorizationInfo.addObjectPermission(new BitPermission("+user1+10"));
        authorizationInfo.addObjectPermission(new WildcardPermission("user1:*"));
        authorizationInfo.addStringPermission("+user2+10");
        authorizationInfo.addStringPermission("user2:*");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();  //寰楀埌鐢ㄦ埛鍚�
        String password = new String((char[])token.getCredentials()); //寰楀埌瀵嗙爜
        if(!"zhang".equals(username)) {
            throw new UnknownAccountException(); //濡傛灉鐢ㄦ埛鍚嶉敊璇�
        }
        if(!"123".equals(password)) {
            throw new IncorrectCredentialsException(); //濡傛灉瀵嗙爜閿欒
        }
        //濡傛灉韬唤璁よ瘉楠岃瘉鎴愬姛锛岃繑鍥炰竴涓狝uthenticationInfo瀹炵幇锛�
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
