package org.tang.jpa.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tang.jpa.dto.system.ResourceDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.system.ResourceService;
import org.tang.jpa.service.system.UserService;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ResourceService resourceService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        
        UserDTO dto = new UserDTO();
        dto.setUserName(username);
        UserDTO user = userService.verifyUserLoginInfo(dto);
        
        Set<String> roles = new HashSet<String>();
        roles.add(user.getRoleId());
        
        ResourceDTO rdto = new ResourceDTO();
        rdto.setRoleId(user.getRoleId());
        rdto.setResourceType("2");
        List<ResourceDTO> rlist = resourceService.findUserResource(rdto);
        
        Set<String> permissions = new HashSet<String>();
        
        for(ResourceDTO rs :rlist){
        	permissions.add(rs.getResourceCode());
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String username = (String)token.getPrincipal();
        
        UserDTO dto = new UserDTO();
        dto.setUserName(username);
        UserDTO user = userService.verifyUserLoginInfo(dto);
        
        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        request.getSession().setAttribute("currentUser", user);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUserName(), //用户名
                user.getUserPwd(), //密码
                getName()  //realm name
        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
