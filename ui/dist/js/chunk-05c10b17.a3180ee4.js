(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-05c10b17","chunk-281a1000"],{"0c581":function(t,e,a){var i=a("346e"),n=/^\s+/;function r(t){return t?t.slice(0,i(t)+1).replace(n,""):t}t.exports=r},"1f89":function(t,e,a){var i=a("82c6"),n=function(){return i.Date.now()};t.exports=n},"346e":function(t,e){var a=/\s/;function i(t){var e=t.length;while(e--&&a.test(t.charAt(e)));return e}t.exports=i},aa15:function(t,e,a){var i=a("0c581"),n=a("139b"),r=a("d19b"),o=NaN,s=/^[-+]0x[0-9a-f]+$/i,d=/^0b[01]+$/i,l=/^0o[0-7]+$/i,c=parseInt;function u(t){if("number"==typeof t)return t;if(r(t))return o;if(n(t)){var e="function"==typeof t.valueOf?t.valueOf():t;t=n(e)?e+"":e}if("string"!=typeof t)return 0===t?t:+t;t=i(t);var a=d.test(t);return a||l.test(t)?c(t.slice(2),a?2:8):s.test(t)?o:+t}t.exports=u},b58a:function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t._self._c;return e("el-dialog",{attrs:{visible:t.visible,title:t.dataForm.id?t.$t("update"):t.$t("add"),"close-on-click-modal":!1,"close-on-press-escape":!1},on:{"update:visible":function(e){t.visible=e}}},[e("el-form",{ref:"dataForm",attrs:{model:t.dataForm,rules:t.dataRule,"label-width":"120px"},nativeOn:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.dataFormSubmitHandle()}}},[e("el-form-item",{attrs:{prop:"departure",label:t.$t("jc.departure")}},[e("el-input",{attrs:{placeholder:t.$t("jc.departure")},model:{value:t.dataForm.departure,callback:function(e){t.$set(t.dataForm,"departure",e)},expression:"dataForm.departure"}})],1),e("el-form-item",{attrs:{prop:"destination",label:t.$t("jc.destination")}},[e("el-input",{attrs:{placeholder:t.$t("jc.destination")},model:{value:t.dataForm.destination,callback:function(e){t.$set(t.dataForm,"destination",e)},expression:"dataForm.destination"}})],1),e("el-form-item",{attrs:{prop:"departuretime",label:t.$t("jc.departuretime")}},[e("el-date-picker",{attrs:{type:"date",placeholder:"选择日期","value-format":"yyyy-MM-dd"},model:{value:t.dataForm.departuretime,callback:function(e){t.$set(t.dataForm,"departuretime",e)},expression:"dataForm.departuretime"}})],1),e("el-form-item",{attrs:{prop:"destinationtime",label:t.$t("jc.destinationtime")}},[e("el-date-picker",{attrs:{type:"date",placeholder:"选择日期","value-format":"yyyy-MM-dd"},model:{value:t.dataForm.destinationtime,callback:function(e){t.$set(t.dataForm,"destinationtime",e)},expression:"dataForm.destinationtime"}})],1),e("el-form-item",{attrs:{prop:"remake",label:t.$t("bt.remake")}},[e("el-input",{attrs:{placeholder:t.$t("bt.remake")},model:{value:t.dataForm.remake,callback:function(e){t.$set(t.dataForm,"remake",e)},expression:"dataForm.remake"}})],1)],1),e("template",{slot:"footer"},[e("el-button",{on:{click:function(e){t.visible=!1}}},[t._v(t._s(t.$t("cancel")))]),e("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.dataFormSubmitHandle()}}},[t._v(t._s(t.$t("confirm")))])],1)],2)},n=[],r=a("8ee2"),o=a("c81a"),s=a.n(o),d={data:function(){return{pickerOptions0:{disabledDate:function(t){return t.getTime()>Date.now()-864e4}},visible:!1,dataForm:{id:"",departure:"",destination:"",departuretime:"",destinationtime:"",audit:"",createDate:"",remake:""}}},computed:{dataRule:function(){return{paramCode:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}],paramValue:[{required:!0,message:this.$t("validate.required"),trigger:"blur"}]}}},methods:{init:function(){var t=this;this.visible=!0,this.$nextTick((function(){t.$refs["dataForm"].resetFields(),t.dataForm.id&&t.getInfo()}))},getInfo:function(){var t=this;this.$http.get("/getRegistrationList?id=".concat(this.dataForm.id)).then((function(e){var a=e.data;if(0!==a.code)return console.log(a.message),t.$message.error(a.message);t.dataForm=Object(r["a"])(Object(r["a"])({},t.dataForm),a.data.list[0])})).catch((function(){}))},dataFormSubmitHandle:s()((function(){var t=this;this.$refs["dataForm"].validate((function(e){if(!e)return!1;t.dataForm.id?t.$http[(t.dataForm.id,"post")]("/updRegistrationInfo",t.dataForm).then((function(e){var a=e.data;if(0!==a.code)return t.$message.error(a.message);t.$message({message:t.$t("prompt.success"),type:"success",duration:500,onClose:function(){t.visible=!1,t.$emit("refreshDataList")}})})).catch((function(){})):t.$http[(t.dataForm.id,"post")]("/saveRegistrationInfo",t.dataForm).then((function(e){var a=e.data;if(0!==a.code)return t.$message.error(a.message);t.$message({message:t.$t("prompt.success"),type:"success",duration:500,onClose:function(){t.visible=!1,t.$emit("refreshDataList")}})})).catch((function(){}))}))}),1e3,{leading:!0,trailing:!1})}},l=d,c=a("0b56"),u=Object(c["a"])(l,i,n,!1,null,null,null);e["default"]=u.exports},c81a:function(t,e,a){var i=a("139b"),n=a("1f89"),r=a("aa15"),o="Expected a function",s=Math.max,d=Math.min;function l(t,e,a){var l,c,u,p,m,f,h=0,g=!1,b=!1,v=!0;if("function"!=typeof t)throw new TypeError(o);function $(e){var a=l,i=c;return l=c=void 0,h=e,p=t.apply(i,a),p}function L(t){return h=t,m=setTimeout(k,e),g?$(t):p}function y(t){var a=t-f,i=t-h,n=e-a;return b?d(n,u-i):n}function x(t){var a=t-f,i=t-h;return void 0===f||a>=e||a<0||b&&i>=u}function k(){var t=n();if(x(t))return F(t);m=setTimeout(k,y(t))}function F(t){return m=void 0,v&&l?$(t):(l=c=void 0,p)}function w(){void 0!==m&&clearTimeout(m),h=0,l=f=c=m=void 0}function O(){return void 0===m?p:F(n())}function _(){var t=n(),a=x(t);if(l=arguments,c=this,f=t,a){if(void 0===m)return L(f);if(b)return clearTimeout(m),m=setTimeout(k,e),$(f)}return void 0===m&&(m=setTimeout(k,e)),p}return e=r(e)||0,i(a)&&(g=!!a.leading,b="maxWait"in a,u=b?s(r(a.maxWait)||0,e):u,v="trailing"in a?!!a.trailing:v),_.cancel=w,_.flush=O,_}t.exports=l},c912:function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t._self._c;return e("el-card",{staticClass:"aui-card--fill",attrs:{shadow:"never"}},[e("div",{staticClass:"mod-sys__params"},[e("el-form",{attrs:{inline:!0,model:t.dataForm},nativeOn:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.getDataList()}}},[e("el-form-item",[e("el-date-picker",{attrs:{type:"date",placeholder:"选择日期","picker-options":t.pickerOptions0},model:{value:t.dataForm.date,callback:function(e){t.$set(t.dataForm,"date",e)},expression:"dataForm.date"}})],1),e("el-form-item",[e("el-button",{on:{click:function(e){return t.getDataList()}}},[t._v(t._s(t.$t("query")))])],1),e("el-form-item",[e("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.addOrUpdateHandle()}}},[t._v(t._s(t.$t("add")))])],1)],1),e("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.dataListLoading,expression:"dataListLoading"}],staticStyle:{width:"100%"},attrs:{data:t.dataList,border:""},on:{"selection-change":t.dataListSelectionChangeHandle}},[e("el-table-column",{attrs:{type:"selection","header-align":"center",align:"center",width:"50"}}),e("el-table-column",{attrs:{prop:"id",label:t.$t("jc.id"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"departure",label:t.$t("jc.departure"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"destination",label:t.$t("jc.destination"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"departuretime",label:t.$t("jc.departuretime"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"destinationtime",label:t.$t("jc.destinationtime"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"audit",label:t.$t("jc.audit"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"remake",label:t.$t("jc.remake"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"createDate",label:t.$t("jc.createdate"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{label:t.$t("handle"),fixed:"right","header-align":"center",align:"center",width:"150"},scopedSlots:t._u([{key:"default",fn:function(a){return[e("el-button",{attrs:{type:"text",size:"small"},on:{click:function(e){return t.addOrUpdateHandle(a.row.id)}}},[t._v(t._s(t.$t("update")))])]}}])})],1),e("el-pagination",{attrs:{"current-page":t.page,"page-sizes":[10,20,50,100],"page-size":t.limit,total:t.total,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":t.pageSizeChangeHandle,"current-change":t.pageCurrentChangeHandle}}),t.addOrUpdateVisible?e("add-or-update",{ref:"addOrUpdate",on:{refreshDataList:t.getDataList}}):t._e()],1)])},n=[],r=(a("c5cb"),a("08c7"),a("54f8"),a("4e3f"),a("e1a5")),o=a("b58a"),s={mixins:[r["a"]],data:function(){return{pickerOptions0:{disabledDate:function(t){return t.getTime()>Date.now()-864e4}},mixinViewModuleOptions:{getDataListURL:"/getRegistrationList/",getDataListIsPage:!0,deleteURL:"/delRegistrationInfo",deleteIsBatch:!0},dataForm:{date:""},selectData:[],selectWindow:!1}},components:{AddOrUpdate:o["default"]},methods:{exportExcel:function(){var t=[];if(!(this.dataListSelections.length>0))return this.$message.error("请选择要导出的内容！"),!1;t=this.dataListSelections,this.exportList(t)},exportList:function(t){var e=[["序号","姓名","所属","出发地","目的地","出行时间","回来时间","审核情况","备注","登记时间"]];t.forEach((function(t,a){var i=[];i=[a+1,t.name,t.depart,t.departure,t.destination,t.departuretime,t.destinationtime,t.audit,t.remake,t.createDate],e.push(i)}));var a=this.XLSX.utils.aoa_to_sheet(e),i=this.XLSX.utils.book_new();this.XLSX.utils.book_append_sheet(i,a,"行程报备表"),this.XLSX.writeFile(i,"行程报备表.xlsx")}}},d=s,l=a("0b56"),c=Object(l["a"])(d,i,n,!1,null,null,null);e["default"]=c.exports},d19b:function(t,e,a){var i=a("dc9b"),n=a("d92c"),r="[object Symbol]";function o(t){return"symbol"==typeof t||n(t)&&i(t)==r}t.exports=o},e1a5:function(t,e,a){"use strict";var i=a("8ee2"),n=(a("aa48"),a("3446"),a("cd5d"),a("c5cb"),a("54f8"),a("4e3f"),a("6e84"),a("075f"),a("e04f")),r=a.n(n),o=a("34d3"),s=a.n(o);e["a"]={provide:function(){return{reload:this.reload}},data:function(){return{isShow:!0,mixinViewModuleOptions:{createdIsNeed:!0,activatedIsNeed:!1,getDataListURL:"",getDataListIsPage:!1,deleteURL:"",deleteIsBatch:!1,deleteIsBatchKey:"id",exportURL:""},dataForm:{},dataList:[],order:"",orderField:"",page:1,limit:10,total:0,dataListLoading:!1,dataListSelections:[],addOrUpdateVisible:!1}},created:function(){this.mixinViewModuleOptions.createdIsNeed&&this.query()},activated:function(){this.mixinViewModuleOptions.activatedIsNeed&&this.query()},methods:{reload:function(){this.query()},query:function(){var t=this;this.dataListLoading=!0,this.$http.get(this.mixinViewModuleOptions.getDataListURL,{params:Object(i["a"])({order:this.order,orderField:this.orderField,page:this.mixinViewModuleOptions.getDataListIsPage?this.page:null,limit:this.mixinViewModuleOptions.getDataListIsPage?this.limit:null},this.dataForm)}).then((function(e){var a=e.data;if(t.dataListLoading=!1,0!==a.code)return t.dataList=[],t.total=0,t.$message.error(a.msg);t.dataList=t.mixinViewModuleOptions.getDataListIsPage?a.data.list:a.data,t.total=t.mixinViewModuleOptions.getDataListIsPage?a.data.total:0})).catch((function(){t.dataListLoading=!1}))},dataListSelectionChangeHandle:function(t){this.dataListSelections=t},dataListSortChangeHandle:function(t){if(!t.order||!t.prop)return this.order="",this.orderField="",!1;this.order=t.order.replace(/ending$/,""),this.orderField=t.prop.replace(/([A-Z])/g,"_$1").toLowerCase(),this.query()},pageSizeChangeHandle:function(t){this.page=1,this.limit=t,this.query()},pageCurrentChangeHandle:function(t){this.page=t,this.query()},getDataList:function(){this.page=1,this.query()},addOrUpdateHandle:function(t){var e=this;this.addOrUpdateVisible=!0,this.$nextTick((function(){e.$refs.addOrUpdate.dataForm.id=t,e.$refs.addOrUpdate.init()}))},closeCurrentTab:function(t){var e=this.$store.state.contentTabsActiveName;if(this.$store.state.contentTabs=this.$store.state.contentTabs.filter((function(t){return t.name!==e})),this.$store.state.contentTabs.length<=0)return this.$store.state.sidebarMenuActiveName=this.$store.state.contentTabsActiveName="home",!1;e===this.$store.state.contentTabsActiveName&&this.$router.push({name:this.$store.state.contentTabs[this.$store.state.contentTabs.length-1].name})},deleteHandle:function(t){var e=this;if(this.mixinViewModuleOptions.deleteIsBatch&&!t&&this.dataListSelections.length<=0)return this.$message({message:this.$t("prompt.deleteBatch"),type:"warning",duration:500});this.$confirm(this.$t("prompt.info",{handle:this.$t("delete")}),this.$t("prompt.title"),{confirmButtonText:this.$t("confirm"),cancelButtonText:this.$t("cancel"),type:"warning"}).then((function(){e.$http.delete("".concat(e.mixinViewModuleOptions.deleteURL).concat(e.mixinViewModuleOptions.deleteIsBatch?"":"/"+t),e.mixinViewModuleOptions.deleteIsBatch?{data:t?[t]:e.dataListSelections.map((function(t){return t[e.mixinViewModuleOptions.deleteIsBatchKey]}))}:{}).then((function(t){var a=t.data;if(0!==a.code)return e.$message.error(a.msg);e.$message({message:e.$t("prompt.success"),type:"success",duration:500,onClose:function(){e.query()}})})).catch((function(){}))})).catch((function(){}))},exportHandle:function(){var t=s.a.stringify(Object(i["a"])({token:r.a.get("token")},this.dataForm));window.location.href="".concat(window.SITE_CONFIG["apiURL"]).concat(this.mixinViewModuleOptions.exportURL,"?").concat(t)}}}}}]);