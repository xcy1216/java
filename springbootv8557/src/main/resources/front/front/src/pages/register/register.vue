<template>
	<div>

	<div class="container" :style='{"minHeight":"100vh","alignItems":"center","backgroundImage":"url(http://codegen.caihongy.cn/20210103/532242aadc9a4ee2840d8b9d24fbe75f.jpg)","display":"flex","width":"100%","backgroundSize":"100% 100%","backgroundPosition":"center center","backgroundRepeat":"no-repeat","justifyContent":"center"}'>
		<el-form class='rgs-form' v-if="pageFlag=='register'" :style='{"padding":"20px","borderRadius":"10px","left":"565px","background":"#fff","width":"750px","position":"absolute","height":"auto"}' ref="registerForm" :model="registerForm" :rules="rules">
			<div v-if="true" :style='{"margin":"0 0 10px 0","color":"rgba(64, 158, 255, 1)","textAlign":"center","width":"100%","lineHeight":"44px","fontSize":"20px","textShadow":"4px 4px 2px rgba(64, 158, 255, .5)"}'>USER / REGISTER</div>
			<div v-if="true" :style='{"width":"100%","margin":"30px 0 20px 0","lineHeight":"44px","fontSize":"36px","color":"#000","textAlign":"center"}'>智果易联</p></div>
			<!-- <el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='shanghu'" prop="shangjiahao">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>商家号：</div>
				<el-input v-model="registerForm.shangjiahao"  placeholder="请输入商家号" />
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='shanghu'" prop="password">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>密码：</div>
				<el-input v-model="registerForm.password" type="password" placeholder="请输入密码" />
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='shanghu'" prop="password">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>确认密码：</div>
				<el-input v-model="registerForm.password2" type="password" placeholder="请再次输入密码" />
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='shanghu'" prop="shangjiamingcheng">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>商家名称：</div>
				<el-input v-model="registerForm.shangjiamingcheng"  placeholder="请输入商家名称" />
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='shanghu'" prop="dianhua">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>电话：</div>
				<el-input v-model="registerForm.dianhua"  placeholder="请输入电话" />
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='shanghu'" prop="youxiang">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>邮箱：</div>
				<el-input v-model="registerForm.youxiang"  placeholder="请输入邮箱" />
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='shanghu'" prop="dizhi">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>地址：</div>
				<el-input v-model="registerForm.dizhi"  placeholder="请输入地址" />
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='shanghu'" prop="yingyezhizhao">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>营业执照：</div>
                <file-upload
					tip="点击上传营业执照"
					action="file/upload"
					:limit="1"
					:multiple="true"
					:fileUrls="registerForm.yingyezhizhao?registerForm.yingyezhizhao:''"
					@change="shanghuyingyezhizhaoUploadChange"
				></file-upload>
			</el-form-item>
			<el-form-item v-if="tableName=='shanghu'" class="el-email" :style='{"width":"80%","margin":"0 auto 24px"}'>
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>手机号：</div>
				<div style="display: flex;flex: 1;">
					<input v-model="registerForm.mobile" :style='{"padding":"0 12px","margin":"0","outline":"none","borderColor":"rgba(204, 204, 204, 1)","color":"rgba(153, 153, 153, 1)","borderRadius":"6px 0 0 6px","background":"#fff","borderWidth":"1px 0 1px 1px","width":"calc(100% - 80px)","fontSize":"14px","borderStyle":" solid","height":"40px"}' type="text" id="mobile" name="mobile" placeholder="请输入手机号" autocomplete="off" class="el-input">
					<button v-if="isEndFlag" :style='{"cursor":"pointer","outline":"none","borderColor":"rgba(204, 204, 204, 1)","color":"#666","borderRadius":"0 6px 6px 0","background":"#f5f5f5","borderWidth":"1px 1px 1px 0","width":"80px","fontSize":"12px","borderStyle":"solid","height":"40px"}' @click="sendsmscode" id="sendsmscode" type="button" class="el-btn">发送验证码</button>
					<button v-if="!isEndFlag" disabled="disabled" :style='{"cursor":"pointer","outline":"none","borderColor":"rgba(204, 204, 204, 1)","color":"#666","borderRadius":"0 6px 6px 0","background":"#f5f5f5","borderWidth":"1px 1px 1px 0","width":"80px","fontSize":"12px","borderStyle":"solid","height":"40px"}' type="button" class="el-btn">{{count}}秒后重发</button>
				</div>
			</el-form-item>
			<el-form-item v-if="tableName=='shanghu'" class="el-email" :style='{"width":"80%","margin":"0 auto 24px"}'>
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>验证码：</div>
				<el-input v-model="smscode" type="text" id="smscode" name="smscode" placeholder="请输入验证码" autocomplete="off" class="el-input" />
			</el-form-item> -->
			<el-form-item v-if="true" class="el-email" :style='{"width":"80%","margin":"0 auto 24px"}'>
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>手机号：</div>
				<div style="display: flex;flex: 1;">
					<el-input v-model="registerForm.phonenumber"  placeholder="请输入手机号" />
				</div>
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="true" prop="password">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>密码：</div>
				<el-input v-model="registerForm.password" type="password" placeholder="请输入密码" />
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="true" prop="password">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>确认密码：</div>
				<el-input v-model="registerForm.password2" type="password" placeholder="请再次输入密码" />
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="true" prop="username">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>昵称：</div>
				<el-input v-model="registerForm.username"  placeholder="请输入昵称" />
			</el-form-item>
			<!-- <el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='nonghu'" prop="dianziyouxiang">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>电子邮箱：</div>
				<el-input v-model="registerForm.dianziyouxiang"  placeholder="请输入电子邮箱" />
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='nonghu'" prop="dizhi">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>地址：</div>
				<el-input v-model="registerForm.dizhi"  placeholder="请输入地址" />
			</el-form-item>
			<el-form-item v-if="tableName=='nonghu'" class="el-email" :style='{"width":"80%","margin":"0 auto 24px"}'>
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>手机号：</div>
				<div style="display: flex;flex: 1;">
					<input v-model="registerForm.mobile" :style='{"padding":"0 12px","margin":"0","outline":"none","borderColor":"rgba(204, 204, 204, 1)","color":"rgba(153, 153, 153, 1)","borderRadius":"6px 0 0 6px","background":"#fff","borderWidth":"1px 0 1px 1px","width":"calc(100% - 80px)","fontSize":"14px","borderStyle":" solid","height":"40px"}' type="text" id="mobile" name="mobile" placeholder="请输入手机号" autocomplete="off" class="el-input">
					<button v-if="isEndFlag" :style='{"cursor":"pointer","outline":"none","borderColor":"rgba(204, 204, 204, 1)","color":"#666","borderRadius":"0 6px 6px 0","background":"#f5f5f5","borderWidth":"1px 1px 1px 0","width":"80px","fontSize":"12px","borderStyle":"solid","height":"40px"}' @click="sendsmscode" id="sendsmscode" type="button" class="el-btn">发送验证码</button>
					<button v-if="!isEndFlag" disabled="disabled" :style='{"cursor":"pointer","outline":"none","borderColor":"rgba(204, 204, 204, 1)","color":"#666","borderRadius":"0 6px 6px 0","background":"#f5f5f5","borderWidth":"1px 1px 1px 0","width":"80px","fontSize":"12px","borderStyle":"solid","height":"40px"}' type="button" class="el-btn">{{count}}秒后重发</button>
				</div>
			</el-form-item>
			<el-form-item v-if="tableName=='nonghu'" class="el-email" :style='{"width":"80%","margin":"0 auto 24px"}'>
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>验证码：</div>
				<el-input v-model="smscode" type="text" id="smscode" name="smscode" placeholder="请输入验证码" autocomplete="off" class="el-input" />
			</el-form-item> -->
			<el-button :style='{"border":"none","cursor":"pointer","padding":"0 10px","margin":"20px auto 5px","outline":"none","color":"#fff","borderRadius":"8px","background":"rgba(0, 0, 0, 1)","display":"block","width":"80%","fontSize":"16px","height":"40px"}' type="primary" @click="submitForm('registerForm')">注册</el-button>
			<el-button :style='{"cursor":"pointer","border":"1px solid rgba(204, 204, 204, 1)","padding":"0 10px","margin":"20px auto 5px","outline":"none","color":"#000","borderRadius":"8px","background":"none","display":"block","width":"80%","fontSize":"16px","height":"40px"}' @click="resetForm('registerForm')">重置</el-button>
			<router-link :style='{"cursor":"pointer","padding":"0 10%","color":"rgba(40, 40, 40, 1)","display":"inline-block","lineHeight":"1.5","fontSize":"12px","textDecoration":"none"}' to="/login">已有账户登录</router-link>
		</el-form>
    </div>
  </div>
</div>
</template>

<script>

export default {
    //数据集合
    data() {
		return {
            pageFlag : '',
			tableName: '',
			registerForm: {
            },
			rules: {},
            smscode:'',
			//倒计时60s
			count: 60,
			//倒计时定时器
			inter: null,
			//倒计结束标识
			isEndFlag: true,
		}
    },
	mounted() {
	},
    created() {
      this.pageFlag = this.$route.query.pageFlag;
    //   this.tableName = this.$route.query.role;
    //   if ('shanghu' == this.tableName) {
    //     this.rules.dianhua = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }];
    //   }
    //   if ('shanghu' == this.tableName) {
    //     this.rules.youxiang = [{ required: true, validator: this.$validate.isEmail, trigger: 'blur' }];
    //   }
    //   if ('shanghu' == this.tableName) {
    //     this.rules.mobile = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }];
    //   }
    //   if ('nonghu' == this.tableName) {
    //     this.rules.nonghuhao = [{ required: true, message: '请输入农户号', trigger: 'blur' }];
    //   }
    //   if ('nonghu' == this.tableName) {
    //     this.rules.password = [{ required: true, message: '请输入密码', trigger: 'blur' }];
    //   }
    //   if ('nonghu' == this.tableName) {
    //     this.rules.nonghuming = [{ required: true, message: '请输入农户名', trigger: 'blur' }];
    //   }
    //   if ('nonghu' == this.tableName) {
    //     this.rules.dianziyouxiang = [{ required: true, validator: this.$validate.isEmail, trigger: 'blur' }];
    //   }
    //   if ('nonghu' == this.tableName) {
    //     this.rules.mobile = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }];
    //   }
    },
    //方法集合
    methods: {
      // 获取uuid
      getUUID () {
        return new Date().getTime();
      },
        // 下二随
      shanghuyingyezhizhaoUploadChange(fileUrls) {
          this.registerForm.yingyezhizhao = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
      },

        // 多级联动参数

        // sendsmscode() {
        //     if(!this.registerForm.mobile){
        //       this.$message.error(`手机号码不能为空`);
        //       return
        //     }
        //     let mobileF = this.$validate.isMobile2(this.registerForm.mobile);
        //     if(this.registerForm.mobile&&(!this.$validate.isMobile2(this.registerForm.mobile))){
        //       this.$message.error(`请输入正确的手机格式`);
        //       return
        //     }
        //     this.isEndFlag = false;
        //     var _this = this;
        //     this.inter = window.setInterval(function() {
        //         _this.count = _this.count - 1;
        //         if (_this.count <= 0) {
        //           window.clearInterval(_this.inter);
        //           _this.isEndFlag = true;
        //           _this.count=60;
        //         }
        //     }, 1000);
        //     this.$http({
        //       url: `${this.tableName}/sendsms?mobile=`+this.registerForm.mobile,
        //       method: "get",
        //       params: {}
        //     }).then(({ data }) => {
        //       if (data && data.code === 0) {
        //           this.$message.success(`发送成功`);
        //       } else {
        //           this.$message.error(`服务器异常，请稍后重试`)
        //       }
        //     });
        // },

      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // var url=this.tableName+"/register";
            //   if(`shanghu` == this.tableName ){
            //     url=this.tableName+"/register?smscode="+this.smscode;
                if(this.registerForm.phonenumber&&(!this.$validate.isMobile2(this.registerForm.phonenumber))){
                        this.$message.error(`请输入正确的手机格式`);
                        return
                }
            //   }
            //   if((!this.smscode) && `shanghu` == this.tableName){
            //     this.$message.error(`验证码不能为空`);
            //     return
            //   }
               if(this.registerForm.password!=this.registerForm.password2) {
                this.$message.error(`两次密码输入不一致`);
                return
               }
            //   if(`nonghu` == this.tableName ){
            //     url=this.tableName+"/register";
            //     if(this.registerForm.mobile&&(!this.$validate.isMobile2(this.registerForm.mobile))){
            //             this.$message.error(`请输入正确的手机格式`);
            //             return
            //     }
            //   }
            //   if((!this.smscode) && `nonghu` == this.tableName){
            //     this.$message.error(`验证码不能为空`);
            //     return
            //   }
            // if(`nonghu` == this.tableName && this.registerForm.password!=this.registerForm.password2) {
			// 	this.$message.error(`两次密码输入不一致`);
			// 	return
            // }
            this.$http.post(`user/register`, this.registerForm).then(res => {
              if (res.body.code === 0) {
                this.$message({
                  message: '注册成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.$router.push('/login');
                  }
                });
              } else {
                this.$message.error(res.data.msg);
              }
            });
          } else {
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.container {
		position: relative;

		.el-date-editor.el-input {
			width: 100%;
		}
		
		.rgs-form .el-input /deep/ .el-input__inner {
						border: 1px solid rgba(204, 204, 204, 1);
						border-radius: 8px;
						padding: 0 10px;
						outline: none;
						color: rgba(153, 153, 153, 1);
						width: 100%;
						font-size: 14px;
						height: 40px;
					}
		
		.rgs-form .el-select /deep/ .el-input__inner {
						border: 1px solid rgba(204, 204, 204, 1);
						border-radius: 8px;
						padding: 0 10px;
						outline: none;
						color:  rgba(153, 153, 153, 1);
						width: 288px;
						font-size: 14px;
						height: 40px;
					}
		
		.rgs-form .el-date-editor /deep/ .el-input__inner {
						border: 1px solid rgba(204, 204, 204, 1);
						border-radius: 8px;
						padding: 0 10px 0 30px;
						outline: none;
						color: rgba(153, 153, 153, 1);
						width: 288px;
						font-size: 14px;
						height: 40px;
					}
		
		.rgs-form .el-date-editor /deep/ .el-input__inner {
						border: 1px solid rgba(204, 204, 204, 1);
						border-radius: 8px;
						padding: 0 10px 0 30px;
						outline: none;
						color: rgba(153, 153, 153, 1);
						width: 288px;
						font-size: 14px;
						height: 40px;
					}
		
		.rgs-form /deep/ .el-upload--picture-card {
			background: transparent;
			border: 0;
			border-radius: 0;
			width: auto;
			height: auto;
			line-height: initial;
			vertical-align: middle;
		}
		
		.rgs-form /deep/ .upload .upload-img {
		  		  border: 1px solid rgba(204, 204, 204, 1);
		  		  cursor: pointer;
		  		  border-radius: 8px;
		  		  color: rgba(153, 153, 153, 1);
		  		  width: 160px;
		  		  font-size: 32px;
		  		  line-height: 100px;
		  		  text-align: center;
		  		  height: 100px;
		  		}
		
		.rgs-form /deep/ .el-upload-list .el-upload-list__item {
		  		  border: 1px solid rgba(204, 204, 204, 1);
		  		  cursor: pointer;
		  		  border-radius: 8px;
		  		  color: rgba(153, 153, 153, 1);
		  		  width: 160px;
		  		  font-size: 32px;
		  		  line-height: 100px;
		  		  text-align: center;
		  		  height: 100px;
		  		}
		
		.rgs-form /deep/ .el-upload .el-icon-plus {
		  		  border: 1px solid rgba(204, 204, 204, 1);
		  		  cursor: pointer;
		  		  border-radius: 8px;
		  		  color: rgba(153, 153, 153, 1);
		  		  width: 160px;
		  		  font-size: 32px;
		  		  line-height: 100px;
		  		  text-align: center;
		  		  height: 100px;
		  		}
	}
</style>
