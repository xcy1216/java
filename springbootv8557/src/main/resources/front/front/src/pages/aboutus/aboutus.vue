<template>
    <div id="app">
      <div class="about-us">
        <div class="team-members">
          <h2>开发团队</h2>
          <ul>
            <li v-for="member in teamMembers" :key="member.name">
              {{ member.name }} - {{ member.role }}
            </li>
          </ul>
        </div>
        <!-- <div class="development-log">
          <h2>开发日志</h2>
          <ul>
            <li v-for="log in developmentLog" :key="log.date">
              {{ log.date }}: {{ log.description }}
            </li>
          </ul>
        </div> -->
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'App',
    data() {
      return {
        teamMembers: [
          { name: '林泽枫', role: '前后端开发' },
          { name: '罗嘉宇', role: '前后端开发' }
        ],
        developmentLog: [
          // { date: '2024-06-06', description: '项目启动会议' },
          // { date: '2024-06-07', description: '完成项目需求分析' },
          // { date: '2024-06-08', description: '前端页面设计完成' }
        ]
      };
    }
  };
  </script>
  
  <style>
  #app {
    text-align: center;
    padding: 20px;
  }
  
  .about-us {
    margin-top: 20px;
  }
  
  .team-members, .development-log {
    margin-bottom: 20px;
  }
  
  h1, h2 {
    color: #333;
  }
  
  ul {
    list-style: none;
    padding: 0;
  }
  
  li {
    margin: 5px 0;
  }
  </style>