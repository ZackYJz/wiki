<template>
  <div class="home">
    <a-layout style="padding: 24px 0; background: #fff">
      <a-layout-sider width="200" style="background: #fff">
        <a-menu
            mode="inline"
            style="height: 100%"
        >
          <a-sub-menu key="sub1">
            <template #title>
                <span>
                  <user-outlined />
                  subnav 12345
                </span>
            </template>
            <a-menu-item key="1">option1</a-menu-item>
            <a-menu-item key="2">option2</a-menu-item>
            <a-menu-item key="3">option3</a-menu-item>
            <a-menu-item key="4">option4</a-menu-item>
          </a-sub-menu>
          <a-sub-menu key="sub2">
            <template #title>
                <span>
                  <laptop-outlined />
                  subnav 2
                </span>
            </template>
            <a-menu-item key="5">option5</a-menu-item>
            <a-menu-item key="6">option6</a-menu-item>
            <a-menu-item key="7">option7</a-menu-item>
            <a-menu-item key="8">option8</a-menu-item>
          </a-sub-menu>
          <a-sub-menu key="sub3">
            <template #title>
                <span>
                  <notification-outlined />
                  subnav 3
                </span>
            </template>
            <a-menu-item key="9">option9</a-menu-item>
            <a-menu-item key="10">option10</a-menu-item>
            <a-menu-item key="11">option11</a-menu-item>
            <a-menu-item key="12">option12</a-menu-item>
          </a-sub-menu>
        </a-menu>
      </a-layout-sider>
      <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
        <a-list item-layout="vertical" size="large"
                :data-source="ebooks" :grid="{gutter:15,column:3}">
          <template #renderItem="{ item }">
            <a-list-item key="item.title">
              <template #actions>
          <span v-for="{ type, text } in actions" :key="type">
            <component v-bind:is="type" style="margin-right: 8px" />
            {{ text }}
          </span>
              </template>
              <a-list-item-meta :description="item.description">
                <template #title>
                  <a :href="item.href">{{ item.name }}</a>
                </template>
                <template #avatar><a-avatar :src="item.cover" /></template>
              </a-list-item-meta>
              {{ item.content }}
            </a-list-item>
          </template>
        </a-list>
      </a-layout-content>
    </a-layout>

  </div>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref } from 'vue';
import axios from 'axios';
const listData: any = [];

export default defineComponent({
  name: 'Home',
  setup(){
    const ebooks =ref();

    //初始化方法
    onMounted(()=>{
      axios.get("/ebook/list",{params:{
        page:1,
          size:100
        }}).then((response) => {
        const data=response.data;
        ebooks.value = data.content.list;
      });
    })
    return{
      ebooks,
      listData,
      pagination:{
        onChange: (page : any) => {
          console.log(page);
        },
        pageSize: 3,
      },
      actions:[
          { type: 'StarOutlined', text: '1'},
          { type: 'LikeOutlined', text: '2'},
          { type: 'MessageOutlined', text: '3'},
        ],
      }
    }
});
</script>

<style type="text/css" scoped>
  .ant-avatar{
    width: 50px;
    height: 50px;
    line-height: 50px;
    margin: 5px 0;
    border-radius: 8%;
  }
</style>