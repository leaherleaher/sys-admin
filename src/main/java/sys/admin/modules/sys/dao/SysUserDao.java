package sys.admin.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import sys.admin.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:34:11
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);

	/**
	 * @Description: 查询用户的所有URL
	 * @Date: 16:58 2018/9/4
	 * @Param: [userId]
	 * @return: java.util.List<java.lang.String>
	 **/
	List<String> queryAllUrl(Long userId);

}
