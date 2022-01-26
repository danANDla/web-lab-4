export const tokenModule = {
    state: () => ({
        token: "",
        login: ""
    }),
    getters: {

    },
    mutations: {
        updateToken(state, newToken){
            state.token = newToken
        },
        updateLogin(state, newLogin){
            state.login = newLogin
        }
    },
    actions: {
    },
    namespaced: true
}