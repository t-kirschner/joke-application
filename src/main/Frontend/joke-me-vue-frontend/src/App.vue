<script>
import UserInput from './components/UserInput.vue'

export default {
  components: {
    UserInput
  }
}
</script>

<template>
  <div>
    <h1>Joke-Me!</h1>
    <user-input></user-input>
  </div>
</template>

<style>
h1 {
  text-align: center;
  font-weight: bold;
  margin-bottom: 50px;
}
</style>