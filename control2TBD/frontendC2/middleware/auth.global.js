function isTokenExpired(token) {
    if (!token) {
        return true; // Interpret no token as "expired"
    }
    const payloadBase64 = token.split('.')[1];
    const decodedJson = atob(payloadBase64);
    const decoded = JSON.parse(decodedJson);
    const exp = decoded.exp * 1000; // Convert to milliseconds
    const isExpired = Date.now() > exp;
    return isExpired;
}

export default defineNuxtRouteMiddleware((to, from) => {
    if (import.meta.client) {  // Ensure this code runs only on the client
        if (to.path === '/register' || to.path === '/login') {
            return;  // Do nothing if we're already on the registration page
        }
        const token = localStorage.getItem('authToken');
        if (!token || isTokenExpired(token)) {
            // Redirect to login if there is no token or it's expired
            return navigateTo('/login');
        }
    }
});
