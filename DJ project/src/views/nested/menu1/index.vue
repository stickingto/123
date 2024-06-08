<template>
  <div class="app-container">
    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="编号" width="80">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column width="180px" align="center" label="日期">
        <template slot-scope="{row}">
          <span>{{ row.time }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="汇报人">
        <template slot-scope="{row}">
          <span>{{ row.person }}</span>
        </template>
      </el-table-column>

      <el-table-column width="100px" label="汇报等级">
        <template slot-scope="{row}">
          <svg-icon v-for="n in + row.grade" :key="n" icon-class="star" class="meta-item__icon" />
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="汇报状态" width="110">
        <template slot-scope="{row}">
          <el-tag :type="row.stage | statusFilter">
            {{ row.stage }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column min-width="300px" label="工作汇报标题">
        <template slot-scope="{row}">
          <template v-if="row.edit">
            <el-input v-model="row.title" class="edit-input" size="small" />
            <el-button
              class="cancel-btn"
              size="small"
              icon="el-icon-refresh"
              type="warning"
              @click="cancelEdit(row)"
            >
              取消
            </el-button>
          </template>
          <span v-else>{{ row.title }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="{row}">
          <el-button
            v-if="row.edit"
            type="success"
            size="small"
            icon="el-icon-circle-check-outline"
            @click="confirmEdit(row)"
          >
            确定
          </el-button>
          <el-button
            v-else
            type="primary"
            size="small"
            icon="el-icon-edit"
            @click="row.edit=true"
          >
            编辑
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'InlineEditTable',
  filters: {
    statusFilter(stage) {
      const statusMap = {
        已汇报: 'success',
        修改中: 'gray'
      }
      return statusMap[stage]
    }
  },
  data() {
    return {
      list: null,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    async getList() {
      this.listLoading = true
      axios.get('http://localhost:8080/work/show').then(res => {
        this.list = res.data
        console.log('成功1')
        console.log(this.list)
      }).catch(Error => {
        console.log('shjiabile')
      })
      this.listLoading = false
    },
    cancelEdit(row) {
      row.edit = false
      this.$message({
        message: '标题已恢复',
        type: 'warning'
      })
    },
    confirmEdit(row) {
      axios.get('http://localhost:8080/work/changetitle',{params:{
        id: row.id,
        title: row.title
      }}).then(res => {
        console.log(row.title)
      }).catch(Error => {
        console.log('是啊比')
      })
      row.edit = false
      this.$message({
        message: '修改成功',
        type: 'success'
      })
    }
  }
}
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>
