package sys.admin.modules.apiAuth;

import org.apache.http.HttpStatus;
import sys.admin.modules.sys.dao.SysUserDao;
import sys.admin.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * <p>Title: AuthPermission</p>
 * <p>Description: </p>
 * 第三方服务请求后台管理系统
 * 1.判断是否存在session  不存在则登录
 * 2.存在则返回是否有访问对应url的权限
 *
 * @Author yangtao
 * @Date 2018/9/4 16:33
 */
@RestController
@RequestMapping(value = "/sys")
public class AuthPermission {

    Logger logger = LoggerFactory.getLogger(AuthPermission.class);

    @Autowired
    private SysUserDao sysUserDao;

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public boolean getAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestUrl = request.getHeader("X-Original-URI");
            //定义list集合存储url
        SysUserEntity sysUser = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        List<String> urlList = sysUserDao.queryAllUrl(sysUser.getUserId());

        //用户Url列表 将用户Url存储在set集合中（去掉重复，忽略空的Url）
        Set<String> permsSet = new HashSet<>();
        for (String url : urlList) {
            if (!StringUtils.isBlank(url)) {
                permsSet.addAll(Arrays.asList(url.trim().split(",")));
            }
        }
        //遍历获取的url集合 返回对应的状态
        for (String url : permsSet) {
            if (requestUrl.contains(url)) {
                return true;
            }
        }

        //logger.error("No access is allowed, please contact the system administrator.");
        //throw new UnauthorizedException("No access is allowed, please contact the system administrator");
        //没有权限访问 返回403
        response.setStatus(HttpStatus.SC_FORBIDDEN);

        return false;
    }
}



