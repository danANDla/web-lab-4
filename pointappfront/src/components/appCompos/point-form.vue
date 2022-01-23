<template>
  <form v-on:submit.prevent>
    <div class="x-radio">
      <div class="wrappers">
        <div class="value-label">X</div>
        <radio-btn @radioChange="changeXRadioBtn" ref="xComponent"></radio-btn>
      </div>
    </div>

    <div class="y-text">
      <div class="wrappers">
        <div class="value-label">Y</div>
        <input type="text" placeholder="Type Y" v-model="newPoint.y">
      </div>
    </div>
    <div class="r-radio">
      <div class="wrappers">
        <div class="value-label">R</div>
        <radio-btn @radioChange="changeRRadioBtn" ref="rComponent"></radio-btn>
      </div>
    </div>

    <div class="btns-block">
      <div class="main-btns-block">
        <usual-button @click.native="sendData">send</usual-button>
        <usual-button @click.native="resetForm">reset</usual-button>
      </div>
      <div class="side-btns-block">
        <usual-button @click.native="clearData" btn-type="dangerous">clear</usual-button>
      </div>
    </div>
  </form>
</template>

<script>
import UsualButton from "@/components/UI/button";
import RadioBtn from "@/components/UI/radio-btn";

export default {
  name: "point-form",
  components: {RadioBtn, UsualButton},
  data(){
    return{
      newPoint: {
        x: 0,
        y: 0,
        r: 0,
      },
      points: [  ]
    }
  },
  methods:{
    changeXRadioBtn: function (newX){
      this.newPoint.x = newX;
    },
    changeRRadioBtn: function (newR){
      this.newPoint.r = newR;
    },
    sendData: function (){
      this.$emit('pushPoint', this.newPoint);
    },
    clearData: function (){
      this.$emit('clearPoints')
    },
    resetForm: function (){
      this.newPoint.x = 0;
      this.newPoint.y = 0;
      this.newPoint.r = 0;
      this.newPoint.hit = 0;
      this.$refs.xComponent.resetChoice();
      this.$refs.rComponent.resetChoice();
    }
  }
}
</script>

<style scoped>
.x-radio, .y-text, .r-radio, .btns-block{
  margin-top: 5px;
  display: block;
  width: 100%;
  background: #202020;
  padding: 10px;
}

.wrappers{
  display: flex;
}

.value-label{
  margin-left: 2px;
  font-weight: 500;
}

.y-text > .wrappers > input{
  margin-left: 10px;
}

.btns-block{
  display: grid;
  grid-template-columns: 1fr 1fr;
}

.side-btns-block {
  text-align: right;
}

.main-btns-block {
  text-align: left;
}

.main-btns-block > button:first-of-type, .side-btns-block > button:first-of-type {
  margin: 0;
}

</style>
