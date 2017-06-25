package chapter3;

import junit.framework.Assert;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.junit.Test;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-26
 * <p>Version: 1.0
 */
public class AuthorizerTest extends BaseTest {

    @Test
    public void testIsPermitted() {
        login("classpath:chapter3/shiro-authorizer.ini", "zhang", "123");
        //鍒ゆ柇鎷ユ湁鏉冮檺锛歶ser:create
        Assert.assertTrue(subject().isPermitted("user1:update"));
        Assert.assertTrue(subject().isPermitted("user2:update"));
        //閫氳繃浜岃繘鍒朵綅鐨勬柟寮忚〃绀烘潈闄�
        Assert.assertTrue(subject().isPermitted("+user1+2"));//鏂板鏉冮檺
        Assert.assertTrue(subject().isPermitted("+user1+8"));//鏌ョ湅鏉冮檺
        Assert.assertTrue(subject().isPermitted("+user2+10"));//鏂板鍙婃煡鐪�

        Assert.assertFalse(subject().isPermitted("+user1+4"));//娌℃湁鍒犻櫎鏉冮檺

        Assert.assertTrue(subject().isPermitted("menu:view"));//閫氳繃MyRolePermissionResolver瑙ｆ瀽寰楀埌鐨勬潈闄�
    }

    @Test
    public void testIsPermitted2() {
        login("classpath:chapter3/shiro-jdbc-authorizer.ini", "zhang", "123");
        //鍒ゆ柇鎷ユ湁鏉冮檺锛歶ser:create
        Assert.assertTrue(subject().isPermitted("user1:update"));
        Assert.assertTrue(subject().isPermitted("user2:update"));
        //閫氳繃浜岃繘鍒朵綅鐨勬柟寮忚〃绀烘潈闄�
        Assert.assertTrue(subject().isPermitted("+user1+2"));//鏂板鏉冮檺
        Assert.assertTrue(subject().isPermitted("+user1+8"));//鏌ョ湅鏉冮檺
        Assert.assertTrue(subject().isPermitted("+user2+10"));//鏂板鍙婃煡鐪�

        Assert.assertFalse(subject().isPermitted("+user1+4"));//娌℃湁鍒犻櫎鏉冮檺

        Assert.assertTrue(subject().isPermitted("menu:view"));//閫氳繃MyRolePermissionResolver瑙ｆ瀽寰楀埌鐨勬潈闄�
    }






}
