<template>
  <div class="box">
    <el-card class="box-card">
      <h2 style="font-size: xx-large;margin-left: 160px;bottom: 100px;">注册</h2>
      <el-form
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        label-position="left"
        label-width="80px"
        class="demo-ruleForm"
      >
        <el-form-item label="用户名" prop="uname">
          <el-input v-model="ruleForm.uname"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass">
          <el-input
            type="password"
            v-model="ruleForm.pass"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="password">
          <el-input
            type="password"
            v-model="ruleForm.password"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div class="btnGroup">
        <el-button
        type = "primary"
        @click="submitForm('ruleForm')"
          >提交</el-button
        >
        <el-button @click="resetForm('ruleForm')">重置</el-button>
        <router-link to="/login"><el-button>返回</el-button></router-link>
      </div>
    </el-card>
</div>
</template>

<script>
import { router } from '@/router'
import axios from 'axios'
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'))
      } else callback()
    }
    return {
      ruleForm: {
        uname: '',
        pass: '',
        password: ''
      },
      rules: {
        uname: [
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ],
        pass: [{ required: true, validator: validatePass, trigger: 'blur' }],
        password: [
          { required: true, validator: validatePass2, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const name = this.ruleForm.uname
          const pass = this.ruleForm.pass
          axios({
            methods: 'get',
            url: 'http://localhost:8080/ol/register',
            params: {
              name: name,
              pass: pass
            }
          }).then(Result => {
            const a = Result.data
            if (a === 1) {
              alert('注册成功')
              this.$router.push({ path: '/login' })
            } else if (a === 2) {
              alert('用户名已被注册过')
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    goBack() {
      router.push({ path: '/login' })
    }
  }
}

</script>

  <style scoped>
  .box{
    width: 100%;
    height: 100vh;
    background:url("../../assets/backimages/image.jpg") no-repeat;
    background-size: cover;
  }
  .box-card {
  position: relative;
  top: 100px;
    width: 520px;
    max-width: 100%;
    padding: 30px 35px 0 40px;
    margin: 0 auto;
  }
  .login-from {
  margin: auto auto;
}
</style>
