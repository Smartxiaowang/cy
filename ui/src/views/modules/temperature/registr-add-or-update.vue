<template>
    <el-dialog :visible.sync="visible" :title="!dataForm.id ? $t('add') : $t('update')" :close-on-click-modal="false" :close-on-press-escape="false">
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmitHandle()" label-width="120px">
            <el-form-item prop="inschool" :label="$t('bt.inschool')">
                <el-radio v-model="dataForm.inschool" label="是">是</el-radio>
                <el-radio v-model="dataForm.inschool" label="否">否</el-radio>
           <!--  <el-input v-model="dataForm.inschool" :placeholder="$t('bt.inschool')"></el-input>-->
            </el-form-item>
            <el-form-item prop="symptom" :label="$t('bt.symptom')">
                <el-input v-model="dataForm.symptom" :placeholder="$t('bt.symptom')"></el-input>
            </el-form-item>
            <el-form-item prop="isconfirmed" :label="$t('bt.isconfirmed')">
                <el-radio v-model="dataForm.isconfirmed" label="是">是</el-radio>
                <el-radio v-model="dataForm.isconfirmed" label="否">否</el-radio>
            </el-form-item>
            <el-form-item prop="isdanger" :label="$t('bt.isdanger')">
                <el-radio v-model="dataForm.isdanger" label="是">是</el-radio>
                <el-radio v-model="dataForm.isdanger" label="否">否</el-radio>
            </el-form-item>
            <el-form-item prop="temperature" :label="$t('bt.temperature')">
                <el-input v-model="dataForm.temperature" :placeholder="$t('bt.temperature')"></el-input>
            </el-form-item>
            <el-form-item prop="place" :label="$t('bt.place')">
                <el-input v-model="dataForm.place" :placeholder="$t('bt.place')"></el-input>
            </el-form-item>
            <el-form-item prop="remake" :label="$t('bt.remake')">
                <el-input v-model="dataForm.remake" :placeholder="$t('bt.remake')"></el-input>
            </el-form-item>
            <el-form-item prop="remake" :label="$t('bt.createdate')">
                <el-date-picker
                        v-model="dataForm.createDate"
                        type="date"
                        placeholder="选择日期"
                        value-format="yyyy-MM-dd"
                        :picker-options="pickerOptions0">
                </el-date-picker>
            </el-form-item>

        </el-form>
        <template slot="footer">
            <el-button @click="visible = false">{{ $t('cancel') }}</el-button>
            <el-button type="primary" @click="dataFormSubmitHandle()">{{ $t('confirm') }}</el-button>
        </template>
    </el-dialog>
</template>

<script>
    import debounce from 'lodash/debounce'
    export default {
        data () {
            return {
                pickerOptions0:{
                    disabledDate(time) {
                        return time.getTime() > Date.now() - 8.64e6;//如果不包括今天。就是return time.getTime() > Date.now() - 24*3600*1000;
                    }
                },
                dto :{
                    checkList: []
                },

                visible: false,
                dataForm: {
                    id: '',
                    inschool:'',
                    symptom: '',
                    isconfirmed: '',
                    isdanger: '',
                    temperature: '',
                    place: '',
                    createDate: '',
                    remake: ''
                }
            }
        },
        computed: {
            dataRule () {
                return {
                    paramCode: [
                        { required: true, message: this.$t('validate.required'), trigger: 'blur' }
                    ],
                    paramValue: [
                        { required: true, message: this.$t('validate.required'), trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            init () {
                this.visible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].resetFields()
                    if (this.dataForm.id) {
                        this.getInfo()
                    }
                })
            },
            // 获取信息
            getInfo () {
                this.$http.get(`/getBodyTempByUid?id=${this.dataForm.id}`).then(({ data: res }) => {

                    if (res.code !== 0) {
                        console.log(res.message)
                        return this.$message.error(res.message)
                    }
                    this.dataForm = {
                        ...this.dataForm,
                        ...res.data.list[0]
                    }
                }).catch(() => {})
            },
            // 表单提交
            dataFormSubmitHandle: debounce(function () {
                this.$refs['dataForm'].validate((valid) => {
                    if (!valid) {
                        return false
                    }
                    if (!this.dataForm.id) {
                        //没id新增
                        this.$http[!this.dataForm.id ? 'post' : 'post']('/saveBodyTemperatureInfo',this.dataForm).then(({ data: res }) => {
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
                        }).catch(() => {})

                    }else {
                        //有id修改
                        this.$http[!this.dataForm.id ? 'post' : 'post']('/updBodyTemInfo', this.dataForm).then(({ data: res }) => {
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
                        }).catch(() => {})
                    }
                })
            }, 1000, { 'leading': true, 'trailing': false })
        }
    }
</script>
