import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import RegisterView from '@/views/RegisterView.vue'
import LoginView from '@/views/LoginView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },

    {
      path: '/register',
      name: 'register',
      component: RegisterView,
    },

    {
      path: '/login',
      name: 'login',
      component: LoginView,
    },
  ]
})

function isTokenExpired(token: string | null) {
  if (!token || token.length < 1) {
    return true; // Interpret no token as "expired"
  }
  const payloadBase64 = token.split('.')[1];
  const decodedJson = atob(payloadBase64);
  const decoded = JSON.parse(decodedJson);
  const exp = decoded.exp * 1000; // Convert to milliseconds
  const isExpired = Date.now() > exp;
  return isExpired;
}


router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('authToken');
  const isExpired = isTokenExpired(token);

  // If the token is expired or not present, allow access to login or register routes
  if (isExpired) {
    if (to.name === 'login' || to.name === 'register') {
      next(); // Proceed to the requested route
    } else {
      // Redirect to the login page if the user tries to access any other route
      next({ name: 'login' });
    }
  } else {
    // If the token is not expired, redirect to a different route (e.g., 'home')
    // This prevents users from accessing login or register pages when they are already logged in
    if (to.name === 'login' || to.name === 'register') {
      next({ name: 'home' }); // Redirect to the home page or any other appropriate route
    } else {
      next(); // Proceed to the requested route
    }
  }
});


export default router
