<template>
  <div>
    <a-date-picker
      :value="time"
      :open="isopen"
      mode="year"
      placeholder='请选择年份'
      format="YYYY"
      @openChange="handleOpenChange"
      @panelChange="handlePanelChange"
      @change="clearValue"></a-date-picker>
  </div>
</template>

<script>

    export default {
        name: "YearPicker",
        data () {
            return {
                isopen:false,
                time:null,
            }
        },
        methods :{
            handlePanelChange (value) {
                console.log(">>>>>", value)
                this.time = value;
                this.isopen = false;
            },
            handleOpenChange(status) {
                 console.log(status)
                if(status){
                    this.isopen = status;
                } else {
                    this.isopen = status;
                }
            },
            clearValue()  {
                this.time = null;
            }
        }
    }
</script>

<style scoped>

</style>