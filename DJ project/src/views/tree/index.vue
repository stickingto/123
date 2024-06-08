<template>
  <div class="app-container">
    <div class="filter-container">
      <!-- 当回车按键弹起的时候触发handleFilter方法，native阻止input默认事情-->
      <el-input v-model="listQuery.title" placeholder="个人简介" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <!-- 根据id进行升降处理 -->
      <!-- listQuery.sort与 item.key进行绑定从而改变listQuery中的参数-->
      <el-select v-model="listQuery.importance" placeholder="服务星数" clearable style="width: 120px" class="filter-item">
        <el-option v-for="item in importanceOptions" :key="item" :label="item" :value="item" />
      </el-select>
      <el-select v-model="listQuery.type" placeholder="类型" clearable class="filter-item" style="width: 130px">
        <el-option v-for="item in calendarTypeOptions" :key="item.key" :label="item.display_name+'('+item.key+')'" :value="item.key" />
      </el-select>
      <el-select v-model="listQuery.sort" style="width: 140px" class="filter-item" @change="handleFilter">
        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key" />
      </el-select>
      <!-- 搜索按钮 -->
      <!--饿了么的button 组件   v-waves使用水波纹特效 type设置样式  icon设置图标 @click触发方法 -->
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
      </el-button>
      <!-- 添加按钮 -->
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        添加
      </el-button>
      <!-- 导出按钮 -->
      <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
        导出
      </el-button>
      <!--<el-checkbox v-model="showReviewer" class="filter-item" style="margin-left:15px;" @change="tableKey=tableKey+1">
        reviewer
      </el-checkbox>-->
    </div>

    <!-- 这里就是下面的列表模块 -->
    <!--以上都是搜索框的内容  -->
    <!-- 下面是列表展示  -->
    <!-- v-loading 过渡效果 listLoading
             data 遍历的数组
             border 是否带有纵向边框
             fit 列的宽度是否自撑开
             @sort-change 当表格的排序条件发生变化的时候会触发该事件
 -->
    <!-- 表格data显示的数据 -->
    <!-- sortChange是下面的列表升降序箭头 -->
    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @sort-change="sortChange"
    >
      <!-- 表单的列组件  label  显示的标题  prop  对应列内容的字段名  sortable 对应列是否可以排序  align 对齐方式 -->

      <el-table-column label="编号" prop="id" sortable="custom" align="center" width="80" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.timestamp | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <!-- 这里绑定了一个修改数据的回调函数，和edit的是一样 -->

      <el-table-column label="个人简介" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type" @click="handleUpdate(row)">{{ row.title }}</span>
          <el-tag>{{ row.type | typeFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="姓名" width="110px" align="center">
        <template slot-scope="{row}">
          <span class="link-type" @click="handleUpdate(row)">{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column v-if="showReviewer" label="Reviewer" width="110px" align="center">
        <template slot-scope="{row}">
          <span style="color:red;">{{ row.reviewer }}</span>
        </template>
      </el-table-column>
      <el-table-column label="服务星数" width="80px">
        <template slot-scope="{row}">
          <svg-icon v-for="n in + row.importance" :key="n" icon-class="star" class="meta-item__icon" />
        </template>
      </el-table-column>
      <el-table-column label="浏览人次" align="center" width="95">
        <template slot-scope="{row}">
          <span v-if="row.pageviews" class="link-type" @click="handleFetchPv(row.pageviews)">{{ row.pageviews }}</span>
          <span v-else>0</span>
        </template>
      </el-table-column>

      <!-- 状态栏 -->
      <el-table-column label="状态" class-name="status-col" width="100">
        <template slot-scope="{row}">
          <el-tag :type="row.status | statusFilter">
            {{ row.status }}
          </el-tag>
        </template>
      </el-table-column>

      <!-- 这里是我们的操作栏 -->
      <el-table-column label="管理" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)">
            编辑
          </el-button>
          <el-button v-if="row.status!='休息中'" size="mini" type="success" @click="handleModifyStatus(row,'休息中')">
            休息中
          </el-button>
          <el-button v-if="row.status!='工作中'" size="mini" @click="handleModifyStatus(row,'工作中')">
            工作中
          </el-button>
          <el-button v-if="row.status!='删除'" size="mini" type="danger" @click="handleDelete(row,$index)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 这里是下面的分页器 -->
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!--创建表格-->
    <!-- 下面分别是编辑页面，以及添加页面，这里应该是定位的形式出现 -->
    <!-- 饿了么的对话框组件  这里用于展示 新增修改界面
       :title="textMap[dialogStatus]"  标题为下面声明的textMap数组中的dialogStatus key 的值
       :visible.sync="dialogFormVisible"   是否显示 Dialog 传入true展示 flase隐藏 这里界面初始化时为false 点击新增和修改时修改为true
 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <!-- 这部分是添加的表单 -->
      <!-- 饿了么的表单组件
    ref  给表单起个别名dataForm 下面可以用this.$ref.dataForm获取表单dom
    rules设置表单数据校验规则为rules，rules在下面vue中声明  label-position标题对齐方式
-->
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <!--新增的name-->
        <el-form-item label="姓名" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="temp.type" class="filter-item" placeholder="请选择">
            <el-option v-for="item in calendarTypeOptions" :key="item.key" :label="item.display_name" :value="item.key" />
          </el-select>
        </el-form-item>
        <el-form-item label="创建时间" prop="timestamp">
          <el-date-picker v-model="temp.timestamp" type="datetime" placeholder="请选择" />
        </el-form-item>
        <el-form-item label="个人简介" prop="title">
          <el-input v-model="temp.title" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="temp.status" class="filter-item" placeholder="请选择">
            <el-option v-for="item in statusOptions" :key="item" :label="item" :value="item" />
          </el-select>
        </el-form-item>
        <el-form-item label="服务星数">
          <el-rate v-model="temp.importance" :colors="['#99A9BF', '#F7BA2A', '#FF9900']" :max="3" style="margin-top:8px;" />
        </el-form-item>
        <!--<el-form-item label="Remark">
          <el-input v-model="temp.remark" :autosize="{ minRows: 2, maxRows: 4}" type="textarea" placeholder="Please input" />
        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          确认创建
        </el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogPvVisible" title="Reading statistics">
      <el-table :data="pvData" border fit highlight-current-row style="width: 100%">
        <el-table-column prop="key" label="Channel" />
        <el-table-column prop="pv" label="Pv" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPvVisible = false">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
// 从api中引入与后台交互的请求方法
import { fetchList, fetchPv, createArticle, updateArticle } from '@/api/article'
// 引入水波纹
import waves from '@/directive/waves' // waves directive
// 格式化时间
import { parseTime } from '@/utils'
// 分页组件
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

// calendarTypeOptions  选择框中的下拉数组
const calendarTypeOptions = [
  { key: '党员', display_name: '共产党' },
  { key: '团员', display_name: '青年团' },
  { key: '志愿', display_name: '青年志愿者' },
  { key: '群众', display_name: '中国公民' }
]

// arr to obj, such as { CN : "China", US : "USA" }
const calendarTypeKeyValue = calendarTypeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.display_name
  return acc
}, {})

export default {
  name: 'ComplexTable',
  // 引用的组件
  components: { Pagination },
  // 自定义组件引入水波纹特效
  directives: { waves },
  filters: {
    // 状态转换器，控制状态tag显示的颜色
    statusFilter(status) {
      const statusMap = {
        休息中: 'success',
        工作中: 'info',
        删除: 'danger'
      }
      return statusMap[status]
    },
    typeFilter(type) {
      return calendarTypeKeyValue[type]
    }
  },
  // 定义基础数据
  data() {
    return {
      tableKey: 0,
      // 获取过来的数据
      // 先让页面显示这些静态的数据
      list: null,
      // 目前中list中数据的条数
      total: 0,
      listLoading: true,
      // 这个时我们发送fetchList需要携带的参数
      listQuery: {
        // 请求的是第几页的数据
        page: 1,
        // 请求数据的多少
        limit: 20,
        importance: undefined,
        name: undefined,
        title: undefined,
        type: undefined,
        // 控制后端发送过来的数据的升降
        sort: '+id'
      },
      // 这部分数据是控制列表中的升降序
      // label组件的显示名，每个选项的key
      importanceOptions: [1, 2, 3],
      calendarTypeOptions,
      sortOptions: [{ label: '编号升序', key: '+id' }, { label: '编号降序', key: '-id' }],
      // 修改页面中的选择状态框
      statusOptions: ['休息中', '工作中'],
      showReviewer: false,
      // 新增与修改界面使用的缓存数据
      temp: {
        id: undefined,
        importance: 1,
        remark: '',
        timestamp: new Date(),
        title: '',
        type: '',
        status: '休息中'
      },
      // 对话框展示控制属性
      dialogFormVisible: false,
      dialogStatus: '',
      // 对话框标题数组
      //  update，create是 dialogStatus的值，  :title="textMap[dialogStatus]"控制dialog显示添加还是编辑
      textMap: {
        update: '编辑',
        create: '添加'
      },
      // 控制修改页面能否显示
      dialogPvVisible: false,
      // 通过这个状态点击确认时执行修改方法还是编辑方法
      pvData: [],
      rules: {
        type: [{ required: true, message: '未选择类型', trigger: 'change' }],
        timestamp: [{ type: 'date', required: true, message: '未选择时间', trigger: 'change' }],
        title: [{ required: true, message: '未填写简介', trigger: 'blur' }]
      },
      // 控制导出按钮的loing?
      downloadLoading: false
    }
  },
  // 钩子函数，当界面创建的时候调用.getList() 获取列表数据
  created() {
    this.getList()
  },
  methods: {
    // 获取列表数据
    getList() {
      // 将table设置 载入效果
      this.listLoading = true
      // 从后台请求数据
      fetchList(this.listQuery).then(response => {
        // 将获取到的数据替换data中的数据
        this.list = response.data.items
        // 下面这个是每次请求的数量也就是我们的每一页的参数
        this.total = response.data.total

        // Just to simulate the time of the request
        // 模拟请求的延迟，正式开发去掉
        setTimeout(() => {
          // 取消tabel的载入效果，展示数据
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    //  这个是搜索函数
    //  搜索框绑定了这个函数，但是getList()，搞好接口好应该可以使用
    // 搜索这里是直接对接后端的所以我这里不好修改
    // 它是通过获取限制条件下取得的数据然后直接重新渲染列表
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    // 处理我们的状态
    handleModifyStatus(row, status) {
      this.$message({
        message: '操作成功',
        type: 'success'
      })
      row.status = status
    },
    // 这个是升降箭头的回调函数
    // 下面两个函数展示都用不了因为后端没搭建好
    sortChange(data) {
      const { prop, order } = data
      if (prop === 'id') {
        this.sortByID(order)
      }
    },
    sortByID(order) {
      if (order === 'ascending') {
        this.listQuery.sort = '+id'
      } else {
        this.listQuery.sort = '-id'
      }
      // 先不用后端接口
      this.handleFilter()
    },
    // 重置temp缓存数据 在每次点击新增和修改时调用，避免数据出错
    resetTemp() {
      this.temp = {
        id: undefined,
        importance: 1,
        remark: '',
        timestamp: new Date(),
        name: '',
        title: '',
        status: '休息中',
        type: ''
      }
    },
    // 点击新增按钮触发的方法  展示新增界面
    handleCreate() {
      // 清空temp数据
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      // 当下一次vue的dom元素更新时执行
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 出现添加页面后，创建数据方法
    createData() {
      // 饿了么form表单前端校验的固定写法，当点击确定新增数据时，再校验一遍数据是否符合rules
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          // 设置基础数据
          this.temp.id = parseInt(Math.random() * 100) + 1024 // mock a id
          this.temp.author = ''
          // 到后端请求新增数据
          createArticle(this.temp).then(() => {
            // 删除list中的数据
            this.list.unshift(this.temp)
            // 将dialog对话框隐藏
            this.dialogFormVisible = false
            // 这里添加成功后弹出一个消息窗口
            this.$notify({
              title: '成功',
              message: '创建成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    // 点击修改执行的方法  显示修改界面
    handleUpdate(row) {
      // 将table中的row数据深拷贝到temp 中
      //  将那一行要修改的数据先放到，修改框中
      this.temp = Object.assign({}, row) // copy obj
      //  设置dialog基础数据
      //  随机生成一个时间后面，可以用后端提供的数据替换
      this.temp.timestamp = new Date(this.temp.timestamp)
      // 将页面显示为修改页面
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      // 当下一次vue的dom元素更新时执行
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 修改页面中确认按钮绑定的回调函数
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          tempData.timestamp = +new Date(tempData.timestamp) // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
          updateArticle(tempData).then(() => {
            const index = this.list.findIndex(v => v.id === this.temp.id)
            this.list.splice(index, 1, this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: '成功',
              message: '创建成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    // 这个是删除按钮的回调函数
    handleDelete(row, index) {
      this.$notify({
        title: '成功',
        message: '删除成功',
        type: 'success',
        duration: 2000
      })
      this.list.splice(index, 1)
    },
    handleFetchPv(pv) {
      fetchPv(pv).then(response => {
        this.pvData = response.data.pvData
        this.dialogPvVisible = true
      })
    },
    // 导出按钮的回调函数
    // 先不管我知道里面的信息做了
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['timestamp', 'title', 'type', 'importance', 'status']
        const filterVal = ['timestamp', 'title', 'type', 'importance', 'status']
        const data = this.formatJson(filterVal)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: 'table-list'
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal) {
      return this.list.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    },
    getSortClass: function(key) {
      const sort = this.listQuery.sort
      // 返回升序和降序order
      return sort === `+${key}` ? 'ascending' : 'descending'
    }
  }
}
</script>
