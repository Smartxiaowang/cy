(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-29b6aac7"],{4248:function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t._self._c;return e("el-card",{staticClass:"aui-card--fill",attrs:{shadow:"never"}},[e("div",{staticClass:"mod-sys__params"},[e("el-form",{attrs:{inline:!0,model:t.dataForm},nativeOn:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.getDataList()}}},[e("el-form-item",[e("el-date-picker",{attrs:{type:"date",placeholder:"选择日期","picker-options":t.pickerOptions0},model:{value:t.dataForm.date,callback:function(e){t.$set(t.dataForm,"date",e)},expression:"dataForm.date"}})],1),e("el-form-item",[e("el-button",{on:{click:function(e){return t.getDataList()}}},[t._v(t._s(t.$t("query")))])],1),e("el-form-item",[e("el-button",{attrs:{type:"primary"},on:{click:t.exportExcel}},[t._v("导出Excel")])],1)],1),e("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.dataListLoading,expression:"dataListLoading"}],staticStyle:{width:"100%"},attrs:{data:t.dataList,border:""},on:{"selection-change":t.dataListSelectionChangeHandle}},[e("el-table-column",{attrs:{type:"selection","header-align":"center",align:"center",width:"50"}}),e("el-table-column",{attrs:{prop:"id",label:t.$t("jc.id"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"departure",label:t.$t("jc.departure"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"name",label:t.$t("jc.name"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"depart",label:t.$t("jc.depart"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"destination",label:t.$t("jc.destination"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"departuretime",label:t.$t("jc.departuretime"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"destinationtime",label:t.$t("jc.destinationtime"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"audit",label:t.$t("jc.audit"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"remake",label:t.$t("jc.remake"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{prop:"create_date",label:t.$t("jc.createdate"),"header-align":"center",align:"center"}}),e("el-table-column",{attrs:{label:t.$t("handle"),fixed:"right","header-align":"center",align:"center",width:"150"},scopedSlots:t._u([{key:"default",fn:function(a){return[e("el-button",{attrs:{type:"text",size:"small"},on:{click:function(e){return t.passInfo(a.row.id,1)}}},[t._v(t._s(t.$t("通过")))]),e("el-button",{attrs:{type:"text",size:"small"},on:{click:function(e){return t.passInfo(a.row.id,0)}}},[t._v(t._s(t.$t("驳回")))])]}}])})],1),e("el-pagination",{attrs:{"current-page":t.page,"page-sizes":[10,20,50,100],"page-size":t.limit,total:t.total,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":t.pageSizeChangeHandle,"current-change":t.pageCurrentChangeHandle}}),t.addOrUpdateVisible?e("add-or-update",{ref:"addOrUpdate",on:{refreshDataList:t.getDataList}}):t._e()],1)])},n=[],s=(a("c5cb"),a("08c7"),a("54f8"),a("4e3f"),a("e1a5")),r={mixins:[s["a"]],inject:["reload"],data:function(){return{pickerOptions0:{disabledDate:function(t){return t.getTime()>Date.now()-864e4}},inject:["reload"],mixinViewModuleOptions:{getDataListURL:"/getRegistrationAllMyStuList/",getDataListIsPage:!0,deleteURL:"/delRegistrationInfo",deleteIsBatch:!0},dataForm:{date:""}}},components:{},methods:{exportExcel:function(){var t=[];if(!(this.dataListSelections.length>0))return this.$message.error("请选择要导出的内容！"),!1;t=this.dataListSelections,this.exportList(t)},exportList:function(t){var e=[["序号","姓名","所属","出发地","目的地","出行时间","回来时间","审核情况","备注","登记时间"]];t.forEach((function(t,a){var i=[];i=[a+1,t.name,t.depart,t.departure,t.destination,t.departuretime,t.destinationtime,t.audit,t.remake,t.create_date],e.push(i)}));var a=this.XLSX.utils.aoa_to_sheet(e),i=this.XLSX.utils.book_new();this.XLSX.utils.book_append_sheet(i,a,"行程报备审核表"),this.XLSX.writeFile(i,"行程报备审核表.xlsx")},passInfo:function(t,e){var a=this;this.$http[(this.dataForm.id,"get")]("/passRegistrationInfo?id="+t+"&type="+e).then((function(t){var e=t.data;if(0!==e.code)return a.$message.error(e.message);a.$message({message:a.$t("prompt.success"),type:"success",duration:500,onClose:function(){a.visible=!1,a.$emit("refreshDataList")}}),a.reload()})).catch((function(){}))}}},o=r,l=a("0b56"),d=Object(l["a"])(o,i,n,!1,null,null,null);e["default"]=d.exports},e1a5:function(t,e,a){"use strict";var i=a("8ee2"),n=(a("aa48"),a("3446"),a("cd5d"),a("c5cb"),a("54f8"),a("4e3f"),a("6e84"),a("075f"),a("e04f")),s=a.n(n),r=a("34d3"),o=a.n(r);e["a"]={provide:function(){return{reload:this.reload}},data:function(){return{isShow:!0,mixinViewModuleOptions:{createdIsNeed:!0,activatedIsNeed:!1,getDataListURL:"",getDataListIsPage:!1,deleteURL:"",deleteIsBatch:!1,deleteIsBatchKey:"id",exportURL:""},dataForm:{},dataList:[],order:"",orderField:"",page:1,limit:10,total:0,dataListLoading:!1,dataListSelections:[],addOrUpdateVisible:!1}},created:function(){this.mixinViewModuleOptions.createdIsNeed&&this.query()},activated:function(){this.mixinViewModuleOptions.activatedIsNeed&&this.query()},methods:{reload:function(){this.query()},query:function(){var t=this;this.dataListLoading=!0,this.$http.get(this.mixinViewModuleOptions.getDataListURL,{params:Object(i["a"])({order:this.order,orderField:this.orderField,page:this.mixinViewModuleOptions.getDataListIsPage?this.page:null,limit:this.mixinViewModuleOptions.getDataListIsPage?this.limit:null},this.dataForm)}).then((function(e){var a=e.data;if(t.dataListLoading=!1,0!==a.code)return t.dataList=[],t.total=0,t.$message.error(a.msg);t.dataList=t.mixinViewModuleOptions.getDataListIsPage?a.data.list:a.data,t.total=t.mixinViewModuleOptions.getDataListIsPage?a.data.total:0})).catch((function(){t.dataListLoading=!1}))},dataListSelectionChangeHandle:function(t){this.dataListSelections=t},dataListSortChangeHandle:function(t){if(!t.order||!t.prop)return this.order="",this.orderField="",!1;this.order=t.order.replace(/ending$/,""),this.orderField=t.prop.replace(/([A-Z])/g,"_$1").toLowerCase(),this.query()},pageSizeChangeHandle:function(t){this.page=1,this.limit=t,this.query()},pageCurrentChangeHandle:function(t){this.page=t,this.query()},getDataList:function(){this.page=1,this.query()},addOrUpdateHandle:function(t){var e=this;this.addOrUpdateVisible=!0,this.$nextTick((function(){e.$refs.addOrUpdate.dataForm.id=t,e.$refs.addOrUpdate.init()}))},closeCurrentTab:function(t){var e=this.$store.state.contentTabsActiveName;if(this.$store.state.contentTabs=this.$store.state.contentTabs.filter((function(t){return t.name!==e})),this.$store.state.contentTabs.length<=0)return this.$store.state.sidebarMenuActiveName=this.$store.state.contentTabsActiveName="home",!1;e===this.$store.state.contentTabsActiveName&&this.$router.push({name:this.$store.state.contentTabs[this.$store.state.contentTabs.length-1].name})},deleteHandle:function(t){var e=this;if(this.mixinViewModuleOptions.deleteIsBatch&&!t&&this.dataListSelections.length<=0)return this.$message({message:this.$t("prompt.deleteBatch"),type:"warning",duration:500});this.$confirm(this.$t("prompt.info",{handle:this.$t("delete")}),this.$t("prompt.title"),{confirmButtonText:this.$t("confirm"),cancelButtonText:this.$t("cancel"),type:"warning"}).then((function(){e.$http.delete("".concat(e.mixinViewModuleOptions.deleteURL).concat(e.mixinViewModuleOptions.deleteIsBatch?"":"/"+t),e.mixinViewModuleOptions.deleteIsBatch?{data:t?[t]:e.dataListSelections.map((function(t){return t[e.mixinViewModuleOptions.deleteIsBatchKey]}))}:{}).then((function(t){var a=t.data;if(0!==a.code)return e.$message.error(a.msg);e.$message({message:e.$t("prompt.success"),type:"success",duration:500,onClose:function(){e.query()}})})).catch((function(){}))})).catch((function(){}))},exportHandle:function(){var t=o.a.stringify(Object(i["a"])({token:s.a.get("token")},this.dataForm));window.location.href="".concat(window.SITE_CONFIG["apiURL"]).concat(this.mixinViewModuleOptions.exportURL,"?").concat(t)}}}}}]);