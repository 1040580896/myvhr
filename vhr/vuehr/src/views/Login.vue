<template>
    <div>
        <el-form
            v-loading="loading"
            element-loading-text="正在登陆中"
            element-loading-spinner="el-icon-loading"
            element-loading-background="rgba(0, 0, 0, 0.8)"
            :rules="rules"
            ref="loginForm"
            :model="loginForm"
            class="loginContainer">
            <h3 class="loginTitle">系统登陆</h3>
            <el-form-item prop="username">
                <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="text" v-model="loginForm.password" auto-complete="off" placeholder="请输入用户密码"
                          @keydown.enter.native="submitLogin"></el-input>
            </el-form-item>
            <el-form-item prop="code">
                <el-input size="normal" type="text" v-model="loginForm.code" auto-complete="off"
                          placeholder="点击图片更换验证码" @keydown.enter.native="submitLogin" style="width: 250px"></el-input>
                <img :src="vcUrl" @click="updateVerifyCode" alt="" style="cursor: pointer">
            </el-form-item>
            <el-checkbox class="loginRemember" v-model="checked">记住我</el-checkbox>
            <el-button type="primary" style="width: 100%" @click="submitLogin('loginForm')">登陆</el-button>
        </el-form>
    </div>
</template>

<script>
// import { postKeyValueRequest } from '@/utils/api'

export default {
    name: 'Login',
    data () {
        return {
            vcUrl: '/verifyCode?time='+new Date(),
            loading:false,
            loginForm: {
                username: 'admin',
                password: '123',
                code:''
            },
            checked: true,
            rules: {
                username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
                code: [{ required: true, message: '请输入验证码', trigger: 'blur' }],
            },
        }
    },
    methods: {
        updateVerifyCode() {
            this.vcUrl = '/verifyCode?time='+new Date();
        },
        submitLogin () {

            this.$refs.loginForm.validate((valid) => {
                if (valid) {
                    this.loading = true;
                    this.postKeyValueRequest('/doLogin', this.loginForm).then(resp => {
                        this.loading = false;
                        // console.log(resp)
                        if (resp) {
                            this.$store.commit('INIT_CURRENTHR', resp.obj);
                            window.sessionStorage.setItem('user', JSON.stringify(resp.obj))
                            let path = this.$route.query.redirect
                            console.log(path)
                            this.$router.replace((path == '/' || path == undefined) ? '/home' : path)
                        }else {
                            this.vcUrl = '/verifyCode?time='+new Date();
                        }
                    })
                    // alert('submit!')
                } else {
                    this.$message.error('请输入所有字段')
                }
            })
        },
    },
}
</script>

<style>
.loginContainer {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 15px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    /*阴影*/
    box-shadow: 0 0 25px #cac;
}

.loginTitle {
    margin: 20px auto 40px auto;
    text-align: center;
    color: #ccaacc;
}

.loginRemember {
    text-align: left;
    margin: 0px 0px 15px 0px;
}

.el-form-item__content{
    display: flex;
    align-items: center;
}

</style>