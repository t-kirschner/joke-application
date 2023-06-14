<script>
import UserInterface from './UserInterface.vue'

export default {
  components: {
    UserInterface
  }
}
</script>

<template>
  <div>
    <h1>Joke Application</h1>
    <user-interface></user-interface>
  </div>
</template>

<style>
h1 {
  text-align: center;
  font-weight: bold;
  margin-bottom: 50px;
}
</style>