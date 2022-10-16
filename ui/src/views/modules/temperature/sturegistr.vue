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
                    <el-button v-if="$hasPermission('sys:params:save')" type="primary" @click="addOrUpdateHandle()">{{ $t('add') }}</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button v-if="$hasPermission('sys:params:delete')" type="danger" @click="deleteHandle()">{{ $t('deleteBatch') }}</el-button>
                </el-form-item>
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
        }
    }
</script>
