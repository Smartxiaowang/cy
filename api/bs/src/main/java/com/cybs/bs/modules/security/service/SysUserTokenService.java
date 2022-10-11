/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.cybs.bs.modules.security.service;

import com.cybs.bs.common.Global.R;
import com.cybs.bs.common.utils.BaseService;
import com.cybs.bs.modules.security.entity.SysUserTokenEntity;

/**
 * 用户Token
 * 
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserTokenService extends BaseService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(Long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(Long userId);

}