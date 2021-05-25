<template>
  <el-form
    :model="userForm"
    status-icon
    :rules="rules"
    ref="userForm"
    label-width="100px"
    class="demo-userForm"
  >
    <el-form-item label="用户名" prop="name">
      <el-input type="text" v-model="userForm.name" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input type="password" v-model="userForm.password" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('userForm')">提交</el-button>
      <el-button @click="resetForm('userForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
// import axios  from 'axios';
export default {
  data() {
    var checkAge = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("年龄不能为空"));
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error("请输入数字值"));
        } else {
          if (value < 18) {
            callback(new Error("必须年满18岁"));
          } else {
            callback();
          }
        }
      }, 1000);
    };
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.userForm.checkPass !== "") {
          this.$refs.userForm.validateField("checkPass");
        }
        callback();
      }
    };

    return {
      userForm: {
        name: "",
        password: ""
      },
      rules: {
        pass: [{ validator: validatePass, trigger: "blur" }],
        age: [{ validator: checkAge, trigger: "blur" }]
      }
    };
  },
  methods: {
    submitForm(formName) {
      var me = this;
      var userParam = [];
      userParam.name = this.userForm.name;
      userParam.password = this.userForm.password;
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios({
            url: "/user/userLogin",
            method: "get",
            params: {
              name: userParam.name,
              password: userParam.password
            }
          }).then(function(res) {
            debugger
            if (res.data.code == "666") {
              sessionStorage.setItem("user",JSON.stringify(res.data.responseEntity));
              me.$router.push("/index");
            } else {
              alert("信息错误");
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>