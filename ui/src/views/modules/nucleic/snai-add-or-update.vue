<template>
    <el-dialog :visible.sync="visible" :title="!dataForm.id ? $t('add') : $t('update')" :close-on-click-modal="false"
               :close-on-press-escape="false">
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmitHandle()"
                 label-width="120px">
            <el-form-item prop="isunusual" :label="$t('na.isunusual')">
                <el-radio v-model="dataForm.isunusual" label="阴性">阴性</el-radio>
                <el-radio v-model="dataForm.isunusual" label="阳性">阳性</el-radio>
                <!--  <el-input v-model="dataForm.inschool" :placeholder="$t('bt.inschool')"></el-input>-->
            </el-form-item>
            <el-form-item prop="remake" :label="$t('bt.remake')">
                <el-input v-model="dataForm.remake" :placeholder="$t('bt.remake')"></el-input>
            </el-form-item>
            <el-form-item :label="$t('na.nucleicacid')">
                <el-upload action="#" list-type="picture-card"
                           :auto-upload="false"
                           :on-change="handleChange"
                           :limit="1"
                           :file-list="fileList">
                    <i slot="default" class="el-icon-plus"></i>
                    <div slot="file" slot-scope="{file}">
                        <img
                                class="el-upload-list__item-thumbnail"
                                :src="file.url" alt=""
                        >
                        <span class="el-upload-list__item-actions">
        <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
          <i class="el-icon-zoom-in"></i>
             <span
                     v-if="!disabled"
                     class="el-upload-list__item-delete"
                     @click="handleRemove(file)"
             >
          <i class="el-icon-delete"></i>
        </span>
        </span>
      </span>
                    </div>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="">
                </el-dialog>
            </el-form-item>
            <el-form-item prop="remake" :label="$t('bt.createdate')">
                <el-date-picker
                        v-model="dataForm.createDate"
                        type="date"
                        placeholder="选择日期"
                        value-format="yyyy-MM-dd"
                        :picker-options="pickerOptions0"
                        >
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
        data() {
            return {
                pickerOptions0:{
                    disabledDate(time) {
                        return time.getTime() > Date.now() - 8.64e6;//如果不包括今天。就是return time.getTime() > Date.now() - 24*3600*1000;
                    }
                },
                dialogImageUrl: '',
                dialogVisible: false,
                fileList: [],
                disabled: false,
                visible: false,
                dataForm: {
                    id: '',
                    nucleicacid:'',
                    isunusual: '',
                    createDate: '',
                    remake: ''
                }
            }
        },
        computed: {
            dataRule() {
                return {
                    paramCode: [
                        {required: true, message: this.$t('validate.required'), trigger: 'blur'}
                    ],
                    paramValue: [
                        {required: true, message: this.$t('validate.required'), trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            init() {
                this.visible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].resetFields()
                    if (this.dataForm.id) {
                        this.getInfo()
                    }
                })
            },
            getBase64(file) {
                return new Promise(function(resolve, reject) {
                    let reader = new FileReader();
                    let imgResult = "";
                    reader.readAsDataURL(file.raw);
                    reader.onload = function() {
                        imgResult = reader.result;
                    };
                    reader.onerror = function(error) {
                        reject(error);
                    };
                    reader.onloadend = function() {
                        resolve(imgResult);
                    };
                });
            },
            handleChange(file, fileList) {//上传文件变化时
                this.getBase64(file).then(res =>{
                    this.dataForm.nucleicacid = res;
                })
            },
            handleRemove(file) {
                this.fileList = this.fileList.pop()
            },
            handlePictureCardPreview(file) {

                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            // 获取信息
            getInfo() {
                this.$http.get(`/getBodyTempByUid?id=${this.dataForm.id}`).then(({data: res}) => {

                    if (res.code !== 0) {
                        console.log(res.message)
                        return this.$message.error(res.message)
                    }
                    this.dataForm = {
                        ...this.dataForm,
                        ...res.data.list[0]
                    }
                }).catch(() => {
                })
            },
            // 表单提交
            dataFormSubmitHandle: debounce(function () {
                this.$refs['dataForm'].validate((valid) => {
                    if (!valid) {
                        return false
                    }
                    if (!this.dataForm.id) {
                        //没id新增
                        this.$http[!this.dataForm.id ? 'post' : 'post']('/saveNucleicAcidInfo', this.dataForm).then(({data: res}) => {
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
                        }).catch(() => {
                        })
                        console.log(this.dialogImageUrl)
                    } else {
                        //有id修改
                        this.$http[!this.dataForm.id ? 'post' : 'post']('/updNucleicAcidInfo', this.dataForm).then(({data: res}) => {
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
                        }).catch(() => {
                        })
                    }
                })
            }, 1000, {'leading': true, 'trailing': false})
        }
    }
</script>
