<template>
    <el-card shadow="never" class="aui-card--fill">
        <div class="mod-sys__params">
            <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
                <el-form-item>
                    <el-date-picker
                            v-model="dataForm.date"
                            type="date"
                            placeholder="选择日期"
                            :picker-options="pickerOptions0">
                    </el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button @click="getDataList()">{{ $t('query') }}</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="addOrUpdateHandle()">{{ $t('add') }}</el-button>
                </el-form-item>
               <!-- <el-form-item>
                    <el-button type="danger" @click="deleteHandle()">{{ $t('deleteBatch') }}</el-button>
                </el-form-item>-->

            </el-form>
            <el-table  v-loading="dataListLoading"
                      :data="dataList" border @selection-change="dataListSelectionChangeHandle" style="width: 100%;">
                <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
                <el-table-column prop="id" :label="$t('jc.id')" header-align="center" align="center"></el-table-column>
                <el-table-column prop="departure" :label="$t('jc.departure')" header-align="center" align="center"></el-table-column>
                <el-table-column prop="destination" :label="$t('jc.destination')" header-align="center" align="center"></el-table-column>
                <el-table-column prop="departuretime" :label="$t('jc.departuretime')" header-align="center" align="center"></el-table-column>
                <el-table-column prop="destinationtime" :label="$t('jc.destinationtime')" header-align="center" align="center"></el-table-column>
                <el-table-column prop="audit" :label="$t('jc.audit')" header-align="center" align="center"></el-table-column>
                 <el-table-column prop="remake" :label="$t('jc.remake')" header-align="center" align="center"></el-table-column>
                 <el-table-column prop="createDate" :label="$t('jc.createdate')" header-align="center" align="center"></el-table-column>
                <el-table-column :label="$t('handle')" fixed="right" header-align="center" align="center" width="150">
                    <template slot-scope="scope">
                        <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">{{ $t('update') }}</el-button>
                    <!--    <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">{{ $t('delete') }}</el-button>
                    --></template>
                </el-table-column>
            </el-table>
            <el-pagination
                    :current-page="page"
                    :page-sizes="[10, 20, 50, 100]"
                    :page-size="limit"
                    :total="total"
                    layout="total, sizes, prev, pager, next, jumper"
                    @size-change="pageSizeChangeHandle"
                    @current-change="pageCurrentChangeHandle">
            </el-pagination>
            <!-- 弹窗, 新增 / 修改 -->
            <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
        </div>

    </el-card>

</template>

<script>
    import mixinViewModule from '@/mixins/view-module'
    import AddOrUpdate from './registration-add-or-update'

    export default {
        mixins: [mixinViewModule],
        data () {
            return {
                pickerOptions0:{
                    disabledDate(time) {
                        return time.getTime() > Date.now() - 8.64e6;//如果不包括今天。就是return time.getTime() > Date.now() - 24*3600*1000;
                    }
                },
                mixinViewModuleOptions: {
                    getDataListURL: '/getRegistrationList/',
                    getDataListIsPage: true,
                    deleteURL: '/delRegistrationInfo',
                    deleteIsBatch: true
                },
                dataForm: {
                    date:''
                },
                selectData:[],
                selectWindow:false,
            }
        },
        components: {
            AddOrUpdate
        },
        methods:{
            //导出
            exportExcel() {
                let list = [];
                if (this.dataListSelections.length >0){
                    //如图我选择了导出那几条数据则导出我需要导出的数据（this.selectRows表示我选中导出数据的数组）
                    list =this.dataListSelections  //选择导出部分代码
                    this.exportList(list);
                }else {
                    this.$message.error('请选择要导出的内容！');
                    return false;
                }

            },

            exportList(list){
                let tableData = [
                    ['序号','姓名','所属', '出发地','目的地', '出行时间',"回来时间","审核情况","备注","登记时间"]//导出表头
                ] // 表格表头
                list.forEach ((item,index)=> {
                    let rowData = []
                    //导出内容的字段
                    rowData = [
                        index+1,
                        item.name,
                        item.depart,
                        item.departure,
                        item.destination,
                        item.departuretime,
                        item.destinationtime,
                        item.audit,
                        item.remake,
                        item.createDate
                    ]
                    tableData.push(rowData)
                })
                let ws = this.XLSX.utils.aoa_to_sheet(tableData)
                let wb = this.XLSX.utils.book_new()
                this.XLSX.utils.book_append_sheet(wb, ws, '行程报备表') // 工作簿名称
                this.XLSX.writeFile(wb, '行程报备表.xlsx') // 保存的文件名
            }
        }
    }
</script>
