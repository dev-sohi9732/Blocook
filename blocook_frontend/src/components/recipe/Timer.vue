<template>
  <div class="c_timer" id="timer">

<!-- SVG -->
  <div class="timer-face">
    <svg class="timer-ring" height="300" width="100%">
      <circle 
        class="timer-ring__bgline" 
        stroke="darkgray" 
        stroke-width="8" 
        fill="transparent" 
        r="40%" cx="50%" cy="150"
      />
      <circle 
        class="timer-ring__line"
        v-bind:style="{ stroke: started ? '#BB4E75' : '#BB4E75' }"
        stroke-width="8" 
        fill="transparent"
        r="40%" cx="50%" cy="150"
        :stroke-dashoffset="setProgress"
      />
    </svg>
    <div style="font-size:4rem;" class="timer-face__text">{{ minutes }}:{{ seconds }}</div>
  </div>
  
<!-- BUTTONS -->
  <div class="buttons">
    <button class="button start" v-if="!started" @click="startTimer()" id="timer_start">Start</button>
    <button class="button pause" v-if="started" @click="pauseTimer()" id="timer_pause">Pause</button>
    <button class="button reset" @click="resetTimer" id="timer_reset">&#8635;</button> 
  </div>
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
// const params = new URL(document.location).searchParams;
export default {
  name: "Timer",
  props: {
        cooking: {
            type: Object,
        },
    },
  data() {
    return{
        timer: null,
        time: 0, // cooking.timerTime
        startTime: 0,
        started: false,
        radioVal: 0,
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
      const offset = 654 - this.time / this.startTime * 654;
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

    created() {
      this.time = this.cooking.timerM *60 + this.cooking.timerS
      this.startTime = this.cooking.timerM *60 + this.cooking.timerS
      this.radioVal = this.cooking.timerM *60 + this.cooking.timerS
  }
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
  padding: 10px 10px;
  box-shadow: 3px 3px 3px 0px rgba(0,0,0,0.3)
}

.button
  {font-family: 'Rubik', serif;
  font-weight: bold;
  padding: 12px 23px;
  margin-top: -10px;
  margin-bottom: 30px;
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
  bottom:36%;
  left:50%; 
  transform:translateX(-50%);
  /* text-align:center; */
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