/**
 * trainerService.js — API calls for Trainer plan management
 *
 * GET  /api/trainer/athletes                           → list all athletes
 * GET  /api/trainer/athletes/:userId/workout-plans     → athlete's workout plans
 * GET  /api/trainer/athletes/:userId/meal-plans        → athlete's meal plans
 * PUT  /api/trainer/workout-plans/:id                  → edit a workout plan (with note)
 * PUT  /api/trainer/meal-plans/:id                     → edit a meal plan (with note)
 */
import http from './http'

const trainerService = {
  async getAthletes() {
    const { data } = await http.get('/trainer/athletes')
    return data
  },

  async getAthleteWorkoutPlans(userId) {
    const { data } = await http.get(`/trainer/athletes/${userId}/workout-plans`)
    return data
  },

  async getAthleteMealPlans(userId) {
    const { data } = await http.get(`/trainer/athletes/${userId}/meal-plans`)
    return data
  },

  async editWorkoutPlan(id, payload) {
    // payload: { planName, generatedPlan, trainerNote }
    const { data } = await http.put(`/trainer/workout-plans/${id}`, payload)
    return data
  },

  async editMealPlan(id, payload) {
    // payload: { planName, generatedPlan, trainerNote }
    const { data } = await http.put(`/trainer/meal-plans/${id}`, payload)
    return data
  }
}

export default trainerService
