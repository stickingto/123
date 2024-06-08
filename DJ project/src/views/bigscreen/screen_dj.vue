<template>
  <div id="index" ref="appRef">
    <div class="bg">
      <dv-loading v-show="loading">载入中</dv-loading>
      <div class="host-body">
        <div>
          <!-- 顶部title部分 -->
          <el-row>
            <el-col :span="6">
              <div class="title_text1">党建管理</div>
              <dv-decoration-8 class="title_right" :color="['#008CFF', '#00ADDD']" /></el-col>
            <el-col :span="12">
              <router-link to="/screen_home">
                <div class="title_text2">小镇数字化指挥中心</div>
              </router-link>
              <dv-decoration-5 class="title_center" :color="['#008CFF', '#00ADDD']" />
            </el-col>
            <el-col :span="6">
              <div class="title_time">{{ dateYear + dateWeek + dateDay }}</div>
              <dv-decoration-8 :reverse="true" class="title_left" :color="['#008CFF', '#00ADDD']" />
            </el-col>
          </el-row>
          <!-- 主体部分 -->
          <el-row>
            <!-- 第一列 -->
            <el-col :span="6">
              <!-- 饼图部分 -->
              <div class="left_box1ok">

                <div class="box">
                  <div class="title">上海市临港新片区建设者小镇党支部</div>
                  <div class="content">
                    <div class="section">
                      <div class="label">支部书记：</div>
                      <div class="value"> 张得帅 </div>
                    </div>
                    <div class="section">
                      <div class="label">支部人员：</div>
                      <div class="value">罗杰</div>
                      <div class="value">张三</div>
                      <div class="value">李四</div>
                      <div class="value">王五</div>
                      <div class="value">老六</div>
                    </div>
                  </div>
                </div>

              </div>
              <!-- 柱状图部分 -->
              <div class="left_box2ok">
                <div class="title">专题会议</div>

                <el-carousel :interval="4000" type="" height="272px">
                  <el-carousel-item v-for="item in imagebox2" :key="item.id">
                    <img :src="item.idView" class="image">
                  </el-carousel-item>
                </el-carousel>

              </div>
              <!-- 轮播表格部分 -->
              <div class="left_box3ok">
                <div class="title">党建学习</div>

                <el-carousel :interval="4000" type="" height="272px">
                  <el-carousel-item v-for="item in imagebox3" :key="item.id">
                    <img :src="item.idView" class="image">
                  </el-carousel-item>
                </el-carousel>

              </div>
            </el-col>
            <!-- 第二列 -->
            <el-col :span="12">
              <!-- 中国地图 -->
              <div id="china-map" />
              <!-- 折线图 -->
              <div class="line_center">
                <dv-border-box-8>
                  <div id="line_center_diagram" />
                </dv-border-box-8>
              </div>
            </el-col>
            <!-- 第三列 -->
            <el-col :span="6">
              <!-- 轮播排行榜部分 -->
              <div class="right_box1">
                <dv-border-box-12>
                  <div class="box">
                    <div class="title">支部简介</div>
                    <div class="content">
                      <div class="label2">发展规划处党支部成立于2016年9月，成立之初即根据支部特点，明确提出造“勤于学习、勇于担当”的党支部，以提升凝聚力为目标，致力于增强党员干部的责任感和担当精神，使全体党员干部保持阳光心态投入工作、精益求精地对待工作，将党建工作与推动学校事业发展相结合，推进各项工作的落实。</div>
                    </div>
                  </div>
                </dv-border-box-12>
              </div>
              <!-- 虚线柱状图部分 -->
              <div class="right_box2">

                <div class="transparent-list">
                  <div class="title2">优秀党员展示</div>
                  <div class="list-header">
                    <div class="item">党员编号</div>
                    <div class="item">党员姓名</div>
                    <div class="item">入党时间</div>
                  </div>
                  <div v-for="(member, index) in excellentPartyMembers" :key="index" class="list-item">
                    <div class="item">{{ member.id }}</div>
                    <div class="item">{{ member.name }}</div>
                    <div class="item">{{ member.joinDate }}</div>
                  </div>
                </div>

              </div>
              <!-- 锥型部分 -->
              <div class="right_box3">
                <dv-border-box-12 :reverse="true">
                  <el-carousel :interval="4000" type="card" height="272px">
                    <el-carousel-item v-for="item in imagebox" :key="item.id">
                      <img :src="item.idView" class="image">
                    </el-carousel-item>
                  </el-carousel>

                </dv-border-box-12>
              </div>
            </el-col>
          </el-row>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import drawMixin from '@/utils/drawMixin' // 自适应缩放
import { formatTime } from '@/utils/index2.js' // 日期格式转换
// import * as echarts from 'echarts'
export default {
  // mixins: [drawMixin],
  data() {
    return {
      drawer: false,
      direction: 'btt',

      imagebox: [
        { id: 0, idView: require('@/assets/carousel/dangyuan1.jpg') },
        { id: 1, idView: require('@/assets/carousel/dangyuan2.jpg') },
        { id: 2, idView: require('@/assets/carousel/dangyuan3.jpg') },
        { id: 3, idView: require('@/assets/carousel/dangyuan4.jpg') },
        { id: 4, idView: require('@/assets/carousel/dangyuan5.jpg') },
        { id: 5, idView: require('@/assets/carousel/dangyuan6.jpg') },
        { id: 6, idView: require('@/assets/carousel/dangyuan7.jpg') },
        { id: 7, idView: require('@/assets/carousel/dangyuan8.jpg') }

      ],
      imagebox2: [
        { id: 0, idView: require('@/assets/carousel/huiyi1.jpg') },
        { id: 1, idView: require('@/assets/carousel/huiyi2.jpg') },
        { id: 2, idView: require('@/assets/carousel/huiyi3.jpg') },
        { id: 3, idView: require('@/assets/carousel/huiyi4.jpg') },
        { id: 4, idView: require('@/assets/carousel/huiyi5.jpg') },
        { id: 5, idView: require('@/assets/carousel/huiyi6.jpg') },
        { id: 6, idView: require('@/assets/carousel/huiyi7.jpg') },
        { id: 7, idView: require('@/assets/carousel/huiyi8.jpg') }

      ],
      imagebox3: [
        { id: 0, idView: require('@/assets/carousel/dj1.jpg') },
        { id: 1, idView: require('@/assets/carousel/dj2.jpg') },
        { id: 2, idView: require('@/assets/carousel/dj3.jpg') },
        { id: 3, idView: require('@/assets/carousel/dj4.jpg') },
        { id: 4, idView: require('@/assets/carousel/dj5.jpg') },
        { id: 5, idView: require('@/assets/carousel/dj6.jpg') },
        { id: 6, idView: require('@/assets/carousel/dj7.jpg') },
        { id: 7, idView: require('@/assets/carousel/dj8.jpg') }

      ],
      excellentPartyMembers: [
        { id: 1, name: '张三', joinDate: '2020-01-01' },
        { id: 2, name: '李四', joinDate: '2019-07-15' },
        { id: 3, name: '赛阿萨', joinDate: '2018-07-15' },
        { id: 4, name: '士大夫', joinDate: '2016-07-15' },
        { id: 5, name: '沃达丰', joinDate: '2010-07-15' },
        { id: 6, name: '王五', joinDate: '2021-03-20' }
      ],
      // 浏览器宽度
      screenWidth: 0,
      // 定时器
      timing: null,
      // loading图
      loading: true,
      // 时分秒
      dateDay: null,
      // 年月日
      dateYear: null,
      // 周几
      dateWeek: null,
      // 周几
      weekday: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
      // 轮播排行榜
      config: {
        data: [

        ]
      },
      // 左侧饼图文字
      numberData: [

      ],
      // 左侧轮播表格配置项
      board_info: {
        header: [],
        data: [

        ],
        evenRowBGC: '#020308',
        oddRowBGC: '#382B47',
        headerBGC: '#020308'
      },
      // 定义颜色
      colorList: {
        linearYtoG: {
          type: 'linear',
          x: 0,
          y: 0,
          x2: 1,
          y2: 1,
          colorStops: [
            {
              offset: 0,
              color: '#f5b44d'
            },
            {
              offset: 1,
              color: '#28f8de'
            }
          ]
        },
        linearGtoB: {
          type: 'linear',
          x: 0,
          y: 0,
          x2: 1,
          y2: 0,
          colorStops: [
            {
              offset: 0,
              color: '#43dfa2'
            },
            {
              offset: 1,
              color: '#28f8de'
            }
          ]
        },
        linearBtoG: {
          type: 'linear',
          x: 0,
          y: 0,
          x2: 1,
          y2: 0,
          colorStops: [
            {
              offset: 0,
              color: '#1c98e8'
            },
            {
              offset: 1,
              color: '#28f8de'
            }
          ]
        },
        areaBtoG: {
          type: 'linear',
          x: 0,
          y: 0,
          x2: 0,
          y2: 1,
          colorStops: [
            {
              offset: 0,
              color: 'rgba(35,184,210,.2)'
            },
            {
              offset: 1,
              color: 'rgba(35,184,210,0)'
            }
          ]
        }
      },
      // 锥形柱状图
      cone: {
        data: [

        ],
        colors: ['#e062ae', '#fb7293', '#e690d1', '#32c5e9', '#96bfff'],
        unit: '单位',
        showValue: true
      }
    }
  },

  mounted() {
    // 首次加载时,需要调用一次
    this.screenWidth = window.innerWidth
    this.setSize()
    // 窗口大小发生改变时,调用一次
    window.onresize = () => {
      this.screenWidth = window.innerWidth
      this.setSize()
    }
    // 获取实时时间
    this.timeFn()
    // 加载loading图
    this.cancelLoading()
    // 中国地图
    this.china_map()
    // 左侧玫瑰饼图

    // 虚线柱状图

    // 左侧柱状图

    // 中间折线图
    this.line_center_diagram()
    // 虚线柱状图
    // 虚线柱状图
  },
  beforeDestroy() {
    // 离开时删除计时器
    clearInterval(this.timing)
  },
  methods: {
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => { })
    },
    setSize: function() {
      // 通过浏览器宽度(图片宽度)实现计算高度
      this.bannerHeight = 400 / 1920 * this.screenWidth
    },
    // 右上角当前日期时间显示：每一秒更新一次最新时间
    timeFn() {
      this.timing = setInterval(() => {
        // 获取当前时分秒
        this.dateDay = formatTime(new Date(), 'HH: mm: ss')
        // 获取当前年月日
        this.dateYear = formatTime(new Date(), 'yyyy-MM-dd')
        // 获取当前周几
        this.dateWeek = this.weekday[new Date().getDay()]
      }, 1000)
    },
    // loading图
    cancelLoading() {
      setTimeout(() => {
        this.loading = false
      }, 500)
    },

    // 中国地图
    china_map() {
      // const mapChart = this.$echarts.init(document.getElementById('china-map')) // 图表初始化，china-map是绑定的元素
      // window.onresize = mapChart.resize // 如果容器变大小，自适应从新构图
      // const series = [] // 存放循环配置项
      // const res = [] // 存放射线的起始点和结束点位置
      // const province = [] // 存放有射线的省的名字，以此来拿到对应省份的坐标
      // // 提前存好的所有省份坐标，用于后面根据名字拿到对应的左边
      // const chinaGeoCoordMap = {
      //   新疆: [86.9023, 41.148],
      //   西藏: [87.8695, 31.6846],
      //   内蒙古: [110.5977, 41.3408],
      //   青海: [95.2402, 35.4199],
      //   四川: [102.9199, 30.1904],
      //   黑龙江: [128.1445, 46.7156],
      //   甘肃: [102.7129, 38.166],
      //   云南: [101.0652, 24.6807],
      //   广西: [108.7813, 23.6426],
      //   湖南: [111.5332, 27.3779],
      //   陕西: [108.5996, 33.7396],
      //   广东: [113.8668, 22.8076],
      //   吉林: [126.1746, 43.5938],
      //   河北: [115.4004, 38.1688],
      //   湖北: [112.2363, 30.8572],
      //   贵州: [106.6113, 26.6385],
      //   山东: [118.2402, 36.2307],
      //   江西: [115.7156, 27.99],
      //   河南: [113.0668, 33.8818],
      //   辽宁: [123.0438, 41.0889],
      //   山西: [112.4121, 36.6611],
      //   安徽: [117.2461, 31.0361],
      //   福建: [118.3008, 25.9277],
      //   浙江: [120.498, 29.0918],
      //   江苏: [119.8586, 32.915],
      //   重庆: [107.7539, 29.8904],
      //   宁夏: [105.9961, 37.1096],
      //   海南: [109.9512, 19.2041],
      //   台湾: [120.8254, 23.5986],
      //   北京: [116.4551, 40.2539],
      //   天津: [117.4219, 39.4189],
      //   上海: [121.4648, 31.2891],
      //   香港: [114.6178, 22.3242],
      //   澳门: [113.5547, 21.6484]
      // }
      // // 后台给的数据模拟
      // const lineData = [
      //   {
      //     val: 32, // 数据
      //     blat: [86.9023, 41.148], // 发射点
      //     elon: [87.8695, 31.6846], // 接收点
      //     bcitysim: '新疆', // 发射省的名字
      //     ecitysim: '西藏' // 接收省的名字
      //   },
      //   {
      //     val: 31,
      //     blat: [87.8695, 31.6846],
      //     elon: [95.2402, 35.4199],
      //     bcitysim: '西藏',
      //     ecitysim: '青海'
      //   },
      //   {
      //     val: 33,
      //     blat: [86.9023, 41.148],
      //     elon: [95.2402, 35.4199],
      //     bcitysim: '新疆',
      //     ecitysim: '青海'
      //   },
      //   {
      //     val: 33,
      //     blat: [116.4551, 40.2539],
      //     elon: [119.8586, 32.915],
      //     bcitysim: '北京',
      //     ecitysim: '江苏'
      //   },
      //   {
      //     val: 33,
      //     blat: [120.8254, 23.5986],
      //     elon: [109.9512, 19.2041],
      //     bcitysim: '台湾',
      //     ecitysim: '海南'
      //   },
      //   {
      //     val: 33,
      //     blat: [120.498, 29.0918],
      //     elon: [115.7156, 27.99],
      //     bcitysim: '浙江',
      //     ecitysim: '江西'
      //   },
      //   {
      //     val: 33,
      //     blat: [117.2461, 31.0361],
      //     elon: [119.8586, 32.915],
      //     bcitysim: '安徽',
      //     ecitysim: '江苏'
      //   },
      //   {
      //     val: 33,
      //     blat: [117.2461, 31.0361],
      //     elon: [105.9961, 37.1096],
      //     bcitysim: '安徽',
      //     ecitysim: '宁夏'
      //   },
      //   {
      //     val: 33,
      //     blat: [117.2461, 31.0361],
      //     elon: [107.7539, 29.8904],
      //     bcitysim: '安徽',
      //     ecitysim: '重庆'
      //   },
      //   {
      //     val: 33,
      //     blat: [117.2461, 31.0361],
      //     elon: [123.0438, 41.0889],
      //     bcitysim: '安徽',
      //     ecitysim: '辽宁'
      //   },
      //   {
      //     val: 33,
      //     blat: [119.8586, 32.915],
      //     elon: [102.7129, 38.166],
      //     bcitysim: '江苏',
      //     ecitysim: '甘肃'
      //   },
      //   {
      //     val: 33,
      //     blat: [119.8586, 32.915],
      //     elon: [128.1445, 46.7156],
      //     bcitysim: '江苏',
      //     ecitysim: '黑龙江'
      //   },
      //   {
      //     val: 33,
      //     blat: [119.8586, 32.915],
      //     elon: [110.5977, 41.3408],
      //     bcitysim: '江苏',
      //     ecitysim: '内蒙古'
      //   },
      //   {
      //     val: 33,
      //     blat: [119.8586, 32.915],
      //     elon: [101.0652, 24.6807],
      //     bcitysim: '江苏',
      //     ecitysim: '云南'
      //   },
      //   {
      //     val: 33,
      //     blat: [119.8586, 32.915],
      //     elon: [86.9023, 41.148],
      //     bcitysim: '江苏',
      //     ecitysim: '新疆'
      //   },
      //   {
      //     val: 33,
      //     blat: [86.9023, 41.148],
      //     elon: [110.5977, 41.3408],
      //     bcitysim: '新疆',
      //     ecitysim: '内蒙古'
      //   },
      //   {
      //     val: 33,
      //     blat: [86.9023, 41.148],
      //     elon: [102.9199, 30.1904],
      //     bcitysim: '新疆',
      //     ecitysim: '四川'
      //   }
      // ]
      // 循环拿到处理好的数据
      // for (var i = 0; i < lineData.length; i++) {
      //   province.push(lineData[i].bcitysim) // 存进去每个省的名字
      //   province.push(lineData[i].ecitysim) // 存进去每个省的名字
      //   res.push({
      //     fromName: lineData[i].bcitysim, // 发射的省名，保存用于弹框显示
      //     toName: lineData[i].ecitysim, // 接收的省名，保存用于弹框显示
      //     coords: [
      //       lineData[i].blat, // 发射
      //       lineData[i].elon // 接收
      //     ],
      //     count: lineData[i].val // 数据
      //   })
      // }
      // const index_data = new Set(province) // 把省的名字去重
      // const data_res = [] // 定义一个新的变量存放省的坐标

      // // 注意这里一定要用name和value的形式。不是这个格式的散点图显示不出来
      // index_data.forEach((item) => {
      //   data_res.push({
      //     name: item, // 每个省的名字
      //     value: chinaGeoCoordMap[item] // 每个省的坐标
      //   })
      // })
      // // 循环往series内添加配置项
      // series.push(
      //   {
      //     // 射线效果图层
      //     type: 'lines', // 类型：射线
      //     zlevel: 1, // 类似图层效果
      //     effect: {
      //       show: true, // 是否显示图标
      //       symbol: 'arrow', // 箭头图标
      //       symbolSize: 5, // 图标大小
      //       trailLength: 0.02 // 特效尾迹长度[0,1]值越大，尾迹越长重
      //     },
      //     label: {
      //       show: true
      //     },
      //     lineStyle: {
      //       color: '#fff',
      //       normal: {
      //         color: '#00A0FF',
      //         width: 1, // 尾迹线条宽度
      //         opacity: 0.5, // 尾迹线条透明度
      //         curveness: 0.1 // 尾迹线条曲直度
      //       }
      //     },
      //     // 提示框信息
      //     tooltip: {
      //       formatter: function(param) {
      //         return (
      //           param.data.fromName +
      //           '>' +
      //           param.data.toName +
      //           '<br>数量：' +
      //           param.data.count
      //         )
      //       }
      //     },
      //     data: res // 拿到射线的起始点和结束点
      //   },
      // 散点图
      // {
      //   type: "effectScatter",//散点图
      //   coordinateSystem: "geo",//这个不能删，删了不显示
      //   zlevel: 1,
      //   rippleEffect: {
      //     //涟漪特效
      //     period: 4, //动画时间，值越小速度越快
      //     brushType: "stroke", //波纹绘制方式 stroke, fill
      //     scale: 4, //波纹圆环最大限制，值越大波纹越大
      //   },
      //   //设置文字部分
      //   label: {
      //     normal: {
      //       show: true, //省份名显示隐藏
      //       position: "right", //省份名显示位置
      //       offset: [5, 0], //省份名偏移设置
      //       formatter: function (params) {
      //         //圆环显示省份名
      //         return params.name;  //这个名字是根据data中的name来显示的
      //       },
      //       fontSize: 12,//文字大小
      //     },
      //     emphasis: {
      //       show: true,
      //     },
      //   },
      //   symbol: "circle",//散点图
      //   symbolSize: 5,//散点大小
      //   itemStyle: {//散点样式
      //     normal: {
      //       show: true,
      //       color: "#fff",
      //     },
      //   },
      //   data: data_res, //处理好后的散点图坐标数组
      // },
      // 点击高亮
      //     {
      //       type: 'map',
      //       mapType: 'china',
      //       zlevel: 1,
      //       roam: true,
      //       geoIndex: 0,
      //       tooltip: {
      //         show: true
      //       },
      //       itemStyle: {
      //         areaColor: '#00196d',
      //         borderColor: '#0a53e9'
      //       },
      //       emphasis: {
      //         show: true,
      //         label: {
      //           normal: {
      //             show: true, // 是否显示对应地名
      //             textStyle: {
      //               color: '#fff'
      //             }
      //           }
      //         },
      //         itemStyle: {
      //           areaColor: '#00196d',
      //           borderColor: '#0a53e9'
      //         }
      //       }
      //     }
      //   )
      //   // 配置
      //   const option = {
      //     // title可要可不要

    //     // title: {
    //     //   text: "查查的地图",
    //     //   textStyle: {
    //     //     color: "#ffffff",
    //     //   },
    //     // },
    //     legend: {
    //       show: true,
    //       selected: {},
    //       x: 'left',
    //       orient: 'vertical',
    //       textStyle: {
    //         color: 'white'
    //       },
    //       data: []
    //     },
    //     // 鼠标移上去的弹框
    //     tooltip: {
    //       trigger: 'item',
    //       show: true
    //     },
    //     // geo：这是重点
    //     geo: {
    //       map: '', // 中国地图
    //       zoom: 1.2, // 缩放倍数
    //       roam: false, // 是否允许缩放和拖拽地图
    //       label: {
    //         normal: {
    //           show: true, // 是否显示省份名字，现在是隐藏的状态，因为和散点图的名字重叠了。如果需要就true
    //           textStyle: {
    //             // 名字的样式
    //             color: '#fff'
    //           }
    //         },
    //         emphasis: {
    //           show: true
    //         }
    //       },
    //       // 地图样式
    //       itemStyle: {
    //         normal: {
    //           borderColor: '#293171', // 地图边框颜色
    //           borderWidth: '2', // 地图边框粗细
    //           areaColor: '#0A0F33' // 地图背景色
    //         },
    //         // 省份地图阴影
    //         emphasis: {
    //           areaColor: null,
    //           shadowOffsetX: 0,
    //           shadowOffsetY: 0,
    //           shadowBlur: 20,
    //           borderWidth: 0,
    //           shadowColor: '#fff'
    //         }
    //       }
    //     },
    //     series: series // 图表配置
    //   }
    //   mapChart.setOption(option) // 生成图表
    // },
    // 玫瑰饼图
    // Rose_diagram() {
      // const mapChart = this.$echarts.init(
      //   document.getElementById('Rose_diagram')
      // ) // 图表初始化，china-map是绑定的元素
      // window.onresize = mapChart.resize // 如果容器变大小，自适应从新构图
      // const option = {
      //   color: [
      //     '#37a2da',
      //     '#32c5e9',
      //     '#9fe6b8',
      //     '#ffdb5c',
      //     '#ff9f7f',
      //     '#fb7293',
      //     '#e7bcf3',
      //     '#8378ea'
      //   ],
      //   tooltip: {
      //     trigger: 'item',
      //     formatter: '{a} <br/>{b} : {c} ({d}%)'
      //   },
      //   toolbox: {
      //     show: true
      //   },
      //   calculable: true,
      //   legend: {
      //     orient: 'horizontal',
      //     icon: 'circle',
      //     bottom: 0,
      //     x: 'center',
      //     data: ['周口店', '漯河', '商丘', '平顶山', '随州', '郑州'],
      //     textStyle: {
      //       color: '#fff'
      //     }
      //   },
      //   series: [
      //     {
      //       name: '政务事件',
      //       type: 'pie',
      //       radius: [10, 50],
      //       roseType: 'area',
      //       center: ['50%', '40%'],
      //       data: [
      //         { value: 10, name: '周口店' },
      //         { value: 5, name: '漯河' },
      //         { value: 15, name: '商丘' },
      //         { value: 25, name: '平顶山' },
      //         { value: 20, name: '随州' },
      //         { value: 35, name: '郑州' }
      //       ]
      //     }
      //   ]
      // }
      // mapChart.setOption(option) // 生成图表
    // },
    // 柱状图
    // columnar() {
      // const mapChart = this.$echarts.init(document.getElementById('columnar')) // 图表初始化，china-map是绑定的元素
      // window.onresize = mapChart.resize // 如果容器变大小，自适应从新构图
      // const option = {
      //   title: {
      //     text: ''
      //   },
      //   tooltip: {
      //     trigger: 'axis',
      //     backgroundColor: 'rgba(255,255,255,0.1)',
      //     axisPointer: {
      //       type: 'shadow',
      //       label: {
      //         show: true,
      //         backgroundColor: '#7B7DDC'
      //       }
      //     }
    },
    //     legend: {
    //       data: ['已贯通', '计划贯通', '贯通率'],
    //       textStyle: {
    //         color: '#B4B4B4'
    //       },
    //       top: '0%'
    //     },
    //     grid: {
    //       x: '8%',
    //       width: '95%',
    //       y: '4%'
    //     },
    //     xAxis: {
    //       data: [
    //         '市区',
    //         '万州',
    //         '江北',
    //         '南岸',
    //         '北碚',
    //         '綦南',
    //         '长寿',
    //         '永川',
    //         '璧山',
    //         '江津',
    //         '城口',
    //         '大足',
    //         '垫江',
    //         '丰都',
    //         '奉节',
    //         '合川',
    //         '江津区',
    //         '开州',
    //         '南川',
    //         '彭水',
    //         '黔江',
    //         '石柱',
    //         '铜梁',
    //         '潼南',
    //         '巫山',
    //         '巫溪',
    //         '武隆',
    //         '秀山',
    //         '酉阳',
    //         '云阳',
    //         '忠县',
    //         '川东',
    //         '检修'
    //       ],
    //       axisLine: {
    //         lineStyle: {
    //           color: '#B4B4B4'
    //         }
    //       },
    //       axisTick: {
    //         show: false
    //       }
    //     },
    //     yAxis: [
    //       {
    //         splitLine: { show: false },
    //         axisLine: {
    //           lineStyle: {
    //             color: '#B4B4B4'
    //           }
    //         },

    //         axisLabel: {
    //           formatter: '{value} '
    //         }
    //       }
    //     ],
    //     series: [
    //       {
    //         name: '已贯通',
    //         type: 'bar',
    //         barWidth: 10,
    //         itemStyle: {
    //           normal: {
    //             barBorderRadius: 5,
    //             color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
    //               { offset: 0, color: '#956FD4' },
    //               { offset: 1, color: '#3EACE5' }
    //             ])
    //           }
    //         },
    //         data: [
    //           46, 50, 55, 650, 75, 85, 99, 125, 140, 215, 232, 244, 252, 333,
    //           46, 50, 55, 65, 75, 85, 99, 225, 140, 215, 85, 99, 125, 140, 215,
    //           232, 244, 252, 75
    //         ]
    //       },
    //       {
    //         name: '计划贯通',
    //         type: 'bar',
    //         barGap: '-100%',
    //         barWidth: 10,
    //         itemStyle: {
    //           normal: {
    //             barBorderRadius: 5,
    //             color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
    //               { offset: 0, color: 'rgba(156,107,211,0.8)' },
    //               { offset: 0.2, color: 'rgba(156,107,211,0.5)' },
    //               { offset: 1, color: 'rgba(156,107,211,0.2)' }
    //             ])
    //           }
    //         },
    //         z: -12,
    //         data: [
    //           180, 207, 240, 283, 328, 360, 398, 447, 484, 504, 560, 626, 595,
    //           675, 180, 207, 240, 283, 328, 360, 398, 447, 484, 504, 360, 398,
    //           447, 484, 504, 500, 326, 495, 328
    //         ]
    //       }
    //     ]
    //   }
    //   mapChart.setOption(option) // 生成图表
    // },
    // 折线图
    line_center_diagram() {
      const mapChart = this.$echarts.init(
        document.getElementById('line_center_diagram')
      ) // 图表初始化，china-map是绑定的元素
      window.onresize = mapChart.resize // 如果容器变大小，自适应从新构图
      const option = {
        xAxis: {
          type: 'category',
          data: ['2018', '2019', '2020', '2021', '2022', '2023', '2024'],
          position: 'bottom',
          axisLine: true,
          axisLabel: {
            color: 'rgba(255,255,255,.8)',
            fontSize: 12
          }
        },
        yAxis: {
          type: 'value',
          name: '每年党员人数',
          nameLocation: 'end',
          nameGap: 24,
          nameTextStyle: {
            color: 'rgba(255,255,255,.5)',
            fontSize: 14
          },
          splitNumber: 4,
          axisLine: {
            lineStyle: {
              opacity: 0
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#fff',
              opacity: 0.1
            }
          },
          axisLabel: {
            color: 'rgba(255,255,255,.8)',
            fontSize: 12
          }
        },
        grid: {
          left: 50,
          right: 10,
          bottom: 25,
          top: '18%'
        },
        series: [
          {
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line',
            smooth: true,
            symbol: 'emptyCircle',
            symbolSize: 8,
            itemStyle: {
              normal: {
                color: '#fff'
              }
            },
            // 线的颜色样式
            lineStyle: {
              normal: {
                color: this.colorList.linearBtoG,
                width: 3
              }
            },
            // 填充颜色样式
            areaStyle: {
              normal: {
                color: this.colorList.areaBtoG
              }
            }
          }
        ]
      }
      mapChart.setOption(option) // 生成图表
    }
    // 右侧虚线柱状图图
    // dotter_bar() {
    // const mapChart = this.$echarts.init(document.getElementById('dotter_bar')) // 图表初始化，china-map是绑定的元素
    // window.onresize = mapChart.resize // 如果容器变大小，自适应从新构图
    // // Generate data
    // const category = []
    // let dottedBase = +new Date()
    // const lineData = []
    // const barData = []
    // for (let i = 0; i < 20; i++) {
    //   const date = new Date((dottedBase += 3600 * 24 * 1000))
    //   category.push(
    //     [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-')
    //   )
    //   const b = parseInt(Math.random() * 2000)
    //   const d = parseInt(Math.random() * 2000)
    //   barData.push(b)
    //   lineData.push(d + b)
    // }
    // // option
    // const option = {
    //   tooltip: {
    //     trigger: 'axis',
    //     axisPointer: {
    //       type: 'shadow'
    //     }
    //   },
    //   grid: {
    //     left: 50,
    //     right: 10,
    //     bottom: 25,
    //     top: '18%'
    //   },
    //   legend: {
    //     data: ['出租房', '在售房'],
    //     textStyle: {
    //       color: '#ccc'
    //     }
    //   },
    //   xAxis: {
    //     data: category,
    //     axisLine: {
    //       lineStyle: {
    //         color: '#ccc'
    //       }
    //     }
    //   },
    //   yAxis: {
    //     splitLine: { show: false },
    //     axisLine: {
    //       lineStyle: {
    //         color: '#ccc'
    //       }
    //     }
    //   },
    //   series: [
    //     {
    //       name: '出租房',
    //       type: 'line',
    //       smooth: true,
    //       showAllSymbol: true,
    //       symbol: 'emptyCircle',
    //       symbolSize: 15,
    //       data: lineData
    //     },
    //     {
    //       name: '在售房',
    //       type: 'bar',
    //       barWidth: 10,
    //       itemStyle: {
    //         borderRadius: 5,
    //         // color: "#14c8d4",
    //         color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
    //           { offset: 0, color: '#14c8d4' },
    //           { offset: 1, color: '#43eec6' }
    //         ])
    //       },
    //       data: barData
    //     },
    //     {
    //       name: '商业房',
    //       type: 'bar',
    //       barGap: '-100%',
    //       barWidth: 10,
    //       itemStyle: {
    //         // color: "rgba(20,200,212,0.5)",
    //         color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
    //           { offset: 0, color: 'rgba(20,200,212,0.5)' },
    //           { offset: 0.2, color: 'rgba(20,200,212,0.2)' },
    //           { offset: 1, color: 'rgba(20,200,212,0)' }
    //         ])
    //       },
    //       z: -12,
    //       data: lineData
    //     },
    //     {
    //       name: '门面',
    //       type: 'pictorialBar',
    //       symbol: 'rect',
    //       itemStyle: {
    //         color: '#0f375f'
    //       },
    //       symbolRepeat: true,
    //       symbolSize: [12, 4],
    //       symbolMargin: 1,
    //       z: -10,
    //       data: lineData
    //     }
    //   ]
    // }
    // mapChart.setOption(option) // 生成图表
    // }

  }
}
</script>

<style lang="scss">
//全局样式部分！！！！
* {
  margin: 0;
  padding: 0;
  list-style-type: none;
  outline: none;
  box-sizing: border-box;
}

html {
  margin: 0;
  padding: 0;
}

body {
  font-family: Arial, Helvetica, sans-serif;
  line-height: 1.2em;
  background-color: #f1f1f1;
  margin: 0;
  padding: 0;
}

a {
  color: #343440;
  text-decoration: none;
}

//--------------------------------------------

//页面样式部分！！！！
#index {
  color: #d3d6dd;
  width: 1920px;
  height: 1080px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  transform-origin: left top;
  overflow: hidden;

  .bg {
    //整体页面背景
    width: 100%;
    height: 100%;
    padding: 16px 16px 0 16px;
    background-image: url('~@/assets/backimages/back2.png'); //背景图
    background-size: cover; //背景尺寸
    background-position: center center; //背景位置
  }

  //顶部右边装饰效果
  .title_left {
    width: 100%;
    height: 50px;
  }

  //顶部左边装饰效果
  .title_right {
    width: 100%;
    height: 50px;
    margin-top: 18px;
  }

  //顶部中间装饰效果
  .title_center {
    width: 100%;
    height: 50px;
  }

  //顶部中间文字数据可视化系统
  .title_text1 {
    text-align: center;
    font-size: 35px;
    font-weight: bold;
    margin-top: 14px;
    color: #ff0008;
  }
  //顶部中间文字数据可视化系统
  .title_text2 {
    text-align: center;
    font-size: 35px;
    font-weight: bold;
    margin-top: 14px;
    color: #008cff;
  }

  //时间日期
  .title_time {
    font-size: 35px;
    text-align: center;
  }

  //中国地图
  #china-map {
    height: 660px;
    width: 100%;
  }

  //中间折线图
  .line_center {
    width: 100%;
    height: 288px;
  }

  //左1模块
  .left_box1ok {
    height: 310px;
    width: 100%;
    margin-bottom: 10px;
    position: relative;
  }

  //左2模块
  .left_box2ok {
    height: 310px;
    width: 100%;
    margin-bottom: 50px;
    margin-top: -70px;
  }

  //左3模块
  .left_box3ok {
    height: 310px;
    width: 100%;
    margin-top: 10px;
  }

  //右1模块
  .right_box1 {
    height: 310px;
    width: 100%;
    margin-bottom: 10px;
  }

  //右2模块
  .right_box2 {
    height: 310px;
    width: 100%;
    margin-bottom: 10px;
  }

  //右3模块
  .right_box3 {
    height: 310px;
    width: 100%;
  }

  //左1模块-玫瑰饼图
  #Rose_diagram {
    height: 70%;
    width: 55%;
  }

  //左1模块-圆环图
  .left_box1_rose_right {
    height: 85%;
    width: 55%;
    position: absolute;
    right: 0;
    top: 0;
  }

  //左1模块-文字部分
  .rose_text {
    display: inline-block;
    margin-top: 4%;
    margin-left: 4%;
  }

  // 左1模块-￥符号样式
  .coin {
    font-size: 20px;
    color: #ffc107;
  }

  //左1模块-（件）样式
  .colorYellow {
    color: yellowgreen;
  }

  //左1模块-数字样式
  .rose_text_nmb {
    font-size: 20px;
    color: #00b891;
  }

  //左2模块 柱状图
  #columnar {
    height: 97%;
    width: 95%;
    margin-left: 3%;
    margin-top: 5px;
  }

  //折线图
  #line_center_diagram {
    height: 100%;
    width: 100%;
  }

  //轮播表格
  .carousel_list {
    width: 96%;
    height: 98%;
    margin-left: 10px;
  }

  //虚线柱状图
  #dotter_bar {
    width: 100%;
    height: 100%;
  }

  //锥形图
  .cone_box {
    width: 95%;
    height: 97%;
    margin-left: 3%;
  }
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

img {
  /*设置图片宽度和浏览器宽度一致*/
  width: 100%;
  height: inherit;
}
.box {
  border: 0px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  margin-bottom: 20px;
}

.title {
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 30px;
  color: #00b891;
}
.title2 {
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #00b891;
}

.section {
  display: flex;
  margin-bottom: 20px;
}

.label {
  width: 140px;
  font-size: 25px;
  font-family: Arial, sans-serif;
}
.label2 {
  font-size: 25px;
  font-family: Arial, sans-serif;
  line-height: 30px;
  margin-right: 5px;
  margin-left: 5px;
}

.value {
  font-size: 25px;
  flex: 2;
  font-family: Arial, sans-serif;
}
.transparent-list {
  background-color: transparent; /* 设置背景为透明 */
  border: none; /* 去除边框 */
}

.list-header {
  display: flex;
  border-bottom: 1px solid #ccc; /* 添加底部边框 */
}

.list-header .item {
  flex: 1;
  padding: 5px;
  font-size: 26px;
}

.list-item {
  display: flex;
  border-bottom: 1px solid #ccc; /* 添加底部边框 */
}

.list-item .item {
  flex: 1;
  padding: 5px;
  font-size: 24px;
}
</style>
