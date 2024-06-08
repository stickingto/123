<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="党建活动名称">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="发布机构">
        <el-select v-model="form.region" placeholder="请选择机构">
          <el-option label="第一所" value="第一所" />
          <el-option label="第二所" value="第二所" />
          <el-option label="第三所" value="第三所" />
          <el-option label="第四所" value="第四所" />
          <el-option label="第五所" value="第五所" />
          <el-option label="第六所" value="第六所" />
        </el-select>
        <!-- 发布机构 -->
      </el-form-item>
      <el-form-item label="党建时间">
        <el-col :span="11">
          <el-date-picker v-model="form.date1" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择一个日期" style="width: 100%;" />
        </el-col>
        <!-- <el-col :span="11">
          <el-time-picker v-model="form.date2" type="fixed-time" placeholder="确定时间" style="width: 100%;" />
        </el-col> -->
      </el-form-item>
      <el-form-item label="通知创建人">
        <el-input v-model="form.person" style="width: 20%;"/>
      </el-form-item>
      <el-form-item label="活动形式">
        <el-radio-group v-model="form.type">
          <el-radio label="线上会议" name="type" />
          <el-radio label="线下会议" name="type" />
          <el-radio label="非正式会议" name="type" />
          <el-radio label="正式会议" name="type" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="性质">
      <el-radio-group v-model="form.xingzhi">
          <el-radio label="通报" name="type" />
          <el-radio label="新闻" name="type" />
          <el-radio label="通知" name="type" />
          <el-radio label="会议" name="type" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="活动内容">
        <el-input v-model="form.desc" type="textarea" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">创建</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    }
  },
  methods: {
    onSubmit() {
      axios({
        method: 'post',
        url: 'http://localhost:8080/chu/tong',
        params: {
          name: this.form.name,
          diqu: this.form.region,
          neirong: this.form.desc,
          date: this.form.date1,
          person: this.form.person,
          xingzhi: this.form.xingzhi
        }
      }).then(Result => {
        alert(Result.data)
      }).catch(Error => {
        console.log(2)
      })
      alert('提交成功！')
      location.reload()
    },
    onCancel() {
      this.$message({
        message: '已取消!',
        type: 'warning'
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

