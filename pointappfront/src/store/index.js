import { createStore } from 'vuex'
import createPersistedState from "vuex-persistedstate";
import * as Cookies from 'js-cookie'
import {tokenModule} from "@/store/tokenModule";
import {pointsModule} from "@/store/pointsModule";

export default createStore({
  modules: {
    auth: tokenModule,
    array: pointsModule
  },
  plugins: [
    createPersistedState({
  // //     storage:{
  // //       getState: (key) => Cookies.getJSON(key),
  // //       setState: (key, state) => Cookies.set(key, state, { expires: 3, secure: true })
  // //     }
      storage: window.sessionStorage
    })
  ]
})
