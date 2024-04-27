<script setup lang="ts">
import { z } from "zod"
import { ref } from "vue";
import InputText from "primevue/inputtext";
import Button from "primevue/button";

import { useRouter } from "vue-router";

const router = useRouter()

const schema = z.object({
    username: z.string().min(4, 'Ingrese nombre de usuario'),
    password: z.string().min(8, 'Contraseña debe ser de al menos 8 caracteres.')
})

const state = ref({
    username: '',
    password: ''


})

const registerPost = async () => {
    try {
        const response = await fetch('http://localhost:8091/user/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(state.value)
        });

        if (!response.ok) {
            throw new Error('Failed to register');
        }

        const result = await response.json();
        if (result) {
            const token = response.headers.get('Authorization');
            localStorage.setItem('authToken', token as string);  // Store the token in localStorage
            localStorage.setItem('idUser', result.id)
            console.log(token)
            console.log('Registration successful, token stored');
            // Optionally, redirect the user or perform other actions
        } else {
            console.error('Token was not provided in the response');
            // Handle cases where the token is not provided
        }
        // Handle token and user data here, e.g., storing the token or redirecting the user
    } catch (error) {
        console.error('Registration error:', error);
    }
};

const errors = ref("")
async function onSubmit(event: Event) {
    event.preventDefault(); // Prevent default form submission
    try {
        schema.parse(state.value); // Validate the form data
        await registerPost();
        router.push('/')

    } catch (error) {
        errors.value = "Contraseña debe ser de 8 caracters"
        console.error('Validation error:', error);
        // Handle validation errors, e.g., show error messages in the UI
    }
}
</script>

<template>
    <div class=" h-full flex flex-col items-center justify-center align-middle gap-2 ">
        <h1 v-if="state.username !== undefined && state.username.length < 4"> Ingrese su nombre de usuario </h1>
        <InputText type="text" v-model="state.username" />
        <h1 v-if="state.password !== undefined && state.password.length < 8"> Ingrese su contraseña</h1>
        <InputText type="password" v-model="state.password" />
        <Button label="Ingresar" @click="onSubmit" />
        <h1 v-if="errors">Intente de nuevo</h1>

    </div>

</template>