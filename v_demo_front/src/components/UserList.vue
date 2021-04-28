<template>
    <div id="da">
        <el-button type="primary" @click="loadUserList" icon="el-icon-search">搜索</el-button>
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
            var me = this;
            this.$axios({
                method: 'get',
                url: '/user/getUsers'
            }).then(function(res) { 
                if(res.data.code == '666'){
                    debugger
                    me.tableData = res.data.responseEntity;
                }else{
                    alert("error");
                }
            })
        }
    }
}
</script>