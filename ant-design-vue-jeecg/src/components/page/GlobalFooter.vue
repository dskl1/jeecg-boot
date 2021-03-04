<template>
  <div class="footer">
    <div class="copyright">
      Copyright &copy; {{ date[0] + date[1] + date[2] + date[3] }} 江苏易云科技有限公司
    </div>
  </div>
</template>

<script>
export default {
  name: 'LayoutFooter',
  data() {
    return {
      date: new Date(),
    }
  },
  mounted() {
    let _this = this
    this.timer = setInterval(function () {
      _this.date = new Date().toLocaleString()
    })
  },
}
</script>

<style lang="less" scoped>
.footer {
  padding: 0 16px;
  margin: 48px 0 24px;
  text-align: center;

  .links {
    margin-bottom: 8px;

    a {
      color: rgba(0, 0, 0, .45);

      &:hover {
        color: rgba(0, 0, 0, .65);
      }

      &:not(:last-child) {
        margin-right: 40px;
      }
    }
  }

  .copyright {
    color: rgba(0, 0, 0, .45);
    font-size: 14px;
  }
}
</style>