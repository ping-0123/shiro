package test.authenticator.strategy;	

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.CollectionUtils;

import test.realm.MyRealm1;

import java.util.Collection;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-25
 * <p>Version: 1.0
 */
public class OnlyOneAuthenticatorStrategy extends AbstractAuthenticationStrategy {
	
	private Log logger = LogFactory.getLog(OnlyOneAuthenticatorStrategy.class);

    @Override
    public AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> realms, AuthenticationToken token) throws AuthenticationException {
    	logger.info("OnlyOneAuthenticatorStrategy.beforeAllAttempts is executing......");
        return new SimpleAuthenticationInfo();//杩斿洖涓�涓潈闄愮殑璁よ瘉淇℃伅
    }

    @Override
    public AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
    	logger.info("OnlyOneAuthenticatorStrategy.beforeAttempt is executing......");
        return aggregate;//杩斿洖涔嬪墠鍚堝苟鐨�
    }

    @Override
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo, AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {
    	logger.info("OnlyOneAuthenticatorStrategy.afterAttempt is executing......");
        AuthenticationInfo info;
        if (singleRealmInfo == null) {
            info = aggregateInfo;
        } else {
            if (aggregateInfo == null) {
                info = singleRealmInfo;
            } else {
                info = merge(singleRealmInfo, aggregateInfo);
                if(info.getPrincipals().getRealmNames().size() > 1) {
                    System.out.println(info.getPrincipals().getRealmNames());
                    throw new AuthenticationException("Authentication token of type [" + token.getClass() + "] " +
                            "could not be authenticated by any configured realms.  Please ensure that only one realm can " +
                            "authenticate these tokens.");
                }
            }
        }


        return info;
    }

    @Override
    public AuthenticationInfo afterAllAttempts(AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
    	logger.info("OnlyOneAuthenticatorStrategy.afterAllAttempts is executing......");
        return aggregate;
    }
}
