/**
 * appointmentService.js — API calls for UC008/UC009/UC012
 *
 * GET  /api/appointments/trainers        → list trainers for booking form
 * POST /api/appointments                 → UC008 create appointment
 * GET  /api/appointments                 → UC009 athlete's appointments
 * PUT  /api/appointments/:id/edit        → UC009 edit pending appointment
 * PUT  /api/appointments/:id/cancel      → UC009 cancel pending appointment
 * GET  /api/appointments/trainer         → UC012 trainer's appointments
 * PUT  /api/appointments/:id/approve     → UC012 approve
 * PUT  /api/appointments/:id/reject      → UC012 reject
 */
import http from './http'

const appointmentService = {
  async getAvailableTrainers() {
    const { data } = await http.get('/appointments/trainers')
    return data
  },

  async createAppointment(payload) {
    const { data } = await http.post('/appointments', payload)
    return data
  },

  async getAthleteAppointments() {
    const { data } = await http.get('/appointments')
    return data
  },

  async editAppointment(id, payload) {
    const { data } = await http.put(`/appointments/${id}/edit`, payload)
    return data
  },

  async cancelAppointment(id) {
    const { data } = await http.put(`/appointments/${id}/cancel`)
    return data
  },

  async getTrainerAppointments() {
    const { data } = await http.get('/appointments/trainer')
    return data
  },

  async approveAppointment(id, trainerRemarks = '') {
    const { data } = await http.put(`/appointments/${id}/approve`, { trainerRemarks })
    return data
  },

  async rejectAppointment(id, trainerRemarks = '') {
    const { data } = await http.put(`/appointments/${id}/reject`, { trainerRemarks })
    return data
  }
}

export default appointmentService
