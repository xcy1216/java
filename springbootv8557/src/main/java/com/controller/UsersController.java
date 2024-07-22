
package com.controller;


import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.entity.view.UsersView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.TokenEntity;
import com.entity.UsersEntity;
import com.service.TokenService;
import com.service.UsersService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 登录相关
 */
//@RequestMapping("/user")
@RestController
public class UsersController{
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private TokenService tokenService;

	/**
	 * 前台用户登录
	 */
	@IgnoreAuth
	@GetMapping(value = "/user/login")
	public R loginZ(String username, String password, String captcha, HttpServletRequest request) {
		System.out.println(username+" "+password);
		UsersEntity user = userService.selectOne(new EntityWrapper<UsersEntity>().eq("username", username));
		if(user==null || !user.getPassword().equals(password)) {
			return R.error("账号或密码不正确");
		}else if (user.getRole().equals("管理员")){
			return R.error("该账号为管理员账号，非用户账号！");
		}
		String token = tokenService.generateToken(user.getId(),username, "users", user.getRole());
		System.out.println("ok  " + token);
		return R.ok().put("token", token);

	}

	/**
	 * 后台管理员登录
	 */
	@IgnoreAuth
	@PostMapping(value = "/admin/user/login")
//	@PostMapping(value = "users/login")
	public R loginA(String username, String password, String captcha, HttpServletRequest request) {
		UsersEntity user = userService.selectOne(new EntityWrapper<UsersEntity>().eq("username", username));
		if(user==null || !user.getPassword().equals(password)) {
			return R.error("账号或密码不正确");
		}else if (user.getRole().equals("用户")){
			return R.error("该账号为用户账号，非管理员账号！");
		}
		String token = tokenService.generateToken(user.getId(),username, "users", user.getRole());
		return R.ok().put("token", token);
	}

	/**
	 * 前台用户注册
	 */
	@IgnoreAuth
	@PostMapping(value = "/user/register")
	public R registerZ(@RequestBody UsersEntity user){
//    	ValidatorUtils.validateEntity(user);
		System.out.println(user.getPhonenumber());
		if(userService.selectOne(new EntityWrapper<UsersEntity>().eq("username", user.getUsername())) !=null) {
			return R.error("用户已存在");
		}
		user.setRole("用户");
		user.setAddtime(new Date());
		userService.insert(user);
//		System.out.println(user);
//		userService.insert(new UsersEntity(user.getUsername(),user.getPassword(),user.getRole(),user.getPhonenumber()));
		return R.ok();
	}

//	/**
//	 * 后台管理员注册（实际上管理员不能注册哈哈哈哈）
//	 */
//	@IgnoreAuth
//	@PostMapping(value = "admin/user/register")
//	public R registerA(@RequestBody UsersEntity user){
////    	ValidatorUtils.validateEntity(user);
//    	if(userService.selectOne(new EntityWrapper<UsersEntity>().eq("username", user.getUsername())) !=null) {
//    		return R.error("用户已存在");
//    	}
//		user.setRole("管理员");
//		user.setAddtime(new Date());
//		userService.insert(user);
////		userService.insert(new UsersEntity(user.getUsername(),user.getPassword(),user.getRole(),user.getPhonenumber()));
//        return R.ok();
//    }

	/**
	 * 前台退出
	 */
	@GetMapping(value = "/user/logout")
	public R logoutZ(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}

	/**
	 * 后台退出
	 */
	@GetMapping(value = "/admin/user/logout")
	public R logoutA(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 用户密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/user/resetPass")
    public R resetPassZ(String username, HttpServletRequest request){
    	UsersEntity user = userService.selectOne(new EntityWrapper<UsersEntity>().eq("username", username));
    	if(user==null) {
    		return R.error("账号不存在");
    	}else if (user.getRole().equals("管理员")){
			return R.error("该账号为管理员账号，非用户账号！");
		}
    	user.setPassword("123456");
        userService.updateById(user);
        return R.ok("密码已重置为：123456");
    }

//	/**
//	 * 管理员密码重置（这个说实话也没看见按钮在哪，不管了哈哈哈哈
//	 */
//	@IgnoreAuth
//	@RequestMapping(value = "admin/user/resetPass")
//	public R resetPassA(String username, HttpServletRequest request){
//		UsersEntity user = userService.selectOne(new EntityWrapper<UsersEntity>().eq("username", username));
//		if(user==null) {
//			return R.error("账号不存在");
//		}
//		user.setPassword("123456");
//		userService.update(user,null);
//		return R.ok("密码已重置为：123456");
//	}
	


//	/**
//     * 列表（本来说给前台用的，但是好像用不着？）
//     */
//    @RequestMapping("/list")
//    public R list( UsersEntity user){
//       	EntityWrapper<UsersEntity> ew = new EntityWrapper<UsersEntity>();
//      	ew.allEq(MPUtil.allEQMapPre( user, "user"));
//        return R.ok().put("data", userService.selectListView(ew));
//    }

//    /**
//     * 信息（不知道有没有用到这个）
//	 * 根据ID获取用户信息
//     */
//    @RequestMapping("zhiguo/user/info/{id}")
//    public R infoZ(@PathVariable("id") String id){
//        UsersEntity user = userService.selectById(id);
//        return R.ok().put("data", user);
//    }




//	/**
//	 * 获取用户的session用户信息（在个人信息查看那使用/留言板等地方应该都有涉及）
//	 */
//	@RequestMapping("zhiguo/user/session")
//	public R getCurrUserZ(HttpServletRequest request){
//		Long id = (Long)request.getSession().getAttribute("userId");
//		UsersEntity user = userService.selectById(id);
//		return R.ok().put("data", user);
//	}

	/**
	 * 获取用户的session用户信息（在个人信息查看那使用/留言板等地方应该都有涉及）
	 */
	@RequestMapping("/user/getInfo")
	public R getCurrUserZ(HttpServletRequest request){
		System.out.println("into getInfo");
        //获取session
		HttpSession session = request.getSession();
		String token = (String)request.getHeader("token");
		EntityWrapper<TokenEntity> ew = new EntityWrapper<TokenEntity>();
		ew.eq("token", token);
		Long id = (Long)tokenService.selectOne(ew).getUserid();
		UsersEntity user = userService.selectById(id);
		return R.ok().put("data", user);
	}

    /**
     * 获取管理员的session用户信息（在个人信息查看那使用）
     */
    @RequestMapping("/admin/user/session")
    public R getCurrUserA(HttpServletRequest request){
		System.out.println("into getInfo");
		//获取session
		HttpSession session = request.getSession();
		String token = (String)request.getHeader("token");
		EntityWrapper<TokenEntity> ew = new EntityWrapper<TokenEntity>();
		ew.eq("token", token);
		Long id = (Long)tokenService.selectOne(ew).getUserid();
		UsersEntity user = userService.selectById(id);
		return R.ok().put("data", user);
    }

	/**
	 * 列表（只有后台可以看到用户管理的界面）
	 * 查询所有用户信息（这个应该不给看管理员的？？）
	 */
	@RequestMapping("/admin/user/getAll")
	public R page(@RequestParam Map<String, Object> params,UsersEntity user){
		EntityWrapper<UsersEntity> ew = new EntityWrapper<UsersEntity>();
		ew.ne("role", "管理员"); // 添加不等于"管理员"的条件
		PageUtils page = userService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, user), params), params));
		return R.ok().put("data", page);
	}

	/**
	 * 信息
	 * 后台根据ID获取用户信息（用户详情）
	 */
	@RequestMapping("/admin/user/info/{id}")
	public R infoA(@PathVariable("id") String id){
		UsersEntity user = userService.selectById(id);
		return R.ok().put("data", user);
	}

    /**
     * 增加用户/保存（后台用户管理：新增数据）
     */
    @PostMapping("/admin/user/add")
    public R save(@RequestBody UsersEntity user){
//    	ValidatorUtils.validateEntity(user);
    	if(userService.selectOne(new EntityWrapper<UsersEntity>().eq("username", user.getUsername())) !=null) {
    		return R.error("用户已存在");
    	}
		user.setRole("用户");
		user.setAddtime(new Date());
        userService.insert(user);
        return R.ok();
    }

	/**
	 * 用户删除(好像没有看见删除管理员数据的功能，这个算删除用户的数据）
	 * 用户端
	 */
	@RequestMapping("/admin/user/delete")
	public R delete(@RequestBody Long[] ids){
		for (Long id : ids) {
			UsersEntity user = userService.selectById(id);
			if (user != null && user.getRole().equals("管理员")) {
				return R.error("包含管理员id，不能删除！");
			}
		}
		userService.deleteBatchIds(Arrays.asList(ids));
		return R.ok();
	}

	/**
	 * 前台数据修改(修改当前登录用户数据)
	 */
	@RequestMapping("/user/update")
	public R updateZ(@RequestBody UsersEntity user,HttpServletRequest request){
//        ValidatorUtils.validateEntity(user);
		Long id = (Long)request.getSession().getAttribute("userId");
		user.setId(id);
		UsersEntity u = userService.selectOne(new EntityWrapper<UsersEntity>().eq("username", user.getUsername()));
		if(u!=null && u.getId()!=user.getId() && u.getUsername().equals(user.getUsername())) {
			return R.error("用户名已存在。");
		}
		userService.updateById(user);//全部更新
		return R.ok();
	}

    /**
     * 后台数据修改（包含管理员数据修改+用户数据修改）
     */
    @RequestMapping("/admin/user/update")
    public R updateA(@RequestBody UsersEntity user){
//        ValidatorUtils.validateEntity(user);
    	UsersEntity u = userService.selectOne(new EntityWrapper<UsersEntity>().eq("username", user.getUsername()));
    	if(u!=null && u.getId()!=user.getId() && u.getUsername().equals(user.getUsername())) {
    		return R.error("用户名已存在。");
    	}
        userService.updateById(user);//全部更新
        return R.ok();
    }
	/**
	 * 用户查询
	 */
	@RequestMapping("/admin/user/query")
	public R query(UsersEntity user){
		EntityWrapper< UsersEntity> ew = new EntityWrapper< UsersEntity>();
		ew.allEq(MPUtil.allEQMapPre( user, "user")).ne("role", "管理员");
		UsersView usersView =   userService.selectView(ew);
		return R.ok("查询用户成功").put("data", usersView);
	}


}
