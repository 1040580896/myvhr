<template>
    <div>
        <div>
            <el-input size="small" v-model="jl.name" style="width: 300px" prefix-icon="el-icon-plus"
                      placeholder="添加职称"></el-input>
            <el-select v-model="jl.titleLevel" placeholder="职称登记" size="small" style="margin-left: 5px">
                <el-option
                    v-for="item in titleLevels"
                    :key="item"
                    :label="item"
                    :value="item">
                </el-option>
            </el-select>
            <el-button icon="el-icon-plus" type="primary" size="small" style="margin-left: 5px" @click="addJobLevel">
                添加
            </el-button>
        </div>
        <div style="margin-top: 10px">
            <el-table
                :data="jls"
                stripe
                size="small"
                style="width: 80%"
                @selection-change="handleSelectionChange">
                <el-table-column
                    type="selection"
                    width="55"
                >
                </el-table-column>
                <el-table-column
                    prop="id"
                    label="编号"
                    width="180">
                </el-table-column>
                <el-table-column
                    prop="name"
                    label="职称名称"
                    width="180">
                </el-table-column>
                <el-table-column
                    prop="titleLevel"
                    label="职称级别">
                </el-table-column>
                <el-table-column
                    prop="createDate"
                    label="创建时间">
                </el-table-column>
                <el-table-column
                    label="是否启用">
                    <template slot-scope="scope">
                        <el-tag type="success" v-if="scope.row.enabled">已启用</el-tag>
                        <el-tag type="danger" v-else>未启用</el-tag>
                    </template>
                </el-table-column>
                <el-table-column
                    width="200px"
                    label="操作">
                    <template slot-scope="scope">
                        <el-button size="small" @click="showEditView(scope.row)">编辑</el-button>
                        <el-button type="danger" size="small" @click="deleteHandler(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-button @click="deleteMany" type="danger" size="small" style="margin-top: 10px" :disabled="multipleSelection.length==0">
                批量删除
            </el-button>
        </div>

        <el-dialog
            title="修改职称等级"
            :visible.sync="dialogVisible"
            width="30%">
            <div>
                <table>
                    <tr>
                        <td>
                            <el-tag>职称名</el-tag>
                        </td>
                        <td>
                            <el-input size="small" v-model="updateJl.name"></el-input>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <el-tag>职称级别</el-tag>
                        </td>
                        <td>
                            <el-select v-model="updateJl.titleLevel" placeholder="职称等级" size="small"
                                       style="margin-left: 5px;margin-right: 5px">
                                <el-option
                                    v-for="item in titleLevels"
                                    :key="item"
                                    :label="item"
                                    :value="item">
                                </el-option>
                            </el-select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <el-tag>是否启用</el-tag>
                        </td>
                        <td>
                            <el-switch
                                v-model="updateJl.enabled"
                                active-text="启用"
                                inactive-text="禁用">
                            </el-switch>
                        </td>
                    </tr>
                </table>
            </div>
            <el-button type="danger" size="small" style="margin-top: 10px" :disabled="multipleSelection.length==0">
                批量删除
            </el-button>
            <span slot="footer" class="dialog-footer">
    <el-button size="small" @click="dialogVisible = false">取 消</el-button>
    <el-button size="small" type="primary" @click="doUpdate">确 定</el-button>
  </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: 'JobLevelMana',
    data () {
        return {
            multipleSelection: [],
            dialogVisible: false,
            updateJl: {
                name: '',
                titleLevel: '',
                enabled: false,
            },
            jl: {
                name: '',
                titleLevel: '',
            },
            jls: [],
            titleLevels: [
                '正高级',
                '副高级',
                '初级',
                '中级',
                '员级',
            ],
        }
    },
    mounted () {
        this.initJls()
    },
    methods: {
        deleteMany(){
            this.$confirm('此操作将永久删除【'+this.multipleSelection.length+'】记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
              let ids = '?';
              this.multipleSelection.forEach(item=>{
                  ids+='ids='+item.id+'&'
              })
                this.deleteRequest("/system/basic/jobLevel/"+ids).then(resp=>{
                    if(resp){
                        this.initJls();
                    }
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        handleSelectionChange (val) {
            this.multipleSelection = val
        },
        doUpdate () {
            console.log(this.updateJl)
            this.putRequest('/system/basic/jobLevel/', this.updateJl).then(resp => {
                if (resp) {
                    this.initJls()
                    this.dialogVisible = false
                }
            })
        },
        showEditView (data) {
            // 赋值操作
            console.log(data)
            Object.assign(this.updateJl, data)
            this.dialogVisible = true
        },
        deleteHandler (data) {
            this.$confirm('此操作将永久删除[' + data.name + '], 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            }).then(() => {
                this.deleteRequest('/system/basic/jobLevel/' + data.id).then(resp => {
                    if (resp) {
                        this.initJls()
                    }
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除',
                })
            })
        },
        addJobLevel () {
            if (this.jl.name && this.jl.titleLevel) {
                this.postRequest('/system/basic/jobLevel/', this.jl).then(resp => {
                    if (resp) {
                        this.initJls()
                        this.jl = {
                            name: '',
                            titleLevel: '',
                        }
                    }
                })
            } else {
                this.$message.error('添加的字段不能为空')
            }
        },
        initJls () {
            this.getRequest('/system/basic/jobLevel/').then(resp => {
                if (resp) {
                    this.jls = resp
                }
            })
        },
    },
}
</script>

<style scoped>

</style>