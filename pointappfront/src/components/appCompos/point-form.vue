<template>
  <div>
    <point-canvas ref="canvasComponent" @pointFromMouse="sendFromMouse"></point-canvas>
    <form v-on:submit.prevent>
      <div class="x-radio">
        <div class="wrappers">
          <div class="value-label">X</div>
          <radio-btn-x @radioChange="changeXRadioBtn" ref="xComponent"></radio-btn-x>
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
          <radio-btn-r @radioChange="changeRRadioBtn" ref="rComponent"></radio-btn-r>
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

      <div class="error-block" v-if="errorShow">
        {{this.error}}
      </div>
    </form>
  </div>
</template>

<script>
import UsualButton from "@/components/UI/button";
import pointCanvas from "@/components/appCompos/point-canvas";
import RadioBtnX from "@/components/UI/radio-btn-x";
import RadioBtnR from "@/components/UI/radio-btn-r";

export default {
  name: "point-form",
  components: {RadioBtnX, RadioBtnR, UsualButton, pointCanvas},
  data(){
    return{
      newPoint: {
        x: 0,
        y: 0,
        r: 1,
        isMouse: false
      },
      points: [  ],
      error: "",
      errorShow: false
    }
  },
  methods:{
    changeXRadioBtn: function (newX){
      this.newPoint.x = newX;
    },
    changeRRadioBtn: function (newR){
      this.newPoint.r = newR;
      this.$refs.canvasComponent.init(newR.toString());
    },
    updateCanvas: function(){
      this.$refs.canvasComponent.redraw();
    },
    drawNew: function(newX, newY, newHit){
      let color = newHit === "hit" ? "#59ab42" : "#ab2a3d";
      this.$refs.canvasComponent.drawFromCoordinates(newX, newY, color)
    },
    sendData: function (){
      if(this.check()) this.$emit('pushPoint', this.newPoint);
    },
    check: function (){
      // let valid = ((this.newPoint.y.match(/^[0-2](\.\d+)?$/) || this.newPoint.y.match(/^-[0-4](\.\d+)?$/)
      //     || this.newPoint.y.match(/^-5$/) || this.newPoint.y.match(/^3$/) || this.newPoint.y.match(/^0$/))
      //     && !this.newPoint.y.match(/^-?0(\.0+)?$/));
      let valid = !(this.newPoint.y.valueOf() > 100 || this.newPoint.y.valueOf() < -5
          || isNaN(this.newPoint.y) || this.newPoint.y === "")
      if(!valid) this.showError("Y should be a number from [-5;3]")
      else this.errorShow = false
      return valid
    },
    showError: function (msg){
        this.error = msg;
        this.errorShow = true;
    },
    sendFromMouse: function(newX, newY, newR){
      let fromMouse = {
        x: newX,
        y: newY,
        r: newR,
        isMouse: "true"
      };
      this.$emit('pushPoint', fromMouse);
    },
    clearData: function (){
      this.$emit('clearPoints')
    },
    resetForm: function (){
      this.newPoint.x = 0;
      this.newPoint.y = 0;
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

.error-block{
  margin-top: 5px;
  display: block;
  width: 100%;
  background: #202020;
  padding: 10px;
  text-align: center;
  color: #c1364c;
  font-weight: 600;
}
</style>
