import { createStore } from 'vuex'
import {tokenModule} from "@/store/tokenModule";

export default createStore({
  modules: {
    auth: tokenModule
  }
})
