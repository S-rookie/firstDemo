<template>
    <div id="da">
        <el-button type="primary" icon="el-icon-search">搜索</el-button>
        <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="id"
        label="编号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="password"
        label="密码">
      </el-table-column>
      
    </el-table>
    </div>
</template>

<script>
export default {
    data(){
        return{
            tableData: [],
        }
    },
    methods: {
        loadUserList(){
            this.$axios.get("user/getUsers").then(function(res) {
                if(res != null){
                    this.tableData = res;
                }else{
                    alert("error");
                }
            })
        }
    }
}
</script>