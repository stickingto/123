<template>
  <div class="components-container board">
    <Kanban :key="1" :list="list1" :group="group" class="kanban todo" header-text="已结束会议" />
    <Kanban :key="2" :list="list2" :group="group" class="kanban working" header-text="今日会议" />
    <Kanban :key="3" :list="list3" :group="group" class="kanban done" header-text="预期会议" />
  </div>
</template>
<script>
import Kanban from '@/components/Kanban'
import axios from 'axios'

export default {
  name: 'DragKanbanDemo',
  components: {
    Kanban
  },
  data() {
    return {
      group: 'mission',
      list1: [],
      list2: [],
      list3: []
    }
  },
  created() {
    this.fetchdata()
  },
  methods: {
    fetchdata() {
      axios.post('http://localhost:8080/table/get').then(res => {
        console.log('成功')
        this.list1 = res.data.yesterday
        console.log(this.list1)
        // 说明已收到信息。
        this.list2 = res.data.today
        console.log(res.data.today)
        this.list3 = res.data.tomm
        console.log(res.data.tomm)
      }).catch(Error => {
        console.log('"sadada"')
      })
    }
  }
}

</script>

<style lang="scss">
.board {
  width: 1000px;
  margin-left: 20px;
  display: flex;
  justify-content: space-around;
  flex-direction: row;
  align-items: flex-start;
}
.kanban {
  &.todo {
    .board-column-header {
      background: #4A9FF9;
    }
  }
  &.working {
    .board-column-header {
      background: #f9944a;
    }
  }
  &.done {
    .board-column-header {
      background: #2ac06d;
    }
  }
}
</style>

