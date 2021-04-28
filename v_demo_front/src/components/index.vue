<template>
    <div id="uf">
        <el-button @click="seeMyInfo" type="text">我的</el-button>
        <el-button @click="seeUserList" type="text">用户列表</el-button>
    </div>
</template>

<script>
export default {
    methods: {
        seeMyInfo(){
            this.$router.push('/my')
        },
        seeUserList(){
            this.$router.push('/userList')
        }
    }
}
</script>