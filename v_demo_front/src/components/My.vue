<template>
  <div>
    <div>
      <el-upload
        class="upload-demo"
        ref="upload"
        :action="this.$axios.defaults.baseURL+'/file/uploadFiles'"
        :data="userInfo"
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
    <el-table 
    :data="tableData" 
    stripe style="width: 100%"
    >
      <el-table-column prop="createDate" :formatter="dateTimeFormat" label="上传日期" width="180"></el-table-column>
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
      fileList: [],
      userInfo: JSON.parse(sessionStorage.getItem("user"))
    };
  },
  methods: {
    uploadUrl() {
      return this.$axios.defaults.baseURL + "/file/uploadFiles";
    },
    downFile(index, row) {
      this.$axios({
        url: "/file/downloadFiles",
        method: "post",
        params: {
          row: row
        }
      })
        .then(res => {
          const blob = this.dataURItoBlob(res.data.responseEntity);
          let downloadElement = document.createElement("a");
          let href = window.URL.createObjectURL(blob);
          downloadElement.href = href;
          downloadElement.download = row.originalName;
          document.body.appendChild(downloadElement);
          // 点击下载
          downloadElement.click();
          // 下载完成移除元素
          document.body.removeChild(downloadElement);
          // 释放掉blob对象
          window.URL.revokeObjectURL(href);

          // const url = window.URL.createObjectURL(blob)
          // window.location.href = url
        })
        .catch(error => this.$message.error(error));
    },
    deleteFile(index, row) {
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$axios({
            url: "/file/deleteFiles",
            method: "post",
            params: {
              row: row
            }
          }).then(res => {
            debugger;
            if (res.data.code === "666") {
              this.$message({
                type: "success",
                message: "删除成功！"
              });
              this.searchFiles();
            }else{
              this.$message({
                type: "error",
                message: "删除失败！"
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
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
        console.log(me.tableData);
      });
    },
    // 上传成功调用
    handleSuccess(res) {
      if (res.code == "666") {
        this.$message({
          message: "上传成功",
          type: "success"
        });
        this.searchFiles();
      } else {
        this.$message({
          message: "error",
          type: "error"
        });
      }
    },
    handleError() {
      this.$message({
        message: "上传失败",
        type: "error"
      });
    },
    dataURItoBlob(base64Data) {
      let Base64 = require("js-base64").Base64;
      let u8arr = Base64.decode(base64Data);
      return new Blob([u8arr], { type: "text/plain" });
    },
    dateTimeFormat(row, column) {
      var t = new Date(row.createDate);
      return t.getFullYear() + "-" + t.getMonth() + "-" + t.getDate();
    },
  }
};
</script>