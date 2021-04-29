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
      >
        <template #trigger>
          <el-button size="small" type="primary">选取文件</el-button>
        </template>
        <el-button
          style="margin-left: 10px;"
          size="small"
          type="success"
          @click="submitUpload"
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
      <el-table-column prop="date" label="日期" width="180"></el-table-column>
      <el-table-column prop="name" label="文件名" width="180"></el-table-column>
      <el-table-column prop="address" label="大小"></el-table-column>
      <el-table-column prop="address" label="类型"></el-table-column>
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
     this.$refs.upload.submit();
     
    },
    handlePreview(file) {
      console.log(file); 
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    searchFiles() {
      var userId = "1";
      this.$axios({
        method: "get",
        url: "/file/loadFiles",
        params: [{ userId }]
      }).then(function(res) {
        console.log(res);
      });
    }
  }
};
</script>