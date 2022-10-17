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
                    <el-button type="primary" @click="exportExcel">导出Excel</el-button>   </el-form-item>
            </el-form>
            <el-table v-loading="dataListLoading" :data="dataList" border @selection-change="dataListSelectionChangeHandle" style="width: 100%;">
                <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
                <el-table-column prop="id" :label="$t('bt.id')" header-align="center" align="center"></el-table-column>
                <el-table-column prop="inschool" :label="$t('bt.inschool')" header-align="center" align="center"></el-table-column>
                <el-table-column prop="symptom" :label="$t('bt.symptom')" header-align="center" align="center"></el-table-column>
                <el-table-column prop="isconfirmed" :label="$t('bt.isconfirmed')" header-align="center" align="center"></el-table-column>
                <el-table-column prop="isdanger" :label="$t('bt.isdanger')" header-align="center" align="center"></el-table-column>
                <el-table-column prop="temperature" :label="$t('bt.temperature')" header-align="center" align="center"></el-table-column>
                <el-table-column prop="place" :label="$t('bt.place')" header-align="center" align="center"></el-table-column>
                <el-table-column prop="remake" :label="$t('bt.remake')" header-align="center" align="center"></el-table-column>
               <!-- <el-table-column prop="uid" :label="$t('bt.uid')" header-align="center" align="center"></el-table-column>
               --> <el-table-column prop="name" :label="$t('bt.name')" header-align="center" align="center"></el-table-column>
                <el-table-column prop="depart" :label="$t('bt.depart')" header-align="center" align="center"></el-table-column>

                <el-table-column prop="create_date" :label="$t('bt.create_date')" header-align="center" align="center"></el-table-column>
                <!--<el-table-column :label="$t('handle')" fixed="right" header-align="center" align="center" width="150">
                    <template slot-scope="scope">
                        <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">{{ $t('update') }}</el-button>
                        <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">{{ $t('delete') }}</el-button>
                    </template>
                </el-table-column>-->
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
    import AddOrUpdate from './registr-add-or-update'
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
                    getDataListURL: '/getBodyTempAllMyStuList',
                    getDataListIsPage: true,
                    deleteURL: '/delBodyTemInfo',
                    deleteIsBatch: true
                },
                dataForm: {
                    date:''
                }
            }
        },
        components: {
            AddOrUpdate
        },
        methods:{
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
                    ['序号','是否在校','症状', '是否确诊','是否密接', '体温',"目前所在地","其他说明","姓名","所属","登记时间"]//导出表头
                ] // 表格表头
                list.forEach ((item,index)=> {
                    let rowData = []
                    //导出内容的字段
                    rowData = [
                        index+1,
                        item.inschool,
                        item.symptom,
                        item.isconfirmed,
                        item.isdanger,
                        item.temperature,
                        item.place,
                        item.remake,
                        item.name,
                        item.depart,
                        item.create_date
                    ]
                    tableData.push(rowData)
                })
                let ws = this.XLSX.utils.aoa_to_sheet(tableData)
                let wb = this.XLSX.utils.book_new()
                this.XLSX.utils.book_append_sheet(wb, ws, '本班学生体温登记表') // 工作簿名称
                this.XLSX.writeFile(wb, '本班学生体温登记表.xlsx') // 保存的文件名
            }
        }
    }
</script>
