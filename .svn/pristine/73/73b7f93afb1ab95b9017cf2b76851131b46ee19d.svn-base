$.extend($.fn.validatebox.defaults.rules, {
	//身份证号码
	idCode:{
          validator:function(value,param){
            return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
          },
          message: '请输入正确的身份证号'
        },
    //输入框非空
	notNull: {
	    validator:function(value,param){
	        if(value.length>0){
	            return true;
	        }
	    },
	    message: '不能为空'
	},
	// 验证数字
   numberCheckSub : {
    validator : function(value) {
    return /^[0-9]+$/.test(value);},
    message : "只能输入数字"
   },
    //手机号或者座机号码
    mobileAndTel: {
        validator: function (value, param) {
          return /(^([0\+]\d{2,3})\d{3,4}\-\d{3,8}$)|(^([0\+]\d{2,3})\d{3,4}\d{3,8}$)|(^([0\+]\d{2,3}){0,1}13\d{9}$)|(^\d{3,4}\d{3,8}$)|(^\d{3,4}\-\d{3,8}$)/.test(value);
        },
        message: '请正确输入电话号码'
      },
	//密码两次输入对比
	  equals: {
	      validator: function(value,param){
	          return value == $(param[0]).val();
	      },
	      message: '密码不一致'
	  }

});