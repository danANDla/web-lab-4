export const tokenModule = {
    state: () => ({
        token: ""
    }),
    getters: {

    },
    mutations: {
        updateToken(state, newToken){
            state.token = newToken
        }
    },
    actions: {
    },
    namespaced: true
}