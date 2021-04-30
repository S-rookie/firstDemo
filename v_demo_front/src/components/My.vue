<template>
  <div>
  <div>
      <el-upload
        class="upload-demo"
        ref="upload"
        :action="this.$axios.defaults.baseURL+'/file/uploadFiles'"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :file-list="fileList"
        :auto-upload="false"
        :on-success="handleSuccess"
        :on-error="handleError"
      >
        <template #trigger>
          <el-button size="small" type="primary">选取文件</el-button>
        </template>
        <el-button
          style="margin-left: 10px;"
          size="small"
          type="success"
          @click="submitUpload()"
        >上传文件到服务器</el-button>
        <!-- <template #tip>
          <div class="el-upload__tip">只能上传 jpg/png 文件，且不超过 500kb</div>
        </template>-->
         <el-button
            @click="searchFiles"
            style="margin-left: 10px;"
            size="small"
            type="primary"
            icon="el-icon-search"
          >搜索</el-button>
      </el-upload>
      
    </div>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="createDate" label="日期" width="180"></el-table-column>
      <el-table-column prop="originalName" label="文件名" width="180"></el-table-column>
      <el-table-column prop="fileSize" label="大小"></el-table-column>
      <el-table-column prop="fileType" label="类型"></el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="mini" @click="downFile(scope.$index, scope.row)">下载</el-button>
          <el-button size="mini" type="danger" @click="deleteFile(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>

export default {
  data() {
    return {
      tableData: [],
      fileList: []
    };
  },
  methods: {
    uploadUrl(){
      return this.$axios.defaults.baseURL+"/file/uploadFiles"
    },
    downFile(index, row) {
      alert("下载", index, row);
    },
    deleteFile(index, row) {
      alert("上传文件", index, row);
    },
    submitUpload() {
      // 提交的时候怎么获取到文件列表？
      // if(fileList.length == 0){
      //  return this.$message({
      //     message: '还没有选择文件',
      //     type: 'warning'
      //   })
      // }
     this.$refs.upload.submit()
    },
    handlePreview(file) {
      console.log(file); 
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    // 搜索
    searchFiles() {
      var userId = "1";
      var me = this;
      this.$axios({
        method: "get",
        url: "/file/searchFiles",
        params: [{ userId }]
      }).then(function(res) {
         me.tableData = res.data.responseEntity;
         console.log(me.tableData)
      });
    },
    // 上传成功调用
    handleSuccess(res){
      if(res.code == '666'){
        this.$message({
          message: '上传成功',
          type: 'success'
        })
      }else{
        this.$message({
          message: 'error',
          type: 'error'
        })
      }
    },
    handleError(){
      this.$message({
          message: '上传失败',
          type: 'error'
        })
      }
    
  },
}
</script>