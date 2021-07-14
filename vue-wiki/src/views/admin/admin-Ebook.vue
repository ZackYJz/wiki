<template>
  <a-layout style="padding: 24px 0; background: #fff">
    <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
      <p>
        <a-button type="primary" @click="add()" size='large'>
          新增Wiki
        </a-button>
      </p>
      <a-table
          :columns="columns"
          :row-key="record => record.id"
          :data-source="ebooks"
          :pagination="pagination"
          :loading="loading"
          @change="handleTableChange"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" style="width:50px; height: 50px;"/>
        </template>
        <template v-slot:action="{ text, record }">
          <a-space size="small">
            <router-link :to="'/admin/doc?ebookId=' + record.id">
              <a-button type="default">
                文档管理
              </a-button>
            </router-link>
            <a-button type="primary" @click="edit(record)">
                编辑
            </a-button>
            <a-popconfirm
                title="确认删除?"
                ok-text="是"
                cancel-text="否"
                @confirm="del(record.id)"
            >
              <a-button type="danger">
                删除
              </a-button>
            </a-popconfirm>

          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>
  <a-modal
      title="编辑电子书"
      v-model:visible="modalVisible"
      :confirm-loading="modalLoading"
      @ok="handleModalOk"
  >
    <a-form :model="ebook" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="封面">
        <a-input v-model:value="ebook.cover" />
      </a-form-item>
      <a-form-item label="名称">
        <a-input v-model:value="ebook.name" />
      </a-form-item>
      <a-form-item label="分类">
        <a-input v-model:value="ebook.categoryId1"/>
      </a-form-item>
      <a-form-item label="描述">
        <a-input v-model:value="ebook.description" type="textarea" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref,createVNode} from 'vue';
import axios from 'axios';
import { message } from 'ant-design-vue';
import { Modal } from 'ant-design-vue';
// import {Tool} from "@/util/tool";

export default defineComponent({
  name: 'AdminEbook',
  setup() {
    //const param = ref();
    //param.value = {};
    //TODO 取消模态表单框的响应式变量
    const ebooks = ref()
    const pagination = ref({ //定义分页
      current: 1,  //当前页
      pageSize: 4, //每页条目数
      total: 0
    });
    const loading = ref(false);

    const columns = [
      {
        title: '封面',
        dataIndex: 'cover',
        slots: { customRender: 'cover' }
      },
      {
        title: '名称',
        dataIndex: 'name'
      },
      {
        title: '分类',
        slots: { customRender: 'category' }
      },
      {
        title: '文档数',
        dataIndex: 'docCount'
      },
      {
        title: '阅读数',
        dataIndex: 'viewCount'
      },
      {
        title: '点赞数',
        dataIndex: 'voteCount'
      },
      {
        title: 'Action',
        key: 'action',
        slots: { customRender: 'action' }
      }
    ];

    /**
     * 数据查询
     **/
    const handleQuery = (params: any) => {
      loading.value = true;
      // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
      //ebooks.value = [];
      axios.get("/ebook/list", {
        params:{
          page:params.page,
          size:params.size
        }
      }).then((response) => {
        loading.value = false;
        const data = response.data;
        ebooks.value = data.content.list;
        // 重置分页按钮,防止点击效果在第一页
        pagination.value.current = params.page;
        pagination.value.total = data.content.total;
      });
    };

    /**
     * 表格点击页码时触发
     */
    const handleTableChange = (pagination: any) => {
      handleQuery({
        page: pagination.current,
        size: pagination.pageSize
      });
    };

    /**
     *  电子书编辑
     */
    const ebook = ref();
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () => {
      //显示 Loading 效果
      modalLoading.value = true;
      // ebook.value.category1Id = categoryIds.value[0];
      // ebook.value.category2Id = categoryIds.value[1];
      axios.post("/ebook/save", ebook.value).then((response) => {
        //显示 Loading 效果
        modalLoading.value = false;
        const data = response.data; // data = commonResp
        if (data.success) {  //返回成功结果
          //取消加载效果
          modalVisible.value = false;
          // 重新加载列表
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize,
          });
        } else {  //返回失败结果
          //弹出提示框
          message.error(data.message);
        }
      });
    };

    /**
     * 编辑
     */
    const edit = (record :any) => {
      modalVisible.value = true;
      ebook.value = record;
    };

    onMounted(() => {
      handleQuery({
        page:1,
        size:pagination.value.pageSize
      });
    });

    /*
    *  新增电子书
    * */
    const add = ()=>{
      //显示模态框
      modalVisible.value = true;
      ebook.value = {}
    }

    /*
    * 删除电子书
    * */
    const del = (id:number) =>{
      loading.value = true;
      // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
      //ebooks.value = [];
      axios.delete("/ebook/delete/"+id).then((response) => {
        const data = response.data; // data = commonResp
        if (data.success) {  //返回成功结果
          message.success("删除成功");
          // 重新加载列表
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize,
          });
        } else {  //返回失败结果
          //弹出提示框
          message.error(data.message);
        }
      });
    }

    return {
      ebooks,
      pagination,
      columns,
      loading,
      handleTableChange,
      ebook,
      edit,
      add,
      del,

      modalVisible,
      modalLoading,
      handleModalOk,
    }
  }
});
</script>