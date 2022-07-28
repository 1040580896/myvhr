<template>
    <div>
        <div >
            <el-button icon="el-icon-plus">添加账套</el-button>
            <el-button icon="el-icon-refresh"></el-button>
        </div>
        <div style="margin-top: 10px">
            <el-table :data="salaries" border stripe>
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column width="120" prop="name" label="账套名称"></el-table-column>
                <el-table-column width="70" prop="basicSalary" label="基本工资"></el-table-column>
                <el-table-column width="70" prop="trafficSalary" label="交通补助"></el-table-column>
                <el-table-column width="70" prop="lunchSalary" label="午餐补助"></el-table-column>
                <el-table-column width="70" prop="bonus" label="奖金"></el-table-column>
                <el-table-column width="100" prop="createDate" label="启用时间"></el-table-column>
                <el-table-column label="养老金" align="center">
                    <el-table-column width="70" prop="pensionPer" label="比率"></el-table-column>
                    <el-table-column width="70" prop="pensionBase" label="基数"></el-table-column>
                </el-table-column>
                <el-table-column label="医疗保险" align="center">
                    <el-table-column width="70" prop="medicalPer" label="比率"></el-table-column>
                    <el-table-column width="70" prop="medicalBase" label="基数"></el-table-column>
                </el-table-column>
                <el-table-column label="公积金" align="center">
                    <el-table-column width="70" prop="accumulationFundPer" label="比率"></el-table-column>
                    <el-table-column width="70" prop="accumulationFundBase" label="基数"></el-table-column>
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button @click="showEditSalaryView(scope.row)">编辑</el-button>
                        <el-button type="danger" @click="deleteSalary(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
export default {
    name: 'SalMonth',
    data(){
        return{
            salaries:[]
        }
    },
    mounted () {
        this.initSalaries();
    },
    methods:{
        initSalaries(){
            this.getRequest("/salary/sob/").then(resp=>{
                if(resp){
                    this.salaries = resp;
                }
            })
        }
    }
}
</script>

<style scoped>

</style>