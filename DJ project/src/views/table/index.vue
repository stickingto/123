<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="编号" width="95">
        <template slot-scope="scope">
          {{ scope.$index+1 }}
        </template>
      </el-table-column>
      <el-table-column label="标题">
        <template slot-scope="scope">
          {{ scope.row.title }}
        </template>
      </el-table-column>
      <el-table-column label="发布人" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.person }}</span>
        </template>
      </el-table-column>
      <el-table-column label="地区" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.diqu }}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="性质" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.xingzhi | statusFilter">{{ scope.row.xingzhi }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="发布时间" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.time }}</span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  filters: {
    statusFilter(xingzhi) {
      const statusMap = {
        通知: 'success',
        新闻: 'gray',
        通报: 'danger',
        会议: 'warning'
      }
      return statusMap[xingzhi]
    }
  },
  data() {
    return {
      list: null,
      listLoading: true
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      axios({
        method: 'post',
        url: 'http://localhost:8080/chu/show',
        params: {
        }
      }).then(response => {
        this.list = response.data
        this.listLoading = false
      }).catch(Error => {
        console.log('"aaaa"')
      })
    }
  }
}
</script>
