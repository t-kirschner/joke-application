<script>
import axios from 'axios'

export default {
  data() {
    return {
      inputLanguage: '1',
      apiResult: ''
    }
  },
  methods: {
    // action triggered when clicking "Submit" button. Sends selected option in dropdown menu to backend.
    // in bigger applications this method would be outsourced for multible usage. But for this small application this is not necessary.
    handleClick() {
      axios.post('http://localhost:8080/', this.inputLanguage)
        .then(response => {
          this.apiResult = response.data;
        })
        .catch(error => {
          this.apiResult = error.response.data;
          //this.apiResult = "No connection to service. Please restart application and try again."
          console.error(error)
        })
    }
  }
}
</script>


<template>
  <div>
    <div class="form-group">
      <div>
        <label for="joke-type" class="form-label">Joke Type:</label>
        <span id="joke-type">Programming</span>
      </div>
    </div>

    <div class="form-group">
      <label for="language-dropdown" class="form-label">Language:</label>
      <select id="language-dropdown" v-model="inputLanguage" class="form-control">
        <option value="1">English</option>
        <option value="2">German</option>
      </select>
    </div>

    <div class="form-group">
      <button @click="handleClick" class="btn">Submit</button>
    </div>

    <div class="form-group">
      <div>
        <label for="joke-result" class="result-label">Result:</label>
        <span id="joke-result" class="result-value">{{ apiResult }}</span>
      </div>
    </div>
  </div>
</template>