(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2505857e"],{"9fca":function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t._self._c;return e("el-dialog",{attrs:{visible:t.visible,title:t.$t("schedule.log"),"close-on-click-modal":!1,"close-on-press-escape":!1,width:"75%"},on:{"update:visible":function(e){t.visible=e}}},[e("el-form",{attrs:{inline:!0,model:t.dataForm},nativeOn:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.getDataList()}}},[e("el-form-item",[e("el-input",{attrs:{placeholder:t.$t("schedule.jobId"),clearable:""},model:{value:t.dataForm.jobId,callback:function(e){t.$set(t.dataForm,"jobId",e)},expression:"dataForm.jobId"}})],1),e("el-form-item",[e("el-button",{on:{click:function(e){return t.getDataList()}}},[t._v(t._s(t.$t("query")))])],1)],1),e("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.dataListLoading,expression:"dataListLoading"}],staticStyle:{width:"100%"},attrs:{data:t.dataList,border:"",height:"460"},on:{"sort-change":t.dataListSortChangeHandle}},[e("el-table-column",{attrs:{prop:"jobId",label:t.$t("schedule.jobId"),"header-align":"center",align:"center",width:"80"}}),e("el-table-column",{attrs:{prop:"beanName",label:t.$t("schedule.beanName"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"params",label:t.$t("schedule.params"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"status",label:t.$t("schedule.status"),"header-align":"center",align:"center"},scopedSlots:t._u([{key:"default",fn:function(a){return[1===a.row.status?e("el-tag",{attrs:{size:"small"}},[t._v(t._s(t.$t("schedule.statusLog1")))]):e("el-tag",{staticStyle:{cursor:"pointer"},attrs:{type:"danger",size:"small"},nativeOn:{click:function(e){return t.showErrorInfo(a.row.id)}}},[t._v(t._s(t.$t("schedule.statusLog0")))])]}}])}),e("el-table-column",{attrs:{prop:"times",label:t.$t("schedule.times"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"createDate",label:t.$t("schedule.createDate"),"header-align":"center",align:"center",width:"180"}})],1),e("el-pagination",{attrs:{"current-page":t.page,"page-sizes":[10,20,50,100],"page-size":t.limit,total:t.total,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":t.pageSizeChangeHandle,"current-change":t.pageCurrentChangeHandle}})],1)},n=[],s=a("e1a5"),o={mixins:[s["a"]],data:function(){return{visible:!1,mixinViewModuleOptions:{getDataListURL:"/sys/scheduleLog/page",getDataListIsPage:!0},dataForm:{jobId:""}}},methods:{init:function(){this.visible=!0,this.getDataList()},showErrorInfo:function(t){var e=this;this.$http.get("/sys/scheduleLog/".concat(t)).then((function(t){var a=t.data;if(0!==a.code)return e.$message.error(a.msg);e.$alert(a.data.error)})).catch((function(){}))}}},r=o,l=a("0b56"),d=Object(l["a"])(r,i,n,!1,null,null,null);e["default"]=d.exports},e1a5:function(t,e,a){"use strict";var i=a("8ee2"),n=(a("aa48"),a("3446"),a("cd5d"),a("c5cb"),a("54f8"),a("4e3f"),a("6e84"),a("075f"),a("e04f")),s=a.n(n),o=a("34d3"),r=a.n(o);e["a"]={provide:function(){return{reload:this.reload}},data:function(){return{isShow:!0,mixinViewModuleOptions:{createdIsNeed:!0,activatedIsNeed:!1,getDataListURL:"",getDataListIsPage:!1,deleteURL:"",deleteIsBatch:!1,deleteIsBatchKey:"id",exportURL:""},dataForm:{},dataList:[],order:"",orderField:"",page:1,limit:10,total:0,dataListLoading:!1,dataListSelections:[],addOrUpdateVisible:!1}},created:function(){this.mixinViewModuleOptions.createdIsNeed&&this.query()},activated:function(){this.mixinViewModuleOptions.activatedIsNeed&&this.query()},methods:{reload:function(){this.query()},query:function(){var t=this;this.dataListLoading=!0,this.$http.get(this.mixinViewModuleOptions.getDataListURL,{params:Object(i["a"])({order:this.order,orderField:this.orderField,page:this.mixinViewModuleOptions.getDataListIsPage?this.page:null,limit:this.mixinViewModuleOptions.getDataListIsPage?this.limit:null},this.dataForm)}).then((function(e){var a=e.data;if(t.dataListLoading=!1,0!==a.code)return t.dataList=[],t.total=0,t.$message.error(a.msg);t.dataList=t.mixinViewModuleOptions.getDataListIsPage?a.data.list:a.data,t.total=t.mixinViewModuleOptions.getDataListIsPage?a.data.total:0})).catch((function(){t.dataListLoading=!1}))},dataListSelectionChangeHandle:function(t){this.dataListSelections=t},dataListSortChangeHandle:function(t){if(!t.order||!t.prop)return this.order="",this.orderField="",!1;this.order=t.order.replace(/ending$/,""),this.orderField=t.prop.replace(/([A-Z])/g,"_$1").toLowerCase(),this.query()},pageSizeChangeHandle:function(t){this.page=1,this.limit=t,this.query()},pageCurrentChangeHandle:function(t){this.page=t,this.query()},getDataList:function(){this.page=1,this.query()},addOrUpdateHandle:function(t){var e=this;this.addOrUpdateVisible=!0,this.$nextTick((function(){e.$refs.addOrUpdate.dataForm.id=t,e.$refs.addOrUpdate.init()}))},closeCurrentTab:function(t){var e=this.$store.state.contentTabsActiveName;if(this.$store.state.contentTabs=this.$store.state.contentTabs.filter((function(t){return t.name!==e})),this.$store.state.contentTabs.length<=0)return this.$store.state.sidebarMenuActiveName=this.$store.state.contentTabsActiveName="home",!1;e===this.$store.state.contentTabsActiveName&&this.$router.push({name:this.$store.state.contentTabs[this.$store.state.contentTabs.length-1].name})},deleteHandle:function(t){var e=this;if(this.mixinViewModuleOptions.deleteIsBatch&&!t&&this.dataListSelections.length<=0)return this.$message({message:this.$t("prompt.deleteBatch"),type:"warning",duration:500});this.$confirm(this.$t("prompt.info",{handle:this.$t("delete")}),this.$t("prompt.title"),{confirmButtonText:this.$t("confirm"),cancelButtonText:this.$t("cancel"),type:"warning"}).then((function(){e.$http.delete("".concat(e.mixinViewModuleOptions.deleteURL).concat(e.mixinViewModuleOptions.deleteIsBatch?"":"/"+t),e.mixinViewModuleOptions.deleteIsBatch?{data:t?[t]:e.dataListSelections.map((function(t){return t[e.mixinViewModuleOptions.deleteIsBatchKey]}))}:{}).then((function(t){var a=t.data;if(0!==a.code)return e.$message.error(a.msg);e.$message({message:e.$t("prompt.success"),type:"success",duration:500,onClose:function(){e.query()}})})).catch((function(){}))})).catch((function(){}))},exportHandle:function(){var t=r.a.stringify(Object(i["a"])({token:s.a.get("token")},this.dataForm));window.location.href="".concat(window.SITE_CONFIG["apiURL"]).concat(this.mixinViewModuleOptions.exportURL,"?").concat(t)}}}}}]);