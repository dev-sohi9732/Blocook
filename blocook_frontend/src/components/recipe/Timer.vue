<template>
  <div class="c_timer" style="margin-top:90px; size:120%;" id="timer">

<!-- SVG -->
  <div class="timer-face">
    <svg class="timer-ring" height="260px" width="260px">
      <circle 
        class="timer-ring__bgline" 
        stroke="darkgray" 
        stroke-width="8" 
        fill="transparent" 
        r="125" cx="130" cy="130"
      />
      <circle 
        class="timer-ring__line"
        v-bind:style="{ stroke: started ? '#BB4E75' : '#BB4E75' }"
        stroke-width="8" 
        fill="transparent"
        r="125" cx="130" cy="130"
        :stroke-dashoffset="setProgress"
      />
    </svg>
    <div style="font-size:5rem;" class="timer-face__text">{{ minutes }}:{{ seconds }}</div>
  </div>
  
<!-- BUTTONS -->
  <div class="buttons">
    <button class="button start" v-if="!started" @click="startTimer()">Start</button>
    <button class="button pause" v-if="started" @click="pauseTimer()">Pause</button>
    <button class="button reset" @click="resetTimer">&#8635;</button> 
  </div>
  <button style="float:right; background-color:#a4baf5;" class="button back" @click="backrecipe">Return Recipe</button> 
</div>

<!-- FOR STROKE GRADIENT 

<defs>
  <linearGradient id="Gradient">
    <stop offset="0%" stop-color="#b72cdd" />
    <stop offset="100%" stop-color="#5c96f9" />
  </linearGradient>
</defs> 

stroke="url(#Gradient)"

-->
</template>

<script>
const params = new URL(document.location).searchParams;
export default {
  name: "Timer",
  data() {
    return{
        timer: null,
        time: params.get('time'), // cooking.timerTime
        startTime: params.get('time'),
        started: false,
        radioVal: params.get('time'),
        // proptime: 0,
    }
  },
  methods: {
    startTimer() {
      this.started = true;
      this.timer = setInterval(this.countdown, 1000)
    },
    pauseTimer() {
      this.started = false;
      clearInterval(this.timer);
    },
    resetTimer() {
      this.time = this.radioVal;
      clearInterval(this.timer);
      this.started = false;
    },
    countdown() {
      let time = this.radioVal;
      if(this.time > 0) {
        this.time--;
      } else {
        this.resetTimer();
      }
    },
    formatTime(time) {
      return (time < 10 ? '0' : '') + time;
    },
    backrecipe() {
        this.$router.go(-1)
    }
  },
  watch: {
    radioVal() {
      this.time = this.radioVal;
      this.startTime = this.radioVal;
      this.resetTimer();
    }
  },
  computed: {
    setProgress() {
      const offset = 784 - this.time / this.startTime * 784;
      return -offset;
    },
    minutes() {
      const minutes = Math.floor(this.time / 60);
      return this.formatTime(minutes);
    },
    seconds() {
      const seconds = this.time - (60 * this.minutes);
      return this.formatTime(seconds);
    }
  },

    // created() {
	// 	const params = new URL(document.location).searchParams;
    //     this.proptime = params.get('time')
//   }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css?family=Rubik');

html {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: 'Rubik', serif;
}
.timer-ring__line {
  stroke-linecap: round;
  stroke-dasharray: 784 784;
  transition: stroke-dashoffset 0.35s;
  transform: rotate(-90deg);
  transform-origin: 50% 50%;
}
.c_timer
  {background-image: linear-gradient( 135deg, #f5bfa4 10%, #fc80ab 100%)}
  
#timer
  {display: flex;
  flex-direction: column;
  align-items: center;
  padding: 40px 40px;
  box-shadow: 3px 3px 3px 0px rgba(0,0,0,0.3)
}

.button
  {font-family: 'Rubik', serif;
  font-weight: bold;
  padding: 12px 23px;
  margin-top: 20px;
  border: 0;
  border-radius: 30px;
  color: #fff;
  cursor: pointer;
  transition: 0.2s;
  transform: scale(1.05)}
 
.start
  {background: #BB4E75}
  
.pause
  {background: darken(#BB4E75, 20)}

.reset
  {background: #555}
  
.timer-face
  {position: relative}

.timer-face__text
  {font-weight: bold;
  position: absolute;
  bottom: 80px;
  left: 30px;
  font-size: 2em}
  
.timer-seconds
  {display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 30px;
  border: 2px solid lightgray;
  border-radius: 10px;
  padding: 5px 15px}
  h3
    {margin-bottom: 0}
  .timer-seconds__radios
    {list-style: none;
    margin-left: -45px}
    li
      {display: inline-block;
      margin: 0 10px;
      font-weight: bold}

</style>