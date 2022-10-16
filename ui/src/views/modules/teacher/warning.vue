<template>
    <el-card shadow="never" class="aui-card--fill">
        <div class="mod-sys__params">
            <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
                <!--<el-form-item>
                    <el-date-picker
                            v-model="dataForm.date"
                            type="date"
                            placeholder="选择日期"
                            :picker-options="pickerOptions0">
                    </el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button @click="getDataList()">{{ $t('query') }}</el-button>
                </el-form-item>-->
                <!-- <el-form-item>
                     <el-button type="primary" @click="addOrUpdateHandle()">{{ $t('add') }}</el-button>
                 </el-form-item>
                 <el-form-item>
                     <el-button type="danger" @click="deleteHandle()">{{ $t('deleteBatch') }}</el-button>
                 </el-form-item>-->
            </el-form>
            <el-table v-loading="dataListLoading" :data="dataList" border @selection-change="dataListSelectionChangeHandle" style="width: 100%;">
                <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
                <el-table-column prop="realname" :label="$t('jc.name')" header-align="center" align="center"></el-table-column>
                <el-table-column prop="depart" :label="$t('jc.depart')" header-align="center" align="center"></el-table-column>
                <el-table-column prop="create_date" :label="$t('jc.warn')" header-align="center" align="center"></el-table-column>

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
        </div>
    </el-card>
</template>

<script>
    import mixinViewModule from '@/mixins/view-module'
    export default {
        mixins: [mixinViewModule],
        inject: ['reload'],
        data () {
            return {
                pickerOptions0:{
                    disabledDate(time) {
                        return time.getTime() > Date.now() - 8.64e6;//如果不包括今天。就是return time.getTime() > Date.now() - 24*3600*1000;
                    }
                },
                inject:['reload'],
                mixinViewModuleOptions: {
                    getDataListURL: '/getWarring/',
                    getDataListIsPage: true,
                    deleteURL: '/delRegistrationInfo',
                    deleteIsBatch: true
                },
                dataForm: {
                    date:''
                }
            }
        },
        components: {
        },
        methods: {
            passInfo(id,type) {
                this.$http[!this.dataForm.id ? 'get' : 'get']('/passRegistrationInfo'+"?id="+id+"&type="+type).then(({ data: res }) => {
                    if (res.code !== 0) {
                        return this.$message.error(res.message)
                    }
                    this.$message({
                        message: this.$t('prompt.success'),
                        type: 'success',
                        duration: 500,

                        onClose: () => {
                            this.visible = false
                            this.$emit('refreshDataList')
                        }
                    })
                    this.reload();
                }).catch(() => {})
            }
        }
    }
</script>
