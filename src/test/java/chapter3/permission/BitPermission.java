package chapter3.permission;

import com.alibaba.druid.util.StringUtils;
import junit.framework.Assert;
import org.apache.shiro.authz.Permission;

/**
 *  瑙勫垯
 *    +璧勬簮瀛楃涓�+鏉冮檺浣�+瀹炰緥ID
 *
 *  浠�+寮�澶� 涓棿閫氳繃+鍒嗗壊
 *
 *  鏉冮檺锛�
 *     0 琛ㄧず鎵�鏈夋潈闄�
 *     1 鏂板 0001
 *     2 淇敼 0010
 *     4 鍒犻櫎 0100
 *     8 鏌ョ湅 1000
 *
 *  濡� +user+10 琛ㄧず瀵硅祫婧恥ser鎷ユ湁淇敼/鏌ョ湅鏉冮檺
 *
 *  涓嶈�冭檻涓�浜涘紓甯告儏鍐�
 *
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-26
 * <p>Version: 1.0
 */
public class BitPermission implements Permission {

    private String resourceIdentify;
    private int permissionBit;
    private String instanceId;

    public BitPermission(String permissionString) {
        String[] array = permissionString.split("\\+");

        if(array.length > 1) {
            resourceIdentify = array[1];
        }

        if(StringUtils.isEmpty(resourceIdentify)) {
            resourceIdentify = "*";
        }

        if(array.length > 2) {
            permissionBit = Integer.valueOf(array[2]);
        }

        if(array.length > 3) {
            instanceId = array[3];
        }

        if(StringUtils.isEmpty(instanceId)) {
            instanceId = "*";
        }

    }

    public boolean implies(Permission p) {
        if(!(p instanceof BitPermission)) {
            return false;
        }
        BitPermission other = (BitPermission) p;

        if(!("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(other.resourceIdentify))) {
            return false;
        }

        if(!(this.permissionBit ==0 || (this.permissionBit & other.permissionBit) != 0)) {
            return false;
        }

        if(!("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BitPermission{" +
                "resourceIdentify='" + resourceIdentify + '\'' +
                ", permissionBit=" + permissionBit +
                ", instanceId='" + instanceId + '\'' +
                '}';
    }
}
