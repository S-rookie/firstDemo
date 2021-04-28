<template>
     <el-table
    :data="tableData"
    stripe
    style="width: 100%">
    <el-table-column
      prop="date"
      label="日期"
      width="180">
    </el-table-column>
    <el-table-column
      prop="name"
      label="文件名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="address"
      label="大小">
    </el-table-column>
    <el-table-column
      prop="address"
      label="类型">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
    data(){
        return {
            tableData: [],
        }
    }
}
</script>