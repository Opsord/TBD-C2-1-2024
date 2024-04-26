<script setup lang="ts">
import { z } from "zod"
import type { FormSubmitEvent } from "#ui/types"

const schema = z.object({
    name: z.string().min(3, 'Ingrese su nombre'),
    surname: z.string().min(3, 'Ingrese su apellido'),
    username: z.string().min(4, 'Ingrese nombre de usuario'),
    password: z.string().min(8, 'Contraseña debe ser de al menos 8 caracteres.')
})

type Schema = z.output<typeof schema>


const state = reactive({
    name: undefined,
    surname: undefined,
    username: undefined,
    password: undefined


})

const registerPost = async () => {
    try {
        const response = await fetch('http://localhost:8091/user/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(state)
        });

        if (!response.ok) {
            throw new Error('Failed to register');
        }

        const result = await response.json();
        if (result) {
            const token = response.headers.get('Authorization');
            localStorage.setItem('authToken', token as string);  // Store the token in localStorage
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

async function onSubmit(event: FormSubmitEvent<Schema>) {

    await registerPost()

}


</script>

<template>
    <div class=" flex flex-col justify-center items-center flex-grow space-y-12">
        <div class=" drop-shadow-lg">
            <UForm :schema="schema" :state="state" class="space-y-4" @submit="onSubmit">
                <UFormGroup label="Nombre" name="name">
                    <UInput v-model="state.name" />
                </UFormGroup>

                <UFormGroup label="Apellido" name="surname">
                    <UInput v-model="state.surname" />
                </UFormGroup>

                <UFormGroup label="Nombre de usuario" name="username">
                    <UInput v-model="state.username" />
                </UFormGroup>

                <UFormGroup label="Contraseña" name="password">
                    <UInput v-model="state.password" type="password" />
                </UFormGroup>

                <UButton label="Registrarse" type="submit" class=" text-center flex items-center justify-center " />
            </UForm>
        </div>
        <div class="drop-shadow-lg space-y-2 flex flex-col">
            <h1> ¿Ya estas registrado?</h1>
            <UButton class=" text-center flex items-center justify-center" label="Log in"
                @click="navigateTo('login')" />
        </div>
    </div>
</template>