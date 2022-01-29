<template>
  <div class="imageCell">
    <div class="svg-container">
      <canvas id="responsive-canvas" @mousedown="mouse"></canvas>
    </div>
  </div>
</template>

<script>
export default {
  name: "point-canvas",
  data() {
    return {
      img_src: "",
      ctx: undefined,
      canvas: undefined,
      points: this.$store.state.array.points,
      x: 0,
      y: 0,
      r: 1,
    }
  },
  methods: {
    init: function (newR) {
      this.canvas = document.getElementById("responsive-canvas");
      const lctx = this.canvas.getContext("2d");

      let heightRatio = 1;
      this.canvas.height = this.canvas.width * heightRatio;
      let img = new Image();
      switch (parseInt(newR)) {
        case 0:
          this.img_src = require("@/assets/img/0.jpg");
          break;
        case 1:
          this.img_src = require("@/assets/img/1.jpg");
          break;
        case 2:
          this.img_src = require("@/assets/img/2.jpg");
          break;
        case 3:
          this.img_src = require("@/assets/img/3.jpg");
          break;
        case 4:
          this.img_src = require("@/assets/img/4.jpg");
          break;
        default:
          this.img_src = require("@/assets/img/r.jpg");
          break;
      }
      img.src = this.img_src;
      let width = this.canvas.width;
      let height = this.canvas.height;
      img.onload = function () {
        lctx.drawImage(img, 0, 0, width, height);
      }
      this.r = newR
      this.drawAll();
    },
    redraw: function() {
      this.canvas = document.getElementById("responsive-canvas");
      const lctx = this.canvas.getContext("2d");
      let heightRatio = 1;
      this.canvas.height = this.canvas.width * heightRatio;
      let img = new Image();
      if(this.img_src === undefined) this.img_src = require("@/assets/img/r.jpg");
      img.src = this.img_src;
      let width = this.canvas.width;
      let height = this.canvas.height;
      img.onload = function () {
        lctx.drawImage(img, 0, 0, width, height);
      }
    },
    drawAll: function () {
      this.points = this.$store.state.array.points;
      let array = this.points
      const lctx = this.canvas.getContext("2d");
      let img = new Image();
      if (this.img_src === undefined) this.img_src = "../../assets/img/r.jpg";
      img.src = this.img_src;
      let w = this.canvas.width;
      let h = this.canvas.height;
      let radius = this.r;
      img.onload = function (){
        array.forEach(point => {
          if (point.r == radius) {
            let offsetx = (w * 150) / 300;
            let offsety = (h * 150) / 300;
            let posx = offsetx + point.x / radius * 107 / 300 * w;
            let posy = offsety - point.y / radius * 107 / 300 * w;
            let color = point.hit ? "#59ab42" : "#ab2a3d";
            console.log("Draw point: ", posx, posy, color)
            lctx.fillStyle = color;
            lctx.beginPath();
            lctx.arc(posx, posy, 2, 0, 2 * Math.PI);
            lctx.fill();
            lctx.closePath();
          }
        })
      }
    },
    draw: function (posx, posy, color) {
      console.log("Draw point: ", posx, posy, color)
      const lctx = this.canvas.getContext("2d");
      var radius = this.r;
      let img = new Image();
      if (this.img_src === undefined) this.img_src = "../../assets/img/r.jpg";
      img.src = this.img_src;
      img.onload = function () {
        lctx.fillStyle = color;
        lctx.beginPath();
        lctx.arc(posx, posy, 2, 0, 2 * Math.PI);
        lctx.fill();
        lctx.closePath();
      }
    },
    drawFromCoordinates: function(newX, newY, color){
      let offsetx = (this.canvas.width * 150) / 300;
      let offsety = (this.canvas.height * 150) / 300;
      let posx = offsetx + newX / this.r * 107 / 300 * this.canvas.width;
      let posy = offsety - newY / this.r * 107 / 300 * this.canvas.width;
      this.draw(posx, posy, color);
    },
    mouse: function (e) {
      var radius = this.r;
      if (typeof radius == 'undefined') {
      }
      else {
        let mouseX = e.offsetX * this.canvas.width / this.canvas.clientWidth | 0;
        let mouseY = e.offsetY * this.canvas.height / this.canvas.clientHeight | 0;
        let posx = mouseX;
        let posy = mouseY;
        let offsetx = (this.canvas.width * 150) / 300;
        let offsety = (this.canvas.height * 150) / 300;
        let xsend = (posx - offsetx) * radius * 300 / (107 * this.canvas.width);
        let ysend = -(posy - offsety) * radius * 300 / (107 * this.canvas.width);
        console.log(xsend, ysend);
        this.canvasflag = true;
        this.draw(posx, posy, "#000000");
        this.$emit('pointFromMouse', xsend, ysend, radius)
      }
    },
  },
  mounted() {
    this.init(this.r)
  }
}

</script>

<style scoped>
.imageCell {
  text-align: center;
  background: white;
  width: 100%;
}

.svg-container {
  display: inline-block;
  position: relative;
  vertical-align: middle;
  overflow: hidden;
}

#responsive-canvas {
  width: 100%;
}
</style>