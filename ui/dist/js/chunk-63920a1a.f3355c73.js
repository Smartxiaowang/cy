(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-63920a1a"],{"0c581":function(e,t,a){var i=a("346e"),o=/^\s+/;function r(e){return e?e.slice(0,i(e)+1).replace(o,""):e}e.exports=r},"1f89":function(e,t,a){var i=a("82c6"),o=function(){return i.Date.now()};e.exports=o},"346e":function(e,t){var a=/\s/;function i(e){var t=e.length;while(t--&&a.test(e.charAt(t)));return t}e.exports=i},aa15:function(e,t,a){var i=a("0c581"),o=a("139b"),r=a("d19b"),l=NaN,s=/^[-+]0x[0-9a-f]+$/i,n=/^0b[01]+$/i,u=/^0o[0-7]+$/i,c=parseInt;function d(e){if("number"==typeof e)return e;if(r(e))return l;if(o(e)){var t="function"==typeof e.valueOf?e.valueOf():e;e=o(t)?t+"":t}if("string"!=typeof e)return 0===e?e:+e;e=i(e);var a=n.test(e);return a||u.test(e)?c(e.slice(2),a?2:8):s.test(e)?l:+e}e.exports=d},c81a:function(e,t,a){var i=a("139b"),o=a("1f89"),r=a("aa15"),l="Expected a function",s=Math.max,n=Math.min;function u(e,t,a){var u,c,d,m,p,f,q=0,y=!1,g=!1,b=!0;if("function"!=typeof e)throw new TypeError(l);function $(t){var a=u,i=c;return u=c=void 0,q=t,m=e.apply(i,a),m}function v(e){return q=e,p=setTimeout(k,t),y?$(e):m}function h(e){var a=e-f,i=e-q,o=t-a;return g?n(o,d-i):o}function F(e){var a=e-f,i=e-q;return void 0===f||a>=t||a<0||g&&i>=d}function k(){var e=o();if(F(e))return x(e);p=setTimeout(k,h(e))}function x(e){return p=void 0,b&&u?$(e):(u=c=void 0,m)}function K(){void 0!==p&&clearTimeout(p),q=0,u=f=c=p=void 0}function S(){return void 0===p?m:x(o())}function A(){var e=o(),a=F(e);if(u=arguments,c=this,f=e,a){if(void 0===p)return v(f);if(g)return clearTimeout(p),p=setTimeout(k,t),$(f)}return void 0===p&&(p=setTimeout(k,t)),m}return t=r(t)||0,i(a)&&(y=!!a.leading,g="maxWait"in a,d=g?s(r(a.maxWait)||0,t):d,b="trailing"in a?!!a.trailing:b),A.cancel=K,A.flush=S,A}e.exports=u},d19b:function(e,t,a){var i=a("dc9b"),o=a("d92c"),r="[object Symbol]";function l(e){return"symbol"==typeof e||o(e)&&i(e)==r}e.exports=l},e80f:function(e,t,a){"use strict";a.r(t);var i=function(){var e=this,t=e._self._c;return t("el-dialog",{attrs:{visible:e.visible,title:e.$t("oss.config"),"close-on-click-modal":!1,"close-on-press-escape":!1},on:{"update:visible":function(t){e.visible=t}}},[t("el-form",{ref:"dataForm",attrs:{model:e.dataForm,rules:e.dataRule,"label-width":"120px"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.dataFormSubmitHandle()}}},[t("el-form-item",{attrs:{label:e.$t("oss.type"),size:"mini"}},[t("el-radio-group",{model:{value:e.dataForm.type,callback:function(t){e.$set(e.dataForm,"type",t)},expression:"dataForm.type"}},[t("el-radio",{attrs:{label:1}},[e._v(e._s(e.$t("oss.type1")))]),t("el-radio",{attrs:{label:2}},[e._v(e._s(e.$t("oss.type2")))]),t("el-radio",{attrs:{label:3}},[e._v(e._s(e.$t("oss.type3")))])],1)],1),1===e.dataForm.type?[t("el-form-item",{attrs:{size:"mini"}},[t("a",{attrs:{href:"https://s.qiniu.com/7Rfaym",target:"_blank"}},[e._v("免费申请(七牛)10GB储存空间")])]),t("el-form-item",{attrs:{prop:"qiniuDomain",label:e.$t("oss.qiniuDomain")}},[t("el-input",{attrs:{placeholder:e.$t("oss.qiniuDomainTips")},model:{value:e.dataForm.qiniuDomain,callback:function(t){e.$set(e.dataForm,"qiniuDomain",t)},expression:"dataForm.qiniuDomain"}})],1),t("el-form-item",{attrs:{prop:"qiniuPrefix",label:e.$t("oss.qiniuPrefix")}},[t("el-input",{attrs:{placeholder:e.$t("oss.qiniuPrefixTips")},model:{value:e.dataForm.qiniuPrefix,callback:function(t){e.$set(e.dataForm,"qiniuPrefix",t)},expression:"dataForm.qiniuPrefix"}})],1),t("el-form-item",{attrs:{prop:"qiniuAccessKey",label:e.$t("oss.qiniuAccessKey")}},[t("el-input",{attrs:{placeholder:e.$t("oss.qiniuAccessKeyTips")},model:{value:e.dataForm.qiniuAccessKey,callback:function(t){e.$set(e.dataForm,"qiniuAccessKey",t)},expression:"dataForm.qiniuAccessKey"}})],1),t("el-form-item",{attrs:{prop:"qiniuSecretKey",label:e.$t("oss.qiniuSecretKey")}},[t("el-input",{attrs:{placeholder:e.$t("oss.qiniuSecretKeyTips")},model:{value:e.dataForm.qiniuSecretKey,callback:function(t){e.$set(e.dataForm,"qiniuSecretKey",t)},expression:"dataForm.qiniuSecretKey"}})],1),t("el-form-item",{attrs:{prop:"qiniuBucketName",label:e.$t("oss.qiniuBucketName")}},[t("el-input",{attrs:{placeholder:e.$t("oss.qiniuBucketNameTips")},model:{value:e.dataForm.qiniuBucketName,callback:function(t){e.$set(e.dataForm,"qiniuBucketName",t)},expression:"dataForm.qiniuBucketName"}})],1)]:2===e.dataForm.type?[t("el-form-item",{attrs:{size:"mini"}},[t("a",{attrs:{href:"https://www.aliyun.com/minisite/goods?userCode=y93lfwbg",target:"_blank"}},[e._v("免费领取阿里云优惠券")])]),t("el-form-item",{attrs:{prop:"aliyunDomain",label:e.$t("oss.aliyunDomain")}},[t("el-input",{attrs:{placeholder:e.$t("oss.aliyunDomainTips")},model:{value:e.dataForm.aliyunDomain,callback:function(t){e.$set(e.dataForm,"aliyunDomain",t)},expression:"dataForm.aliyunDomain"}})],1),t("el-form-item",{attrs:{prop:"aliyunPrefix",label:e.$t("oss.aliyunPrefix")}},[t("el-input",{attrs:{placeholder:e.$t("oss.aliyunPrefixTips")},model:{value:e.dataForm.aliyunPrefix,callback:function(t){e.$set(e.dataForm,"aliyunPrefix",t)},expression:"dataForm.aliyunPrefix"}})],1),t("el-form-item",{attrs:{prop:"aliyunEndPoint",label:e.$t("oss.aliyunEndPoint")}},[t("el-input",{attrs:{placeholder:e.$t("oss.aliyunEndPointTips")},model:{value:e.dataForm.aliyunEndPoint,callback:function(t){e.$set(e.dataForm,"aliyunEndPoint",t)},expression:"dataForm.aliyunEndPoint"}})],1),t("el-form-item",{attrs:{prop:"aliyunAccessKeyId",label:e.$t("oss.aliyunAccessKeyId")}},[t("el-input",{attrs:{placeholder:e.$t("oss.aliyunAccessKeyIdTips")},model:{value:e.dataForm.aliyunAccessKeyId,callback:function(t){e.$set(e.dataForm,"aliyunAccessKeyId",t)},expression:"dataForm.aliyunAccessKeyId"}})],1),t("el-form-item",{attrs:{prop:"aliyunAccessKeySecret",label:e.$t("oss.aliyunAccessKeySecret")}},[t("el-input",{attrs:{placeholder:e.$t("oss.aliyunAccessKeySecretTips")},model:{value:e.dataForm.aliyunAccessKeySecret,callback:function(t){e.$set(e.dataForm,"aliyunAccessKeySecret",t)},expression:"dataForm.aliyunAccessKeySecret"}})],1),t("el-form-item",{attrs:{prop:"aliyunBucketName",label:e.$t("oss.aliyunBucketName")}},[t("el-input",{attrs:{placeholder:e.$t("oss.aliyunBucketNameTips")},model:{value:e.dataForm.aliyunBucketName,callback:function(t){e.$set(e.dataForm,"aliyunBucketName",t)},expression:"dataForm.aliyunBucketName"}})],1)]:3===e.dataForm.type?[t("el-form-item",{attrs:{size:"mini"}},[t("a",{attrs:{href:"https://curl.qcloud.com/zt3xdYbZ",target:"_blank"}},[e._v("免费领取腾讯云优惠券")])]),t("el-form-item",{attrs:{prop:"qcloudDomain",label:e.$t("oss.qcloudDomain")}},[t("el-input",{attrs:{placeholder:e.$t("oss.qcloudDomainTips")},model:{value:e.dataForm.qcloudDomain,callback:function(t){e.$set(e.dataForm,"qcloudDomain",t)},expression:"dataForm.qcloudDomain"}})],1),t("el-form-item",{attrs:{prop:"qcloudPrefix",label:e.$t("oss.qcloudPrefix")}},[t("el-input",{attrs:{placeholder:e.$t("oss.qcloudPrefixTips")},model:{value:e.dataForm.qcloudPrefix,callback:function(t){e.$set(e.dataForm,"qcloudPrefix",t)},expression:"dataForm.qcloudPrefix"}})],1),t("el-form-item",{attrs:{prop:"qcloudAppId",label:e.$t("oss.qcloudAppId")}},[t("el-input",{attrs:{placeholder:e.$t("oss.qcloudAppIdTips")},model:{value:e.dataForm.qcloudAppId,callback:function(t){e.$set(e.dataForm,"qcloudAppId",t)},expression:"dataForm.qcloudAppId"}})],1),t("el-form-item",{attrs:{prop:"qcloudSecretId",label:e.$t("oss.qcloudSecretId")}},[t("el-input",{attrs:{placeholder:e.$t("oss.qcloudSecretIdTips")},model:{value:e.dataForm.qcloudSecretId,callback:function(t){e.$set(e.dataForm,"qcloudSecretId",t)},expression:"dataForm.qcloudSecretId"}})],1),t("el-form-item",{attrs:{prop:"qcloudSecretKey",label:e.$t("oss.qcloudSecretKey")}},[t("el-input",{attrs:{placeholder:e.$t("oss.qcloudSecretKeyTips")},model:{value:e.dataForm.qcloudSecretKey,callback:function(t){e.$set(e.dataForm,"qcloudSecretKey",t)},expression:"dataForm.qcloudSecretKey"}})],1),t("el-form-item",{attrs:{prop:"qcloudBucketName",label:e.$t("oss.qcloudBucketName")}},[t("el-input",{attrs:{placeholder:e.$t("oss.qcloudBucketNameTips")},model:{value:e.dataForm.qcloudBucketName,callback:function(t){e.$set(e.dataForm,"qcloudBucketName",t)},expression:"dataForm.qcloudBucketName"}})],1),t("el-form-item",{attrs:{prop:"qcloudRegion",label:e.$t("oss.qcloudRegion")}},[t("el-select",{staticClass:"w-percent-100",attrs:{clearable:"",placeholder:e.$t("oss.qcloudRegionTips")},model:{value:e.dataForm.qcloudRegion,callback:function(t){e.$set(e.dataForm,"qcloudRegion",t)},expression:"dataForm.qcloudRegion"}},[t("el-option",{attrs:{value:"ap-beijing-1",label:e.$t("oss.qcloudRegionBeijing1")}}),t("el-option",{attrs:{value:"ap-beijing",label:e.$t("oss.qcloudRegionBeijing")}}),t("el-option",{attrs:{value:"ap-shanghai",label:e.$t("oss.qcloudRegionShanghai")}}),t("el-option",{attrs:{value:"ap-guangzhou",label:e.$t("oss.qcloudRegionGuangzhou")}}),t("el-option",{attrs:{value:"ap-chengdu",label:e.$t("oss.qcloudRegionChengdu")}}),t("el-option",{attrs:{value:"ap-chongqing",label:e.$t("oss.qcloudRegionChongqing")}}),t("el-option",{attrs:{value:"ap-singapore",label:e.$t("oss.qcloudRegionSingapore")}}),t("el-option",{attrs:{value:"ap-hongkong",label:e.$t("oss.qcloudRegionHongkong")}}),t("el-option",{attrs:{value:"na-toronto",label:e.$t("oss.qcloudRegionToronto")}}),t("el-option",{attrs:{value:"eu-frankfurt",label:e.$t("oss.qcloudRegionFrankfurt")}})],1)],1)]:e._e()],2),t("template",{slot:"footer"},[t("el-button",{on:{click:function(t){e.visible=!1}}},[e._v(e._s(e.$t("cancel")))]),t("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.dataFormSubmitHandle()}}},[e._v(e._s(e.$t("confirm")))])],1)],2)},o=[],r=a("c81a"),l=a.n(r),s={data:function(){return{visible:!1,dataForm:{type:0,qiniuDomain:"",qiniuPrefix:"",qiniuAccessKey:"",qiniuSecretKey:"",qiniuBucketName:"",aliyunDomain:"",aliyunPrefix:"",aliyunEndPoint:"",aliyunAccessKeyId:"",aliyunAccessKeySecret:"",aliyunBucketName:"",qcloudDomain:"",qcloudPrefix:"",qcloudAppId:0,qcloudSecretId:"",qcloudSecretKey:"",qcloudBucketName:"",qcloudRegion:""}}},computed:{dataRule:function(){return{qiniuDomain:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}],qiniuAccessKey:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}],qiniuSecretKey:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}],qiniuBucketName:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}],aliyunDomain:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}],aliyunEndPoint:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}],aliyunAccessKeyId:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}],aliyunAccessKeySecret:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}],aliyunBucketName:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}],qcloudDomain:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}],qcloudAppId:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}],qcloudSecretId:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}],qcloudSecretKey:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}],qcloudBucketName:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}],qcloudRegion:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}]}}},watch:{"dataForm.type":function(e){this.$refs["dataForm"].clearValidate()}},methods:{init:function(){var e=this;this.visible=!0,this.$nextTick((function(){e.$refs["dataForm"].resetFields(),e.getInfo()}))},getInfo:function(){var e=this;this.$http.get("/sys/oss/info").then((function(t){var a=t.data;if(0!==a.code)return e.$message.error(a.msg);e.dataForm=a.data})).catch((function(){}))},dataFormSubmitHandle:l()((function(){var e=this;this.$refs["dataForm"].validate((function(t){if(!t)return!1;e.$http.post("/sys/oss",e.dataForm).then((function(t){var a=t.data;if(0!==a.code)return e.$message.error(a.msg);e.$message({message:e.$t("prompt.success"),type:"success",duration:500,onClose:function(){e.visible=!1,e.$emit("refreshDataList")}})})).catch((function(){}))}))}),1e3,{leading:!0,trailing:!1})}},n=s,u=a("0b56"),c=Object(u["a"])(n,i,o,!1,null,null,null);t["default"]=c.exports}}]);