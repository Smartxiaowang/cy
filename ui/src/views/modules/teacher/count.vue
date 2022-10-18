<template>
    <div>
        <el-card shadow="never" class="aui-card--fill">
            <div class="mod-sys__params">
                <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
                    <el-form-item>
                        <el-date-picker
                                v-model="dataForm.date"
                                type="month"
                                placeholder="选择日期"
                                value-format="yyyy-MM"
                                :picker-options="pickerOptions0">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="getInit(dataForm.date)">{{ $t('query') }}</el-button>
                    </el-form-item>
                </el-form>
                <div class="chart" style="height: 600px;width: 600px;" id="main"></div>
            </div>
            <div class="row">
            </div>

        </el-card>


    </div>
</template>

<script>
    import mixinViewModule from '@/mixins/view-module'
    import * as echarts from 'echarts';
    import debounce from 'lodash/debounce'
    export default {
        mixins: [mixinViewModule],
        data() {
            return {
                dataForm: {
                    date: ''
                }
            }
        },
        components: {},
        mounted() {
            this.getInit()
        },
        methods: {
            pickerOptions0:{
                disabledDate(time) {
                    return time.getTime() > Date.now() - 8.64e6;//如果不包括今天。就是return time.getTime() > Date.now() - 24*3600*1000;
                }
            },
            getInit(date) {
                let incomclass ;
                this.$http.get(`/count`+"?date="+date).then(({ data: res }) => {
                    if (res.code !== 0) {
                       return this.$message.error(res.message)
                    }
                    incomclass = res.data.list;
                    var app = {};
                    /*incomclass = [
                            [{name: "返校人数", value: 900},
                            {name: "出校人数", value: 1212}],
                        [{name: "返校人数", value: 9020},
                            {name: "出校人数", "value": 0}]]*/

                    var chartDom = document.getElementById('main');
                    var myChart = echarts.init(chartDom);
                    var option;
                    const cellSize = [80, 80];
                    const pieRadius = 30;
                    var bdate =res.data.bdate;
                    var ebdate =res.data.ebdate;
                    var rdate =res.data.rdate;
                    function getVirtulData() {
                        let date = +echarts.number.parseDate(bdate);
                        let end = +echarts.number.parseDate(ebdate);
                        let dayTime = 3600 * 24 * 1000;
                        let data = [];
                        for (let time = date; time <= end; time += dayTime) {
                            data.push([
                                echarts.format.formatTime('yyyy-MM-dd', time),
                                Math.floor(Math.random() * 10000)
                            ]);
                        }
                        return data;
                    }

                    function getPieSeries(scatterData, chart) {
                        var i = 0;
                        return scatterData.map(function (item, index) {
                            var pieData = incomclass[i]
                            i = i + 1;
                            var center = chart.convertToPixel('calendar', item);
                            return {
                                id: index + 'pie',
                                type: 'pie',
                                center: center,
                                label: {
                                    formatter: '{c}',
                                    position: 'inside'
                                },
                                radius: pieRadius,
                                data: pieData
                            };
                        });
                    }

                    function getPieSeriesUpdate(scatterData, chart) {
                        return scatterData.map(function (item, index) {
                            var center = chart.convertToPixel('calendar', item);
                            return {
                                id: index + 'pie',
                                center: center
                            };
                        });
                    }

                    var scatterData = getVirtulData();
                    option = {
                        tooltip: {},
                        legend: {
                            data: ['Work', 'Entertainment', 'Sleep'],
                            bottom: 20
                        },
                        calendar: {
                            top: 'middle',
                            left: 'center',
                            orient: 'vertical',
                            cellSize: cellSize,
                            yearLabel: {
                                show: false,
                                fontSize: 30
                            },
                            dayLabel: {
                                margin: 20,
                                firstDay: 1,
                                nameMap: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                            },
                            monthLabel: {
                                show: false
                            },
                            range: rdate
                        },
                        series: [
                            {
                                id: 'label',
                                type: 'scatter',
                                coordinateSystem: 'calendar',
                                symbolSize: 1,
                                label: {
                                    show: true,
                                    formatter: function (params) {
                                        return echarts.format.formatTime('dd', params.value[0]);
                                    },
                                    offset: [-cellSize[0] / 2 + 10, -cellSize[1] / 2 + 10],
                                    fontSize: 14
                                },
                                data: scatterData
                            }
                        ]
                    };
                    let pieInitialized;
                    setTimeout(function () {
                        pieInitialized = true;
                        myChart.setOption({
                            series: getPieSeries(scatterData, myChart)
                        });
                    }, 10);
                    app.onresize = function () {
                        if (pieInitialized) {
                            myChart.setOption({
                                series: getPieSeriesUpdate(scatterData, myChart)
                            });
                        }
                    };

                    option && myChart.setOption(option);

                }).catch()
        }}
    };

</script>
