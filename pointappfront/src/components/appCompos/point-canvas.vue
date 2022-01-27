<template>
  <div class="imageCell">
      <div class="svg-container">
        <canvas id="responsive-canvas"></canvas>
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
      x: 0,
      y: 0,
      points: []
    }
  },
  methods: {
    init: function(newR) {
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
      console.log(img.src)
      let width = this.canvas.width;
      let height = this.canvas.height;
      img.onload = function () {
        lctx.drawImage(img, 0, 0, width, height);
      }
      //this.drawAll();
    },
    drawAll: function () {
      this.points.forEach(point => {
        if (point.r == this.r) {
          var color = point.hit ? "#59ab42" : "#ab2a3d";
          this.draw(point.x, point.r, color);
        }
      })
    },
    draw: function (posx, posy, color) {
      var radius = this.r;
      console.log("Draw", posx, posy, radius);
      let img = new Image();
      if (this.img_src === undefined) this.img_src = "../../assets/img/r.jpg";
      img.src = this.img_src;
      img.onload = function () {
        this.ctx.fillStyle = color;
        this.ctx.beginPath();
        this.ctx.arc(posx, posy, 2, 0, 2 * Math.PI);
        this.ctx.fill();
        this.ctx.closePath();
      }
    },
    mouse: function (e) {
      var radius = this.r;
      if (typeof radius == 'undefined') {
      } else {
        let posx = this.getMouesPosition(e).x;
        let posy = this.getMouesPosition(e).y;
        posx = parseFloat(posx);
        posy = parseFloat(posy);
        let offsetx = (this.canvas.width * 150) / 300;
        let offsety = (this.canvas.height * 150) / 300;
        this.xsend = (posx - offsetx) * radius * 300 / (107 * this.canvas.width);
        this.ysend = -(posy - offsety) * radius * 300 / (107 * this.canvas.width);
        console.log(this.xsend, this.ysend);
        this.canvasflag = true;
        this.draw(posx, posy, "#000000");
      }
    },
    getMousePosition: function (e) {
      let mouseX = e.offsetX * this.canvas.width / this.canvas.clientWidth | 0;
      let mouseY = e.offsetY * this.height / this.canvas.clientHeight | 0;
      return {x: mouseX, y: mouseY};
    }
  },
  mounted() {
    this.init(0)
  }
  // mounted() {
  //     this.canvas = document.getElementById("responsive-canvas");
  //     var lctx = this.canvas.getContext("2d");
  //
  //     var radius = this.r;
  //     console.log("init canvas", radius);
  //     let heightRatio = 1;
  //     this.canvas.height = this.canvas.width * heightRatio;
  //     let img = new Image();
  //     switch (parseInt(radius.toString())) {
  //       case 0:
  //         this.img_src = require("@/assets/img/0.jpg");
  //         break;
  //       case 1:
  //         this.img_src = require("@/assets/img/1.jpg");
  //         break;
  //       case 2:
  //         this.img_src = require("@/assets/img/2.jpg");
  //         break;
  //       case 3:
  //         this.img_src = require("@/assets/img/3.jpg");
  //         break;
  //       case 4:
  //         this.img_src = require("@/assets/img/4.jpg");
  //         break;
  //       default:
  //         this.img_src = require("@/assets/img/r.jpg");
  //         break;
  //     }
  //     img.src = this.img_src;
  //     console.log(img.src)
  //     let width = this.canvas.width;
  //     let height = this.canvas.height;
  //     img.onload = function () {
  //       lctx.drawImage(img, 0, 0, width, height);
  //     }
  //     //this.drawAll();
  //   },
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