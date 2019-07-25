<template>
  <el-container>
  <el-header>北京交通大学 515 2020届毕业生求职信息系统</el-header>
  <el-main>
    <el-button type="primary" @click="addVisible=true" style="float:left;margin-bottom:10px">新增招聘信息</el-button>
    <el-button @click="exportExcel()" style="float:right;margin-bottom:10px">导出Excel</el-button>
    <el-table id="table-data" :data="tableData" border style="width: 100%" :default-sort="{prop: 'startTime', order: 'descending'}">
      <el-table-column prop="companyId" label="编号" width="49">
      </el-table-column>
      <el-table-column prop="companyName" label="公司姓名" width="100">
      </el-table-column>
      <el-table-column prop="companyType" label="公司类别" width="100">
      </el-table-column>
      <el-table-column prop="zhaopinType" label="招聘类别" width="100">
      </el-table-column>
      <el-table-column prop="companyUrl" label="招聘网址" width="200">
        <template slot-scope="scope">
          <a :href="scope.row.companyUrl" target="_blank">{{scope.row.companyUrl}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="salary" label="待遇" width="100">
      </el-table-column>
      <el-table-column prop="" label="操作" width="200">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" style="margin-bottom:1px"
          @click="addDetail(scope.$index, scope.row)">已投递该公司</el-button>
        <br>
        <el-button size="mini" type="success"
          @click="showDetail(scope.$index, scope.row)">查看所有投递该公司的同学</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="startTime" label="简历投递开始时间" width="100">
      </el-table-column>
      <el-table-column prop="endTime" label="简历投递结束时间" width="100">
      </el-table-column>
      <el-table-column prop="exmTime" label="笔试开始时间" width="100">
      </el-table-column>
      <el-table-column prop="" label="编辑">
        <template slot-scope="scope">
          <el-button size="mini" type="danger"
            @click="editCompany(scope.$index, scope.row)">编辑</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="uploadUser" label="上传人" width="70">
      </el-table-column>
      <el-table-column prop="uploadIp" label="上传人IP" width="90">
      </el-table-column>
      <el-table-column prop="uploadTime" label="上传时间" width="100">
      </el-table-column>
      
  </el-table>
    <!-- <el-pagination
      background
      layout="prev, pager, next"
      :total="total"
      page-size="5">
    </el-pagination> -->
  </el-main>

  <el-dialog title="新增招聘信息" :visible.sync="addVisible" width="70%" :before-close="handleClose">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="公司名称">
        <el-input v-model="form.companyName"></el-input>
      </el-form-item>
      <el-form-item label="公司类型">
        <el-select v-model="form.companyType" placeholder="请选择公司类型">
          <el-option label="互联网一线" value="互联网一线"></el-option>
          <el-option label="互联网独角兽" value="互联网独角兽"></el-option>
          <el-option label="互联网其他" value="互联网其他"></el-option>
          <el-option label="银行" value="银行"></el-option>
          <el-option label="国企" value="国企"></el-option>
          <el-option label="央企" value="央企"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="招聘类型">
        <el-select v-model="form.zhaopinType" placeholder="请选择公司类型">
          <el-option label="实习生" value="实习生"></el-option>
          <el-option label="提前批" value="提前批"></el-option>
          <el-option label="内推" value="内推"></el-option>
          <el-option label="正式批" value="正式批"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="招聘主页">
        <el-input v-model="form.companyUrl"></el-input>
      </el-form-item>
      <el-form-item label="待遇">
        <el-input v-model="form.salary" placeholder="如：23w+/20w+户口/18k*4"></el-input>
      </el-form-item>
      <el-form-item label="时间结点">
        <el-col :span="6">
          <el-date-picker placeholder="简历投递开始" v-model="form.startTime" style="width: 100%;"></el-date-picker>
        </el-col>
        <el-col :span="6">
          <el-date-picker placeholder="简历投递结束" v-model="form.endTime" style="width: 100%;"></el-date-picker>
        </el-col>
        <el-col :span="6">
          <el-date-picker placeholder="笔试开始时间" v-model="form.exmTime" style="width: 100%;"></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="上传人">
        <el-input v-model="form.uploadUser" style="width:30%"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="cancle()">取 消</el-button>
      <el-button type="primary" @click="addZhaopinSubmit">确 定</el-button>
    </span>
  </el-dialog>

  <!-- -->
  <el-dialog title="修改招聘信息" :visible.sync="editVisible" width="70%" :before-close="handleCloseEdit">
    <el-form ref="formEdit" :model="formEdit" label-width="80px">
      <el-form-item label="公司名称">
        <el-input v-model="formEdit.companyName"></el-input>
      </el-form-item>
      <el-form-item label="公司类型">
        <el-select v-model="formEdit.companyType" placeholder="请选择公司类型">
          <el-option label="互联网一线" value="互联网一线"></el-option>
          <el-option label="互联网独角兽" value="互联网独角兽"></el-option>
          <el-option label="互联网其他" value="互联网其他"></el-option>
          <el-option label="银行" value="银行"></el-option>
          <el-option label="国企" value="国企"></el-option>
          <el-option label="央企" value="央企"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="招聘类型">
        <el-select v-model="formEdit.zhaopinType" placeholder="请选择公司类型">
          <el-option label="实习生" value="实习生"></el-option>
          <el-option label="提前批" value="提前批"></el-option>
          <el-option label="内推" value="内推"></el-option>
          <el-option label="正式批" value="正式批"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="招聘主页">
        <el-input v-model="formEdit.companyUrl"></el-input>
      </el-form-item>
      <el-form-item label="待遇">
        <el-input v-model="formEdit.salary" placeholder="如：23w+/20w+户口/18k*4"></el-input>
      </el-form-item>
      <el-form-item label="时间结点">
        <el-col :span="6">
          <el-date-picker placeholder="简历投递开始" v-model="formEdit.startTime" style="width: 100%;"></el-date-picker>
        </el-col>
        <el-col :span="6">
          <el-date-picker placeholder="简历投递结束" v-model="formEdit.endTime" style="width: 100%;"></el-date-picker>
        </el-col>
        <el-col :span="6">
          <el-date-picker placeholder="笔试开始时间" v-model="formEdit.exmTime" style="width: 100%;"></el-date-picker>
        </el-col>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="cancleEdit()">取 消</el-button>
      <el-button type="primary" @click="editZhaopinSubmit">确 定</el-button>
    </span>
  </el-dialog>

   <!-- -->
  <el-dialog title="投递详情" :visible.sync="detailVisible" width="70%" :before-close="handleCloseDetail">
    <el-form ref="detail" :model="detail" label-width="80px">
      <el-form-item label="投递人">
        <el-input v-model="detail.userName"></el-input>
      </el-form-item>
      <el-form-item label="投递的岗位">
        <el-input v-model="detail.jobName"></el-input>
      </el-form-item>
      <el-form-item label="投递的部门">
        <el-input v-model="detail.departmentName"></el-input>
      </el-form-item>
      <el-form-item label="应聘状态">
        <el-select v-model="detail.status" placeholder="请选择公司类型">
          <el-option label="简历筛选" value="简历筛选"></el-option>
          <el-option label="测评" value="测评"></el-option>
          <el-option label="笔试" value="笔试"></el-option>
          <el-option label="电话/视频面试" value="电话/视频面试"></el-option>
          <el-option label="面试" value="面试"></el-option>
          <el-option label="offer call!!" value="offer call!!"></el-option>
          <el-option label="凉凉" value="凉凉"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="cancleDetail()">取 消</el-button>
      <el-button type="primary" @click="detailSubmit">确 定</el-button>
    </span>
  </el-dialog>
  <!-- -->
  <el-dialog title="投递详情" :visible.sync="dialogTableVisible" width="70%">
    <el-table :data="tableDetail">
      <el-table-column property="companyId" label="投递公司编号" ></el-table-column>
      <el-table-column property="companyName" label="投递公司" ></el-table-column>
      <el-table-column property="userName" label="投递人" ></el-table-column>
      <el-table-column property="jobName" label="投递岗位"></el-table-column>
      <el-table-column property="departmentName" label="投递部门"></el-table-column>
      <el-table-column property="status" label="应聘状态"></el-table-column>
      <el-table-column property="createTime" label="创建时间"></el-table-column>
      <el-table-column prop="" label="编辑">
        <template slot-scope="scope">
          <el-button size="mini" type="danger"
            @click="editDetail(scope.$index, scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>

  
  <el-dialog title="投递修改" :visible.sync="detaileditVisible" width="40%" :before-close="handleCloseEdit">
    <el-form ref="editdetail" :model="editdetail" label-width="90px">
      <el-form-item label="投递公司">
        {{editdetail.companyName}}
      </el-form-item>
      <el-form-item label="投递人">
        {{editdetail.userName}}
      </el-form-item>
      <el-form-item label="投递的岗位">
        <el-input v-model="editdetail.jobName"></el-input>
      </el-form-item>
      <el-form-item label="投递的部门">
        <el-input v-model="editdetail.departmentName"></el-input>
      </el-form-item>
      <el-form-item label="应聘状态">
        <el-select v-model="editdetail.status" placeholder="请选择公司类型">
          <el-option label="简历筛选" value="简历筛选"></el-option>
          <el-option label="测评" value="测评"></el-option>
          <el-option label="笔试" value="笔试"></el-option>
          <el-option label="电话/视频面试" value="电话/视频面试"></el-option>
          <el-option label="面试" value="面试"></el-option>
          <el-option label="offer call!!" value="offer call!!"></el-option>
          <el-option label="凉凉" value="凉凉"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="cancleDetailEdit()">取 消</el-button>
      <el-button type="primary" @click="detailEditSubmit">确 定</el-button>
    </span>
  </el-dialog>

</el-container>
</template>

<script>
import axios from 'axios'
import fileSaver from 'file-saver'
import xlsx from 'xlsx'

let base = 'http://202.112.147.101:2020'
//let base = 'http://localhost:2020'

export default {
  name: 'App',
  data(){
    return {
      tableData:[],
      tableDetail:[],
      total2:0,
      total:0,
      addVisible:false,
      editVisible:false,
      detailVisible:false,
      dialogTableVisible:false,
      detaileditVisible:false,
      form: {
          companyName: '',
          companyType: '',
          zhaopinType: '',
          companyUrl: '',
          startTime: '',
          endTime:'',
          exmTime:'',
          uploadUser:'',
          salary:'',
        },
        formEdit: {
          companyName: '',
          companyType: '',
          zhaopinType: '',
          companyUrl: '',
          startTime: '',
          endTime:'',
          exmTime:'',
          uploadUser:'',
          salary:'',
        },
        detail:{
          detailId:'',
          companyName:'',
          userName:'',
          jobName:'',
          departmentName:'',
          status
        },
        editdetail:{
          detailId:'',
          companyId:'',
          companyName:'',
          userName:'',
          jobName:'',
          departmentName:'',
          status:''
        },
    }
  },
  mounted(){
    axios.post(`${base}/getAllCampusZhaopinData`)
    .then(response=>{
      console.log(response.data.msg)
      if(response.data.code==1){
        this.tableData = response.data.result.data
        this.total = response.data.result.total_count
      }else{
        this.$message.error(response.data.msg);
      }
    }).catch(error=>{
      console.log(error)
      this.$message.error('异常');
    })
  },
  methods:{
    cancleDetailEdit(){
      this.detaileditVisible = false
      this.editdetail = {}
    },
    handleCloseEdit(done){
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
          this.editdetail = {}
        })
        .catch(_ => {});
    },
    cancleDetail(){
      this.detailVisible = false
      this.detail = {}
    },
    cancleEdit(){
      this.editVisible = false;
    },
    cancle(){
      this.addVisible = false;
      this.form = {}
    },
    handleCloseDetail(done){
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
          this.detail = {}
        })
        .catch(_ => {});
    },
    // 新增关闭
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
          this.form = {}
        })
        .catch(_ => {});
    },
    // 编辑关闭
    handleCloseEdit(done){
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    // 新增提交
    addZhaopinSubmit(){
        this.addVisible = false
        // 组装信息
        console.log(this.form)
        axios.get(`${base}/addOneCampusZhaopinData`,{
          params: {
      　　　　...this.form,
      　　}
        })
        .then(response=>{
          console.log(response.data.msg)
          if(response.data.code==1){
            this.addVisible = false
            this.$message({
              message: '恭喜你，添加成功！',
              type:'success'
            })
            this.form = {}
            //重新获取数据
            axios.post(`${base}/getAllCampusZhaopinData`)
            .then(response=>{
              console.log(response.data.msg)
              if(response.data.code==1){
                this.tableData = response.data.result.data
                this.total = response.data.result.total_count
              }else{
                this.$message.error(response.data.msg);
              }
            }).catch(error=>{
              console.log(error)
              this.$message.error('异常')
            })

          }else{
            this.$message.error(response.data.msg);
          }
        }).catch(error=>{
          console.log(error)
          this.$message.error('异常');
          this.form = {}
        })
    },
    addDetail(index,row){
      console.log(row.companyId)
      // 增加
      this.detailVisible = true;
      this.detail.companyId = row.companyId
    },
    detailSubmit(){
      this.detailVisible = false
        // 组装信息
        console.log(this.detail)
        axios.get(`${base}/addOneDetailZhaopinData`,{
          params: {
      　　　　...this.detail,
      　　}
        })
        .then(response=>{
          if(response.data.code==1){
            this.detailVisible = false
            this.$message({
              message: '恭喜你，投递成功！',
              type:'success'
            })
            this.detail = {}
          }else{
            this.$message.error(response.data.msg);
          }
        }).catch(error=>{
          console.log(error)
          this.$message.error('异常');
          this.detail = {}
        })
    },
    showDetail(index,row){
      this.dialogTableVisible = true

      console.log(row.companyId)
      let companyId = row.companyId
      axios.get(`${base}/getAllDetailZhaopinData`,{
        params: {
          companyId,
      　}
      })
      .then(response=>{
        console.log(response)
        if(response.data.code==1){
          //this.tableData = response.data.result.data
          this.tableDetail = response.data.result.data
          this.total2 = response.data.result.total_count
        }else{
          this.$message.error(response.data.msg);
        }
      }).catch(error=>{
        console.log(error)
        this.$message.error('异常');
      })
    },
    //编辑
    editCompany(index, row){
      //
      console.log(row)
      this.editVisible = true
      let companyId = row.companyId
      axios.get(`${base}/getOneCampusZhaopinData`,{
        params: {
          companyId,
      　}
      })
        .then(response=>{
          console.log(response.data.result.data)
          if(response.data.code==1){
            this.formEdit = response.data.result.data
          }else{
            this.$message.error(response.data.msg);
          }
        }).catch(error=>{
          console.log(error)
          this.$message.error('异常')
        })

    },
    // 编辑提交
    editZhaopinSubmit(){
      //
      console.log(this.formEdit)

        axios.get(`${base}/updateCampusZhaopinData`,{
          params: {
      　　　　...this.formEdit,
      　　}
        })
        .then(response=>{
          console.log(response.data.msg)
          if(response.data.code==1){
            this.editVisible = false
            this.$message({
              message: '恭喜你，修改成功！',
              type:'success'
            })
           
            //重新获取数据
            axios.post(`${base}/getAllCampusZhaopinData`)
            .then(response=>{
              console.log(response.data.msg)
              if(response.data.code==1){
                this.tableData = response.data.result.data
                this.total = response.data.result.total_count
              }else{
                this.$message.error(response.data.msg);
              }
            }).catch(error=>{
              console.log(error)
              this.$message.error('异常')
            })

          }else{
            this.$message.error(response.data.msg);
          }
        }).catch(error=>{
          console.log(error)
          this.$message.error('异常');
        })
    },
    exportExcel () {
      console.log("exportExcel")
      let box = xlsx.utils.table_to_book(document.querySelector('#table-data'))
      let out = xlsx.write(box, {
              bookType: 'xlsx',
              bookSST: true,
              type: 'array'
            })
      try {
        fileSaver.saveAs(
          new Blob([out], {
            type: 'application/octet-stream'
          }),
          'sheet.xlsx'
        )
      } catch (e) {
        // 错误处理方式
      }
      return out
    },
    editDetail(index, row){
      this.detaileditVisible = true
      console.log(row)
      // 详情信息
      this.editdetail.companyId = row.companyId
      this.editdetail.detailId = row.detailId
      this.editdetail.companyName = row.companyName
      this.editdetail.userName = row.userName
      this.editdetail.jobName = row.jobName
      this.editdetail.departmentName = row.departmentName
      this.editdetail.status = row.status
    },
    detailEditSubmit(){
      // 提交信息
      console.log(this.editdetail)

      axios.get(`${base}/editDetailZhaopinData`,{
          params: {
      　　　　...this.editdetail,
      　　}
        })
        .then(response=>{
          console.log(response.data.msg)
          if(response.data.code==1){
            this.editVisible = false
            this.$message({
              message: '恭喜你，详情修改成功！',
              type:'success'
            })

            this.detaileditVisible = false
            // 重新
            let companyId = this.editdetail.companyId
            axios.get(`${base}/getAllDetailZhaopinData`,{
              params: {
                companyId,
            　}
            })
            .then(response=>{
              console.log(response)
              if(response.data.code==1){
                //this.tableData = response.data.result.data
                this.tableDetail = response.data.result.data
                this.total2 = response.data.result.total_count
              }else{
                this.$message.error(response.data.msg);
              }
            }).catch(error=>{
              console.log(error)
              this.$message.error('异常');
            })

          }else{
            this.$message.error(response.data.msg);
          }
        }).catch(error=>{
          console.log(error)
          this.$message.error('异常');
        })
    }
  }, 
}
</script>
<style>
.el-header {
    color: #333;
    text-align: center;
    line-height: 60px;
    font-size: 22px;
    font-weight: bold;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif
  }
 .el-main {
    color: #333;
    text-align: center;
  }
</style>



