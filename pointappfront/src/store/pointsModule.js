export const pointsModule = {
    state: () => ({
        points: []
    }),
    getters: {
        getPoints(state){
            return state.points
        }
    },
    mutations: {
        updatePoints(state, newArray){
            state.points = newArray
        },
    },
    actions: {
    },
    namespaced: true
}